package mod.sin.wyvernmods.bounty;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.logging.Logger;

import org.gotti.wurmunlimited.modloader.ReflectionUtil;
import org.gotti.wurmunlimited.mods.creatures.Reaper;
import org.gotti.wurmunlimited.mods.creatures.Hlesey.Aegir;
import org.gotti.wurmunlimited.mods.creatures.SpectralDrake;
import org.gotti.wurmunlimited.mods.wyvernmods.Arena;
import org.gotti.wurmunlimited.mods.wyvernmods.MiscChanges;

import com.wurmonline.mesh.Tiles;
import com.wurmonline.server.FailedException;
import com.wurmonline.server.HistoryManager;
import com.wurmonline.server.Players;
import com.wurmonline.server.creatures.Creature;
import com.wurmonline.server.items.Item;
import com.wurmonline.server.items.ItemFactory;
import com.wurmonline.server.items.ItemTemplate;
import com.wurmonline.server.items.NoSuchTemplateException;
import com.wurmonline.server.players.Player;
import com.wurmonline.server.players.Titles.Title;
import com.wurmonline.server.Server;
import com.wurmonline.server.creatures.CreatureTemplate;
import com.wurmonline.server.creatures.CreatureTemplateFactory;
import com.wurmonline.server.creatures.Creatures;
import com.wurmonline.server.items.ItemTemplateFactory;
import com.wurmonline.server.villages.Village;
import com.wurmonline.server.villages.Villages;
import com.wurmonline.server.items.ItemList;
import mod.sin.armour.SpectralArmor.SpectralHide;
import mod.sin.items.AffinityOrb;



public class MethodsBounty {
	protected static Logger logger = Logger.getLogger(MethodsBounty.class.getName());
	public static HashMap<Long, Map<Long, Double>> dealtDamage = new HashMap<Long, Map<Long, Double>>();

	// Using a hook in CombatEngine.addWound, we call this function to create a list of creatures that actually inflicted damage.
	public static void addDealtDamage(long defender, long attacker, double damage){
		if(dealtDamage.containsKey(defender)){
			Map<Long, Double> dealers = dealtDamage.get(defender);
			if(!dealers.containsKey(attacker)){
				dealers.put(attacker, damage);
			}else{
				double newDam = dealers.get(attacker);
				newDam += damage;
				dealers.put(attacker, newDam);
			}
		}else{
			Map<Long, Double> dealers = new HashMap<Long, Double>();
			dealers.put(attacker, damage);
			dealtDamage.put(defender, dealers);
		}
	}

    public static long lastAttacked(Map<Long, Long> attackers, long playerId){
    	return System.currentTimeMillis()-attackers.get(playerId);
    }

	public static boolean isCombatant(Map<Long, Long> attackers, long playerId){
    	long now = System.currentTimeMillis();
    	long delta = now-attackers.get(playerId);
    	if(delta > 120000){
    		return false;
    	}
    	return true;
    }

	public static Map<Long, Long> getAttackers(Creature mob){
		try {
			return ReflectionUtil.getPrivateField(mob, ReflectionUtil.getField(mob.getClass(), "attackers"));
		} catch (IllegalArgumentException | IllegalAccessException | ClassCastException | NoSuchFieldException e) {
			e.printStackTrace();
		}
		return null;
	}





	public static double getCreatureStrength(Creature mob){
		float combatRating = mob.getBaseCombatRating() + mob.getBonusCombatRating();
	    float maxDmg = Math.max(mob.getTemplate().getBreathDamage(), mob.getHandDamage());
	    maxDmg = Math.max(maxDmg, mob.getBiteDamage());
	    maxDmg = Math.max(maxDmg, mob.getKickDamage());
	    maxDmg = Math.max(maxDmg, mob.getHeadButtDamage());
	    double fighting = mob.getFightingSkill().getKnowledge();
	    double weaponlessFighting = mob.getWeaponLessFightingSkill().getKnowledge();
	    double fs = Math.max(fighting, weaponlessFighting);
	    double bodyStr = mob.getBodyStrength().getKnowledge();
	    double cretStr = 2000D + ((double)combatRating*(double)maxDmg*Math.sqrt(fs)*bodyStr);
	    //logger.info("pre-armour: "+cretStr);
	    //cretStr /= Math.max(mob.getArmourMod(), 0.001d);
	    fs /= mob.getArmourMod();
	    cretStr = 500D + ((double)combatRating*Math.cbrt(maxDmg)*Math.sqrt(fs)*Math.cbrt(bodyStr));
	    cretStr *= 2d;
	    //logger.info("post-armour: "+cretStr);
	    //cretStr *= 1-(Math.min(Math.max(mob.getArmourMod(), 0.001d), 0.8f));
	    //cretStr = 2000D + ((double)combatRating*(double)maxDmg*Math.sqrt(fs)*bodyStr);
	    double k = 100000000d;
	    cretStr = (cretStr*Math.pow(2, (-(cretStr/k)))+k*(1-Math.pow(2, -cretStr/k)))/(1+Math.pow(2, -cretStr/k));
	    if(cretStr < 500D){
	    	cretStr *= 1+(Server.rand.nextFloat()*0.2f);
	    	cretStr = Math.max(cretStr, 500D);
	    }
	    //logger.info("capped: "+cretStr);
	    return cretStr;
	}

	public static void checkLootTable(Creature mob, Item corpse) throws Exception{
    	Random random = new Random();
	    double cretStr = getCreatureStrength(mob);
	    boolean hasCrystals = false;
	    // Award chaos crystals if the strength is high enough:


	    boolean sendLootHelp = false;
	    // Begin loot table drops
    	if(mob.getTemplate().getTemplateId() == Reaper.templateId){
    		Server.getInstance().broadCastNormal("The "+mob.getName()+" has been slain.");
    		sendLootHelp = true;
    	}else if(mob.getTemplate().getTemplateId() == SpectralDrake.templateId){
    		logger.info("Generating spectral hide for the corpse of the "+mob.getName()+".");
    		for(int i = 0; i < 2; i++){
	    		Item spectralHide = ItemFactory.createItem(SpectralHide.templateId, 50+(50*random.nextFloat()), ""); // Spectral Hide ID: 22764
	    		ItemTemplate itemTemplate = spectralHide.getTemplate();
				int weightGrams = itemTemplate.getWeightGrams();
				spectralHide.setWeight((int)((weightGrams*0.5f)+(weightGrams*0.5f*random.nextFloat())), true);
				corpse.insertItem(spectralHide);
				if(!mob.getStatus().isChampion()){
					break;
				}
	    	}
    		Server.getInstance().broadCastNormal("The "+mob.getName()+" has been slain.");
    		sendLootHelp = true;
    	}else if(Arena.isTitan(mob)){
    		Server.getInstance().broadCastAlert("The boss "+mob.getName()+" has been defeated!");
    		MiscChanges.sendGlobalFreedomChat(mob, "The boss "+mob.getName()+" has been defeated!", 255, 105, 180);
    		MiscChanges.sendServerTabMessage("The boss "+mob.getName()+" has been defeated!", 255, 105, 180);
    		Arena.removeTitan(mob);
    		sendLootHelp = true;
    	}
		if(mob.getTemplate().getTemplateId() == CreatureTemplateFactory.GOBLIN_CID){
			// Random lump of metal from goblins.
			try{
				int[] lumpIds = {
						//44, 45, 46, 47, 48, 49, 205, 221, 223, 220
						ItemList.brassBar,
						ItemList.bronzeBar,
						ItemList.copperBar,
						ItemList.goldBar,
						ItemList.ironBar,
						ItemList.leadBar,
						ItemList.silverBar,
						ItemList.steelBar,
						ItemList.zincBar
				};
				Item randomLump = ItemFactory.createItem(lumpIds[random.nextInt(lumpIds.length)], 20+(60*random.nextFloat()), "");
				corpse.insertItem(randomLump);
			} catch (FailedException | NoSuchTemplateException e) {
				e.printStackTrace();
			}
		}
    	if(mob.isUnique()){
    		// Spawn random addy/glimmer veins throughout the world
			int i = 20;
			while(i > 0){
				int x = random.nextInt(Server.surfaceMesh.getSize());
				int y = random.nextInt(Server.surfaceMesh.getSize());
				short height = Tiles.decodeHeight(Server.surfaceMesh.getTile(x, y));
				int type = Tiles.decodeType((int)Server.caveMesh.getTile(x, y));
				if(height >= 100 && (type == Tiles.Tile.TILE_CAVE_WALL.id || type == Tiles.Tile.TILE_CAVE.id)){
					Tiles.Tile tileType = random.nextBoolean() ? Tiles.Tile.TILE_CAVE_WALL_ORE_ADAMANTINE : Tiles.Tile.TILE_CAVE_WALL_ORE_GLIMMERSTEEL;
					Server.caveMesh.setTile(x, y, Tiles.encode(Tiles.decodeHeight(Server.caveMesh.getTile(x, y)), tileType.id, Tiles.decodeData(Server.caveMesh.getTile(x, y))));
					Players.getInstance().sendChangedTile(x, y, false, true);
					Server.setCaveResource(x, y, 400+random.nextInt(600));
					Village v = Villages.getVillage(x, y, true);
			        if (v == null) {
			            for (int vx = -50; vx < 50; vx += 5) {
			                for (int vy = -50; vy < 50 && (v = Villages.getVillage(x + vx, y + vy, true)) == null; vy += 5) {
			                }
			            }
			        }
			        if(v != null){
			        	HistoryManager.addHistory(mob.getTemplate().getName(), "blesses the world with a "+tileType.getName()+" near "+v.getName()+"!");
			        }
					logger.info("Placed a "+tileType.getName()+" at "+x+", "+y+" - "+height+" height");
					i--;
				}
			}
			Server.getInstance().broadCastAlert("The death of the "+mob.getTemplate().getName()+" has blessed the world with valuable ores!");
			// Spawn 5-10 friyan tablets throughout the world.


			// Spawn Spectral Drake
    		if (mob.isDragon()) {
    			int mTemplate = mob.getTemplate().getTemplateId();
    			int lootTemplate = 371;
    			byte ctype = 0;
    			if(mTemplate == 16 || mTemplate == 89 || mTemplate == 91 || mTemplate == 90 || mTemplate == 92){
    				ctype = 99;
    				lootTemplate = 372;
    			}else{
    				ctype = (byte)Math.max(0, Server.rand.nextInt(17) - 5);
    			}

    			float x = mob.getPosX();
    			float y = mob.getPosY();

				// Spawn the spectral drake.
    			logger.info("Spawning a spectral drake.");
    			CreatureTemplate template = CreatureTemplateFactory.getInstance().getTemplate(SpectralDrake.templateId); // Spectral Drake ID: 2147483646
    			Creature spectralDrake = Creature.doNew(template.getTemplateId(), true, x, y, random.nextFloat()*360.0f, mob.getLayer(),
    					template.getName(), (byte)0, mob.getKingdomId(), ctype, false, (byte)150);
    			Server.getInstance().broadCastAction("The spirit of the "+mob.getTemplate().getName()+" is released into the world!", mob, 20);
	    		Server.getInstance().broadCastAlert(spectralDrake.getName()+" is released from the soul of the "+mob.getTemplate().getName()+", seeking vengeance for its physical form!");

	    		// Insert extra hide / scale

    			logger.info("Generating extra hide & scale to insert on the corpse of "+mob.getName()+".");
    			ItemTemplate itemTemplate = ItemTemplateFactory.getInstance().getTemplate(lootTemplate);
    			for(i = 0; i < 2; i++){
	    			Item loot = ItemFactory.createItem(lootTemplate, 80+(15*random.nextFloat()), "");
	                String creatureName = mob.getTemplate().getName().toLowerCase();
	                if (!loot.getName().contains(creatureName)){
	                    loot.setName(creatureName.toLowerCase() + " " + itemTemplate.getName());
	                }
	    			loot.setData2(mTemplate);
	    			int weightGrams = itemTemplate.getWeightGrams() * (lootTemplate == 371 ? 3 : 1);
	    			loot.setWeight((int)((weightGrams*1.10f)+(weightGrams*1.00f*random.nextFloat())), true);
	    			corpse.insertItem(loot);
    			}
    			for(i = 0; i < 4; i++){
	    			Item loot = ItemFactory.createItem(lootTemplate, 80+(15*random.nextFloat()), "");
	                String creatureName = mob.getTemplate().getName().toLowerCase();
	                if (!loot.getName().contains(creatureName)){
	                    loot.setName(creatureName.toLowerCase() + " " + itemTemplate.getName());
	                }
	    			loot.setData2(mTemplate);
	    			int weightGrams = itemTemplate.getWeightGrams() * (lootTemplate == 371 ? 3 : 1);
	    			loot.setWeight((int)((weightGrams*1.00f)+(weightGrams*1.00f*random.nextFloat())), true);
	    			spectralDrake.getInventory().insertItem(loot);
    			}
    		} else {
				// Spawn the reaper
    			byte ctype = (byte)Math.max(0, Server.rand.nextInt(17) - 5);
	    		CreatureTemplate template = CreatureTemplateFactory.getInstance().getTemplate(Reaper.templateId); // Reaper ID: 2147483647
	    		Creature reaper = Creature.doNew(template.getTemplateId(), true, mob.getPosX(), mob.getPosY(), random.nextFloat()*360.0f, mob.getLayer(),
	    				template.getName(), (byte)0, mob.getKingdomId(), ctype, false, (byte)150);
	    		Server.getInstance().broadCastAction("The death of the "+mob.getTemplate().getName()+" attracts a powerful being from below, seeking to claim it's soul.", mob, 20);
	    		Server.getInstance().broadCastAlert(reaper.getName()+" is released from the underworld, seeking the soul of a powerful creature!");
    		} // else
    		sendLootHelp = true;
    	}

    	if(sendLootHelp){
			logger.info("Beginning loot assistance message generation...");
			ArrayList<String> atkNames = new ArrayList<String>();
			Map<Long, Long> attackers = getAttackers(mob);
			if(attackers != null){
				for(Long wid : attackers.keySet()){
					Creature cret = Creatures.getInstance().getCreatureOrNull(wid);
					if(cret != null && cret.isPlayer()){
						atkNames.add(Players.getInstance().getPlayer(wid).getName());
					}
				}
				if(atkNames.size() > 0){
					String atkStrBuilder = "Loot Assistance <Attackers> ("+mob.getName()+"): ";
		    		while(atkNames.size() > 0){
		    			int index = Server.rand.nextInt(atkNames.size());
		    			atkStrBuilder += atkNames.get(index);
		    			atkNames.remove(index);
		    			if(atkNames.size() > 0){
		    				atkStrBuilder += ", ";
		    			}
		    		}
		    		MiscChanges.sendServerTabMessage(atkStrBuilder, 0, 128, 255);
		    		logger.info("Broadcast loot assistance message success [Attackers].");
				}else{
	    			logger.warning("Powerful creature "+mob.getName()+" died, but no players were credited to its death [Attackers].");
				}
			}else{
				logger.warning("Attackers was null for creature "+mob.getName()+" [Attackers].");
			}
    		if(dealtDamage.containsKey(mob.getWurmId())){
    			logger.info("Found the damageDealt entry, parsing...");
	    		ArrayList<String> names = new ArrayList<String>();
	    		ArrayList<Double> damages = new ArrayList<Double>();
	    		for(long creatureId : dealtDamage.get(mob.getWurmId()).keySet()){
	    			if(Players.getInstance().getPlayerOrNull(creatureId) != null){
	    				names.add(Players.getInstance().getPlayerOrNull(creatureId).getName());
	    				damages.add(dealtDamage.get(mob.getWurmId()).get(creatureId));
	    			}else{
	    				if(Creatures.getInstance().getCreatureOrNull(creatureId) != null){
	    					logger.info("Skipping creature "+Creatures.getInstance().getCreatureOrNull(creatureId).getName()+" in loot assistance.");
	    				}
	    			}
	    		}
	    		logger.info("Names have been added: "+names);
	    		String strBuilder = "Loot Assistance <Damagers> ("+mob.getName()+"): ";
	    		DecimalFormat formatter = new DecimalFormat("#,###,###");
	    		while(names.size() > 0){
	    			int index = Server.rand.nextInt(names.size());
	    			strBuilder += names.get(index);
	    			strBuilder += " ["+formatter.format(Math.round(damages.get(index)))+"]";
	    			names.remove(index);
	    			damages.remove(index);
	    			if(names.size() > 0){
	    				strBuilder += ", ";
	    			}
	    		}
	    		MiscChanges.sendServerTabMessage(strBuilder, 0, 128, 255);
	    		logger.info("Broadcast loot assistance message success [Damage].");
    		}else{
    			logger.warning("Powerful creature "+mob.getName()+" died, but no players were credited to its death [Damage].");
    		}
    	}

    }





	public static void checkPlayerReward(Player player, Creature mob){
		try{
    		Random random = new Random();
			double fightskill = player.getFightingSkill().getKnowledge();
			int mobTemplateId = mob.getTemplate().getTemplateId();
    		if(dealtDamage.containsKey(mob.getWurmId()) && dealtDamage.get(mob.getWurmId()).containsKey(player.getWurmId())){

		    	if((mobTemplateId == Reaper.templateId || mobTemplateId == SpectralDrake.templateId|| mobTemplateId == Aegir.templateId) && fightskill >= 10){
		    		// Award affinity orb:
		    		Item affinityOrb = ItemFactory.createItem(AffinityOrb.templateId, 99+(1*random.nextFloat()), "");
				    player.getInventory().insertItem(affinityOrb);
				    // Award enchant orb:

		    		// Add spectral hide for spectral drakes:
		    		if(mob.getTemplate().getTemplateId() == SpectralDrake.templateId){
			    		Item spectralHide = ItemFactory.createItem(SpectralHide.templateId, 70+(30*random.nextFloat()), ""); // Spectral Hide ID: 22764
			    		ItemTemplate itemTemplate = spectralHide.getTemplate();
						int weightGrams = itemTemplate.getWeightGrams();
						spectralHide.setWeight((int)((weightGrams*2.25f)+(weightGrams*2.25f*fightskill/100f*random.nextFloat())), true);
						player.getInventory().insertItem(spectralHide);
						String fightStrength = "strong";
						if(fightskill >= 60){
							fightStrength = "great";
						}
						if(fightskill >= 70){
							fightStrength = "powerful";
						}
						if(fightskill >= 80){
							fightStrength = "master";
						}
						if(fightskill >= 90){
							fightStrength = "legendary";
						}
			    		player.getCommunicator().sendSafeServerMessage("The spirit recognizes you as a "+fightStrength+" warrior, and rewards you accordingly.");

					if (mob.getTemplate().getTemplateId() == Aegir.templateId){
						player.addTitle(Title.getTitle(701));
					}

					}
							return;
		    	} // Spectral/Reaper Reward (50+ FS)
    		} // Damage Dealt
    		String mobName = mob.getTemplate().getName().toLowerCase();
    		String mobType = mob.getPrefixes();
    		long iron;
		    double cretStr = getCreatureStrength(mob);

    	}catch (NoSuchTemplateException | FailedException  e) {
			e.printStackTrace();
		}
    } // checkPlayerReward

	public static void checkBounty(Player player, Creature creature){
		try {
			Map<Long, Long> attackers = ReflectionUtil.getPrivateField(creature, ReflectionUtil.getField(creature.getClass(), "attackers"));
			if(!mod.sin.wyvernmods.bounty.MethodsBounty.isCombatant(attackers, player.getWurmId()) || creature.isPlayer() || creature.isReborn()){
				return;
			}
			logger.info(player.getName()+" killed "+creature.getName());
			checkPlayerReward(player, creature);
		} catch (IllegalArgumentException | IllegalAccessException | ClassCastException | NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

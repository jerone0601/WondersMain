package org.gotti.wurmunlimited.mods.wyvernmods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

import org.gotti.wurmunlimited.modloader.classhooks.HookException;
import org.gotti.wurmunlimited.modloader.classhooks.HookManager;

import com.wurmonline.mesh.Tiles;
import com.wurmonline.server.Players;
import com.wurmonline.server.Server;
import com.wurmonline.server.Servers;
import com.wurmonline.server.TimeConstants;
import com.wurmonline.server.bodys.Wound;
import com.wurmonline.server.creatures.Creature;
import com.wurmonline.server.creatures.Creatures;
import com.wurmonline.server.creatures.MineDoorPermission;
import com.wurmonline.server.creatures.SpellEffects;
import com.wurmonline.server.players.Player;
import com.wurmonline.server.spells.SpellEffect;
import com.wurmonline.server.zones.AreaSpellEffect;
import com.wurmonline.server.zones.VolaTile;
import com.wurmonline.server.zones.Zones;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;
import mod.sin.creatures.titans.*;
import org.gotti.wurmunlimited.mods.creatures.AndvarisHall.Andvari;
import org.gotti.wurmunlimited.mods.creatures.AndvarisHall.Narvi;
import org.gotti.wurmunlimited.mods.creatures.GoblinCitadel.GoblinHighShaman;
import org.gotti.wurmunlimited.mods.creatures.GoblinCitadel.GoblinKing;
import org.gotti.wurmunlimited.mods.creatures.GoblinCitadel.Ymir;
import org.gotti.wurmunlimited.mods.creatures.Hlesey.Aegir;
import org.gotti.wurmunlimited.mods.creatures.Hlesey.Ran;
import org.gotti.wurmunlimited.mods.creatures.KarisFortress.Frosti;
import org.gotti.wurmunlimited.mods.creatures.KarisFortress.Jokul;
import org.gotti.wurmunlimited.mods.creatures.KarisFortress.Kari;
import org.gotti.wurmunlimited.mods.creatures.RaidMobs.DragonWhelps;
import org.gotti.wurmunlimited.mods.creatures.RaidMobs.DvergrGuard;
import org.gotti.wurmunlimited.mods.creatures.RaidMobs.Fafnir;

public class Arena {
	public static Logger logger = Logger.getLogger(Arena.class.getName());
	public static byte getArenaAttitude(Player player, Creature aTarget){
		if (player.getPower() > 0) {
            if (player.getPower() >= 5) {
                return 6;
            }
            return 3;
        }
		if (player == aTarget){
			return 1;
		}
		if (player.opponent == aTarget) {
            return 2;
        }
		if (player.getSaveFile().pet != -10 && aTarget.getWurmId() == player.getSaveFile().pet) {
            return 1;
        }
        if (aTarget.getDominator() != null && aTarget.getDominator() != player) {
            return player.getAttitude(aTarget.getDominator());
        }
        if (aTarget.isReborn() && player.getKingdomTemplateId() == 3) {
            return 0;
        }
        if (aTarget.hasAttackedUnmotivated() && (aTarget.isPlayer() || !aTarget.isDominated() || aTarget.getDominator() != player)) {
            return 2;
        }
        if (aTarget.isPlayer() && player.getTeam() != null && player.getTeam().contains(aTarget)){
        	return 1;
        }
        if (aTarget.isPlayer() && player.isFriend(aTarget.getWurmId())) {
            return 1;
        }
        if (aTarget.isPlayer()){
        	return 2;
        }
        if (aTarget.isAggHuman()) {
            return 2;
        }
		return 0;
	}

	
	public static boolean isTitan(int templateId){
		if(templateId == Lilith.templateId){
			return true;
		}else if(templateId == Ifrit.templateId){
			return true;
		}else if(templateId == Aegir.templateId){
			return true;
		}else if(templateId == Ran.templateId){
			return true;
		}else if(templateId == Andvari.templateId){
			return true;
		}else if(templateId == Narvi.templateId){
			return true;
		}else if(templateId == GoblinHighShaman.templateId){
			return true;
		}else if(templateId == GoblinKing.templateId){
			return true;
		}else if(templateId == Ymir.templateId){
			return true;
		}else if(templateId == Frosti.templateId){
			return true;
		}else if(templateId == Kari.templateId){
			return true;
		}else if(templateId == Jokul.templateId){
			return true;
		}else if(templateId == Fafnir.templateId){
			return true;
		}
		return false;
	}
	public static boolean isTitan(Creature creature){
		return isTitan(creature.getTemplate().getTemplateId());
	}
	
	public static boolean isTitanMinion(Creature creature){
		int templateId = creature.getTemplate().getTemplateId();
		if(templateId == LilithWraith.templateId){
			return true;
		}else if(templateId == LilithZombie.templateId){
			return true;
		}else if(templateId == IfritFiend.templateId){
			return true;
		}else if(templateId == IfritSpider.templateId){
			return true;
		}else if(templateId == DvergrGuard.templateId){
			return true;
		}else if(templateId == DragonWhelps.templateId){
			return true;
		}
		return false;
	}
	
	public static void checkDestroyMineDoor(Creature titan, int x, int y){
		int tile = Server.surfaceMesh.getTile(x, y);
		if(Tiles.isMineDoor((byte)Tiles.decodeType((int)tile))){
    		if (Tiles.decodeType((int)Server.caveMesh.getTile(x, y)) == Tiles.Tile.TILE_CAVE_EXIT.id) {
                Server.setSurfaceTile(x, y, Tiles.decodeHeight((int)Server.surfaceMesh.getTile(x, y)), Tiles.Tile.TILE_HOLE.id, (byte) 0);
            } else {
                Server.setSurfaceTile(x, y, Tiles.decodeHeight((int)Server.surfaceMesh.getTile(x, y)), Tiles.Tile.TILE_ROCK.id, (byte) 0);
            }
            Players.getInstance().sendChangedTile(x, y, true, true);
            MineDoorPermission.deleteMineDoor(x, y);
            Server.getInstance().broadCastAction(titan.getName() + "'s ability destroys a mine door!", titan, 50);
    	}
	}
	public static Creature[] getUndergroundCreatures(int x, int y){
		VolaTile tCave = Zones.getOrCreateTile(x, y, false);
        if(tCave == null){
        	return null;
        }
        int tileCave = Server.caveMesh.getTile(x, y);
        byte typeCave = Tiles.decodeType(tileCave);
        if(typeCave != Tiles.Tile.TILE_CAVE.id && typeCave != Tiles.Tile.TILE_CAVE_EXIT.id && typeCave != Tiles.Tile.TILE_CAVE_FLOOR_REINFORCED.id && typeCave != Tiles.Tile.TILE_CAVE_PREPATED_FLOOR_REINFORCED.id){
        	return null;
        }
        return tCave.getCreatures();
	}
	
	// --- Advanced Abilities --- //
	public static void lilithMyceliumVoidAttack(Creature titan, Creature lCret, int tilex, int tiley){
		if (lCret.isUnique() || lCret.isInvulnerable() || lCret == titan || isTitanMinion(lCret)){
			return;
		}
        if (!lCret.addWoundOfType(lCret, Wound.TYPE_INTERNAL, 1, true, 1.0f, true, 60000f)) {
            Creatures.getInstance().setCreatureDead(lCret);
            Players.getInstance().setCreatureDead(lCret);
            lCret.setTeleportPoints((short)tilex, (short)tiley, titan.getLayer(), 0);
            lCret.startTeleporting();
            lCret.getCommunicator().sendAlertServerMessage("You are absorbed by the Mycelium and brought to Yggdrasil!");
            lCret.getCommunicator().sendTeleport(false);
            if (!lCret.isPlayer()) {
                lCret.getMovementScheme().resumeSpeedModifier();
            }
        }
	}
	public static void ifritMassIncinerateAttack(Creature titan, Creature lCret){
		if (lCret.isUnique() || lCret.isInvulnerable() || lCret == titan || isTitanMinion(lCret)){
			return;
		}
    	SpellEffect eff;
        SpellEffects effs = lCret.getSpellEffects();
        if (effs == null) {
            effs = lCret.createSpellEffects();
        }
        eff = effs.getSpellEffect((byte) 94);
        if (eff == null) {
            lCret.getCommunicator().sendAlertServerMessage("You are engulfed by the flames of Ifrit!", (byte) 4);
            eff = new SpellEffect(lCret.getWurmId(), (byte) 94, 80f, 180, (byte) 9, (byte) 1, true);
            effs.addSpellEffect(eff);
            Server.getInstance().broadCastAction(titan.getName() + " has engulfed " + lCret.getNameWithGenus() + " in flames!", titan, 50);
        } else {
            lCret.getCommunicator().sendAlertServerMessage("The heat around you increases. The pain is excruciating!", (byte) 4);
            eff.setPower(eff.getPower()+200f);
            eff.setTimeleft(180);
            lCret.sendUpdateSpellEffect(eff);
            Server.getInstance().broadCastAction(titan.getName() + " has engulfed " + lCret.getNameWithGenus() + " in flames again, increasing the intensity!", titan, 50);
        }
	}
	public static void fafnirMassIncinerateAttack(Creature titan, Creature lCret){
		if (lCret.isUnique() || lCret.isInvulnerable() || lCret == titan || isTitanMinion(lCret)){
			return;
		}
		SpellEffect eff;
		SpellEffects effs = lCret.getSpellEffects();
		if (effs == null) {
			effs = lCret.createSpellEffects();
		}
		eff = effs.getSpellEffect((byte) 94);
		if (eff == null) {
			lCret.getCommunicator().sendAlertServerMessage("You are engulfed by the flames of Fafnir!", (byte) 4);
			eff = new SpellEffect(lCret.getWurmId(), (byte) 94, 90f, 180, (byte) 9, (byte) 1, true);
			effs.addSpellEffect(eff);
			Server.getInstance().broadCastAction(titan.getName() + " has engulfed " + lCret.getNameWithGenus() + " in flames!", titan, 50);
		} else {
			lCret.getCommunicator().sendAlertServerMessage("The heat around you increases. The pain is excruciating!", (byte) 4);
			eff.setPower(eff.getPower()+250f);
			eff.setTimeleft(180);
			lCret.sendUpdateSpellEffect(eff);
			Server.getInstance().broadCastAction(titan.getName() + " has engulfed " + lCret.getNameWithGenus() + " in flames again, increasing the intensity!", titan, 50);
		}
	}
	public static void performAdvancedAbility(Creature titan){
		int tilex = titan.getTileX();
		int tiley = titan.getTileY();
		if(titan.getTemplate().getTemplateId() == Lilith.templateId){ // Lilith Ability
			int tarx = (tilex-3)+(Server.rand.nextInt(7));
			int tary = (tiley-3)+(Server.rand.nextInt(7));
			int sx = Zones.safeTileX(tarx - 1);
	        int ex = Zones.safeTileX(tarx + 1);
	        int sy = Zones.safeTileY(tary - 1);
	        int ey = Zones.safeTileY(tary + 1);
	        Zones.flash(tarx, tary, false);
            Server.getInstance().broadCastAction(titan.getName() + " casts Mycelium Void, turning the earth to fungus and pulling enemies to "+titan.getHimHerItString()+"!", titan, 50);
	        for (int x = sx; x <= ex; ++x) {
	            for (int y = sy; y <= ey; ++y) {
	            	VolaTile t = Zones.getOrCreateTile(x, y, true);
	                if (t == null){
	                	continue;
	                }
	                checkDestroyMineDoor(titan, x, y);
	                int tile = Server.surfaceMesh.getTile(x, y);
                    byte type = Tiles.decodeType((int)tile);
                    Tiles.Tile theTile = Tiles.getTile((byte)type);
                    byte data = Tiles.decodeData((int)tile);
                    // Copied from Fungus to prevent wacko behaviours like deleting minedoors and glitching tunnels:
                    if (type != Tiles.Tile.TILE_FIELD.id && type != Tiles.Tile.TILE_FIELD2.id && type != Tiles.Tile.TILE_GRASS.id && type != Tiles.Tile.TILE_REED.id && type != Tiles.Tile.TILE_DIRT.id && type != Tiles.Tile.TILE_LAWN.id && type != Tiles.Tile.TILE_STEPPE.id && !theTile.isNormalTree() && !theTile.isEnchanted() && !theTile.isNormalBush()){
                    	//
                    }else{
	                    if (theTile.isNormalTree()) {
	                        Server.setSurfaceTile(x, y, Tiles.decodeHeight((int)tile), theTile.getTreeType(data).asMyceliumTree(), data);
	                    } else if (theTile.isEnchantedTree()) {
	                        Server.setSurfaceTile(x, y, Tiles.decodeHeight((int)tile), theTile.getTreeType(data).asNormalTree(), data);
	                    } else if (theTile.isNormalBush()) {
	                        Server.setSurfaceTile(x, y, Tiles.decodeHeight((int)tile), theTile.getBushType(data).asMyceliumBush(), data);
	                    } else if (theTile.isEnchantedBush()) {
	                        Server.setSurfaceTile(x, y, Tiles.decodeHeight((int)tile), theTile.getBushType(data).asNormalBush(), data);
	                    } else if (type == Tiles.Tile.TILE_LAWN.id) {
	                        Server.setSurfaceTile(x, y, Tiles.decodeHeight((int)tile), Tiles.Tile.TILE_MYCELIUM_LAWN.id, (byte) 0);
	                    } else {
	                        Server.setSurfaceTile(x, y, Tiles.decodeHeight((int)tile), Tiles.Tile.TILE_MYCELIUM.id, (byte) 0);
	                    }
	                    Players.getInstance().sendChangedTile(x, y, true, false);
                    }
	                Creature[] crets2 = t.getCreatures();
	                for (Creature lCret : crets2) {
	                	lilithMyceliumVoidAttack(titan, lCret, tilex, tiley);
	                }
	                VolaTile tCave = Zones.getOrCreateTile(x, y, false);
	                if(tCave == null){
	                	continue;
	                }
	                int tileCave = Server.caveMesh.getTile(x, y);
	                byte typeCave = Tiles.decodeType(tileCave);
	                if(typeCave != Tiles.Tile.TILE_CAVE.id && typeCave != Tiles.Tile.TILE_CAVE_EXIT.id && typeCave != Tiles.Tile.TILE_CAVE_FLOOR_REINFORCED.id && typeCave != Tiles.Tile.TILE_CAVE_PREPATED_FLOOR_REINFORCED.id){
	                	continue;
	                }
	                Creature[] crets3 = tCave.getCreatures();
	                for (Creature lCret : crets3) {
	                	lilithMyceliumVoidAttack(titan, lCret, tilex, tiley);
	                }
	            }
	        }
		}else if(titan.getTemplate().getTemplateId() == Ifrit.templateId){ // Ifrit Ability
			int tarx = (tilex-4)+(Server.rand.nextInt(9));
			int tary = (tiley-4)+(Server.rand.nextInt(9));
			int sx = Zones.safeTileX(tarx - 1);
	        int ex = Zones.safeTileX(tarx + 1);
	        int sy = Zones.safeTileY(tary - 1);
	        int ey = Zones.safeTileY(tary + 1);
	        Zones.flash(tarx, tary, false);
            Server.getInstance().broadCastAction(titan.getName() + " casts Mass Incinerate, burning enemies near "+titan.getHimHerItString()+"!", titan, 50);
	        for (int x = sx; x <= ex; ++x) {
	            for (int y = sy; y <= ey; ++y) {
	            	VolaTile t = Zones.getOrCreateTile(x, y, true);
	                if (t == null){
	                	continue;
	                }
	                checkDestroyMineDoor(titan, x, y);
	                new AreaSpellEffect(titan.getWurmId(), x, y, titan.getLayer(), (byte) 35, System.currentTimeMillis() + 5000, 20.0f, titan.getLayer(), 0, true);
	                Creature[] crets2 = t.getCreatures();
	                for (Creature lCret : crets2) {
	                	ifritMassIncinerateAttack(titan, lCret);
	                }
	                Creature[] undergroundCreatures = getUndergroundCreatures(x, y);
	                if(undergroundCreatures != null){
	                	for(Creature lCret : undergroundCreatures){
	                		ifritMassIncinerateAttack(titan, lCret);
	                	}
	                }
	            }
	        }
		}else if(titan.getTemplate().getTemplateId() == Fafnir.templateId){ // Ifrit Ability
			int tarx = (tilex-4)+(Server.rand.nextInt(9));
			int tary = (tiley-4)+(Server.rand.nextInt(9));
			int sx = Zones.safeTileX(tarx - 1);
			int ex = Zones.safeTileX(tarx + 1);
			int sy = Zones.safeTileY(tary - 1);
			int ey = Zones.safeTileY(tary + 1);
			Zones.flash(tarx, tary, false);
			Server.getInstance().broadCastAction(titan.getName() + " casts Mass Incinerate, burning enemies near "+titan.getHimHerItString()+"!", titan, 50);
			for (int x = sx; x <= ex; ++x) {
				for (int y = sy; y <= ey; ++y) {
					VolaTile t = Zones.getOrCreateTile(x, y, true);
					if (t == null){
						continue;
					}
					checkDestroyMineDoor(titan, x, y);
					new AreaSpellEffect(titan.getWurmId(), x, y, titan.getLayer(), (byte) 35, System.currentTimeMillis() + 5000, 30.0f, titan.getLayer(), 0, true);
					Creature[] crets2 = t.getCreatures();
					for (Creature lCret : crets2) {
						fafnirMassIncinerateAttack(titan, lCret);
					}
					Creature[] undergroundCreatures = getUndergroundCreatures(x, y);
					if(undergroundCreatures != null){
						for(Creature lCret : undergroundCreatures){
							fafnirMassIncinerateAttack(titan, lCret);
						}
					}
				}
			}
		}

	}
	
	// --- Basic Abilities --- //
	public static void lilithPainRainAttack(Creature titan, Creature lCret, VolaTile t){
		if (lCret.isUnique() || lCret.isInvulnerable() || lCret == titan || isTitanMinion(lCret)){
			return;
		}
        t.sendAttachCreatureEffect((Creature)lCret, (byte) 8, (byte) 0, (byte) 0, (byte) 0, (byte) 0);
        try {
			if (lCret.addWoundOfType(titan, (byte) 9, lCret.getBody().getRandomWoundPos(), false, 1.0f, false, 25000.0 * (double)lCret.addSpellResistance((short) 448))){
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
        lCret.setTarget(titan.getWurmId(), false);
	}
	
	public static void performBasicAbility(Creature titan){
		int tilex = titan.getTileX();
		int tiley = titan.getTileY();
		if(titan.getTemplate().getTemplateId() == Lilith.templateId) { // Lilith Ability
			int sx = Zones.safeTileX(tilex - 10);
			int sy = Zones.safeTileY(tiley - 10);
			int ex = Zones.safeTileX(tilex + 10);
			int ey = Zones.safeTileY(tiley + 10);
			//this.calculateArea(sx, sy, ex, ey, tilex, tiley, layer, currstr);
			int x, y;
			Server.getInstance().broadCastAction(titan.getName() + " casts Pain Rain, harming all around " + titan.getHimHerItString() + "!", titan, 50);
			for (x = sx; x <= ex; ++x) {
				for (y = sy; y <= ey; ++y) {
					VolaTile t = Zones.getTileOrNull(x, y, titan.isOnSurface());
					if (t == null) {
						continue;
					}
					Creature[] crets2 = t.getCreatures();
					for (Creature lCret : crets2) {
						lilithPainRainAttack(titan, lCret, t);
					}
					Creature[] undergroundCreatures = getUndergroundCreatures(x, y);
					if (undergroundCreatures != null) {
						for (Creature lCret : undergroundCreatures) {
							lilithPainRainAttack(titan, lCret, t);
						}
					}
				}
			}
		}else if(titan.getTemplate().getTemplateId() == Fafnir.templateId) { // Lilith Ability
			int sx = Zones.safeTileX(tilex - 10);
			int sy = Zones.safeTileY(tiley - 10);
			int ex = Zones.safeTileX(tilex + 10);
			int ey = Zones.safeTileY(tiley + 10);
			//this.calculateArea(sx, sy, ex, ey, tilex, tiley, layer, currstr);
			int x, y;
			Server.getInstance().broadCastAction(titan.getName() + " casts Pain Rain, harming all around " + titan.getHimHerItString() + "!", titan, 50);
			for (x = sx; x <= ex; ++x) {
				for (y = sy; y <= ey; ++y) {
					VolaTile t = Zones.getTileOrNull(x, y, titan.isOnSurface());
					if (t == null) {
						continue;
					}
					Creature[] crets2 = t.getCreatures();
					for (Creature lCret : crets2) {
						lilithPainRainAttack(titan, lCret, t);
					}
					Creature[] undergroundCreatures = getUndergroundCreatures(x, y);
					if (undergroundCreatures != null) {
						for (Creature lCret : undergroundCreatures) {
							lilithPainRainAttack(titan, lCret, t);
						}
					}
				}
			}
		}else if(titan.getTemplate().getTemplateId() == Ran.templateId){ // Lilith Ability
				int sx = Zones.safeTileX(tilex - 10);
				int sy = Zones.safeTileY(tiley - 10);
				int ex = Zones.safeTileX(tilex + 10);
				int ey = Zones.safeTileY(tiley + 10);
				//this.calculateArea(sx, sy, ex, ey, tilex, tiley, layer, currstr);
				int x, y;
				Server.getInstance().broadCastAction(titan.getName() + " casts Pain Rain, harming all around "+titan.getHimHerItString()+"!", titan, 50);
				for (x = sx; x <= ex; ++x) {
					for (y = sy; y <= ey; ++y) {
						VolaTile t = Zones.getTileOrNull(x, y, titan.isOnSurface());
						if (t == null){
							continue;
						}
						Creature[] crets2 = t.getCreatures();
						for (Creature lCret : crets2) {
							lilithPainRainAttack(titan, lCret, t);
						}
						Creature[] undergroundCreatures = getUndergroundCreatures(x, y);
						if(undergroundCreatures != null){
							for(Creature lCret : undergroundCreatures){
								lilithPainRainAttack(titan, lCret, t);
							}
						}
					}
				}
		}else if(titan.getTemplate().getTemplateId() == Ifrit.templateId){
			int sx = Zones.safeTileX(tilex - 10);
	        int sy = Zones.safeTileY(tiley - 10);
	        int ex = Zones.safeTileX(tilex + 10);
	        int ey = Zones.safeTileY(tiley + 10);
	        int x, y;
	        ArrayList<Creature> targets = new ArrayList<Creature>();
	        for (x = sx; x <= ex; ++x) {
	            for (y = sy; y <= ey; ++y) {
	                VolaTile t = Zones.getTileOrNull(x, y, titan.isOnSurface());
	                if (t == null){
	                	continue;
	                }
	                Creature[] crets2 = t.getCreatures();
	                for (Creature lCret : crets2) {
	                    if (lCret.isUnique() || lCret.isInvulnerable() || lCret == titan || isTitanMinion(lCret)){
	                    	continue;
	                    }
	                    targets.add(lCret);
	                }
	                Creature[] undergroundCreatures = getUndergroundCreatures(x, y);
	                if(undergroundCreatures != null){
	                	for(Creature lCret : undergroundCreatures){
	                		if (lCret.isUnique() || lCret.isInvulnerable() || lCret == titan || isTitanMinion(lCret)){
	                			continue;
	                		}
	                		targets.add(lCret);
	                	}
	                }
	            }
	        }
	        if(!targets.isEmpty()){
	        	Creature target = targets.get(Server.rand.nextInt(targets.size()));
	            int damage = target.getStatus().damage;
	            int minhealth = 65435;
	            float maxdam = (float)Math.max(0, minhealth - damage);
	            if (maxdam > 500.0f) {
	                Server.getInstance().broadCastAction(titan.getName() + " picks a target at random and Smites "+target.getName()+"!", titan, 50);
	                target.getCommunicator().sendAlertServerMessage(titan.getName() + " smites you.", (byte) 4);
	                try {
						target.addWoundOfType(titan, Wound.TYPE_BURN, target.getBody().getRandomWoundPos(), false, 1.0f, false, maxdam);
					} catch (Exception e) {
						e.printStackTrace();
					}
	            }
	        }
		}
	}
	
	public static void summonChampions(Creature titan, int nums){
		int templateType = -10;
		String spellName = "";
		if(titan.getTemplate().getTemplateId() == Lilith.templateId){
			templateType = LilithWraith.templateId;
			spellName = "Raise Wraith";
		}else if(titan.getTemplate().getTemplateId() == Ifrit.templateId){
			templateType = IfritFiend.templateId;
			spellName = "Summon Fiend";
		}else if(titan.getTemplate().getTemplateId() == Fafnir.templateId){
			templateType = DragonWhelps.templateId;
			spellName = "Hatch Eggs";
		}
		if(templateType == -10){
			logger.severe("[ERROR]: Template type not set in summonChampions()");
			return;
		}
		try {
            Server.getInstance().broadCastAction(titan.getName() + " casts "+spellName+", calling champions to "+titan.getHimHerItString()+" aid!", titan, 50);
			for(int i = 0; i < nums; i++){
				int tilex = ((titan.getTileX()*4)+3)-Server.rand.nextInt(7);
				int tiley = ((titan.getTileY()*4)+3)-Server.rand.nextInt(7);
				int sx = Zones.safeTileX(tilex - 2);
		        int sy = Zones.safeTileY(tiley - 2);
		        int ex = Zones.safeTileX(tilex + 2);
		        int ey = Zones.safeTileY(tiley + 2);
		        Creature target = null;
		        for (int x = sx; x <= ex; ++x) {
		            for (int y = sy; y <= ey; ++y) {
		                VolaTile t = Zones.getTileOrNull(x, y, titan.isOnSurface());
		                if (t == null){
		                	continue;
		                }
		                Creature[] crets2 = t.getCreatures();
		                for (Creature lCret : crets2) {
		                    if (lCret.isUnique() || lCret.isInvulnerable() || lCret == titan || isTitanMinion(lCret)) continue;
		                    if(Server.rand.nextInt(3) == 0){
		                    	target = lCret;
		                    	break;
		                    }
		                }
		                Creature[] undergroundCreatures = getUndergroundCreatures(x, y);
		                if(undergroundCreatures != null){
		                	for(Creature lCret : undergroundCreatures){
		                		if (lCret.isUnique() || lCret.isInvulnerable() || lCret == titan || isTitanMinion(lCret)) continue;
			                    if(Server.rand.nextInt(3) == 0){
			                    	target = lCret;
			                    	break;
			                    }
		                	}
		                }
		                if(target != null){
		                	break;
		                }
		            }
		            if(target != null){
		            	break;
		            }
		        }
				// public static Creature doNew(int templateid, float aPosX, float aPosY, float aRot, int layer, String name, byte gender) throws Exception {
				Creature champion = Creature.doNew(templateType, tilex, tiley, 360f*Server.rand.nextFloat(), 1, "", (byte)0);
				if(target != null){
					champion.setOpponent(target);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void summonMinions(Creature titan, int nums){
		int templateType = -10;
		String spellName = "";
		if(titan.getTemplate().getTemplateId() == Lilith.templateId){
			templateType = LilithZombie.templateId;
			spellName = "Raise Zombie";
		}else if(titan.getTemplate().getTemplateId() == Ifrit.templateId){
			templateType = IfritSpider.templateId;
			spellName = "Summon Spider";
		}else if(titan.getTemplate().getTemplateId() == Fafnir.templateId){
			templateType = DvergrGuard.templateId;
			spellName = "Summon Brethren";
		}
		if(templateType == -10){
			logger.severe("[ERROR]: Template type not set in summonMinions()");
			return;
		}
		try {
            Server.getInstance().broadCastAction(titan.getName() + " casts "+spellName+", calling minions to "+titan.getHimHerItString()+" aid!", titan, 50);
			for(int i = 0; i < nums; i++){
				int tilex = ((titan.getTileX()*4)+3)-Server.rand.nextInt(7);
				int tiley = ((titan.getTileY()*4)+3)-Server.rand.nextInt(7);
				int sx = Zones.safeTileX(tilex - 10);
		        int sy = Zones.safeTileY(tiley - 10);
		        int ex = Zones.safeTileX(tilex + 10);
		        int ey = Zones.safeTileY(tiley + 10);
		        Creature target = null;
		        for (int x = sx; x <= ex; ++x) {
		            for (int y = sy; y <= ey; ++y) {
		                VolaTile t = Zones.getTileOrNull(x, y, titan.isOnSurface());
		                if (t == null){
		                	continue;
		                }
		                Creature[] crets2 = t.getCreatures();
		                for (Creature lCret : crets2) {
		                    if (lCret.isUnique() || lCret.isInvulnerable() || lCret == titan || isTitanMinion(lCret)) continue;
		                    if(Server.rand.nextInt(3) == 0){
		                    	target = lCret;
		                    	break;
		                    }
		                }
		                Creature[] undergroundCreatures = getUndergroundCreatures(x, y);
		                if(undergroundCreatures != null){
		                	for(Creature lCret : undergroundCreatures){
		                		if (lCret.isUnique() || lCret.isInvulnerable() || lCret == titan || isTitanMinion(lCret)) continue;
			                    if(Server.rand.nextInt(3) == 0){
			                    	target = lCret;
			                    	break;
			                    }
		                	}
		                }
		                if(target != null){
		                	break;
		                }
		            }
		            if(target != null){
		            	break;
		            }
		        }
				// public static Creature doNew(int templateid, float aPosX, float aPosY, float aRot, int layer, String name, byte gender) throws Exception {
				Creature minion = Creature.doNew(templateType, tilex, tiley, 360f*Server.rand.nextFloat(), 1, "", (byte)0);
				if(target != null){
					minion.setOpponent(target);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static HashMap<Creature, Integer> titanDamage = new HashMap<Creature, Integer>();
	public static void pollTitan(Creature titan){
		if(titanDamage.containsKey(titan)){
			int prevDamage = titanDamage.get(titan);
			int currentDamage = titan.getStatus().damage;
			if(titan.isOnSurface() && Server.rand.nextInt(50) == 0){
				performAdvancedAbility(titan);
			}else if(Server.rand.nextInt(20) == 0){
				performAdvancedAbility(titan);
			}
			if(currentDamage > prevDamage){
				// Health threshold actions
				if(currentDamage > 0 && prevDamage == 0){ // First attack
					String msg = "<"+titan.getName()+" [100%]> Mere mortals dare to face me?";
					MiscChanges.sendServerTabMessage(msg, 255, 105, 180);
					Zones.flash(titan.getTileX(), titan.getTileY(), false);
				}
				if(currentDamage > 8191 && prevDamage < 8191){ // 87.5%
					String msg = "<"+titan.getName()+" [88%]> You actually think you can defeat me?";
					MiscChanges.sendServerTabMessage(msg, 255, 105, 180);
					Zones.flash(titan.getTileX(), titan.getTileY(), false);
				}
				if(currentDamage > 16383 && prevDamage < 16383){ // 75%
					String msg = "<"+titan.getName()+" [75%]> You will perish!";
					MiscChanges.sendServerTabMessage(msg, 255, 105, 180);
					Zones.flash(titan.getTileX(), titan.getTileY(), false);
					summonMinions(titan, Server.rand.nextInt(2)+2);
				}
				if(currentDamage > 26214 && prevDamage < 26214){ // 60%
					String msg = "<"+titan.getName()+" [60%]> You will feel my wrath!";
					MiscChanges.sendServerTabMessage(msg, 255, 105, 180);
					Zones.flash(titan.getTileX(), titan.getTileY(), false);
					performBasicAbility(titan);
				}
				if(currentDamage > 32767 && prevDamage < 32767){ // 50%
					String msg = "<"+titan.getName()+" [50%]> I've had enough of you. ";
					MiscChanges.sendServerTabMessage(msg, 255, 105, 180);
					Zones.flash(titan.getTileX(), titan.getTileY(), false);
					summonMinions(titan, Server.rand.nextInt(4)+4);
					performBasicAbility(titan);
				}
				if(currentDamage > 39321 && prevDamage < 39321){ // 40%
					String msg = "<"+titan.getName()+" [40%]> Let's try something new, shall we?";
					MiscChanges.sendServerTabMessage(msg, 255, 105, 180);
					Zones.flash(titan.getTileX(), titan.getTileY(), false);
					performAdvancedAbility(titan);
				}
				if(currentDamage > 45874 && prevDamage < 45874){ // 30%
					String msg = "<"+titan.getName()+" [30%]> You will not overcome my fury!";
					MiscChanges.sendServerTabMessage(msg, 255, 105, 180);
					Zones.flash(titan.getTileX(), titan.getTileY(), false);
					summonChampions(titan, Server.rand.nextInt(2)+2);
					performBasicAbility(titan);
				}
				if(currentDamage > 52428 && prevDamage < 52428){ // 20%
					String msg = "<"+titan.getName()+" [20%]> Enough! I will end you!";
					MiscChanges.sendServerTabMessage(msg, 255, 105, 180);
					Zones.flash(titan.getTileX(), titan.getTileY(), false);
					performBasicAbility(titan);
					performAdvancedAbility(titan);
				}
				if(currentDamage > 58981 && prevDamage < 58981){ // 10%
					String msg = "<"+titan.getName()+" [10%]> Is this the end? I think not!";
					MiscChanges.sendServerTabMessage(msg, 255, 105, 180);
					Zones.flash(titan.getTileX(), titan.getTileY(), false);
					summonMinions(titan, Server.rand.nextInt(5)+7);
					summonChampions(titan, Server.rand.nextInt(3)+3);
					performBasicAbility(titan);
					performAdvancedAbility(titan);
				}
				// Extra abilities
				if(currentDamage > 16383 && Server.rand.nextInt(10) == 0){
					if(currentDamage > 45874){
						summonMinions(titan, Server.rand.nextInt(2)+2);
					}else if(currentDamage > 32767){
						summonMinions(titan, Server.rand.nextInt(3)+1);
					}else{
						summonMinions(titan, Server.rand.nextInt(2)+1);
					}
				}
				if(currentDamage > 16383 && Server.rand.nextInt(15) == 0){
					if(currentDamage > 45874){ // 30%
						if(Server.rand.nextInt(10) == 0){
							performBasicAbility(titan);
						}
					}else if(currentDamage > 32767){ // 50%
						if(Server.rand.nextInt(12) == 0){
							performBasicAbility(titan);
						}
					}else{ // 75%
						if(Server.rand.nextInt(10) == 0){
							performBasicAbility(titan);
						}
					}
				}
				if(currentDamage > 32767 && Server.rand.nextInt(20) == 0){
					if(currentDamage > 45874){
						if(Server.rand.nextInt(15) == 0){
							performAdvancedAbility(titan);
						}
					}else{
						if(Server.rand.nextInt(20) == 0){
							performAdvancedAbility(titan);
						}
					}
				}
				if(currentDamage > 45874 && Server.rand.nextInt(15) == 0){
					summonChampions(titan, 1);
				}
				titanDamage.put(titan, currentDamage);
			}
		}else{
			titanDamage.put(titan, titan.getStatus().damage);
		}
	}
	
	public static ArrayList<Creature> titans = new ArrayList<Creature>();
	public static long lastPolledTitans = 0;
	public static final long titanRespawnTime = TimeConstants.HOUR_MILLIS*26L;
	public static void addTitan(Creature mob){
		if(isTitan(mob) && !titans.contains(mob)){
			titans.add(mob);
		}
	}
	public static void removeTitan(Creature mob){
		if(isTitan(mob) && titans.contains(mob)){
			titans.remove(mob);
		}
	}
	public static void pollTitanSpawn(){
		Creature[] crets = Creatures.getInstance().getCreatures();
		for(Creature c : crets){
			if(isTitan(c) && !titans.contains(c)){
				titans.add(c);
			}
		}
		for(Creature c : titans){
			if(c.isDead()){
				titans.remove(c);
			}
		}
		if(titans.isEmpty() && Servers.localServer.PVPSERVER){
			if(lastPolledTitans + titanRespawnTime < System.currentTimeMillis()){
				logger.info("No Titan was found, and the timer has expired. Spawning a new one.");
				float worldSizeX = Zones.worldTileSizeX;
				float worldSizeY = Zones.worldTileSizeY;
				float minX = worldSizeX*0.25f;
				float minY = worldSizeY*0.25f;
				int tilex = (int) (minX+(minX*2*Server.rand.nextFloat()))*4;
				int tiley = (int) (minY+(minY*2*Server.rand.nextFloat()))*4;
				int[] titanTemplates = {/*Lilith.templateId,*/ Ifrit.templateId}; // Lilith disabled for memory leak
				try {
					Creature.doNew(titanTemplates[Server.rand.nextInt(titanTemplates.length)], tilex, tiley, 360f*Server.rand.nextFloat(), 1, "", (byte)0);
				} catch (Exception e) {
					logger.severe("Failed to create Titan.");
					e.printStackTrace();
				}
			}
		}else{
			lastPolledTitans = System.currentTimeMillis();
		}
	}
	public static void pollTitans(){
		for(Creature c : titans){
			if(isTitan(c)){
				pollTitan(c);
			}
		}
	}
	
	public static void preInit(){
		try {
			ClassPool classPool = HookManager.getInstance().getClassPool();
			
			// - Add poll for Titans - //
			CtClass ctServer = classPool.get("com.wurmonline.server.Server");
            ctServer.getDeclaredMethod("run").instrument(new ExprEditor(){
                public void edit(MethodCall m) throws CannotCompileException {
                    if (m.getMethodName().equals("pruneTransfers")) {
                        m.replace("org.gotti.wurmunlimited.mods.wyvernmods.Arena.pollTitanSpawn();"
                        		+ "$_ = $proceed($$);");
                        return;
                    }
                }
            });
            ctServer.getDeclaredMethod("run").instrument(new ExprEditor(){
                public void edit(MethodCall m) throws CannotCompileException {
                    if (m.getMethodName().equals("pollValreiData")) {
                        m.replace("org.gotti.wurmunlimited.mods.wyvernmods.Arena.pollTitans();"
                        		+ "$_ = $proceed($$);");
                        return;
                    }
                }
            });
			
            // - Remove regeneration from titans - //



            

            CtClass ctMethodsItems = classPool.get("com.wurmonline.server.behaviours.MethodsItems");

            


            
            // - Fix nearby enemy check to find aggression instead of kingdom - //
            ctMethodsItems.getDeclaredMethod("isEnemiesNearby").instrument(new ExprEditor(){
                public void edit(MethodCall m) throws CannotCompileException {
                    if (m.getMethodName().equals("isFriendlyKingdom")) {
                        m.replace("if(com.wurmonline.server.Servers.localServer.PVPSERVER){"
                    			+ "  $_ = c.getAttitude(performer) != 2;"
                    			+ "}else{"
                    			+ "  $_ = $proceed($$);"
                    			+ "}");
                        return;
                    }
                }
            });



            // - Disable Smiting Titans - //
            CtClass ctSmite = classPool.get("com.wurmonline.server.spells.Smite");
            ctSmite.getDeclaredMethod("precondition").insertBefore(""
            		+ "if(org.gotti.wurmunlimited.mods.wyvernmods.Arena.isTitan($3)){"
            		+ "  $2.getCommunicator().sendNormalServerMessage(\"You cannot smite a boss!\");"
            		+ "  return false;"
            		+ "}");
            


            // - Multiply mine door bash damage by 3 on Arena - //
            CtClass ctTerraforming = classPool.get("com.wurmonline.server.behaviours.Terraforming");
            ctTerraforming.getDeclaredMethod("destroyMineDoor").instrument(new ExprEditor(){
                public void edit(MethodCall m) throws CannotCompileException {
                    if (m.getMethodName().equals("getOrCreateTile")) {
                        m.replace("if(com.wurmonline.server.Servers.localServer.PVPSERVER){"
                        		+ "  damage *= 3d;"
                        		+ "}"
                        		+ "$_ = $proceed($$);");
                        return;
                    }
                }
            });

			
		}catch (CannotCompileException | NotFoundException e) {
			throw new HookException((Throwable)e);
        }
	}
}

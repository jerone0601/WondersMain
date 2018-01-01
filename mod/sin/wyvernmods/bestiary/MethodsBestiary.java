package mod.sin.wyvernmods.bestiary;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Logger;

import org.gotti.wurmunlimited.modloader.ReflectionUtil;
import org.gotti.wurmunlimited.mods.creatures.*;
import org.gotti.wurmunlimited.mods.creatures.AndvarisHall.*;
import org.gotti.wurmunlimited.mods.creatures.DungeonWorldSpawn.DvergerExplorer;
import org.gotti.wurmunlimited.mods.creatures.DungeonWorldSpawn.GoblinScout;
import org.gotti.wurmunlimited.mods.creatures.DungeonWorldSpawn.HleseyCrab;
import org.gotti.wurmunlimited.mods.creatures.DungeonWorldSpawn.Jotunn;
import org.gotti.wurmunlimited.mods.creatures.GoblinCitadel.*;
import org.gotti.wurmunlimited.mods.creatures.Hlesey.*;
import org.gotti.wurmunlimited.mods.creatures.Jotunheimr.*;
import org.gotti.wurmunlimited.mods.creatures.KarisFortress.*;
import org.gotti.wurmunlimited.mods.creatures.LSprison.DeputyWardenBob;
import org.gotti.wurmunlimited.mods.creatures.LSprison.GuardCaptainFred;
import org.gotti.wurmunlimited.mods.creatures.Mounts.*;
import org.gotti.wurmunlimited.mods.creatures.NCP.*;
import org.gotti.wurmunlimited.mods.creatures.RaidMobs.DragonWhelps;
import org.gotti.wurmunlimited.mods.creatures.RaidMobs.Fafnir;
import org.gotti.wurmunlimited.mods.creatures.Unique.Facebreyker;
import org.gotti.wurmunlimited.mods.creatures.Unique.King;
import org.gotti.wurmunlimited.mods.creatures.Unique.LizardKing;
import org.gotti.wurmunlimited.mods.wyvernmods.Arena;
import org.gotti.wurmunlimited.mods.wyvernmods.MiscChanges;

import com.wurmonline.server.Server;
import com.wurmonline.server.combat.Archery;
import com.wurmonline.server.creatures.Creature;
import com.wurmonline.server.creatures.CreatureStatus;
import com.wurmonline.server.creatures.CreatureTemplate;
import com.wurmonline.server.creatures.CreatureTemplateFactory;
import com.wurmonline.server.creatures.NoSuchCreatureTemplateException;
import com.wurmonline.server.items.Item;
import com.wurmonline.server.items.ItemFactory;
import com.wurmonline.server.items.ItemList;
import com.wurmonline.server.items.Materials;

import mod.sin.creatures.titans.*;
import mod.sin.weapons.Club;

public class MethodsBestiary {
	protected static Logger logger = Logger.getLogger(MethodsBestiary.class.getName());
	
	public static boolean checkColorTemplate(CreatureTemplate template){
		try {
			int templateId = template.getTemplateId();
			if(templateId == Lilith.templateId){
	        	return true;
	        }else if(templateId == ForestSpider.templateId){
				return true;
			}else if(templateId == Avenger.templateId){
				return true;
			}else if(templateId == Andvari.templateId){
				return true;
			}else if(templateId == DaughterofFire.templateId){
				return true;
			}else if(templateId == DvergerArtisan.templateId){
				return true;
			}else if(templateId == DvergerExplorer.templateId){
				return true;
			}else if(templateId == DvergerGoat.templateId){
				return true;
			}else if(templateId == DvergerGoatHerder.templateId){
				return true;
			}else if(templateId == Dvergr.templateId){
				return true;
			}else if(templateId == GoblinAssassin.templateId){
				return true;
			}else if(templateId == GoblinBerserker.templateId){
				return true;
			}else if(templateId == GoblinBrute.templateId){
				return true;
			}else if(templateId == GoblinGeneral.templateId){
				return true;
			}else if(templateId == GoblinHighShaman.templateId){
				return true;
			}else if(templateId == GoblinKing.templateId){
				return true;
			}else if(templateId == GoblinScout.templateId){
				return true;
			}else if(templateId == GoblinMiner.templateId){
				return true;
			}else if(templateId == GoblinShaman.templateId){
				return true;
			}else if(templateId == HleseyCrab.templateId){
				return true;
			}else if(templateId == Jotunn.templateId){
				return true;
			}else if(templateId == Logi.templateId){
				return true;
			}else if(templateId == Narvi.templateId){
				return true;
			}else if(templateId == Risi.templateId){
				return true;
			}else if(templateId == FrostGiant.templateId){
				return true;
			}else if(templateId == Kari.templateId){
				return true;
			}else if(templateId == NorthWindDreki.templateId){
				return true;
			}else if(templateId == Frosti.templateId){
				return true;
			}else if(templateId == Jokul.templateId){
				return true;
			}else if(templateId == Silverback.templateId){
				return true;
			}else if(templateId == Veattir.templateId){
				return true;
			}else if(templateId == Ymir.templateId){
				return true;
			}else if(templateId == TinyWurm.templateId){
				return true;
			}else if(templateId == TinyBlueFiend.templateId){
				return true;
			}else if(templateId == TinyUnicorn.templateId){
				return true;
			}
		} catch (IllegalArgumentException | ClassCastException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static byte getCreatureColorRed(CreatureTemplate template){
		try {
			int templateId = template.getTemplateId();
			if(templateId == ForestSpider.templateId){
	        	return (byte)0;
			}else if(templateId == Kari.templateId){
				return (byte)40;
			}else if(templateId == Jokul.templateId){
				return (byte)40;
			}else if(templateId == FrostGiant.templateId){
				return (byte)40;
			}else if(templateId == TinyBlueFiend.templateId){
				return (byte)40;
	        }else if(templateId == Avenger.templateId){
	        	return (byte)70;
			}else if(templateId == Andvari.templateId){
				return (byte)76;
			}else if(templateId == DaughterofFire.templateId){
				return (byte)255;
			}else if(templateId == DvergerArtisan.templateId){
				return (byte)76;
			}else if(templateId == DvergerExplorer.templateId){
				return (byte)76;
			}else if(templateId == DvergerGoat.templateId){
				return (byte)76;
			}else if(templateId == DvergerGoatHerder.templateId){
				return (byte)76;
			}else if(templateId == Dvergr.templateId){
				return (byte)76;
			}else if(templateId == GoblinAssassin.templateId){
				return (byte)168;
			}else if(templateId == GoblinBerserker.templateId){
				return (byte)168;
			}else if(templateId == GoblinBrute.templateId){
				return (byte)168;
			}else if(templateId == GoblinGeneral.templateId){
				return (byte)168;
			}else if(templateId == GoblinHighShaman.templateId){
				return (byte)168;
			}else if(templateId == GoblinKing.templateId){
				return (byte)168;
			}else if(templateId == GoblinScout.templateId){
				return (byte)168;
			}else if(templateId == GoblinMiner.templateId){
				return (byte)168;
			}else if(templateId == GoblinShaman.templateId){
				return (byte)168;
			}else if(templateId == HleseyCrab.templateId){
				return (byte)2;
			}else if(templateId == Jotunn.templateId){
				return (byte)168;
			}else if(templateId == Logi.templateId){
				return (byte)168;
			}else if(templateId == Narvi.templateId){
				return (byte)76;
			}else if(templateId == Risi.templateId){
				return (byte)168;
			}else if(templateId == Silverback.templateId){
				return (byte)123;
			}else if(templateId == Veattir.templateId){
				return (byte)168;
			}else if(templateId == Ymir.templateId){
				return (byte)168;
			}else if(templateId == TinyWurm.templateId){
				return (byte)1;
			}else if(templateId == TinyUnicorn.templateId){
				return (byte)244;
	        }
		} catch (IllegalArgumentException | ClassCastException e) {
			e.printStackTrace();
		}
		return (byte)127;
	}

	public static byte getCreatureColorGreen(CreatureTemplate template){
		try {
			int templateId = template.getTemplateId();
			if(templateId == Lilith.templateId){
	        	return (byte)0;
			}else if(templateId == Kari.templateId){
				return (byte)240;
			}else if(templateId == Jokul.templateId){
				return (byte)240;
			}else if(templateId == FrostGiant.templateId){
				return (byte)240;
			}else if(templateId == TinyBlueFiend.templateId){
				return (byte)240;
	        }else if(templateId == Avenger.templateId){
	        	return (byte)70;
	        }else if(templateId == LilithZombie.templateId){
	        	return (byte)0;
			}else if(templateId == Andvari.templateId){
				return (byte)76;
			}else if(templateId == DaughterofFire.templateId){
				return (byte)25;
			}else if(templateId == DvergerArtisan.templateId){
				return (byte)76;
			}else if(templateId == DvergerExplorer.templateId){
				return (byte)76;
			}else if(templateId == DvergerGoat.templateId){
				return (byte)76;
			}else if(templateId == DvergerGoatHerder.templateId){
				return (byte)76;
			}else if(templateId == Dvergr.templateId){
				return (byte)76;
			}else if(templateId == GoblinAssassin.templateId){
				return (byte)25;
			}else if(templateId == GoblinBerserker.templateId){
				return (byte)25;
			}else if(templateId == GoblinBrute.templateId){
				return (byte)25;
			}else if(templateId == GoblinGeneral.templateId){
				return (byte)25;
			}else if(templateId == GoblinHighShaman.templateId){
				return (byte)25;
			}else if(templateId == GoblinKing.templateId){
				return (byte)25;
			}else if(templateId == GoblinScout.templateId){
				return (byte)25;
			}else if(templateId == GoblinMiner.templateId){
				return (byte)25;
			}else if(templateId == GoblinShaman.templateId){
				return (byte)25;
			}else if(templateId == HleseyCrab.templateId){
				return (byte)6;
			}else if(templateId == Jotunn.templateId){
				return (byte)25;
			}else if(templateId == Logi.templateId){
				return (byte)25;
			}else if(templateId == Narvi.templateId){
				return (byte)76;
			}else if(templateId == Risi.templateId){
				return (byte)25;
			}else if(templateId == Silverback.templateId){
				return (byte)123;
			}else if(templateId == Veattir.templateId){
				return (byte)25;
			}else if(templateId == Ymir.templateId){
				return (byte)25;
			}else if(templateId == TinyWurm.templateId){
				return (byte)222;
			}else if(templateId == TinyUnicorn.templateId){
				return (byte)44;
	        }
		} catch (IllegalArgumentException | ClassCastException e) {
			e.printStackTrace();
		}
		return (byte)127;
	}

	public static byte getCreatureColorBlue(CreatureTemplate template){
		try {
			int templateId = template.getTemplateId();
			if(templateId == Lilith.templateId){
				return (byte)0;
			}else if(templateId == ForestSpider.templateId){
	        	return (byte)0;
			}else if(templateId == Kari.templateId){
				return (byte)240;
			}else if(templateId == Jokul.templateId){
				return (byte)240;
			}else if(templateId == FrostGiant.templateId){
				return (byte)240;
			}else if(templateId == TinyBlueFiend.templateId){
				return (byte)240;
	        }else if(templateId == LilithZombie.templateId){
	        	return (byte)0;
			}else if(templateId == Andvari.templateId){
				return (byte)76;
			}else if(templateId == DaughterofFire.templateId){
				return (byte)6;
			}else if(templateId == DvergerArtisan.templateId){
				return (byte)76;
			}else if(templateId == DvergerExplorer.templateId){
				return (byte)76;
			}else if(templateId == DvergerGoat.templateId){
				return (byte)76;
			}else if(templateId == DvergerGoatHerder.templateId){
				return (byte)76;
			}else if(templateId == Dvergr.templateId){
				return (byte)76;
			}else if(templateId == GoblinAssassin.templateId){
				return (byte)6;
			}else if(templateId == GoblinBerserker.templateId){
				return (byte)6;
			}else if(templateId == GoblinBrute.templateId){
				return (byte)6;
			}else if(templateId == GoblinGeneral.templateId){
				return (byte)6;
			}else if(templateId == GoblinHighShaman.templateId){
				return (byte)6;
			}else if(templateId == GoblinKing.templateId){
				return (byte)6;
			}else if(templateId == GoblinScout.templateId){
				return (byte)6;
			}else if(templateId == GoblinMiner.templateId){
				return (byte)6;
			}else if(templateId == GoblinShaman.templateId){
				return (byte)6;
			}else if(templateId == HleseyCrab.templateId){
				return (byte)239;
			}else if(templateId == Jotunn.templateId){
				return (byte)6;
			}else if(templateId == Logi.templateId){
				return (byte)6;
			}else if(templateId == Narvi.templateId){
				return (byte)76;
			}else if(templateId == Risi.templateId){
				return (byte)6;
			}else if(templateId == Silverback.templateId){
				return (byte)123;
			}else if(templateId == Veattir.templateId){
				return (byte)6;
			}else if(templateId == Ymir.templateId){
				return (byte)6;
			}else if(templateId == TinyWurm.templateId){
				return (byte)1;
			}else if(templateId == TinyUnicorn.templateId){
				return (byte)244;
	        }
		} catch (IllegalArgumentException | ClassCastException e) {
			e.printStackTrace();
		}
		return (byte)127;
	}
	
	public static float getAdjustedSizeMod(CreatureStatus status){
		try {
			float floatToRet = 1.0f;
			Creature statusHolder = ReflectionUtil.getPrivateField(status, ReflectionUtil.getField(status.getClass(), "statusHolder"));
			byte modtype = ReflectionUtil.getPrivateField(status, ReflectionUtil.getField(status.getClass(), "modtype"));
			float ageSizeModifier = ReflectionUtil.callPrivateMethod(status, ReflectionUtil.getMethod(status.getClass(), "getAgeSizeModifier"));
	        if ((!statusHolder.isVehicle() || statusHolder.isDragon()) && modtype > 0) {
	            switch (modtype) {
	                case 3: {
	                    floatToRet = 1.4f;
	                    break;
	                }
	                case 4: {
	                    floatToRet = 2.0f;
	                    break;
	                }
	                case 6: {
	                    floatToRet = 2.0f;
	                    break;
	                }
	                case 7: {
	                    floatToRet = 1.8f;
	                    break;
	                }
	                case 8: {
	                    floatToRet = 1.9f;
	                    break;
	                }
	                case 9: {
	                    floatToRet = 1.5f;
	                    break;
	                }
	                case 10: {
	                    floatToRet = 1.3f;
	                    break;
	                }
	                case 99: {
	                    floatToRet = 3.0f;
	                    break;
	                }
	                default: {
	                    //return floatToRet * ageSizeModifier;
	                }
	            }
	        }
	        int templateId = statusHolder.getTemplate().getTemplateId();
	        if(templateId == Lilith.templateId){
	        	floatToRet *= 2.45f;
	        }else if(templateId == Ifrit.templateId){
	        	floatToRet *= 0.35f; // The base model is way too big. I'm tilted.
	        }else if(templateId == WyvernBlack.templateId){
	        	floatToRet *= 0.6f;
			}else if(templateId == Fafnir.templateId){
				floatToRet *= 4.0f;
			}else if(templateId == DragonWhelps.templateId){
				floatToRet *= 0.4f;
	        }else if(templateId == WyvernGreen.templateId){
	        	floatToRet *= 0.6f;
	        }else if(templateId == WyvernRed.templateId){
	        	floatToRet *= 0.6f;
	        }else if(templateId == WyvernWhite.templateId){
	        	floatToRet *= 0.6f;
			}else if(templateId == WyvernBlue.templateId){
				floatToRet *= 0.6f;
			}else if(templateId == GhostDrake.templateId){
				floatToRet *= 0.6f;
	        }else if(templateId == ForestSpider.templateId){
	        	floatToRet *= 1.4f;
	        }else if(templateId == Avenger.templateId){
	        	floatToRet *= 0.35f;
			}else if(templateId == HornedPony.templateId){
				floatToRet *= 1.1f;
	        }else if(templateId == LargeBoar.templateId){
	        	floatToRet *= 6.2f;
			}else if(templateId == NorthWindDreki.templateId){
				floatToRet *= 0.4f;
			}else if(templateId == LittleLizard.templateId){
				floatToRet *= 0.2f;
			}else if(templateId == TinyBear.templateId){
				floatToRet *= 0.3f;
			}else if(templateId == TinyBlack.templateId){
				floatToRet *= 0.2f;
			}else if(templateId == TinyBlue.templateId){
				floatToRet *= 0.2f;
			}else if(templateId == TinyBlackWidow.templateId){
				floatToRet *= 0.4f;
			}else if(templateId == TinyBlueFiend.templateId){
				floatToRet *= 0.3f;
			}else if(templateId == TinyCrab.templateId){
				floatToRet *= 0.4f;
			}else if(templateId == TinyCroc.templateId){
				floatToRet *= 0.3f;
			}else if(templateId == TinyDiablo.templateId){
				floatToRet *= 0.2f;
			}else if(templateId == TinyGorilla.templateId){
				floatToRet *= 0.4f;
			}else if(templateId == TinyGreen.templateId){
				floatToRet *= 0.2f;
			}else if(templateId == TinyHellScorp.templateId){
				floatToRet *= 0.2f;
			}else if(templateId == TinyHellHorse.templateId){
				floatToRet *= 0.3f;
			}else if(templateId == TinyHorse.templateId){
				floatToRet *= 0.3f;
			}else if(templateId == TinyMountainLion.templateId){
				floatToRet *= 0.5f;
			}else if(templateId == TinyRed.templateId){
				floatToRet *= 0.2f;
			}else if(templateId == TinyScorp.templateId){
				floatToRet *= 0.3f;
			}else if(templateId == TinySkeleton.templateId){
				floatToRet *= 0.4f;
			}else if(templateId == TinySnake.templateId){
				floatToRet *= 0.4f;
			}else if(templateId == TinySnowMan.templateId){
				floatToRet *= 0.4f;
			}else if(templateId == TinySpider.templateId){
				floatToRet *= 0.2f;
			}else if(templateId == TinyTurtle.templateId){
				floatToRet *= 0.4f;
			}else if(templateId == TinyWhite.templateId){
				floatToRet *= 0.2f;
			}else if(templateId == TinyUnicorn.templateId){
				floatToRet *= 0.3f;
			}else if(templateId == TinyWorg.templateId){
				floatToRet *= 0.4f;
			}else if(templateId == TinyWurm.templateId){
				floatToRet *= 0.1f;
			}else if(templateId == TinyWolf.templateId){
				floatToRet *= 0.4f;
			}else if(templateId == TinyZombie.templateId){
				floatToRet *= 0.4f;
			}else if(templateId == Frosti.templateId){
				floatToRet *= 2.0f;
			}else if(templateId == FrostGiant.templateId){
				floatToRet *= 2.0f;
			}else if(templateId == Kari.templateId){
				floatToRet *= 2.5f;
			}else if(templateId == Jokul.templateId){
				floatToRet *= 0.4f;
			}else if(templateId == LizardKing.templateId){
				floatToRet *= 5.50f;
			}else if(templateId == King.templateId){
				floatToRet *= 5.50f;
	        }else if(templateId == Giant.templateId){
	        	floatToRet *= 0.75f;
	        }else if(templateId == LilithZombie.templateId){
	        	floatToRet *= 0.75f;
			}else if(templateId == Aegir.templateId){
				floatToRet *= 2.5f;
			}else if(templateId == DaughterOfAegir.templateId){
				floatToRet *= 2.0f;
			}else if(templateId == FimaFeng.templateId){
				floatToRet *= 2.0f;
			}else if(templateId == SeaMonster.templateId){
				floatToRet *= 0.5f;
			}else if(templateId == Dvergr.templateId){
				floatToRet *= 1.05f;
			}else if(templateId == DvergerGoatHerder.templateId){
				floatToRet *= 1.25f;
			}else if(templateId == DvergerExplorer.templateId){
				floatToRet *= 1.25f;
			}else if(templateId == DvergerArtisan.templateId){
				floatToRet *= 1.55f;
			}else if(templateId == DvergerGoat.templateId){
				floatToRet *= 1.5f;
			}else if(templateId == Andvari.templateId){
				floatToRet *= 2.5f;
			}else if(templateId == Narvi.templateId){
				floatToRet *= 2.0f;
			}else if(templateId == DaughterofFire.templateId){
				floatToRet *= 0.25f;
			}else if(templateId == DeputyWardenBob.templateId){
				floatToRet *= 1.5f;
			}else if(templateId == GuardCaptainFred.templateId){
				floatToRet *= 1.25f;
			}else if(templateId == GoblinBrute.templateId){
				floatToRet *= 1.25f;
			}else if(templateId == GoblinKing.templateId){
				floatToRet *= 2.5f;
			}else if(templateId == GoblinGeneral.templateId){
				floatToRet *= 1.5f;
			}else if(templateId == GoblinShaman.templateId){
				floatToRet *= 0.75f;
			}else if(templateId == GoblinHighShaman.templateId){
				floatToRet *= 1.75f;
			}else if(templateId == HleseyCrab.templateId){
				floatToRet *= 2.5f;
			}else if(templateId == Hyena.templateId){
				floatToRet *= 1.5f;
			}else if(templateId == Jotunn.templateId){
				floatToRet *= 2.5f;
			}else if(templateId == Logi.templateId){
				floatToRet *= 2.75f;
			}else if(templateId == Glod.templateId){
				floatToRet *= 2.5f;
			}else if(templateId == Risi.templateId){
				floatToRet *= 1.75f;
			}else if(templateId == Silverback.templateId){
				floatToRet *= 2.5f;
			}else if(templateId == Veattir.templateId){
				floatToRet *= 1.75f;
			}else if(templateId == GiantTortoise.templateId){
				floatToRet *= 1.50f;
			}else if(templateId == Ymir.templateId){
				floatToRet *= 3.25f;
	        }
	        
	        return floatToRet * ageSizeModifier;
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | ClassCastException | NoSuchFieldException e) {
			e.printStackTrace();
		}
        return 1.0f;
	}

	
	public static boolean isArcheryImmune(Creature performer, Creature defender){
		if(Arena.isTitan(defender) || Arena.isTitanMinion(defender)){
			performer.getCommunicator().sendCombatNormalMessage("You cannot archer "+defender.getName()+", as it is protected by a boss.");
			return true;
		}
		String message = "The "+defender.getName()+" would be unaffected by your arrows.";
		boolean immune = false;
		Item arrow = Archery.getArrow(performer);
		if(arrow == null){ // Copied directly from the attack() method in Archery.
			performer.getCommunicator().sendCombatNormalMessage("You have no arrows left to shoot!");
            return true;
		}
		int defenderTemplateId = defender.getTemplate().getTemplateId();
		if(defender.isRegenerating() && arrow.getTemplateId() == ItemList.arrowShaft){
			message = "The "+defender.getName()+" would be unaffected by the "+arrow.getName()+".";
			immune = true;
		}else if(defender.getTemplate().isNotRebirthable()){
			immune = true;
		}else if(defenderTemplateId == Giant.templateId){
			immune = true;
		}else if(defender.isUnique()){
			immune = true;
		}
		if(immune){
			performer.getCommunicator().sendCombatNormalMessage(message);
		}
		return immune;
	}
	
	public static void modifyNewCreature(Creature creature){
		try{
			if(Arena.isTitan(creature)){
				Arena.addTitan(creature);
				MiscChanges.sendGlobalFreedomChat(creature, "The boss "+creature.getName()+" has stepped into the mortal realm. Challenge "+creature.getHimHerItString()+"  if you dare.", 255, 105, 180);

			}else if(creature.getTemplate().getTemplateId() == Facebreyker.templateId){
				Item club = ItemFactory.createItem(Club.templateId, 80f+(Server.rand.nextFloat()*15f), Server.rand.nextBoolean() ? Materials.MATERIAL_GLIMMERSTEEL : Materials.MATERIAL_ADAMANTINE, Server.rand.nextBoolean() ? (byte) 1 : (byte) 2, "Facebreyker");
				creature.getInventory().insertItem(club);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void setTemplateVariables(){
		try{
			// Set corpse models
			CreatureTemplate spectralDrake = CreatureTemplateFactory.getInstance().getTemplate(SpectralDrake.templateId);
			if(spectralDrake != null){
				ReflectionUtil.setPrivateField(spectralDrake, ReflectionUtil.getField(spectralDrake.getClass(), "corpsename"), "fogspider.");
			}
			CreatureTemplate blackWyvern = CreatureTemplateFactory.getInstance().getTemplate(WyvernBlack.templateId);
			if(blackWyvern != null){
				ReflectionUtil.setPrivateField(blackWyvern, ReflectionUtil.getField(blackWyvern.getClass(), "corpsename"), "blackdragonhatchling.");
			}
			CreatureTemplate greenWyvern = CreatureTemplateFactory.getInstance().getTemplate(WyvernGreen.templateId);
			if(greenWyvern != null){
				ReflectionUtil.setPrivateField(greenWyvern, ReflectionUtil.getField(greenWyvern.getClass(), "corpsename"), "greendragonhatchling.");
			}
			CreatureTemplate redWyvern = CreatureTemplateFactory.getInstance().getTemplate(WyvernRed.templateId);
			if(redWyvern != null){
				ReflectionUtil.setPrivateField(redWyvern, ReflectionUtil.getField(redWyvern.getClass(), "corpsename"), "reddragonhatchling.");
			}
			CreatureTemplate whiteWyvern = CreatureTemplateFactory.getInstance().getTemplate(WyvernWhite.templateId);
			if(whiteWyvern != null){
				ReflectionUtil.setPrivateField(whiteWyvern, ReflectionUtil.getField(whiteWyvern.getClass(), "corpsename"), "whitedragonhatchling.");
			}
			CreatureTemplate facebreyker = CreatureTemplateFactory.getInstance().getTemplate(Facebreyker.templateId);
			if(facebreyker != null){
				ReflectionUtil.setPrivateField(facebreyker, ReflectionUtil.getField(facebreyker.getClass(), "corpsename"), "riftogre.");
			}
			CreatureTemplate forestSpider = CreatureTemplateFactory.getInstance().getTemplate(ForestSpider.templateId);
			if(forestSpider != null){
				ReflectionUtil.setPrivateField(forestSpider, ReflectionUtil.getField(forestSpider.getClass(), "corpsename"), "fogspider.");
			}
			CreatureTemplate giant = CreatureTemplateFactory.getInstance().getTemplate(Giant.templateId);
			if(giant != null){
				ReflectionUtil.setPrivateField(giant, ReflectionUtil.getField(giant.getClass(), "corpsename"), "forestgiant.");
			}
			CreatureTemplate largeBoar = CreatureTemplateFactory.getInstance().getTemplate(LargeBoar.templateId);
			if(largeBoar != null){
				ReflectionUtil.setPrivateField(largeBoar, ReflectionUtil.getField(largeBoar.getClass(), "corpsename"), "wildboar.");
			}
			CreatureTemplate ifritSpider = CreatureTemplateFactory.getInstance().getTemplate(IfritSpider.templateId);
			if(ifritSpider != null){
				ReflectionUtil.setPrivateField(ifritSpider, ReflectionUtil.getField(ifritSpider.getClass(), "corpsename"), "lavaspider.");
			}
			CreatureTemplate ifritFiend = CreatureTemplateFactory.getInstance().getTemplate(IfritFiend.templateId);
			if(ifritFiend != null){
				ReflectionUtil.setPrivateField(ifritFiend, ReflectionUtil.getField(ifritFiend.getClass(), "corpsename"), "lavafiend.");
			}

			// Also apply the ghost modifier
			CreatureTemplate spiritTroll = CreatureTemplateFactory.getInstance().getTemplate(SpiritTroll.templateId);
			if(spiritTroll != null){
				ReflectionUtil.setPrivateField(spiritTroll, ReflectionUtil.getField(spiritTroll.getClass(), "ghost"), true);
			}
			CreatureTemplate avenger = CreatureTemplateFactory.getInstance().getTemplate(Avenger.templateId);
			if(avenger != null){
				ReflectionUtil.setPrivateField(avenger, ReflectionUtil.getField(avenger.getClass(), "ghost"), true);
			}
			CreatureTemplate lilithWight = CreatureTemplateFactory.getInstance().getTemplate(LilithWraith.templateId);
			if(lilithWight != null){
				ReflectionUtil.setPrivateField(lilithWight, ReflectionUtil.getField(lilithWight.getClass(), "ghost"), true);
			}
			CreatureTemplate ghostDrake = CreatureTemplateFactory.getInstance().getTemplate(GhostDrake.templateId);
			if(ghostDrake != null){
				ReflectionUtil.setPrivateField(ghostDrake, ReflectionUtil.getField(ghostDrake.getClass(), "ghost"), true);
			}


			
			// Dragon natural armour increases:
			CreatureTemplate dragonBlue = CreatureTemplateFactory.getInstance().getTemplate(CreatureTemplate.DRAGON_BLUE_CID);
			ReflectionUtil.setPrivateField(dragonBlue, ReflectionUtil.getField(dragonBlue.getClass(), "naturalArmour"), 0.03f);
			CreatureTemplate dragonGreen = CreatureTemplateFactory.getInstance().getTemplate(CreatureTemplate.DRAGON_WHITE_CID);
			ReflectionUtil.setPrivateField(dragonGreen, ReflectionUtil.getField(dragonGreen.getClass(), "naturalArmour"), 0.03f);
			CreatureTemplate dragonBlack = CreatureTemplateFactory.getInstance().getTemplate(CreatureTemplate.DRAGON_BLACK_CID);
			ReflectionUtil.setPrivateField(dragonBlack, ReflectionUtil.getField(dragonBlack.getClass(), "naturalArmour"), 0.03f);
			CreatureTemplate dragonWhite = CreatureTemplateFactory.getInstance().getTemplate(CreatureTemplate.DRAGON_WHITE_CID);
			ReflectionUtil.setPrivateField(dragonWhite, ReflectionUtil.getField(dragonWhite.getClass(), "naturalArmour"), 0.03f);
			// Drake natural armour increases:
			CreatureTemplate drakeRed = CreatureTemplateFactory.getInstance().getTemplate(CreatureTemplate.DRAKE_RED_CID);
			ReflectionUtil.setPrivateField(drakeRed, ReflectionUtil.getField(drakeRed.getClass(), "naturalArmour"), 0.03f);
			CreatureTemplate drakeBlue = CreatureTemplateFactory.getInstance().getTemplate(CreatureTemplate.DRAKE_BLUE_CID);
			ReflectionUtil.setPrivateField(drakeBlue, ReflectionUtil.getField(drakeBlue.getClass(), "naturalArmour"), 0.03f);
			CreatureTemplate drakeWhite = CreatureTemplateFactory.getInstance().getTemplate(CreatureTemplate.DRAKE_WHITE_CID);
			ReflectionUtil.setPrivateField(drakeWhite, ReflectionUtil.getField(drakeWhite.getClass(), "naturalArmour"), 0.03f);
			CreatureTemplate drakeGreen = CreatureTemplateFactory.getInstance().getTemplate(CreatureTemplate.DRAKE_GREEN_CID);
			ReflectionUtil.setPrivateField(drakeGreen, ReflectionUtil.getField(drakeGreen.getClass(), "naturalArmour"), 0.03f);
			CreatureTemplate drakeBlack = CreatureTemplateFactory.getInstance().getTemplate(CreatureTemplate.DRAKE_BLACK_CID);
			ReflectionUtil.setPrivateField(drakeBlack, ReflectionUtil.getField(drakeBlack.getClass(), "naturalArmour"), 0.03f);
			// Goblin leader natural armour increase:
			CreatureTemplate goblinLeader = CreatureTemplateFactory.getInstance().getTemplate(CreatureTemplate.GOBLIN_LEADER_CID);
			ReflectionUtil.setPrivateField(goblinLeader, ReflectionUtil.getField(goblinLeader.getClass(), "naturalArmour"), 0.03f);
			
		} catch (NoSuchCreatureTemplateException | IllegalArgumentException | IllegalAccessException | ClassCastException | NoSuchFieldException e) {
			e.printStackTrace();
		}
	}
}

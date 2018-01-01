package org.gotti.wurmunlimited.mods.creatures.Mounts;

import org.gotti.wurmunlimited.modsupport.CreatureTemplateBuilder;
import org.gotti.wurmunlimited.modsupport.creatures.EncounterBuilder;
import org.gotti.wurmunlimited.modsupport.creatures.ModCreature;
import org.gotti.wurmunlimited.modsupport.vehicles.ModVehicleBehaviour;
import org.gotti.wurmunlimited.modsupport.vehicles.VehicleFacade;

import com.wurmonline.mesh.Tiles;
import com.wurmonline.server.Servers;
import com.wurmonline.server.behaviours.Vehicle;
import com.wurmonline.server.bodys.BodyTemplate;
import com.wurmonline.server.bodys.Wound;
import com.wurmonline.server.combat.ArmourTypes;
import com.wurmonline.server.creatures.Creature;
import com.wurmonline.server.creatures.CreatureTypes;
import com.wurmonline.server.items.Item;
import com.wurmonline.server.items.ItemList;
import com.wurmonline.server.items.Materials;
import com.wurmonline.server.skills.SkillList;

public class WyvernBlack implements ModCreature, CreatureTypes {
	public static int templateId;
	@Override
	public CreatureTemplateBuilder createCreateTemplateBuilder() {
		// {C_TYPE_MOVE_LOCAL, C_TYPE_VEHICLE, C_TYPE_ANIMAL, C_TYPE_LEADABLE, C_TYPE_GRAZER, C_TYPE_OMNIVORE, C_TYPE_DOMINATABLE, C_TYPE_AGG_HUMAN, C_TYPE_NON_NEWBIE, C_TYPE_BURNING}; - Hell Horse
		int[] types = {
				CreatureTypes.C_TYPE_VEHICLE,
				CreatureTypes.C_TYPE_HERBIVORE,
				CreatureTypes.C_TYPE_LEADABLE,
				CreatureTypes.C_TYPE_MOVE_GLOBAL,
				CreatureTypes.C_TYPE_SWIMMING,
				CreatureTypes.C_TYPE_HUNTING,
				CreatureTypes.C_TYPE_HORSE,
				CreatureTypes.C_TYPE_DOMINATABLE,
				CreatureTypes.C_TYPE_NON_NEWBIE,
				CreatureTypes.C_TYPE_GRAZER,
				CreatureTypes.C_TYPE_DOMESTIC,
				CreatureTypes.C_TYPE_ANIMAL
		};
		
		//public CreatureTemplateBuilder(final String identifier, final String name, final String description,
		//       final String modelName, final int[] types, final byte bodyType, final short vision, final byte sex, final short centimetersHigh, final short centimetersLong, final short centimetersWide,
		//       final String deathSndMale, final String deathSndFemale, final String hitSndMale, final String hitSndFemale,
		//       final float naturalArmour, final float handDam, final float kickDam, final float biteDam, final float headDam, final float breathDam, final float speed, final int moveRate,
		//       final int[] itemsButchered, final int maxHuntDist, final int aggress) {
		CreatureTemplateBuilder builder = new CreatureTemplateBuilder("mod.creature.wyvern.black", "Drage", "A battle-hardened drage with scales as black as night.",
				"model.creature.drake.black", types, BodyTemplate.TYPE_DRAGON, (short) 10, (byte) 0, (short) 350, (short) 100, (short) 60,
				"sound.death.dragon", "sound.death.dragon", "sound.combat.hit.dragon", "sound.combat.hit.dragon",
				Servers.localServer.PVPSERVER ? 0.65f : 0.35f, 20.0f, 23.0f, 0.0f, 0.0f, 0.0f, 1.6f, 800,
				new int[]{ItemList.animalHide, ItemList.tail, ItemList.eye, ItemList.gland, ItemList.tooth}, 40, 70, Materials.MATERIAL_MEAT_DRAGON);
		
		builder.skill(SkillList.BODY_STRENGTH,40.0f);
		builder.skill(SkillList.BODY_STAMINA, 50.0f);
		builder.skill(SkillList.BODY_CONTROL, 50.0f);
		builder.skill(SkillList.MIND_LOGICAL, 50.0f);
		builder.skill(SkillList.MIND_SPEED, 50.0f);
		builder.skill(SkillList.SOUL_STRENGTH, 50.0f);
		builder.skill(SkillList.SOUL_DEPTH, 50.0f);
		builder.skill(SkillList.WEAPONLESS_FIGHTING, 70.0f);
		builder.skill(SkillList.GROUP_FIGHTING, 70.0f);
		
		builder.boundsValues(-0.5f, -1.0f, 0.5f, 1.42f);
		builder.handDamString("bite");
		builder.kickDamString("wingbuff");
		builder.maxAge(200);
		builder.armourType(Servers.localServer.PVPSERVER ? ArmourTypes.ARMOUR_CLOTH : ArmourTypes.ARMOUR_SCALE_DRAGON);
		builder.baseCombatRating(28.0f);
		builder.combatDamageType(Wound.TYPE_PIERCE);
		builder.maxGroupAttackSize(10);

		templateId = builder.getTemplateId();
		return builder;
	}
	
	public ModVehicleBehaviour getVehicleBehaviour() {

		return new ModVehicleBehaviour() {

			@Override
			public void setSettingsForVehicle(Item item, Vehicle vehicle) {
			}

			@Override
			public void setSettingsForVehicle(Creature creature, Vehicle v) {
				VehicleFacade vehicle = wrap(v);

				vehicle.createPassengerSeats(0);
				vehicle.setSeatFightMod(0, 0.8f, 1.1f);
				vehicle.setSeatOffset(0, 0.0f, 0.0f, 0.0f);
				vehicle.setCanHaveEquipment(true);
				vehicle.setCreature(true);
				vehicle.setSkillNeeded(30.0f);
				vehicle.setName(creature.getName());
				vehicle.setMaxHeightDiff(0.15f);
				vehicle.setMaxDepth(-50f);
				vehicle.setMaxSpeed(51.0f);
				vehicle.setCommandType((byte) 3);
			}
		};
	}

}

package org.gotti.wurmunlimited.mods.creatures.GoblinCitadel;

import com.wurmonline.mesh.Tiles;
import com.wurmonline.server.bodys.BodyTemplate;
import com.wurmonline.server.bodys.Wound;
import com.wurmonline.server.combat.ArmourTypes;
import com.wurmonline.server.creatures.CreatureTypes;
import com.wurmonline.server.items.ItemList;
import com.wurmonline.server.items.Materials;
import com.wurmonline.server.skills.SkillList;
import org.gotti.wurmunlimited.modsupport.CreatureTemplateBuilder;
import org.gotti.wurmunlimited.modsupport.creatures.EncounterBuilder;
import org.gotti.wurmunlimited.modsupport.creatures.ModCreature;

/**
 * Created by Jerone on 10/27/2017.
 */
public class GoblinHighShaman implements ModCreature, CreatureTypes {
    public static int templateId;

    @Override
    public CreatureTemplateBuilder createCreateTemplateBuilder() {
        // {C_TYPE_MOVE_LOCAL, C_TYPE_VEHICLE, C_TYPE_ANIMAL, C_TYPE_LEADABLE, C_TYPE_GRAZER, C_TYPE_OMNIVORE, C_TYPE_DOMINATABLE, C_TYPE_AGG_HUMAN, C_TYPE_NON_NEWBIE, C_TYPE_BURNING}; - Hell Horse
        // int[] types = new int[]{7, 6, 13, 3, 29, 39, 60, 61}; - goblin
        int[] types = {
                CreatureTypes.C_TYPE_MOVE_LOCAL,
                CreatureTypes.C_TYPE_AGG_HUMAN,
                CreatureTypes.C_TYPE_HUNTING,
                CreatureTypes.C_TYPE_ANIMAL,
                CreatureTypes.C_TYPE_CARNIVORE,
                CreatureTypes.C_TYPE_NON_NEWBIE,
                CreatureTypes.C_TYPE_NO_REBIRTH,
                CreatureTypes.C_TYPE_REGENERATING,
                CreatureTypes.C_TYPE_CASTER,
                CreatureTypes.C_TYPE_SUMMONER,
        };

        //public CreatureTemplateBuilder(final String identifier, final String name, final String description,
        //       final String modelName, final int[] types, final byte bodyType, final short vision, final byte sex, final short centimetersHigh, final short centimetersLong, final short centimetersWide,
        //       final String deathSndMale, final String deathSndFemale, final String hitSndMale, final String hitSndFemale,
        //       final float naturalArmour, final float handDam, final float kickDam, final float biteDam, final float headDam, final float breathDam, final float speed, final int moveRate,
        //       final int[] itemsButchered, final int maxHuntDist, final int aggress) {
        CreatureTemplateBuilder builder = new CreatureTemplateBuilder("mod.creature.goblin.highshaman", "Goblin High Shaman", "High Shaman responsible for summoning Ymir.",
                "model.creature.humanoid.goblin", types, BodyTemplate.TYPE_HUMAN, (short) 5, (byte) 0, (short) 85, (short) 50, (short) 85,
                "sound.death.goblin", "sound.death.goblin", "sound.combat.hit.goblin", "sound.combat.hit.goblin",
                0.05f, 15f, 0f, 15.0f, 0.0f, 35.0f, 1.2f, 500,
                new int[]{ItemList.heart}, 10, 74, Materials.MATERIAL_MEAT_HUMANOID);

        builder.skill(SkillList.BODY_STRENGTH, 80.0f);
        builder.skill(SkillList.BODY_STAMINA, 50.0f);
        builder.skill(SkillList.BODY_CONTROL, 45.0f);
        builder.skill(SkillList.MIND_LOGICAL, 30.0f);
        builder.skill(SkillList.MIND_SPEED, 90.0f);
        builder.skill(SkillList.SOUL_STRENGTH, 90.0f);
        builder.skill(SkillList.SOUL_DEPTH, 90.0f);
        builder.skill(SkillList.WEAPONLESS_FIGHTING, 50.0f);
        builder.skill(SkillList.GROUP_FIGHTING, 30.0f);

        builder.boundsValues(-0.5f, -1.0f, 0.5f, 1.42f);
        builder.handDamString("poison splash");
        builder.maxAge(100);
        builder.armourType(ArmourTypes.ARMOUR_LEATHER_DRAGON);
        builder.baseCombatRating(53.0f);
        builder.combatDamageType(Wound.TYPE_POISON);
        builder.denMaterial(Materials.MATERIAL_STONE);
        builder.denName("forest goblin lair");
        builder.maxPercentOfCreatures(0.005f);
        builder.maxGroupAttackSize(100);
        builder.setCombatMoves(new int[] { 9, 11 });

        templateId = builder.getTemplateId();
        return builder;
    }
}
package org.gotti.wurmunlimited.mods.creatures.Unique;

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
 * Created by Jerone on 11/24/2017.
 */
public class LizardKing implements ModCreature, CreatureTypes {
    public static int templateId;

    @Override
    public CreatureTemplateBuilder createCreateTemplateBuilder() {
        // {C_TYPE_MOVE_LOCAL, C_TYPE_VEHICLE, C_TYPE_ANIMAL, C_TYPE_LEADABLE, C_TYPE_GRAZER, C_TYPE_OMNIVORE, C_TYPE_DOMINATABLE, C_TYPE_AGG_HUMAN, C_TYPE_NON_NEWBIE, C_TYPE_BURNING}; - Hell Horse
        // int[] types = new int[]{7, 6, 13, 3, 29, 39, 60, 61}; - Spider
        int[] types = {
                CreatureTypes.C_TYPE_HERBIVORE,
                CreatureTypes.C_TYPE_MONSTER,
                CreatureTypes.C_TYPE_MOVE_GLOBAL,
                CreatureTypes.C_TYPE_VEHICLE,
                CreatureTypes.C_TYPE_AGG_HUMAN,
                CreatureTypes.C_TYPE_LEADABLE,
                CreatureTypes.C_TYPE_SWIMMING,
                CreatureTypes.C_TYPE_HUNTING,
                CreatureTypes.C_TYPE_DOMINATABLE,
                CreatureTypes.C_TYPE_NON_NEWBIE,
                CreatureTypes.C_TYPE_GRAZER,
                CreatureTypes.C_TYPE_CAVEDWELLER,
                CreatureTypes.C_TYPE_ANIMAL
        };

        //public CreatureTemplateBuilder(final String identifier, final String name, final String description,
        //       final String modelName, final int[] types, final byte bodyType, final short vision, final byte sex, final short centimetersHigh, final short centimetersLong, final short centimetersWide,
        //       final String deathSndMale, final String deathSndFemale, final String hitSndMale, final String hitSndFemale,
        //       final float naturalArmour, final float handDam, final float kickDam, final float biteDam, final float headDam, final float breathDam, final float speed, final int moveRate,
        //       final int[] itemsButchered, final int maxHuntDist, final int aggress) {
        CreatureTemplateBuilder builder = new CreatureTemplateBuilder("mod.creature.lizardKing", "Lizard King", "The King of the ancient alien race of deathcrawlers",
                "model.creature.deathcrawler", types, (byte) BodyTemplate.TYPE_SNAKE, (short) 5, (byte) 0, (short) 85, (short) 50, (short) 85,
                "sound.death.deathcrawler", "sound.death.deathcrawler", "sound.combat.hit.deathcrawler", "sound.combat.hit.deathcrawler",
                0.02f, 50f, 20f, 50.0f, 24.0f, 50.0f, 1.8f, 700,
                new int[]{ItemList.fur, ItemList.skull, ItemList.boneCollar, ItemList.heart}, 10, 74, Materials.MATERIAL_MEAT_TOUGH);

        builder.skill(SkillList.BODY_STRENGTH, 90.0f);
        builder.skill(SkillList.BODY_STAMINA, 90.0f);
        builder.skill(SkillList.BODY_CONTROL, 90.0f);
        builder.skill(SkillList.MIND_LOGICAL, 30.0f);
        builder.skill(SkillList.MIND_SPEED, 90.0f);
        builder.skill(SkillList.SOUL_STRENGTH, 90.0f);
        builder.skill(SkillList.SOUL_DEPTH, 90.0f);
        builder.skill(SkillList.WEAPONLESS_FIGHTING, 80.0f);
        builder.skill(SkillList.GROUP_FIGHTING, 80.0f);
        builder.skill(SkillList.GROUP_CLUBS, 99.0f);
        builder.skill(SkillList.CLUB_HUGE, 99.0f);

        builder.boundsValues(-0.5f, -1.0f, 0.5f, 1.42f);
        builder.handDamString("claw");
        builder.kickDamString("tail whip");
        builder.maxAge(200);
        builder.armourType(10);
        builder.baseCombatRating(90.0f);
        builder.combatDamageType(Wound.TYPE_POISON);
        builder.maxGroupAttackSize(100);
        builder.setCombatMoves(new int[] { 7, 9, 6 });

        templateId = builder.getTemplateId();
        return builder;
    }

}
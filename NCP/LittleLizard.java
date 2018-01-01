package org.gotti.wurmunlimited.mods.creatures.NCP;

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
 * Created by Jerone on 11/21/2017.
 */
public class LittleLizard implements ModCreature, CreatureTypes {
    public static int templateId;

    @Override
    public CreatureTemplateBuilder createCreateTemplateBuilder() {
        // {C_TYPE_MOVE_LOCAL, C_TYPE_VEHICLE, C_TYPE_ANIMAL, C_TYPE_LEADABLE, C_TYPE_GRAZER, C_TYPE_OMNIVORE, C_TYPE_DOMINATABLE, C_TYPE_AGG_HUMAN, C_TYPE_NON_NEWBIE, C_TYPE_BURNING}; - Hell Horse
        // int[] types = new int[]{7, 6, 13, 3, 29, 39, 60, 61}; - Spider
        int[] types = {
                CreatureTypes.C_TYPE_HERBIVORE,
                CreatureTypes.C_TYPE_MOVE_GLOBAL,
                CreatureTypes.C_TYPE_SWIMMING,
                CreatureTypes.C_TYPE_HUNTING,
                CreatureTypes.C_TYPE_MONSTER,
                CreatureTypes.C_TYPE_HORSE,
                CreatureTypes.C_TYPE_DOMINATABLE,
                CreatureTypes.C_TYPE_DOMESTIC,
                CreatureTypes.C_TYPE_GRAZER,
                CreatureTypes.C_TYPE_ANIMAL
        };

        //public CreatureTemplateBuilder(final String identifier, final String name, final String description,
        //       final String modelName, final int[] types, final byte bodyType, final short vision, final byte sex, final short centimetersHigh, final short centimetersLong, final short centimetersWide,
        //       final String deathSndMale, final String deathSndFemale, final String hitSndMale, final String hitSndFemale,
        //       final float naturalArmour, final float handDam, final float kickDam, final float biteDam, final float headDam, final float breathDam, final float speed, final int moveRate,
        //       final int[] itemsButchered, final int maxHuntDist, final int aggress) {
        CreatureTemplateBuilder builder = new CreatureTemplateBuilder("mod.creature.littlelizard", "Spike", "A little lizard named spike.",
                "model.creature.deathcrawler", types, (byte) BodyTemplate.TYPE_HUMAN, (short) 5, (byte) 1, (short) 85, (short) 50, (short) 85,
                "sound.combat.deathcrawler.attack", "sound.combat.deathcrawler.death", "sound.combat.deathcrawler.gethit", "sound.combat.deathcrawler.gethit",
                0.8f, 1f, 0f, 0.0f, 0.0f, 0.0f, 1.6f, 500,
                new int[]{}, 10, 74, Materials.MATERIAL_MEAT_TOUGH);

        builder.skill(SkillList.BODY_STRENGTH, 1.0f);
        builder.skill(SkillList.BODY_STAMINA, 95.0f);
        builder.skill(SkillList.BODY_CONTROL, 45.0f);
        builder.skill(SkillList.MIND_LOGICAL, 5.0f);
        builder.skill(SkillList.MIND_SPEED, 10.0f);
        builder.skill(SkillList.SOUL_STRENGTH, 34.0f);
        builder.skill(SkillList.SOUL_DEPTH, 3.0f);
        builder.skill(SkillList.WEAPONLESS_FIGHTING, 40.0f);
        builder.skill(SkillList.GROUP_FIGHTING, 30.0f);

        builder.boundsValues(-0.5f, -1.0f, 0.5f, 1.42f);
        builder.handDamString("bite");
        builder.maxAge(100);
        builder.armourType(ArmourTypes.ARMOUR_CLOTH);
        builder.baseCombatRating(10.0f);
        builder.combatDamageType(Wound.TYPE_CRUSH);
        builder.maxPercentOfCreatures(0.001f);
        builder.maxGroupAttackSize(100);

        templateId = builder.getTemplateId();
        return builder;
    }


}
package org.gotti.wurmunlimited.mods.creatures.Jotunheimr;

import com.wurmonline.server.bodys.BodyTemplate;
import com.wurmonline.server.bodys.Wound;
import com.wurmonline.server.combat.ArmourTypes;
import com.wurmonline.server.creatures.CreatureTypes;
import com.wurmonline.server.items.ItemList;
import com.wurmonline.server.items.Materials;
import com.wurmonline.server.skills.SkillList;
import org.gotti.wurmunlimited.modsupport.CreatureTemplateBuilder;
import org.gotti.wurmunlimited.modsupport.creatures.ModCreature;

/**
 * Created by Jerone on 11/3/2017.
 */
public class Glod implements ModCreature, CreatureTypes {
    public static int templateId;

    @Override
    public CreatureTemplateBuilder createCreateTemplateBuilder() {
        // {C_TYPE_MOVE_LOCAL, C_TYPE_VEHICLE, C_TYPE_ANIMAL, C_TYPE_LEADABLE, C_TYPE_GRAZER, C_TYPE_OMNIVORE, C_TYPE_DOMINATABLE, C_TYPE_AGG_HUMAN, C_TYPE_NON_NEWBIE, C_TYPE_BURNING}; - Hell Horse
        // int[] types = new int[]{7, 6, 13, 3, 29, 39, 60, 61}; - dog
        int[] types = {
                CreatureTypes.C_TYPE_MOVE_LOCAL,
                CreatureTypes.C_TYPE_AGG_HUMAN,
                CreatureTypes.C_TYPE_HUNTING,
                CreatureTypes.C_TYPE_MONSTER,
                CreatureTypes.C_TYPE_CARNIVORE,
                CreatureTypes.C_TYPE_NON_NEWBIE,
                CreatureTypes.C_TYPE_NO_REBIRTH,
        };

        //public CreatureTemplateBuilder(final String identifier, final String name, final String description,
        //       final String modelName, final int[] types, final byte bodyType, final short vision, final byte sex, final short centimetersHigh, final short centimetersLong, final short centimetersWide,
        //       final String deathSnddog, final String deathSndFedog, final String hitSnddog, final String hitSndFedog,
        //       final float naturalArmour, final float handDam, final float kickDam, final float biteDam, final float headDam, final float breathDam, final float speed, final int moveRate,
        //       final int[] itemsButchered, final int maxHuntDist, final int aggress) {
        CreatureTemplateBuilder builder = new CreatureTemplateBuilder("mod.creature.glod", "Glod", "Great Fire Giant Glod",
                "model.creature.humanoid.lavacreature", types, BodyTemplate.TYPE_CYCLOPS, (short) 5, (byte) 0, (short) 85, (short) 50, (short) 85,
                "sound.death.troll", "sound.death.troll", "sound.combat.troll", "sound.combat.troll",
                0.1f, 15f, 0f, 10.0f, 0.0f, 10.0f, 1.2f, 500,
                new int[]{ItemList.heart}, 10, 74, Materials.MATERIAL_MEAT_TOUGH);

        builder.skill(SkillList.BODY_STRENGTH, 50.0f);
        builder.skill(SkillList.BODY_STAMINA, 70.0f);
        builder.skill(SkillList.BODY_CONTROL, 30.0f);
        builder.skill(SkillList.MIND_LOGICAL, 30.0f);
        builder.skill(SkillList.MIND_SPEED, 30.0f);
        builder.skill(SkillList.SOUL_STRENGTH, 30.0f);
        builder.skill(SkillList.SOUL_DEPTH, 30.0f);
        builder.skill(SkillList.WEAPONLESS_FIGHTING, 60.0f);
        builder.skill(SkillList.GROUP_FIGHTING, 30.0f);

        builder.boundsValues(-0.5f, -1.0f, 0.5f, 1.42f);
        builder.handDamString("Maul");
        builder.maxAge(100);
        builder.armourType(ArmourTypes.ARMOUR_STUDDED);
        builder.baseCombatRating(20.0f);
        builder.combatDamageType(Wound.TYPE_BURN);
        builder.maxPercentOfCreatures(0.003f);
        builder.maxGroupAttackSize(100);


        templateId = builder.getTemplateId();
        return builder;
    }
}
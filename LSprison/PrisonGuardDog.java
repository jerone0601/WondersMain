package org.gotti.wurmunlimited.mods.creatures.LSprison;

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
public class PrisonGuardDog implements ModCreature, CreatureTypes {
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
        CreatureTemplateBuilder builder = new CreatureTemplateBuilder("mod.creature.prisonguarddog", "Prison Guard Dog", "Trained to protect the prison guards",
                "model.creature.quadraped.dog", types, BodyTemplate.TYPE_DOG, (short) 5, (byte) 0, (short) 85, (short) 50, (short) 85,
                "sound.death.dog", "sound.death.dog", "sound.combat.hit.dog", "sound.combat.hit.dog",
                0.8f, 5f, 0f, 8.0f, 0.0f, 0.0f, 1.2f, 500,
                new int[]{ItemList.heart, ItemList.fur, ItemList.pelt}, 10, 74, Materials.MATERIAL_MEAT_CANINE);

        builder.skill(SkillList.BODY_STRENGTH, 30.0f);
        builder.skill(SkillList.BODY_STAMINA, 30.0f);
        builder.skill(SkillList.BODY_CONTROL, 30.0f);
        builder.skill(SkillList.MIND_LOGICAL, 30.0f);
        builder.skill(SkillList.MIND_SPEED, 30.0f);
        builder.skill(SkillList.SOUL_STRENGTH, 30.0f);
        builder.skill(SkillList.SOUL_DEPTH, 30.0f);
        builder.skill(SkillList.WEAPONLESS_FIGHTING, 30.0f);
        builder.skill(SkillList.GROUP_FIGHTING, 30.0f);

        builder.boundsValues(-0.5f, -1.0f, 0.5f, 1.42f);
        builder.handDamString("bite");
        builder.maxAge(100);
        builder.armourType(ArmourTypes.ARMOUR_LEATHER);
        builder.baseCombatRating(9.0f);
        builder.combatDamageType(Wound.TYPE_PIERCE);
        builder.maxPercentOfCreatures(0.003f);
        builder.maxGroupAttackSize(100);

        templateId = builder.getTemplateId();
        return builder;
    }
}

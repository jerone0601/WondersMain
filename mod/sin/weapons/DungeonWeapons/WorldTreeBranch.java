package mod.sin.weapons.DungeonWeapons;

import com.wurmonline.server.MiscConstants;
import com.wurmonline.server.items.*;
import com.wurmonline.server.skills.SkillList;
import mod.sin.weapons.Eviscerator;
import org.gotti.wurmunlimited.modsupport.ItemTemplateBuilder;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created by Jerone on 10/24/2017.
 */
public class WorldTreeBranch implements ItemTypes, MiscConstants {
    public static Logger logger = Logger.getLogger(Eviscerator.class.getName());
    public static int templateId;
    private String name = "World Tree Branch";

    public void createTemplate() throws IOException {
		/*  ItemTemplateCreator.createItemTemplate(337,
		 * "Hammer of Magranon", "hammers of magranon", "excellent", "good", "ok", "poor",
		 * "A huge brutal warhammer made totally from bronze.",
		 * new short[]{52, 48, 69, 37, 14, 40, 71},
		 * 1339, 35, 80, Long.MAX_VALUE, 5, 10, 80, 10070, MiscConstants.EMPTY_BYTE_PRIMITIVE_ARRAY,
		 * "model.artifact.hammerhuge.", 99.0f, 7000, 31, 3000000, false);
		 */
        ItemTemplateBuilder itemBuilder = new ItemTemplateBuilder("mod.item.WorldTreeBranch");
        itemBuilder.name(name, "WorldTreeBranch", "A branch from the corrupted World Tree that could be wielded as a maul.");
        itemBuilder.itemTypes(new short[]{ // new short[]{108, 44, 147, 22, 37, 14, 189} - Large Maul
                ItemTypes.ITEM_TYPE_NAMED,
                ItemTypes.ITEM_TYPE_REPAIRABLE,
                ItemTypes.ITEM_TYPE_WOOD,
                ItemTypes.ITEM_TYPE_WEAPON,
                ItemTypes.ITEM_TYPE_TWOHANDED,
                ItemTypes.ITEM_TYPE_WEAPON_CRUSH
        });
        itemBuilder.imageNumber((short) 1279);
        itemBuilder.behaviourType((short) 35);
        itemBuilder.combatDamage(40);
        itemBuilder.decayTime(Long.MAX_VALUE);
        itemBuilder.dimensions(5, 10, 80);
        itemBuilder.primarySkill(SkillList.MAUL_LARGE);
        itemBuilder.bodySpaces(MiscConstants.EMPTY_BYTE_PRIMITIVE_ARRAY);
        itemBuilder.modelName("model.artifact.sceptreascension.");
        itemBuilder.difficulty(90.0f);
        itemBuilder.weightGrams(10000);
        itemBuilder.material(Materials.MATERIAL_WOOD_OAK);
        itemBuilder.value(1000);

        ItemTemplate template = itemBuilder.build();
        templateId = template.getTemplateId();
        logger.info(name + " TemplateID: " + templateId);
    }
    public void initCreationEntry(){
        logger.info("initCreationEntry()");
        if(templateId > 0){
            logger.info("Creating "+name+" creation entry, ID = "+templateId);
            CreationEntryCreator.createSimpleEntry(SkillList.CARPENTRY_FINE, AegirClub.templateId, FrostiPuppet.templateId,
                    templateId, true, true, 0.0f, false, false, CreationCategories.WEAPONS);
            //final AdvancedCreationEntry entry = CreationEntryCreator.createAdvancedEntry(SkillList.SMITHING_WEAPON_HEADS,
            //		ItemList.ironBand, ItemList.shaft, templateId, false, false, 0f, true, false, CreationCategories.TOOLS);
            //entry.addRequirement(new CreationRequirement(1, ItemList.woodenHandleSword, 2, true));
            //entry.addRequirement(new CreationRequirement(2, ItemList.nailsIronSmall, 1, true));
        }else{
            logger.info(name+" does not have a template ID on creation entry.");
        }
    }
}
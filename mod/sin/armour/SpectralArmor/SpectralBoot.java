package mod.sin.armour.SpectralArmor;

import java.io.IOException;
import java.util.logging.Logger;

import org.gotti.wurmunlimited.modsupport.ItemTemplateBuilder;

import com.wurmonline.server.MiscConstants;
import com.wurmonline.server.combat.ArmourTypes;
import com.wurmonline.server.items.CreationCategories;
import com.wurmonline.server.items.CreationEntryCreator;
import com.wurmonline.server.items.ItemList;
import com.wurmonline.server.items.ItemTemplate;
import com.wurmonline.server.items.ItemTypes;
import com.wurmonline.server.items.Materials;
import com.wurmonline.server.skills.SkillList;

public class SpectralBoot implements ItemTypes, MiscConstants {
	public static Logger logger = Logger.getLogger(SpectralBoot.class.getName());
	public static int templateId;
	private String name = "spectral boot";
	public void createTemplate() throws IOException{
		ItemTemplateBuilder itemBuilder = new ItemTemplateBuilder("mod.item.spectral.boot");
		itemBuilder.name(name, "spectral boots", "A spectral boot.");
		itemBuilder.itemTypes(new short[]{ // {108, 44, 23, 4, 99} - Drake hide jacket
				ItemTypes.ITEM_TYPE_NAMED,
				ItemTypes.ITEM_TYPE_REPAIRABLE,
				ItemTypes.ITEM_TYPE_LEATHER,
				ItemTypes.ITEM_TYPE_ARMOUR,
				ItemTypes.ITEM_TYPE_DRAGONARMOUR
		});
		itemBuilder.imageNumber((short) 1065);
		itemBuilder.behaviourType((short) 1);
		itemBuilder.combatDamage(0);
		itemBuilder.decayTime(Long.MAX_VALUE);
		itemBuilder.dimensions(2, 40, 40);
		itemBuilder.primarySkill(-10);
		itemBuilder.bodySpaces(new byte[]{15, 16});
		itemBuilder.modelName("model.armour.foot.dragon.");
		itemBuilder.difficulty(70.0f);
		itemBuilder.weightGrams(300);
		itemBuilder.material(Materials.MATERIAL_LEATHER);
		itemBuilder.value(1000000);
		itemBuilder.armourType(ArmourTypes.ARMOUR_LEATHER_DRAGON);
		
		ItemTemplate template = itemBuilder.build();
		templateId = template.getTemplateId();
		logger.info(name+" TemplateID: "+templateId);
	}
	
	public void initCreationEntry(){
		logger.info("initCreationEntry()");
		if(templateId > 0){
			logger.info("Creating "+name+" creation entry, ID = "+templateId);
			CreationEntryCreator.createSimpleEntry(SkillList.LEATHERWORKING, ItemList.needleIron, SpectralHide.templateId,
					templateId, false, true, 0.0f, false, false, CreationCategories.ARMOUR);
			CreationEntryCreator.createSimpleEntry(SkillList.LEATHERWORKING, ItemList.needleCopper, SpectralHide.templateId,
					templateId, false, true, 0.0f, false, false, CreationCategories.ARMOUR);
		}else{
			logger.info(name+" does not have a template ID on creation entry.");
		}
	}
}

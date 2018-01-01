package org.gotti.wurmunlimited.mods.wyvernmods;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

import mod.sin.armour.FafnirsArmor.*;
import mod.sin.armour.GlimmerScale.*;
import mod.sin.armour.JotDungeonArmor.*;
import mod.sin.armour.PrisonArmor.*;
import mod.sin.armour.SpectralArmor.*;
import mod.sin.weapons.DungeonWeapons.*;
import mod.sin.weapons.DungeonWeapons.fafnirsWeapons.*;
import org.gotti.wurmunlimited.modloader.ReflectionUtil;
import org.gotti.wurmunlimited.mods.items.*;
import org.gotti.wurmunlimited.mods.items.actions.*;
import org.gotti.wurmunlimited.modsupport.actions.ModActions;

import com.wurmonline.server.combat.Armour;
import com.wurmonline.server.combat.Weapon;
import com.wurmonline.server.items.CreationCategories;
import com.wurmonline.server.items.CreationEntryCreator;
import com.wurmonline.server.items.Item;
import com.wurmonline.server.items.ItemList;
import com.wurmonline.server.items.ItemTemplate;
import com.wurmonline.server.items.ItemTemplateFactory;
import com.wurmonline.server.items.NoSuchTemplateException;
import com.wurmonline.server.skills.SkillList;

import mod.sin.items.*;
import mod.sin.items.actions.*;
import mod.sin.weapons.*;
import mod.sin.weapons.heads.*;
import mod.sin.weapons.titan.*;

public class ItemMod {
	public static Logger logger = Logger.getLogger(ItemMod.class.getName());
	public static AffinityOrb AFFINITY_ORB = new AffinityOrb();
	//public static ArenaCache ARENA_CACHE = new ArenaCache();
	//public static ArenaSupplyDepot ARENA_SUPPLY_DEPOT = new ArenaSupplyDepot();
	public static ArrowPackHunting ARROW_PACK_HUNTING = new ArrowPackHunting();
	public static ArrowPackWar ARROW_PACK_WAR = new ArrowPackWar();
	public static Club CLUB = new Club();
	public static CoinDecoration COIN_DECORATION = new CoinDecoration();
	public static CorpseDecoration CORPSE_DECORATION = new CorpseDecoration();
	//public static DepthDrill DEPTH_DRILL = new DepthDrill();
	//public static DisintegrationRod DISINTEGRATION_ROD = new DisintegrationRod();
	//public static EnchantOrb ENCHANT_ORB = new EnchantOrb();
	public static Eviscerator EVISCERATOR = new Eviscerator();
	public static AegirClub AegirsClub = new AegirClub();
	public static mod.sin.weapons.DungeonWeapons.RansDagger RansDagger = new RansDagger();
	//public static FriyanTablet FRIYAN_TABLET = new FriyanTablet();
	//public static HugeCrate HUGE_CRATE = new HugeCrate();
	public static Knuckles KNUCKLES = new Knuckles();
	//public static MassStorageUnit MASS_STORAGE_UNIT = new MassStorageUnit();
	public static SkeletonDecoration SKELETON_DECORATION = new SkeletonDecoration();
	//public static StatuetteBreyk STATUETTE_BREYK = new StatuetteBreyk();
	//public static StatuetteCyberhusky STATUETTE_CYBERHUSKY = new StatuetteCyberhusky();
	//public static TreasureBox TREASURE_BOX = new TreasureBox();
	public static Warhammer WARHAMMER = new Warhammer();
	public static CurvedDaggerBlade CurvedDaggerBlade = new CurvedDaggerBlade();
	public static CurvedDagger CurvedDagger = new CurvedDagger();
	public static mod.sin.weapons.DungeonWeapons.NarvisHalberd NarvisHalberd = new NarvisHalberd();
	public static AndvarisWarhammer AndvarisWarhammer = new AndvarisWarhammer();
	public static WarhammerHead WARHAMMER_HEAD = new WarhammerHead();
	public static mod.sin.weapons.DungeonWeapons.KarisStaff KarisStaff = new KarisStaff();
	public static mod.sin.weapons.DungeonWeapons.FrostiPuppet FrostiPuppet = new FrostiPuppet();
	public static mod.sin.weapons.DungeonWeapons.JokulBattleYoYo JokulBattleYoYo = new JokulBattleYoYo();
	public static Andvaranaut Andvaranaut = new Andvaranaut();
	public static FafnirsBackSpike FafnirsBackSpike = new FafnirsBackSpike();
	public static FafnirsNail FafnirsNail = new FafnirsNail();
	public static FafnirsTail FafnirsTail = new FafnirsTail();
	public static FafnirsTooth FafnirsTooth = new FafnirsTooth();
	public static WorldTreeBranch WorldTreeBranch = new WorldTreeBranch();
	public static HelasSpear HelasSpear = new HelasSpear();

	
	// Crystals
	//public static ChaosCrystal CHAOS_CRYSTAL = new ChaosCrystal();
	//public static EnchantersCrystal ENCHANTERS_CRYSTAL = new EnchantersCrystal();
	
	// Titan weaponry
	public static MaartensMight MAARTENS_MIGHT = new MaartensMight();
	public static RaffehsRage RAFFEHS_RAGE = new RaffehsRage();
	public static VindictivesVengeance VINDICTIVES_VENGEANCE = new VindictivesVengeance();
	public static WilhelmsWrath WILHELMS_WRATH = new WilhelmsWrath();

	// Spectral set
	public static SpectralHide SPECTRAL_HIDE = new SpectralHide();
	public static SpectralBoot SPECTRAL_BOOT = new SpectralBoot();
	public static SpectralCap SPECTRAL_CAP = new SpectralCap();
	public static SpectralGlove SPECTRAL_GLOVE = new SpectralGlove();
	public static SpectralHose SPECTRAL_HOSE = new SpectralHose();
	public static SpectralJacket SPECTRAL_JACKET = new SpectralJacket();
	public static SpectralSleeve SPECTRAL_SLEEVE = new SpectralSleeve();

	// Glimmerscale set
	public static Glimmerscale GLIMMERSCALE = new Glimmerscale();
	public static GlimmerscaleBoot GLIMMERSCALE_BOOT = new GlimmerscaleBoot();
	public static GlimmerscaleGlove GLIMMERSCALE_GLOVE = new GlimmerscaleGlove();
	public static GlimmerscaleHelmet GLIMMERSCALE_HELMET = new GlimmerscaleHelmet();
	public static GlimmerscaleHose GLIMMERSCALE_HOSE = new GlimmerscaleHose();
	public static GlimmerscaleSleeve GLIMMERSCALE_SLEEVE = new GlimmerscaleSleeve();
	public static GlimmerscaleVest GLIMMERSCALE_VEST = new GlimmerscaleVest();

	//Fafnirs Armor
	public static FafnirScale FafnirScale = new FafnirScale();
	public static FafnirsBoot FafnirsBoot = new FafnirsBoot();
	public static FafnirsGlove FafnirsGlove = new FafnirsGlove();
	public static FafnirsHose FafnirsHose = new FafnirsHose();
	public static FafnirsSleeve FafnirsSleeve = new FafnirsSleeve();
	public static FafnirsVest FafnirsVest = new FafnirsVest();

	//Prison Guard Ring Set
	public static mod.sin.armour.PrisonArmor.PrisonGuardBoot PrisonGuardBoot = new PrisonGuardBoot();
	public static mod.sin.armour.PrisonArmor.PrisonGuardGlove PrisonGuardGlove = new PrisonGuardGlove();
	public static mod.sin.armour.PrisonArmor.PrisonGuardCoif PrisonGuardCoif = new PrisonGuardCoif();
	public static mod.sin.armour.PrisonArmor.PrisonGuardHose PrisonGuardHose = new PrisonGuardHose();
	public static mod.sin.armour.PrisonArmor.PrisonGuardSleeve PrisonGuardSleeve = new PrisonGuardSleeve();
	public static PrisonGuardVest PrisonGuardVest = new PrisonGuardVest();

	//Giant's Fire Set
	public static mod.sin.armour.JotDungeonArmor.GiantFireBoot GiantFireBoot = new GiantFireBoot();
	public static mod.sin.armour.JotDungeonArmor.GiantFireGlove GiantFireGlove = new GiantFireGlove();
	public static mod.sin.armour.JotDungeonArmor.GiantFireCoif GiantFireCoif = new GiantFireCoif();
	public static mod.sin.armour.JotDungeonArmor.GiantFireHose GiantFireHose = new GiantFireHose();
	public static GiantFireSleeve GiantFireSleeve = new GiantFireSleeve();
	public static mod.sin.armour.JotDungeonArmor.GiantFireJacket GiantFireJacket = new GiantFireJacket();

	public static void createItems(){
		logger.info("createItems()");
		try{
			AFFINITY_ORB.createTemplate();
			//ARENA_CACHE.createTemplate();
			//ARENA_SUPPLY_DEPOT.createTemplate();
			ARROW_PACK_HUNTING.createTemplate();
			ARROW_PACK_WAR.createTemplate();
			CLUB.createTemplate();
			COIN_DECORATION.createTemplate();
			CORPSE_DECORATION.createTemplate();
			//DEPTH_DRILL.createTemplate();
			//DISINTEGRATION_ROD.createTemplate();
			//ENCHANT_ORB.createTemplate();
			EVISCERATOR.createTemplate();
			RansDagger.createTemplate();
			AegirsClub.createTemplate();
			//FRIYAN_TABLET.createTemplate();
			//HUGE_CRATE.createTemplate();
			KNUCKLES.createTemplate();
			//MASS_STORAGE_UNIT.createTemplate();
			SKELETON_DECORATION.createTemplate();
			//SOUL.createTemplate();
			//STATUETTE_BREYK.createTemplate();
			//STATUETTE_CYBERHUSKY.createTemplate();
			//TREASURE_BOX.createTemplate();
			WARHAMMER.createTemplate();
			WARHAMMER_HEAD.createTemplate();
			NarvisHalberd.createTemplate();
			AndvarisWarhammer.createTemplate();
			KarisStaff.createTemplate();
			FrostiPuppet.createTemplate();
			JokulBattleYoYo.createTemplate();
			FafnirsTooth.createTemplate();
			FafnirsTail.createTemplate();
			FafnirsNail.createTemplate();
			Andvaranaut.createTemplate();
			FafnirsBackSpike.createTemplate();
			CurvedDaggerBlade.createTemplate();
			CurvedDagger.createTemplate();
			WorldTreeBranch.createTemplate();
			HelasSpear.createTemplate();
			
			// Crystals
			//CHAOS_CRYSTAL.createTemplate();
			//ENCHANTERS_CRYSTAL.createTemplate();
			
			// Titan weaponry
			//MAARTENS_MIGHT.createTemplate();
			//RAFFEHS_RAGE.createTemplate();
			//VINDICTIVES_VENGEANCE.createTemplate();
			//WILHELMS_WRATH.createTemplate();

			// Spectral set
			SPECTRAL_HIDE.createTemplate();
			SPECTRAL_BOOT.createTemplate();
			SPECTRAL_CAP.createTemplate();
			SPECTRAL_GLOVE.createTemplate();
			SPECTRAL_HOSE.createTemplate();
			SPECTRAL_JACKET.createTemplate();
			SPECTRAL_SLEEVE.createTemplate();
			
			// Glimmerscale set
			GLIMMERSCALE.createTemplate();
			GLIMMERSCALE_BOOT.createTemplate();
			GLIMMERSCALE_GLOVE.createTemplate();
			GLIMMERSCALE_HELMET.createTemplate();
			GLIMMERSCALE_HOSE.createTemplate();
			GLIMMERSCALE_SLEEVE.createTemplate();
			GLIMMERSCALE_VEST.createTemplate();

			//Fafnir's Set
			FafnirScale.createTemplate();
			FafnirsBoot.createTemplate();
			FafnirsGlove.createTemplate();
			FafnirsHose.createTemplate();
			FafnirsSleeve.createTemplate();
			FafnirsVest.createTemplate();

			//Prison Guard's Set
			PrisonGuardBoot.createTemplate();
			PrisonGuardGlove.createTemplate();
			PrisonGuardCoif.createTemplate();
			PrisonGuardHose.createTemplate();
			PrisonGuardSleeve.createTemplate();
			PrisonGuardVest.createTemplate();

			//Giant's Fire Set
			GiantFireBoot.createTemplate();
			GiantFireGlove.createTemplate();
			GiantFireCoif.createTemplate();
			GiantFireHose.createTemplate();
			GiantFireSleeve.createTemplate();
			GiantFireJacket.createTemplate();


		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public static void registerActions(){
		ModActions.registerAction(new AffinityOrbAction());
		//ModActions.registerAction(new ArenaCacheOpenAction());
		//ModActions.registerAction(new ArenaSupplyDepotAction());
		ModActions.registerAction(new ArrowPackUnpackAction());
		//ModActions.registerAction(new CrystalCombineAction());
		//ModActions.registerAction(new ChaosCrystalInfuseAction());
		//ModActions.registerAction(new DepthDrillAction());
		//ModActions.registerAction(new DisintegrationRodAction());
		//ModActions.registerAction(new EnchantersCrystalInfuseAction());
		//ModActions.registerAction(new EnchantOrbAction());
		//ModActions.registerAction(new FriyanTabletAction());
		//ModActions.registerAction(new TreasureBoxAction());
	}
	
	public static void initCreationEntries(){
		logger.info("initCreationEntries()");
		ARROW_PACK_HUNTING.initCreationEntry();
		ARROW_PACK_WAR.initCreationEntry();
		CLUB.initCreationEntry();
		COIN_DECORATION.initCreationEntry();
		CORPSE_DECORATION.initCreationEntry();
		//DEPTH_DRILL.initCreationEntry();
		EVISCERATOR.initCreationEntry();
		RansDagger.initCreationEntry();
		AegirsClub.initCreationEntry();
		KNUCKLES.initCreationEntry();
		//MASS_STORAGE_UNIT.initCreationEntry();
		SKELETON_DECORATION.initCreationEntry();
		//STATUETTE_BREYK.initCreationEntry();
		//STATUETTE_CYBERHUSKY.initCreationEntry();
		WARHAMMER.initCreationEntry();
		WARHAMMER_HEAD.initCreationEntry();
		NarvisHalberd.initCreationEntry();
		KarisStaff.initCreationEntry();
		FrostiPuppet.initCreationEntry();
		JokulBattleYoYo.initCreationEntry();
		FafnirsBackSpike.initCreationEntry();
		FafnirsTooth.initCreationEntry();
		FafnirsTail.initCreationEntry();
		FafnirsNail.initCreationEntry();
		Andvaranaut.initCreationEntry();
		CurvedDagger.initCreationEntry();
		CurvedDaggerBlade.initCreationEntry();
		WorldTreeBranch.initCreationEntry();
		HelasSpear.initCreationEntry();


		// Spectral set
		SPECTRAL_BOOT.initCreationEntry();
		SPECTRAL_CAP.initCreationEntry();
		SPECTRAL_GLOVE.initCreationEntry();
		SPECTRAL_HOSE.initCreationEntry();
		SPECTRAL_JACKET.initCreationEntry();
		SPECTRAL_SLEEVE.initCreationEntry();
		
		// Glimmerscale set
		GLIMMERSCALE.initCreationEntry();
		GLIMMERSCALE_BOOT.initCreationEntry();
		GLIMMERSCALE_GLOVE.initCreationEntry();
		GLIMMERSCALE_HELMET.initCreationEntry();
		GLIMMERSCALE_HOSE.initCreationEntry();
		GLIMMERSCALE_SLEEVE.initCreationEntry();
		GLIMMERSCALE_VEST.initCreationEntry();

		// Fafnir's set
		FafnirsBoot.initCreationEntry();
		FafnirsGlove.initCreationEntry();
		FafnirsHose.initCreationEntry();
		FafnirsSleeve.initCreationEntry();
		FafnirsVest.initCreationEntry();

		//Prison Guard Set
		PrisonGuardVest.initCreationEntry();
		PrisonGuardBoot.initCreationEntry();
		PrisonGuardGlove.initCreationEntry();
		PrisonGuardSleeve.initCreationEntry();
		PrisonGuardHose.initCreationEntry();
		PrisonGuardCoif.initCreationEntry();

		//Giant's Fire Set
		GiantFireJacket.initCreationEntry();
		GiantFireBoot.initCreationEntry();
		GiantFireGlove.initCreationEntry();
		GiantFireSleeve.initCreationEntry();
		GiantFireHose.initCreationEntry();
		GiantFireCoif.initCreationEntry();
		
		// Allow sickle heads from steel & moon metals:
		CreationEntryCreator.createSimpleEntry(SkillList.SMITHING_WEAPON_BLADES, ItemList.anvilLarge, ItemList.steelBar,
				ItemList.sickleBlade, false, true, 0.0f, false, false, CreationCategories.BLADES);
		CreationEntryCreator.createSimpleEntry(SkillList.SMITHING_WEAPON_BLADES, ItemList.anvilLarge, ItemList.adamantineBar,
				ItemList.sickleBlade, false, true, 0.0f, false, false, CreationCategories.BLADES);
		CreationEntryCreator.createSimpleEntry(SkillList.SMITHING_WEAPON_BLADES, ItemList.anvilLarge, ItemList.glimmerSteelBar,
				ItemList.sickleBlade, false, true, 0.0f, false, false, CreationCategories.BLADES);
		CreationEntryCreator.createSimpleEntry(SkillList.SMITHING_WEAPON_BLADES, ItemList.anvilLarge, ItemList.seryllBar,
				ItemList.sickleBlade, false, true, 0.0f, false, false, CreationCategories.BLADES);
		// Allow steel staff to be created from moon metals:
		CreationEntryCreator.createSimpleEntry(SkillList.SMITHING_WEAPON_BLADES, ItemList.anvilLarge, ItemList.adamantineBar,
				ItemList.staffSteel, false, true, 0.0f, false, false, CreationCategories.BLADES);
		CreationEntryCreator.createSimpleEntry(SkillList.SMITHING_WEAPON_BLADES, ItemList.anvilLarge, ItemList.glimmerSteelBar,
				ItemList.staffSteel, false, true, 0.0f, false, false, CreationCategories.BLADES);
		CreationEntryCreator.createSimpleEntry(SkillList.SMITHING_WEAPON_BLADES, ItemList.anvilLarge, ItemList.seryllBar,
				ItemList.staffSteel, false, true, 0.0f, false, false, CreationCategories.BLADES);
		// Allow butcher knife heads from steel & moon metals:
		CreationEntryCreator.createSimpleEntry(SkillList.SMITHING_WEAPON_BLADES, ItemList.anvilLarge, ItemList.steelBar,
				ItemList.knifeBladeButchering, false, true, 0.0f, false, false, CreationCategories.BLADES);
		CreationEntryCreator.createSimpleEntry(SkillList.SMITHING_WEAPON_BLADES, ItemList.anvilLarge, ItemList.adamantineBar,
				ItemList.knifeBladeButchering, false, true, 0.0f, false, false, CreationCategories.BLADES);
		CreationEntryCreator.createSimpleEntry(SkillList.SMITHING_WEAPON_BLADES, ItemList.anvilLarge, ItemList.glimmerSteelBar,
				ItemList.knifeBladeButchering, false, true, 0.0f, false, false, CreationCategories.BLADES);
		CreationEntryCreator.createSimpleEntry(SkillList.SMITHING_WEAPON_BLADES, ItemList.anvilLarge, ItemList.seryllBar,
				ItemList.knifeBladeButchering, false, true, 0.0f, false, false, CreationCategories.BLADES);

//sand/dirt/sandstone/plank creation
		CreationEntryCreator.createSimpleEntry(SkillList.BODY_STRENGTH, ItemList.maulLarge, ItemList.sandstone,
				ItemList.sand, false, true, 0.0f, false, false, CreationCategories.RESOURCES);
		CreationEntryCreator.createSimpleEntry(SkillList.BODY_STRENGTH, ItemList.maulLarge, ItemList.rock,
				ItemList.dirtPile, false, true, 0.0f, false, false, CreationCategories.RESOURCES);
		CreationEntryCreator.createSimpleEntry(SkillList.BODY_CONTROL, ItemList.bodyHand, ItemList.sand,
				ItemList.sandstone, false, true, 0.0f, false, false, CreationCategories.RESOURCES);
		CreationEntryCreator.createSimpleEntry(SkillList.BODY_CONTROL, ItemList.hammerWood, ItemList.scrapwood,
				ItemList.plank, false, true, 0.0f, false, false, CreationCategories.RESOURCES);

	}
	
	public static void createCustomArmours(){
		try {
			logger.info("Beginning custom armour creation.");
			Map<Integer, Armour> armours = ReflectionUtil.getPrivateField(null, ReflectionUtil.getField(Armour.class, "armours"));

			armours.put(SpectralBoot.templateId, new Armour(SpectralBoot.templateId, 0.002f, 0.3f));
			armours.put(SpectralCap.templateId, new Armour(SpectralCap.templateId, 0.003f, 0.3f));
			armours.put(SpectralGlove.templateId, new Armour(SpectralGlove.templateId, 0.002f, 0.3f));
			armours.put(SpectralHose.templateId, new Armour(SpectralHose.templateId, 0.0075f, 0.3f));
			armours.put(SpectralJacket.templateId, new Armour(SpectralJacket.templateId, 0.01f, 0.3f));
			armours.put(SpectralSleeve.templateId, new Armour(SpectralSleeve.templateId, 0.004f, 0.3f));

			armours.put(GlimmerscaleBoot.templateId, new Armour(GlimmerscaleBoot.templateId, 0.002f, 0.15f));
			armours.put(GlimmerscaleGlove.templateId, new Armour(GlimmerscaleGlove.templateId, 0.001f, 0.15f));
			armours.put(GlimmerscaleHelmet.templateId, new Armour(GlimmerscaleHelmet.templateId, 0.008f, 0.15f));
			armours.put(GlimmerscaleHose.templateId, new Armour(GlimmerscaleHose.templateId, 0.05f, 0.15f));
			armours.put(GlimmerscaleSleeve.templateId, new Armour(GlimmerscaleSleeve.templateId, 0.008f, 0.15f));
			armours.put(GlimmerscaleVest.templateId, new Armour(GlimmerscaleVest.templateId, 0.05f, 0.15f));

			armours.put(FafnirsBoot.templateId, new Armour(FafnirsBoot.templateId, 0.002f, 0.15f));
			armours.put(FafnirsGlove.templateId, new Armour(FafnirsGlove.templateId, 0.001f, 0.15f));
			armours.put(FafnirsHose.templateId, new Armour(FafnirsHose.templateId, 0.05f, 0.15f));
			armours.put(FafnirsSleeve.templateId, new Armour(FafnirsSleeve.templateId, 0.008f, 0.15f));
			armours.put(FafnirsVest.templateId, new Armour(FafnirsVest.templateId, 0.05f, 0.15f));

			armours.put(PrisonGuardBoot.templateId, new Armour(PrisonGuardBoot.templateId, 0.002f, 0.3f));
			armours.put(PrisonGuardCoif.templateId, new Armour(PrisonGuardCoif.templateId, 0.003f, 0.3f));
			armours.put(PrisonGuardGlove.templateId, new Armour(PrisonGuardGlove.templateId, 0.002f, 0.3f));
			armours.put(PrisonGuardHose.templateId, new Armour(PrisonGuardHose.templateId, 0.0075f, 0.3f));
			armours.put(PrisonGuardVest.templateId, new Armour(PrisonGuardVest.templateId, 0.01f, 0.3f));
			armours.put(PrisonGuardSleeve.templateId, new Armour(PrisonGuardSleeve.templateId, 0.004f, 0.3f));

			armours.put(GiantFireBoot.templateId, new Armour(GiantFireBoot.templateId, 0.002f, 0.3f));
			armours.put(GiantFireCoif.templateId, new Armour(GiantFireCoif.templateId, 0.003f, 0.3f));
			armours.put(GiantFireGlove.templateId, new Armour(GiantFireGlove.templateId, 0.002f, 0.3f));
			armours.put(GiantFireHose.templateId, new Armour(GiantFireHose.templateId, 0.0075f, 0.3f));
			armours.put(GiantFireJacket.templateId, new Armour(GiantFireJacket.templateId, 0.01f, 0.3f));
			armours.put(GiantFireSleeve.templateId, new Armour(GiantFireSleeve.templateId, 0.004f, 0.3f));
			//ReflectionUtil.setPrivateField(null, ReflectionUtil.getField(Armour.class, "armours"), armours);
		} catch (IllegalArgumentException | IllegalAccessException | ClassCastException | NoSuchFieldException e) {
			e.printStackTrace();
		}
	}
	
	public static void createCustomWeapons(){
		try {
			logger.info("Beginning custom weapon creation.");
			new Weapon(Warhammer.templateId, 5.5f, 2.75f, 0.208f, 4, 3, 1f, 0d);
			new Weapon(Knuckles.templateId, 5.50f, 2.75f, 0.202f, 1, 1, 0.2f, 0.5d);
			new Weapon(CurvedDagger.templateId, 5.50f, 2.75f, 0.202f, 1, 1, 0.2f, 0.5d);
			new Weapon(Club.templateId, 5.50f, 2.75f, 0.202f, 1, 1, 0.2f, 0.5d);
			// Titan weaponry
			//new Weapon(MaartensMight.templateId, 11, 5, 0.02f, 4, 4, 1.0f, 0d);
			//new Weapon(RaffehsRage.templateId, 9.5f, 4.25f, 0.02f, 3, 3, 1.0f, 0d);
			//new Weapon(VindictivesVengeance.templateId, 9, 4f, 0.02f, 3, 3, 0.5f, 0d);
			//new Weapon(WilhelmsWrath.templateId, 6f, 4.5f, 0.02f, 6, 6, 0.5f, 0d);
			// Genocide weapon
			new Weapon(Eviscerator.templateId, 8, 2.7f, 0.32f, 3, 1, 0.4f, 0.0d);
			new Weapon(RansDagger.templateId, 8, 2.7f, 0.22f, 3, 1, 0.7f, 0.0d);
			new Weapon(AegirsClub.templateId, 8, 2.7f, 0.22f, 4, 4, 0.1f, 0.0d);
			new Weapon(NarvisHalberd.templateId, 16, 5.5f, 0.52f, 5, 5, 0.2f, 0.0d);
			new Weapon(AndvarisWarhammer.templateId, 8, 2.7f, 0.22f, 3, 5, 0.01f, 0.0d);
			new Weapon(FrostiPuppet.templateId, 8, 2.7f, 0.22f, 1, 1, 0.0f, 0.0d);
			new Weapon(JokulBattleYoYo.templateId, 16, 5.5f, 0.52f, 15, 1, 0.0f, 0.0d);
			new Weapon(KarisStaff.templateId, 16, 5.5f, 0.52f, 4, 2, 0.8f, 0.0d);
			new Weapon(FafnirsNail.templateId, 16, 5.5f, 0.52f, 4, 2, 0.4f, 0.0d);
			new Weapon(Andvaranaut.templateId, 8, 2.7f, 0.22f, 2, 1, 0.3f, 0.0d);
			new Weapon(FafnirsTooth.templateId, 8, 2.7f, 0.22f, 4, 2, 0.6f, 0.0d);
			new Weapon(FafnirsBackSpike.templateId, 8, 2.7f, 0.22f, 4, 1, 0.3f, 0.0d);
			new Weapon(FafnirsTail.templateId, 16, 5.5f, 0.22f, 8, 5, 0.3f, 0.0d);
			new Weapon(WorldTreeBranch.templateId, 16, 5.5f, 0.52f, 6, 2, 0.8f, 0.0d);
			new Weapon(HelasSpear.templateId, 8, 2.7f, 0.22f, 6, 2, 0.3f, 0.0d);




		} catch (IllegalArgumentException | ClassCastException e) {
			e.printStackTrace();
		}
	}
	
	public static int getModdedImproveTemplateId(Item item){
		if(item.getTemplateId() == SpectralBoot.templateId){
			return SpectralHide.templateId;
		}else if(item.getTemplateId() == SpectralCap.templateId){
			return SpectralHide.templateId;
		}else if(item.getTemplateId() == SpectralGlove.templateId){
			return SpectralHide.templateId;
		}else if(item.getTemplateId() == SpectralHose.templateId){
			return SpectralHide.templateId;
		}else if(item.getTemplateId() == SpectralJacket.templateId){
			return SpectralHide.templateId;
		}else if(item.getTemplateId() == SpectralSleeve.templateId){
			return SpectralHide.templateId;
		}else if(item.getTemplateId() == GlimmerscaleBoot.templateId){
			return Glimmerscale.templateId;
		}else if(item.getTemplateId() == GlimmerscaleGlove.templateId){
			return Glimmerscale.templateId;
		}else if(item.getTemplateId() == GlimmerscaleHelmet.templateId){
			return Glimmerscale.templateId;
		}else if(item.getTemplateId() == GlimmerscaleHose.templateId){
			return Glimmerscale.templateId;
		}else if(item.getTemplateId() == GlimmerscaleSleeve.templateId){
			return Glimmerscale.templateId;
		}else if(item.getTemplateId() == GlimmerscaleVest.templateId){
			return Glimmerscale.templateId;
		}
		return -10;
	}
	
	public static void modifyItems() throws NoSuchTemplateException, IllegalArgumentException, IllegalAccessException, ClassCastException, NoSuchFieldException{
		// Make leather able to be combined.
		ItemTemplate leather = ItemTemplateFactory.getInstance().getTemplate(ItemList.leather);
		ReflectionUtil.setPrivateField(leather, ReflectionUtil.getField(leather.getClass(), "combine"), true);

		// Set silver mirror price to 10 silver instead of 1 iron.
		ItemTemplate handMirror = ItemTemplateFactory.getInstance().getTemplate(ItemList.handMirror);
		ReflectionUtil.setPrivateField(handMirror, ReflectionUtil.getField(handMirror.getClass(), "value"), 100000);

		// Set transmutation rod to 2 gold instead of 50 silver.
		//ItemTemplate transmutationRod = ItemTemplateFactory.getInstance().getTemplate(668);
		//ReflectionUtil.setPrivateField(transmutationRod, ReflectionUtil.getField(transmutationRod.getClass(), "value"), 2000000);
		
		// Make logs able to be combined.
		ItemTemplate log = ItemTemplateFactory.getInstance().getTemplate(ItemList.log);
		ReflectionUtil.setPrivateField(log, ReflectionUtil.getField(log.getClass(), "combine"), true);
		
		// Make dirt/sand difficulty easier
        ItemTemplate dirt = ItemTemplateFactory.getInstance().getTemplate(ItemList.dirtPile);
        ReflectionUtil.setPrivateField(dirt, ReflectionUtil.getField(dirt.getClass(), "difficulty"), 10.0f);

        ItemTemplate sand = ItemTemplateFactory.getInstance().getTemplate(ItemList.sand);
        ReflectionUtil.setPrivateField(sand, ReflectionUtil.getField(sand.getClass(), "difficulty"), 10.0f);

        ItemTemplate sandstone = ItemTemplateFactory.getInstance().getTemplate(ItemList.sandstone);
		ReflectionUtil.setPrivateField(sandstone, ReflectionUtil.getField(sandstone.getClass(), "difficulty"), 10.0f);


		// Make long spears one-handed.
		ItemTemplate longSpear = ItemTemplateFactory.getInstance().getTemplate(ItemList.spearLong);
		ReflectionUtil.setPrivateField(longSpear, ReflectionUtil.getField(longSpear.getClass(), "isTwohanded"), false);
		
		createCustomWeapons();
		createCustomArmours();

		// Make huge crates larger
		//ItemTemplate hugeCrate = ItemTemplateFactory.getInstance().getTemplate(HUGE_CRATE.getTemplateId());
		//ReflectionUtil.setPrivateField(hugeCrate, ReflectionUtil.getField(hugeCrate.getClass(), "combine"), true);
	}

}

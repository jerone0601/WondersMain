package org.gotti.wurmunlimited.mods.wyvernmods;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.gotti.wurmunlimited.modloader.classhooks.HookException;
import org.gotti.wurmunlimited.modloader.classhooks.HookManager;
import org.gotti.wurmunlimited.modloader.interfaces.Configurable;
import org.gotti.wurmunlimited.modloader.interfaces.Initable;
import org.gotti.wurmunlimited.modloader.interfaces.ItemTemplatesCreatedListener;
import org.gotti.wurmunlimited.modloader.interfaces.PreInitable;
import org.gotti.wurmunlimited.modloader.interfaces.ServerStartedListener;
import org.gotti.wurmunlimited.modloader.interfaces.WurmServerMod;
import org.gotti.wurmunlimited.mods.creatures.*;
import org.gotti.wurmunlimited.mods.creatures.AndvarisHall.*;
import org.gotti.wurmunlimited.mods.creatures.DungeonWorldSpawn.*;
import org.gotti.wurmunlimited.mods.creatures.Hlesey.*;
import org.gotti.wurmunlimited.mods.creatures.Jotunheimr.*;
import org.gotti.wurmunlimited.mods.creatures.KarisFortress.*;
import org.gotti.wurmunlimited.mods.creatures.LSprison.*;
import org.gotti.wurmunlimited.mods.creatures.MiniDungeon.ArmyDeserter;
import org.gotti.wurmunlimited.mods.creatures.MiniDungeon.CaptainFrank;
import org.gotti.wurmunlimited.mods.creatures.MiniDungeon.GuardDog;
import org.gotti.wurmunlimited.mods.creatures.MiniDungeon.TrollLord;
import org.gotti.wurmunlimited.mods.creatures.Mounts.*;
import org.gotti.wurmunlimited.mods.creatures.NCP.*;
import org.gotti.wurmunlimited.mods.creatures.PracticeDummies.*;
import org.gotti.wurmunlimited.mods.creatures.RaidMobs.DragonWhelps;
import org.gotti.wurmunlimited.mods.creatures.RaidMobs.DvergrGuard;
import org.gotti.wurmunlimited.mods.creatures.RaidMobs.Fafnir;
import org.gotti.wurmunlimited.mods.creatures.Unique.Facebreyker;
import org.gotti.wurmunlimited.modsupport.actions.ModActions;
import org.gotti.wurmunlimited.modsupport.creatures.ModCreatures;
import org.gotti.wurmunlimited.modsupport.vehicles.ModVehicleBehaviours;

import com.wurmonline.server.items.NoSuchTemplateException;
import com.wurmonline.server.players.Player;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;
import mod.sin.creatures.titans.*;
//import mod.sin.wyvern.AntiCheat;
import mod.sin.wyvern.Mastercraft;
//import mod.sin.wyvern.Soulstealing;
//import mod.sin.wyvernmods.arena.SupplyDepots;
import mod.sin.wyvernmods.bestiary.MethodsBestiary;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.gotti.wurmunlimited.modloader.ReflectionUtil;
import org.gotti.wurmunlimited.modloader.classhooks.HookException;
import org.gotti.wurmunlimited.modloader.classhooks.HookManager;
import org.gotti.wurmunlimited.modloader.interfaces.Configurable;
import org.gotti.wurmunlimited.modloader.interfaces.Initable;
import org.gotti.wurmunlimited.modloader.interfaces.ItemTemplatesCreatedListener;
import org.gotti.wurmunlimited.modloader.interfaces.PreInitable;
import org.gotti.wurmunlimited.modloader.interfaces.ServerPollListener;
import org.gotti.wurmunlimited.modloader.interfaces.ServerStartedListener;
import org.gotti.wurmunlimited.modloader.interfaces.WurmServerMod;
import org.gotti.wurmunlimited.modsupport.actions.ModActions;
import org.gotti.wurmunlimited.modsupport.creatures.ModCreatures;
import org.gotti.wurmunlimited.modsupport.vehicles.ModVehicleBehaviours;

import com.wurmonline.server.TimeConstants;
import com.wurmonline.server.deities.Deities;
import com.wurmonline.server.deities.Deity;
import com.wurmonline.server.items.NoSuchTemplateException;
import com.wurmonline.server.players.Player;
import com.wurmonline.server.skills.SkillList;
import com.wurmonline.server.skills.SkillSystem;
import com.wurmonline.server.skills.SkillTemplate;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;

import mod.sin.creatures.*;
import mod.sin.creatures.titans.*;

public class WyvernMods
implements WurmServerMod, Configurable, PreInitable, Initable, ItemTemplatesCreatedListener, ServerStartedListener {
	private static Logger logger = Logger.getLogger(WyvernMods.class.getName());
	public static boolean espCounter = false;
	public static boolean enableDepots = false;
	
    boolean bDebug = false;
    
    public static boolean customCommandHandler(ByteBuffer byteBuffer, Player player) throws UnsupportedEncodingException{
    	byte[] tempStringArr = new byte[byteBuffer.get() & 255];
        byteBuffer.get(tempStringArr);
        String message = new String(tempStringArr, "UTF-8");
        tempStringArr = new byte[byteBuffer.get() & 255];
        byteBuffer.get(tempStringArr);
        //String title = new String(tempStringArr, "UTF-8");
        if(player.mayMute() && message.startsWith("!")){
    		logger.info("Player "+player.getName()+" used custom WyvernMods command: "+message);
    		if(message.startsWith("!toggleESP") && player.getPower() >= 5){
                espCounter = !espCounter;
                player.getCommunicator().sendSafeServerMessage("ESP counter for this server is now = "+espCounter);
    		}else if(message.startsWith("!enableDepots") && player.getPower() >= 5){
                enableDepots = !enableDepots;
                player.getCommunicator().sendSafeServerMessage("Arena depots for this server is now = "+enableDepots);
    		}else{
    			player.getCommunicator().sendSafeServerMessage("Custom command not found: "+message);
    		}
    		return true;
        }
        return false;
    }

    public void configure(Properties properties) {
        this.bDebug = Boolean.parseBoolean(properties.getProperty("debug", Boolean.toString(this.bDebug)));
        try {
            String logsPath = Paths.get("mods", new String[0]) + "/logs/";
            File newDirectory = new File(logsPath);
            if (!newDirectory.exists()) {
                newDirectory.mkdirs();
            }
            FileHandler fh = new FileHandler(String.valueOf(String.valueOf(logsPath)) + this.getClass().getSimpleName() + ".log", 10240000, 200, true);
            if (this.bDebug) {
                fh.setLevel(Level.INFO);
            } else {
                fh.setLevel(Level.WARNING);
            }
            fh.setFormatter(new SimpleFormatter());
            logger.addHandler(fh);
        }
        catch (IOException ie) {
            System.err.println(String.valueOf(this.getClass().getName()) + ": Unable to add file handler to logger");
        }
        //this.logger.log(Level.INFO, "Property: " + this.somevalue);
        this.Debug("Debugging messages are enabled.");
    }

    private void Debug(String x) {
        if (this.bDebug) {
            System.out.println(String.valueOf(this.getClass().getSimpleName()) + ": " + x);
            System.out.flush();
            logger.log(Level.INFO, x);
        }
    }

    public void preInit() {
    	logger.info("Pre-Initializing.");
        try {
        	ModActions.init();
        	Bounty.preInit(this);
        	//TreasureChests.preInit();
            MiscChanges.preInit();
            Arena.preInit();
           // AntiCheat.preInit();
            Mastercraft.addNewTitles();
            
			ClassPool classPool = HookManager.getInstance().getClassPool();

            // - Enable custom command handler - //
			CtClass ctCommunicator = classPool.get("com.wurmonline.server.creatures.Communicator");
        	ctCommunicator.getDeclaredMethod("reallyHandle").instrument(new ExprEditor(){
                public void edit(MethodCall m) throws CannotCompileException {
                    if (m.getMethodName().equals("reallyHandle_CMD_MESSAGE")) {
                        m.replace("java.nio.ByteBuffer tempBuffer = $1.duplicate();"
                        		+ "if(!org.gotti.wurmunlimited.mods.wyvernmods.WyvernMods.customCommandHandler($1, this.player)){"
                        		+ "  $_ = $proceed(tempBuffer);"
                        		+ "}");
                        return;
                    }
                }
            });
        } catch (CannotCompileException | NotFoundException | IllegalArgumentException | ClassCastException e) {
            throw new HookException((Throwable)e);
        }
    }
    
	@Override
	public void init() {
		logger.info("Initializing.");
		ModCreatures.init();
		ModVehicleBehaviours.init();
		
		// Vanilla:
		logger.info("Registering Vanilla creature changes.");
		ModCreatures.addCreature(new Bison());
		
		// Epic:
		logger.info("Registering Epic creatures.");


		
		// Wyverns:
		ModCreatures.addCreature(new WyvernBlack());
		ModCreatures.addCreature(new WyvernGreen());
		ModCreatures.addCreature(new WyvernRed());
		ModCreatures.addCreature(new WyvernWhite());
		ModCreatures.addCreature(new WyvernBlue());
		ModCreatures.addCreature(new GhostDrake());
		
		// Flavor Mobs:
		ModCreatures.addCreature(new Avenger());
		//ModCreatures.addCreature(new Charger());
		ModCreatures.addCreature(new ForestSpider());
		ModCreatures.addCreature(new Giant());
		ModCreatures.addCreature(new HornedPony());
		ModCreatures.addCreature(new LargeBoar());
		ModCreatures.addCreature(new SpiritTroll());
		ModCreatures.addCreature(new FlyingCarpent());
		ModCreatures.addCreature(new GiantTortoise());
		ModCreatures.addCreature(new Beast());
		//Non-Combat Pets
		ModCreatures.addCreature(new LittleLizard());
		ModCreatures.addCreature(new TinyBear());
		ModCreatures.addCreature(new TinyBlack());
		ModCreatures.addCreature(new TinyBlackWidow());
		ModCreatures.addCreature(new TinyBlue());
		ModCreatures.addCreature(new TinyBlueFiend());
		ModCreatures.addCreature(new TinyCrab());
		ModCreatures.addCreature(new TinyCroc());
		ModCreatures.addCreature(new TinyDiablo());
		ModCreatures.addCreature(new TinyGorilla());
		ModCreatures.addCreature(new TinyGreen());
		ModCreatures.addCreature(new TinyHellScorp());
		ModCreatures.addCreature(new TinyHellHorse());
		ModCreatures.addCreature(new TinyHorse());
		ModCreatures.addCreature(new TinyMountainLion());
		ModCreatures.addCreature(new TinyRed());
		ModCreatures.addCreature(new TinyScorp());
		ModCreatures.addCreature(new TinySkeleton());
		ModCreatures.addCreature(new TinySnake());
		ModCreatures.addCreature(new TinySnowMan());
		ModCreatures.addCreature(new TinySpider());
		ModCreatures.addCreature(new TinyTurtle());
		ModCreatures.addCreature(new TinyUnicorn());
		ModCreatures.addCreature(new TinyWhite());
		ModCreatures.addCreature(new TinyWolf());
		ModCreatures.addCreature(new TinyWorg());
		ModCreatures.addCreature(new TinyWurm());
		ModCreatures.addCreature(new TinyZombie());

		//Training Dummy
		ModCreatures.addCreature(new TrollDummyOne());
		//ModCreatures.addCreature(new TrollDummyTwo());
		//ModCreatures.addCreature(new TrollDummyThree());
		ModCreatures.addCreature(new HatchlingDummy());
		ModCreatures.addCreature(new DragonDummy());


		//Hlesey 70+ FS
		ModCreatures.addCreature(new Aegir());
		ModCreatures.addCreature(new Draugen());
		ModCreatures.addCreature(new DaughterOfAegir());
		ModCreatures.addCreature(new Ran());
		ModCreatures.addCreature(new FimaFeng());
		ModCreatures.addCreature(new ZombieSailor());
		ModCreatures.addCreature(new SeaMonster());

		//Andvari's Hall  70+ FS
		ModCreatures.addCreature(new Andvari());
		ModCreatures.addCreature(new Dvergr());
		ModCreatures.addCreature(new DvergerArtisan());
		ModCreatures.addCreature(new DvergerGoatHerder());
		ModCreatures.addCreature(new DvergerGoat());
		ModCreatures.addCreature(new Narvi());

		//Goblin Citadel 90+ FS
		//ModCreatures.addCreature(new GoblinBerserker());
		//ModCreatures.addCreature(new GoblinBrute());
		//ModCreatures.addCreature(new GoblinGeneral());
		//ModCreatures.addCreature(new GoblinHighShaman());
		//ModCreatures.addCreature(new GoblinKing());
		//ModCreatures.addCreature(new GoblinAssassin());
		//ModCreatures.addCreature(new GoblinShaman());
		//ModCreatures.addCreature(new GoblinMiner());

		//ModCreatures.addCreature(new Ymir());

		//Kari's Fortress 70+ FS
		ModCreatures.addCreature(new Kari());
		ModCreatures.addCreature(new Jokul());
		ModCreatures.addCreature(new Frosti());
		ModCreatures.addCreature(new Reindeer());
		ModCreatures.addCreature(new NorthWindDreki());
		ModCreatures.addCreature(new FrostGiant());


		//World Spawn additions 50+ FS (70+ group in TreasureTroll)
		ModCreatures.addCreature(new DvergerExplorer()); //Andvaris hall world mob
		ModCreatures.addCreature(new Silverback());
		ModCreatures.addCreature(new Gorilla());
		ModCreatures.addCreature(new Hyena());
		//ModCreatures.addCreature(new GoblinScout()); //Goblin Citadel world mob
		ModCreatures.addCreature(new RockCrawler());
		ModCreatures.addCreature(new EscapedPrisoner()); //Prison world mob
		ModCreatures.addCreature(new HleseyCrab()); //Hlesey world mob
		ModCreatures.addCreature(new Jotunn()); //Jotunheimr world mob
		//ModCreatures.addCreature(new TreasureTroll());
        //ModCreatures.addCreature(new Mimic());

		//Mini Dungeons
		ModCreatures.addCreature(new GuardDog());
		ModCreatures.addCreature(new ArmyDeserter());
		ModCreatures.addCreature(new TrollLord());
		ModCreatures.addCreature(new CaptainFrank());

		//Jötunheimr  50+ FS
		ModCreatures.addCreature(new Logi());
		ModCreatures.addCreature(new Glod());
		ModCreatures.addCreature(new Veattir());
		ModCreatures.addCreature(new Risi());
		ModCreatures.addCreature(new DaughterofFire());

		//LifeSpring Prison 25+ FS
		ModCreatures.addCreature(new CorruptGuard());
		ModCreatures.addCreature(new Prisoner());
		ModCreatures.addCreature(new PrisonGuardDog());
		ModCreatures.addCreature(new GuardCaptainFred());
		ModCreatures.addCreature(new DeputyWardenBob());

		//Villager


		// Bosses:
		logger.info("Registering Custom Boss creatures.");
		ModCreatures.addCreature(new Reaper());
		ModCreatures.addCreature(new SpectralDrake());
		//Fafnir and Minions
		ModCreatures.addCreature(new Fafnir());
		ModCreatures.addCreature(new DragonWhelps());
		ModCreatures.addCreature(new DvergrGuard());


		// Uniques:
		ModCreatures.addCreature(new Facebreyker());
		//ModCreatures.addCreature(new King());
		//ModCreatures.addCreature(new LizardKing());


		// Titans:
		ModCreatures.addCreature(new Ifrit());
		ModCreatures.addCreature(new Lilith());
		// Titan Spawns:
		ModCreatures.addCreature(new IfritFiend());
		ModCreatures.addCreature(new IfritSpider());
		ModCreatures.addCreature(new LilithWraith());
		ModCreatures.addCreature(new LilithZombie());
		
		// NPC's
		logger.info("Registering Custom NPC creatures.");
		//ModCreatures.addCreature(new RobZombie());
		//ModCreatures.addCreature(new MacroSlayer());
		
		Bounty.init();
		
		Mastercraft.changeExistingTitles();
	}

	@Override
	public void onItemTemplatesCreated() {
		logger.info("Creating Item Mod items.");
		ItemMod.createItems();
		try {
			logger.info("Editing existing item templates.");
			ItemMod.modifyItems();
			logger.info("Registering permissions hook for custom items.");
			//ItemMod.registerPermissionsHook();
		} catch (NoSuchTemplateException | IllegalArgumentException | IllegalAccessException | ClassCastException | NoSuchFieldException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onServerStarted() {
		try {
			logger.info("Registering Item Mod creation entries.");
			ItemMod.initCreationEntries();
			logger.info("Registering Item Mod actions.");
			ItemMod.registerActions();
			logger.info("Setting custom creature corpse models.");
			MethodsBestiary.setTemplateVariables();

		} catch (IllegalArgumentException | ClassCastException e) {
			e.printStackTrace();
		}
	}

}
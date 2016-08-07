package com.thexfactor117.levels;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.thexfactor117.levels.capabilities.CapabilityEnemyLevel;
import com.thexfactor117.levels.handlers.ConfigHandler;
import com.thexfactor117.levels.init.ModCommands;
import com.thexfactor117.levels.init.ModEvents;
import com.thexfactor117.levels.network.PacketEnemyLevel;
import com.thexfactor117.levels.network.PacketRarity;
import com.thexfactor117.levels.proxies.CommonProxy;
import com.thexfactor117.levels.utils.Reference;

import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

/**
 * 
 * @author TheXFactor117
 * 
 * Levels 2 - a major rewrite of many core features of the original Levels.
 * 
 * 
 * 
 * Levels 2 todo list.
 * TODO:
 * + Add tiered abilities 																					(Complete)
 * + Add dynamically configured loot to dungeon chests based on the chest/structure
 * + Add axes as melee weapons to receive the leveling systems												(Complete)
 * - Remove mob drops 																						(Complete)
 * * Fix current leveling algorithm 																		(Complete)
 * * Dynamically determine bonus experience based on entity health/level									(Complete)
 * * Adjust leveling cap, plus fix things according to that change 											(Complete)
 * * Change durability to enabled by default																(Complete)
 * * Change durability bonuses added by rarities (and possibly abilities)									(Complete)
 * * Rework enemy leveling completely (legendary enemies need to be extremely challenging)
 * * Rework configurations, add new ones for certain features, and remove unnecessary fluff
 * 
 */
@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION, updateJSON = Reference.UPDATE_URL)
public class Levels 
{
	@SidedProxy(clientSide = Reference.CLIENT_PROXY, serverSide = Reference.COMMON_PROXY)
	public static CommonProxy proxy;
	public static final Logger LOGGER = LogManager.getLogger("Levels");
	public static SimpleNetworkWrapper network;
	private static File config_dir;
	public static boolean isWailaLoaded;
	
	public static File getConfigDir() { return config_dir; }
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		Levels.LOGGER.info("Beginning initialization phases...");
		
		config_dir = new File(event.getModConfigurationDirectory() + "/" + Reference.MODID);
		config_dir.mkdirs();
		ConfigHandler.init(new File(config_dir.getPath(), Reference.MODID + ".cfg"));
		
		ModEvents.registerEvents();
		proxy.preInit();
		CapabilityEnemyLevel.register();
		
		network = NetworkRegistry.INSTANCE.newSimpleChannel("levels");
		network.registerMessage(PacketRarity.Handler.class, PacketRarity.class, 0, Side.CLIENT);
		network.registerMessage(PacketEnemyLevel.Handler.class, PacketEnemyLevel.class, 1, Side.CLIENT);
		
		FMLInterModComms.sendMessage("Waila", "register", "com.thexfactor117.levels.waila.WailaHandler.callbackRegister");
		
		Levels.LOGGER.info("Configurations and core events have been loaded...");
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) 
	{
		isWailaLoaded = Loader.isModLoaded("Waila");
		if (isWailaLoaded) Levels.LOGGER.info("Waila has been loaded. Opting for Waila display information!");
	}

	@EventHandler
	public void onServerLoad(FMLServerStartingEvent event)
	{
		ModCommands.registerCommands(event);
	}
}
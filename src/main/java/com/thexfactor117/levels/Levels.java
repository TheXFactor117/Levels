package com.thexfactor117.levels;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.thexfactor117.levels.handlers.ConfigHandler;
import com.thexfactor117.levels.init.ModEvents;
import com.thexfactor117.levels.network.PacketRarity;
import com.thexfactor117.levels.network.PacketRarity.Handler;
import com.thexfactor117.levels.proxies.CommonProxy;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

/**
 * 
 * @author TheXFactor117
 *
 */
@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION, dependencies = Reference.DEPENDENCIES, updateJSON = Reference.UPDATE_URL)
public class Levels 
{
	@SidedProxy(clientSide = Reference.CLIENT_PROXY, serverSide = Reference.COMMON_PROXY)
	public static CommonProxy PROXY;
	public static final Logger LOGGER = LogManager.getLogger("Levels");
	public static SimpleNetworkWrapper NETWORK;
	private static File CONFIG_DIR;
	
	public static File getConfigDir() { return CONFIG_DIR; }
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		Levels.LOGGER.info("Beginning initialization phases...");
		
		CONFIG_DIR = new File(event.getModConfigurationDirectory() + "/" + Reference.MODID);
		CONFIG_DIR.mkdirs();
		ConfigHandler.init(new File(CONFIG_DIR.getPath(), Reference.MODID + ".cfg"));
		
		ModEvents.registerEvents();
		
		NETWORK = NetworkRegistry.INSTANCE.newSimpleChannel("rarities");
		NETWORK.registerMessage(Handler.class, PacketRarity.class, 0, Side.CLIENT);
		
		Levels.LOGGER.info("Configurations and core events have been loaded...");
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {}
}
package com.thexfactor117.levels;

import com.thexfactor117.levels.handlers.ConfigHandler;
import com.thexfactor117.levels.handlers.VersionChecker;
import com.thexfactor117.levels.helpers.LogHelper;
import com.thexfactor117.levels.init.ModEvents;
import com.thexfactor117.levels.proxies.CommonProxy;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * 
 * @author TheXFactor117
 *
 */
@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION)
public class Levels 
{
	@SidedProxy(clientSide = Reference.CLIENT_PROXY, serverSide = Reference.COMMON_PROXY)
	public static CommonProxy proxy;
	public static VersionChecker versionChecker;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		LogHelper.info("Beginning initialization phases...");
		
		ConfigHandler.registerConfig(event.getModConfigurationDirectory());
		ModEvents.registerEvents();
		
		LogHelper.info("Configurations and core events have been loaded...");
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{		
		LogHelper.info("Entity construction and rendering events have been loaded...");
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		LogHelper.info("Checking if latest version...");
		
		proxy.postInit();
		
		LogHelper.info("VersionChecker complete...");
		LogHelper.info("Levels has finished initializing!");
	}
}

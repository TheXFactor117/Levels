package com.thexfactor117.levels;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.thexfactor117.levels.init.ModEvents;
import com.thexfactor117.levels.network.PacketRarity;
import com.thexfactor117.levels.util.ConfigHandler;
import com.thexfactor117.levels.util.Reference;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
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
 * A simple Minecraft mod focused on the aspect of leveling certain areas
 * of the game. In its simplest state, it will add weapon leveling systems
 * on top of armor, bow, and enemy leveling in the near future. On top of
 * that, other interesting leveling systems are planned to enhance the
 * overall feel of Minecraft, while sticking to a primarily vanilla feel.
 * 
 * ***PRE-ALPHA CODE***
 * 
 */
@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION)
public class Levels 
{
	public static final Logger LOGGER = LogManager.getLogger("Levels");
	public static SimpleNetworkWrapper network;
	private static File configDir;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		configDir = new File(event.getModConfigurationDirectory() + "/" + Reference.MODID);
		configDir.mkdirs();
		ConfigHandler.init(new File(configDir.getPath(), Reference.MODID + ".cfg"));
		
		ModEvents.registerEvents();
		
		network = NetworkRegistry.INSTANCE.newSimpleChannel("levels");
		network.registerMessage(PacketRarity.Handler.class, PacketRarity.class, 0, Side.CLIENT);
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		
	}
	
	public static File getConfigDir()
	{
		return configDir;
	}
}

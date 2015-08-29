package com.thexfactor117.levels;

import net.minecraftforge.common.MinecraftForge;

import com.thexfactor117.levels.events.EventExperienceGain;
import com.thexfactor117.levels.handlers.ConfigHandler;
import com.thexfactor117.levels.init.ModItems;
import com.thexfactor117.levels.init.ModRecipes;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

/**
 * 
 * @author TheXFactor117
 *
 */
@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION)
public class Levels 
{
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		ConfigHandler.registerConfig(event.getSuggestedConfigurationFile());
		ModItems.registerItems();
		MinecraftForge.EVENT_BUS.register(new EventExperienceGain());
	}
	
	@Mod.EventHandler
	public void init(FMLInitializationEvent event)
	{
		ModRecipes.registerRecipes();
	}
}

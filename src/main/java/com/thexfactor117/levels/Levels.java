package com.thexfactor117.levels;

import com.thexfactor117.levels.handlers.ConfigHandler;
import com.thexfactor117.levels.helpers.LogHelper;
import com.thexfactor117.levels.init.ModEvents;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

/**
 * To-Do List
 * 
 * General
 * - Bow Leveling System
 * - Bow Abilities
 * - Bow Crafting Recipes
 * 
 * r1.0.0 - Official Release
 */

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
		LogHelper.info("Beginning initialization phases...");
		
		ConfigHandler.registerConfig(event.getSuggestedConfigurationFile());
		ModEvents.registerEvents();
		
		LogHelper.info("Levels has finished initializing.");
	}
}

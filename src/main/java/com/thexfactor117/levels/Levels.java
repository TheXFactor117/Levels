package com.thexfactor117.levels;

import com.thexfactor117.levels.handlers.ConfigHandler;
import com.thexfactor117.levels.helpers.LogHelper;
import com.thexfactor117.levels.init.ModEvents;
import com.thexfactor117.levels.init.ModItems;
import com.thexfactor117.levels.init.ModRecipes;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

/**
 * To-Do List
 * b2.1.0
 * - Clean up and optimization
 * 
 * b3.0.0
 * - Bow Leveling System
 * - Bow Abilities
 * - Bow Crafting Recipes
 * 
 * b4.0.0
 * - Mod integration
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
		ModItems.registerItems();
		ModEvents.registerEvents();
		//ModIntegration.registerIntegration();
	}
	
	@Mod.EventHandler
	public void init(FMLInitializationEvent event)
	{
		ModRecipes.registerRecipes();
		LogHelper.info("Levels has finished initializing!");
	}
}

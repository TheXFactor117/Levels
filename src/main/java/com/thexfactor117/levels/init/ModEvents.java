package com.thexfactor117.levels.init;

import net.minecraftforge.common.MinecraftForge;

import com.thexfactor117.levels.events.EventItemTooltip;
import com.thexfactor117.levels.events.EventLivingDeath;
import com.thexfactor117.levels.events.EventLivingHurt;
import com.thexfactor117.levels.handlers.ConfigHandler;
import com.thexfactor117.levels.handlers.VersionChecker;
import com.thexfactor117.levels.helpers.LogHelper;

import cpw.mods.fml.common.FMLCommonHandler;

/**
 * 
 * @author TheXFactor117
 *
 */
public class ModEvents 
{
	public static void registerEvents()
	{
		LogHelper.info("Registering events...");

		MinecraftForge.EVENT_BUS.register(new EventLivingHurt());
		MinecraftForge.EVENT_BUS.register(new EventItemTooltip());
		MinecraftForge.EVENT_BUS.register(new EventLivingDeath());
		
		FMLCommonHandler.instance().bus().register(new VersionChecker());

		if (ConfigHandler.enableDevFeatures)
		{
			LogHelper.info("Levels is running in a development environment. Enabling development features...");
			// 1000 xp on mob death
			LogHelper.info("Finished enabling development features.");
		}
		else
		{
			LogHelper.info("Levels is not in a development environment. Disabling development features.");
		}
		
		LogHelper.info("Event registration has finished.");
	}
}

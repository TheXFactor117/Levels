package com.thexfactor117.levels.init;

import com.thexfactor117.levels.events.EventItemTooltip;
import com.thexfactor117.levels.events.EventLivingDeath;
import com.thexfactor117.levels.events.EventLivingDrops;
import com.thexfactor117.levels.events.EventLivingHurt;
import com.thexfactor117.levels.events.EventEntityJoinWorld;
import com.thexfactor117.levels.handlers.ConfigHandler;
import com.thexfactor117.levels.handlers.VersionChecker;
import com.thexfactor117.levels.helpers.LogHelper;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraftforge.common.MinecraftForge;

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

		if (ConfigHandler.enableEnemyLeveling)
		{
			LogHelper.info("Weapon Leveling system activating...");
			MinecraftForge.EVENT_BUS.register(new EventLivingHurt());
			MinecraftForge.EVENT_BUS.register(new EventItemTooltip());
			MinecraftForge.EVENT_BUS.register(new EventLivingDeath());
		}
		
		MinecraftForge.EVENT_BUS.register(new EventLivingDrops());
		
		if (ConfigHandler.enableEnemyLeveling) 
		{
			LogHelper.info("Enemy Leveling system activating...");
			MinecraftForge.EVENT_BUS.register(new EventEntityJoinWorld());
		}
		
		if (ConfigHandler.enableVersionChecker)
		{
			LogHelper.info("Enabling version checker...");
			FMLCommonHandler.instance().bus().register(new VersionChecker());
		}

		LogHelper.info("Configurations are not working right now. Development features being disabled.");
		
		LogHelper.info("Event registration has finished.");
	}
}

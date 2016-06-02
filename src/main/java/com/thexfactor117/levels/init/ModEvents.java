package com.thexfactor117.levels.init;

import com.thexfactor117.levels.events.EventItemCrafted;
import com.thexfactor117.levels.events.EventItemTooltip;
import com.thexfactor117.levels.events.EventLivingDeath;
import com.thexfactor117.levels.events.EventLivingDrops;
import com.thexfactor117.levels.events.EventLivingHurt;
import com.thexfactor117.levels.handlers.ConfigHandler;
import com.thexfactor117.levels.handlers.VersionChecker;
import com.thexfactor117.levels.helpers.LogHelper;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;

/**
 * 
 * @author TheXFactor117
 *
 */
public class ModEvents 
{
	@SuppressWarnings("deprecation")
	public static void registerEvents()
	{
		LogHelper.info("Registering events...");

		if (ConfigHandler.enableWeaponLeveling)
		{
			LogHelper.info("Weapon Leveling system activating...");
			MinecraftForge.EVENT_BUS.register(new EventLivingHurt());
			MinecraftForge.EVENT_BUS.register(new EventItemTooltip());
			MinecraftForge.EVENT_BUS.register(new EventLivingDeath());
			FMLCommonHandler.instance().bus().register(new EventItemCrafted());
		}
		
		MinecraftForge.EVENT_BUS.register(new EventLivingDrops());
		
		if (ConfigHandler.enableVersionChecker)
		{
			LogHelper.info("Enabling version checker...");
			FMLCommonHandler.instance().bus().register(new VersionChecker());
		}

		LogHelper.info("Configurations are not working right now. Development features being disabled.");
		
		LogHelper.info("Event registration has finished.");
	}
}

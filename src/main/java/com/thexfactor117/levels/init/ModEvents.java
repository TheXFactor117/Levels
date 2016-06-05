package com.thexfactor117.levels.init;

import com.thexfactor117.levels.Levels;
import com.thexfactor117.levels.Reference;
import com.thexfactor117.levels.events.EventItemCrafted;
import com.thexfactor117.levels.events.EventItemPickup;
import com.thexfactor117.levels.events.EventItemTooltip;
import com.thexfactor117.levels.events.EventLivingDeath;
import com.thexfactor117.levels.events.EventLivingDrops;
import com.thexfactor117.levels.events.EventLivingHurt;
import com.thexfactor117.levels.handlers.ConfigHandler;
import com.thexfactor117.xlib.version.VersionChecker;

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
		Levels.LOGGER.info("Registering events...");

		if (ConfigHandler.enableWeaponLeveling)
		{
			Levels.LOGGER.info("Weapon Leveling system activating...");
			MinecraftForge.EVENT_BUS.register(new EventLivingHurt());
			MinecraftForge.EVENT_BUS.register(new EventItemTooltip());
			MinecraftForge.EVENT_BUS.register(new EventLivingDeath());
			FMLCommonHandler.instance().bus().register(new EventItemCrafted());
			FMLCommonHandler.instance().bus().register(new EventItemPickup());
		}
		
		if (ConfigHandler.enableVersionChecker)
		{
			FMLCommonHandler.instance().bus().register(new VersionChecker(Reference.VERSION, Reference.VERSION_CHECKER_URL, Reference.UPDATE_MESSAGE, Reference.UPDATE_URL));
		}
		
		if (ConfigHandler.enableMobDrops)
		{
			Levels.LOGGER.info("Mob droppings have been enabled...");
			MinecraftForge.EVENT_BUS.register(new EventLivingDrops());
		}

		Levels.LOGGER.info("Configurations are not working right now. Development features being disabled.");
		Levels.LOGGER.info("Event registration has finished.");
	}
}

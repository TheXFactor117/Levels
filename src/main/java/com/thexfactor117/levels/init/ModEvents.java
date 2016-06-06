package com.thexfactor117.levels.init;

import com.thexfactor117.levels.Levels;
import com.thexfactor117.levels.events.EventItemTooltip;
import com.thexfactor117.levels.events.EventLivingDeath;
import com.thexfactor117.levels.events.EventLivingDrops;
import com.thexfactor117.levels.events.EventLivingHurt;
import com.thexfactor117.levels.events.EventLivingUpdate;
import com.thexfactor117.levels.handlers.ConfigHandler;

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
		Levels.LOGGER.info("Registering events...");

		if (ConfigHandler.enableWeaponLeveling)
		{
			Levels.LOGGER.info("Weapon Leveling system activating...");
			MinecraftForge.EVENT_BUS.register(new EventLivingHurt());
			MinecraftForge.EVENT_BUS.register(new EventItemTooltip());
			MinecraftForge.EVENT_BUS.register(new EventLivingDeath());
			MinecraftForge.EVENT_BUS.register(new EventLivingUpdate());
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

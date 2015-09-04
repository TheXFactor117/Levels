package com.thexfactor117.levels.init;

import net.minecraftforge.common.MinecraftForge;

import com.thexfactor117.levels.events.EventArmorExperienceGain;
import com.thexfactor117.levels.events.EventMeleeExperienceGain;
import com.thexfactor117.levels.helpers.LogHelper;

public class ModEvents 
{
	public static void registerEvents()
	{
		LogHelper.info("Events registering...");
		MinecraftForge.EVENT_BUS.register(new EventMeleeExperienceGain());
		MinecraftForge.EVENT_BUS.register(new EventArmorExperienceGain());
		LogHelper.info("Event registering finished.");
	}
}

package com.thexfactor117.levels.init;

import net.minecraftforge.common.MinecraftForge;

import com.thexfactor117.levels.events.EventArmorExperienceGain;
import com.thexfactor117.levels.events.EventCrafted;
import com.thexfactor117.levels.events.EventEntityDeath;
import com.thexfactor117.levels.events.EventHitEntity;
import com.thexfactor117.levels.events.EventToolTip;
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
		LogHelper.info("Events registering...");
		//MinecraftForge.EVENT_BUS.register(new EventMeleeExperienceGain());
		MinecraftForge.EVENT_BUS.register(new EventArmorExperienceGain());
		FMLCommonHandler.instance().bus().register(new EventCrafted());
		MinecraftForge.EVENT_BUS.register(new EventHitEntity());
		MinecraftForge.EVENT_BUS.register(new EventToolTip());
		MinecraftForge.EVENT_BUS.register(new EventEntityDeath());
		LogHelper.info("Event registering finished.");
	}
}

package com.thexfactor117.levels.init;

import net.minecraft.launchwrapper.Launch;
import net.minecraftforge.common.MinecraftForge;

import com.thexfactor117.levels.events.EventLivingHurt;
import com.thexfactor117.levels.events.EventLivingDrops;
import com.thexfactor117.levels.events.EventItemCrafted;
import com.thexfactor117.levels.events.EventItemTooltip;
import com.thexfactor117.levels.events.EventLivingDeath;
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

		FMLCommonHandler.instance().bus().register(new EventItemCrafted());
		MinecraftForge.EVENT_BUS.register(new EventLivingHurt());
		MinecraftForge.EVENT_BUS.register(new EventItemTooltip());
		MinecraftForge.EVENT_BUS.register(new EventLivingDeath());
		
		FMLCommonHandler.instance().bus().register(new VersionChecker());
		
		// Test Events
		boolean developmentEnvironment = (Boolean)Launch.blackboard.get("fml.deobfuscatedEnvironment");
		
		if (developmentEnvironment)
		{
			MinecraftForge.EVENT_BUS.register(new EventLivingDrops());
		}
		
		LogHelper.info("Event registration has finished.");
	}
}

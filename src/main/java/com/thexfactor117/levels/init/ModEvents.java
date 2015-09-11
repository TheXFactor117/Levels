package com.thexfactor117.levels.init;

import net.minecraft.launchwrapper.Launch;
import net.minecraftforge.common.MinecraftForge;

import com.thexfactor117.levels.events.EventEntityAttacked;
import com.thexfactor117.levels.events.EventEntityDrops;
import com.thexfactor117.levels.events.EventItemCrafted;
import com.thexfactor117.levels.events.EventTooltip;
import com.thexfactor117.levels.events.EventWeaponDeath;
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
		MinecraftForge.EVENT_BUS.register(new EventEntityAttacked());
		MinecraftForge.EVENT_BUS.register(new EventTooltip());
		MinecraftForge.EVENT_BUS.register(new EventWeaponDeath());
		
		FMLCommonHandler.instance().bus().register(new VersionChecker());
		
		// Test Events
		boolean developmentEnvironment = (Boolean)Launch.blackboard.get("fml.deobfuscatedEnvironment");
		
		if (developmentEnvironment)
		{
			MinecraftForge.EVENT_BUS.register(new EventEntityDrops());
		}
		
		LogHelper.info("Event registration has finished.");
	}
}

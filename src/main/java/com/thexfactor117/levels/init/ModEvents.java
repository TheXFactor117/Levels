package com.thexfactor117.levels.init;

import com.thexfactor117.levels.events.EventCreateWeapon;
import com.thexfactor117.levels.events.EventItemTooltip;

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
		MinecraftForge.EVENT_BUS.register(new EventCreateWeapon());
		MinecraftForge.EVENT_BUS.register(new EventItemTooltip());
	}
}

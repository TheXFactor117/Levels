package com.thexfactor117.levels.init;

import com.thexfactor117.levels.event.EventCreateWeapon;
import com.thexfactor117.levels.event.EventItemTooltip;
import com.thexfactor117.levels.event.EventSoulBound;
import com.thexfactor117.levels.event.EventAttack;

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
		MinecraftForge.EVENT_BUS.register(new EventAttack());
		MinecraftForge.EVENT_BUS.register(new EventSoulBound());
	}
}

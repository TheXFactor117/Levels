package com.thexfactor117.levels.init;

import com.thexfactor117.levels.event.EventInput;
import com.thexfactor117.levels.event.EventItemTooltip;
import com.thexfactor117.levels.event.EventLivingUpdate;

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
		MinecraftForge.EVENT_BUS.register(new EventItemTooltip());
		MinecraftForge.EVENT_BUS.register(new EventLivingUpdate());
		MinecraftForge.EVENT_BUS.register(new EventInput());
	}
}

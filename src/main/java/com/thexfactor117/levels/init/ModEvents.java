package com.thexfactor117.levels.init;

import com.thexfactor117.levels.events.EventAttack;
import com.thexfactor117.levels.events.EventCreateWeapon;
import com.thexfactor117.levels.events.EventInput;
import com.thexfactor117.levels.events.EventTooltip;
import com.thexfactor117.levels.events.attributes.EventBarrage;
import com.thexfactor117.levels.events.attributes.EventSoulBound;

import net.minecraftforge.common.MinecraftForge;

/**
 * 
 * @author TheXFactor117
 *
 */
public class ModEvents 
{
	public static void register()
	{
		MinecraftForge.EVENT_BUS.register(new EventInput());
		MinecraftForge.EVENT_BUS.register(new EventCreateWeapon());
		MinecraftForge.EVENT_BUS.register(new EventAttack());
		MinecraftForge.EVENT_BUS.register(new EventTooltip());
		MinecraftForge.EVENT_BUS.register(new EventSoulBound());
		MinecraftForge.EVENT_BUS.register(new EventBarrage());
	}
}

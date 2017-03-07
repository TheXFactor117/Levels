package com.thexfactor117.levels.init;

import com.thexfactor117.levels.config.Config;
import com.thexfactor117.levels.event.EventAchievement;
import com.thexfactor117.levels.event.EventInput;
import com.thexfactor117.levels.event.EventItemTooltip;
import com.thexfactor117.levels.event.EventLivingDeath;
import com.thexfactor117.levels.event.EventLivingHurt;
import com.thexfactor117.levels.event.EventLivingUpdate;
import com.thexfactor117.levels.event.EventPlayerClone;
import com.thexfactor117.levels.event.EventPlayerDrops;
import com.thexfactor117.levels.event.EventPlayerTracking;

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
		MinecraftForge.EVENT_BUS.register(new EventLivingHurt());
		MinecraftForge.EVENT_BUS.register(new EventLivingDeath());
		MinecraftForge.EVENT_BUS.register(new EventPlayerDrops());
		MinecraftForge.EVENT_BUS.register(new EventPlayerClone());
		MinecraftForge.EVENT_BUS.register(new EventAchievement());
		
		if (Config.enemyLeveling)
		{
			MinecraftForge.EVENT_BUS.register(new EventPlayerTracking());
		}
	}
}

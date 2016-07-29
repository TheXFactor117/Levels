package com.thexfactor117.levels.waila;

import com.thexfactor117.levels.Levels;

import mcp.mobius.waila.api.IWailaRegistrar;
import net.minecraft.entity.monster.EntityMob;

/**
 * 
 * @author TheXFactor117
 *
 */
public class WailaHandler
{
	public static void callbackRegister(IWailaRegistrar registrar)
	{
		Levels.LOGGER.info("WailaHandler is being registered...");
		registrar.registerBodyProvider(new WailaEntityHandler(), EntityMob.class);
	}
}

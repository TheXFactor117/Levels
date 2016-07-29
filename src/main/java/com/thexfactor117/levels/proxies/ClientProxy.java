package com.thexfactor117.levels.proxies;

import com.thexfactor117.levels.events.EventRenderOverlay;
import com.thexfactor117.levels.handlers.ConfigHandler;

import net.minecraftforge.common.MinecraftForge;

/**
 * 
 * @author TheXFactor117
 *
 */
public class ClientProxy extends CommonProxy
{
	@Override
	public void preInit()
	{
		if (ConfigHandler.ENEMY_LEVELING)
		{
			MinecraftForge.EVENT_BUS.register(new EventRenderOverlay());
		}
	}
	
	@Override
	public void postInit() {}
}
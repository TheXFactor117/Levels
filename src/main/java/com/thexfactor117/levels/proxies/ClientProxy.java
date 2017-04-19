package com.thexfactor117.levels.proxies;

import com.thexfactor117.levels.event.EventRenderTooltip;

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
		MinecraftForge.EVENT_BUS.register(new EventRenderTooltip());
	}
	
	@Override
	public void init()
	{

	}
}

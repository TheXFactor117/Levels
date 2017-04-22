package com.thexfactor117.levels.config;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * 
 * @author TheXFactor117
 *
 * Handles syncing config files between client and server.
 *
 */
public class ConfigSync 
{	
	@SubscribeEvent
	@SideOnly(Side.SERVER)
	public void playerLoggedIn(PlayerLoggedInEvent event) 
	{
		
	}
}

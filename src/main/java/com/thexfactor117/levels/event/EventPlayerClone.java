package com.thexfactor117.levels.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * 
 * @author TheXFactor117
 *
 */
public class EventPlayerClone 
{
	@SubscribeEvent
	public void onPlayerClone(PlayerEvent.Clone event)
	{
		if (event.isWasDeath())
		{
			EntityPlayer player = event.getEntityPlayer();
			player.inventory.copyInventory(event.getOriginal().inventory);
		}
	}
}

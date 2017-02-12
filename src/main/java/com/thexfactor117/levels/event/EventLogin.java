package com.thexfactor117.levels.event;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * 
 * @author TheXFactor117
 *
 */
@SideOnly(Side.CLIENT)
public class EventLogin 
{
	@SubscribeEvent
	public void onLogin(PlayerEvent.PlayerLoggedInEvent event)
	{
		EntityPlayer player = event.player;
		
		player.addChatMessage(new TextComponentString(TextFormatting.GOLD + "=========={ Levels 2 }=========="));
		player.addChatMessage(new TextComponentString(""));
		player.addChatMessage(new TextComponentString(TextFormatting.GOLD + "- " + TextFormatting.GRAY + I18n.format("levels.login1")));
		player.addChatMessage(new TextComponentString(""));
		player.addChatMessage(new TextComponentString(TextFormatting.GOLD + "- " + TextFormatting.GRAY + I18n.format("levels.login2")));
		player.addChatMessage(new TextComponentString(""));
		player.addChatMessage(new TextComponentString(TextFormatting.GOLD + "================================"));
	}
}

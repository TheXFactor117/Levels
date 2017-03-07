package com.thexfactor117.levels.event;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.player.AchievementEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * 
 * @author TheXFactor117
 *
 */
public class EventAchievement 
{
	@SubscribeEvent
	public void onAchievement(AchievementEvent event)
	{
		if (event.getAchievement().equals(AchievementList.BUILD_SWORD))
		{
			EntityPlayer player = event.getEntityPlayer();
			
			player.addChatMessage(new TextComponentString(TextFormatting.GOLD + "=========={ Levels 2 }=========="));
			player.addChatMessage(new TextComponentString(""));
			player.addChatMessage(new TextComponentString(TextFormatting.GOLD + "- " + TextFormatting.GRAY + I18n.format("levels.login1")));
			player.addChatMessage(new TextComponentString(""));
			player.addChatMessage(new TextComponentString(TextFormatting.GOLD + "=============================="));
			player.addChatMessage(new TextComponentString(""));
		}
	}
}

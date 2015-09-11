package com.thexfactor117.levels.events;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

import com.thexfactor117.levels.helpers.AbilityHelper;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EventTooltip 
{
	@SubscribeEvent
	public void addInformation(ItemTooltipEvent event)
	{
		ItemStack stack = event.itemStack;
		NBTTagCompound nbt = stack.getTagCompound();
		
		if (nbt != null)
		{			
			/*
			 * 
			 * WEAPONS
			 * 
			 */
			if (stack.getItem() instanceof ItemSword)
			{
				/*
				 * Leveling system
				 */
				if (nbt.getInteger("LEVEL") == 1 && nbt.getInteger("EXPERIENCE") >= 1000)
				{
					nbt.setInteger("LEVEL", 2);
					AbilityHelper.drawLevelTwoMeleeAbility(stack);
					Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.WHITE + "Your weapon has leveled up!"));
				}
				
				if (nbt.getInteger("LEVEL") == 2 && nbt.getInteger("EXPERIENCE") >= 2500)
				{
					nbt.setInteger("LEVEL", 3);
					AbilityHelper.drawLevelThreeMeleeAbility(stack);
					Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_GREEN + "Your weapon has leveled up!"));
				}
				
				if (nbt.getInteger("LEVEL") == 3 && nbt.getInteger("EXPERIENCE") >= 5000)
				{
					nbt.setInteger("LEVEL", 4);
					AbilityHelper.drawLevelFourMeleeAbility(stack);
					Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "Your weapon has leveled up!"));
				}
				
				if (nbt.getInteger("LEVEL") == 4 && nbt.getInteger("EXPERIENCE") >= 10000)
				{
					nbt.setInteger("LEVEL", 5);
					AbilityHelper.drawLevelFiveMeleeAbility(stack);
					Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_PURPLE + "Your weapon has leveled up!"));
				}
				
				if (nbt.getInteger("LEVEL") == 5 && nbt.getInteger("EXPERIENCE") >= 20000)
				{
					nbt.setInteger("LEVEL", 6);
					AbilityHelper.drawLevelFiveMeleeAbility(stack);
					Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Your weapon has reached the max level!"));
				}
				
				/*
				 * Tooltip
				 */
				event.toolTip.add("");
				
				if (nbt.getString("RARITY") == "COMMON") event.toolTip.add(EnumChatFormatting.WHITE + "" + EnumChatFormatting.ITALIC + "Common");
				if (nbt.getString("RARITY") == "UNCOMMON") event.toolTip.add(EnumChatFormatting.DARK_GREEN + "" + EnumChatFormatting.ITALIC + "Uncommon");
				if (nbt.getString("RARITY") == "RARE") event.toolTip.add(EnumChatFormatting.AQUA + "" + EnumChatFormatting.ITALIC + "Rare");
				if (nbt.getString("RARITY") == "LEGENDARY") event.toolTip.add(EnumChatFormatting.DARK_PURPLE + "" + EnumChatFormatting.ITALIC + "Legendary");
				if (nbt.getString("RARITY") == "EXTRAORDINARY") event.toolTip.add(EnumChatFormatting.GOLD + "" + EnumChatFormatting.ITALIC + "EXTRAORDINARY");
				
				event.toolTip.add("Level: " + nbt.getInteger("LEVEL"));
				
				if (nbt.getInteger("LEVEL") == 1) event.toolTip.add("Experience: " + nbt.getInteger("EXPERIENCE") + "/1000");
				if (nbt.getInteger("LEVEL") == 2) event.toolTip.add("Experience: " + nbt.getInteger("EXPERIENCE") + "/2500");
				if (nbt.getInteger("LEVEL") == 3) event.toolTip.add("Experience: " + nbt.getInteger("EXPERIENCE") + "/5000");
				if (nbt.getInteger("LEVEL") == 4) event.toolTip.add("Experience: " + nbt.getInteger("EXPERIENCE") + "/10000");
				if (nbt.getInteger("LEVEL") == 5) event.toolTip.add("Experience: " + nbt.getInteger("EXPERIENCE") + "/20000");
				if (nbt.getInteger("LEVEL") == 6) event.toolTip.add("Experience: " + nbt.getInteger("EXPERIENCE"));
				
				event.toolTip.add("");
				
				if (nbt.getBoolean("FIRE")) event.toolTip.add(EnumChatFormatting.DARK_RED + "Fire");
				if (nbt.getBoolean("FROST")) event.toolTip.add(EnumChatFormatting.AQUA + "Frost");
				if (nbt.getBoolean("POISON")) event.toolTip.add(EnumChatFormatting.DARK_GREEN + "Poison");
				if (nbt.getBoolean("STRENGTH")) event.toolTip.add(EnumChatFormatting.LIGHT_PURPLE + "Strength");
				if (nbt.getBoolean("ETHEREAL")) event.toolTip.add(EnumChatFormatting.BLUE + "Ethereal");
				if (nbt.getBoolean("VOID")) event.toolTip.add(EnumChatFormatting.DARK_PURPLE + "Void");
			}
			
			/*
			 * 
			 * ARMOR
			 * 
			 */
			if (stack.getItem() instanceof ItemArmor)
			{
				/*
				 * Leveling system
				 */
				if (nbt.getInteger("LEVEL") == 1 && nbt.getInteger("EXPERIENCE") >= 100)
				{
					nbt.setInteger("LEVEL", 2);
					AbilityHelper.drawLevelTwoArmorAbility(stack);
					Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.WHITE + "Your armor piece has leveled up!"));
				}
				
				if (nbt.getInteger("LEVEL") == 2 && nbt.getInteger("EXPERIENCE") >= 250)
				{
					nbt.setInteger("LEVEL", 3);
					AbilityHelper.drawLevelThreeArmorAbility(stack);
					Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_GREEN + "Your armor piece has leveled up!"));
				}
				
				if (nbt.getInteger("LEVEL") == 3 && nbt.getInteger("EXPERIENCE") >= 500)
				{
					nbt.setInteger("LEVEL", 4);
					AbilityHelper.drawLevelFourArmorAbility(stack);
					Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "Your armor piece has leveled up!"));
				}
				
				if (nbt.getInteger("LEVEL") == 4 && nbt.getInteger("EXPERIENCE") >= 1000)
				{
					nbt.setInteger("LEVEL", 5);
					AbilityHelper.drawLevelFiveArmorAbility(stack);
					Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_PURPLE + "Your armor piece has leveled up!"));
				}
				
				if (nbt.getInteger("LEVEL") == 5 && nbt.getInteger("EXPERIENCE") >= 2000)
				{
					nbt.setInteger("LEVEL", 6);
					AbilityHelper.drawLevelSixArmorAbility(stack);
					Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Your armor piece has reached the max level!"));
				}
				
				/*
				 * Tooltip
				 */
				event.toolTip.add("");
				event.toolTip.add("Level: " + nbt.getInteger("LEVEL"));
				
				if (nbt.getInteger("LEVEL") == 1) event.toolTip.add("Experience: " + nbt.getInteger("EXPERIENCE") + "/100");
				if (nbt.getInteger("LEVEL") == 2) event.toolTip.add("Experience: " + nbt.getInteger("EXPERIENCE") + "/250");
				if (nbt.getInteger("LEVEL") == 3) event.toolTip.add("Experience: " + nbt.getInteger("EXPERIENCE") + "/500");
				if (nbt.getInteger("LEVEL") == 4) event.toolTip.add("Experience: " + nbt.getInteger("EXPERIENCE") + "/1000");
				if (nbt.getInteger("LEVEL") == 5) event.toolTip.add("Experience: " + nbt.getInteger("EXPERIENCE") + "/2000");
				if (nbt.getInteger("LEVEL") == 6) event.toolTip.add("Experience: " + nbt.getInteger("EXPERIENCE"));
				
				event.toolTip.add(stack.getMaxDamage() - stack.getItemDamage() + " Hits Remaining");
				event.toolTip.add("");
				
				if (nbt.getBoolean("HARDENED")) event.toolTip.add(EnumChatFormatting.WHITE + "Hardened");
				if (nbt.getBoolean("POISONED")) event.toolTip.add(EnumChatFormatting.DARK_GREEN + "Poisoned");
				if (nbt.getBoolean("STRENGTH")) event.toolTip.add(EnumChatFormatting.LIGHT_PURPLE + "Strength");
				if (nbt.getBoolean("IMMUNIZATION")) event.toolTip.add(EnumChatFormatting.GOLD + "Immunization");
				if (nbt.getBoolean("ETHEREAL")) event.toolTip.add(EnumChatFormatting.BLUE + "Ethereal");
				if (nbt.getBoolean("VOID")) event.toolTip.add(EnumChatFormatting.DARK_PURPLE + "Void");
			}
		}
	}
}

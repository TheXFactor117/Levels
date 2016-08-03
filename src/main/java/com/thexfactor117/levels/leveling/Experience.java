package com.thexfactor117.levels.leveling;

import java.util.Random;

import com.thexfactor117.levels.handlers.ConfigHandler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

/**
 * 
 * @author TheXFactor117
 * @author MrIbby
 *
 */
public class Experience 
{
	public static int getNextLevel(EntityPlayer player, ItemStack stack, NBTTagCompound nbt, AbilityHelper abilityHelper, int currentLevel, int experience, Random rand)
	{
		int newLevel = currentLevel;
		
		while (currentLevel < ConfigHandler.MAX_LEVEL_CAP && experience >= Experience.getMaxLevelExp(currentLevel))
		{
			newLevel = currentLevel + 1;
			currentLevel++;
			AbilitySelection.getRandomizedAbilities(player, stack, nbt, newLevel, abilityHelper, rand);
			player.addChatMessage(new TextComponentString(TextFormatting.GRAY + "Your weapon has leveled up to level " + TextFormatting.GOLD + "" + newLevel + TextFormatting.GRAY + "!"));
		}
		
		return newLevel;
	}
	
	public static int getLevel(NBTTagCompound nbt)
	{
		return nbt != null ? Math.max(nbt.getInteger("LEVEL"), 1) : 1;
	}
	
	public static void setLevel(NBTTagCompound nbt, int level)
	{
		if (nbt != null)
		{
			if (level > 1)
			{
				nbt.setInteger("LEVEL", level);
			}
			else
			{
				nbt.removeTag("LEVEL");
			}
		}
	}
	
	public static int getExperience(NBTTagCompound nbt)
	{
		return nbt != null ? nbt.getInteger("EXPERIENCE") : 0;
	}
	
	public static void setExperience(NBTTagCompound nbt, int experience)
	{
		if (nbt != null)
		{
			if (experience > 0)
			{
				nbt.setInteger("EXPERIENCE", experience);
			}
			else
			{
				nbt.removeTag("EXPERIENCE");
			}
		}
	}
	
	public static int getMaxLevelExp(int level)
	{
		if (level == 1) return 50;
		int maxXP = (int) Math.pow(level, 2.4D) * 20;
		return maxXP;
	}
}

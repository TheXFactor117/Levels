package com.thexfactor117.levels.leveling;

import java.util.Random;

import com.thexfactor117.levels.handlers.ConfigHandler;

import net.minecraft.entity.player.EntityPlayer;
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
	private static final String LEVEL_KEY = "LEVEL";
	private static final String EXPERIENCE_KEY = "EXPERIENCE";
	
	/**
	 * Levels the stack up according to its experience values; also used to call the randomized ability methods.
	 * @param player
	 * @param nbt
	 * @param abilityHelper
	 * @param level
	 * @param experience
	 * @param rand
	 * @return
	 */
	/*public static int getNextLevel(EntityPlayer player, NBTTagCompound nbt, AbilityHelper abilityHelper, int level, int experience, Random rand)
	{
		while (level < ConfigHandler.maxLevelCap && experience >= Experience.getMaxLevelExp(level))
		{
			level++;
			player.addChatMessage(new TextComponentString(TextFormatting.GRAY + "Your weapon has leveled up to level " + level + "!"));
			
			int select1 = ConfigHandler.maxLevelCap / 6;
			int select2 = ConfigHandler.maxLevelCap / 3;
			int select3 = ConfigHandler.maxLevelCap / 2;
			int select4 = (int) (ConfigHandler.maxLevelCap / 1.5);
			int select5 = (int) (ConfigHandler.maxLevelCap / 1.2);
			int select6 = ConfigHandler.maxLevelCap;
			
			if (level == select1) AbilitySelection.getRandomizedAbilities(player, nbt, level, abilityHelper, rand);
			if (level == select2) AbilitySelection.getRandomizedAbilities(player, nbt, level, abilityHelper, rand);
			if (level == select3) AbilitySelection.getRandomizedAbilities(player, nbt, level, abilityHelper, rand);
			if (level == select4) AbilitySelection.getRandomizedAbilities(player, nbt, level, abilityHelper, rand);
			if (level == select5) AbilitySelection.getRandomizedAbilities(player, nbt, level, abilityHelper, rand);
			if (level == select6) AbilitySelection.getRandomizedAbilities(player, nbt, level, abilityHelper, rand);
		}
		
		return level;
	}*/
	
	public static int getNextLevel(EntityPlayer player, NBTTagCompound nbt, AbilityHelper abilityHelper, int currentLevel, int experience, Random rand)
	{
		int newLevel = currentLevel;
		
		while (currentLevel < ConfigHandler.MAX_LEVEL_CAP && experience >= Experience.getMaxLevelExp(currentLevel))
		{
			newLevel = currentLevel + 1;
			currentLevel++;
			AbilitySelection.getRandomizedAbilities(player, nbt, newLevel, abilityHelper, rand);
			player.addChatMessage(new TextComponentString(TextFormatting.GRAY + "Your weapon has leveled up to level " + newLevel + "!"));
		}
		
		return newLevel;
	}
	
	
	
	public static int getLevel(NBTTagCompound nbt)
	{
		return nbt != null ? Math.max(nbt.getInteger(LEVEL_KEY), 1) : 1;
	}
	
	public static void setLevel(NBTTagCompound nbt, int level)
	{
		if (nbt != null)
		{
			if (level > 1)
			{
				nbt.setInteger(LEVEL_KEY, level);
			}
			else
			{
				nbt.removeTag(LEVEL_KEY);
			}
		}
	}
	
	public static int getExperience(NBTTagCompound nbt)
	{
		return nbt != null ? nbt.getInteger(EXPERIENCE_KEY) : 0;
	}
	
	public static void setExperience(NBTTagCompound nbt, int experience)
	{
		if (nbt != null)
		{
			if (experience > 0)
			{
				nbt.setInteger(EXPERIENCE_KEY, experience);
			}
			else
			{
				nbt.removeTag(EXPERIENCE_KEY);
			}
		}
	}
	
	public static int getMaxLevelExp(int level)
	{
		int maxXP = (int) Math.pow(level, 3D) * 20;
		if (level == 1) maxXP *= 2;
		return maxXP;
	}
}

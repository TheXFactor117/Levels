package com.thexfactor117.levels.helpers;

import java.util.Random;

import com.thexfactor117.levels.handlers.ConfigHandler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

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
	public static int getNextLevel(EntityPlayer player, NBTTagCompound nbt, AbilityHelper abilityHelper, int level, int experience, Random rand)
	{
		while (level < ConfigHandler.maxLevelCap && experience >= Experience.getMaxLevelExp(level))
		{
			level++;
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.WHITE + "Your weapon has leveled up to level " + level + "!"));
			
			switch (level)
			{
				case 3:
					AbilitySelection.getRandomizedAbilities(player, nbt, level, abilityHelper, rand);
					break;
				case 7:
					AbilitySelection.getRandomizedAbilities(player, nbt, level, abilityHelper, rand);
					break;
				case 10:
					AbilitySelection.getRandomizedAbilities(player, nbt, level, abilityHelper, rand);
					break;
				case 13:
					AbilitySelection.getRandomizedAbilities(player, nbt, level, abilityHelper, rand);
					break;
				case 17:
					AbilitySelection.getRandomizedAbilities(player, nbt, level, abilityHelper, rand);
					break;
				case 20:
					AbilitySelection.getRandomizedAbilities(player, nbt, level, abilityHelper, rand);
					break;
			}
		}
		
		return level;
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
		int maxXP = (int) Math.pow(level, 2.2) * 10;
		return maxXP;
	}
}

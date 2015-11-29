package com.thexfactor117.levels.helpers;

import java.util.Random;

import com.thexfactor117.levels.Reference;
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
	private static final int[] maxLevelExp = new int[Reference.MAX_LEVEL - 1];
	
	public static int getNextLevel(EntityPlayer player, NBTTagCompound nbt, AbilityHelper abilityHelper, int level, int experience, Random rand)
	{
		while (level < Reference.MAX_LEVEL && experience >= Experience.getMaxLevelExp(level))
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
		return maxLevelExp[level - 1];
	}
	
	public static void setMaxLevelExp(int level, int maxExp)
	{
		maxLevelExp[level - 1] = maxExp;
	}
	
	public static void setMaxLevels()
	{
		Experience.setMaxLevelExp(1, ConfigHandler.l2Exp);
		Experience.setMaxLevelExp(2, ConfigHandler.l3Exp);
		Experience.setMaxLevelExp(3, ConfigHandler.l4Exp);
		Experience.setMaxLevelExp(4, ConfigHandler.l5Exp);
		Experience.setMaxLevelExp(5, ConfigHandler.l6Exp);
		Experience.setMaxLevelExp(6, ConfigHandler.l7Exp);
		Experience.setMaxLevelExp(7, ConfigHandler.l8Exp);
		Experience.setMaxLevelExp(8, ConfigHandler.l9Exp);
		Experience.setMaxLevelExp(9, ConfigHandler.l10Exp);
		Experience.setMaxLevelExp(10, ConfigHandler.l11Exp);
		Experience.setMaxLevelExp(11, ConfigHandler.l12Exp);
		Experience.setMaxLevelExp(12, ConfigHandler.l13Exp);
		Experience.setMaxLevelExp(13, ConfigHandler.l14Exp);
		Experience.setMaxLevelExp(14, ConfigHandler.l15Exp);
		Experience.setMaxLevelExp(15, ConfigHandler.l16Exp);
		Experience.setMaxLevelExp(16, ConfigHandler.l17Exp);
		Experience.setMaxLevelExp(17, ConfigHandler.l18Exp);
		Experience.setMaxLevelExp(18, ConfigHandler.l19Exp);
		Experience.setMaxLevelExp(19, ConfigHandler.l20Exp);
	}
}

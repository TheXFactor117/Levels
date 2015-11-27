package com.thexfactor117.levels.helpers;

import java.util.Random;

import com.thexfactor117.levels.Reference;
import com.thexfactor117.levels.handlers.ConfigHandler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

/**
 * 
 * @author TheXFactor117
 * @author MrIbby
 *
 */
public class ExperienceHelper 
{
	private static final String LEVEL_KEY = "LEVEL";
	private static final String EXPERIENCE_KEY = "EXPERIENCE";
	private static final int[] maxLevelExp = new int[Reference.MAX_LEVEL - 1];
	
	public static int getNextLevel(EntityPlayer player, NBTTagCompound nbt, int level, int experience, Random rand)
	{
		while (level < Reference.MAX_LEVEL && experience >= ExperienceHelper.getMaxLevelExp(level))
		{
			level++;
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
		ExperienceHelper.setMaxLevelExp(1, ConfigHandler.l2Exp);
		ExperienceHelper.setMaxLevelExp(2, ConfigHandler.l3Exp);
		ExperienceHelper.setMaxLevelExp(3, ConfigHandler.l4Exp);
		ExperienceHelper.setMaxLevelExp(4, ConfigHandler.l5Exp);
		ExperienceHelper.setMaxLevelExp(5, ConfigHandler.l6Exp);
		ExperienceHelper.setMaxLevelExp(6, ConfigHandler.l7Exp);
		ExperienceHelper.setMaxLevelExp(7, ConfigHandler.l8Exp);
		ExperienceHelper.setMaxLevelExp(8, ConfigHandler.l9Exp);
		ExperienceHelper.setMaxLevelExp(9, ConfigHandler.l10Exp);
		ExperienceHelper.setMaxLevelExp(10, ConfigHandler.l11Exp);
		ExperienceHelper.setMaxLevelExp(11, ConfigHandler.l12Exp);
		ExperienceHelper.setMaxLevelExp(12, ConfigHandler.l13Exp);
		ExperienceHelper.setMaxLevelExp(13, ConfigHandler.l14Exp);
		ExperienceHelper.setMaxLevelExp(14, ConfigHandler.l15Exp);
		ExperienceHelper.setMaxLevelExp(15, ConfigHandler.l16Exp);
		ExperienceHelper.setMaxLevelExp(16, ConfigHandler.l17Exp);
		ExperienceHelper.setMaxLevelExp(17, ConfigHandler.l18Exp);
		ExperienceHelper.setMaxLevelExp(18, ConfigHandler.l19Exp);
		ExperienceHelper.setMaxLevelExp(19, ConfigHandler.l20Exp);
	}
}

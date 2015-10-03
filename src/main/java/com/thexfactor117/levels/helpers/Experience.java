package com.thexfactor117.levels.helpers;

import com.thexfactor117.levels.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;

import java.util.Random;

/**
 *
 * @author MrIbby
 *
 */
public final class Experience
{
	private static final String LEVEL_KEY = "LEVEL";
	private static final String EXPERIENCE_KEY = "EXPERIENCE";

	public static int getLevelsUp(EntityPlayer player, NBTTagCompound nbt, int level, int experience, ItemType type, Random random)
	{
		while (level < Reference.MAX_LEVEL && experience >= type.getMaxLevelExp(level))
		{
			level++;
			AbilityHelper.getRandomizedAbilities(nbt, level, type, random);
			EnumChatFormatting color = getLevelUpColor(level);
			String message = StatCollector.translateToLocal(level < Reference.MAX_LEVEL ? "levels.levelUp" : "levels.levelUp.max");
			String typeString = StatCollector.translateToLocal("levels.levelUp." + type.toString().toLowerCase());
			player.addChatMessage(new ChatComponentText(color + String.format(message, typeString)));
		}

		return level;
	}
	
	public static int getLevel(NBTTagCompound nbt)
	{
		return nbt != null ? Math.max(nbt.getInteger(LEVEL_KEY), 1) : 1;
	}
	
	public static void setLevel(NBTTagCompound nbt, int level)
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
	
	public static int getExperience(NBTTagCompound nbt)
	{
		return nbt != null ? nbt.getInteger(EXPERIENCE_KEY) : 0;
	}
	
	public static void setExperience(NBTTagCompound nbt, int experience)
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

	private static EnumChatFormatting getLevelUpColor(int level)
	{
		switch (level)
		{
			case 2:
				return EnumChatFormatting.WHITE;
			case 3:
				return EnumChatFormatting.DARK_GREEN;
			case 4:
				return EnumChatFormatting.AQUA;
			case 5:
				return EnumChatFormatting.DARK_PURPLE;
			case 6:
				return EnumChatFormatting.GOLD;
			default:
				return null;
		}
	}
}

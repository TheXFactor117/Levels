package com.thexfactor117.levels.helpers;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.List;

public enum ItemType
{
	WEAPON(6),
	ARMOR(6);

	private final int maxLevel;
	private final int[] maxLevelExp;

	ItemType(int maxLevel)
	{
		this.maxLevel = maxLevel;
		this.maxLevelExp = new int[maxLevel];
	}

	@SideOnly(Side.CLIENT)
	public void addTooltip(ItemStack stack, List<String> tooltip)
	{
		NBTTagCompound nbt = stack.getTagCompound();
		int level = Experience.getLevel(nbt);

		tooltip.add(I18n.format("levels.level") + ": " + level);

		if (level == maxLevel)
		{
			tooltip.add(I18n.format("levels.experience") + ": " + I18n.format("levels.experience.max"));
		}
		else
		{
			tooltip.add(I18n.format("levels.experience") + ": " + Experience.getExperience(nbt) + "/" + getMaxLevelExp(level));
		}

		tooltip.add(I18n.format("levels.durability." + toString().toLowerCase(), stack.getMaxDamage() - stack.getItemDamage()));
	}

	public int getMaxLevel()
	{
		return maxLevel;
	}

	public int getMaxLevelExp(int level)
	{
		return maxLevelExp[level];
	}

	public void setMaxLevelExp(int level, int maxExp)
	{
		maxLevelExp[level] = maxExp;
	}
}

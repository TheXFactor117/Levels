package com.thexfactor117.levels.helpers;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.resources.I18n;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;

import java.util.List;

/**
 *
 * @author MrIbby
 *
 */
public enum Rarity
{
	UNKOWN(""),
	BASIC(EnumChatFormatting.WHITE),
	UNCOMMON(EnumChatFormatting.DARK_GREEN),
	RARE(EnumChatFormatting.AQUA),
	LEGENDARY(EnumChatFormatting.DARK_PURPLE),
	ANCIENT(EnumChatFormatting.GOLD);
	
	private static final Rarity[] RARITIES = Rarity.values();
	private final String color;
	
	Rarity(Object color)
	{
		this.color = color.toString();
	}

	@SideOnly(Side.CLIENT)
	public static void addTooltip(NBTTagCompound nbt, List<String> tooltip)
	{
		getRarity(nbt).addTooltip(tooltip);
	}

	public static Rarity getRarity(NBTTagCompound nbt)
	{
		return nbt != null && nbt.hasKey("RARITY") ? RARITIES[nbt.getInteger("RARITY")] : UNKOWN;
	}
	
	public void setRarity(NBTTagCompound nbt)
	{
		nbt.setInteger("RARITY", ordinal());
	}
	
	@SideOnly(Side.CLIENT)
	private void addTooltip(List<String> tooltip)
	{
		tooltip.add(color + EnumChatFormatting.ITALIC + I18n.format("levels.rarity." + ordinal()));
	}
}

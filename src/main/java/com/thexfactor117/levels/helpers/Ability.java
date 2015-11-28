package com.thexfactor117.levels.helpers;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;

/**
 * 
 * @author TheXFactor117
 * @author MrIbby
 *
 */
public enum Ability 
{
	FIRE(EnumChatFormatting.RED, Rarity.UNCOMMON, 0.16D, 0.13D, 0.09D),
	FROST(EnumChatFormatting.AQUA, Rarity.UNCOMMON, 0.16D, 0.13D, 0.09D),
	POISON(EnumChatFormatting.DARK_GREEN, Rarity.UNCOMMON, 0.17D, 0.12D, 0.09D),
	STRENGTH(EnumChatFormatting.LIGHT_PURPLE, Rarity.UNCOMMON, 0.16D, 0.12D, 0.08D),
	ELEMENTAL(EnumChatFormatting.GREEN, Rarity.RARE, 0.07D, 0.08D, 0.1D),
	DARKNESS(EnumChatFormatting.DARK_GRAY, Rarity.RARE, 0.07D, 0.08D, 0.1D),
	LIGHT(EnumChatFormatting.WHITE, Rarity.RARE, 0.06D, 0.09D, 0.1D),
	BLOODLUST(EnumChatFormatting.DARK_RED, Rarity.LEGENDARY, 0.05D, 0.08D, 0.1D),
	ETHEREAL(EnumChatFormatting.YELLOW, Rarity.LEGENDARY, 0.05D, 0.07D, 0.1D),
	STING(EnumChatFormatting.GOLD, Rarity.ANCIENT, 0.03D, 0.05D, 0.08D),
	VOID(EnumChatFormatting.DARK_GRAY, Rarity.ANCIENT, 0.02D, 0.05D, 0.07D);
	
	private final String color;
	private final Rarity rarity;
	private final double[] weights;
	
	Ability(Object color, Rarity rarity, double... weights)
	{
		this.color = color.toString();
		this.rarity = rarity;
		this.weights = weights;
	}

	public boolean hasAbility(NBTTagCompound nbt)
	{
		return nbt != null && nbt.getBoolean(toString());
	}
	
	public void addAbility(NBTTagCompound nbt)
	{
		nbt.setBoolean(toString(), true);
	}
	
	public void removeAbility(NBTTagCompound nbt)
	{
		nbt.removeTag(toString());
	}
	
	public String getColor()
	{
		return color;
	}
	
	public Rarity getAbilityRarity()
	{
		return rarity;
	}
	
	public double[] getWeights()
	{
		return weights;
	}
}

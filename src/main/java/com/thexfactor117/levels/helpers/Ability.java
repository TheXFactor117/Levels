package com.thexfactor117.levels.helpers;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;

/**
 *
 * @author MrIbby
 *
 */
public enum Ability
{
	FIRE(EnumChatFormatting.DARK_RED, 0.4D, 0.3D, 0.2D),
	FROST(EnumChatFormatting.AQUA, 0.4D, 0.3D, 0.2D),
	POISON(EnumChatFormatting.DARK_GREEN, 0.07D, 0.13D, 0.17D),
	STRENGTH(EnumChatFormatting.LIGHT_PURPLE, 0.07D, 0.13D, 0.17D),
	ETHEREAL(EnumChatFormatting.BLUE, 0.03D, 0.07D, 0.13D),
	VOID(EnumChatFormatting.DARK_PURPLE, 0.03D, 0.07D, 0.13D),
	HARDENED(EnumChatFormatting.WHITE, 0.4D, 0.3D, 0.2D),
	POISONED(EnumChatFormatting.DARK_GREEN, 0.4D, 0.3D, 0.2D),
	IMMUNIZATION(EnumChatFormatting.GOLD, 0.07D, 0.13D, 0.17D);

	private final String color;
	private final double[] weights;
	
	Ability(Object color, double... weights)
	{
		this.color = color.toString();
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

	public double[] getWeights()
	{
		return weights;
	}
}

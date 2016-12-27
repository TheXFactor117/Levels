package com.thexfactor117.levels.leveling;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;

/**
 * 
 * @author TheXFactor117
 *
 */
public enum Ability
{
	// weapon abilities
	FIRE(TextFormatting.RED, 1, 1),
	FROST(TextFormatting.AQUA, 1, 1),
	POISON(TextFormatting.DARK_GREEN, 1, 1),
	LIGHT(TextFormatting.YELLOW, 2, 1),
	BLOODLUST(TextFormatting.DARK_RED, 2, 1),
	ETHEREAL(TextFormatting.GREEN, 2, 1),
	CHAINED(TextFormatting.GRAY, 3, 1),
	VOID(TextFormatting.DARK_GRAY, 3, 1);
	
	private String color;
	private int tier;
	private double multiplier;
	
	Ability(Object color, int tier, double multiplier)
	{
		this.color = color.toString();
		this.tier = tier;
		this.multiplier = multiplier;
	}
	
	/**
	 * Returns true if the stack has the ability.
	 * @param nbt
	 * @return
	 */
	public boolean hasAbility(NBTTagCompound nbt)
	{
		return nbt != null && nbt.getBoolean(toString());
	}
	
	/**
	 * Adds the specified ability to the stack.
	 * @param nbt
	 */
	public void addAbility(NBTTagCompound nbt, int level)
	{
		nbt.setBoolean(toString(), true);
		setLevel(nbt, level);
	}
	
	/**
	 * Removes the specified ability from the stack.
	 * @param nbt
	 */
	public void removeAbility(NBTTagCompound nbt)
	{
		nbt.removeTag(toString());
		nbt.removeTag(toString() + "_level");
	}
	
	/**
	 * Sets the level of the specified ability.
	 * @param nbt
	 * @param level
	 */
	public void setLevel(NBTTagCompound nbt, int level)
	{
		if (tier <= 2)
		{
			nbt.setInteger(toString() + "_level", level);
		}
	}
	
	/**
	 * Returns the level of the specified ability.
	 * @param nbt
	 * @return
	 */
	public int getLevel(NBTTagCompound nbt)
	{
		if (nbt != null) return nbt.getInteger(toString() + "_level");
		else return 0;
	}
	
	public double getMultiplier()
	{
		return multiplier;
	}
	
	public String getColor()
	{
		return color;
	}
}

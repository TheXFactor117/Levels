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
	FIRE(TextFormatting.RED, 0xFF5555, 1, 1),
	FROST(TextFormatting.AQUA, 0x55FFFF, 1, 1),
	POISON(TextFormatting.DARK_GREEN, 0x00AA00, 1, 1),
	LIGHT(TextFormatting.YELLOW, 0xFFFF55, 2, 1),
	BLOODLUST(TextFormatting.DARK_RED, 0xAA0000, 2, 1),
	ETHEREAL(TextFormatting.GREEN, 0x55FF55, 2, 1),
	CHAINED(TextFormatting.GRAY, 0xAAAAAA, 3, 1),
	VOID(TextFormatting.DARK_GRAY, 0x555555, 3, 1);
	
	public static final int WEAPON_ABILITIES = 8;

	private String color;
	private int hex;
	private int tier;
	private double multiplier;
	
	Ability(Object color, int hex, int tier, double multiplier)
	{
		this.color = color.toString();
		this.hex = hex;
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
		if (level <= 2)
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
	
	public int getTier()
	{
		return tier;
	}
	
	public String getColor()
	{
		return color;
	}
	
	public int getHex()
	{
		return hex;
	}
	
	public String getName()
	{
		String str = this.toString().toLowerCase();
		String first = str.substring(0, 1);
		
		return first.toUpperCase() + str.substring(1);
	}
}

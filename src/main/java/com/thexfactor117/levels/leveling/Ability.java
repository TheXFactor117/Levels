package com.thexfactor117.levels.leveling;

import net.minecraft.client.resources.I18n;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;

/**
 * 
 * @author TheXFactor117
 *
 */
public enum Ability
{
	// weapon abilities (type, color, color code, tier, multiplier)
	// active
	FIRE(0, TextFormatting.RED, 0xFF5555, 1, 1.5),
	FROST(0, TextFormatting.AQUA, 0x55FFFF, 1, 1.5),
	POISON(0, TextFormatting.DARK_GREEN, 0x00AA00, 1, 1.5),
	BLOODLUST(0, TextFormatting.DARK_RED, 0xAA0000, 2, 1.5),
	CHAINED(0, TextFormatting.GRAY, 0xAAAAAA, 3, 1.5),
	VOID(0, TextFormatting.DARK_GRAY, 0x555555, 3, 1),
	// passive
	LIGHT(1, TextFormatting.YELLOW, 0xFFFF55, 2, 1),
	ETHEREAL(1, TextFormatting.GREEN, 0x55FF55, 2, 2),
	SOUL_BOUND(1, TextFormatting.DARK_PURPLE, 0xAA00AA, 3, 1);
	
	public static final int WEAPON_ABILITIES = 9;

	private int type;
	private String color;
	private int hex;
	private int tier;
	private double multiplier;
	
	Ability(int type, Object color, int hex, int tier, double multiplier)
	{
		this.type = type;
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
		if (level <= 3)
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
	
	public boolean canUpgradeLevel(NBTTagCompound nbt)
	{
		if (getType() == 0)
		{
			if (getLevel(nbt) < 3)
				return true;
			else
				return false;
		}
		else
			return false;
	}
	
	public double getMultiplier(int level)
	{
		if (level == 1) return 1;
		else if (level == 2) return multiplier;
		else if (level == 3) return multiplier + (multiplier / 2);
		else return 1;
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
		return I18n.format("levels.ability." + this.ordinal());
	}
	
	public String getName(NBTTagCompound nbt)
	{
		if (getLevel(nbt) == 1)
			return I18n.format("levels.ability." + this.ordinal());
		else if (getLevel(nbt) == 2)
			return I18n.format("levels.ability." + this.ordinal()) + " II";
		else if (getLevel(nbt) == 3)
			return I18n.format("levels.ability." + this.ordinal()) + " III";
		
		return I18n.format("levels.ability." + this.ordinal());
	}
	
	public int getType()
	{
		return type;
	}
}

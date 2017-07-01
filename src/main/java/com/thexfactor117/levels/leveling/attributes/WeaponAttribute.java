package com.thexfactor117.levels.leveling.attributes;

import java.util.ArrayList;

import com.thexfactor117.levels.config.Config;
import com.thexfactor117.levels.leveling.Rarity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;

/**
 * 
 * @author TheXFactor117
 *
 */
public enum WeaponAttribute 
{
	FIRE("Fire", Config.weaponFire, TextFormatting.RED, 0xFF5555, Rarity.UNCOMMON),
	FROST("Frost", Config.weaponFrost, TextFormatting.AQUA, 0x55FFFF, Rarity.UNCOMMON),
	POISON("Poison", Config.weaponPoison, TextFormatting.DARK_GREEN, 0x00AA00, Rarity.UNCOMMON),
	DURABLE("Durable", Config.weaponDurable, TextFormatting.GRAY, 0xAAAAAA, Rarity.UNCOMMON),
	ABSORB("Absorb", Config.weaponAbsorb, TextFormatting.GREEN, 0x55FF55, Rarity.RARE),
	SOUL_BOUND("Soul Bound", Config.weaponSoulBound, TextFormatting.DARK_PURPLE, 0xAA00AA, Rarity.RARE),
	CRITICAL("Critical", Config.weaponCritical, TextFormatting.BLUE, 0x5555FF, Rarity.RARE),
	CHAINED("Chained", Config.weaponChained, TextFormatting.WHITE, 0xFFFFFF, Rarity.LEGENDARY),
	UNBREAKABLE("Unbreakable", Config.weaponUnbreakable, TextFormatting.GRAY, 0xAAAAAA, Rarity.LEGENDARY),
	VOID("Void", Config.weaponVoid, TextFormatting.DARK_GRAY, 0x555555, Rarity.LEGENDARY);
	
	private String name;
	private boolean enabled;
	private String color;
	private int hex;
	private Rarity rarity;
	
	public static ArrayList<WeaponAttribute> WEAPON_ATTRIBUTES = new ArrayList<WeaponAttribute>();

	WeaponAttribute(String name, boolean enabled, Object color, int hex, Rarity rarity)
	{
		this.name = name;
		this.enabled = enabled;
		this.color = color.toString();
		this.hex = hex;
		this.rarity = rarity;
	}
	
	/**
	 * Returns true if the NBT tag compound has the specified Attribute.
	 * @param nbt
	 * @return
	 */
	public boolean hasAttribute(NBTTagCompound nbt)
	{
		return nbt != null && nbt.getBoolean(toString());
	}
	
	/**
	 * Adds the specified Attribute to the NBT tag compound.
	 * @param nbt
	 */
	public void addAttribute(NBTTagCompound nbt)
	{
		if (nbt != null)
		{
			nbt.setBoolean(toString(), true);
			nbt.setInteger(name + "_TIER", 1);
		}
	}
	
	/**
	 * Removes the specified Attribute from the NBT tag compound.
	 * @param nbt
	 */
	public void removeAttribute(NBTTagCompound nbt)
	{
		if (nbt != null)
		{
			nbt.removeTag(toString());
			nbt.removeTag(name + "_TIER");
		}
	}
	
	/**
	 * Sets the tier of the specific attribute.
	 * @param nbt
	 * @param tier
	 */
	public void setAttributeTier(NBTTagCompound nbt, int tier)
	{
		if (nbt != null)
		{
			nbt.setInteger(name + "_TIER", tier);
		}
	}
	
	/**
	 * Returns the tier of the specific attribute.
	 * @param nbt
	 * @return
	 */
	public int getAttributeTier(NBTTagCompound nbt)
	{
		return nbt != null ? nbt.getInteger(name + "_TIER") : 0;
	}
	
	public double getCalculatedValue(NBTTagCompound nbt, double baseValue, double multiplier)
	{
		if (getAttributeTier(nbt) == 1)
			return baseValue;
		else if (getAttributeTier(nbt) == 2)
			return baseValue * multiplier;
		else
			return baseValue * (Math.pow(multiplier, 2));
		
	}
	
	public String getName(NBTTagCompound nbt)
	{
		if (getAttributeTier(nbt) == 1)
			return name;
		else if (getAttributeTier(nbt) == 2)
			return name + " II";
		else if (getAttributeTier(nbt) == 3)
			return name + " III";
		else
			return name;
	}
	
	public boolean isEnabled()
	{
		return enabled;
	}
	
	public String getColor()
	{
		return color;
	}
	
	public int getHex()
	{
		return hex;
	}
	
	public Rarity getRarity()
	{
		return rarity;
	}
	
	static
	{
		for (int i = 0; i < WeaponAttribute.values().length; i++)
		{
			if (WeaponAttribute.values()[i].enabled)
			{
				WeaponAttribute.WEAPON_ATTRIBUTES.add(WeaponAttribute.values()[i]);
			}
		}
	}
}

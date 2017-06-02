package com.thexfactor117.levels.leveling;

import java.util.Random;

import com.thexfactor117.levels.Levels;
import com.thexfactor117.levels.config.Config;
import com.thexfactor117.levels.util.RandomCollection;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;

/**
 * 
 * @author TheXFactor117
 *
 * Handles adding attributes to weapons and armor.
 *
 */
public enum Attribute 
{
	FIRE("Fire", TextFormatting.RED, Rarity.UNCOMMON, 0.25, 0.17, 0.12),
	FROST("Frost", TextFormatting.AQUA, Rarity.UNCOMMON, 0.25, 0.17, 0.12),
	DURABLE("Durable", TextFormatting.GRAY, Rarity.UNCOMMON, 0.25, 0.17, 0.12),
	ABSORB("Absorb", TextFormatting.GREEN, Rarity.RARE, 0.1, 0.18, 0.18),
	SOUL_BOUND("Soul Bound", TextFormatting.DARK_PURPLE, Rarity.RARE, 0.1, 0.18, 0.18),
	VOID("Void", TextFormatting.DARK_GRAY, Rarity.LEGENDARY, 0.025, 0.065, 0.14),
	UNBREAKABLE("Unbreakable", TextFormatting.GRAY, Rarity.LEGENDARY, 0.025, 0.065, 0.14);
	
	private String name;
	private String color;
	private Rarity rarity;
	private double[] weights;
	
	private static final RandomCollection<Attribute> UNCOMMON_ATTRIBUTES = new RandomCollection<Attribute>();
	private static final RandomCollection<Attribute> RARE_ATTRIBUTES = new RandomCollection<Attribute>();
	private static final RandomCollection<Attribute> LEGENDARY_ATTRIBUTES = new RandomCollection<Attribute>();
	
	Attribute(String name, Object color, Rarity rarity, double... weights)
	{
		this.name = name;
		this.color = color.toString();
		this.rarity = rarity;
		this.weights = weights;
	}
	
	/**
	 * Returns a randomized attribute based on the passed in Rarity.
	 * @param rand
	 * @param rarity
	 * @return
	 */
	public static Attribute getRandomAttribute(Random rand, Rarity rarity)
	{
		switch (rarity)
		{
			case COMMON:
				return UNCOMMON_ATTRIBUTES.next(rand);
			case UNCOMMON:
				return UNCOMMON_ATTRIBUTES.next(rand);
			case RARE:
				return RARE_ATTRIBUTES.next(rand);
			case LEGENDARY:
				return LEGENDARY_ATTRIBUTES.next(rand);
			case MYTHIC:
				return LEGENDARY_ATTRIBUTES.next(rand);
			default:
				break;
		}
		
		return null;
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
	 * Returns true if the NBT tag compound has the specified Attribute active.
	 * @param nbt
	 * @return
	 */
	public boolean isActive(NBTTagCompound nbt)
	{
		return nbt != null && nbt.getBoolean(this.getName() + "Active");
	}
	
	/**
	 * Gets the level in which the attribute becomes active.
	 * @param nbt
	 * @return
	 */
	public int getActiveAt(NBTTagCompound nbt)
	{
		return nbt.getInteger(this.getName() + "ActiveAt");
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
			nbt.setBoolean(this.getName() + "Active", false);
			nbt.setInteger(this.getName() + "ActiveAt", (int) (Math.random() * Config.maxLevel + 1));
			Levels.LOGGER.info(getActiveAt(nbt));
		}
	}
	
	/**
	 * Activates the current Attribute. Check to make sure the tag compound has the Attribute first!
	 * @param nbt
	 */
	public void activate(NBTTagCompound nbt)
	{
		if (nbt != null)
		{
			nbt.setBoolean(this.getName() + "Active", true);
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
			nbt.removeTag(this.getName() + "Active");
			nbt.removeTag(this.getName() + "ActiveAt");
		}
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getColor()
	{
		return color;
	}
	
	public Rarity getRarity()
	{
		return rarity;
	}
	
	public double[] getWeights()
	{
		return weights;
	}
	
	static
	{
		for (Attribute attribute : Attribute.values())
		{
			UNCOMMON_ATTRIBUTES.add(attribute.weights[0], attribute);
			RARE_ATTRIBUTES.add(attribute.weights[1], attribute);
			LEGENDARY_ATTRIBUTES.add(attribute.weights[2], attribute);
		}
	}
}

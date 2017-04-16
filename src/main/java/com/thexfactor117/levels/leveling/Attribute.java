package com.thexfactor117.levels.leveling;

import java.util.Random;

import com.thexfactor117.levels.util.Config;
import com.thexfactor117.levels.util.RandomCollection;

import net.minecraft.client.resources.I18n;
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
	FIRE(TextFormatting.RED, Rarity.UNCOMMON, Config.fireChance),
	FROST(TextFormatting.AQUA, Rarity.UNCOMMON, Config.frostChance),
	DURABLE(TextFormatting.GRAY, Rarity.UNCOMMON, Config.durableChance),
	ABSORB(TextFormatting.GREEN, Rarity.RARE, Config.absorbChance),
	SOUL_BOUND(TextFormatting.DARK_PURPLE, Rarity.RARE, Config.soulBoundChance),
	VOID(TextFormatting.DARK_GRAY, Rarity.LEGENDARY, Config.voidChance),
	UNBREAKABLE(TextFormatting.GRAY, Rarity.LEGENDARY, Config.unbreakableChance);
	
	private String color;
	private Rarity rarity;
	private double weight;
	
	private static final RandomCollection<Attribute> UNCOMMON_ATTRIBUTES = new RandomCollection<Attribute>();
	private static final RandomCollection<Attribute> RARE_ATTRIBUTES = new RandomCollection<Attribute>();
	private static final RandomCollection<Attribute> LEGENDARY_ATTRIBUTES = new RandomCollection<Attribute>();
	
	Attribute(Object color, Rarity rarity, double weight)
	{
		this.color = color.toString();
		this.rarity = rarity;
		this.weight = weight;
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
	 * Adds the specified Attribute to the NBT tag compound.
	 * @param nbt
	 */
	public void addAttribute(NBTTagCompound nbt)
	{
		if (nbt != null)
		{
			nbt.setBoolean(toString(), true);
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
		}
	}
	
	public String getName()
	{
		return I18n.format("aw.attributes." + ordinal());
	}
	
	public String getColor()
	{
		return color;
	}
	
	public Rarity getRarity()
	{
		return rarity;
	}
	
	public double getWeight()
	{
		return weight;
	}
	
	static
	{
		for (Attribute attribute : Attribute.values())
		{
			if (attribute.getWeight() > 0.0D)
			{
				switch (attribute.getRarity())
				{
					case UNCOMMON:
						UNCOMMON_ATTRIBUTES.add(attribute.getWeight(), attribute);
						break;
					case RARE:
						RARE_ATTRIBUTES.add(attribute.getWeight(), attribute);
						break;
					case LEGENDARY:
						LEGENDARY_ATTRIBUTES.add(attribute.getWeight(), attribute);
						break;
					default:
						break;
				}
			}
		}
	}
}

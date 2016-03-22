package com.thexfactor117.levels.helpers;

import java.util.Random;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;

/**
 * 
 * @author TheXFactor117
 *
 */
public enum Rarity 
{
	UNKNOWN("", 0.0D),
	BASIC(TextFormatting.WHITE, 0.5D),
	UNCOMMON(TextFormatting.DARK_GREEN, 0.25D),
	RARE(TextFormatting.AQUA, 0.15D),
	LEGENDARY(TextFormatting.DARK_PURPLE, 0.07D),
	ANCIENT(TextFormatting.GOLD, 0.03D);
	
	private static final Rarity[] RARITIES = Rarity.values();
	private static final RandomCollection<Rarity> RANDOM_RARITIES = new RandomCollection<Rarity>();
	private final String color;
	private final double weight;
	
	Rarity(Object color, double weight)
	{
		this.color = color.toString();
		this.weight = weight;
	}
	
	/**
	 * Returns one of the enums above, according to their weight.
	 * @param random
	 * @return
	 */
	public static Rarity getRandomRarity(Random random)
	{
		return RANDOM_RARITIES.next(random);
	}

	/**
	 * Retrieves the rarity applied. Same thing as #getInteger.
	 * @param nbt
	 * @return
	 */
	public static Rarity getRarity(NBTTagCompound nbt)
	{
		return nbt != null && nbt.hasKey("RARITY") ? RARITIES[nbt.getInteger("RARITY")] : UNKNOWN;
	}
	
	public void setRarity(NBTTagCompound nbt)
	{
		if (nbt != null)
		{
			nbt.setInteger("RARITY", ordinal());
		}
	}

	public String getColor()
	{
		return color;
	}

	static
	{
		for (Rarity rarity : RARITIES)
		{
			if (rarity.weight > 0.0D)
			{
				RANDOM_RARITIES.add(rarity.weight, rarity);
			}
		}
	}
}

package com.thexfactor117.levels.helpers;

import java.util.Random;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;

/**
 * 
 * @author TheXFactor117
 *
 */
public enum Rarity 
{
	UNKNOWN("", 0.0D),
	BASIC(EnumChatFormatting.WHITE, 0.60D),
	UNCOMMON(EnumChatFormatting.DARK_GREEN, 0.2D),
	RARE(EnumChatFormatting.AQUA, 0.12D),
	LEGENDARY(EnumChatFormatting.DARK_PURPLE, 0.06D),
	ANCIENT(EnumChatFormatting.GOLD, 0.02D);
	
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

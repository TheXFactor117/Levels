package com.thexfactor117.levels.leveling;

import java.util.Random;

import com.thexfactor117.levels.util.RandomCollection;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;

/**
 * 
 * @author TheXFactor117
 *
 */
public enum Rarity 
{
	DEFAULT("", 0.0),
	COMMON(TextFormatting.GRAY, 0.5),
	UNCOMMON(TextFormatting.DARK_GREEN, 0.25),
	RARE(TextFormatting.AQUA, 0.13),
	ULTRA_RARE(TextFormatting.DARK_PURPLE, 0.07),
	LEGENDARY(TextFormatting.GOLD, 0.035),
	ARCHAIC(TextFormatting.WHITE, 0.015);
	
	private String color;
	private double weight;
	private static final Rarity[] RARITIES = Rarity.values();
	private static final RandomCollection<Rarity> RANDOM_RARITIES = new RandomCollection<Rarity>();
	
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
		return nbt != null && nbt.hasKey("RARITY") ? RARITIES[nbt.getInteger("RARITY")] : DEFAULT;
	}
	
	public void setRarity(NBTTagCompound nbt)
	{
		if (nbt != null)
		{
			nbt.setInteger("RARITY", ordinal());
		}
	}
	
	public static void setRarity(NBTTagCompound nbt, String rarityName)
	{
		int rarity = Integer.parseInt(rarityName);
		nbt.setInteger("RARITY", rarity);
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

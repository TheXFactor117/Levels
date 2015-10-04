package com.thexfactor117.levels.helpers;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;

import java.util.Random;

/**
 *
 * @author MrIbby
 *
 */
public enum Rarity
{
	UNKOWN("", 0.0D),
	BASIC(EnumChatFormatting.WHITE, 0.65D),
	UNCOMMON(EnumChatFormatting.DARK_GREEN, 0.17D),
	RARE(EnumChatFormatting.AQUA, 0.11D),
	LEGENDARY(EnumChatFormatting.DARK_PURPLE, 0.05D),
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

	public static Rarity getRandomRarity(Random random)
	{
		return RANDOM_RARITIES.next(random);
	}

	public static Rarity getRarity(NBTTagCompound nbt)
	{
		return nbt != null && nbt.hasKey("RARITY") ? RARITIES[nbt.getInteger("RARITY")] : UNKOWN;
	}
	
	public void setRarity(NBTTagCompound nbt)
	{
		nbt.setInteger("RARITY", ordinal());
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

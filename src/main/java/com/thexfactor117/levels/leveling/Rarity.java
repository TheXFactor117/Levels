package com.thexfactor117.levels.leveling;

import java.util.Random;

import com.thexfactor117.levels.config.Config;
import com.thexfactor117.levels.util.RandomCollection;

import net.minecraft.client.resources.I18n;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;

/**
 * 
 * @author TheXFactor117
 *
 */
public enum Rarity 
{
	DEFAULT("", 0, 0.0),
	COMMON(TextFormatting.WHITE, 0xFFFFFF, Config.commonChance),
	UNCOMMON(TextFormatting.DARK_GREEN, 0x00AA00, Config.uncommonChance),
	RARE(TextFormatting.AQUA, 0x55FFFF, Config.rareChance),
	ULTRA_RARE(TextFormatting.DARK_PURPLE, 0xAA00AA, Config.ultraRareChance),
	LEGENDARY(TextFormatting.GOLD, 0xFFAA00, Config.legendaryChance),
	ARCHAIC(TextFormatting.LIGHT_PURPLE, 0xFF55FF, Config.archaicChance);
	
	private String color;
	private int hex;
	private double weight;
	private static final Rarity[] RARITIES = Rarity.values();
	private static final RandomCollection<Rarity> RANDOM_RARITIES = new RandomCollection<Rarity>();
	
	Rarity(Object color, int hex, double weight)
	{
		this.color = color.toString();
		this.hex = hex;
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
	 * Retrieves the rarity applied.
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

	public String getName()
	{
		return I18n.format("levels.rarity." + this.ordinal());
	}
	
	public String getColor()
	{
		return color;
	}
	
	public int getHex()
	{
		return hex;
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

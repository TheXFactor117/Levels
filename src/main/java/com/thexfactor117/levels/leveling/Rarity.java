package com.thexfactor117.levels.leveling;

import java.util.Random;

import com.thexfactor117.levels.util.Config;
import com.thexfactor117.levels.util.RandomCollection;

import net.minecraft.client.resources.I18n;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * 
 * @author TheXFactor117
 *
 * Basic rarity implementation for weapons and armor.
 * 
 */
public enum Rarity 
{
	DEFAULT(TextFormatting.GRAY, 0, 0xF0100010, 0x505000FF),
	COMMON(TextFormatting.WHITE, Config.commonChance, 0xF0100010, 0x50FFFFFF),
	UNCOMMON(TextFormatting.DARK_GREEN, Config.uncommonChance, 0xF0100010, 0x5000AA00),
	RARE(TextFormatting.AQUA, Config.rareChance, 0xF0100010, 0x5055FFFF),
	LEGENDARY(TextFormatting.DARK_PURPLE, Config.legendaryChance, 0xF0100010, 0x50AA00AA),
	MYTHIC(TextFormatting.GOLD, Config.mythicChance, 0xF0100010, 0x50FFAA00);
	
	private String color;
	private double weight;
	private int backgroundColor; // used in tooltips
	private int borderColor; // used in tooltips
	
	private static final RandomCollection<Rarity> RANDOM_RARITIES = new RandomCollection<Rarity>();
	
	Rarity(Object color, double weight, int backgroundColor, int borderColor)
	{
		this.color = color.toString();
		this.weight = weight;
		this.backgroundColor = backgroundColor;
		this.borderColor = borderColor;
	}
	
	/**
	 * Returns a random rarity from the Rarity RandomCollection.
	 * @param rand
	 * @return
	 */
	public static Rarity getRandomRarity(Random rand)
	{
		return RANDOM_RARITIES.next(rand);
	}
	
	/**
	 * Return the current rarity in the given NBTTagCompound. Returns Common if it can't find it.
	 * @param nbt
	 * @return
	 */
	public static Rarity getRarity(NBTTagCompound nbt)
	{
		return nbt != null && nbt.hasKey("RARITY") ? Rarity.values()[nbt.getInteger("RARITY")] : DEFAULT;
	}
	
	/**
	 * Sets the rarity specified to the given NBTTagCompound.
	 * @param nbt
	 * @param rarity
	 */
	public static void setRarity(NBTTagCompound nbt, Rarity rarity)
	{
		if (nbt != null)
		{
			nbt.setInteger("RARITY", rarity.ordinal());
		}
	}
	
	@SideOnly(Side.CLIENT)
	public String getName()
	{
		return I18n.format("aw.rarities." + ordinal());
	}
	
	public String getColor()
	{
		return color;
	}
	
	public double getWeight()
	{
		return weight;
	}
	
	public int getBackgroundColor()
	{
		return backgroundColor;
	}
	
	public int getBorderColor()
	{
		return borderColor;
	}
	
	/**
	 * Stores enums into the RandomCollection.
	 */
	static
	{
		for (Rarity rarity : Rarity.values())
		{
			if (rarity.getWeight() > 0.0D)
			{
				RANDOM_RARITIES.add(rarity.weight, rarity);
			}
		}
	}
}

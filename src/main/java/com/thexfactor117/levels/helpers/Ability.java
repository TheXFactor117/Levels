package com.thexfactor117.levels.helpers;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;

/**
 * 
 * @author TheXFactor117
 * @author MrIbby
 *
 */
public enum Ability 
{
	FIRE(TextFormatting.RED, Rarity.BASIC, 0.2666667, 0.2333333D, 0.2D, 0.1666667D, 0.1166667D),
	FROST(TextFormatting.AQUA, Rarity.BASIC, 0.2666667D, 0.2333333D, 0.2D, 0.1666667D, 0.1166667D),
	POISON(TextFormatting.DARK_GREEN, Rarity.BASIC, 0.2666666D, 0.2333334D, 0.2D, 0.1666666D, 0.1166667D),
	STRENGTH(TextFormatting.LIGHT_PURPLE, Rarity.UNCOMMON, 0.04D, 0.0533333D, 0.0666667D, 0.0833333D, 0.0933333D),
	ELEMENTAL(TextFormatting.GREEN, Rarity.UNCOMMON, 0.04D, 0.0533333D, 0.066667D, 0.0833333D, 0.0933333D),
	DARKNESS(TextFormatting.DARK_GRAY, Rarity.UNCOMMON, 0.04D, 0.0533334D, 0.066666D, 0.0833333D, 0.0933333D),
	LIGHT(TextFormatting.WHITE, Rarity.RARE, 0.0166667D, 0.0333333D, 0.0433333D, 0.05D, 0.06D),
	BLOODLUST(TextFormatting.DARK_RED, Rarity.RARE, 0.0166667D, 0.0333333D, 0.0433333D, 0.05D, 0.06D),
	STING(TextFormatting.GOLD, Rarity.RARE, 0.0166666D, 0.0333334D, 0.0433334D, 0.05D, 0.06D),
	ETHEREAL(TextFormatting.YELLOW, Rarity.LEGENDARY, 0.0125D, 0.015D, 0.025D, 0.035D, 0.065D),
	CHAINED(TextFormatting.GRAY, Rarity.LEGENDARY, 0.0125D, 0.015D, 0.025D, 0.035D, 0.065D),
	VOID(TextFormatting.DARK_GRAY, Rarity.ANCIENT, 0.005D, 0.01D, 0.02D, 0.03D, 0.06D);
	
	/*FIRE(TextFormatting.RED, Rarity.BASIC, 26.66667D, 23.33333D, 20D, 16.66667D, 11.66667D),
	FROST(TextFormatting.AQUA, Rarity.BASIC, 26.66667D, 23.33333D, 20D, 16.66667D, 11.66667D),
	POISON(TextFormatting.DARK_GREEN, Rarity.BASIC, 26.66666D, 23.33334D, 20D, 16.66666D, 11.66667D),
	STRENGTH(TextFormatting.LIGHT_PURPLE, Rarity.UNCOMMON, 4D, 5.33333D, 6.66667D, 8.33333D, 9.33333D),
	ELEMENTAL(TextFormatting.GREEN, Rarity.UNCOMMON, 4D, 5.33333D, 6.6667D, 8.33333D, 9.33333D),
	DARKNESS(TextFormatting.DARK_GRAY, Rarity.UNCOMMON, 4D, 5.33334D, 6.6666D, 8.33333D, 9.33333D),
	LIGHT(TextFormatting.WHITE, Rarity.RARE, 1.66667D, 3.33333D, 4.33333D, 5D, 6D),
	BLOODLUST(TextFormatting.DARK_RED, Rarity.RARE, 1.66667D, 3.33333D, 4.33333D, 5D, 6D),
	STING(TextFormatting.GOLD, Rarity.RARE, 1.66666D, 3.33334D, 4.33334D, 5D, 6D),
	ETHEREAL(TextFormatting.YELLOW, Rarity.LEGENDARY, 1.25D, 1.5D, 2.5D, 3.5D, 6.5D),
	CHAINED(TextFormatting.GRAY, Rarity.LEGENDARY, 1.25D, 1.5D, 2.5D, 3.5D, 6.5D),
	VOID(TextFormatting.DARK_GRAY, Rarity.ANCIENT, 0.5D, 1D, 2D, 3D, 6D);*/
	
	private final String color;
	private final Rarity rarity;
	private final double[] weights;
	
	Ability(Object color, Rarity rarity, double... weights)
	{
		this.color = color.toString();
		this.rarity = rarity;
		this.weights = weights;
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
	public void addAbility(NBTTagCompound nbt)
	{
		nbt.setBoolean(toString(), true);
	}
	
	/**
	 * Removes the specified ability from the stack.
	 * @param nbt
	 */
	public void removeAbility(NBTTagCompound nbt)
	{
		nbt.removeTag(toString());
	}
	
	public static boolean isInRange(double x, double min, double max)
	{
		return x > min && x < max;
	}

	public String getColor()
	{
		return color;
	}

	public Rarity getAbilityRarity()
	{
		return rarity;
	}
	
	public double[] getWeights()
	{
		return weights;
	}
}

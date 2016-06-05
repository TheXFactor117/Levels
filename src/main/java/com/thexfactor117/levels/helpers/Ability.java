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
	FIRE(TextFormatting.RED, Rarity.BASIC, 0.2666667D, 0.2333333D, 0.2D, 0.1666667D, 0.1166667D),
	FROST(TextFormatting.AQUA, Rarity.BASIC, 0.2666667D, 0.2333333D, 0.2D, 0.1666667D, 0.1166667D),
	POISON(TextFormatting.DARK_PURPLE, Rarity.BASIC, 0.2666666D, 0.2333334D, 0.2D, 0.1666666D, 0.1166667D),
	STRENGTH(TextFormatting.LIGHT_PURPLE, Rarity.UNCOMMON, 0.04D, 0.0533333D, 0.0666667D, 0.0833333D, 0.0933333D),
	ELEMENTAL(TextFormatting.DARK_GREEN, Rarity.UNCOMMON, 0.04D, 0.0533333D, 0.066667D, 0.0833333D, 0.0933333D),
	DARKNESS(TextFormatting.DARK_GRAY, Rarity.UNCOMMON, 0.04D, 0.0533334D, 0.066666D, 0.0833333D, 0.0933333D),
	LIGHT(TextFormatting.YELLOW, Rarity.RARE, 0.0166667D, 0.0333333D, 0.0433333D, 0.05D, 0.06D),
	BLOODLUST(TextFormatting.DARK_RED, Rarity.RARE, 0.0166667D, 0.0333333D, 0.0433333D, 0.05D, 0.06D),
	STING(TextFormatting.GOLD, Rarity.RARE, 0.0166666D, 0.0333334D, 0.0433334D, 0.05D, 0.06D),
	ETHEREAL(TextFormatting.GREEN, Rarity.LEGENDARY, 0.0125D, 0.015D, 0.025D, 0.035D, 0.065D),
	CHAINED(TextFormatting.GRAY, Rarity.LEGENDARY, 0.0125D, 0.015D, 0.025D, 0.035D, 0.065D),
	VOID(TextFormatting.DARK_GRAY, Rarity.ANCIENT, 0.005D, 0.01D, 0.02D, 0.03D, 0.06D),
	
	MOLTEN(TextFormatting.RED, Rarity.BASIC, 0.266667D, 0.2333333D, 0.2D, 0.166667D, 0.116667D),
	FROZEN(TextFormatting.AQUA, Rarity.BASIC, 0.266667D, 0.2333333D, 0.2D, 0.166667D, 0.116667D),
	TOXIC(TextFormatting.DARK_PURPLE, Rarity.UNCOMMON, 0.266666D, 0.233334D, 0.2D, 0.166666D, 0.116666D),
	//BEASTIAL(TextFormatting.GOLD, Rarity.UNCOMMON),
	ENLIGHTENED(TextFormatting.YELLOW, Rarity.RARE, 0.12D, 0.2D, 0.25D, 0.3D, 0.4D),
	//ABSORB(TextFormatting.GREEN, Rarity.RARE),
	HARDENED(TextFormatting.GRAY, Rarity.LEGENDARY, 0.06D, 0.07D, 0.1D, 0.12D, 0.15D),
	//INVISIBILITY(TextFormatting.WHITE, Rarity.LEGENDARY),
	VOID_ARMOR(TextFormatting.DARK_GRAY, Rarity.ANCIENT, 0.02D, 0.03D, 0.05D, 0.08D, 0.1D);
	
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

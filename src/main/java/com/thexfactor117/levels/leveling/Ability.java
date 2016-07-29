package com.thexfactor117.levels.leveling;

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
	FIRE(TextFormatting.RED, Rarity.BASIC, 2D, 4D, 3, 0.2666667D, 0.2333333D, 0.2D, 0.1666667D, 0.1166667D),
	FROST(TextFormatting.AQUA, Rarity.BASIC, 2D, 4D, 3, 0.2666667D, 0.2333333D, 0.2D, 0.1666667D, 0.1166667D),
	POISON(TextFormatting.DARK_PURPLE, Rarity.BASIC, 2D, 4D, 3, 0.2666666D, 0.2333334D, 0.2D, 0.1666666D, 0.1166667D),
	STRENGTH(TextFormatting.LIGHT_PURPLE, Rarity.UNCOMMON, 2D, 1D, 2, 0.04D, 0.0533333D, 0.0666667D, 0.0833333D, 0.0933333D),
	ELEMENTAL(TextFormatting.DARK_GREEN, Rarity.UNCOMMON, 1D, 1D, 3, 0.04D, 0.0533333D, 0.066667D, 0.0833333D, 0.0933333D),
	DARKNESS(TextFormatting.DARK_GRAY, Rarity.UNCOMMON, 2D, 1D, 2, 0.04D, 0.0533334D, 0.066666D, 0.0833333D, 0.0933333D),
	LIGHT(TextFormatting.YELLOW, Rarity.RARE, 2D, 1D, 2, 0.0166667D, 0.0333333D, 0.0433333D, 0.05D, 0.06D),
	BLOODLUST(TextFormatting.DARK_RED, Rarity.RARE, 1.5D, 1D, 2, 0.0166667D, 0.0333333D, 0.0433333D, 0.05D, 0.06D),
	STING(TextFormatting.GOLD, Rarity.RARE, 1.25D, 1.75D, 3, 0.0166666D, 0.0333334D, 0.0433334D, 0.05D, 0.06D),
	ETHEREAL(TextFormatting.GREEN, Rarity.LEGENDARY, 1D, 1D, 2, 0.0125D, 0.015D, 0.025D, 0.035D, 0.065D),
	CHAINED(TextFormatting.GRAY, Rarity.LEGENDARY, 0.2D, 0.4D, 2, 0.0125D, 0.015D, 0.025D, 0.035D, 0.065D),
	VOID(TextFormatting.DARK_GRAY, Rarity.ANCIENT, 1D, 1D, 1, 0.005D, 0.01D, 0.02D, 0.03D, 0.06D),
	
	MOLTEN(TextFormatting.RED, Rarity.BASIC, 2D, 4D, 3, 0.4D, 0.35D, 0.3D, 0.25D, 0.175D),
	FROZEN(TextFormatting.AQUA, Rarity.BASIC, 2D, 4D, 3, 0.4D, 0.35D, 0.3D, 0.25D, 0.175D),
	TOXIC(TextFormatting.DARK_PURPLE, Rarity.UNCOMMON, 2D, 4D, 3, 0.06D, 0.08D, 0.1D, 0.125D, 0.14D),
	BEASTIAL(TextFormatting.GOLD, Rarity.UNCOMMON, 1D, 2D, 2, 0.06D, 0.08D, 0.1D, 0.125D, 0.14D),
	ENLIGHTENED(TextFormatting.YELLOW, Rarity.RARE, 1D, 1D, 2, 0.025D, 0.05D, 0.065D, 0.075D, 0.09D),
	ABSORB(TextFormatting.GREEN, Rarity.RARE, 1.25D, 1.75D, 3, 0.025D, 0.05D, 0.065D, 0.075D, 0.09D),
	HARDENED(TextFormatting.GRAY, Rarity.LEGENDARY, 1D, 1D, 2, 0.0125D, 0.015D, 0.025D, 0.035D, 0.065D),
	INVISIBILITY(TextFormatting.WHITE, Rarity.LEGENDARY, 2D, 4D, 2, 0.0125D, 0.015D, 0.025D, 0.035D, 0.065D),
	VOID_ARMOR(TextFormatting.DARK_GRAY, Rarity.ANCIENT, 1D, 1D, 1, 0.005D, 0.01D, 0.02D, 0.03D, 0.07D);
	
	private final String color;
	private final Rarity rarity;
	private final double tier2Multiplier;
	private final double tier3Multiplier;
	private final int maxTier;
	private final double[] weights;
	
	Ability(Object color, Rarity rarity, double tier2Multiplier, double tier3Multiplier, int maxTier, double... weights)
	{
		this.color = color.toString();
		this.rarity = rarity;
		this.tier2Multiplier = tier2Multiplier;
		this.tier3Multiplier = tier3Multiplier;
		this.maxTier = maxTier;
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
	public void addAbility(NBTTagCompound nbt, int tier)
	{
		nbt.setBoolean(toString(), true);
		setTier(nbt, tier);
	}
	
	/**
	 * Removes the specified ability from the stack.
	 * @param nbt
	 */
	public void removeAbility(NBTTagCompound nbt)
	{
		nbt.removeTag(toString());
		nbt.removeTag(toString() + "_tier");
	}

	public static void setAbility(NBTTagCompound nbt, String abilityName, int tier)
	{
		String ability = abilityName.toUpperCase();
		nbt.setBoolean(ability, true);
		nbt.setInteger(ability + "_tier", tier);
	}
	
	public static void setAbilities(NBTTagCompound nbt, Ability... abilities)
	{
		for (Ability ability : abilities)
		{
			nbt.setBoolean(ability.toString(), true);
		}
	}
	
	public void setTier(NBTTagCompound nbt, int tier)
	{
		if (tier <= getMaxTier())
		{
			nbt.setInteger(toString() + "_tier", tier);
		}
	}
	
	public int getTier(NBTTagCompound nbt)
	{
		if (nbt != null) return nbt.getInteger(toString() + "_tier");
		else return 0;
	}

	public String getColor()
	{
		return color;
	}

	public Rarity getAbilityRarity()
	{
		return rarity;
	}
	
	public double getMultiplier(int tier, NBTTagCompound nbt)
	{
		if (tier == 2) return getTier2Multiplier();
		if (tier == 3) return getTier3Multiplier();
		return 1D;
	}
	
	public double getTier2Multiplier()
	{
		return tier2Multiplier;
	}
	
	public double getTier3Multiplier()
	{
		return tier3Multiplier;
	}
	
	public int getMaxTier()
	{
		return maxTier;
	}
	
	public double[] getWeights()
	{
		return weights;
	}
}

package com.thexfactor117.levels.helpers;

import java.util.Random;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;

/**
 * 
 * @author TheXFactor117
 *
 */
public enum EnemyLevel 
{
	WEAKENED(EnumChatFormatting.GRAY, 0.25D),
	NORMAL(EnumChatFormatting.WHITE, 0.30D),
	HARDENED(EnumChatFormatting.DARK_GREEN, 0.20D),
	SUPERIOR(EnumChatFormatting.AQUA, 0.15D),
	ELITE(EnumChatFormatting.DARK_PURPLE, 0.7D),
	LEGENDARY(EnumChatFormatting.GOLD, 0.3D);
	
	private static final EnemyLevel[] LEVELS = EnemyLevel.values();
	private static final RandomCollection<EnemyLevel> RANDOM_LEVELS = new RandomCollection<EnemyLevel>();
	private final String color;
	private final double weight;
	
	EnemyLevel(Object color, double weight)
	{
		this.color = color.toString();
		this.weight = weight;
	}
	
	/**
	 * Returns one of the enums above, according to their weight.
	 * @param random
	 * @return
	 */
	public static EnemyLevel getRandomLevel(Random random)
	{
		return RANDOM_LEVELS.next(random);
	}

	public static EnemyLevel getEnemyLevel(NBTTagCompound nbt)
	{
		return nbt != null && nbt.hasKey("ENEMY_LEVEL") ? LEVELS[nbt.getInteger("ENEMY_LEVEL")] : NORMAL;		
	}
	
	public void setEnemyLevel(NBTTagCompound nbt)
	{
		if (nbt != null)
		{
			nbt.setInteger("ENEMY_LEVEL", ordinal());
		}
	}
	
	public String getColor()
	{
		return color;
	}
	
	public double getWeight()
	{
		return weight;
	}
	
	static
	{
		for (EnemyLevel level : LEVELS)
		{
			if (level.weight > 0.0D)
			{
				RANDOM_LEVELS.add(level.weight, level);
			}
		}
	}
}

package com.thexfactor117.levels.leveling;

import java.util.Random;

import com.thexfactor117.levels.util.RandomCollection;

import net.minecraft.nbt.NBTTagCompound;

/**
 * 
 * @author TheXFactor117
 *
 */
public enum EnemyLevel 
{
	DEFAULT(0xFFFFFF, 0),
	WEAKENED(0xAAAAAA, 0.15D),
	NORMAL(0xFFFFFF, 0.50D),
	HARDENED(0x00AA00, 0.15D),
	SUPERIOR(0x55FFFF, 0.1D),
	ELITE(0xAA00AA, 0.07D),
	LEGENDARY(0xFFAA00, 0.03D);
	
	private static final EnemyLevel[] LEVELS = EnemyLevel.values();
	private static final RandomCollection<EnemyLevel> RANDOM_LEVELS = new RandomCollection<EnemyLevel>();
	private int color;
	private double weight;
	
	EnemyLevel(int color, double weight)
	{
		this.color = color;
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

	/**
	 * Returns the EnemyLevel of the entity.
	 * @param nbt
	 * @return
	 */
	public static EnemyLevel getEnemyLevel(NBTTagCompound nbt)
	{
		return nbt != null && nbt.hasKey("ENEMY_LEVEL") ? LEVELS[nbt.getInteger("ENEMY_LEVEL")] : DEFAULT;		
	}
	
	public void setEnemyLevel(NBTTagCompound nbt)
	{
		if (nbt != null)
		{
			nbt.setInteger("ENEMY_LEVEL", ordinal());
		}
	}
	
	public int getColor()
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

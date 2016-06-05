package com.thexfactor117.levels.leveling;

import java.util.Random;

import com.thexfactor117.xlib.misc.RandomCollection;

/**
 * 
 * @author TheXFactor117
 *
 */
public enum WeaponType
{
	WOOD(0.25D),
	STONE(0.2D),
	GOLD(0.15D),
	IRON(0.1D),
	DIAMOND(0.05D),
	BOW(0.25D);
	
	private static final WeaponType[] WEAPONS = WeaponType.values();
	private static final RandomCollection<WeaponType> RANDOM_WEAPON = new RandomCollection<WeaponType>();
	private final double weight;
	
	WeaponType(double weight)
	{
		this.weight = weight;
	}
	
	public static WeaponType getRandomWeaponType(Random rand)
	{
		return RANDOM_WEAPON.next(rand);
	}
	
	static
	{
		for (WeaponType weapon : WEAPONS)
		{
			if (weapon.weight > 0.0D)
			{
				RANDOM_WEAPON.add(weapon.weight, weapon);
			}
		}
	}
}

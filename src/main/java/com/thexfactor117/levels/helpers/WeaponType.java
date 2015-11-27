package com.thexfactor117.levels.helpers;

import java.util.Random;

import com.thexfactor117.levels.helpers.WeaponType;

/**
 * 
 * @author TheXFactor117
 *
 */
public enum WeaponType
{
	WOOD(0.3D),
	STONE(0.25D),
	GOLD(0.2D),
	IRON(0.15D),
	DIAMOND(0.1D);
	
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

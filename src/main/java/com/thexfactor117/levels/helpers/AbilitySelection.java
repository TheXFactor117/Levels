package com.thexfactor117.levels.helpers;

import java.util.Random;

import com.thexfactor117.levels.Reference;

import net.minecraft.nbt.NBTTagCompound;

/**
 * 
 * @author TheXFactor117
 * @author MrIbby
 *
 */
public class AbilitySelection 
{
	@SuppressWarnings("unchecked")
	private static final RandomCollection<Integer>[] ABILITY_LEVELS = new RandomCollection[Reference.MAX_LEVEL];
	
	public static void getRandomizedAbilities(NBTTagCompound nbt, int level, AbilityHelper abilityHelper, Random rand)
	{
		if (level > 1)
		{
			int abilityLevel = ABILITY_LEVELS[level - 1].next(rand);
			Ability ability = abilityHelper.getRandomAbility(abilityLevel, rand);
			LogHelper.info(ability);
			ability.addAbility(nbt);
		}
	}
	
	static
	{
		for (int level = 2; level <= Reference.MAX_LEVEL; level++)
		{
			RandomCollection<Integer> abilityLevels = new RandomCollection<Integer>();
			
			switch (level)
			{
				case 3:
					abilityLevels.add(0.8D, 1);
					abilityLevels.add(0.15D, 2);
					abilityLevels.add(0.05D, 3);
					break;
				case 7:
					abilityLevels.add(0.7D, 1);
					abilityLevels.add(0.2D, 2);
					abilityLevels.add(0.1D, 3);
					break;
				case 10:
					abilityLevels.add(0.6D, 1);
					abilityLevels.add(0.25D, 2);
					abilityLevels.add(0.15D, 3);
					break;
				case 13:
					abilityLevels.add(0.5D, 1);
					abilityLevels.add(0.3D, 2);
					abilityLevels.add(0.2D, 3);
					break;
				case 17:
					abilityLevels.add(0.4D, 1);
					abilityLevels.add(0.35D, 2);
					abilityLevels.add(0.25D, 3);
					break;
				case 20:
					abilityLevels.add(0.3D, 1);
					abilityLevels.add(0.4D, 2);
					abilityLevels.add(0.3D, 3);
					break;
			}
			
			ABILITY_LEVELS[level - 1] = abilityLevels;
		}
	}
}

package com.thexfactor117.levels.helpers;

import com.thexfactor117.levels.Reference;
import net.minecraft.nbt.NBTTagCompound;

import java.util.Random;

/**
 * 
 * @author TheXFactor117
 *
 */
public class AbilityHelper 
{
	private static final RandomCollection<Integer>[] ABILITY_LEVELS = new RandomCollection[Reference.MAX_LEVEL];
	
	/**
	 * Simply retrieves the ability needing to be added to the item.
	 * 
	 * Works by running through several probabilities, depending on level of item and
	 * ability category (rarity). Depending on the calculations, an ability is picked.
	 * Higher item levels have a higher chance of drawing an A3 (Ability 3 Category)
	 * ability (Ethereal/Void), though even a level 2 item has a chance of drawing an
	 * A3 ability. Complicated, yes.
	 * @param nbt
	 * @param level
	 * @param type
	 * @param random
	 */
	public static void getRandomizedAbilities(NBTTagCompound nbt, int level, ItemType type, Random random)
	{
		if (level > 1)
		{
			int abilityLevel = ABILITY_LEVELS[level - 1].next(random);
			Ability ability = type.getRandomAbility(abilityLevel, random);
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
				case 2:
					abilityLevels.add(0.8D, 1);
					abilityLevels.add(0.15D, 2);
					abilityLevels.add(0.05D, 3);
					break;
				case 3:
					abilityLevels.add(0.7D, 1);
					abilityLevels.add(0.2D, 2);
					abilityLevels.add(0.1D, 3);
					break;
				case 4:
					abilityLevels.add(0.6D, 1);
					abilityLevels.add(0.25D, 2);
					abilityLevels.add(0.15D, 3);
					break;
				case 5:
					abilityLevels.add(0.5D, 1);
					abilityLevels.add(0.3D, 2);
					abilityLevels.add(0.2D, 3);
					break;
				case 6:
					abilityLevels.add(0.4D, 1);
					abilityLevels.add(0.35D, 2);
					abilityLevels.add(0.25D, 3);
					break;
			}

			ABILITY_LEVELS[level - 1] = abilityLevels;
		}
	}
}

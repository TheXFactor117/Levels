package com.thexfactor117.levels.helpers;

import java.util.Random;

import com.thexfactor117.levels.handlers.ConfigHandler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

/**
 * 
 * @author TheXFactor117
 * @author MrIbby
 *
 */
public class AbilitySelection 
{
	@SuppressWarnings("unchecked")
	private static final RandomCollection<Integer>[] ABILITY_LEVELS = new RandomCollection[ConfigHandler.maxLevelCap];
	
	/**
	 * Uses AbilityHelper.getRandomAbility to set a random ability to the stack when it levels up.
	 * @param player
	 * @param nbt
	 * @param level
	 * @param abilityHelper
	 * @param rand
	 */
	public static void getRandomizedAbilities(EntityPlayer player, NBTTagCompound nbt, int level, AbilityHelper abilityHelper, Random rand)
	{
		if (level > 1)
		{
			int abilityLevel = ABILITY_LEVELS[level - 1].next(rand);
			Ability ability = abilityHelper.getRandomAbility(abilityLevel, rand);
			
			if (!ability.hasAbility(nbt))
			{
				player.addChatMessage(new ChatComponentText(EnumChatFormatting.WHITE + "Your weapon has a new ability, " + ability.getColor() + ability.toString().toLowerCase() + EnumChatFormatting.WHITE + "!"));
			}
			else
			{
				player.addChatMessage(new ChatComponentText(EnumChatFormatting.WHITE + "Well, looks like you already have this ability..."));
			}
			
			LogHelper.info(ability);
			ability.addAbility(nbt);
		}
	}
	
	/**
	 * Selects an ability to be added to the stack. Depending on what level it is, the chances of getting better abilities
	 * increases as the level increases.
	 */
	static
	{
		for (int level = 2; level <= ConfigHandler.maxLevelCap; level++)
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

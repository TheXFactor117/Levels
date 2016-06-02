package com.thexfactor117.levels.helpers;

import java.util.Random;

import com.thexfactor117.levels.handlers.ConfigHandler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

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
		setAbilityWeights(nbt);
		
		if (level > 1)
		{
			int abilityLevel = ABILITY_LEVELS[level - 1].next(rand);
			Ability ability = abilityHelper.getRandomAbility(abilityLevel, rand);
			
			if (!ability.hasAbility(nbt))
			{
				player.addChatMessage(new TextComponentString(TextFormatting.GRAY + "Your weapon has a new ability, " + ability.getColor() + ability.toString().toLowerCase() + TextFormatting.GRAY + "!"));
			}
			else
			{
				ability = abilityHelper.getRandomAbility(abilityLevel, rand);
				
				if (!ability.hasAbility(nbt))
				{
					player.addChatMessage(new TextComponentString(TextFormatting.GRAY + "Your weapon has a new ability, " + ability.getColor() + ability.toString().toLowerCase() + TextFormatting.GRAY + "!"));
				}
				else
				{
					player.addChatMessage(new TextComponentString(TextFormatting.GRAY + "Uh oh...looks like you have gotten the same ability twice!"));
				}
			}

			LogHelper.info(ability);
			ability.addAbility(nbt);
		}
	}
	
	/**
	 * Selects an ability to be added to the stack. Depending on what level it is, the chances of getting better abilities
	 * increases as the level increases.
	 */
	public static void setAbilityWeights(NBTTagCompound nbt)
	{
		Rarity rarity = Rarity.getRarity(nbt);
		LogHelper.info(rarity);
		
		if (rarity != Rarity.UNKNOWN)
		{
			for (int level = 2; level <= ConfigHandler.maxLevelCap; level++)
			{
				RandomCollection<Integer> abilityLevels = new RandomCollection<Integer>();
				
				int select1 = ConfigHandler.maxLevelCap / 6;
				int select2 = ConfigHandler.maxLevelCap / 3;
				int select3 = ConfigHandler.maxLevelCap / 2;
				int select4 = (int) (ConfigHandler.maxLevelCap / 1.5);
				int select5 = (int) (ConfigHandler.maxLevelCap / 1.2);
				int select6 = ConfigHandler.maxLevelCap;
				
				if (level == select1 || level == select2 || level == select3 || level == select4 || level == select5 || level == select6)
				{
					if (rarity == Rarity.BASIC) 
					{
						abilityLevels.add(0.95D, 1);
						abilityLevels.add(0.03D, 2);
						abilityLevels.add(0.02D, 3);
						LogHelper.info("Basic");
					}
					
					if (rarity == Rarity.UNCOMMON)
					{
						abilityLevels.add(0.03D, 1);
						abilityLevels.add(0.95D, 2);
						abilityLevels.add(0.02D, 3);
						LogHelper.info("Uncommon");
						
					}
					
					if (rarity == Rarity.RARE)
					{
						abilityLevels.add(0.03D, 2);
						abilityLevels.add(0.95D, 3);
						abilityLevels.add(0.02D, 4);
						LogHelper.info("Rare");
					}
					
					if (rarity == Rarity.LEGENDARY)
					{
						abilityLevels.add(0.03D, 3);
						abilityLevels.add(0.95D, 4);
						abilityLevels.add(0.02D, 5);
						LogHelper.info("Legendary");
					}
					
					if (rarity == Rarity.ANCIENT)
					{
						abilityLevels.add(0.02D, 3);
						abilityLevels.add(0.03D, 4);
						abilityLevels.add(0.95D, 5);
						LogHelper.info("Ancient");
					}
				}
				
				ABILITY_LEVELS[level - 1] = abilityLevels;
			}
		}
	}
}

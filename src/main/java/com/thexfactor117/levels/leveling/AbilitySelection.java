package com.thexfactor117.levels.leveling;

import java.util.Random;

import com.thexfactor117.levels.handlers.ConfigHandler;
import com.thexfactor117.levels.utils.RandomCollection;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
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
	private static final RandomCollection<Integer>[] ABILITY_LEVELS = new RandomCollection[ConfigHandler.MAX_LEVEL_CAP];
	
	/**
	 * Uses AbilityHelper.getRandomAbility to set a random ability to the stack when it levels up.
	 * @param player
	 * @param nbt
	 * @param level
	 * @param abilityHelper
	 * @param rand
	 */
	public static void getRandomizedAbilities(EntityPlayer player, ItemStack stack, NBTTagCompound nbt, int level, AbilityHelper abilityHelper, Random rand)
	{
		setAbilityWeights(nbt);

		if (level > 1)
		{
			int abilityLevel = ABILITY_LEVELS[level - 1].next(rand);
			// generate the new ability
			Ability ability = abilityHelper.getRandomAbility(abilityLevel, rand);
			
			// if stack doesn't have the new ability, add it as a tier 1.
			if (!ability.hasAbility(nbt))
			{
				player.addChatMessage(new TextComponentString(TextFormatting.GRAY + "Your " + stack.getDisplayName() + " has a new ability, " + ability.getColor() + ability.toString().toLowerCase() + TextFormatting.GRAY + "!"));
				ability.addAbility(nbt, 1);
			}
			else
			{
				// if stack does have new ability, add a tier to it (while checking to make sure it doesn't pass it's max tier).
				if (ability.getTier(nbt) == ability.getMaxTier())
				{
					player.addChatMessage(new TextComponentString(TextFormatting.GRAY + "The " + ability.getColor() + ability.toString().toLowerCase() + TextFormatting.GRAY + " ability is already at its max tier!"));
				}
				else
				{
					ability.setTier(nbt, ability.getTier(nbt) + 1);
					player.addChatMessage(new TextComponentString(TextFormatting.GRAY + "The " + ability.getColor() + ability.toString().toLowerCase() + TextFormatting.GRAY + " ability has gained a tier!"));
				}
			}
		}
	}
	
	/**
	 * Selects an ability to be added to the stack. Depending on what level it is, the chances of getting better abilities
	 * increases as the level increases.
	 * @param nbt
	 */
	public static void setAbilityWeights(NBTTagCompound nbt)
	{
		Rarity rarity = Rarity.getRarity(nbt);
		
		if (rarity != Rarity.UNKNOWN)
		{
			for (int level = 2; level <= ConfigHandler.MAX_LEVEL_CAP; level++)
			{
				RandomCollection<Integer> abilityLevels = new RandomCollection<Integer>();
				
				// the result of abilityLevels is essentially the rarity in other words
				if (rarity == Rarity.BASIC) 
				{
					abilityLevels.add(0.95D, 1);
					abilityLevels.add(0.03D, 2);
					abilityLevels.add(0.02D, 3);
				}
				
				if (rarity == Rarity.UNCOMMON)
				{
					abilityLevels.add(0.03D, 1);
					abilityLevels.add(0.95D, 2);
					abilityLevels.add(0.02D, 3);
				}
				
				if (rarity == Rarity.RARE)
				{
					abilityLevels.add(0.03D, 2);
					abilityLevels.add(0.95D, 3);
					abilityLevels.add(0.02D, 4);
				}
				
				if (rarity == Rarity.LEGENDARY)
				{
					abilityLevels.add(0.03D, 3);
					abilityLevels.add(0.95D, 4);
					abilityLevels.add(0.02D, 5);
				}
				
				if (rarity == Rarity.ANCIENT)
				{
					abilityLevels.add(0.02D, 3);
					abilityLevels.add(0.03D, 4);
					abilityLevels.add(0.95D, 5);
				}
				
				ABILITY_LEVELS[level - 1] = abilityLevels;
			}
		}
	}
}

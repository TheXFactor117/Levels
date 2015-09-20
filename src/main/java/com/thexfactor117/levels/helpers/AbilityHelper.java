package com.thexfactor117.levels.helpers;

import java.util.Random;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * 
 * @author TheXFactor117
 *
 */
public class AbilityHelper 
{	
	/**
	 * New System
	 */
	
	/**
	 * Simply retrieves the ability needing to be added to the weapon.
	 * 
	 * Works by running through several probabilities, depending on level of weapon and
	 * ability category (rarity). Depending on the calculations, an ability is picked.
	 * Higher weapon levels have a higher chance of drawing an A3 (Ability 3 Category)
	 * ability (Ethereal/Void), though even a level 2 weapon has a chance of drawing an
	 * A3 ability. Complicated, yes. Will it work, hopefully.
	 * @param stack
	 * @param level
	 */
	public static void getRandomizedMeleeAbilities(ItemStack stack, int level)
	{
		NBTTagCompound nbt = stack.getTagCompound();
		
		if (nbt != null && level >= 2)
		{	
			RandomCollection<String> abilityLevelCollection = new RandomCollection<String>();
			
			if (level == 2)
			{
				abilityLevelCollection.add(0.8D, "L2A1");
				abilityLevelCollection.add(0.15D, "L2A2");
				abilityLevelCollection.add(0.05D, "L2A3");
				String abilityLevel = abilityLevelCollection.next();
				
				if (abilityLevel == "L2A1") addA1Abilities(nbt);
				if (abilityLevel == "L2A2") addA2Abilities(nbt);
				if (abilityLevel == "L2A3") addA3Abilities(nbt);
			}
			
			if (level == 3)
			{
				abilityLevelCollection.add(0.7D, "L3A1");
				abilityLevelCollection.add(0.2D, "L3A2");
				abilityLevelCollection.add(0.1D, "L3A3");
				String abilityLevel = abilityLevelCollection.next();
				
				if (abilityLevel == "L3A1") addA1Abilities(nbt);
				if (abilityLevel == "L3A2") addA2Abilities(nbt);
				if (abilityLevel == "L3A3") addA3Abilities(nbt);
			}
			
			if (level == 4)
			{
				abilityLevelCollection.add(0.6D, "L4A1");
				abilityLevelCollection.add(0.25D, "L4A2");
				abilityLevelCollection.add(0.15D, "L4A3");
				String abilityLevel = abilityLevelCollection.next();
				
				if (abilityLevel == "L4A1") addA1Abilities(nbt);
				if (abilityLevel == "L4A2") addA2Abilities(nbt);
				if (abilityLevel == "L4A3") addA3Abilities(nbt);
			}
			
			if (level == 5)
			{
				abilityLevelCollection.add(0.5D, "L5A1");
				abilityLevelCollection.add(0.3D, "L5A2");
				abilityLevelCollection.add(0.2D, "L5A3");
				String abilityLevel = abilityLevelCollection.next();
				
				if (abilityLevel == "L5A1") addA1Abilities(nbt);
				if (abilityLevel == "L5A2") addA2Abilities(nbt);
				if (abilityLevel == "L5A3") addA3Abilities(nbt);
			}
			
			if (level == 6)
			{
				abilityLevelCollection.add(0.4D, "L6A1");
				abilityLevelCollection.add(0.35D, "L6A2");
				abilityLevelCollection.add(0.25D, "L6A3");
				String abilityLevel = abilityLevelCollection.next();
				
				if (abilityLevel == "L6A1") addA1Abilities(nbt);
				if (abilityLevel == "L6A2") addA2Abilities(nbt);
				if (abilityLevel == "L6A3") addA3Abilities(nbt);
			}
		}
	}
	
	public static void addA1Abilities(NBTTagCompound nbt)
	{
		RandomCollection<String> abilitiesCollection = new RandomCollection<String>();
		
		abilitiesCollection.add(0.4D, "fire");
		abilitiesCollection.add(0.4D, "frost");
		abilitiesCollection.add(0.07D, "poison");
		abilitiesCollection.add(0.07D, "strength");
		abilitiesCollection.add(0.03D, "ethereal");
		abilitiesCollection.add(0.03D, "void");
		String abilities = abilitiesCollection.next();
		LogHelper.info(abilities);
		
		if (abilities == "fire") nbt.setBoolean("FIRE", true);
		if (abilities == "frost") nbt.setBoolean("FROST", true);
		if (abilities == "poison") nbt.setBoolean("POISON", true);
		if (abilities == "strength") nbt.setBoolean("STRENGTH", true);
		if (abilities == "ethereal") nbt.setBoolean("ETHEREAL", true);
		if (abilities == "void") nbt.setBoolean("VOID", true);
	}
	
	public static void addA2Abilities(NBTTagCompound nbt)
	{
		RandomCollection<String> abilitiesCollection = new RandomCollection<String>();
		
		abilitiesCollection.add(0.15D, "fire");
		abilitiesCollection.add(0.15D, "frost");
		abilitiesCollection.add(0.25D, "poison");
		abilitiesCollection.add(0.25D, "strength");
		abilitiesCollection.add(0.10D, "ethereal");
		abilitiesCollection.add(0.10D, "void");
		String abilities = abilitiesCollection.next();
		LogHelper.info(abilities);
		
		if (abilities == "fire") nbt.setBoolean("FIRE", true);
		if (abilities == "frost") nbt.setBoolean("FROST", true);
		if (abilities == "poison") nbt.setBoolean("POISON", true);
		if (abilities == "strength") nbt.setBoolean("STRENGTH", true);
		if (abilities == "ethereal") nbt.setBoolean("ETHEREAL", true);
		if (abilities == "void") nbt.setBoolean("VOID", true);
	}
	
	public static void addA3Abilities(NBTTagCompound nbt)
	{
		RandomCollection<String> abilitiesCollection = new RandomCollection<String>();
		
		abilitiesCollection.add(0.10D, "fire");
		abilitiesCollection.add(0.10D, "frost");
		abilitiesCollection.add(0.15D, "poison");
		abilitiesCollection.add(0.15D, "strength");
		abilitiesCollection.add(0.25D, "ethereal");
		abilitiesCollection.add(0.25D, "void");
		String abilities = abilitiesCollection.next();
		LogHelper.info(abilities);
		
		if (abilities == "fire") nbt.setBoolean("FIRE", true);
		if (abilities == "frost") nbt.setBoolean("FROST", true);
		if (abilities == "poison") nbt.setBoolean("POISON", true);
		if (abilities == "strength") nbt.setBoolean("STRENGTH", true);
		if (abilities == "ethereal") nbt.setBoolean("ETHEREAL", true);
		if (abilities == "void") nbt.setBoolean("VOID", true);
	}
	
	
	
	
	
	/**
	 * Draws between the given abilities, picking one to be equipped to the armor.
	 * Default percentages are 50-50 for either Hardneded or Poisoned.
	 * @param stack
	 */
	public static void drawLevelTwoArmorAbility(ItemStack stack)
	{
		NBTTagCompound nbt = stack.getTagCompound();
		
		if (nbt != null)
		{
			Random rand = new Random();
			int var = rand.nextInt(2);
			if (var == 0) nbt.setBoolean("HARDENED", true);
			if (var == 1) nbt.setBoolean("POISONED", true);
		}
	}
	
	/**
	 * Draws between the given abilities, picking one to be equipped to the armor.
	 * Default percentages are 33-33-33 for Strength, Immunization, and nothing respectively.
	 * @param stack
	 */
	public static void drawLevelThreeArmorAbility(ItemStack stack)
	{
		NBTTagCompound nbt = stack.getTagCompound();
		
		if (nbt != null)
		{
			Random rand = new Random();
			int var = rand.nextInt(3);
			if (var == 0) nbt.setBoolean("STRENGTH", true);
			if (var == 1) nbt.setBoolean("IMMUNIZATION", true);
		}
	}

	/**
	 * Draws between the given abilities and parameters. Percentages are more complex here,
	 * due to the several scenarios taken into account.
	 * @param stack
	 */
	public static void drawLevelFourArmorAbility(ItemStack stack)
	{
		NBTTagCompound nbt = stack.getTagCompound();
		
		if (nbt != null)
		{
			if (nbt.getBoolean("HARDENED") == true)
			{
				/*
				 * If level two yielded nothing, add two abilities (the opposite of what the first ability was,
				 * and a random chance between Strength and Immunization.
				 * 
				 * If level two did yield an ability, 50-50 draw between opposite of first ability and other
				 * second ability.
				 */
				if (nbt.getBoolean("STRENGTH") == false && nbt.getBoolean("IMMUNIZATION") == false)
				{
					nbt.setBoolean("POISONED", true);
					
					Random rand = new Random();
					int var = rand.nextInt(2);
					if (var == 0) nbt.setBoolean("STRENGTH", true);
					if (var == 1) nbt.setBoolean("IMMUNIZATION", true);
				}
				else
				{
					if (nbt.getBoolean("STRENGTH") == true)
					{
						Random rand = new Random();
						int var = rand.nextInt(2);
						if (var == 0) nbt.setBoolean("POISONED", true);
						if (var == 1) nbt.setBoolean("IMMUNIZATION", true);
					}
					else
					{
						Random rand = new Random();
						int var = rand.nextInt(2);
						if (var == 0) nbt.setBoolean("POISONED", true);
						if (var == 1) nbt.setBoolean("STRENGTH", true);
					}
				}
			}
			
			if (nbt.getBoolean("POISONED") == true)
			{
				/*
				 * If level two yielded nothing, add two abilities (the opposite of what the first ability was,
				 * and a random chance between Strength and Immunization.
				 * 
				 * If level two did yield an ability, 50-50 draw between opposite of first ability and other
				 * second ability.
				 */
				if (nbt.getBoolean("STRENGTH") == false && nbt.getBoolean("IMMUNIZATION") == false)
				{
					nbt.setBoolean("HARDENED", true);
					
					Random rand = new Random();
					int var = rand.nextInt(2);
					if (var == 0) nbt.setBoolean("STRENGTH", true);
					if (var == 1) nbt.setBoolean("HARDENED", true);
				}
				else
				{
					if (nbt.getBoolean("STRENGTH") == true)
					{
						Random rand = new Random();
						int var = rand.nextInt(2);
						if (var == 0) nbt.setBoolean("HARDENED", true);
						if (var == 1) nbt.setBoolean("IMMUNIZATION", true);
					}
					else
					{
						Random rand = new Random();
						int var = rand.nextInt(2);
						if (var == 0) nbt.setBoolean("HARDENED", true);
						if (var == 1) nbt.setBoolean("STRENGTH", true);
					}
				}
			}
		}
	}

	/**
	 * Draws between the given abilities, picking one to be equipped to the weapon.
	 * Default percentages are 50-50 for Ethereal and Void. 
	 * @param stack
	 */
	public static void drawLevelFiveArmorAbility(ItemStack stack)
	{
		NBTTagCompound nbt = stack.getTagCompound();
		
		if (nbt != null)
		{
			Random rand = new Random();
			int var = rand.nextInt(2);
			if (var == 0) nbt.setBoolean("ETHEREAL", true);
			if (var == 1) nbt.setBoolean("VOID", true);
		}
	}

	/**
	 * Based on the previous ability obtained, a host of possibilities are
	 * available to be attained.
	 * @param stack
	 */
	public static void drawLevelSixArmorAbility(ItemStack stack)
	{
		NBTTagCompound nbt = stack.getTagCompound();
		
		if (nbt != null)
		{
			if (nbt.getBoolean("ETHEREAL") == true)
			{
				Random rand = new Random();
				int var = rand.nextInt(5);
				
				if (var == 0)
				{
					nbt.setBoolean("HARDENED", true);
					nbt.setBoolean("IMMUNIZATION", true);
				}
				else if (var == 1)
				{
					nbt.setBoolean("POISONED", true);
					nbt.setBoolean("STRENGTH", true);
				}
				else if (var == 2)
				{
					nbt.setBoolean("HARDENED", true);
					nbt.setBoolean("STRENGTH", true);
					nbt.setBoolean("IMMUNIZATION", true);
				}
				else if (var == 3)
				{
					nbt.setBoolean("POISONED", true);
					nbt.setBoolean("IMMUNIZATION", true);
				}
				else
				{
					nbt.setBoolean("VOID", true);
				}
			}
			
			if (nbt.getBoolean("VOID") == true)
			{
				Random rand = new Random();
				int var = rand.nextInt(5);
				
				if (var == 0)
				{
					nbt.setBoolean("HARDENED", true);
					nbt.setBoolean("IMMUNIZATION", true);
				}
				else if (var == 1)
				{
					nbt.setBoolean("POISONED", true);
					nbt.setBoolean("STRENGTH", true);
				}
				else if (var == 2)
				{
					nbt.setBoolean("HARDENED", true);
					nbt.setBoolean("STRENGTH", true);
					nbt.setBoolean("IMMUNIZATION", true);
				}
				else if (var == 3)
				{
					nbt.setBoolean("POISONED", true);
					nbt.setBoolean("IMMUNIZATION", true);
				}
				else
				{
					nbt.setBoolean("ETHEREAL", true);
				}
			}
		}
	}
}

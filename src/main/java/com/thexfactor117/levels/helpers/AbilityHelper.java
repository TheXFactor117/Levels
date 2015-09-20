package com.thexfactor117.levels.helpers;

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
	 * Simply retrieves the ability needing to be added to the weapon.
	 * 
	 * Works by running through several probabilities, depending on level of weapon and
	 * ability category (rarity). Depending on the calculations, an ability is picked.
	 * Higher weapon levels have a higher chance of drawing an A3 (Ability 3 Category)
	 * ability (Ethereal/Void), though even a level 2 weapon has a chance of drawing an
	 * A3 ability. Complicated, yes.
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
		
		abilitiesCollection.add(0.3D, "fire");
		abilitiesCollection.add(0.3D, "frost");
		abilitiesCollection.add(0.13D, "poison");
		abilitiesCollection.add(0.13D, "strength");
		abilitiesCollection.add(0.07D, "ethereal");
		abilitiesCollection.add(0.07D, "void");
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
		
		abilitiesCollection.add(0.2D, "fire");
		abilitiesCollection.add(0.2D, "frost");
		abilitiesCollection.add(0.17D, "poison");
		abilitiesCollection.add(0.17D, "strength");
		abilitiesCollection.add(0.13D, "ethereal");
		abilitiesCollection.add(0.13D, "void");
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
	 * Simply retrieves the ability needing to be added to the armor.
	 * 
	 * Works by running through several probabilities, depending on level of armor and
	 * ability category (rarity). Depending on the calculations, an ability is picked.
	 * Higher armor levels have a higher chance of drawing an A3 (Ability 3 Category)
	 * ability (Ethereal/Void), though even a level 2 armor has a chance of drawing an
	 * A3 ability. Complicated, yes.
	 * @param stack
	 * @param level
	 */
	public static void getRandomizedArmorAbilities(ItemStack stack, int level)
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
	
	public static void addA1AbilitiesArmor(NBTTagCompound nbt)
	{
		RandomCollection<String> abilitiesCollection = new RandomCollection<String>();
		
		abilitiesCollection.add(0.4D, "hardened");
		abilitiesCollection.add(0.4D, "poisoned");
		abilitiesCollection.add(0.07D, "strength");
		abilitiesCollection.add(0.07D, "immunization");
		abilitiesCollection.add(0.03D, "ethereal");
		abilitiesCollection.add(0.03D, "void");
		String abilities = abilitiesCollection.next();
		LogHelper.info(abilities);
		
		if (abilities == "hardened") nbt.setBoolean("HARDENED", true);
		if (abilities == "poisoned") nbt.setBoolean("POISONED", true);
		if (abilities == "strength") nbt.setBoolean("STRENGTH", true);
		if (abilities == "immunization") nbt.setBoolean("IMMUNIZATION", true);
		if (abilities == "ethereal") nbt.setBoolean("ETHEREAL", true);
		if (abilities == "void") nbt.setBoolean("VOID", true);
	}
	
	public static void addA2AbilitiesArmor(NBTTagCompound nbt)
	{
		RandomCollection<String> abilitiesCollection = new RandomCollection<String>();
		
		abilitiesCollection.add(0.3D, "hardened");
		abilitiesCollection.add(0.3D, "poisoned");
		abilitiesCollection.add(0.13D, "strength");
		abilitiesCollection.add(0.13D, "immunization");
		abilitiesCollection.add(0.07D, "ethereal");
		abilitiesCollection.add(0.07D, "void");
		String abilities = abilitiesCollection.next();
		LogHelper.info(abilities);
		
		if (abilities == "hardened") nbt.setBoolean("HARDENED", true);
		if (abilities == "poisoned") nbt.setBoolean("POISONED", true);
		if (abilities == "strength") nbt.setBoolean("STRENGTH", true);
		if (abilities == "immunization") nbt.setBoolean("IMMUNIZATION", true);
		if (abilities == "ethereal") nbt.setBoolean("ETHEREAL", true);
		if (abilities == "void") nbt.setBoolean("VOID", true);
	}
	
	public static void addA3AbilitiesArmor(NBTTagCompound nbt)
	{
		RandomCollection<String> abilitiesCollection = new RandomCollection<String>();
		
		abilitiesCollection.add(0.2D, "hardened");
		abilitiesCollection.add(0.2D, "poisoned");
		abilitiesCollection.add(0.17D, "strength");
		abilitiesCollection.add(0.17D, "immunization");
		abilitiesCollection.add(0.13D, "ethereal");
		abilitiesCollection.add(0.13D, "void");
		String abilities = abilitiesCollection.next();
		LogHelper.info(abilities);
		
		if (abilities == "hardened") nbt.setBoolean("HARDENED", true);
		if (abilities == "poisoned") nbt.setBoolean("POISONED", true);
		if (abilities == "strength") nbt.setBoolean("STRENGTH", true);
		if (abilities == "immunization") nbt.setBoolean("IMMUNIZATION", true);
		if (abilities == "ethereal") nbt.setBoolean("ETHEREAL", true);
		if (abilities == "void") nbt.setBoolean("VOID", true);
	}
}

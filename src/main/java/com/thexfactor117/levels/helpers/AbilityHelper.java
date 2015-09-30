package com.thexfactor117.levels.helpers;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import static com.thexfactor117.levels.helpers.Ability.*;

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
			RandomCollection<Integer> abilityLevelCollection = new RandomCollection<Integer>();
			
			if (level == 2)
			{
				abilityLevelCollection.add(0.8D, 1);
				abilityLevelCollection.add(0.15D, 2);
				abilityLevelCollection.add(0.05D, 3);
				Integer abilityLevel = abilityLevelCollection.next();
				
				if (abilityLevel == 1) addA1Abilities(nbt);
				if (abilityLevel == 2) addA2Abilities(nbt);
				if (abilityLevel == 3) addA3Abilities(nbt);
			}
			
			if (level == 3)
			{
				abilityLevelCollection.add(0.7D, 1);
				abilityLevelCollection.add(0.2D, 2);
				abilityLevelCollection.add(0.1D, 3);
				Integer abilityLevel = abilityLevelCollection.next();
				
				if (abilityLevel == 1) addA1Abilities(nbt);
				if (abilityLevel == 2) addA2Abilities(nbt);
				if (abilityLevel == 3) addA3Abilities(nbt);
			}
			
			if (level == 4)
			{
				abilityLevelCollection.add(0.6D, 1);
				abilityLevelCollection.add(0.25D, 2);
				abilityLevelCollection.add(0.15D, 3);
				Integer abilityLevel = abilityLevelCollection.next();
				
				if (abilityLevel == 1) addA1Abilities(nbt);
				if (abilityLevel == 2) addA2Abilities(nbt);
				if (abilityLevel == 3) addA3Abilities(nbt);
			}
			
			if (level == 5)
			{
				abilityLevelCollection.add(0.5D, 1);
				abilityLevelCollection.add(0.3D, 2);
				abilityLevelCollection.add(0.2D, 3);
				Integer abilityLevel = abilityLevelCollection.next();
				
				if (abilityLevel == 1) addA1Abilities(nbt);
				if (abilityLevel == 2) addA2Abilities(nbt);
				if (abilityLevel == 3) addA3Abilities(nbt);
			}
			
			if (level == 6)
			{
				abilityLevelCollection.add(0.4D, 1);
				abilityLevelCollection.add(0.35D, 2);
				abilityLevelCollection.add(0.25D, 3);
				Integer abilityLevel = abilityLevelCollection.next();
				
				if (abilityLevel == 1) addA1Abilities(nbt);
				if (abilityLevel == 2) addA2Abilities(nbt);
				if (abilityLevel == 3) addA3Abilities(nbt);
			}
		}
	}

	public static void addA1Abilities(NBTTagCompound nbt)
	{
		RandomCollection<Ability> abilitiesCollection = new RandomCollection<Ability>();
		
		abilitiesCollection.add(0.4D, FIRE);
		abilitiesCollection.add(0.4D, FROST);
		abilitiesCollection.add(0.07D, POISON);
		abilitiesCollection.add(0.07D, STRENGTH);
		abilitiesCollection.add(0.03D, ETHEREAL);
		abilitiesCollection.add(0.03D, VOID);
		Ability ability = abilitiesCollection.next();
		LogHelper.info(ability);
		
		if (ability == FIRE) FIRE.addAbility(nbt);
		if (ability == FROST) FROST.addAbility(nbt);
		if (ability == POISON) POISON.addAbility(nbt);
		if (ability == STRENGTH) STRENGTH.addAbility(nbt);
		if (ability == ETHEREAL) ETHEREAL.addAbility(nbt);
		if (ability == VOID) VOID.addAbility(nbt);
	}
	
	public static void addA2Abilities(NBTTagCompound nbt)
	{
		RandomCollection<Ability> abilitiesCollection = new RandomCollection<Ability>();
		
		abilitiesCollection.add(0.3D, FIRE);
		abilitiesCollection.add(0.3D, FROST);
		abilitiesCollection.add(0.13D, POISON);
		abilitiesCollection.add(0.13D, STRENGTH);
		abilitiesCollection.add(0.07D, ETHEREAL);
		abilitiesCollection.add(0.07D, VOID);
		Ability ability = abilitiesCollection.next();
		LogHelper.info(ability);
		
		if (ability == FIRE) FIRE.addAbility(nbt);
		if (ability == FROST) FROST.addAbility(nbt);
		if (ability == POISON) POISON.addAbility(nbt);
		if (ability == STRENGTH) STRENGTH.addAbility(nbt);
		if (ability == ETHEREAL) ETHEREAL.addAbility(nbt);
		if (ability == VOID) VOID.addAbility(nbt);
	}
	
	public static void addA3Abilities(NBTTagCompound nbt)
	{
		RandomCollection<Ability> abilitiesCollection = new RandomCollection<Ability>();
		
		abilitiesCollection.add(0.2D, FIRE);
		abilitiesCollection.add(0.2D, FROST);
		abilitiesCollection.add(0.17D, POISON);
		abilitiesCollection.add(0.17D, STRENGTH);
		abilitiesCollection.add(0.13D, ETHEREAL);
		abilitiesCollection.add(0.13D, VOID);
		Ability ability = abilitiesCollection.next();
		LogHelper.info(ability);
		
		if (ability == FIRE) FIRE.addAbility(nbt);
		if (ability == FROST) FROST.addAbility(nbt);
		if (ability == POISON) POISON.addAbility(nbt);
		if (ability == STRENGTH) STRENGTH.addAbility(nbt);
		if (ability == ETHEREAL) ETHEREAL.addAbility(nbt);
		if (ability == VOID) VOID.addAbility(nbt);
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
			RandomCollection<Integer> abilityLevelCollection = new RandomCollection<Integer>();
			
			if (level == 2)
			{
				abilityLevelCollection.add(0.8D, 1);
				abilityLevelCollection.add(0.15D, 2);
				abilityLevelCollection.add(0.05D, 3);
				Integer abilityLevel = abilityLevelCollection.next();
				
				if (abilityLevel == 1) addA1AbilitiesArmor(nbt);
				if (abilityLevel == 2) addA2AbilitiesArmor(nbt);
				if (abilityLevel == 3) addA3AbilitiesArmor(nbt);
			}
			
			if (level == 3)
			{
				abilityLevelCollection.add(0.7D, 1);
				abilityLevelCollection.add(0.2D, 2);
				abilityLevelCollection.add(0.1D, 3);
				Integer abilityLevel = abilityLevelCollection.next();
				
				if (abilityLevel == 1) addA1AbilitiesArmor(nbt);
				if (abilityLevel == 2) addA2AbilitiesArmor(nbt);
				if (abilityLevel == 3) addA3AbilitiesArmor(nbt);
			}
			
			if (level == 4)
			{
				abilityLevelCollection.add(0.6D, 1);
				abilityLevelCollection.add(0.25D, 2);
				abilityLevelCollection.add(0.15D, 3);
				Integer abilityLevel = abilityLevelCollection.next();
				
				if (abilityLevel == 1) addA1AbilitiesArmor(nbt);
				if (abilityLevel == 2) addA2AbilitiesArmor(nbt);
				if (abilityLevel == 3) addA3AbilitiesArmor(nbt);
			}
			
			if (level == 5)
			{
				abilityLevelCollection.add(0.5D, 1);
				abilityLevelCollection.add(0.3D, 2);
				abilityLevelCollection.add(0.2D, 3);
				Integer abilityLevel = abilityLevelCollection.next();
				
				if (abilityLevel == 1) addA1AbilitiesArmor(nbt);
				if (abilityLevel == 2) addA2AbilitiesArmor(nbt);
				if (abilityLevel == 3) addA3AbilitiesArmor(nbt);
			}
			
			if (level == 6)
			{
				abilityLevelCollection.add(0.4D, 1);
				abilityLevelCollection.add(0.35D, 2);
				abilityLevelCollection.add(0.25D, 3);
				Integer abilityLevel = abilityLevelCollection.next();
				
				if (abilityLevel == 1) addA1AbilitiesArmor(nbt);
				if (abilityLevel == 2) addA2AbilitiesArmor(nbt);
				if (abilityLevel == 3) addA3AbilitiesArmor(nbt);
			}
		}
	}
	
	public static void addA1AbilitiesArmor(NBTTagCompound nbt)
	{
		RandomCollection<Ability> abilitiesCollection = new RandomCollection<Ability>();
		
		abilitiesCollection.add(0.4D, HARDENED);
		abilitiesCollection.add(0.4D, POISONED);
		abilitiesCollection.add(0.07D, STRENGTH);
		abilitiesCollection.add(0.07D, IMMUNIZATION);
		abilitiesCollection.add(0.03D, ETHEREAL);
		abilitiesCollection.add(0.03D, VOID);
		Ability ability = abilitiesCollection.next();
		LogHelper.info(ability);
		
		if (ability == HARDENED) HARDENED.addAbility(nbt);
		if (ability == POISONED) POISONED.addAbility(nbt);
		if (ability == STRENGTH) STRENGTH.addAbility(nbt);
		if (ability == IMMUNIZATION) IMMUNIZATION.addAbility(nbt);
		if (ability == ETHEREAL) ETHEREAL.addAbility(nbt);
		if (ability == VOID) VOID.addAbility(nbt);
	}
	
	public static void addA2AbilitiesArmor(NBTTagCompound nbt)
	{
		RandomCollection<Ability> abilitiesCollection = new RandomCollection<Ability>();
		
		abilitiesCollection.add(0.3D, HARDENED);
		abilitiesCollection.add(0.3D, POISONED);
		abilitiesCollection.add(0.13D, STRENGTH);
		abilitiesCollection.add(0.13D, IMMUNIZATION);
		abilitiesCollection.add(0.07D, ETHEREAL);
		abilitiesCollection.add(0.07D, VOID);
		Ability ability = abilitiesCollection.next();
		LogHelper.info(ability);
		
		if (ability == HARDENED) HARDENED.addAbility(nbt);
		if (ability == POISONED) POISONED.addAbility(nbt);
		if (ability == STRENGTH) STRENGTH.addAbility(nbt);
		if (ability == IMMUNIZATION) IMMUNIZATION.addAbility(nbt);
		if (ability == ETHEREAL) ETHEREAL.addAbility(nbt);
		if (ability == VOID) VOID.addAbility(nbt);
	}
	
	public static void addA3AbilitiesArmor(NBTTagCompound nbt)
	{
		RandomCollection<Ability> abilitiesCollection = new RandomCollection<Ability>();
		
		abilitiesCollection.add(0.2D, HARDENED);
		abilitiesCollection.add(0.2D, POISONED);
		abilitiesCollection.add(0.17D, STRENGTH);
		abilitiesCollection.add(0.17D, IMMUNIZATION);
		abilitiesCollection.add(0.13D, ETHEREAL);
		abilitiesCollection.add(0.13D, VOID);
		Ability ability = abilitiesCollection.next();
		LogHelper.info(ability);
		
		if (ability == HARDENED) HARDENED.addAbility(nbt);
		if (ability == POISONED) POISONED.addAbility(nbt);
		if (ability == STRENGTH) STRENGTH.addAbility(nbt);
		if (ability == IMMUNIZATION) IMMUNIZATION.addAbility(nbt);
		if (ability == ETHEREAL) ETHEREAL.addAbility(nbt);
		if (ability == VOID) VOID.addAbility(nbt);
	}
}

package com.thexfactor117.levels.helpers;

import static com.thexfactor117.levels.helpers.Ability.BLOODLUST;
import static com.thexfactor117.levels.helpers.Ability.DARKNESS;
import static com.thexfactor117.levels.helpers.Ability.ELEMENTAL;
import static com.thexfactor117.levels.helpers.Ability.ETHEREAL;
import static com.thexfactor117.levels.helpers.Ability.FIRE;
import static com.thexfactor117.levels.helpers.Ability.FROST;
import static com.thexfactor117.levels.helpers.Ability.LIGHT;
import static com.thexfactor117.levels.helpers.Ability.POISON;
import static com.thexfactor117.levels.helpers.Ability.STING;
import static com.thexfactor117.levels.helpers.Ability.STRENGTH;
import static com.thexfactor117.levels.helpers.Ability.VOID;

import java.util.Random;

/**
 * 
 * @author TheXFactor117
 * @author MrIbby
 *
 */
public enum AbilityHelper 
{
	ABILITIES(FIRE, FROST, POISON, STRENGTH, ELEMENTAL, DARKNESS, LIGHT, BLOODLUST, ETHEREAL, STING, VOID);
	
	@SuppressWarnings("unchecked")
	private final RandomCollection<Ability>[] abilityCollections = new RandomCollection[5];
	private final Ability[] abilities;
	
	AbilityHelper(Ability... abilities)
	{
		this.abilities = abilities;
		
		for (int i = 0; i < 5; i++)
		{
			RandomCollection<Ability> abilityCollection = new RandomCollection<Ability>();
			
			for (Ability ability : abilities)
			{
				double weight = ability.getWeights()[i];
				abilityCollection.add(weight, ability);
			}
			
			abilityCollections[i] = abilityCollection;
		}
	}
	
	public Ability[] getAbilities()
	{
		return abilities;
	}
	
	/**
	 * Grabs a randomized ability according to the weights set for each ability.
	 * @param level
	 * @param rand
	 * @return
	 */
	public Ability getRandomAbility(int level, Random rand)
	{
		LogHelper.info("Level: " + level);
		return abilityCollections[level - 1].next(rand);
	}
}

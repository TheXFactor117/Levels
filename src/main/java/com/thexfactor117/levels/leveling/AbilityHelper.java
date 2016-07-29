package com.thexfactor117.levels.leveling;

import static com.thexfactor117.levels.leveling.Ability.ABSORB;
import static com.thexfactor117.levels.leveling.Ability.BEASTIAL;
import static com.thexfactor117.levels.leveling.Ability.BLOODLUST;
import static com.thexfactor117.levels.leveling.Ability.CHAINED;
import static com.thexfactor117.levels.leveling.Ability.DARKNESS;
import static com.thexfactor117.levels.leveling.Ability.ELEMENTAL;
import static com.thexfactor117.levels.leveling.Ability.ENLIGHTENED;
import static com.thexfactor117.levels.leveling.Ability.ETHEREAL;
import static com.thexfactor117.levels.leveling.Ability.FIRE;
import static com.thexfactor117.levels.leveling.Ability.FROST;
import static com.thexfactor117.levels.leveling.Ability.FROZEN;
import static com.thexfactor117.levels.leveling.Ability.HARDENED;
import static com.thexfactor117.levels.leveling.Ability.INVISIBILITY;
import static com.thexfactor117.levels.leveling.Ability.LIGHT;
import static com.thexfactor117.levels.leveling.Ability.MOLTEN;
import static com.thexfactor117.levels.leveling.Ability.POISON;
import static com.thexfactor117.levels.leveling.Ability.STING;
import static com.thexfactor117.levels.leveling.Ability.STRENGTH;
import static com.thexfactor117.levels.leveling.Ability.TOXIC;
import static com.thexfactor117.levels.leveling.Ability.VOID;
import static com.thexfactor117.levels.leveling.Ability.VOID_ARMOR;

import java.util.Random;

import com.thexfactor117.levels.Levels;
import com.thexfactor117.levels.utils.RandomCollection;

/**
 * 
 * @author TheXFactor117
 * @author MrIbby
 *
 */
public enum AbilityHelper 
{
	WEAPON(FIRE, FROST, POISON, STRENGTH, ELEMENTAL, DARKNESS, LIGHT, BLOODLUST, STING, ETHEREAL, CHAINED, VOID),
	ARMOR(MOLTEN, FROZEN, TOXIC, ENLIGHTENED, HARDENED, VOID_ARMOR, BEASTIAL, ABSORB, INVISIBILITY);
	
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
		Levels.LOGGER.info("Level: " + level);
		return abilityCollections[level - 1].next(rand);
	}
}

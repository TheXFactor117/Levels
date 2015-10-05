package com.thexfactor117.levels.helpers;

import com.thexfactor117.levels.Reference;

import java.util.Random;

import static com.thexfactor117.levels.helpers.Ability.*;

public enum ItemType
{
	WEAPON(FIRE, FROST, POISON, STRENGTH, ETHEREAL, VOID),
	ARMOR(HARDENED, POISONED, STRENGTH, IMMUNIZATION, ETHEREAL, VOID);

	@SuppressWarnings("unchecked")
	private final RandomCollection<Ability>[] abilityCollections = new RandomCollection[Reference.MAX_ABILITY_LEVEL];
	private final int[] maxLevelExp = new int[Reference.MAX_LEVEL - 1];
	private final Ability[] abilities;

	ItemType(Ability... abilities)
	{
		this.abilities = abilities;

		for (int i = 0; i < Reference.MAX_ABILITY_LEVEL; i++)
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

	public int getMaxLevelExp(int level)
	{
		return maxLevelExp[level - 1];
	}

	public void setMaxLevelExp(int level, int maxExp)
	{
		maxLevelExp[level - 1] = maxExp;
	}

	public Ability[] getAbilities()
	{
		return abilities;
	}

	public Ability getRandomAbility(int level, Random random)
	{
		return abilityCollections[level - 1].next(random);
	}
}

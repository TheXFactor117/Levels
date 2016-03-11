package com.thexfactor117.levels.handlers;

import java.util.Random;

import com.thexfactor117.levels.helpers.EnemyLevel;
import com.thexfactor117.levels.helpers.EnumAttributeModifierOperations;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

/**
 * 
 * @author TheXFactor117
 *
 */
public class ExtendedMob implements IExtendedEntityProperties
{
	public static final String EXTENDED_PROPERTIES = "ExtendedMob";
	private final EntityMob entity;
	private EnemyLevel level;
	
	public ExtendedMob(EntityMob mob)
	{
		this.entity = mob;
		NBTTagCompound nbt = mob.getEntityData();
		
		if (mob != null)
		{
			if (nbt != null)
			{
				this.level = EnemyLevel.getEnemyLevel(nbt);
				Random rand = mob.worldObj.rand;
				
				if (this.level == EnemyLevel.DEFAULT)
				{
					level = EnemyLevel.getRandomLevel(rand);
					//this.setAttributeModifiers();
				}
			}
		}
	}
	
	public static final void register(EntityMob mob)
	{
		mob.registerExtendedProperties(EXTENDED_PROPERTIES, new ExtendedMob(mob));
	}
	
	public static final ExtendedMob get(EntityMob mob)
	{
		return (ExtendedMob) mob.getExtendedProperties(EXTENDED_PROPERTIES);
	}
	
	@Override
	public void saveNBTData(NBTTagCompound nbt)
	{
		nbt = new NBTTagCompound();
		level.setEnemyLevel(nbt);
		nbt.setTag(EXTENDED_PROPERTIES, nbt);
	}
	
	@Override
	public void loadNBTData(NBTTagCompound compound)
	{
		NBTTagCompound nbt = (NBTTagCompound) compound.getTag(EXTENDED_PROPERTIES);
		level = EnemyLevel.getEnemyLevel(nbt);
	}
	
	@Override
	public void init(Entity entity, World world) {}
	
	public EnemyLevel getEnemyLevelFromProps()
	{
		return level;
	}
	
	public void setAttributeModifiers()
	{
		if (level == EnemyLevel.WEAKENED)
		{
			AttributeModifier weakenedMaxHealth = new AttributeModifier("weakenedMaxHealth", -0.25D, EnumAttributeModifierOperations.ADD_PERC_VAL_TO_SUM.ordinal());
			entity.getEntityAttribute(SharedMonsterAttributes.maxHealth).applyModifier(weakenedMaxHealth);
			AttributeModifier weakenedDamageBoost = new AttributeModifier("weakenedDamageBoost", -0.25D, EnumAttributeModifierOperations.ADD_PERC_VAL_TO_SUM.ordinal());
			entity.getEntityAttribute(SharedMonsterAttributes.attackDamage).applyModifier(weakenedDamageBoost);
		}
		
		if (level == EnemyLevel.HARDENED)
		{
			AttributeModifier hardenedMaxHealth = new AttributeModifier("hardenedMaxHealth", 0.25D, EnumAttributeModifierOperations.ADD_PERC_VAL_TO_SUM.ordinal());
			entity.getEntityAttribute(SharedMonsterAttributes.maxHealth).applyModifier(hardenedMaxHealth);
		}
		
		if (level == EnemyLevel.SUPERIOR)
		{
			AttributeModifier superiorMaxHealth = new AttributeModifier("superiorMaxHealth", 0.3D, EnumAttributeModifierOperations.ADD_PERC_VAL_TO_SUM.ordinal());
			entity.getEntityAttribute(SharedMonsterAttributes.maxHealth).applyModifier(superiorMaxHealth);
			AttributeModifier superiorDamageBoost = new AttributeModifier("superiorDamageBoost", 0.25D, EnumAttributeModifierOperations.ADD_PERC_VAL_TO_SUM.ordinal());
			entity.getEntityAttribute(SharedMonsterAttributes.attackDamage).applyModifier(superiorDamageBoost);
		}
		
		if (level == EnemyLevel.ELITE)
		{
			AttributeModifier eliteMaxHealth = new AttributeModifier("eliteMaxHealth", 0.35D, EnumAttributeModifierOperations.ADD_PERC_VAL_TO_SUM.ordinal());
			entity.getEntityAttribute(SharedMonsterAttributes.maxHealth).applyModifier(eliteMaxHealth);
			AttributeModifier eliteDamageBoost = new AttributeModifier("eliteDamageBoost", 0.3D, EnumAttributeModifierOperations.ADD_PERC_VAL_TO_SUM.ordinal());
			entity.getEntityAttribute(SharedMonsterAttributes.attackDamage).applyModifier(eliteDamageBoost);
		}
		
		if (level == EnemyLevel.LEGENDARY)
		{
			AttributeModifier legendaryMaxHealth = new AttributeModifier("legendaryMaxHealth", 0.5D, EnumAttributeModifierOperations.ADD_PERC_VAL_TO_SUM.ordinal());
			entity.getEntityAttribute(SharedMonsterAttributes.maxHealth).applyModifier(legendaryMaxHealth);
			AttributeModifier legendaryDamageBoost = new AttributeModifier("legendaryDamageBoost", 0.5D, EnumAttributeModifierOperations.ADD_PERC_VAL_TO_SUM.ordinal());
			entity.getEntityAttribute(SharedMonsterAttributes.attackDamage).applyModifier(legendaryDamageBoost);
		}
	}
}

package com.thexfactor117.levels.events;

import java.util.Random;

import com.thexfactor117.levels.helpers.EnemyLevel;
import com.thexfactor117.levels.helpers.EnumAttributeModifierOperations;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;

/**
 * 
 * @author TheXFactor117
 *
 */
public class EventLivingSpawn 
{
	@SubscribeEvent
	public void onEntitySpawn(LivingSpawnEvent event)
	{
		if (event.entity instanceof EntityMob)
		{
			EntityMob entity = (EntityMob) event.entity;
			Random rand = event.world.rand;
			NBTTagCompound nbt = event.entity.getEntityData();
			
			if (nbt != null)
			{
				EnemyLevel level = EnemyLevel.getEnemyLevel(nbt);
				
				if (level == EnemyLevel.NORMAL)
				{
					level = EnemyLevel.getRandomLevel(rand);
					level.setEnemyLevel(nbt);
										
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
		}
	}
}

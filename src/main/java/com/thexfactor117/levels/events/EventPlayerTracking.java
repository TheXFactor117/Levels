package com.thexfactor117.levels.events;

import java.util.UUID;

import com.thexfactor117.levels.Levels;
import com.thexfactor117.levels.capabilities.CapabilityEnemyLevel;
import com.thexfactor117.levels.capabilities.IEnemyLevel;
import com.thexfactor117.levels.leveling.EnemyLevel;
import com.thexfactor117.levels.network.PacketEnemyLevel;
import com.thexfactor117.levels.utils.EnumAttributeModifierOperations;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.monster.EntityMob;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * 
 * @author TheXFactor117
 *
 */
public class EventPlayerTracking 
{
	@SubscribeEvent
	public void onPlayerTracking(PlayerEvent.StartTracking event)
	{
		if (event.getTarget() instanceof EntityMob)
		{			
			if (!event.getTarget().worldObj.isRemote)
			{
				EntityMob mob = (EntityMob) event.getTarget();
				
				if (mob != null)
				{
					IEnemyLevel enemyLevel = mob.getCapability(CapabilityEnemyLevel.ENEMY_LEVEL_CAP, null);
					
					if (enemyLevel != null)
					{
						int level = enemyLevel.getEnemyLevel();
						
						if (enemyLevel.getEnemyLevel() > 0)
						{	
							Levels.network.sendToAll(new PacketEnemyLevel(enemyLevel.getEnemyLevel(), mob.getEntityId()));
							setAttributeModifiers(mob, level);
						}
						else
						{
							int newLevel = EnemyLevel.getRandomLevel(event.getEntity().worldObj.rand).ordinal();
							enemyLevel.setEnemyLevel(newLevel);
							Levels.network.sendToAll(new PacketEnemyLevel(enemyLevel.getEnemyLevel(), mob.getEntityId()));
							setAttributeModifiers(mob, newLevel);
						}
					}
				}
			}
		}
	}
	
	private void setAttributeModifiers(EntityMob mob, int level)
	{
		if (level == 1)
		{
			if (mob.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getModifier(UUID.fromString("e8da868e-5a50-4376-87b9-35701f9f937a")) == null && mob.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getModifier(UUID.fromString("5d17b4c2-2043-45a3-bbb8-b7da6b054a23")) == null)
			{
				AttributeModifier maxHealth = new AttributeModifier(UUID.fromString("e8da868e-5a50-4376-87b9-35701f9f937a"), "weakenedMaxHealth", -10D, EnumAttributeModifierOperations.ADD_VAL_TO_BASE.ordinal());
				mob.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).applyModifier(maxHealth);
				AttributeModifier damageBoost = new AttributeModifier(UUID.fromString("5d17b4c2-2043-45a3-bbb8-b7da6b054a23"), "weakenedDamageBoost", -2D, EnumAttributeModifierOperations.ADD_VAL_TO_BASE.ordinal());
				mob.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).applyModifier(damageBoost);
				mob.setHealth(mob.getMaxHealth());
				//Levels.LOGGER.info(mob + " mob health " + mob.getHealth() + " / " + mob.getMaxHealth());
			}
		}
		
		if (level == 3)
		{
			if (mob.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getModifier(UUID.fromString("3783c4c2-69b2-446a-b330-3fea42324e5a")) == null && mob.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getModifier(UUID.fromString("5dc62546-beda-4ab8-b57f-dfe3b421d218")) == null)
			{
				AttributeModifier maxHealth = new AttributeModifier(UUID.fromString("3783c4c2-69b2-446a-b330-3fea42324e5a"), "hardenedMaxHealth", 0.2D, EnumAttributeModifierOperations.ADD_PERC_VAL_TO_SUM.ordinal());
				mob.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).applyModifier(maxHealth);
				AttributeModifier damageBoost = new AttributeModifier(UUID.fromString("5dc62546-beda-4ab8-b57f-dfe3b421d218"), "hardenedDamageBoost", 0.2D, EnumAttributeModifierOperations.ADD_PERC_VAL_TO_SUM.ordinal());
				mob.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).applyModifier(damageBoost);
				mob.setHealth(mob.getMaxHealth());
				//Levels.LOGGER.info(mob + " mob health " + mob.getHealth() + " / " + mob.getMaxHealth());
			}
		}
		
		if (level == 4)
		{
			if (mob.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getModifier(UUID.fromString("cad20bba-28aa-4e14-809e-e90449a7ffc7")) == null && mob.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getModifier(UUID.fromString("9d539684-bf77-4de9-a13d-c30b4dd3937f")) == null)
			{
				AttributeModifier maxHealth = new AttributeModifier(UUID.fromString("cad20bba-28aa-4e14-809e-e90449a7ffc7"), "superiorMaxHealth", 0.4D, EnumAttributeModifierOperations.ADD_PERC_VAL_TO_SUM.ordinal());
				mob.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).applyModifier(maxHealth);
				AttributeModifier damageBoost = new AttributeModifier(UUID.fromString("9d539684-bf77-4de9-a13d-c30b4dd3937f"), "superiorDamageBoost", 0.4D, EnumAttributeModifierOperations.ADD_PERC_VAL_TO_SUM.ordinal());
				mob.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).applyModifier(damageBoost);
				mob.setHealth(mob.getMaxHealth());
				//Levels.LOGGER.info(mob + " mob health " + mob.getHealth() + " / " + mob.getMaxHealth());
			}
		}
		
		if (level == 5)
		{
			if (mob.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getModifier(UUID.fromString("7c4eba52-941e-4900-9b75-ad4557688216")) == null && mob.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getModifier(UUID.fromString("76f62678-d362-453b-aa0a-883f32658f3d")) == null)
			{
				AttributeModifier maxHealth = new AttributeModifier(UUID.fromString("7c4eba52-941e-4900-9b75-ad4557688216"), "eliteMaxHealth", 0.6D, EnumAttributeModifierOperations.ADD_PERC_VAL_TO_SUM.ordinal());
				mob.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).applyModifier(maxHealth);
				AttributeModifier damageBoost = new AttributeModifier(UUID.fromString("76f62678-d362-453b-aa0a-883f32658f3d"), "eliteDamageBoost", 0.6D, EnumAttributeModifierOperations.ADD_PERC_VAL_TO_SUM.ordinal());
				mob.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).applyModifier(damageBoost);
				mob.setHealth(mob.getMaxHealth());
				//Levels.LOGGER.info(mob + " mob health " + mob.getHealth() + " / " + mob.getMaxHealth());
			}
		}
		
		if (level == 6)
		{
			if (mob.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getModifier(UUID.fromString("f9a1d8dc-7e17-4236-9113-aca7282003f6")) == null && mob.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getModifier(UUID.fromString("1354099c-c91a-4f02-a335-a9ad42a98f8a")) == null)
			{
				AttributeModifier maxHealth = new AttributeModifier(UUID.fromString("f9a1d8dc-7e17-4236-9113-aca7282003f6"), "legendaryMaxHealth", 0.8D, EnumAttributeModifierOperations.ADD_PERC_VAL_TO_SUM.ordinal());
				mob.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).applyModifier(maxHealth);
				AttributeModifier damageBoost = new AttributeModifier(UUID.fromString("1354099c-c91a-4f02-a335-a9ad42a98f8a"), "legendaryDamageBoost", 0.8D, EnumAttributeModifierOperations.ADD_PERC_VAL_TO_SUM.ordinal());
				mob.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).applyModifier(damageBoost);
				mob.setHealth(mob.getMaxHealth());
				//Levels.LOGGER.info(mob + " mob health " + mob.getHealth() + " / " + mob.getMaxHealth());
			}
		}
	}
}

package com.thexfactor117.levels.events;

import java.util.UUID;

import com.thexfactor117.levels.handlers.ExtendedMob;
import com.thexfactor117.levels.helpers.EnemyLevel;
import com.thexfactor117.levels.helpers.EnumAttributeModifierOperations;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.monster.EntityMob;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

/**
 * 
 * @author TheXFactor117
 *
 */
public class EventEntityJoinWorld 
{
	/**
	 * Called whenever an entity is loaded into the world. Used to modify the attributes of monsters whenever they are spawned.
	 * @param event
	 */
	@SubscribeEvent
	public void onEntitySpawn(EntityJoinWorldEvent event)
	{
		if (event.entity instanceof EntityMob)
		{
			if (!event.entity.worldObj.isRemote)
			{
				EntityMob entity = (EntityMob) event.entity;
				ExtendedMob props = ExtendedMob.get(entity);
				EnemyLevel level = props.getEnemyLevelFromProps();
				
				if (level.equals(EnemyLevel.WEAKENED))
				{
					AttributeModifier weakenedMaxHealth = new AttributeModifier(UUID.fromString("a700a0e4-b79a-44b6-a752-a94778bc62b3"), "weakenedMaxHealth", -10D, EnumAttributeModifierOperations.ADD_VAL_TO_BASE.ordinal());
					entity.getEntityAttribute(SharedMonsterAttributes.maxHealth).applyModifier(weakenedMaxHealth);
					AttributeModifier weakenedDamageBoost = new AttributeModifier(UUID.fromString("3cdd6b08-ae6f-47e9-aff1-b7bdddb99511"), "weakenedDamageBoost", -2D, EnumAttributeModifierOperations.ADD_VAL_TO_BASE.ordinal());
					entity.getEntityAttribute(SharedMonsterAttributes.attackDamage).applyModifier(weakenedDamageBoost);
					entity.setHealth(entity.getMaxHealth());
				}
				
				if (level == EnemyLevel.HARDENED)
				{
					AttributeModifier hardenedMaxHealth = new AttributeModifier(UUID.fromString("e787da96-9f24-4c45-9175-b004c1b10111"), "hardenedMaxHealth", 0.15, EnumAttributeModifierOperations.ADD_PERC_VAL_TO_SUM.ordinal());
					entity.getEntityAttribute(SharedMonsterAttributes.maxHealth).applyModifier(hardenedMaxHealth);
					entity.setHealth(entity.getMaxHealth());					
				}
				
				if (level == EnemyLevel.SUPERIOR)
				{
					AttributeModifier superiorMaxHealth = new AttributeModifier(UUID.fromString("d0ffbfdb-10b4-444f-a613-e1d2d5598dee"), "superiorMaxHealth", 0.3D, EnumAttributeModifierOperations.ADD_PERC_VAL_TO_SUM.ordinal());
					entity.getEntityAttribute(SharedMonsterAttributes.maxHealth).applyModifier(superiorMaxHealth);
					AttributeModifier superiorDamageBoost = new AttributeModifier(UUID.fromString("8f21e85f-bfed-4bdb-acf5-31562e5229d7"), "superiorDamageBoost", 0.3D, EnumAttributeModifierOperations.ADD_PERC_VAL_TO_SUM.ordinal());
					entity.getEntityAttribute(SharedMonsterAttributes.attackDamage).applyModifier(superiorDamageBoost);
					entity.setHealth(entity.getMaxHealth());
				}
				
				if (level == EnemyLevel.ELITE)
				{
					AttributeModifier eliteMaxHealth = new AttributeModifier(UUID.fromString("92b9eb29-304f-4e6b-90fd-55ccffe7a40b"), "eliteMaxHealth", 0.5D, EnumAttributeModifierOperations.ADD_PERC_VAL_TO_SUM.ordinal());
					entity.getEntityAttribute(SharedMonsterAttributes.maxHealth).applyModifier(eliteMaxHealth);
					AttributeModifier eliteDamageBoost = new AttributeModifier(UUID.fromString("a7f4074f-51a4-472d-afc2-2586efeb2041"), "eliteDamageBoost", 0.5D, EnumAttributeModifierOperations.ADD_PERC_VAL_TO_SUM.ordinal());
					entity.getEntityAttribute(SharedMonsterAttributes.attackDamage).applyModifier(eliteDamageBoost);
					entity.setHealth(entity.getMaxHealth());
				}
				
				if (level == EnemyLevel.LEGENDARY)
				{
					AttributeModifier legendaryMaxHealth = new AttributeModifier(UUID.fromString("5dd6888e-0a54-424f-a4ec-71dd1a92fd89"), "legendaryMaxHealth", 0.75D, EnumAttributeModifierOperations.ADD_PERC_VAL_TO_SUM.ordinal());
					entity.getEntityAttribute(SharedMonsterAttributes.maxHealth).applyModifier(legendaryMaxHealth);
					AttributeModifier legendaryDamageBoost = new AttributeModifier(UUID.fromString("644515b8-d3c4-4342-a9f6-7e777bbded6f"), "legendaryDamageBoost", 0.75D, EnumAttributeModifierOperations.ADD_PERC_VAL_TO_SUM.ordinal());
					entity.getEntityAttribute(SharedMonsterAttributes.attackDamage).applyModifier(legendaryDamageBoost);
					entity.setHealth(entity.getMaxHealth());
				}
			}
		}
	}
}

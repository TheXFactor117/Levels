package com.thexfactor117.levels.events;

import java.util.UUID;

import com.thexfactor117.levels.handlers.ConfigHandler;
import com.thexfactor117.levels.handlers.ExtendedMob;
import com.thexfactor117.levels.helpers.EnemyLevel;
import com.thexfactor117.levels.helpers.EnumAttributeModifierOperations;

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
	@net.minecraftforge.fml.common.eventhandler.SubscribeEvent
	public void onEntitySpawn(EntityJoinWorldEvent event)
	{
		if (event.getEntity() instanceof EntityMob)
		{			
			if (!event.getEntity().worldObj.isRemote)
			{
				EntityMob entity = (EntityMob) event.getEntity();
				ExtendedMob props = ExtendedMob.get(entity);
				EnemyLevel level = props.getEnemyLevelFromProps();
				
				if (level.equals(EnemyLevel.WEAKENED))
				{
					if (entity.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getModifier(UUID.fromString("a700a0e4-b79a-44b6-a752-a94778bc62b3")) == null && entity.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getModifier(UUID.fromString("3cdd6b08-ae6f-47e9-aff1-b7bdddb99511")) == null)
					{
						AttributeModifier weakenedMaxHealth = new AttributeModifier(UUID.fromString("a700a0e4-b79a-44b6-a752-a94778bc62b3"), "weakenedMaxHealth", ConfigHandler.weakMaxHealth, EnumAttributeModifierOperations.ADD_VAL_TO_BASE.ordinal());
						entity.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).applyModifier(weakenedMaxHealth);
						AttributeModifier weakenedDamageBoost = new AttributeModifier(UUID.fromString("3cdd6b08-ae6f-47e9-aff1-b7bdddb99511"), "weakenedDamageBoost", ConfigHandler.weakDamage, EnumAttributeModifierOperations.ADD_VAL_TO_BASE.ordinal());
						entity.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).applyModifier(weakenedDamageBoost);
						entity.setHealth(entity.getMaxHealth());
					}
				}
				
				if (level == EnemyLevel.HARDENED)
				{
					if (entity.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getModifier(UUID.fromString("e787da96-9f24-4c45-9175-b004c1b10111")) == null)					
					{
						AttributeModifier hardenedMaxHealth = new AttributeModifier(UUID.fromString("e787da96-9f24-4c45-9175-b004c1b10111"), "hardenedMaxHealth", ConfigHandler.hardenedMaxHealth, EnumAttributeModifierOperations.ADD_PERC_VAL_TO_SUM.ordinal());
						entity.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).applyModifier(hardenedMaxHealth);
						entity.setHealth(entity.getMaxHealth());
					}
				}
				
				if (level == EnemyLevel.SUPERIOR)
				{
					if (entity.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getModifier(UUID.fromString("d0ffbfdb-10b4-444f-a613-e1d2d5598dee")) == null && entity.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getModifier(UUID.fromString("8f21e85f-bfed-4bdb-acf5-31562e5229d7")) == null)
					{
						AttributeModifier superiorMaxHealth = new AttributeModifier(UUID.fromString("d0ffbfdb-10b4-444f-a613-e1d2d5598dee"), "superiorMaxHealth", ConfigHandler.superiorMaxHealth, EnumAttributeModifierOperations.ADD_PERC_VAL_TO_SUM.ordinal());
						entity.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).applyModifier(superiorMaxHealth);
						AttributeModifier superiorDamageBoost = new AttributeModifier(UUID.fromString("8f21e85f-bfed-4bdb-acf5-31562e5229d7"), "superiorDamageBoost", ConfigHandler.superiorDamage, EnumAttributeModifierOperations.ADD_PERC_VAL_TO_SUM.ordinal());
						entity.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).applyModifier(superiorDamageBoost);
						entity.setHealth(entity.getMaxHealth());
					}
				}
				
				if (level == EnemyLevel.ELITE)
				{
					if (entity.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getModifier(UUID.fromString("92b9eb29-304f-4e6b-90fd-55ccffe7a40b")) == null && entity.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getModifier(UUID.fromString("a7f4074f-51a4-472d-afc2-2586efeb2041")) == null)
					{
						AttributeModifier eliteMaxHealth = new AttributeModifier(UUID.fromString("92b9eb29-304f-4e6b-90fd-55ccffe7a40b"), "eliteMaxHealth", ConfigHandler.eliteMaxHealth, EnumAttributeModifierOperations.ADD_PERC_VAL_TO_SUM.ordinal());
						entity.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).applyModifier(eliteMaxHealth);
						AttributeModifier eliteDamageBoost = new AttributeModifier(UUID.fromString("a7f4074f-51a4-472d-afc2-2586efeb2041"), "eliteDamageBoost", ConfigHandler.eliteDamage, EnumAttributeModifierOperations.ADD_PERC_VAL_TO_SUM.ordinal());
						entity.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).applyModifier(eliteDamageBoost);
						entity.setHealth(entity.getMaxHealth());
					}
				}
				
				if (level == EnemyLevel.LEGENDARY)
				{
					if (entity.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getModifier(UUID.fromString("5dd6888e-0a54-424f-a4ec-71dd1a92fd89")) == null && entity.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getModifier(UUID.fromString("644515b8-d3c4-4342-a9f6-7e777bbded6f")) == null)
					{
						AttributeModifier legendaryMaxHealth = new AttributeModifier(UUID.fromString("5dd6888e-0a54-424f-a4ec-71dd1a92fd89"), "legendaryMaxHealth", ConfigHandler.legendaryMaxHealth, EnumAttributeModifierOperations.ADD_PERC_VAL_TO_SUM.ordinal());
						entity.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).applyModifier(legendaryMaxHealth);
						AttributeModifier legendaryDamageBoost = new AttributeModifier(UUID.fromString("644515b8-d3c4-4342-a9f6-7e777bbded6f"), "legendaryDamageBoost", ConfigHandler.legendaryDamage, EnumAttributeModifierOperations.ADD_PERC_VAL_TO_SUM.ordinal());
						entity.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).applyModifier(legendaryDamageBoost);
						entity.setHealth(entity.getMaxHealth());
					}
				}
			}
		}
	}
}

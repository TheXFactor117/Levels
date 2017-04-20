package com.thexfactor117.levels.util;

import java.util.Collection;
import java.util.Random;
import java.util.UUID;

import com.google.common.collect.Multimap;
import com.thexfactor117.levels.Levels;
import com.thexfactor117.levels.leveling.Rarity;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

/**
 * 
 * @author TheXFactor117
 * 
 * Helper class for creating attributes for weapons and armor.
 * 
 */
public class WeaponHelper 
{
	private static final UUID ATTACK_DAMAGE = UUID.fromString("38d403d3-3e25-4638-957f-71cd25273933");
	private static final UUID ATTACK_SPEED = UUID.fromString("106410b5-3fa8-4fcf-8252-ca4292dc0391");
	
	public static void createWeapon(ItemStack stack)
	{
		NBTTagCompound nbt = NBTHelper.loadStackNBT(stack);
		
		if (nbt != null)
		{
			Rarity rarity = Rarity.getRarity(nbt);
			
			if (rarity == Rarity.DEFAULT)
			{				
				ItemSword sword = (ItemSword) stack.getItem();
				Rarity.setRarity(nbt, Rarity.getRandomRarity(new Random())); // sets random rarity
				
				if (Config.unlimitedDurability)
				{
					nbt.setInteger("Unbreakable", 1); // adds Unbreakable tag to item
				}
				
				nbt.setDouble("Multiplier", getWeightedMultiplier(Rarity.getRarity(nbt)));
				nbt.setInteger("HideFlags", 6); // hides Attribute Modifier and Unbreakable tags
				setAttributeModifiers(nbt, sword); // sets up Attribute Modifiers
				NBTHelper.saveStackNBT(stack, nbt);
				stack.setStackDisplayName(generateName(stack, Rarity.getRarity(nbt)));
			}
		}
	}
	
	/**
	 * Creates a new Attribute Modifier tag list and adds it to the NBTTagCompound. Overrides default vanilla implementation.
	 * @param nbt
	 * @param sword
	 */
	private static void setAttributeModifiers(NBTTagCompound nbt, ItemSword sword)
	{
		// retrieves the default attributes, like damage and attack speed.
		Multimap<String, AttributeModifier> map = sword.getItemAttributeModifiers(EntityEquipmentSlot.MAINHAND);
		Collection<AttributeModifier> damageCollection = map.get(SharedMonsterAttributes.ATTACK_DAMAGE.getName());
		Collection<AttributeModifier> speedCollection = map.get(SharedMonsterAttributes.ATTACK_SPEED.getName());
		AttributeModifier damageModifier = (AttributeModifier) damageCollection.toArray()[0];
		AttributeModifier speedModifier = (AttributeModifier) speedCollection.toArray()[0];
		
		double baseDamage = damageModifier.getAmount() + 1; // add one to base damage for player strength
		double baseSpeed = speedModifier.getAmount();
		double damage = getWeightedDamage(Rarity.getRarity(nbt), baseDamage);
		double speed = getWeightedAttackSpeed(Rarity.getRarity(nbt), baseSpeed);

		// Creates new AttributeModifier's and applies them to the stack's NBT tag compound.
		AttributeModifier attackDamage = new AttributeModifier(ATTACK_DAMAGE, "attackDamage", damage, 0);
		AttributeModifier attackSpeed = new AttributeModifier(ATTACK_SPEED, "attackSpeed", speed, 0);
		NBTTagCompound damageNbt = writeAttributeModifierToNBT(SharedMonsterAttributes.ATTACK_DAMAGE, attackDamage, EntityEquipmentSlot.MAINHAND);
		NBTTagCompound speedNbt = writeAttributeModifierToNBT(SharedMonsterAttributes.ATTACK_SPEED, attackSpeed, EntityEquipmentSlot.MAINHAND);
		NBTTagList list = new NBTTagList();
		list.appendTag(damageNbt);
		list.appendTag(speedNbt);
		nbt.setTag("AttributeModifiers", list);
	}
	
	/**
	 * Returns a damage value based on the rarity and base damage of the weapon.
	 * @param rarity
	 * @param baseDamage
	 * @return
	 */
	private static double getWeightedDamage(Rarity rarity, double baseDamage)
	{
		double damage = baseDamage;
		int range;
		
		switch (rarity)
		{
			case COMMON:
				range = 3;
				damage = Math.random() * range + (baseDamage - 2);
				break;
			case UNCOMMON:
				range = 4;
				damage = Math.random() * range + (baseDamage - 1);
				break;
			case RARE:
				range = 5;
				damage = Math.random() * range + (baseDamage + 1);
				break;
			case LEGENDARY:
				range = 6;
				damage = Math.random() * range + (baseDamage + 2);
				break;
			case MYTHIC:
				range = 7;
				damage = Math.random() * range + (baseDamage + 3);
				break;
			default:
				break;
		}
		
		return damage;
	}
	
	/**
	 * Returns an attack speed value based on the rarity and base attack speed of the weapon.
	 * @param rarity
	 * @param baseAttackSpeed
	 * @return
	 */
	private static double getWeightedAttackSpeed(Rarity rarity, double baseAttackSpeed)
	{
		double attackSpeed = baseAttackSpeed;
		double range;
		
		switch (rarity)
		{
			case COMMON:
				range = 0.2;
				attackSpeed = Math.random() * range + (baseAttackSpeed - 0.2);
				break;
			case UNCOMMON:
				range = 0.3;
				attackSpeed = Math.random() * range + (baseAttackSpeed - 0.1);
				break;
			case RARE:
				range = 0.4;
				attackSpeed = Math.random() * range + (baseAttackSpeed + 0.1);
				break;
			case LEGENDARY:
				range = 0.5;
				attackSpeed = Math.random() * range + (baseAttackSpeed + 0.2);
				break;
			case MYTHIC:
				range = 0.6;
				attackSpeed = Math.random() * range + (baseAttackSpeed + 0.3);
				break;
			default:
				break;
		}
		
		return attackSpeed;
	}
	
	/**
	 * Returns a randomized, weighted multiplier.
	 * @param rarity
	 * @return
	 */
	private static double getWeightedMultiplier(Rarity rarity)
	{
		double range = 0D;
		Levels.LOGGER.info(rarity);
		
		switch (rarity)
		{
			case COMMON:
				range = 0.05;
				break;
			case UNCOMMON:
				range = 0.08;
				break;
			case RARE:
				range = 0.13;
				break;
			case LEGENDARY:
				range = 0.2;
				break;
			case MYTHIC:
				range = 0.3;
				break;
			default:
				break;
		}
		Levels.LOGGER.info(range);
		double multiplier = Math.random() * range;
		Levels.LOGGER.info(multiplier);
				
		return multiplier;
	}
	
	private static String generateName(ItemStack stack, Rarity rarity)
	{
		//String prefix = "";
		//String suffix = "";
		
		return rarity.getColor() + stack.getDisplayName() + " of Awesome";
	}
	
	private static NBTTagCompound writeAttributeModifierToNBT(IAttribute attribute, AttributeModifier modifier, EntityEquipmentSlot slot) 
	{
		NBTTagCompound nbt = new NBTTagCompound();
		
		nbt.setString("AttributeName", attribute.getName());
		nbt.setString("Name", modifier.getName());
		nbt.setString("Slot", slot.getName());
		nbt.setDouble("Amount", modifier.getAmount());
		nbt.setInteger("Operation", modifier.getOperation());
		nbt.setLong("UUIDMost", modifier.getID().getMostSignificantBits());
		nbt.setLong("UUIDLeast", modifier.getID().getLeastSignificantBits());
		
		return nbt;
	}
}

package com.thexfactor117.levels.util;

import java.util.Collection;
import java.util.Random;
import java.util.UUID;

import com.google.common.collect.Multimap;
import com.thexfactor117.levels.Levels;
import com.thexfactor117.levels.capabilities.CapabilityBlacksmithing;
import com.thexfactor117.levels.capabilities.IBlacksmithing;
import com.thexfactor117.levels.config.Config;
import com.thexfactor117.levels.leveling.Experience;
import com.thexfactor117.levels.leveling.Rarity;
import com.thexfactor117.levels.network.PacketMythicSound;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.play.server.SPacketTitle;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

/**
 * 
 * @author TheXFactor117
 *
 */
public class WeaponHelper
{
	private static final UUID ATTACK_DAMAGE = UUID.fromString("38d403d3-3e25-4638-957f-71cd25273933");
	private static final UUID ATTACK_SPEED = UUID.fromString("106410b5-3fa8-4fcf-8252-ca4292dc0391");
	private static final UUID ARMOR = UUID.fromString("6ff9f9f0-0498-4623-aeca-a1afa64188e7");
	private static final UUID ARMOR_TOUGHNESS = UUID.fromString("245507c2-cb9d-4274-81ee-ecced32dafe4");
	
	public static void create(ItemStack stack, EntityPlayer player)
	{
		NBTTagCompound nbt = NBTHelper.loadStackNBT(stack);
		
		if (nbt != null)
		{
			Rarity rarity = Rarity.getRarity(nbt);
			Random rand = player.getEntityWorld().rand;
			IBlacksmithing blacksmithing = player.getCapability(CapabilityBlacksmithing.BLACKSMITHING_CAP, null);
			
			if (rarity == Rarity.DEFAULT && blacksmithing != null)
			{				
				Rarity.setRarity(nbt, Rarity.getRandomRarity(nbt, blacksmithing.getBlacksmithingRank(), rand)); // sets random rarity
				
				if (Rarity.getRarity(nbt) == Rarity.MYTHIC)
				{
					SPacketTitle packet = new SPacketTitle(SPacketTitle.Type.TITLE, new TextComponentString(TextFormatting.GOLD + "MYTHIC"), -1, 20, -1);
					EntityPlayerMP playermp = (EntityPlayerMP) player;
					playermp.connection.sendPacket(packet);
					Levels.network.sendTo(new PacketMythicSound(), (EntityPlayerMP) player);
				}
				
				if (Config.unlimitedDurability)
				{
					nbt.setInteger("Unbreakable", 1); // adds Unbreakable tag to item
				}
				
				Experience.setLevel(nbt, 1);
				nbt.setDouble("Multiplier", getWeightedMultiplier(Rarity.getRarity(nbt))); // adds a randomized multiplier to the item, weighted by rarity
				nbt.setInteger("HideFlags", 6); // hides Attribute Modifier and Unbreakable tags
				setAttributeModifiers(nbt, stack); // sets up Attribute Modifiers
				NBTHelper.saveStackNBT(stack, nbt);
			}
		}
	}
	
	/**
	 * Creates a new Attribute Modifier tag list and adds it to the NBTTagCompound. Overrides default vanilla implementation.
	 * @param nbt
	 * @param sword
	 */
	private static void setAttributeModifiers(NBTTagCompound nbt, ItemStack stack)
	{
		Item item = stack.getItem();
		
		if (item instanceof ItemSword || item instanceof ItemAxe)
		{
			// retrieves the default attributes, like damage and attack speed.
			@SuppressWarnings("deprecation")
			Multimap<String, AttributeModifier> map = item.getItemAttributeModifiers(EntityEquipmentSlot.MAINHAND);
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
		else if (item instanceof ItemArmor)
		{
			Multimap<String, AttributeModifier> map = ((ItemArmor) item).getAttributeModifiers(((ItemArmor) item).armorType, stack);
			Collection<AttributeModifier> armorCollection = map.get(SharedMonsterAttributes.ARMOR.getName());
			Collection<AttributeModifier> toughnessCollection = map.get(SharedMonsterAttributes.ARMOR_TOUGHNESS.getName());
			AttributeModifier armorModifier = (AttributeModifier) armorCollection.toArray()[0];
			AttributeModifier toughnessModifier = (AttributeModifier) toughnessCollection.toArray()[0];
			
			double baseArmor = armorModifier.getAmount();
			double baseToughness = toughnessModifier.getAmount();
			double newArmor = getWeightedArmor(Rarity.getRarity(nbt), baseArmor);
			double newToughness = getWeightedArmorToughness(Rarity.getRarity(nbt), baseToughness);
			
			// Creates new AttributeModifier's and applies them to the stack's NBT tag compound.
			AttributeModifier armor = new AttributeModifier(ARMOR, "armor", newArmor, 0);
			AttributeModifier toughness = new AttributeModifier(ARMOR_TOUGHNESS, "armorToughness", newToughness, 0);
			NBTTagCompound armorNbt = writeAttributeModifierToNBT(SharedMonsterAttributes.ARMOR, armor, ((ItemArmor) item).armorType);
			NBTTagCompound toughnessNbt = writeAttributeModifierToNBT(SharedMonsterAttributes.ARMOR_TOUGHNESS, toughness, ((ItemArmor) item).armorType);
			NBTTagList list = new NBTTagList();
			list.appendTag(armorNbt);
			list.appendTag(toughnessNbt);
			nbt.setTag("AttributeModifiers", list);
		}
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
	 * Returns an armor value based on the rarity and base armor of the armor.
	 * @param rarity
	 * @param baseArmor
	 * @return
	 */
	private static double getWeightedArmor(Rarity rarity, double baseArmor)
	{
		double armor = baseArmor;
		double range;
		
		switch (rarity)
		{
			case COMMON:
				range = 0.2;
				armor = Math.random() * range + (baseArmor - 0.2);
				break;
			case UNCOMMON:
				range = 0.3;
				armor = Math.random() * range + (baseArmor - 0.1);
				break;
			case RARE:
				range = 0.4;
				armor = Math.random() * range + (baseArmor + 0.1);
				break;
			case LEGENDARY:
				range = 0.5;
				armor = Math.random() * range + (baseArmor + 0.2);
				break;
			case MYTHIC:
				range = 0.6;
				armor = Math.random() * range + (baseArmor + 0.3);
				break;
			default:
				break;
		}
		
		return armor;
	}
	
	/**
	 * Returns a toughness value based on the rarity and base toughness of the armor.
	 * @param rarity
	 * @param baseToughness
	 * @return
	 */
	private static double getWeightedArmorToughness(Rarity rarity, double baseToughness)
	{
		double toughness = baseToughness;
		double range;
		
		switch (rarity)
		{
			case COMMON:
				range = 0.2;
				toughness = Math.random() * range + (baseToughness - 0.2);
				break;
			case UNCOMMON:
				range = 0.3;
				toughness = Math.random() * range + (baseToughness - 0.1);
				break;
			case RARE:
				range = 0.4;
				toughness = Math.random() * range + (baseToughness + 0.1);
				break;
			case LEGENDARY:
				range = 0.5;
				toughness = Math.random() * range + (baseToughness + 0.2);
				break;
			case MYTHIC:
				range = 0.6;
				toughness = Math.random() * range + (baseToughness + 0.3);
				break;
			default:
				break;
		}
		
		if (toughness < 0)
			return 0;
		
		return toughness;
	}
	
	/**
	 * Returns a randomized, weighted multiplier.
	 * @param rarity
	 * @return
	 */
	private static double getWeightedMultiplier(Rarity rarity)
	{
		double range = 0D;
		
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
		
		double multiplier = Math.random() * range;
				
		return multiplier;
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

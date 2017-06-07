package com.thexfactor117.levels.event;

import com.thexfactor117.levels.config.Config;
import com.thexfactor117.levels.leveling.Attribute;
import com.thexfactor117.levels.leveling.Experience;
import com.thexfactor117.levels.leveling.Rarity;
import com.thexfactor117.levels.util.NBTHelper;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * 
 * @author TheXFactor117
 * 
 * Handles adding experience, leveling up, and using attributes.
 *
 */
public class EventAttack 
{
	/**
	 * Called every time a living entity attacks.
	 * @param event
	 */
	@SubscribeEvent
	public void onLivingHurt(LivingHurtEvent event)
	{
		if (event.getSource().getSourceOfDamage() instanceof EntityPlayer && !(event.getSource().getSourceOfDamage() instanceof FakePlayer))
		{
			// WEAPONS
			EntityPlayer player = (EntityPlayer) event.getSource().getSourceOfDamage();
			EntityLivingBase enemy = event.getEntityLiving();
			ItemStack stack = player.inventory.getCurrentItem();
			
			if (enemy != null && player != null)
			{
				if (stack.getItem() instanceof ItemSword || stack.getItem() instanceof ItemAxe)
				{
					NBTTagCompound nbt = NBTHelper.loadStackNBT(stack);
					
					if (nbt != null)
					{
						addExperience(nbt, stack, enemy);
						useRarity(nbt, stack, false);
						useAttributes(nbt, event, stack, player, enemy);
						attemptLevel(nbt, stack, player);
					}
				}
			}
		}
		else if (event.getSource().getSourceOfDamage() instanceof EntityLivingBase && event.getEntityLiving() instanceof EntityPlayer)
		{
			// ARMOR
			EntityPlayer player = (EntityPlayer) event.getEntityLiving();
			EntityLivingBase enemy = (EntityLivingBase) event.getSource().getSourceOfDamage();
			
			if (enemy != null && player != null)
			{
				for (ItemStack stack : player.inventory.armorInventory)
				{
					if (stack != null && stack.getItem() instanceof ItemArmor)
					{
						NBTTagCompound nbt = NBTHelper.loadStackNBT(stack);
						
						if (nbt != null)
						{
							addExperience(nbt, stack, enemy);
							useRarity(nbt, stack, false);
							useAttributes(nbt, event, stack, player, enemy);
							attemptLevel(nbt, stack, player);
						}
					}
				}
			}
		}
	}
	
	/**
	 * Called every time a living entity dies.
	 * @param event
	 */
	@SubscribeEvent
	public void onLivingDeath(LivingDeathEvent event)
	{
		if (event.getSource().getSourceOfDamage() instanceof EntityPlayer && !(event.getSource().getSourceOfDamage() instanceof FakePlayer))
		{
			// WEAPONS
			EntityPlayer player = (EntityPlayer) event.getSource().getSourceOfDamage();
			EntityLivingBase enemy = event.getEntityLiving();
			ItemStack stack = player.inventory.getCurrentItem();
			
			if (enemy != null && player != null)
			{
				if (stack.getItem() instanceof ItemSword || stack.getItem() instanceof ItemAxe)
				{
					NBTTagCompound nbt = NBTHelper.loadStackNBT(stack);
					
					if (nbt != null)
					{
						addExperience(nbt, stack, enemy);
						useRarity(nbt, stack, true);
						attemptLevel(nbt, stack, player);
					}
				}
			}
		}
	}

	/**
	 * Adds experience to the stack's NBT.
	 * @param nbt
	 * @param stack
	 * @param enemy
	 */
	private void addExperience(NBTTagCompound nbt, ItemStack stack, EntityLivingBase enemy)
	{
		if (Experience.getLevel(nbt) < Config.maxLevel)
		{
			boolean isDev = (Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");
			
			if (isDev)
			{
				Experience.setExperience(nbt, Experience.getExperience(nbt) + 200);
			}
			
			if (stack.getItem() instanceof ItemArmor)
			{
				int xp = 0;
				
				if (enemy.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE) != null)
				{
					xp = (int) (enemy.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue() * 0.5);
				}
				else
				{
					xp = (int) (enemy.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getAttributeValue() * 0.5);
				}
				
				Experience.setExperience(nbt, Experience.getExperience(nbt) + xp);
			}
			else
			{
				int xp = (int) (enemy.getMaxHealth() * 0.2);
				Experience.setExperience(nbt, Experience.getExperience(nbt) + xp);
			}
		}
	}
	
	/**
	 * Uses rarity bonuses, such as bonus experience or durability bonuses. 
	 * @param nbt
	 * @param stack
	 */
	private void useRarity(NBTTagCompound nbt, ItemStack stack, boolean death)
	{
		Rarity rarity = Rarity.getRarity(nbt);
		
		if (rarity != Rarity.DEFAULT)
		{
			int var;
			int var1;
			int var2;
			int var3;
			
			switch (rarity)
			{
				case UNCOMMON: // 6% chance of adding 1-3 experience points; 6% chance of not using durability
					if (stack.getItem() instanceof ItemArmor  || ((stack.getItem() instanceof ItemSword || stack.getItem() instanceof ItemAxe) && death))
					{
						var = (int) (Math.random() * 15);
						var1 = (int) (Math.random() * 3 + 1);
						if (var == 0) Experience.setExperience(nbt, Experience.getExperience(nbt) + var1);
					}
					
					if (!Config.unlimitedDurability && !death)
					{
						var2 = (int) (Math.random() * 15);
						if (var2 == 0) stack.setItemDamage(stack.getItemDamage() - 1);
					}
					
					break;

				case RARE: // 10% chance of adding 1-5 experience points; 10% chance of not using durability AND gaining an additional durability point
					if (stack.getItem() instanceof ItemArmor  || ((stack.getItem() instanceof ItemSword || stack.getItem() instanceof ItemAxe) && death))
					{
						var = (int) (Math.random() * 10);
						var1 = (int) (Math.random() * 5 + 1);
						if (var == 0) Experience.setExperience(nbt, Experience.getExperience(nbt) + var1);
					}
					
					if (!Config.unlimitedDurability && !death)
					{
						var2 = (int) (Math.random() * 10);
						var3 = (int) (Math.random() * 2);
						if (var2 == 0) stack.setItemDamage(stack.getItemDamage() - (1 + var3));
					}
					
					break;
				case LEGENDARY: // 14% chance of adding 3-5 experience points; 14% chance of not using durability AND gaining 1-3 durability points
					if (stack.getItem() instanceof ItemArmor  || ((stack.getItem() instanceof ItemSword || stack.getItem() instanceof ItemAxe) && death))
					{
						var = (int) (Math.random() * 7);
						var1 = (int) (Math.random() * 5 + 3);
						if (var == 0) Experience.setExperience(nbt, Experience.getExperience(nbt) + var1);
					}
					
					if (!Config.unlimitedDurability && !death)
					{
						var2 = (int) (Math.random() * 7);
						var3 = (int) (Math.random() * 3 + 1);
						if (var2 == 0) stack.setItemDamage(stack.getItemDamage() - (1 + var3));
					}
					
					break;
				case MYTHIC: // 20% chance of adding 3-10 experience points; 20% chance of not using durability AND gaining 1-5 durability points
					if (stack.getItem() instanceof ItemArmor  || ((stack.getItem() instanceof ItemSword || stack.getItem() instanceof ItemAxe) && death))
					{
						var = (int) (Math.random() * 5);
						var1 = (int) (Math.random() * 8 + 3);
						if (var == 0) Experience.setExperience(nbt, Experience.getExperience(nbt) + var1);
					}
					
					if (!Config.unlimitedDurability && !death)
					{
						var2 = (int) (Math.random() * 5);
						var3 = (int) (Math.random() * 5 + 1);
						if (var2 == 0) stack.setItemDamage(stack.getItemDamage() - (1 + var3));
					}
					
					break;
				default:
					break;
			}
		}
	}
	
	/**
	 * Uses any attributes the stack currently has.
	 * @param nbt
	 * @param event
	 * @param stack
	 * @param player
	 * @param enemy
	 */
	private void useAttributes(NBTTagCompound nbt, LivingHurtEvent event, ItemStack stack, EntityPlayer player, EntityLivingBase enemy)
	{
		if (enemy != null)
		{
			if (Attribute.FIRE.hasAttribute(nbt) && Attribute.FIRE.isActive(nbt) && (int) (Math.random() * 4) == 0) enemy.setFire(5); // 25% chance
			if (Attribute.FROST.hasAttribute(nbt) && Attribute.FROST.isActive(nbt) && (int) (Math.random() * 4) == 0) enemy.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 20, 10)); // 25% chance; 1 second stun
			if (Attribute.DURABLE.hasAttribute(nbt) && Attribute.DURABLE.isActive(nbt) && (int) (Math.random() * 4) == 0) stack.setItemDamage(stack.getItemDamage() - 1); // 25% chance; doesn't use durability
			if (Attribute.ABSORB.hasAttribute(nbt) && Attribute.ABSORB.isActive(nbt) && (int) (Math.random() * 5) == 0) player.setHealth(player.getHealth() + (event.getAmount() / 2)); // 14% chance; returns half the damage dealt back as health
			if (Attribute.VOID.hasAttribute(nbt) && Attribute.VOID.isActive(nbt) && (int) (Math.random() * 15) == 0) enemy.setHealth(0.01F); // 5% chance; sets enemies health to something small, so damage kills enemy in one hit
		}
	}
	
	/**
	 * Attempts to level up the current stack.
	 * @param stack
	 * @param player
	 */
	private void attemptLevel(NBTTagCompound nbt, ItemStack stack, EntityPlayer player)
	{
		Experience.levelUp(player, stack);
		NBTHelper.saveStackNBT(stack, nbt);
	}
}

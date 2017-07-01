package com.thexfactor117.levels.events;

import java.util.Iterator;
import java.util.List;

import com.thexfactor117.levels.config.Config;
import com.thexfactor117.levels.leveling.Experience;
import com.thexfactor117.levels.leveling.Rarity;
import com.thexfactor117.levels.leveling.attributes.ArmorAttribute;
import com.thexfactor117.levels.leveling.attributes.BowAttribute;
import com.thexfactor117.levels.leveling.attributes.ShieldAttribute;
import com.thexfactor117.levels.leveling.attributes.WeaponAttribute;
import com.thexfactor117.levels.util.NBTHelper;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemShield;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * 
 * @author TheXFactor117
 *
 */
public class EventAttack 
{
	@SubscribeEvent
	public void onAttack(LivingHurtEvent event)
	{
		if (event.getSource().getSourceOfDamage() instanceof EntityPlayer && !(event.getSource().getSourceOfDamage() instanceof FakePlayer))
		{
			EntityPlayer player = (EntityPlayer) event.getSource().getSourceOfDamage();
			EntityLivingBase enemy = event.getEntityLiving();
			ItemStack stack = player.inventory.getCurrentItem();
			NBTTagCompound nbt = NBTHelper.loadStackNBT(stack);
			
			if (stack != null && nbt != null && stack.getItem() instanceof ItemSword)
			{
				addExperience(nbt, stack, enemy);
				useRarity(nbt, stack, false);
				useAttributes(nbt, event, stack, player, enemy);
				attemptLevel(nbt, stack, player);
			}
		}
		else if (event.getSource().getSourceOfDamage() instanceof EntityLivingBase && event.getEntityLiving() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.getEntityLiving();
			EntityLivingBase enemy = (EntityLivingBase) event.getSource().getSourceOfDamage();
			
			if (enemy != null && player != null)
			{
				for (ItemStack stack : player.inventory.armorInventory)
				{
					NBTTagCompound nbt = NBTHelper.loadStackNBT(stack);
					
					if (stack != null && nbt != null && stack.getItem() instanceof ItemArmor)
					{
						addExperience(nbt, stack, enemy);
						useRarity(nbt, stack, false);
						useAttributes(nbt, event, stack, player, enemy);
						attemptLevel(nbt, stack, player);
					}
				}
				
				if (player.inventory.offHandInventory.get(0).getItem() instanceof ItemShield)
				{
					ItemStack stack = player.inventory.offHandInventory.get(0);
					NBTTagCompound nbt = NBTHelper.loadStackNBT(stack);
					
					if (stack != null && nbt != null && player.getActiveHand() == EnumHand.OFF_HAND)
					{
						addExperience(nbt, stack, enemy);
						useRarity(nbt, stack, false);
						useAttributes(nbt, event, stack, player, enemy);
						attemptLevel(nbt, stack, player);
					}
				}
			}
		}
		else if (event.getSource().getSourceOfDamage() instanceof EntityArrow)
		{
			EntityArrow arrow = (EntityArrow) event.getSource().getSourceOfDamage();
			
			if (arrow.shootingEntity instanceof EntityPlayer)
			{
				EntityPlayer player = (EntityPlayer) arrow.shootingEntity;
				EntityLivingBase enemy = event.getEntityLiving();
				ItemStack stack = player.inventory.getCurrentItem();
				NBTTagCompound nbt = NBTHelper.loadStackNBT(stack);
				
				if (player != null && enemy != null && stack != null && nbt != null && stack.getItem() instanceof ItemBow)
				{
					addExperience(nbt, stack, enemy);
					useRarity(nbt, stack, false);
					useAttributes(nbt, event, stack, player, enemy);
					attemptLevel(nbt, stack, player);
				}
			}
			else if (arrow.shootingEntity instanceof EntityLivingBase)
			{
				EntityPlayer player = (EntityPlayer) event.getEntityLiving();
				EntityLivingBase enemy = (EntityLivingBase) arrow.shootingEntity;
				
				if (player != null && enemy != null)
				{
					for (ItemStack stack : player.inventory.armorInventory)
					{
						NBTTagCompound nbt = NBTHelper.loadStackNBT(stack);
						
						if (stack != null && nbt != null && stack.getItem() instanceof ItemArmor)
						{
							addExperience(nbt, stack, enemy);
							useRarity(nbt, stack, false);
							useAttributes(nbt, event, stack, player, enemy);
							attemptLevel(nbt, stack, player);
						}
					}
					
					if (player.inventory.offHandInventory.get(0).getItem() instanceof ItemShield)
					{
						ItemStack stack = player.inventory.offHandInventory.get(0);
						NBTTagCompound nbt = NBTHelper.loadStackNBT(stack);
						
						if (stack != null && nbt != null && player.getActiveHand() == EnumHand.OFF_HAND)
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
			EntityPlayer player = (EntityPlayer) event.getSource().getSourceOfDamage();
			EntityLivingBase enemy = event.getEntityLiving();
			ItemStack stack = player.inventory.getCurrentItem();
			NBTTagCompound nbt = NBTHelper.loadStackNBT(stack);
			
			if (player != null && enemy != null && stack != null && nbt != null && stack.getItem() instanceof ItemSword)
			{
				addExperience(nbt, stack, enemy);
				useRarity(nbt, stack, true);
				attemptLevel(nbt, stack, player);
			}
		}
		else if (event.getSource().getSourceOfDamage() instanceof EntityArrow)
		{
			EntityArrow arrow = (EntityArrow) event.getSource().getSourceOfDamage();
			
			if (arrow.shootingEntity instanceof EntityPlayer)
			{
				EntityPlayer player = (EntityPlayer) arrow.shootingEntity;
				EntityLivingBase enemy = event.getEntityLiving();
				ItemStack stack = player.inventory.getCurrentItem();
				NBTTagCompound nbt = NBTHelper.loadStackNBT(stack);
				
				if (player != null && enemy != null && stack != null && nbt != null && stack.getItem() instanceof ItemBow)
				{
					addExperience(nbt, stack, enemy);
					useRarity(nbt, stack, true);
					attemptLevel(nbt, stack, player);
					
					if (BowAttribute.RECOVER.hasAttribute(nbt))
					{
						enemy.dropItem(Items.ARROW, (int) (Math.random() * 2));
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
			// DEV
			boolean isDev = (Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");
			
			if (isDev)
			{
				Experience.setExperience(nbt, Experience.getExperience(nbt) + 200);
			}
			
			// WEAPONS AND BOW
			if (stack.getItem() instanceof ItemSword || stack.getItem() instanceof ItemBow)
			{
				int xp = (int) (enemy.getMaxHealth() * 0.2);
				Experience.setExperience(nbt, Experience.getExperience(nbt) + xp);
			}
			
			// ARMOR AND SHIELD
			if (stack.getItem() instanceof ItemArmor || stack.getItem() instanceof ItemShield)
			{
				int xp = 0;
				
				// Default to use Attack Damage if available; uses Health if Attack Damage doesn't exist.
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
					if ((stack.getItem() instanceof ItemArmor || stack.getItem() instanceof ItemShield) || ((stack.getItem() instanceof ItemSword || stack.getItem() instanceof ItemBow) && death))
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
					if ((stack.getItem() instanceof ItemArmor || stack.getItem() instanceof ItemShield) || ((stack.getItem() instanceof ItemSword || stack.getItem() instanceof ItemBow) && death))
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
					if ((stack.getItem() instanceof ItemArmor || stack.getItem() instanceof ItemShield) || ((stack.getItem() instanceof ItemSword || stack.getItem() instanceof ItemBow) && death))
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
					if ((stack.getItem() instanceof ItemArmor || stack.getItem() instanceof ItemShield) || ((stack.getItem() instanceof ItemSword || stack.getItem() instanceof ItemBow) && death))
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
		// WEAPONS
		if (stack.getItem() instanceof ItemSword)
		{
			if (WeaponAttribute.FIRE.hasAttribute(nbt) && (int) (Math.random() * 4) == 0) enemy.setFire((int) WeaponAttribute.FIRE.getCalculatedValue(nbt, 4, 1.25)); // 25% chance; tiers: (4 second, 5 second, 6 second)
			if (WeaponAttribute.FROST.hasAttribute(nbt) && (int) (Math.random() * 4) == 0) enemy.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, (int) WeaponAttribute.FROST.getCalculatedValue(nbt, 20, 1.5), 10)); // 25% chance; tiers: (1 second, 1.5 second, 2.25 second)
			if (WeaponAttribute.POISON.hasAttribute(nbt) && (int) (Math.random() * 4) == 0) enemy.addPotionEffect(new PotionEffect(MobEffects.POISON, (int) WeaponAttribute.POISON.getCalculatedValue(nbt, 20 * 7, 1.5), WeaponAttribute.POISON.getAttributeTier(nbt))); // 25% chance; tiers: (7 second, 10.5 second, 15.75 second)
			if (WeaponAttribute.DURABLE.hasAttribute(nbt) && (int) (Math.random() * 4) == 0) stack.setItemDamage(stack.getItemDamage() - (int) WeaponAttribute.DURABLE.getCalculatedValue(nbt, 1, 2)); // 25% chance; tiers: (1 point, 2 point, 4 point)
			if (WeaponAttribute.ABSORB.hasAttribute(nbt) && (int) (Math.random() * 5) == 0) player.setHealth(player.getHealth() + (float) (event.getAmount() * WeaponAttribute.ABSORB.getCalculatedValue(nbt, 0.25, 1.5))); // 14% chance; returns half the damage dealt back as health; tiers: (25%, 37.5%, 56.25%)
			if (WeaponAttribute.VOID.hasAttribute(nbt) && (int) (Math.random() * WeaponAttribute.VOID.getCalculatedValue(nbt, 15, 0.8)) == 0) enemy.setHealth(0.001F); // tiers: (6% chance, 8% chance, 1125%% chance); sets enemies health to something small, so damage kills enemy in one hit
			
			if (WeaponAttribute.CRITICAL.hasAttribute(nbt) && (int) (Math.random() * 5) == 0)
			{
				float bonus = (float) (event.getAmount() * WeaponAttribute.CRITICAL.getCalculatedValue(nbt, 0.20, 1.5)); // 20% chance; tiers: (20%, 30%, 45%)
				event.setAmount(event.getAmount() + bonus);
			}
			
			if (WeaponAttribute.CHAINED.hasAttribute(nbt) && (int) (Math.random() * 10) == 0)
			{
				double radius = 10;
				World world = enemy.getEntityWorld();
				List<EntityLivingBase> entityList = world.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(player.posX - radius, player.posY - radius, player.posZ - radius, player.posX + radius, player.posY + radius, player.posZ + radius));
				Iterator<EntityLivingBase> iterator = entityList.iterator();
				
				while (iterator.hasNext())
				{
                    Entity entity = (Entity) iterator.next();
					
					if (entity instanceof EntityLivingBase && !(entity instanceof EntityPlayer) && !(entity instanceof EntityAnimal))
					{
						entity.attackEntityFrom(DamageSource.causePlayerDamage(player), event.getAmount() / 2);
					}
				}
			}
		}
		
		// ARMOR
		if (stack.getItem() instanceof ItemArmor)
		{
			if (ArmorAttribute.FIRE.hasAttribute(nbt) && (int) (Math.random() * 4) == 0) enemy.setFire((int) ArmorAttribute.FIRE.getCalculatedValue(nbt, 4, 1.25)); // 25% chance; tiers: (4 second, 5 second, 6 second)
			if (ArmorAttribute.FROST.hasAttribute(nbt) && (int) (Math.random() * 4) == 0) enemy.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, (int) ArmorAttribute.FROST.getCalculatedValue(nbt, 20, 1.5), 10)); // 25% chance; tiers: (1 second, 1.5 second, 2.25 second)
			if (ArmorAttribute.POISON.hasAttribute(nbt) && (int) (Math.random() * 4) == 0) enemy.addPotionEffect(new PotionEffect(MobEffects.POISON, (int) ArmorAttribute.POISON.getCalculatedValue(nbt, 20 * 7, 1.5), ArmorAttribute.POISON.getAttributeTier(nbt))); // 25% chance; tiers: (7 second, 10.5 second, 15.75 second)
			if (ArmorAttribute.DURABLE.hasAttribute(nbt) && (int) (Math.random() * 4) == 0) stack.setItemDamage(stack.getItemDamage() - (int) ArmorAttribute.DURABLE.getCalculatedValue(nbt, 1, 2)); // 25% chance; tiers: (1 point, 2 point, 4 point)
			if (ArmorAttribute.MAGICAL.hasAttribute(nbt) && event.getSource().isMagicDamage()) event.setAmount((float) (event.getAmount() * ArmorAttribute.MAGICAL.getCalculatedValue(nbt, 0.2, 1.5))); // tiers: (20%, 30%, 45%) 
		}
		
		// BOW
		if (stack.getItem() instanceof ItemBow)
		{
			if (BowAttribute.FIRE.hasAttribute(nbt) && (int) (Math.random() * 4) == 0) enemy.setFire((int) BowAttribute.FIRE.getCalculatedValue(nbt, 4, 1.25)); // 25% chance; tiers: (4 second, 5 second, 6 second)
			if (BowAttribute.FROST.hasAttribute(nbt) && (int) (Math.random() * 4) == 0) enemy.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, (int) BowAttribute.FROST.getCalculatedValue(nbt, 20, 1.5), 10)); // 25% chance; tiers: (1 second, 1.5 second, 2.25 second)
			if (BowAttribute.POISON.hasAttribute(nbt) && (int) (Math.random() * 4) == 0) enemy.addPotionEffect(new PotionEffect(MobEffects.POISON, (int) BowAttribute.POISON.getCalculatedValue(nbt, 20 * 7, 1.5), BowAttribute.POISON.getAttributeTier(nbt))); // 25% chance; tiers: (7 second, 10.5 second, 15.75 second)
			if (BowAttribute.DURABLE.hasAttribute(nbt) && (int) (Math.random() * 4) == 0) stack.setItemDamage(stack.getItemDamage() - (int) BowAttribute.DURABLE.getCalculatedValue(nbt, 1, 2)); // 25% chance; tiers: (1 point, 2 point, 4 point)
			if (BowAttribute.ABSORB.hasAttribute(nbt) && (int) (Math.random() * 5) == 0) player.setHealth(player.getHealth() + (float) (event.getAmount() * BowAttribute.ABSORB.getCalculatedValue(nbt, 0.25, 1.5))); // 14% chance; returns half the damage dealt back as health; tiers: (25%, 37.5%, 56.25%)
			if (BowAttribute.VOID.hasAttribute(nbt) && (int) (Math.random() * BowAttribute.VOID.getCalculatedValue(nbt, 15, 0.8)) == 0) enemy.setHealth(0.001F); // tiers: (6% chance, 8% chance, 1125%% chance); sets enemies health to something small, so damage kills enemy in one hit
			
			if (BowAttribute.CRITICAL.hasAttribute(nbt) && (int) (Math.random() * 5) == 0)
			{
				float bonus = (float) (event.getAmount() * BowAttribute.CRITICAL.getCalculatedValue(nbt, 0.20, 1.5)); // 20% chance; tiers: (20%, 30%, 45%)
				event.setAmount(event.getAmount() + bonus);
			}
		}
		
		// SHIELD
		if (stack.getItem() instanceof ItemShield)
		{
			if (ShieldAttribute.FIRE.hasAttribute(nbt) && (int) (Math.random() * 4) == 0) enemy.setFire((int) ShieldAttribute.FIRE.getCalculatedValue(nbt, 4, 1.25)); // 25% chance; tiers: (4 second, 5 second, 6 second)
			if (ShieldAttribute.FROST.hasAttribute(nbt) && (int) (Math.random() * 4) == 0) enemy.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, (int) ShieldAttribute.FROST.getCalculatedValue(nbt, 20, 1.5), 10)); // 25% chance; tiers: (1 second, 1.5 second, 2.25 second)
			if (ShieldAttribute.POISON.hasAttribute(nbt) && (int) (Math.random() * 4) == 0) enemy.addPotionEffect(new PotionEffect(MobEffects.POISON, (int) ShieldAttribute.POISON.getCalculatedValue(nbt, 20 * 7, 1.5), ShieldAttribute.POISON.getAttributeTier(nbt))); // 25% chance; tiers: (7 second, 10.5 second, 15.75 second)
			if (ShieldAttribute.DURABLE.hasAttribute(nbt) && (int) (Math.random() * 4) == 0) stack.setItemDamage(stack.getItemDamage() - (int) ShieldAttribute.DURABLE.getCalculatedValue(nbt, 1, 2)); // 25% chance; tiers: (1 point, 2 point, 4 point)
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

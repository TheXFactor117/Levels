package com.thexfactor117.levels.events;

import java.util.Random;

import com.thexfactor117.levels.Reference;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import com.thexfactor117.levels.handlers.ConfigHandler;
import com.thexfactor117.levels.helpers.*;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

/**
 * 
 * @author TheXFactor117
 *
 */
public class EventLivingHurt 
{	
	/**
	 * Fired when an entity is about to be hurt.
	 * @param event
	 */
	@SuppressWarnings("incomplete-switch")
	@SubscribeEvent
	public void hitEntity(LivingHurtEvent event)
	{
		/*
		 * 
		 * WEAPONS
		 *
		 */
		if (event.source.getSourceOfDamage() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.source.getSourceOfDamage();
			Random rand = player.worldObj.rand;
			EntityLivingBase enemy = event.entityLiving;
			ItemStack stack = player.inventory.getCurrentItem();
			
			if (stack != null)
			{
				if (stack.getItem() instanceof ItemSword)
				{
					NBTTagCompound nbt = NBTHelper.loadStackNBT(stack);
					int level = Experience.getLevel(nbt);
					int experience = Experience.getExperience(nbt);

					/*
					 * Experience
					 */
					if (level < Reference.MAX_LEVEL)
					{
						experience += level > 3 && rand.nextInt(4) == 0 ? 2 : 1;
						Experience.setExperience(nbt, experience);
					}

					/*
					 * Leveling system
					 */
					level = Experience.getLevelsUp(player, nbt, level, experience, ItemType.WEAPON, rand);
					Experience.setLevel(nbt, level);

					/*
					 * Rarity
					 */
					Rarity rarity = Rarity.getRarity(nbt);
					float multiplier = 1.0F;
					boolean var = false;
					boolean var1 = false;

					switch (rarity)
					{
						case UNCOMMON:
							multiplier = 1.5F;
							break;
						case RARE:
							multiplier = 1.5F;
							var = rand.nextInt(2) == 0;
							break;
						case LEGENDARY:
							multiplier = 2.0F;
							var = rand.nextInt(10) <= 5;
							var1 = rand.nextInt(20) == 0;
							break;
						case ANCIENT:
							multiplier = 3.0F;
							var = rand.nextInt(4) != 0;
							var1 = rand.nextInt(10) == 0;
							break;
					}

					event.ammount *= multiplier;

					if (var)
					{
						if (stack.getItemDamage() == stack.getMaxDamage())
						{
							stack.setItemDamage(stack.getItemDamage());
						}
						else
						{
							stack.setItemDamage(stack.getItemDamage() + 1);
						}
					}

					if (var1)
					{
						if (stack.getItemDamage() == stack.getMaxDamage())
						{
							stack.setItemDamage(stack.getItemDamage());
						}
						else
						{
							stack.setItemDamage(stack.getItemDamage() + 20);
						}
					}

					/*
					 * Abilities
					 */
					if (enemy != null)
					{
						if (Ability.FIRE.hasAbility(nbt))
						{
							enemy.setFire(ConfigHandler.fireAbilityDuration);
						}

						if (Ability.FROST.hasAbility(nbt))
						{
							enemy.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 20 * ConfigHandler.frostAbilityDuration, 10));
						}

						if (Ability.POISON.hasAbility(nbt))
						{
							enemy.addPotionEffect(new PotionEffect(Potion.poison.id, 20 * ConfigHandler.poisonAbilityDuration, 0));
						}

						if (Ability.STRENGTH.hasAbility(nbt) && rand.nextInt(ConfigHandler.strengthAbilityProbability) == 0)
						{
							player.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 20 * ConfigHandler.strengthAbilityDuration, 0));
						}

						if (Ability.ETHEREAL.hasAbility(nbt) && rand.nextInt(4) == 0)
						{
							float health = Math.min(player.getMaxHealth(), player.getHealth() + ConfigHandler.etherealAbilityAmountHealed);
							player.setHealth(health);
						}

						if (Ability.VOID.hasAbility(nbt) && rand.nextInt(20) == 0)
						{
							float health;

							if (ConfigHandler.voidInstantKill || enemy.getHealth() < 30.0F)
							{
								health = 0.0F;
							}
							else
							{
								health = (float) (enemy.getHealth() - ConfigHandler.voidDamageAmount);
							}

							enemy.setHealth(health);
						}
					}

					NBTHelper.saveStackNBT(stack, nbt);
				}
			}
		}
		
		/*
		 * 
		 * ARMOR
		 * 
		 */
		if (event.entityLiving instanceof EntityPlayer)
		{
			Entity source = event.source.getSourceOfDamage();
			EntityPlayer player = (EntityPlayer) event.entityLiving;
			Random rand = player.worldObj.rand;
			
			if (source instanceof EntityMob)
			{
				EntityMob attacker = (EntityMob) source;

				for (ItemStack stack : player.inventory.armorInventory)
				{
					if (stack != null)
					{
						if (stack.getItem() instanceof ItemArmor)
						{
							NBTTagCompound nbt = NBTHelper.loadStackNBT(stack);
							int level = Experience.getLevel(nbt);
							int experience = Experience.getExperience(nbt);

							/*
							 * Experience
							 */
							if (level < Reference.MAX_LEVEL)
							{
								experience += level > 3 && rand.nextInt(3) == 0 ? 1 + rand.nextInt(3) : 1;
								Experience.setExperience(nbt, experience);
							}
							
							/*
							 * Leveling system
							 */
							level = Experience.getLevelsUp(player, nbt, level, experience, ItemType.ARMOR, rand);
							Experience.setLevel(nbt, level);
							
							/*
							 * Abilities
							 */
							if (Ability.HARDENED.hasAbility(nbt) && rand.nextInt(5) == 0)
							{
								attacker.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 20 * ConfigHandler.hardenedAbilityDuration, 10));
							}

							if (Ability.POISONED.hasAbility(nbt) && rand.nextInt(5) == 0)
							{
								attacker.addPotionEffect(new PotionEffect(Potion.poison.id, 20 * ConfigHandler.poisonedAbilityDuration, 0));
							}

							if (Ability.STRENGTH.hasAbility(nbt) && rand.nextInt(ConfigHandler.strengthAbilityProbability) == 0)
							{
								player.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 20 * ConfigHandler.strengthAbilityDuration, 0));
							}

							if (Ability.ETHEREAL.hasAbility(nbt) && rand.nextInt(15) == 0)
							{
								player.setHealth(20.0F);
							}

							if (Ability.VOID.hasAbility(nbt) && rand.nextInt(20) == 0)
							{
								attacker.setHealth(0.0F);
							}

							NBTHelper.saveStackNBT(stack, nbt);
						}
					}
				}
			}
		}
	}
}

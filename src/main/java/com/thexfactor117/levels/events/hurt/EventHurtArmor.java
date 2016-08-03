package com.thexfactor117.levels.events.hurt;

import java.util.Random;

import com.thexfactor117.levels.handlers.ConfigHandler;
import com.thexfactor117.levels.leveling.Ability;
import com.thexfactor117.levels.leveling.AbilityHelper;
import com.thexfactor117.levels.leveling.Experience;
import com.thexfactor117.levels.leveling.Rarity;
import com.thexfactor117.levels.utils.NBTHelper;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * 
 * @author TheXFactor117
 *
 * Untweaked
 */
public class EventHurtArmor 
{
	@SubscribeEvent
	public void hitEntity(LivingHurtEvent event)
	{
		/*
		 * ARMORS
		 * 
		 * Untweaked
		 */
		if (event.getEntityLiving() instanceof EntityPlayer)
		{
			if (event.getSource().getEntity() instanceof EntityLivingBase)
			{
				EntityLivingBase enemy = (EntityLivingBase) event.getSource().getEntity();
				EntityPlayer player = (EntityPlayer) event.getEntityLiving();
				Random rand = player.worldObj.rand;
				
				for (ItemStack stack : player.inventory.armorInventory)
				{
					if (stack != null && stack.getItem() instanceof ItemArmor)
					{
						NBTTagCompound nbt = NBTHelper.loadStackNBT(stack);
						
						if (nbt != null)
						{
							int level = Experience.getLevel(nbt);
							int experience = Experience.getExperience(nbt);
							Rarity rarity = Rarity.getRarity(nbt);
							
							/*
							 * Experience
							 * 
							 * Untweaked
							 */
							if (level < ConfigHandler.MAX_LEVEL_CAP)
							{
								experience += level > 3 && rand.nextInt(3) == 0 ? 1 + rand.nextInt(3) : 1;
								Experience.setExperience(nbt, experience);
							}
							
							/*
							 * Rarities
							 * 
							 * Untweaked
							 */
							if (rarity == Rarity.UNCOMMON)
							{
								if (rand.nextInt(30) == 0) event.setAmount(0F);
								if (rand.nextInt(10) == 0) Experience.setExperience(nbt, experience + 7);
							}
							if (rarity == Rarity.RARE)
							{
								if (rand.nextInt(25) == 0) event.setAmount(0F);
								if (rand.nextInt(7) == 0) Experience.setExperience(nbt, experience + 7);
							}
							if (rarity == Rarity.LEGENDARY)
							{
								if (rand.nextInt(20) == 0) event.setAmount(0F);
								if (rand.nextInt(5) == 0) Experience.setExperience(nbt, experience + 7);
							}
							if (rarity == Rarity.ANCIENT)
							{
								if (rand.nextInt(15) == 0) event.setAmount(0F);
								if (rand.nextInt(3) == 0) Experience.setExperience(nbt, experience + 7);
							}
							
							/*
							 * Durability
							 * 
							 * Untweaked
							 */
							if (ConfigHandler.DURABILITY)
							{
								// Durability boosts
								// rare
								if (rarity == Rarity.RARE)
								{
									// reduction
									int var = rand.nextInt(5);
									if (var == 0)
									{
										if (stack.getItemDamage() == stack.getMaxDamage())
										{
											stack.setItemDamage(stack.getItemDamage());
										}
										else
										{
											stack.setItemDamage(stack.getItemDamage() - 1);
										}
									}
								}
								
								// legendary
								if (rarity == Rarity.LEGENDARY)
								{
									// reduction
									int var = rand.nextInt(5);
									if (var == 0)
									{
										if (stack.getItemDamage() == stack.getMaxDamage())
										{
											stack.setItemDamage(stack.getItemDamage());
										}
										else
										{
											stack.setItemDamage(stack.getItemDamage() - 1);
										}
									}
									
									// additional durability
									int var1 = rand.nextInt(10);
									if (var1 == 0)
									{
										if (stack.getItemDamage() <= stack.getMaxDamage() && stack.getItemDamage() >= (stack.getMaxDamage() - 10))
										{
											stack.setItemDamage(stack.getMaxDamage());
										}
										else
										{
											stack.setItemDamage(stack.getItemDamage() - 10);
	 									}
									}
								}
								
								// ancient
								if (rarity == Rarity.ANCIENT)
								{
									// reduction
									int var = rand.nextInt(10);
									if (var < 3)
									{
										if (stack.getItemDamage() == stack.getMaxDamage())
										{
											stack.setItemDamage(stack.getItemDamage());
										}
										else
										{
											stack.setItemDamage(stack.getItemDamage() - 1);
										}
									}
									
									// additional durability
									int var1 = rand.nextInt(5);
									if (var1 == 0)
									{
										if (stack.getItemDamage() <= stack.getMaxDamage() && stack.getItemDamage() >= (stack.getMaxDamage() - 10))
										{
											stack.setItemDamage(stack.getMaxDamage());
										}
										else
										{
											stack.setItemDamage(stack.getItemDamage() - 10);
										}
									}
								}
							}
							else
							{
								stack.setItemDamage(-stack.getMaxDamage());
							}
							
							/*
							 * Abilities
							 * 
							 * Tweaked
							 */
							// basic
							if (Ability.MOLTEN.hasAbility(nbt) && rand.nextInt(4) == 0) 
							{
								int multiplier = (int) Ability.MOLTEN.getMultiplier(Ability.MOLTEN.getTier(nbt), nbt);
								enemy.setFire(4 * multiplier);
							}
							
							if (Ability.FROZEN.hasAbility(nbt) && rand.nextInt(4) == 0) 
							{
								int multiplier = (int) Ability.FROZEN.getMultiplier(Ability.FROZEN.getTier(nbt), nbt);
								enemy.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, (20 * 4) * multiplier, 10));
							}
							
							// uncommon
							if (Ability.TOXIC.hasAbility(nbt) && rand.nextInt(5) == 0) 
							{
								int multiplier = (int) Ability.TOXIC.getMultiplier(Ability.TOXIC.getTier(nbt), nbt);
								enemy.addPotionEffect(new PotionEffect(MobEffects.POISON, (20 * 7) * multiplier, Ability.TOXIC.getTier(nbt) - 1));
							}
							
							if (Ability.BEASTIAL.hasAbility(nbt))
							{
								int multiplier = (int) Ability.BEASTIAL.getMultiplier(Ability.BEASTIAL.getTier(nbt), nbt);
								
								if (player.getHealth() <= 4F * multiplier) 
								{
									player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 20 * 1, Ability.BEASTIAL.getTier(nbt)));
								}
							}
							
							// rare
							if (Ability.ENLIGHTENED.hasAbility(nbt) && rand.nextInt(7) == 0)
							{
								int multiplier = (int) Ability.ENLIGHTENED.getMultiplier(Ability.ENLIGHTENED.getTier(nbt), nbt);
								float health = player.getHealth() + (event.getAmount() / (2 / multiplier));
								player.setHealth(health);
							}
							
							if (Ability.ABSORB.hasAbility(nbt) && rand.nextInt(7) == 0) 
							{
								int multiplier = (int) Ability.ABSORB.getMultiplier(Ability.ABSORB.getTier(nbt), nbt);
								player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, (20 * 7) * multiplier, Ability.ABSORB.getTier(nbt) - 1));
							}
							
							// legendary
							if (Ability.HARDENED.hasAbility(nbt) && rand.nextInt(10) == 0) 
							{
								event.setAmount(0F);
							}
							
							if (Ability.INVISIBILITY.hasAbility(nbt) && rand.nextInt(10) == 0) 
							{
								int multiplier = (int) Ability.INVISIBILITY.getMultiplier(Ability.INVISIBILITY.getTier(nbt), nbt);
								player.addPotionEffect(new PotionEffect(MobEffects.INVISIBILITY, (20 * 7) * multiplier, Ability.INVISIBILITY.getTier(nbt) - 1));
							}
							
							// ancient
							if (Ability.VOID_ARMOR.hasAbility(nbt) && rand.nextInt(20) == 0) 
							{
								enemy.attackEntityFrom(DamageSource.causePlayerDamage(player), 10000);
							}
							
							/*
							 * Leveling system
							 * 
							 * Tweaked
							 */
							experience = Experience.getExperience(nbt);
							level = Experience.getNextLevel(player, stack, nbt, AbilityHelper.ARMOR, level, experience, rand);
							Experience.setLevel(nbt, level);
							
							NBTHelper.saveStackNBT(stack, nbt);
						}
					}
				}
			}
		}
	}
}

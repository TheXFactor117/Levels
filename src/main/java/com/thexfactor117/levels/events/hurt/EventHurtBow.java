package com.thexfactor117.levels.events.hurt;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.thexfactor117.levels.handlers.ConfigHandler;
import com.thexfactor117.levels.leveling.Ability;
import com.thexfactor117.levels.leveling.AbilityHelper;
import com.thexfactor117.levels.leveling.Experience;
import com.thexfactor117.levels.leveling.Rarity;
import com.thexfactor117.levels.utils.NBTHelper;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * 
 * @author TheXFactor117
 *
 * Untweaked
 */
public class EventHurtBow 
{
	@SuppressWarnings("rawtypes")
	@SubscribeEvent
	public void hitEntity(LivingHurtEvent event)
	{
		/*
		 * 
		 * BOWS
		 * 
		 * Untweaked
		 */
		if (event.getSource().getSourceOfDamage() instanceof EntityArrow)
		{
			EntityArrow arrow = (EntityArrow) event.getSource().getSourceOfDamage();
			EntityLivingBase enemy = event.getEntityLiving();
			
			if (arrow.shootingEntity instanceof EntityPlayer)
			{
				EntityPlayer player = (EntityPlayer) arrow.shootingEntity;
				ItemStack stack = player.inventory.getCurrentItem();
				Random rand = player.worldObj.rand;
				
				if (stack != null)
				{
					if (stack.getItem() instanceof ItemBow)
					{
						if (stack.getItem() instanceof ItemBow)
						{
							NBTTagCompound nbt = NBTHelper.loadStackNBT(stack);
							int level = Experience.getLevel(nbt);
							int experience = Experience.getExperience(nbt);

							/*
							 * Experience
							 * 
							 * Untweaked
							 */
							if (level < ConfigHandler.MAX_LEVEL_CAP)
							{
								boolean isDev = (Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");
								
								if (isDev)
								{
									Experience.setExperience(nbt, Experience.getExperience(nbt) + 1000);
								}
								else
								{
									Experience.setExperience(nbt, Experience.getExperience(nbt) + 1);
								}
							}

							/*
							 * Rarity
							 * 
							 * Untweaked
							 */
							Rarity rarity = Rarity.getRarity(nbt);
							float damageMultiplier = 1.0F;
							//float trueDamage = event.ammount;

							// Damage boosts
							switch (rarity)
							{
								case UNCOMMON:
									damageMultiplier = 1.5F;
									break;
								case RARE:
									damageMultiplier = 1.5F;
									break;
								case LEGENDARY:
									damageMultiplier = 2.0F;
									break;
								case ANCIENT:
									damageMultiplier = 3.0F;
									break;
								case UNKNOWN:
									break;
								default:
									break;
							}

							float amount = event.getAmount();
							event.setAmount(amount *= damageMultiplier);
							
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
							if (enemy != null)
							{
								// basic
								if (Ability.FIRE.hasAbility(nbt) && rand.nextInt(4) == 0) 
								{
									int multiplier = (int) Ability.FIRE.getMultiplier(Ability.FIRE.getTier(nbt), nbt);
									enemy.setFire(4 * multiplier);
								}
								
								if (Ability.FROST.hasAbility(nbt) && rand.nextInt(4) == 0) 
								{
									int multiplier = (int) Ability.FROST.getMultiplier(Ability.FROST.getTier(nbt), nbt);
									enemy.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, (20 * 4) * multiplier, 10));
								}
								
								if (Ability.POISON.hasAbility(nbt) && rand.nextInt(4) == 0) 
								{
									int multiplier = (int) Ability.POISON.getMultiplier(Ability.POISON.getTier(nbt), nbt);
									enemy.addPotionEffect(new PotionEffect(MobEffects.POISON, (20 * 7) * multiplier, Ability.POISON.getTier(nbt) - 1));
								}
								
								// uncommon
								if (Ability.STRENGTH.hasAbility(nbt) && rand.nextInt(5) == 0) 
								{
									int multiplier = (int) Ability.STRENGTH.getMultiplier(Ability.STRENGTH.getTier(nbt), nbt);
									player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, (20 * 3) * multiplier, Ability.STRENGTH.getTier(nbt) - 1));
								}
								
								if (Ability.ELEMENTAL.hasAbility(nbt) && rand.nextInt(5) == 0)
								{
									int multiplier = (int) Ability.ELEMENTAL.getMultiplier(Ability.ELEMENTAL.getTier(nbt), nbt);
									int var1 = rand.nextInt(3);
									if (var1 == 0) enemy.setFire(4 * multiplier);
									if (var1 == 1) enemy.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, (20 * 4) * multiplier, 10));
									if (var1 == 2) enemy.addPotionEffect(new PotionEffect(MobEffects.POISON, (20 * 7) * multiplier, Ability.ELEMENTAL.getTier(nbt) - 1));
								}
								
								if (Ability.DARKNESS.hasAbility(nbt) && rand.nextInt(5) == 0) 
								{
									int multiplier = (int) Ability.DARKNESS.getMultiplier(Ability.DARKNESS.getTier(nbt), nbt);
									enemy.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, (20 * 3) * multiplier, Ability.DARKNESS.getTier(nbt) - 1));
								}
								
								// rare
								if (Ability.LIGHT.hasAbility(nbt) && rand.nextInt(7) == 0)
								{
									int multiplier = (int) Ability.LIGHT.getMultiplier(Ability.LIGHT.getTier(nbt), nbt);
									enemy.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, (20 * 5) * multiplier, Ability.LIGHT.getTier(nbt) - 1));
									int var = rand.nextInt(5);
									if (var == 0) enemy.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, (20 * 3) * multiplier, Ability.LIGHT.getTier(nbt) - 1));
								}
								
								if (Ability.BLOODLUST.hasAbility(nbt) && rand.nextInt(7) == 0)
								{
									int multiplier = (int) Ability.BLOODLUST.getMultiplier(Ability.BLOODLUST.getTier(nbt), nbt);
									int bonusDamage = (rand.nextInt(10) + 1) * multiplier;
									enemy.setHealth(enemy.getHealth() - bonusDamage);
								}
								
								if (Ability.STING.hasAbility(nbt) && rand.nextInt(7) == 0) 
								{
									int multiplier = (int) Ability.STING.getMultiplier(Ability.STING.getTier(nbt), nbt);
									enemy.setHealth(enemy.getHealth() - (10 * multiplier));
								}
								
								// legendary
								if (Ability.ETHEREAL.hasAbility(nbt) && rand.nextInt(10) == 0)
								{
									int multiplier = (int) Ability.ETHEREAL.getMultiplier(Ability.ETHEREAL.getTier(nbt), nbt);
									float health = player.getHealth() + (event.getAmount() / (2 / multiplier));
									player.setHealth(health);
								}
								
								if (Ability.CHAINED.hasAbility(nbt) && rand.nextInt(10) == 0)
								{
									int multiplier = (int) Ability.CHAINED.getMultiplier(Ability.CHAINED.getTier(nbt), nbt);
									int radius = 10 * multiplier;
									World world = enemy.getEntityWorld();
									List entityList = world.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(player.posX - radius, player.posY - radius, player.posZ - radius, player.posX + radius, player.posY + radius, player.posZ + radius));
									Iterator iterator = entityList.iterator();
									
									while (iterator.hasNext())
									{
					                    Entity entity = (Entity) iterator.next();
										
										if (entity instanceof EntityLivingBase)
										{
											entity.setFire(4 * multiplier);
										}
									}
								}
								
								// ancient
								if (Ability.VOID.hasAbility(nbt) && rand.nextInt(20) == 0)
								{
									enemy.attackEntityFrom(DamageSource.causePlayerDamage(player), 10000);
								}
							}
							
							/*
							 * Leveling system
							 */
							experience = Experience.getExperience(nbt);
							level = Experience.getNextLevel(player, stack, nbt, AbilityHelper.WEAPON, level, experience, rand);
							Experience.setLevel(nbt, level);
							
							NBTHelper.saveStackNBT(stack, nbt);
						}
					}
				}
			}
		}
	}
}

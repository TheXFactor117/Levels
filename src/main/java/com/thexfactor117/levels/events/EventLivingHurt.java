package com.thexfactor117.levels.events;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.thexfactor117.levels.handlers.ConfigHandler;
import com.thexfactor117.levels.leveling.Ability;
import com.thexfactor117.levels.leveling.AbilityHelper;
import com.thexfactor117.levels.leveling.Experience;
import com.thexfactor117.levels.leveling.Rarity;
import com.thexfactor117.xlib.misc.NBTHelper;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

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
	@SuppressWarnings({ "incomplete-switch", "rawtypes" })
	@SubscribeEvent
	public void hitEntity(LivingHurtEvent event)
	{
		/*
		 * 
		 * WEAPONS
		 *
		 */
		if (event.getSource().getSourceOfDamage() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.getSource().getSourceOfDamage();
			Random rand = player.worldObj.rand;
			EntityLivingBase enemy = event.getEntityLiving();
			ItemStack stack = player.getHeldItem(player.getActiveHand());
			
			if (stack != null)
			{
				/*****************
				 * MELEE WEAPONS *
				 *****************/
				if (stack.getItem() instanceof ItemSword)
				{
					NBTTagCompound nbt = NBTHelper.loadStackNBT(stack);
					
					if (nbt != null)
					{
						int level = Experience.getLevel(nbt);
						int experience = Experience.getExperience(nbt);

						/*
						 * Experience
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
						 */
						Rarity rarity = Rarity.getRarity(nbt);
						float damageMultiplier = 1.0F;
						//float trueDamage = event.ammount;

						// Damage boosts
						switch (rarity)
						{
							case UNCOMMON:
								damageMultiplier = 1.5F;
								int var = rand.nextInt(10);
								if (var == 0) Experience.setExperience(nbt, Experience.getExperience(nbt) + 10);
								break;
							case RARE:
								damageMultiplier = 1.5F;
								int var1 = rand.nextInt(4);
								if (var1 == 0) Experience.setExperience(nbt, Experience.getExperience(nbt) + 10);
								break;
							case LEGENDARY:
								damageMultiplier = 2.0F;
								int var2 = rand.nextInt(5);
								int var3 = rand.nextInt(10) + 20;
								if (var2 == 0) Experience.setExperience(nbt, Experience.getExperience(nbt) + var3);
								break;
							case ANCIENT:
								damageMultiplier = 3.0F;
								int var4 = rand.nextInt(4);
								int var5 = rand.nextInt(20) + 30;
								if (var4 == 0) Experience.setExperience(nbt, Experience.getExperience(nbt) + var5);
								break;
						}

						float amount = event.getAmount();
						event.setAmount(amount *= damageMultiplier);

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
						 */
						if (enemy != null)
						{
							if (Ability.FIRE.hasAbility(nbt)) enemy.setFire(ConfigHandler.BURN_TIME);
							if (Ability.FROST.hasAbility(nbt)) enemy.addPotionEffect(new PotionEffect(Potion.getPotionById(2), 20 * ConfigHandler.STUN_TIME, 10));
							if (Ability.POISON.hasAbility(nbt)) enemy.addPotionEffect(new PotionEffect(Potion.getPotionById(20), 20 * ConfigHandler.POISON_TIME, 0));
							if (Ability.STRENGTH.hasAbility(nbt) && rand.nextInt(ConfigHandler.STRENGTH_PERCENTAGE) == 0) player.addPotionEffect(new PotionEffect(Potion.getPotionById(5), 20 * ConfigHandler.STRENGTH_TIME, 0));
							if (Ability.ELEMENTAL.hasAbility(nbt) && rand.nextInt(ConfigHandler.ELEMENTAL_PERCENTAGE) == 0)
							{
								int var1 = rand.nextInt(3);
								if (var1 == 0) enemy.setFire(ConfigHandler.BURN_TIME);
								if (var1 == 1) enemy.addPotionEffect(new PotionEffect(Potion.getPotionById(2), 20 * ConfigHandler.STUN_TIME, 10));
								if (var1 == 2) enemy.addPotionEffect(new PotionEffect(Potion.getPotionById(19), 20 * ConfigHandler.POISON_TIME, 0));
							}
							
							if (Ability.DARKNESS.hasAbility(nbt) && rand.nextInt(ConfigHandler.DARKNESS_PERCENTAGE) == 0) enemy.addPotionEffect(new PotionEffect(Potion.getPotionById(15), 20 * ConfigHandler.DARKNESS_TIME, 0));
							if (Ability.LIGHT.hasAbility(nbt))
							{
								enemy.addPotionEffect(new PotionEffect(Potion.getPotionById(18), 20 * ConfigHandler.LIGHT_TIME, 0));
								int var = rand.nextInt(ConfigHandler.LIGHT_PERCENTAGE_BONUS);
								if (var == 0) enemy.addPotionEffect(new PotionEffect(Potion.getPotionById(15), 20 * ConfigHandler.LIGHT_TIME_BONUS, 0));
							}
							
							if (Ability.BLOODLUST.hasAbility(nbt) && rand.nextInt(ConfigHandler.BLOODLUST_PERCENTAGE) == 0)
							{
								int var = rand.nextInt(ConfigHandler.BLOODLUST_BONUS) + 1;
								enemy.setHealth(enemy.getHealth() - var);
							}
							
							if (Ability.STING.hasAbility(nbt) && rand.nextInt(ConfigHandler.STING_PERCENTAGE) == 0) enemy.setHealth(enemy.getHealth() - ConfigHandler.STING_AMOUNT);
							
							if (Ability.ETHEREAL.hasAbility(nbt) && rand.nextInt(ConfigHandler.ETHEREAL_PERCENTAGE) == 0)
							{
								float health = player.getHealth() + (event.getAmount() / 2);
								player.setHealth(health);
							}
							
							if (Ability.CHAINED.hasAbility(nbt) && rand.nextInt(ConfigHandler.CHAINED_PERCENTAGE) == 0)
							{
								World world = enemy.getEntityWorld();
								List entityList = world.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(player.posX - ConfigHandler.CHAINED_RADIUS, player.posY - ConfigHandler.CHAINED_RADIUS, player.posZ - ConfigHandler.CHAINED_RADIUS, player.posX + ConfigHandler.CHAINED_RADIUS, player.posY + ConfigHandler.CHAINED_RADIUS, player.posZ + ConfigHandler.CHAINED_RADIUS));
								Iterator iterator = entityList.iterator();
								
								while (iterator.hasNext())
								{
				                    Entity entity = (Entity) iterator.next();
									
									if (entity instanceof EntityLivingBase)
									{
										entity.setFire(ConfigHandler.BURN_TIME);
									}
								}
							}
							
							if (Ability.VOID.hasAbility(nbt) && rand.nextInt(ConfigHandler.VOID_PERCENTAGE) == 0) enemy.setHealth(0);
						}
						
						/*
						 * Leveling system
						 */
						experience = Experience.getExperience(nbt);
						level = Experience.getNextLevel(player, nbt, AbilityHelper.WEAPON, level, experience, rand);
						Experience.setLevel(nbt, level);
						
						NBTHelper.saveStackNBT(stack, nbt);
					}
				}
			}
		}
		
		/********
		 * BOWS *
		 *******/
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
							}

							float amount = event.getAmount();
							event.setAmount(amount *= damageMultiplier);
							
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
							 */
							if (enemy != null)
							{
								if (Ability.FIRE.hasAbility(nbt)) enemy.setFire(ConfigHandler.BURN_TIME);
								if (Ability.FROST.hasAbility(nbt)) enemy.addPotionEffect(new PotionEffect(Potion.getPotionById(2), 20 * ConfigHandler.STUN_TIME, 10));
								if (Ability.POISON.hasAbility(nbt)) enemy.addPotionEffect(new PotionEffect(Potion.getPotionById(20), 20 * ConfigHandler.POISON_TIME, 0));
								if (Ability.STRENGTH.hasAbility(nbt) && rand.nextInt(ConfigHandler.STRENGTH_PERCENTAGE) == 0) player.addPotionEffect(new PotionEffect(Potion.getPotionById(5), 20 * ConfigHandler.STRENGTH_TIME, 0));
								if (Ability.ELEMENTAL.hasAbility(nbt) && rand.nextInt(ConfigHandler.ELEMENTAL_PERCENTAGE) == 0)
								{
									int var1 = rand.nextInt(3);
									if (var1 == 0) enemy.setFire(ConfigHandler.BURN_TIME);
									if (var1 == 1) enemy.addPotionEffect(new PotionEffect(Potion.getPotionById(2), 20 * ConfigHandler.STUN_TIME, 10));
									if (var1 == 2) enemy.addPotionEffect(new PotionEffect(Potion.getPotionById(19), 20 * ConfigHandler.POISON_TIME, 0));
								}
								
								if (Ability.DARKNESS.hasAbility(nbt) && rand.nextInt(ConfigHandler.DARKNESS_PERCENTAGE) == 0) enemy.addPotionEffect(new PotionEffect(Potion.getPotionById(15), 20 * ConfigHandler.DARKNESS_TIME, 0));
								if (Ability.LIGHT.hasAbility(nbt))
								{
									enemy.addPotionEffect(new PotionEffect(Potion.getPotionById(18), 20 * ConfigHandler.LIGHT_TIME, 0));
									int var = rand.nextInt(ConfigHandler.LIGHT_PERCENTAGE_BONUS);
									if (var == 0) enemy.addPotionEffect(new PotionEffect(Potion.getPotionById(15), 20 * ConfigHandler.LIGHT_TIME_BONUS, 0));
								}
								
								if (Ability.BLOODLUST.hasAbility(nbt) && rand.nextInt(ConfigHandler.BLOODLUST_PERCENTAGE) == 0)
								{
									int var = rand.nextInt(ConfigHandler.BLOODLUST_BONUS) + 1;
									enemy.setHealth(enemy.getHealth() - var);
								}
								
								if (Ability.STING.hasAbility(nbt) && rand.nextInt(ConfigHandler.STING_PERCENTAGE) == 0) enemy.setHealth(enemy.getHealth() - ConfigHandler.STING_AMOUNT);
								
								if (Ability.ETHEREAL.hasAbility(nbt) && rand.nextInt(ConfigHandler.ETHEREAL_PERCENTAGE) == 0)
								{
									float health = player.getHealth() + (event.getAmount() / 2);
									player.setHealth(health);
								}
								
								if (Ability.CHAINED.hasAbility(nbt) && rand.nextInt(ConfigHandler.CHAINED_PERCENTAGE) == 0)
								{
									World world = enemy.getEntityWorld();
									List entityList = world.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(player.posX - ConfigHandler.CHAINED_RADIUS, player.posY - ConfigHandler.CHAINED_RADIUS, player.posZ - ConfigHandler.CHAINED_RADIUS, player.posX + ConfigHandler.CHAINED_RADIUS, player.posY + ConfigHandler.CHAINED_RADIUS, player.posZ + ConfigHandler.CHAINED_RADIUS));
									Iterator iterator = entityList.iterator();
									
									while (iterator.hasNext())
									{
					                    Entity entity = (Entity) iterator.next();
										
										if (entity instanceof EntityLivingBase)
										{
											entity.setFire(ConfigHandler.BURN_TIME);
										}
									}
								}
								
								if (Ability.VOID.hasAbility(nbt) && rand.nextInt(ConfigHandler.VOID_PERCENTAGE) == 0) enemy.setHealth(0);
							}
							
							/*
							 * Leveling system
							 */
							experience = Experience.getExperience(nbt);
							level = Experience.getNextLevel(player, nbt, AbilityHelper.WEAPON, level, experience, rand);
							Experience.setLevel(nbt, level);
							
							NBTHelper.saveStackNBT(stack, nbt);
						}
					}
				}
			}
		}
		
		/*
		 * ARMORS 
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
							 */
							if (level < ConfigHandler.MAX_LEVEL_CAP)
							{
								experience += level > 3 && rand.nextInt(3) == 0 ? 1 + rand.nextInt(3) : 1;
								Experience.setExperience(nbt, experience);
							}
							
							/*
							 * Rarities
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
							 * Abilities
							 */
							if (Ability.MOLTEN.hasAbility(nbt) && rand.nextInt(ConfigHandler.BASIC_ARMOR_PERCENTAGE) == 0) enemy.setFire(ConfigHandler.BURN_TIME);
							if (Ability.FROZEN.hasAbility(nbt) && rand.nextInt(ConfigHandler.BASIC_ARMOR_PERCENTAGE) == 0) enemy.addPotionEffect(new PotionEffect(Potion.getPotionById(2), 20 * ConfigHandler.STUN_TIME, 10));
							if (Ability.TOXIC.hasAbility(nbt) && rand.nextInt(ConfigHandler.BASIC_ARMOR_PERCENTAGE) == 0) enemy.addPotionEffect(new PotionEffect(Potion.getPotionById(20), 20 * ConfigHandler.POISON_TIME, 0));
							if (Ability.BEASTIAL.hasAbility(nbt))
							{
								if (player.getHealth() <= ConfigHandler.BEASTIAL_MIN_HEALTH) player.addPotionEffect(new PotionEffect(Potion.getPotionById(5), 20 * ConfigHandler.BEASTIAL_TIME, 2));
							}
							if (Ability.ENLIGHTENED.hasAbility(nbt) && rand.nextInt(ConfigHandler.ENLIGHTENED_PERCENTAGE) == 0)
							{
								float health = player.getHealth() + (event.getAmount() / 2);
								player.setHealth(health);
							}
							if (Ability.ABSORB.hasAbility(nbt) && rand.nextInt(ConfigHandler.ABSORB_PERCENTAGE) == 0) player.addPotionEffect(new PotionEffect(Potion.getPotionById(10), 20 * ConfigHandler.ABSORB_TIME, 0));
							if (Ability.HARDENED.hasAbility(nbt) && rand.nextInt(ConfigHandler.HARDENED_PERCENTAGE) == 0) event.setAmount(0F);
							if (Ability.INVISIBILITY.hasAbility(nbt) && rand.nextInt(ConfigHandler.INVISIBILITY_PERCENTAGE) == 0) player.addPotionEffect(new PotionEffect(Potion.getPotionById(14), 20 * ConfigHandler.INVISIBILITY_TIME, 0));
							if (Ability.VOID_ARMOR.hasAbility(nbt) && rand.nextInt(ConfigHandler.VOID_PERCENTAGE) == 0) enemy.setHealth(0F);
							
							/*
							 * Leveling system
							 */
							experience = Experience.getExperience(nbt);
							level = Experience.getNextLevel(player, nbt, AbilityHelper.ARMOR, level, experience, rand);
							Experience.setLevel(nbt, level);
							
							NBTHelper.saveStackNBT(stack, nbt);
						}
					}
				}
			}
		}
		
		/*
		 * Entity Leveling
		 */
		/*if (event.getEntityLiving() instanceof EntityPlayer && event.getSource().getSourceOfDamage() instanceof EntityMob)
		{
			Random rand = event.getEntityLiving().worldObj.rand;
			EntityPlayer player = (EntityPlayer) event.getEntityLiving();
			EntityMob mob = (EntityMob) event.getSource().getSourceOfDamage();
			
			if (!mob.worldObj.isRemote)
			{
				NBTTagCompound nbt = mob.getEntityData();
				mob.readFromNBT(nbt);
				EnemyLevel enemyLevel = EnemyLevel.getEnemyLevel(nbt);
				int level = enemyLevel.ordinal();

				if (level == 5)
				{
					int var = rand.nextInt(10);
					int var1 = rand.nextInt(3);
					if (var == 0)
					{
						if (var1 == 0) player.setFire(4);
						if (var1 == 1) player.addPotionEffect(new PotionEffect(Potion.getPotionById(2), 20*5, 20));
						if (var1 == 2) player.addPotionEffect(new PotionEffect(Potion.getPotionById(19), 20*15, 0));
					}
				}
				
				if (level == 6)
				{
					int var = rand.nextInt(5);
					int var1 = rand.nextInt(3);
					if (var == 0)
					{
						if (var1 == 0) player.setFire(5);
						if (var1 == 1) player.addPotionEffect(new PotionEffect(Potion.getPotionById(2), 20*7, 20));
						if (var1 == 2) player.addPotionEffect(new PotionEffect(Potion.getPotionById(19), 20*20, 0));
					}
				}
			}
		}*/
	}
}

package com.thexfactor117.levels.events;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.thexfactor117.levels.capabilities.CapabilityEnemyLevel;
import com.thexfactor117.levels.capabilities.IEnemyLevel;
import com.thexfactor117.levels.handlers.ConfigHandler;
import com.thexfactor117.levels.leveling.Ability;
import com.thexfactor117.levels.leveling.AbilityHelper;
import com.thexfactor117.levels.leveling.Experience;
import com.thexfactor117.levels.leveling.Rarity;
import com.thexfactor117.levels.utils.NBTHelper;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * 
 * @author TheXFactor117
 *
 * Untweaked
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
		 * Untweaked
		 */
		if (event.getSource().getSourceOfDamage() instanceof EntityPlayer && !(event.getSource().getSourceOfDamage() instanceof FakePlayer))
		{
			EntityPlayer player = (EntityPlayer) event.getSource().getSourceOfDamage();
			Random rand = player.worldObj.rand;
			EntityLivingBase enemy = event.getEntityLiving();
			ItemStack stack = player.getHeldItemMainhand();
			
			if (stack != null)
			{
				if (stack.getItem() instanceof ItemSword)
				{
					NBTTagCompound nbt = NBTHelper.loadStackNBT(stack);
					
					if (nbt != null)
					{	
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
						 * Untweaked
						 */
						if (enemy != null)
						{
							if (Ability.FIRE.hasAbility(nbt)) 
							{
								int multiplier = (int) Ability.FIRE.getMultiplier(Ability.FIRE.getTier(nbt), nbt);
								enemy.setFire(4 * multiplier);
							}
							
							if (Ability.FROST.hasAbility(nbt)) 
							{
								int multiplier = (int) Ability.FROST.getMultiplier(Ability.FROST.getTier(nbt), nbt);
								enemy.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, (20 * 4) * multiplier, 10));
							}
							
							if (Ability.POISON.hasAbility(nbt)) 
							{
								int multiplier = (int) Ability.POISON.getMultiplier(Ability.POISON.getTier(nbt), nbt);
								enemy.addPotionEffect(new PotionEffect(MobEffects.POISON, (20 * 7) * multiplier, 0));
							}
							
							if (Ability.STRENGTH.hasAbility(nbt) && rand.nextInt(10) == 0) 
							{
								int multiplier = (int) Ability.STRENGTH.getMultiplier(Ability.STRENGTH.getTier(nbt), nbt);
								player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, (20 * 5) * multiplier, 0));
							}
							
							if (Ability.ELEMENTAL.hasAbility(nbt) && rand.nextInt(5) == 0)
							{
								int multiplier = (int) Ability.ELEMENTAL.getMultiplier(Ability.ELEMENTAL.getTier(nbt), nbt);
								int var1 = rand.nextInt(3);
								if (var1 == 0) enemy.setFire(4 * multiplier);
								if (var1 == 1) enemy.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, (20 * 4) * multiplier, 10));
								if (var1 == 2) enemy.addPotionEffect(new PotionEffect(MobEffects.POISON, (20 * 7) * multiplier, 0));
							}
							
							if (Ability.DARKNESS.hasAbility(nbt) && rand.nextInt(10) == 0) 
							{
								int multiplier = (int) Ability.DARKNESS.getMultiplier(Ability.DARKNESS.getTier(nbt), nbt);
								enemy.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, (20 * 5) * multiplier, 0));
							}
							
							if (Ability.LIGHT.hasAbility(nbt))
							{
								int multiplier = (int) Ability.LIGHT.getMultiplier(Ability.LIGHT.getTier(nbt), nbt);
								enemy.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, (20 * 5) * multiplier, 0));
								int var = rand.nextInt(5);
								if (var == 0) enemy.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, (20 * 3) * multiplier, 0));
							}
							
							if (Ability.BLOODLUST.hasAbility(nbt) && rand.nextInt(10) == 0)
							{
								int bonusDamage = (int) Ability.BLOODLUST.getMultiplier(Ability.BLOODLUST.getTier(nbt), nbt);
								int var = rand.nextInt(10) + 1 + bonusDamage;
								enemy.setHealth(enemy.getHealth() - var);
							}
							
							if (Ability.STING.hasAbility(nbt) && rand.nextInt(10) == 0) 
							{
								int multiplier = (int) Ability.STING.getMultiplier(Ability.STING.getTier(nbt), nbt);
								enemy.setHealth(enemy.getHealth() - (10 * multiplier));
							}
							
							if (Ability.ETHEREAL.hasAbility(nbt) && rand.nextInt(10) == 0)
							{
								int multiplier = (int) Ability.ETHEREAL.getMultiplier(Ability.ETHEREAL.getTier(nbt), nbt);
								float health = player.getHealth() + (event.getAmount() / (2 * multiplier));
								player.setHealth(health);
							}
							
							if (Ability.CHAINED.hasAbility(nbt) && rand.nextInt(10) == 0)
							{
								int multiplier = (int) Ability.CHAINED.getMultiplier(Ability.CHAINED.getTier(nbt), nbt);
								int radius = 10;
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
							
							if (Ability.VOID.hasAbility(nbt) && rand.nextInt(50) == 0)
							{
								enemy.attackEntityFrom(DamageSource.causePlayerDamage(player), 10000);
							}
						}
						
						/*
						 * Leveling system
						 * 
						 * Untweaked
						 */
						experience = Experience.getExperience(nbt);
						level = Experience.getNextLevel(player, stack, nbt, AbilityHelper.WEAPON, level, experience, rand);
						Experience.setLevel(nbt, level);
						
						NBTHelper.saveStackNBT(stack, nbt);
					}
				}
			}
		}
		
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
							 * Untweaked
							 */
							if (enemy != null)
							{
								if (Ability.FIRE.hasAbility(nbt)) 
								{
									int multiplier = (int) Ability.FIRE.getMultiplier(Ability.FIRE.getTier(nbt), nbt);
									enemy.setFire(4 * multiplier);
								}
								
								if (Ability.FROST.hasAbility(nbt)) 
								{
									int multiplier = (int) Ability.FROST.getMultiplier(Ability.FROST.getTier(nbt), nbt);
									enemy.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, (20 * 4) * multiplier, 10));
								}
								
								if (Ability.POISON.hasAbility(nbt)) 
								{
									int multiplier = (int) Ability.POISON.getMultiplier(Ability.POISON.getTier(nbt), nbt);
									enemy.addPotionEffect(new PotionEffect(MobEffects.POISON, (20 * 7) * multiplier, 0));
								}
								
								if (Ability.STRENGTH.hasAbility(nbt) && rand.nextInt(10) == 0) 
								{
									int multiplier = (int) Ability.STRENGTH.getMultiplier(Ability.STRENGTH.getTier(nbt), nbt);
									player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, (20 * 5) * multiplier, 0));
								}
								
								if (Ability.ELEMENTAL.hasAbility(nbt) && rand.nextInt(5) == 0)
								{
									int multiplier = (int) Ability.ELEMENTAL.getMultiplier(Ability.ELEMENTAL.getTier(nbt), nbt);
									int var1 = rand.nextInt(3);
									if (var1 == 0) enemy.setFire(4 * multiplier);
									if (var1 == 1) enemy.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, (20 * 4) * multiplier, 10));
									if (var1 == 2) enemy.addPotionEffect(new PotionEffect(MobEffects.POISON, (20 * 7) * multiplier, 0));
								}
								
								if (Ability.DARKNESS.hasAbility(nbt) && rand.nextInt(10) == 0) 
								{
									int multiplier = (int) Ability.DARKNESS.getMultiplier(Ability.DARKNESS.getTier(nbt), nbt);
									enemy.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, (20 * 5) * multiplier, 0));
								}
								
								if (Ability.LIGHT.hasAbility(nbt))
								{
									int multiplier = (int) Ability.LIGHT.getMultiplier(Ability.LIGHT.getTier(nbt), nbt);
									enemy.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, (20 * 5) * multiplier, 0));
									int var = rand.nextInt(5);
									if (var == 0) enemy.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, (20 * 3) * multiplier, 0));
								}
								
								if (Ability.BLOODLUST.hasAbility(nbt) && rand.nextInt(10) == 0)
								{
									int bonusDamage = (int) Ability.BLOODLUST.getMultiplier(Ability.BLOODLUST.getTier(nbt), nbt);
									int var = rand.nextInt(10) + 1 + bonusDamage;
									enemy.setHealth(enemy.getHealth() - var);
								}
								
								if (Ability.STING.hasAbility(nbt) && rand.nextInt(10) == 0) 
								{
									int multiplier = (int) Ability.STING.getMultiplier(Ability.STING.getTier(nbt), nbt);
									enemy.setHealth(enemy.getHealth() - (10 * multiplier));
								}
								
								if (Ability.ETHEREAL.hasAbility(nbt) && rand.nextInt(10) == 0)
								{
									int multiplier = (int) Ability.ETHEREAL.getMultiplier(Ability.ETHEREAL.getTier(nbt), nbt);
									float health = player.getHealth() + (event.getAmount() / (2 * multiplier));
									player.setHealth(health);
								}
								
								if (Ability.CHAINED.hasAbility(nbt) && rand.nextInt(10) == 0)
								{
									int multiplier = (int) Ability.CHAINED.getMultiplier(Ability.CHAINED.getTier(nbt), nbt);
									int radius = 10;
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
								
								if (Ability.VOID.hasAbility(nbt) && rand.nextInt(10) == 0)
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
							 * Untweaked
							 */
							if (Ability.MOLTEN.hasAbility(nbt) && rand.nextInt(5) == 0) 
							{
								int multiplier = (int) Ability.MOLTEN.getMultiplier(Ability.MOLTEN.getTier(nbt), nbt);
								enemy.setFire(4 * multiplier);
							}
							
							if (Ability.FROZEN.hasAbility(nbt) && rand.nextInt(5) == 0) 
							{
								int multiplier = (int) Ability.FROZEN.getMultiplier(Ability.FROZEN.getTier(nbt), nbt);
								enemy.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, (20 * 4) * multiplier, 10));
							}
							
							if (Ability.TOXIC.hasAbility(nbt) && rand.nextInt(5) == 0) 
							{
								int multiplier = (int) Ability.TOXIC.getMultiplier(Ability.TOXIC.getTier(nbt), nbt);
								enemy.addPotionEffect(new PotionEffect(MobEffects.POISON, (20 * 7) * multiplier, 0));
							}
							
							if (Ability.BEASTIAL.hasAbility(nbt))
							{
								int multiplier = (int) Ability.BEASTIAL.getMultiplier(Ability.BEASTIAL.getTier(nbt), nbt);
								
								if (player.getHealth() <= 4F * multiplier) 
								{
									player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 20 * 1, 2));
								}
							}
							
							if (Ability.ENLIGHTENED.hasAbility(nbt) && rand.nextInt(7) == 0)
							{
								int multiplier = (int) Ability.ENLIGHTENED.getMultiplier(Ability.ENLIGHTENED.getTier(nbt), nbt);
								float health = player.getHealth() + (event.getAmount() / (2 / multiplier));
								player.setHealth(health);
							}
							
							if (Ability.ABSORB.hasAbility(nbt) && rand.nextInt(7) == 0) 
							{
								int multiplier = (int) Ability.ABSORB.getMultiplier(Ability.ABSORB.getTier(nbt), nbt);
								player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, (20 * 10) * multiplier, 0));
							}
							
							if (Ability.HARDENED.hasAbility(nbt) && rand.nextInt(10) == 0) 
							{
								event.setAmount(0F);
							}
							
							if (Ability.INVISIBILITY.hasAbility(nbt) && rand.nextInt(10) == 0) 
							{
								int multiplier = (int) Ability.INVISIBILITY.getMultiplier(Ability.INVISIBILITY.getTier(nbt), nbt);
								player.addPotionEffect(new PotionEffect(MobEffects.INVISIBILITY, (20 * 5) * multiplier, 0));
							}
							
							if (Ability.VOID_ARMOR.hasAbility(nbt) && rand.nextInt(50) == 0) 
							{
								enemy.attackEntityFrom(DamageSource.causePlayerDamage(player), 10000);
							}
							
							/*
							 * Leveling system
							 * 
							 * Untweaked
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
		
		/*
		 * ENEMY LEVELING
		 * 
		 * Untweaked
		 */
		if (ConfigHandler.ENEMY_ABILITIES)
		{
			if (event.getEntityLiving() instanceof EntityPlayer && event.getSource().getSourceOfDamage() instanceof EntityMob)
			{
				Random rand = event.getEntityLiving().worldObj.rand;
				EntityPlayer player = (EntityPlayer) event.getEntityLiving();
				EntityMob mob = (EntityMob) event.getSource().getSourceOfDamage();
				
				if (!mob.worldObj.isRemote)
				{	
					IEnemyLevel enemyLevel = mob.getCapability(CapabilityEnemyLevel.ENEMY_LEVEL_CAP, null);

					if (enemyLevel != null)
					{
						int level = enemyLevel.getEnemyLevel();
						
						if (level > 0)
						{
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
					}
				}
			}
		}
	}
}

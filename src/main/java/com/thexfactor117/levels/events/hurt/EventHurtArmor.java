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
 * Tweaked
 */
public class EventHurtArmor 
{
	@SubscribeEvent
	public void hitEntity(LivingHurtEvent event)
	{
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
								Experience.setExperience(nbt, Experience.getExperience(nbt) + 1);
							}
							
							/*
							 * Rarities
							 */
							switch (rarity)
							{
								case UNCOMMON:
									int var = rand.nextInt(10);
									if (var == 0) Experience.setExperience(nbt, Experience.getExperience(nbt) + rand.nextInt(5));
									break;
								case RARE:
									int var1 = rand.nextInt(7);
									if (var1 == 0) Experience.setExperience(nbt, Experience.getExperience(nbt) + rand.nextInt(10));
									break;
								case LEGENDARY:
									int var2 = rand.nextInt(5); // chance
									int var3 = rand.nextInt(20) + 5;
									if (var2 == 0) Experience.setExperience(nbt, Experience.getExperience(nbt) + var3);
									break;
								case ANCIENT:
									int var4 = rand.nextInt(4); // chance
									int var5 = rand.nextInt(20) + 10;
									if (var4 == 0) Experience.setExperience(nbt, Experience.getExperience(nbt) + var5);
									break;
								default:
									break;
							}
							
							/*
							 * Durability
							 */
							if (ConfigHandler.DURABILITY)
							{
								// durability boosts
								switch (rarity)
								{
									case UNCOMMON:
										if (stack.getItemDamage() == stack.getMaxDamage()) break;
										if (rand.nextInt(7) == 0) stack.setItemDamage(stack.getItemDamage() - 1); // reduction
										if (rand.nextInt(20) == 0) stack.setItemDamage(stack.getItemDamage() - rand.nextInt(3)); // bonus
										break;
									case RARE:
										if (stack.getItemDamage() == stack.getMaxDamage()) break;
										if (rand.nextInt(5) == 0) stack.setItemDamage(stack.getItemDamage() - 1); // reduction
										if (rand.nextInt(10) == 0) stack.setItemDamage(stack.getItemDamage() - rand.nextInt(3)); // bonus
										break;
									case LEGENDARY:
										if (stack.getItemDamage() == stack.getMaxDamage()) break;
										if (rand.nextInt(4) == 0) stack.setItemDamage(stack.getItemDamage() - 1); // reduction
										if (rand.nextInt(7) == 0) stack.setItemDamage(stack.getItemDamage() - rand.nextInt(5)); // bonus
										break;
									case ANCIENT:
										if (stack.getItemDamage() == stack.getMaxDamage()) break;
										if (rand.nextInt(2) == 0) stack.setItemDamage(stack.getItemDamage() - 1); // reduction
										if (rand.nextInt(5) == 0) stack.setItemDamage(stack.getItemDamage() - rand.nextInt(5)); // bonus
										break;
									default:
										break;
								}
							}
							else
							{
								stack.setItemDamage(-stack.getMaxDamage());
							}
							
							/*
							 * Abilities
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

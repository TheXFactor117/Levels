package com.thexfactor117.levels.events;

import java.util.Random;

import com.thexfactor117.levels.handlers.ConfigHandler;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

/**
 * 
 * @author TheXFactor117
 *
 */
public class EventLivingHurt 
{	
	/**
	 * Fired when an entity is attacked by the player.
	 * @param event
	 */
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
			Random rand = new Random();
			EntityPlayer player = (EntityPlayer) event.source.getEntity();
			EntityLivingBase enemy = event.entityLiving;
			ItemStack stack = player.inventory.getCurrentItem();
			
			if (stack != null)
			{
				if (stack.getItem() instanceof ItemSword)
				{
					NBTTagCompound nbt = stack.getTagCompound();
					
					/*
					 * Add experience to weapons based on level.
					 */
					if (nbt != null)
					{
						/*
						 * Experience
						 */
						if (nbt.getInteger("LEVEL") == 1 || nbt.getInteger("LEVEL") == 2 || nbt.getInteger("LEVEL") == 3)
						{
							nbt.setInteger("EXPERIENCE", nbt.getInteger("EXPERIENCE") + 1);
						}
						else if (nbt.getInteger("LEVEL") == 4 || nbt.getInteger("LEVEL") == 5)
						{
							int var = rand.nextInt(4);
							if (var == 0)
							{
								nbt.setInteger("EXPERIENCE", nbt.getInteger("EXPERIENCE") + 2);
							}
							else
							{
								nbt.setInteger("EXPERIENCE", nbt.getInteger("EXPERIENCE") + 1);
							}
						}
						else
						{
							int var = rand.nextInt(3);
							if (var == 0)
							{
								nbt.setInteger("EXPERIENCE", nbt.getInteger("EXPERIENCE") + 2);
							}
							else
							{
								nbt.setInteger("EXPERIENCE", nbt.getInteger("EXPERIENCE") + 1);
							}
						}
						
						/*
						 * Rarity
						 */
						boolean developmentEnvironment = (Boolean)Launch.blackboard.get("fml.deobfuscatedEnvironment");
						
						if (developmentEnvironment || ConfigHandler.enableDevFeatures)
						{							
							if (nbt.getInteger("RARITY") != 1 && nbt.getInteger("RARITY") != 0)
							{
								if (nbt.getInteger("RARITY") == 2)
								{
									event.ammount = event.ammount * 1.5F;
								}
								
								if (nbt.getInteger("RARITY") == 3)
								{
									event.ammount = event.ammount * 1.5F;
									
									int var = rand.nextInt(2);
									if (var == 0)
									{
										stack.setItemDamage(stack.getItemDamage() + 1);
									}
								}
								
								if (nbt.getInteger("RARITY") == 4)
								{
									event.ammount = event.ammount * 2.0F;
									
									int var = rand.nextInt(10);
									if (var <= 5)
									{
										stack.setItemDamage(stack.getItemDamage() + 1);
									}
									
									int var1 = rand.nextInt(20);
									if (var1 == 0)
									{
										stack.setItemDamage(stack.getItemDamage() + 20);
									}
								}
								
								if (nbt.getInteger("RARITY") == 5)
								{
									event.ammount = event.ammount * 3.0F;
									
									int var = rand.nextInt(4);
									if (var != 0)
									{
										stack.setItemDamage(stack.getItemDamage() + 1);
									}
									
									int var1 = rand.nextInt(10);
									if (var1 == 0)
									{
										stack.setItemDamage(stack.getItemDamage() + 20);
									}
								}
							}
						}
						
						/*
						 * Abilities
						 */
						if (enemy != null)
						{
							if (nbt.getBoolean("FIRE")) enemy.setFire(4);
							if (nbt.getBoolean("FROST")) enemy.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 20*4, 10));
							if (nbt.getBoolean("POISON")) enemy.addPotionEffect(new PotionEffect(Potion.poison.id, 20*4, 0));
							if (nbt.getBoolean("Strength")) 
							{
								int var = rand.nextInt(10);
								if (var == 0) player.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 20*10, 0));
							}
							
							if (nbt.getBoolean("ETHEREAL"))
							{
								float healthToBeAdded = 5F;
								
								int var1 = rand.nextInt(4);
								if (var1 == 0) 
								{				
									player.setHealth(player.getHealth() + healthToBeAdded);
									
									if (player.getHealth() > 20)
									{
										player.setHealth(20);
									}
								}
							}
							
							if (nbt.getBoolean("VOID"))
							{
								int var2 = rand.nextInt(20);
								if (var2 == 0)
								{
									enemy.setHealth(0);
								}
							}
						}
					}
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
			Random rand = new Random();
			EntityLivingBase attacker = (EntityLivingBase) event.source.getEntity();
			EntityPlayer player = (EntityPlayer) event.entityLiving;
			
			if (attacker instanceof EntityMob)
			{
				for (int i = 0; i < 4; i++)
				{
					if (player.getCurrentArmor(i) != null)
					{
						if (player.getCurrentArmor(i).getItem() instanceof ItemArmor && player.getCurrentArmor(i).getTagCompound() != null)
						{
							NBTTagCompound nbt = player.getCurrentArmor(i).getTagCompound();
							
							/*
							 * Experience
							 */
							if (nbt.getInteger("LEVEL") > 3)
							{
								int var = rand.nextInt(3);
								int var1 = rand.nextInt(3);
								if (var == 0)
								{
									nbt.setInteger("EXPERIENCE", nbt.getInteger("EXPERIENCE") + 1 + var1);
								}
							}
							else
							{
								nbt.setInteger("EXPERIENCE", nbt.getInteger("EXPERIENCE") + 1);
							}
							
							/*
							 * Abilities
							 */
							if (nbt != null)
							{
								if (nbt.getBoolean("HARDENED"))
								{
									int var = rand.nextInt(5);
									if (var == 0)
									{
										attacker.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 20*2, 10));
									}
								}
								
								if (nbt.getBoolean("POISONED"))
								{
									int var = rand.nextInt(5);
									if (var == 0)
									{
										attacker.addPotionEffect(new PotionEffect(Potion.poison.id, 20*10, 0));
									}
								}
								
								if (nbt.getBoolean("Strength"))
								{
									int var = rand.nextInt(10);
									if (var == 0)
									{
										player.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 20*10, 0));
									}
								}
								
								if (nbt.getBoolean("ETHEREAL"))
								{
									int var = rand.nextInt(20);
									if (var == 0)
									{
										player.setHealth(20.0F);
									}
								}
								
								if (nbt.getBoolean("VOID"))
								{
									int var = rand.nextInt(20);
									if (var == 0)
									{
										attacker.setHealth(0);
									}
								}
							}
						}
					}
				}
			}
		}
	}
}

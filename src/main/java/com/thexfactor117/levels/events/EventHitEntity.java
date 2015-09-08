package com.thexfactor117.levels.events;

import java.util.Random;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

/**
 * 
 * @author TheXFactor117
 *
 */
public class EventHitEntity 
{	
	/**
	 * Fired when an entity is attacked by the player.
	 * @param event
	 */
	@SubscribeEvent
	public void hitEntity(LivingAttackEvent event)
	{
		if (event.source.getSourceOfDamage() instanceof EntityPlayer)
		{
			Random rand = new Random();
			EntityPlayer player = (EntityPlayer) event.source.getEntity();
			EntityLivingBase enemy = event.entityLiving;
			ItemStack stack = player.inventory.getCurrentItem();
			
			if (stack != null)
			{
				NBTTagCompound nbt = stack.getTagCompound();
				
				/*
				 * Add experience to weapons based on level.
				 */
				if (nbt != null)
				{
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
						int var = rand.nextInt(9);
						if (var == 0) player.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 20*10, 0));
					}
					
					if (nbt.getBoolean("ETHEREAL"))
					{
						float healthToBeAdded = 5F;
						
						int var1 = rand.nextInt(3);
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
						int var2 = rand.nextInt(19);
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

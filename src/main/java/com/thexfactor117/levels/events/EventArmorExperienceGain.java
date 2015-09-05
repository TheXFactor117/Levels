package com.thexfactor117.levels.events;

import java.util.Random;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
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
public class EventArmorExperienceGain 
{
	/**
	 * Event used to add experience to armor when damaged by valid entities.
	 * @param event
	 */
	@SubscribeEvent
	public void gainExperience(LivingHurtEvent event)
	{
		if (event.source.getSourceOfDamage() instanceof EntityLivingBase)
		{
			if (event.entity instanceof EntityPlayer)
			{
				EntityPlayer player = (EntityPlayer) event.entity;
				EntityLivingBase enemy = (EntityLivingBase) event.source.getSourceOfDamage();
				Random rand = new Random();
				
				for (int i = 0; i < 4; i++)
				{
					if (player.getCurrentArmor(i) != null)
					{
						if (player.getCurrentArmor(i).getTagCompound() != null)
						{
							NBTTagCompound nbt = player.getCurrentArmor(i).getTagCompound();
							
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
							
							if (nbt != null)
							{
								if (nbt.getBoolean("HARDENED"))
								{
									int var = rand.nextInt(5);
									if (var == 0)
									{
										enemy.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 20*2, 10));
									}
								}
								
								if (nbt.getBoolean("POISONED"))
								{
									int var = rand.nextInt(5);
									if (var == 0)
									{
										enemy.addPotionEffect(new PotionEffect(Potion.poison.id, 20*10, 0));
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
										enemy.setDead();
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

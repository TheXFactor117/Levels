package com.thexfactor117.levels.events;

import java.util.Random;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import com.thexfactor117.levels.helpers.LogHelper;

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
							
							if (nbt != null)
							{
								if (nbt.getBoolean("POISONED"))
								{
									int var = rand.nextInt(1);
									if (var == 0)
									{
										enemy.addPotionEffect(new PotionEffect(Potion.poison.id, 20*10, 1));
										enemy.setFire(4);
										LogHelper.info("Hello?");
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

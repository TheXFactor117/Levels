package com.thexfactor117.levels.events.hurt;

import java.util.Random;

import com.thexfactor117.levels.capabilities.CapabilityEnemyLevel;
import com.thexfactor117.levels.capabilities.IEnemyLevel;
import com.thexfactor117.levels.handlers.ConfigHandler;

import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * 
 * @author TheXFactor117
 *
 */
public class EventHurtEnemyLeveling 
{
	@SubscribeEvent
	public void hitEntity(LivingHurtEvent event)
	{
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

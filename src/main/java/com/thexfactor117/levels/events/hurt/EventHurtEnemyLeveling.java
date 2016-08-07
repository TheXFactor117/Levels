package com.thexfactor117.levels.events.hurt;

import java.util.Random;

import com.thexfactor117.levels.capabilities.CapabilityEnemyLevel;
import com.thexfactor117.levels.capabilities.IEnemyLevel;
import com.thexfactor117.levels.handlers.ConfigHandler;

import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * 
 * @author TheXFactor117
 * 
 * Tweaked
 */
public class EventHurtEnemyLeveling 
{
	@SubscribeEvent
	public void hitEntity(LivingHurtEvent event)
	{
		/*
		 * ENEMY LEVELING
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
							switch (level)
							{
								case 4:
									int var = rand.nextInt(10);
									int var1 = rand.nextInt(3);
									if (var == 0)
									{
										switch (var1)
										{
											case 0: 
												player.setFire(4);
												break;
											case 1: 
												player.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 20*4, 20));
												break;
											case 2: 
												player.addPotionEffect(new PotionEffect(MobEffects.POISON, 20*7, 0));
												break;
										}
									}
									break;
								case 5:
									int var2 = rand.nextInt(7);
									int var3 = rand.nextInt(3);
									if (var2 == 0)
									{
										switch (var3)
										{
											case 0: 
												player.setFire(6);
												break;
											case 1: 
												player.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 20*4, 20));
												break;
											case 2: 
												player.addPotionEffect(new PotionEffect(MobEffects.POISON, 20*7, 1));
												break;
										}
									}
									break;
								case 6:
									int var4 = rand.nextInt(5);
									int var5 = rand.nextInt(3);
									if (var4 == 0)
									{
										switch (var5)
										{
											case 0: 
												player.setFire(10);
												break;
											case 1: 
												player.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 20*4, 20));
												break;
											case 2: 
												player.addPotionEffect(new PotionEffect(MobEffects.POISON, 20*7, 2));
												break;
										}
									}
									break;
								default:
									break;
							}
						}
					}
				}
			}
		}
	}
}

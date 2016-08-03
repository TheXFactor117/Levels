package com.thexfactor117.levels.events;

import java.util.Random;

import com.thexfactor117.levels.capabilities.CapabilityEnemyLevel;
import com.thexfactor117.levels.capabilities.IEnemyLevel;
import com.thexfactor117.levels.handlers.ConfigHandler;
import com.thexfactor117.levels.leveling.AbilityHelper;
import com.thexfactor117.levels.leveling.Experience;
import com.thexfactor117.levels.utils.NBTHelper;

import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * 
 * @author TheXFactor117
 *
 * Untweaked
 */
public class EventLivingDeath 
{
	/**
	 * Called when the specifed entity dies by another specific source. In this case, the source
	 * is the player.
	 * @param event
	 */
	@SubscribeEvent
	public void onEntityDeath(LivingDeathEvent event)
	{	
		/*
		 * 
		 * MELEE WEAPONS
		 * 
		 * Untweaked
		 *
		 */
		if (event.getSource().getSourceOfDamage() instanceof EntityPlayer && !(event.getSource().getSourceOfDamage() instanceof FakePlayer))
		{
			EntityPlayer player = (EntityPlayer) event.getSource().getSourceOfDamage();
			Random rand = player.worldObj.rand;
			ItemStack stack = player.inventory.getCurrentItem();
			
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
						 * Weapon Bonus Experience
						 * 
						 * Tweaked
						 */
						if (level < ConfigHandler.MAX_LEVEL_CAP)
						{
							if (event.getEntityLiving() instanceof EntityMob)
							{
								EntityMob enemy = (EntityMob) event.getEntityLiving();
								IEnemyLevel enemyLevel = enemy.getCapability(CapabilityEnemyLevel.ENEMY_LEVEL_CAP, null);
								int bonusXP = 0;
								
								if (enemy.getMaxHealth() <= 10) bonusXP = 0;
								if (enemy.getMaxHealth() > 10 && enemy.getMaxHealth() <= 25) bonusXP = 1;
								if (enemy.getMaxHealth() > 25 && enemy.getMaxHealth() <= 40) bonusXP = 2;
								if (enemy.getMaxHealth() > 40 && enemy.getMaxHealth() <= 75) bonusXP = 3;
								if (enemy.getMaxHealth() > 75) bonusXP = 4;

								if (enemyLevel != null && enemyLevel.getEnemyLevel() > 0)
								{
									switch (enemyLevel.getEnemyLevel())
									{
										case 1: 
											bonusXP *= 0;
											break;
										case 2: 
											bonusXP *= 1;
											break;
										case 3: 
											bonusXP *= 1.5;
											break;
										case 4: 
											bonusXP *= 2;
											break;
										case 5: 
											bonusXP *= 2.5;
											break;
										case 6: 
											bonusXP *= 3;
											break;
									}
									
									Experience.setExperience(nbt, Experience.getExperience(nbt) + bonusXP);
								}
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
			
			if (arrow.shootingEntity instanceof EntityPlayer)
			{
				EntityPlayer player = (EntityPlayer) arrow.shootingEntity;
				Random rand = player.worldObj.rand;
				ItemStack stack = player.inventory.getCurrentItem();
				
				if (stack != null)
				{
					NBTTagCompound nbt = NBTHelper.loadStackNBT(stack);
					int level = Experience.getLevel(nbt);
					int experience = Experience.getExperience(nbt);
					
					/*
					 * Bow bonus experience
					 * 
					 * Tweaked
					 */
					if (level < ConfigHandler.MAX_LEVEL_CAP)
					{
						if (event.getEntityLiving() instanceof EntityMob)
						{
							EntityMob enemy = (EntityMob) event.getEntityLiving();
							IEnemyLevel enemyLevel = enemy.getCapability(CapabilityEnemyLevel.ENEMY_LEVEL_CAP, null);
							int bonusXP = 0;
							
							if (enemy.getMaxHealth() <= 10) bonusXP = 0;
							if (enemy.getMaxHealth() > 10 && enemy.getMaxHealth() <= 25) bonusXP = 1;
							if (enemy.getMaxHealth() > 25 && enemy.getMaxHealth() <= 40) bonusXP = 2;
							if (enemy.getMaxHealth() > 40 && enemy.getMaxHealth() <= 75) bonusXP = 3;
							if (enemy.getMaxHealth() > 75) bonusXP = 4;

							if (enemyLevel != null && enemyLevel.getEnemyLevel() > 0)
							{
								switch (enemyLevel.getEnemyLevel())
								{
									case 1: 
										bonusXP *= 0;
										break;
									case 2: 
										bonusXP *= 1;
										break;
									case 3: 
										bonusXP *= 1.5;
										break;
									case 4: 
										bonusXP *= 2;
										break;
									case 5: 
										bonusXP *= 2.5;
										break;
									case 6: 
										bonusXP *= 3;
										break;
								}
								
								Experience.setExperience(nbt, Experience.getExperience(nbt) + bonusXP);
							}
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
}

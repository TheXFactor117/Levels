package com.thexfactor117.levels.event;

import com.thexfactor117.levels.capabilities.CapabilityEnemyLevel;
import com.thexfactor117.levels.capabilities.IEnemyLevel;
import com.thexfactor117.levels.config.Config;
import com.thexfactor117.levels.leveling.Experience;
import com.thexfactor117.levels.util.NBTHelper;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemAxe;
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
 * Updates weapon information when killing an enemy with a valid weapon. Used to update experience,
 * level, abilities, and so on.
 *
 */
public class EventLivingDeath 
{
	@SubscribeEvent
	public void onLivingDeath(LivingDeathEvent event)
	{
		if (event.getSource().getSourceOfDamage() instanceof EntityPlayer && !(event.getSource().getSourceOfDamage() instanceof FakePlayer))
		{
			EntityPlayer player = (EntityPlayer) event.getSource().getSourceOfDamage();
			ItemStack stack = player.inventory.getCurrentItem();
			
			if (stack != null && (stack.getItem() instanceof ItemSword || stack.getItem() instanceof ItemAxe))
			{
				NBTTagCompound nbt = NBTHelper.loadStackNBT(stack);
				
				if (nbt != null)
				{
					addBonusExperience(event, nbt);
					updateLevel(player, stack, nbt);
					NBTHelper.saveStackNBT(stack, nbt);
				}
			}
		}
		else if (event.getSource().getSourceOfDamage() instanceof EntityArrow)
		{
			EntityArrow arrow = (EntityArrow) event.getSource().getSourceOfDamage();
			
			if (arrow.shootingEntity instanceof EntityPlayer)
			{
				EntityPlayer player = (EntityPlayer) arrow.shootingEntity;
				ItemStack stack = player.inventory.getCurrentItem();
				
				if (stack != null)
				{
					NBTTagCompound nbt = NBTHelper.loadStackNBT(stack);
					
					if (nbt != null)
					{
						addBonusExperience(event, nbt);
						updateLevel(player, stack, nbt);
					}
				}
			}
		}
	}
	
	/**
	 * Called everytime an enemy dies. Adds bonus experience based on how much health the enemy had.
	 * @param event
	 * @param nbt
	 */
	private void addBonusExperience(LivingDeathEvent event, NBTTagCompound nbt)
	{
		if (Experience.getLevel(nbt) < Config.maxLevel)
		{
			if (event.getEntityLiving() instanceof EntityLivingBase)
			{
				EntityLivingBase enemy = event.getEntityLiving();
				IEnemyLevel enemyLevel = enemy.getCapability(CapabilityEnemyLevel.ENEMY_LEVEL_CAP, null);
				int bonusExperience = 0;
				
				if (enemy.getMaxHealth() <= 10) bonusExperience = 0;
				else if (enemy.getMaxHealth() > 10 && enemy.getMaxHealth() <= 25) bonusExperience = 1;
				else if (enemy.getMaxHealth() > 25 && enemy.getMaxHealth() <= 40) bonusExperience = 2;
				else if (enemy.getMaxHealth() > 40 && enemy.getMaxHealth() <= 75) bonusExperience = 3;
				else if (enemy.getMaxHealth() > 75) bonusExperience = 4;
				
				if (enemyLevel != null && enemyLevel.getEnemyLevel() > 0)
				{
					int level = enemyLevel.getEnemyLevel();
					
					if (level == 1) bonusExperience *= 0;
					else if (level == 2) bonusExperience *= 1;
					else if (level == 3) bonusExperience *= 1.5D;
					else if (level == 4) bonusExperience *= 2;
					else if (level == 5) bonusExperience *= 2.5D;
					else if (level == 6) bonusExperience *= 3;
				}
				
				Experience.setExperience(nbt, Experience.getExperience(nbt) + bonusExperience);
			}
		}
	}
	
	/**
	 * Called everytime an enemy dies. Used to update the level of the weapon.
	 * @param player
	 * @param stack
	 * @param nbt
	 */
	private void updateLevel(EntityPlayer player, ItemStack stack, NBTTagCompound nbt)
	{
		int level = Experience.getNextLevel(player, stack, nbt, Experience.getLevel(nbt), Experience.getExperience(nbt));
		Experience.setLevel(nbt, level);
	}
}

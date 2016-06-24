package com.thexfactor117.levels.events;

import java.util.Random;

import com.thexfactor117.levels.handlers.ConfigHandler;
import com.thexfactor117.levels.leveling.AbilityHelper;
import com.thexfactor117.levels.leveling.Experience;
import com.thexfactor117.xlib.misc.NBTHelper;

import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
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
		/*****************
		 * MELEE WEAPONS *
		 *****************/
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
						 */
						if (level < ConfigHandler.MAX_LEVEL_CAP)
						{
							if (event.getEntityLiving() instanceof EntityMob)
							{
								Experience.setExperience(nbt, Experience.getExperience(nbt) + ConfigHandler.MONSTER_BONUS_XP);
							}

							if (event.getEntityLiving() instanceof EntityAnimal)
							{
								Experience.setExperience(nbt, Experience.getExperience(nbt) + ConfigHandler.ANIMAL_BONUS_XP);
							}
						}

						/*
						 * Leveling system
						 */
						experience = Experience.getExperience(nbt);
						level = Experience.getNextLevel(player, nbt, AbilityHelper.WEAPON, level, experience, rand);
						Experience.setLevel(nbt, level);
						
						NBTHelper.saveStackNBT(stack, nbt);
					}
				}
			}
		}
		
		/********
		 * BOWS *
		 *******/
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
					 */
					if (level < ConfigHandler.MAX_LEVEL_CAP)
					{
						if (event.getEntityLiving() instanceof EntityMob)
						{
							Experience.setExperience(nbt, Experience.getExperience(nbt) + ConfigHandler.MONSTER_BONUS_XP);
						}

						if (event.getEntityLiving() instanceof EntityAnimal)
						{
							Experience.setExperience(nbt, Experience.getExperience(nbt) + ConfigHandler.ANIMAL_BONUS_XP);
						}
					}
					
					/*
					 * Leveling system
					 */
					experience = Experience.getExperience(nbt);
					level = Experience.getNextLevel(player, nbt, AbilityHelper.WEAPON, level, experience, rand);
					Experience.setLevel(nbt, level);
					
					NBTHelper.saveStackNBT(stack, nbt);
				}
			}
		}
	}
}

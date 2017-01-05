package com.thexfactor117.levels.event;

import com.thexfactor117.levels.leveling.Experience;
import com.thexfactor117.levels.util.ConfigHandler;
import com.thexfactor117.levels.util.NBTHelper;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
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
	}
	
	/**
	 * Called everytime an enemy dies. Adds bonus experience based on how much health the enemy had.
	 * @param event
	 * @param nbt
	 */
	private void addBonusExperience(LivingDeathEvent event, NBTTagCompound nbt)
	{
		if (Experience.getLevel(nbt) < ConfigHandler.MAX_LEVEL)
		{
			if (event.getEntityLiving() instanceof EntityLivingBase)
			{
				EntityLivingBase enemy = event.getEntityLiving();
				int bonusExperience = 0;
				
				if (enemy.getMaxHealth() <= 10) bonusExperience = 0;
				else if (enemy.getMaxHealth() > 10 && enemy.getMaxHealth() <= 25) bonusExperience = 1;
				else if (enemy.getMaxHealth() > 25 && enemy.getMaxHealth() <= 40) bonusExperience = 2;
				else if (enemy.getMaxHealth() > 40 && enemy.getMaxHealth() <= 75) bonusExperience = 3;
				else if (enemy.getMaxHealth() > 75) bonusExperience = 4;
				
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

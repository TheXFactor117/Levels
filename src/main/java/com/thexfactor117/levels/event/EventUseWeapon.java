package com.thexfactor117.levels.event;

import com.thexfactor117.levels.leveling.Experience;
import com.thexfactor117.levels.util.Config;
import com.thexfactor117.levels.util.NBTHelper;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * 
 * @author TheXFactor117
 * 
 * Handles adding experience, leveling up, and using attributes.
 *
 */
public class EventUseWeapon 
{
	/**
	 * Called every time a living entity attacks.
	 * @param event
	 */
	@SubscribeEvent
	public void onLivingHurt(LivingHurtEvent event)
	{
		if (event.getSource().getSourceOfDamage() instanceof EntityPlayer && !(event.getSource().getSourceOfDamage() instanceof FakePlayer))
		{
			// WEAPONS
			EntityPlayer player = (EntityPlayer) event.getSource().getSourceOfDamage();
			EntityLivingBase enemy = event.getEntityLiving();
			ItemStack stack = player.inventory.getCurrentItem();
			
			if (stack.getItem() instanceof ItemSword || stack.getItem() instanceof ItemAxe)
			{
				NBTTagCompound nbt = NBTHelper.loadStackNBT(stack);
				
				if (nbt != null)
				{
					addExperience(nbt, stack, enemy);
				}
			}
		}
		else if (event.getSource().getSourceOfDamage() instanceof EntityLivingBase && event.getEntityLiving() instanceof EntityPlayer)
		{
			// ARMOR
			EntityPlayer player = (EntityPlayer) event.getEntityLiving();
			EntityLivingBase enemy = (EntityLivingBase) event.getSource().getSourceOfDamage();
			
			for (ItemStack stack : player.inventory.armorInventory)
			{
				if (stack != null && stack.getItem() instanceof ItemArmor)
				{
					NBTTagCompound nbt = NBTHelper.loadStackNBT(stack);
					
					if (nbt != null)
					{
						addExperience(nbt, stack, enemy);
					}
				}
			}
		}
	}
	
	/**
	 * Called every time a living entity dies.
	 * @param event
	 */
	@SubscribeEvent
	public void onLivingDeath(LivingDeathEvent event)
	{
		
	}

	private void addExperience(NBTTagCompound nbt, ItemStack stack, EntityLivingBase enemy)
	{
		if (Experience.getLevel(nbt) < Config.maxLevel)
		{
			boolean isDev = (Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");
			
			if (isDev)
			{
				Experience.setExperience(nbt, Experience.getExperience(nbt) + 20);
			}
			else
			{
				if (stack.getItem() instanceof ItemArmor)
				{
					int xp = (int) (enemy.getMaxHealth() * 0.2);
					Experience.setExperience(nbt, Experience.getExperience(nbt) + xp);
				}
				else
				{
					int xp = (int) (enemy.getMaxHealth() * 0.2);
					Experience.setExperience(nbt, Experience.getExperience(nbt) + xp);
				}
			}
		}
	}
	
	private void useRarity(NBTTagCompound nbt)
	{
		
	}
	
	private void useAttributes(NBTTagCompound nbt)
	{
		
	}
	
	private void attemptLevel(NBTTagCompound nbt)
	{
		
	}
}

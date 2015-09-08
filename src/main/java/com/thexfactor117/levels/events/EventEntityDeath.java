package com.thexfactor117.levels.events;

import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EventEntityDeath 
{
	@SubscribeEvent
	public void onEntityDeath(LivingDeathEvent event)
	{
		if (event.source.getSourceOfDamage() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.source.getEntity();
			ItemStack stack = player.inventory.getCurrentItem();
			
			if (stack != null)
			{
				NBTTagCompound nbt = stack.getTagCompound();
				
				if (nbt != null)
				{
					if (event.entityLiving instanceof EntityMob)
					{
						nbt.setInteger("EXPERIENCE", nbt.getInteger("EXPERIENCE") + 5000);
					}
					
					if (event.entityLiving instanceof EntityAnimal)
					{
						nbt.setInteger("EXPERIENCE", nbt.getInteger("EXPERIENCE") + 2);
					}
				}
			}
		}
	}
}

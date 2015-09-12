package com.thexfactor117.levels.events;

import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

/**
 * 
 * @author TheXFactor117
 *
 */
public class EventLivingDeath 
{
	/**
	 * Called when the specifed entity dies by the specific source (the Player). Used to determine how
	 * how much bonus experience should be given to the weapon when dealing the killing blow.
	 * @param event
	 */
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
						nbt.setInteger("EXPERIENCE", nbt.getInteger("EXPERIENCE") + 10);
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

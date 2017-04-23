package com.thexfactor117.levels.event;

import com.thexfactor117.levels.config.Config;
import com.thexfactor117.levels.util.NBTHelper;
import com.thexfactor117.levels.util.WeaponHelper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

/**
 * 
 * @author TheXFactor117
 *
 * Creates weapons whenever an item is picked up off the ground or crafted.
 *
 */
public class EventCreateWeapon 
{		
	@SubscribeEvent
	public void onPlayerTick(PlayerTickEvent event)
	{
		if (!event.player.getEntityWorld().isRemote)
		{
			if (event.phase == Phase.START)
			{
				for (ItemStack stack : event.player.inventory.mainInventory)
				{
					if (stack != null && (stack.getItem() instanceof ItemSword || stack.getItem() instanceof ItemAxe || stack.getItem() instanceof ItemArmor))
					{
						create(stack, event.player);
					}
				}
			}
		}
	}
	
	/**
	 * Sets up a weapon with customized values.
	 * @param stack
	 */
	private void create(ItemStack stack, EntityPlayer player)
	{
		NBTTagCompound nbt = NBTHelper.loadStackNBT(stack);
		
		if (nbt != null)
		{
			for (int j = 0; j < Config.itemBlacklist.length; j++)
			{
				if (Config.itemBlacklist[j].equals(stack.getItem().getRegistryName().getResourceDomain() + ":" + stack.getItem().getRegistryName().getResourcePath()))
				{
					return;
				}
			}
			
			WeaponHelper.create(stack, player);
		}
	}
}

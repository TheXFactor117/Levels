package com.thexfactor117.levels.event;

import com.thexfactor117.levels.util.WeaponHelper;
import com.thexfactor117.levels.util.Config;
import com.thexfactor117.levels.util.NBTHelper;

import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

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
	public void onPickup(EntityItemPickupEvent event)
	{
		ItemStack stack = event.getItem().getEntityItem();

		if (stack.getItem() instanceof ItemSword || stack.getItem() instanceof ItemAxe || stack.getItem() instanceof ItemArmor)
		{
			createWeapon(stack);
		}
	}
	
	@SubscribeEvent
	public void onCraft(PlayerEvent.ItemCraftedEvent event)
	{
		ItemStack stack = event.crafting;
		
		if (stack.getItem() instanceof ItemSword && stack.getItem() instanceof ItemAxe && stack.getItem() instanceof ItemArmor)
		{
			createWeapon(event.crafting);
		}
	}
	
	/**
	 * Sets up a weapon with customized values.
	 * @param stack
	 */
	private void createWeapon(ItemStack stack)
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
			
			WeaponHelper.createWeapon(stack);
		}
	}
}

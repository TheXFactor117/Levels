package com.thexfactor117.levels.event;

import com.thexfactor117.levels.leveling.Attribute;
import com.thexfactor117.levels.util.NBTHelper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * 
 * @author TheXFactor117
 *
 * Handles the Soul Bound attribute.
 *
 */
public class EventSoulBound 
{
	@SubscribeEvent
	public void onPlayerClone(PlayerEvent.Clone event)
	{
		if (event.isWasDeath())
		{
			EntityPlayer player = event.getEntityPlayer();
			player.inventory.copyInventory(event.getOriginal().inventory);
		}
	}
	
	@SubscribeEvent
	public void onPlayerDeath(PlayerDropsEvent event)
	{
		EntityPlayer player = event.getEntityPlayer();
		Item item;
		
		for (int i = 0; i < event.getDrops().size(); i++)
		{
			item = event.getDrops().get(i).getEntityItem().getItem();
			
			if (item != null && (item instanceof ItemSword || item instanceof ItemAxe || item instanceof ItemArmor || item instanceof ItemBow))
			{
				ItemStack stack = event.getDrops().get(i).getEntityItem();
				NBTTagCompound nbt = NBTHelper.loadStackNBT(stack);
				
				if (nbt != null && Attribute.SOUL_BOUND.hasAttribute(nbt) && Attribute.SOUL_BOUND.isActive(nbt))
				{
					event.getDrops().remove(i);
					player.inventory.addItemStackToInventory(stack);
				}
			}
		}
	}
}

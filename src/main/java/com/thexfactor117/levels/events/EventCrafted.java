package com.thexfactor117.levels.events;

import com.thexfactor117.levels.helpers.LogHelper;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;

public class EventCrafted 
{
	@SubscribeEvent
	public void onItemCrafted(ItemCraftedEvent event)
	{
		ItemStack stack = event.crafting;
		NBTTagCompound nbt = stack.getTagCompound();
		
		if (nbt == null)
		{
			LogHelper.info("Hello crafted 1?");
			
			if (stack.getItem() instanceof ItemSword)
			{
				
				LogHelper.info("Hello crafted 2?");
				if (nbt == null)
				{
					LogHelper.info("Hello crafted 3?");
					nbt = new NBTTagCompound();
					stack.setTagCompound(nbt);
					
					nbt.setBoolean("FIRE", false);
					nbt.setBoolean("FROST", false);
					nbt.setBoolean("POISON", false);
					nbt.setBoolean("STRENGTH", false);
					nbt.setBoolean("ETHEREAL", false);
					nbt.setBoolean("VOID", false);
					
					nbt.setInteger("LEVEL", 1);
					nbt.setInteger("EXPERIENCE", 0);
				}
			}
		}
	}
}

package com.thexfactor117.levels.events;

import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;

public class EventItemCrafted 
{
	@SubscribeEvent
	public void onItemCrafted(ItemCraftedEvent event)
	{
		ItemStack stack = event.crafting;
		NBTTagCompound nbt = stack.getTagCompound();
		
		if (nbt == null)
		{
			if (stack.getItem() instanceof ItemSword)
			{				
				if (nbt == null)
				{
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
			
			if (stack.getItem() instanceof ItemArmor)
			{
				if (nbt == null)
				{
					nbt = new NBTTagCompound();
					stack.setTagCompound(nbt);
					
					nbt.setBoolean("HARDENED", false);
					nbt.setBoolean("POISONED", false);
					nbt.setBoolean("STRENGTH", false);
					nbt.setBoolean("IMMUNIZATION", false);
					nbt.setBoolean("ETHEREAL", false);
					nbt.setBoolean("VOID", false);
					
					nbt.setInteger("LEVEL", 1);
					nbt.setInteger("EXPERIENCE", 0);
				}
			}
		}
	}
}

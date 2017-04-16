package com.thexfactor117.levels.events;

import java.util.ArrayList;

import com.thexfactor117.levels.leveling.Rarity;
import com.thexfactor117.levels.util.NBTHelper;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * 
 * @author TheXFactor117
 *
 * Uses the ItemTooltipEvent to change the contents of an item's tooltip.
 *
 */
public class EventItemTooltip 
{
	@SubscribeEvent
	public void onTooltip(ItemTooltipEvent event)
	{
		ArrayList<String> tooltip = (ArrayList<String>) event.getToolTip();
		ItemStack stack = event.getItemStack();
		Item item = event.getItemStack().getItem();
		
		if (stack != null)
		{
			if (item instanceof ItemSword)
			{
				NBTTagCompound nbt = NBTHelper.loadStackNBT(stack);
				
				if (nbt != null)
				{
					Rarity rarity = Rarity.getRarity(nbt);
					
					changeTooltip(tooltip, stack, nbt);
					
					tooltip.add("");
					tooltip.add(rarity.getColor() + TextFormatting.ITALIC + rarity.getName());
					
				}
			}
		}
	}
	
	private void changeTooltip(ArrayList<String> tooltip, ItemStack stack, NBTTagCompound nbt)
	{
		String index = "When in main hand:";
		
		if (tooltip.indexOf(index) != -1)
		{	
			NBTTagList taglist = nbt.getTagList("AttributeModifiers", 10);
			NBTTagCompound speedNbt = taglist.getCompoundTagAt(1);
			
			int i = tooltip.indexOf(index); // the index of "When in main hand:"
			
			tooltip.set(i + 1, TextFormatting.BLUE + " +" + speedNbt.getDouble("Amount") + " Attack Speed");
		}
	}
}

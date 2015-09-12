package com.thexfactor117.levels.events;

import java.util.Random;

import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.nbt.NBTTagCompound;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;

/**
 * 
 * @author TheXFactor117
 *
 */
public class EventItemCrafted 
{
	@SubscribeEvent
	public void onItemCrafted(ItemCraftedEvent event)
	{
		ItemStack stack = event.crafting;
		NBTTagCompound nbt = stack.getTagCompound();
		Random rand = new Random();
		
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
					
					boolean developmentEnvironment = (Boolean)Launch.blackboard.get("fml.deobfuscatedEnvironment");
					
					if (developmentEnvironment)
					{
						int var = rand.nextInt(100) + 1;
						if (var <= 75)
						{
							nbt.setInteger("RARITY", 1);
						}
						
						if (var > 75 && var <= 90)
						{
							nbt.setInteger("RARITY", 2);
						}
						
						if (var > 90 && var <= 96)
						{
							nbt.setInteger("RARITY", 3);
						}
						
						if (var > 96 && var <= 99)
						{
							nbt.setInteger("RARITY", 4);
						}
						
						if (var == 100)
						{
							nbt.setInteger("RARITY", 5);
						}
					}
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

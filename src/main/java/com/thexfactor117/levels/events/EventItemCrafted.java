package com.thexfactor117.levels.events;

import java.util.Random;

import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.launchwrapper.Launch;
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
						int var = rand.nextInt(15);
						if (var < 5)
						{
							nbt.setString("RARITY", "COMMON");
						}
						
						if (var > 4 && var < 9)
						{
							nbt.setString("RARITY", "UNCOMMON");
						}
						
						if (var > 8 && var < 12)
						{
							nbt.setString("RARITY", "RARE");
						}
						
						if (var > 11 && var < 14)
						{
							nbt.setString("RARITY", "LEGENDARY");
						}
						
						if (var == 14)
						{
							nbt.setString("RARITY", "EXTRAORDINARY");
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

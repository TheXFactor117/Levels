package com.thexfactor117.levels.events;

import java.util.Random;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

/**
 * 
 * @author TheXFactor117
 *
 */
public class EventLivingDrops 
{
	@SubscribeEvent
	public void entityDrops(LivingDropsEvent event)
	{
		Random rand = new Random();
		
		if (!event.entity.worldObj.isRemote)
		{
			if (event.entityLiving instanceof EntityMob)
			{				
				for (int i = 0; i < event.drops.size(); i++)
				{
					EntityItem entityItem = event.drops.get(i);
					
					if (entityItem.getEntityItem().getItem() instanceof ItemSword)
					{
						NBTTagCompound nbt = entityItem.getEntityItem().getTagCompound();
						
						if (nbt == null)
						{	
							nbt = new NBTTagCompound();
							entityItem.getEntityItem().setTagCompound(nbt);
							
							nbt.setBoolean("FIRE", false);
							nbt.setBoolean("FROST", false);
							nbt.setBoolean("POISON", false);
							nbt.setBoolean("STRENGTH", false);
							nbt.setBoolean("ETHEREAL", false);
							nbt.setBoolean("VOID", false);
							
							nbt.setInteger("LEVEL", 1);
							nbt.setInteger("EXPERIENCE", 0);
							
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
			}
		}
	}
}

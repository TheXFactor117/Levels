package com.thexfactor117.levels.events;

import java.util.Random;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.living.LivingDropsEvent;

import com.thexfactor117.levels.helpers.LogHelper;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

/**
 * 
 * @author TheXFactor117
 *
 */
public class EventEntityDrops 
{
	@SubscribeEvent
	public void entityDrops(LivingDropsEvent event)
	{
		Random rand = new Random();
		
		if (!event.entity.worldObj.isRemote)
		{
			if (event.entityLiving instanceof EntityMob)
			{
				event.drops.add(new EntityItem(event.entityLiving.worldObj, event.entityLiving.posX, event.entityLiving.posY, event.entityLiving.posZ, new ItemStack(Items.diamond_sword)));
				
				for (int i = 0; i < event.drops.size(); i++)
				{
					EntityItem entityItem = event.drops.get(i);
					
					if (entityItem.getEntityItem().getItem() instanceof ItemSword)
					{
						NBTTagCompound nbt = entityItem.getEntityItem().getTagCompound();
						
						if (nbt == null)
						{	
							LogHelper.info("Hello?");
							
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
			}
		}
	}
}

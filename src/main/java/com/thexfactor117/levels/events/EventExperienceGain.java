package com.thexfactor117.levels.events;

import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

import com.thexfactor117.levels.init.ModItems;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

/**
 * 
 * @author TheXFactor117
 *
 */
public class EventExperienceGain 
{
	/**
	 * Event used to add experience when an entity dies by a weapon.
	 * @param event
	 */
	@SubscribeEvent
	public void gainExperience(LivingDeathEvent event)
	{
		if (event.source.getSourceOfDamage() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.source.getSourceOfDamage();
			
			Item itemInHand = player.inventory.getCurrentItem().getItem();
			
			if (itemInHand != null)
			{
				boolean flag = itemInHand == ModItems.woodSword;
				boolean flag1 = itemInHand == ModItems.goldSword;
				boolean flag2 = itemInHand == ModItems.stoneSword;
				boolean flag3 = itemInHand == ModItems.ironSword;
				boolean flag4 = itemInHand == ModItems.diamondSword;
				
				if (flag || flag1 || flag2 || flag3 || flag4)
				{
					if (event.entity instanceof EntityZombie || event.entity instanceof EntitySkeleton || event.entity instanceof EntitySpider || event.entity instanceof EntitySlime)
					{
						for (int i = 0; i < player.inventory.mainInventory.length; i++)
						{
							if (player.inventory.mainInventory[i] != null)
							{
								if (player.inventory.mainInventory[i].getItem() == itemInHand)
								{
									NBTTagCompound nbt = player.inventory.mainInventory[i].getTagCompound();
									nbt.setInteger("EXPERIENCE", nbt.getInteger("EXPERIENCE") + 10);
									return;
								}
							}
						}
					}
					
					if (event.entity instanceof EntityWitch || event.entity instanceof EntityCreeper || event.entity instanceof EntityBlaze || event.entity instanceof EntityCaveSpider || event.entity instanceof EntitySilverfish)
					{
						for (int i = 0; i < player.inventory.mainInventory.length; i++)
						{
							if (player.inventory.mainInventory[i] != null)
							{
								if (player.inventory.mainInventory[i].getItem() == itemInHand)
								{
									NBTTagCompound nbt = player.inventory.mainInventory[i].getTagCompound();
									nbt.setInteger("EXPERIENCE", nbt.getInteger("EXPERIENCE") + 20);
									return;
								}
							}
						}
					}
					
					if (event.entity instanceof EntityEnderman || event.entity instanceof EntityGhast || event.entity instanceof EntityIronGolem || event.entity instanceof EntityMagmaCube)
					{
						for (int i = 0; i < player.inventory.mainInventory.length; i++)
						{
							if (player.inventory.mainInventory[i] != null)
							{
								if (player.inventory.mainInventory[i].getItem() == itemInHand)
								{
									NBTTagCompound nbt = player.inventory.mainInventory[i].getTagCompound();
									nbt.setInteger("EXPERIENCE", nbt.getInteger("EXPERIENCE") + 30);
									return;
								}
							}
						}
					}
				}
			}
		}
	}
}

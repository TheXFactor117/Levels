package com.thexfactor117.levels.events;

import java.util.Random;

import com.thexfactor117.levels.Levels;
import com.thexfactor117.levels.leveling.Rarity;
import com.thexfactor117.levels.network.PacketRarity;
import com.thexfactor117.xlib.misc.NBTHelper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * 
 * @author TheXFactor117
 *
 */
public class EventLivingUpdate 
{
	@SubscribeEvent
	public void onUpdate(LivingEvent.LivingUpdateEvent event)
	{
		if (event.getEntityLiving() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.getEntityLiving();
			ItemStack[] main = player.inventory.mainInventory;
			
			if (!player.worldObj.isRemote)
			{
				for (int i = 0; i < main.length; i++)
				{
					if (main[i] != null)
					{
						if (main[i].getItem() instanceof ItemSword)
						{
							ItemStack stack = main[i];
							NBTTagCompound nbt = NBTHelper.loadStackNBT(stack);
							
							if (nbt != null)
							{
								Rarity rarity = Rarity.getRarity(nbt);
								Random rand = player.worldObj.rand;
								
								if (rarity == Rarity.UNKNOWN)
								{
									rarity = Rarity.getRandomRarity(rand);
									rarity.setRarity(nbt);
									Levels.LOGGER.info(rarity);
									NBTHelper.saveStackNBT(stack, nbt);
									Levels.NETWORK.sendTo(new PacketRarity(NBTHelper.loadStackNBT(stack)), (EntityPlayerMP) player);
									if (rarity == Rarity.ANCIENT) player.worldObj.playSound(player, player.getPosition(), SoundEvents.ENTITY_ENDERDRAGON_DEATH, player.getSoundCategory(), 0.8F, 1.0F);
								}
							}
						}
					}
				}
			}
		}
	}
}

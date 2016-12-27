package com.thexfactor117.levels.event;

import java.util.Random;

import com.thexfactor117.levels.Levels;
import com.thexfactor117.levels.leveling.Rarity;
import com.thexfactor117.levels.network.PacketRarity;
import com.thexfactor117.levels.util.NBTHelper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
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
			
			if (player != null)
			{
				NonNullList<ItemStack> main = player.inventory.mainInventory;
				
				if (!player.worldObj.isRemote)
				{
					for (int i = 0; i < main.size(); i++)
					{
						if (main.get(i) != null)
						{
							if (main.get(i).getItem() instanceof ItemSword || main.get(i).getItem() instanceof ItemBow || main.get(i).getItem() instanceof ItemArmor)
							{
								ItemStack stack = main.get(i);
								NBTTagCompound nbt = NBTHelper.loadStackNBT(stack);
								
								if (nbt != null)
								{
									Rarity rarity = Rarity.getRarity(nbt);
									Random rand = player.worldObj.rand;
									
									if (rarity == Rarity.DEFAULT)
									{
										rarity = Rarity.getRandomRarity(rand);
										rarity.setRarity(nbt);
										NBTHelper.saveStackNBT(stack, nbt);
										Levels.network.sendTo(new PacketRarity(NBTHelper.loadStackNBT(stack)), (EntityPlayerMP) player);
										if (rarity == Rarity.ARCHAIC) player.worldObj.playSound(player, player.getPosition(), SoundEvents.ENTITY_ENDERDRAGON_DEATH, player.getSoundCategory(), 0.8F, 1.0F);
									}
								}
							}
						}
					}
				}
			}
		}
	}
}

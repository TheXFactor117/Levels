package com.thexfactor117.levels.events;

import java.util.Random;

import com.thexfactor117.levels.Levels;
import com.thexfactor117.levels.helpers.NBTHelper;
import com.thexfactor117.levels.helpers.Rarity;
import com.thexfactor117.levels.network.PacketRarity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

/**
 * 
 * @author TheXFactor117
 *
 */
public class EventItemCrafted 
{
	@SubscribeEvent
	public void onItemCraft(PlayerEvent.ItemCraftedEvent event)
	{
		EntityPlayer player = event.player;
		ItemStack stack = event.crafting;

		if (!player.worldObj.isRemote)
		{
			if (stack != null)
			{
				if (stack.getItem() instanceof ItemSword || stack.getItem() instanceof ItemBow || stack.getItem() instanceof ItemArmor)
				{
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
							Levels.network.sendTo(new PacketRarity(NBTHelper.loadStackNBT(stack)), (EntityPlayerMP) player);
							if (rarity == Rarity.ANCIENT) player.worldObj.playSound(player, player.getPosition(), SoundEvents.ENTITY_ENDERDRAGON_DEATH, player.getSoundCategory(), 0.8F, 1.0F);
						}
					}
				}
			}
		}
	}
}

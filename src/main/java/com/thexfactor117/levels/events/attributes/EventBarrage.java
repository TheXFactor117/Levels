package com.thexfactor117.levels.events.attributes;

import com.thexfactor117.levels.leveling.attributes.BowAttribute;
import com.thexfactor117.levels.util.NBTHelper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityArrow.PickupStatus;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * 
 * @author TheXFactor117
 *
 */
public class EventBarrage 
{
	@SuppressWarnings("static-access")
	@SubscribeEvent
	public void onBowFire(ArrowLooseEvent event)
	{
		EntityPlayer player = event.getEntityPlayer();
		ItemStack stack = event.getBow();
		NBTTagCompound nbt = NBTHelper.loadStackNBT(stack);
		
		if (player != null && stack != null && nbt != null && !player.getEntityWorld().isRemote)
		{
			if (BowAttribute.BARRAGE.hasAttribute(nbt))
			{
				for (int i = 0; i < (int) BowAttribute.BARRAGE.getCalculatedValue(nbt, 3, 1.5); i++)
				{
					EntityArrow entityarrow = new EntityTippedArrow(player.getEntityWorld(), player);
					entityarrow.setAim(player, player.rotationPitch, player.rotationYaw, 0, ((ItemBow) event.getBow().getItem()).getArrowVelocity(event.getCharge()) * 3, 20F);
					entityarrow.pickupStatus = PickupStatus.DISALLOWED;
					player.getEntityWorld().spawnEntity(entityarrow);
				}
			}
		}
	}
}

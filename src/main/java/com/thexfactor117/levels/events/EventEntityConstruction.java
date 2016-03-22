package com.thexfactor117.levels.events;

import com.thexfactor117.levels.Reference;
import com.thexfactor117.levels.helpers.EnemyLevelCapability;
import com.thexfactor117.levels.helpers.EnemyLevelProvider;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * 
 * @author TheXFactor117
 *
 */
public class EventEntityConstruction 
{
	@SubscribeEvent
	public void entityJoinWorld(EntityJoinWorldEvent e) 
	{
		if (e.entity instanceof EntityPlayer && !e.entity.worldObj.isRemote) // can be replaces by if (e.entity instanceof EntityPlayerMP)
			EnemyLevelProvider.get((EntityPlayer) e.entity);
	}
	
	@SubscribeEvent
	public void attachCapability(AttachCapabilitiesEvent.Entity e)
	{
		if(!e.getEntity().hasCapability(EnemyLevelProvider.EXTENDEDMOB, null) && e.getEntity() instanceof EntityPlayer)
			e.addCapability(new ResourceLocation(Reference.MODID + " extended mob props"), new EnemyLevelProvider(new EnemyLevelCapability()));
	}
}
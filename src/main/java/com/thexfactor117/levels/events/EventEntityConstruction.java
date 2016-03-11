package com.thexfactor117.levels.events;

import com.thexfactor117.levels.handlers.ExtendedMob;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.monster.EntityMob;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;

/**
 * 
 * @author TheXFactor117
 *
 */
public class EventEntityConstruction 
{
	/**
	 * Event used to create the extended properties for mobs during their construction. Used to
	 * keep track of their level.
	 * @param event
	 */
	@SubscribeEvent
	public void onEntityConstruction(EntityConstructing event)
	{
		if (event.entity instanceof EntityMob && ExtendedMob.get((EntityMob) event.entity) == null)
		{
			ExtendedMob.register((EntityMob) event.entity);
		}
		
		if (event.entity instanceof EntityMob && event.entity.getExtendedProperties(ExtendedMob.EXTENDED_PROPERTIES) == null)
		{
			event.entity.registerExtendedProperties(ExtendedMob.EXTENDED_PROPERTIES, new ExtendedMob((EntityMob) event.entity));
		}
	}
}
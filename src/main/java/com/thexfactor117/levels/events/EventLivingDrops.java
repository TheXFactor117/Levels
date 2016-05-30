package com.thexfactor117.levels.events;

import java.util.Random;

import com.thexfactor117.levels.handlers.ConfigHandler;
import com.thexfactor117.levels.helpers.LogHelper;
import com.thexfactor117.levels.helpers.WeaponType;

import net.minecraft.entity.monster.EntityMob;
import net.minecraft.init.Items;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * 
 * @author TheXFactor117
 *
 */
public class EventLivingDrops 
{	
	/**
	 * Called whenever an entity needs to drop items. Used to drop swords from monsters whenever
	 * they die.
	 * @param event
	 */
	@SubscribeEvent
	public void onLivingDrop(LivingDropsEvent event)
	{		
		if (ConfigHandler.enableMobDrops)
		{
			LogHelper.info("Mob Droppings enabled...");
			
			if (event.getEntityLiving() instanceof EntityMob)
			{
				Random rand = new Random();
				float health = event.getEntityLiving().getMaxHealth();
				int var = 0;

				if (health < 10.0F) var = 50;
				if (health >= 10.0F && health < 20.0F) var = 25;
				if (health >= 20.0F) var = 10;
				
				if (var != 0)
				{
					int var1 = rand.nextInt(var);
					if (var1 == 0)
					{
						WeaponType weapon = WeaponType.getRandomWeaponType(rand);
						
						switch (weapon)
						{
							case WOOD:
								event.getEntityLiving().dropItem(Items.WOODEN_SWORD, 1);
								break;
							case STONE:
								event.getEntityLiving().dropItem(Items.STONE_SWORD, 1);
								break;
							case GOLD:
								event.getEntityLiving().dropItem(Items.GOLDEN_SWORD, 1);
								break;
							case IRON:
								event.getEntityLiving().dropItem(Items.IRON_SWORD, 1);
								break;
							case DIAMOND:
								event.getEntityLiving().dropItem(Items.DIAMOND_SWORD, 1);
								break;
							case BOW:
								event.getEntityLiving().dropItem(Items.BOW, 1);
								break;
						}
					}
				}
			}
		}
	}
}

package com.thexfactor117.levels.events;

import java.util.Random;

import com.thexfactor117.levels.handlers.ConfigHandler;
import com.thexfactor117.levels.helpers.LogHelper;
import com.thexfactor117.levels.helpers.WeaponType;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.init.Items;
import net.minecraftforge.event.entity.living.LivingDropsEvent;

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
			
			if (event.entityLiving instanceof EntityMob)
			{
				Random rand = new Random();
				float health = event.entityLiving.getMaxHealth();
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
								event.entityLiving.dropItem(Items.wooden_sword, 1);
								break;
							case STONE:
								event.entityLiving.dropItem(Items.stone_sword, 1);
								break;
							case GOLD:
								event.entityLiving.dropItem(Items.golden_sword, 1);
								break;
							case IRON:
								event.entityLiving.dropItem(Items.iron_sword, 1);
								break;
							case DIAMOND:
								event.entityLiving.dropItem(Items.diamond_sword, 1);
								break;
							case BOW:
								event.entityLiving.dropItem(Items.bow, 1);
								break;
						}
					}
				}
			}
		}
	}
}

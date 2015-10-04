package com.thexfactor117.levels.events;

import com.thexfactor117.levels.Reference;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

import com.thexfactor117.levels.handlers.ConfigHandler;
import com.thexfactor117.levels.helpers.*;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

import java.util.Random;

/**
 * 
 * @author TheXFactor117
 *
 */
public class EventLivingDeath 
{
	/**
	 * Called when the specifed entity dies by the specific source (the Player). Used to determine how
	 * how much bonus experience should be given to the weapon when dealing the killing blow.
	 * @param event
	 */
	@SubscribeEvent
	public void onEntityDeath(LivingDeathEvent event)
	{
		if (event.source.getSourceOfDamage() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.source.getSourceOfDamage();
			Random rand = player.worldObj.rand;
			ItemStack stack = player.inventory.getCurrentItem();

			if (stack != null && stack.getItem() instanceof ItemSword)
			{
				NBTTagCompound nbt = NBTHelper.loadStackNBT(stack);
				int level = Experience.getLevel(nbt);
				int experience = Experience.getExperience(nbt);

				/*
				 * Rarities
				 */
				Rarity rarity = Rarity.getRarity(nbt);
				if (rarity == Rarity.UNKOWN)
				{
					rarity = Rarity.getRandomRarity(rand);
					LogHelper.info(rarity);
					rarity.setRarity(nbt);
					player.worldObj.playSoundAtEntity(player, "mob.enderdragon.end", 0.25F * (float) (rarity.ordinal() - 1), 1.0F);
				}

				/*
				 * Weapon Bonus Experience
				 */
				if (level < Reference.MAX_LEVEL)
				{
					if (event.entityLiving instanceof EntityMob)
					{
						experience += ConfigHandler.enableDevFeatures ? 1000 : ConfigHandler.weaponMonsterExpBonus;
					}

					if (event.entityLiving instanceof EntityAnimal)
					{
						experience += ConfigHandler.weaponAnimalExpBonus;
					}

					Experience.setExperience(nbt, experience);
				}

				/*
				 * Leveling system
				 */
				level = Experience.getLevelsUp(player, nbt, level, experience, ItemType.WEAPON, rand);
				Experience.setLevel(nbt, level);

				NBTHelper.saveStackNBT(stack, nbt);
			}
		}
	}
}

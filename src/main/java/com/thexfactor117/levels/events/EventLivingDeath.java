package com.thexfactor117.levels.events;

import java.util.Random;

import com.thexfactor117.levels.Reference;
import com.thexfactor117.levels.helpers.ExperienceHelper;
import com.thexfactor117.levels.helpers.LogHelper;
import com.thexfactor117.levels.helpers.Rarity;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

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
				NBTTagCompound nbt = stack.getTagCompound();
				Rarity rarity = Rarity.getRarity(nbt);
				int level = ExperienceHelper.getLevel(nbt);
				int experience = ExperienceHelper.getExperience(nbt);

				/*
				 * Rarities
				 */
				if (rarity == Rarity.UNKNOWN)
				{
					rarity = Rarity.getRandomRarity(rand);
					LogHelper.info(rarity);
					rarity.setRarity(nbt);
					if (rarity == Rarity.ANCIENT) player.worldObj.playSoundAtEntity(player, "mob.enderdragon.end", 0.8F, 1.0F);
				}

				/*
				 * Weapon Bonus Experience
				 */
				if (level < Reference.MAX_LEVEL)
				{
					if (event.entityLiving instanceof EntityMob)
					{
						ExperienceHelper.setExperience(nbt, ExperienceHelper.getExperience(nbt) + Reference.MONSTER_BONUS_EXP);
					}

					if (event.entityLiving instanceof EntityAnimal)
					{
						ExperienceHelper.setExperience(nbt, ExperienceHelper.getExperience(nbt) + Reference.ANIMAL_BONUS_EXP);
					}
				}

				/*
				 * Leveling system
				 */
				level = ExperienceHelper.getNextLevel(player, nbt, level, experience, rand);
				ExperienceHelper.setLevel(nbt, level);
			}
		}
	}
}

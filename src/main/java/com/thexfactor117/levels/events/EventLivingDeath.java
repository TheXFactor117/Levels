package com.thexfactor117.levels.events;

import java.util.Random;

import com.thexfactor117.levels.Reference;
import com.thexfactor117.levels.helpers.AbilityHelper;
import com.thexfactor117.levels.helpers.Experience;
import com.thexfactor117.levels.helpers.NBTHelper;
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
				NBTTagCompound nbt = NBTHelper.loadStackNBT(stack);
				
				if (nbt != null)
				{
					Rarity rarity = Rarity.getRarity(nbt);
					int level = Experience.getLevel(nbt);
					int experience = Experience.getExperience(nbt);

					/*
					 * Rarities
					 */
					if (rarity == Rarity.UNKNOWN)
					{
						rarity = Rarity.getRandomRarity(rand);
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
							Experience.setExperience(nbt, Experience.getExperience(nbt) + Reference.MONSTER_BONUS_EXP);
						}

						if (event.entityLiving instanceof EntityAnimal)
						{
							Experience.setExperience(nbt, Experience.getExperience(nbt) + Reference.ANIMAL_BONUS_EXP);
						}
					}

					/*
					 * Leveling system
					 */
					level = Experience.getNextLevel(player, nbt, AbilityHelper.ABILITIES, level, experience, rand);
					Experience.setLevel(nbt, level);
					
					NBTHelper.saveStackNBT(stack, nbt);
				}
			}
		}
	}
}

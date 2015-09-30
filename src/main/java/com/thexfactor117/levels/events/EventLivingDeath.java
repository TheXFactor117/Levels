package com.thexfactor117.levels.events;

import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

import com.thexfactor117.levels.handlers.ConfigHandler;
import com.thexfactor117.levels.helpers.*;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

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
			ItemStack stack = player.inventory.getCurrentItem();

			if (stack != null && stack.getItem() instanceof ItemSword)
			{
				NBTTagCompound nbt = stack.hasTagCompound() ? stack.getTagCompound() : new NBTTagCompound();
				int level = Experience.getLevel(nbt);
				int experience = Experience.getExperience(nbt);

				/*
				 * Rarities
				 */
				if (Rarity.getRarity(nbt) == Rarity.UNKOWN)
				{
					RandomCollection<Rarity> rarities = new RandomCollection<Rarity>();

					rarities.add(0.65D, Rarity.BASIC);
					rarities.add(0.17D, Rarity.UNCOMMON);
					rarities.add(0.11D, Rarity.RARE);
					rarities.add(0.05D, Rarity.LEGENDARY);
					rarities.add(0.02D, Rarity.ANCIENT);
					Rarity rarity = rarities.next();
					LogHelper.info(rarity);
					rarity.setRarity(nbt);
					player.worldObj.playSoundAtEntity(player, "mob.enderdragon.end", 0.25F * (float) (rarity.ordinal() - 1), 1.0F);
				}

				/*
				 * Weapon Bonus Experience
				 */
				if (level < ItemType.WEAPON.getMaxLevel())
				{
					if (event.entityLiving instanceof EntityMob)
					{
						boolean developmentEnvironment = (Boolean)Launch.blackboard.get("fml.deobfuscatedEnvironment");
						experience += developmentEnvironment ? 1000 : ConfigHandler.weaponMonsterExpBonus;
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
				while (level < Experience.getLevelsUp(player, level, experience, ItemType.WEAPON))
				{
					level++;
					AbilityHelper.getRandomizedMeleeAbilities(stack, level);
				}

				Experience.setLevel(nbt, level);

				if (!nbt.hasNoTags() && !stack.hasTagCompound())
				{
					stack.setTagCompound(nbt);
				}
			}
		}
	}
}

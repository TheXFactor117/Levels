package com.thexfactor117.levels.events;

import java.util.Random;

import com.thexfactor117.levels.Reference;
import com.thexfactor117.levels.handlers.ConfigHandler;
import com.thexfactor117.levels.helpers.Experience;
import com.thexfactor117.levels.helpers.NBTHelper;
import com.thexfactor117.levels.helpers.Rarity;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

/**
 * 
 * @author TheXFactor117
 *
 */
public class EventLivingHurt 
{
	/**
	 * Fired when an entity is about to be hurt.
	 * @param event
	 */
	@SuppressWarnings("incomplete-switch")
	@SubscribeEvent
	public void hitEntity(LivingHurtEvent event)
	{
		/*
		 * 
		 * WEAPONS
		 *
		 */
		if (event.source.getSourceOfDamage() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.source.getSourceOfDamage();
			Random rand = player.worldObj.rand;
			ItemStack stack = player.inventory.getCurrentItem();
			
			if (stack != null)
			{
				if (stack.getItem() instanceof ItemSword)
				{
					NBTTagCompound nbt = NBTHelper.loadStackNBT(stack);
					
					if (nbt != null)
					{
						int level = Experience.getLevel(nbt);
						int experience = Experience.getExperience(nbt);

						/*
						 * Experience
						 */
						if (level < Reference.MAX_LEVEL)
						{
							Experience.setExperience(nbt, Experience.getExperience(nbt) + 1);
						}

						/*
						 * Leveling system
						 */
						level = Experience.getNextLevel(player, nbt, level, experience, rand);
						Experience.setLevel(nbt, level);

						/*
						 * Rarity
						 */
						Rarity rarity = Rarity.getRarity(nbt);
						float damageMultiplier = 1.0F;
						//float trueDamage = event.ammount;

						// Damage boosts
						switch (rarity)
						{
							case UNCOMMON:
								damageMultiplier = 1.5F;
								int var = rand.nextInt(10);
								if (var == 0) Experience.setExperience(nbt, Experience.getExperience(nbt) + 1);
								break;
							case RARE:
								damageMultiplier = 1.5F;
								int var1 = rand.nextInt(4);
								if (var1 == 0) Experience.setExperience(nbt, Experience.getExperience(nbt) + 1);
								break;
							case LEGENDARY:
								damageMultiplier = 2.0F;
								int var2 = rand.nextInt(5);
								int var3 = rand.nextInt(2) + 2;
								if (var2 == 0) Experience.setExperience(nbt, Experience.getExperience(nbt) + var3);
								break;
							case ANCIENT:
								damageMultiplier = 3.0F;
								int var4 = rand.nextInt(4);
								int var5 = rand.nextInt(3) + 3;
								if (var4 == 0) Experience.setExperience(nbt, Experience.getExperience(nbt) + var5);
								break;
						}

						event.ammount *= damageMultiplier;
						// DEBUG
						//LogHelper.info("True Damage: " + trueDamage + "   |   Modified Damage: " + event.ammount);
						
						if (ConfigHandler.enableDurability)
						{
							// Durability boosts
							// rare
							if (rarity == Rarity.RARE)
							{
								// reduction
								int var = rand.nextInt(5);
								if (var == 0)
								{
									if (stack.getItemDamage() == stack.getMaxDamage())
									{
										stack.setItemDamage(stack.getItemDamage());
									}
									else
									{
										stack.setItemDamage(stack.getItemDamage() - 1);
									}
								}
							}
							
							// legendary
							if (rarity == Rarity.LEGENDARY)
							{
								// reduction
								int var = rand.nextInt(5);
								if (var == 0)
								{
									if (stack.getItemDamage() == stack.getMaxDamage())
									{
										stack.setItemDamage(stack.getItemDamage());
									}
									else
									{
										stack.setItemDamage(stack.getItemDamage() - 1);
									}
								}
								
								// additional durability
								int var1 = rand.nextInt(10);
								if (var1 == 0)
								{
									if (stack.getItemDamage() <= stack.getMaxDamage() && stack.getItemDamage() >= (stack.getMaxDamage() - 10))
									{
										stack.setItemDamage(stack.getMaxDamage());
									}
									else
									{
										stack.setItemDamage(stack.getItemDamage() - 10);
									}
								}
							}
							
							// ancient
							if (rarity == Rarity.ANCIENT)
							{
								// reduction
								int var = rand.nextInt(10);
								if (var < 3)
								{
									if (stack.getItemDamage() == stack.getMaxDamage())
									{
										stack.setItemDamage(stack.getItemDamage());
									}
									else
									{
										stack.setItemDamage(stack.getItemDamage() - 1);
									}
								}
								
								// additional durability
								int var1 = rand.nextInt(5);
								if (var1 == 0)
								{
									if (stack.getItemDamage() <= stack.getMaxDamage() && stack.getItemDamage() >= (stack.getMaxDamage() - 10))
									{
										stack.setItemDamage(stack.getMaxDamage());
									}
									else
									{
										stack.setItemDamage(stack.getItemDamage() - 10);
									}
								}
							}
						}
						else
						{
							stack.setItemDamage(-stack.getMaxDamage());
						}
						
						NBTHelper.saveStackNBT(stack, nbt);
					}
				}
			}
		}
	}
}

package com.thexfactor117.levels.events;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

import com.thexfactor117.levels.handlers.ConfigHandler;
import com.thexfactor117.levels.helpers.AbilityHelper;
import com.thexfactor117.levels.helpers.LogHelper;
import com.thexfactor117.levels.helpers.RandomCollection;

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
			
			if (stack != null)
			{
				NBTTagCompound nbt = stack.getTagCompound();
				
				if (nbt != null)
				{
					if (ConfigHandler.enableDevFeatures)
					{
						/*
						 * Rarities
						 */
						if (nbt.getInteger("RARITY") == 0)
						{
							RandomCollection<String> rarities = new RandomCollection<String>();
							
							rarities.add(0.65D, "basic");
							rarities.add(0.17D, "uncommon");
							rarities.add(0.11D, "rare");
							rarities.add(0.05D, "legendary");
							rarities.add(0.02D, "ancient");
							String rarity = rarities.next();
							LogHelper.info(rarity);
							
							if (rarity == "basic") nbt.setInteger("RARITY", 1);
							if (rarity == "uncommon") nbt.setInteger("RARITY", 2);
							if (rarity == "rare") nbt.setInteger("RARITY", 3);
							if (rarity == "legendary") nbt.setInteger("RARITY", 4);
							if (rarity == "ancient") nbt.setInteger("RARITY", 5);
						}
					}
					
					/*
					 * Weapon Bonus Experience
					 */
					if (nbt.getInteger("LEVEL") != 6)
					{
						if (event.entityLiving instanceof EntityMob)
						{
							boolean developmentEnvironment = (Boolean)Launch.blackboard.get("fml.deobfuscatedEnvironment");
							
							if (developmentEnvironment)
							{
								nbt.setInteger("EXPERIENCE", nbt.getInteger("EXPERIENCE") + 1000);
							}
							else
							{
								nbt.setInteger("EXPERIENCE", nbt.getInteger("EXPERIENCE") + 10);
							}
						}
						
						if (event.entityLiving instanceof EntityAnimal)
						{
							nbt.setInteger("EXPERIENCE", nbt.getInteger("EXPERIENCE") + 2);
						}
					}
					
					/*
					 * Leveling system
					 */
					if (nbt.getInteger("LEVEL") == 1 && nbt.getInteger("EXPERIENCE") >= 1000)
					{
						nbt.setInteger("LEVEL", 2);
						AbilityHelper.getRandomizedMeleeAbilities(stack, nbt.getInteger("LEVEL"));
						Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.WHITE + "Your weapon has leveled up!"));
					}
					
					if (nbt.getInteger("LEVEL") == 2 && nbt.getInteger("EXPERIENCE") >= 2500)
					{
						nbt.setInteger("LEVEL", 3);
						AbilityHelper.getRandomizedMeleeAbilities(stack, nbt.getInteger("LEVEL"));
						Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_GREEN + "Your weapon has leveled up!"));
					}
					
					if (nbt.getInteger("LEVEL") == 3 && nbt.getInteger("EXPERIENCE") >= 5000)
					{
						nbt.setInteger("LEVEL", 4);
						AbilityHelper.getRandomizedMeleeAbilities(stack, nbt.getInteger("LEVEL"));
						Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "Your weapon has leveled up!"));
					}
					
					if (nbt.getInteger("LEVEL") == 4 && nbt.getInteger("EXPERIENCE") >= 10000)
					{
						nbt.setInteger("LEVEL", 5);
						AbilityHelper.getRandomizedMeleeAbilities(stack, nbt.getInteger("LEVEL"));
						Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_PURPLE + "Your weapon has leveled up!"));
					}
					
					if (nbt.getInteger("LEVEL") == 5 && nbt.getInteger("EXPERIENCE") >= 20000)
					{
						nbt.setInteger("LEVEL", 6);
						AbilityHelper.getRandomizedMeleeAbilities(stack, nbt.getInteger("LEVEL"));
						Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Your weapon has reached the max level!"));
					}
				}
			}
		}
	}
}

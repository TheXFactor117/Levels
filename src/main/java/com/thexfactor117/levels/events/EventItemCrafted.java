package com.thexfactor117.levels.events;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.nbt.NBTTagCompound;

import com.thexfactor117.levels.Levels;
import com.thexfactor117.levels.handlers.ConfigHandler;
import com.thexfactor117.levels.helpers.LogHelper;
import com.thexfactor117.levels.helpers.RandomCollection;
import com.thexfactor117.levels.network.PacketRarities;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;

/**
 * 
 * @author TheXFactor117
 *
 */
public class EventItemCrafted 
{
	@SubscribeEvent
	public void onItemCrafted(ItemCraftedEvent event)
	{
		ItemStack stack = event.crafting;
		NBTTagCompound nbt = stack.getTagCompound();
		
		if (stack.getItem() instanceof ItemSword)
		{
			if (!event.player.worldObj.isRemote)
			{
				if (nbt == null)
				{				
					EntityPlayerMP player = (EntityPlayerMP) event.player;
					
					nbt = new NBTTagCompound();
					stack.setTagCompound(nbt);
					
					nbt.setBoolean("FIRE", false);
					nbt.setBoolean("FROST", false);
					nbt.setBoolean("POISON", false);
					nbt.setBoolean("STRENGTH", false);
					nbt.setBoolean("ETHEREAL", false);
					nbt.setBoolean("VOID", false);
					
					nbt.setInteger("LEVEL", 1);
					nbt.setInteger("EXPERIENCE", 0);
					
					boolean developmentEnvironment = (Boolean)Launch.blackboard.get("fml.deobfuscatedEnvironment");
					
					if (developmentEnvironment || ConfigHandler.enableDevFeatures)
					{
						RandomCollection<String> rarities = new RandomCollection<String>();
						
						rarities.add(0.65D, "basic");
						rarities.add(0.17D, "uncommon");
						rarities.add(0.11D, "rare");
						rarities.add(0.05D, "legendary");
						rarities.add(0.02D, "ancient");
						String rarity = rarities.next();
						LogHelper.info(rarity);
						
						Levels.network.sendTo(new PacketRarities(rarity, nbt), player);
						LogHelper.info("Packet Sent!");
						
						if (rarity == "basic") nbt.setInteger("RARITY", 1);
						if (rarity == "uncommon") nbt.setInteger("RARITY", 2);
						if (rarity == "rare") nbt.setInteger("RARITY", 3);
						if (rarity == "legendary") nbt.setInteger("RARITY", 4);
						if (rarity == "ancient") nbt.setInteger("RARITY", 5);
					}
				}
			}
		}
		
		if (stack.getItem() instanceof ItemArmor)
		{
			if (nbt == null)
			{
				nbt = new NBTTagCompound();
				stack.setTagCompound(nbt);
				
				nbt.setBoolean("HARDENED", false);
				nbt.setBoolean("POISONED", false);
				nbt.setBoolean("STRENGTH", false);
				nbt.setBoolean("IMMUNIZATION", false);
				nbt.setBoolean("ETHEREAL", false);
				nbt.setBoolean("VOID", false);
				
				nbt.setInteger("LEVEL", 1);
				nbt.setInteger("EXPERIENCE", 0);
			}
		}
	}
}

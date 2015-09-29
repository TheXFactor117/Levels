package com.thexfactor117.levels.events;

import com.thexfactor117.levels.helpers.Ability;
import com.thexfactor117.levels.helpers.ItemType;
import com.thexfactor117.levels.helpers.Rarity;

import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.List;

/**
 * 
 * @author TheXFactor117
 *
 */
public class EventItemTooltip 
{
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void addInformation(ItemTooltipEvent event)
	{
		ItemStack stack = event.itemStack;
		NBTTagCompound nbt = stack.getTagCompound();
		List<String> tooltip = event.toolTip;

		/*
		 *
		 * WEAPONS
		 *
		 */
		if (stack.getItem() instanceof ItemSword)
		{
			/*
			 * Tooltip
			 */
			tooltip.add("");
			Rarity.addTooltip(nbt, tooltip);
			ItemType.WEAPON.addTooltip(stack, tooltip);
			tooltip.add("");
			Ability.addTooltip(nbt, tooltip);
		}

		/*
		 *
		 * ARMOR
		 *
		 */
		if (stack.getItem() instanceof ItemArmor)
		{
			/*
			 * Tooltip
			 */
			tooltip.add("");
			ItemType.ARMOR.addTooltip(stack, tooltip);
			tooltip.add("");
			Ability.addTooltip(nbt, tooltip);
		}
	}
}

package com.thexfactor117.levels.events;

import java.text.DecimalFormat;
import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import com.thexfactor117.levels.leveling.Attribute;
import com.thexfactor117.levels.leveling.Experience;
import com.thexfactor117.levels.leveling.Rarity;
import com.thexfactor117.levels.util.Config;
import com.thexfactor117.levels.util.NBTHelper;

import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * 
 * @author TheXFactor117
 *
 * Uses the ItemTooltipEvent to change the contents of an item's tooltip.
 *
 */
public class EventItemTooltip 
{
	@SubscribeEvent
	public void onTooltip(ItemTooltipEvent event)
	{
		ArrayList<String> tooltip = (ArrayList<String>) event.getToolTip();
		ItemStack stack = event.getItemStack();
		Item item = event.getItemStack().getItem();
		
		if (stack != null)
		{
			if (item instanceof ItemSword)
			{
				NBTTagCompound nbt = NBTHelper.loadStackNBT(stack);
				
				if (nbt != null)
				{
					Rarity rarity = Rarity.getRarity(nbt);
					
					if (rarity != Rarity.DEFAULT)
					{
						changeTooltip(tooltip, stack, nbt);
						addTooltips(tooltip, stack, nbt);
					}
				}
			}
		}
	}
	
	private void addTooltips(ArrayList<String> tooltip, ItemStack stack, NBTTagCompound nbt)
	{
		Rarity rarity = Rarity.getRarity(nbt);
		
		tooltip.add("");
		tooltip.add(rarity.getColor() + "====================");
		
		// rarity
		tooltip.add(rarity.getColor() + TextFormatting.ITALIC + rarity.getName()); // rarity
		
		tooltip.add("");
		
		// level
		if (Experience.getExperience(nbt) >= Config.maxLevel)
			tooltip.add(TextFormatting.GRAY + I18n.format("levels.misc.level") + ": " + I18n.format("levels.misc.max")); // max level
		else
			tooltip.add(TextFormatting.GRAY + I18n.format("levels.misc.level") + ": " + Experience.getExperience(nbt)); // level
		
		// experience
		if (Experience.getExperience(nbt) >= Config.maxLevel)
			tooltip.add(TextFormatting.GRAY + I18n.format("levels.misc.experience") + ": " + I18n.format("levels.misc.max"));
		else
			tooltip.add(TextFormatting.GRAY + I18n.format("levels.misc.experience") + ": " + Experience.getExperience(nbt) + " / " + Experience.getNextLevelExperience(Experience.getLevel(nbt)));

		// durability
		tooltip.add(TextFormatting.GRAY + I18n.format("levels.misc.durability") + ": " + (stack.getMaxDamage() - stack.getItemDamage()) + " / " + stack.getMaxDamage());

		tooltip.add("");
		
		// attributes
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))
		{
			tooltip.add(TextFormatting.GRAY + "" + TextFormatting.ITALIC + I18n.format("levels.misc.attributes"));
			tooltip.add("");
			
			for (Attribute attribute : Attribute.values())
			{
				if (attribute.hasAttribute(nbt))
				{
					tooltip.add(TextFormatting.DARK_GRAY + "" + TextFormatting.ITALIC + attribute.getName());
					
					if (attribute.isActive(nbt))
						tooltip.add(attribute.getColor() + attribute.getName());
					else
						tooltip.add(TextFormatting.DARK_GRAY + "" + TextFormatting.ITALIC + attribute.getName());
				}
			}
		}
		else
		{
			tooltip.add(TextFormatting.GRAY + "" + TextFormatting.ITALIC + I18n.format("levels.misc.attributes.shift"));
		}
		
		tooltip.add("");
		tooltip.add(rarity.getColor() + "====================");
		tooltip.add("");
	}
	
	private void changeTooltip(ArrayList<String> tooltip, ItemStack stack, NBTTagCompound nbt)
	{
		String index = "When in main hand:";
		
		if (tooltip.indexOf(index) != -1)
		{	
			NBTTagList taglist = nbt.getTagList("AttributeModifiers", 10);
			NBTTagCompound speedNbt = taglist.getCompoundTagAt(1);
			DecimalFormat format = new DecimalFormat("#.##");
			
			int i = tooltip.indexOf(index); // the index of "When in main hand:"
			
			tooltip.set(i + 1, TextFormatting.BLUE + " +" + format.format(speedNbt.getDouble("Amount") + 4) + " Attack Speed");
		}
	}
}

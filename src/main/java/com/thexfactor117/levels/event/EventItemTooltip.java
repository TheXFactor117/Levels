package com.thexfactor117.levels.event;

import java.text.DecimalFormat;
import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import com.thexfactor117.levels.config.Config;
import com.thexfactor117.levels.leveling.Attribute;
import com.thexfactor117.levels.leveling.Experience;
import com.thexfactor117.levels.leveling.Rarity;
import com.thexfactor117.levels.util.NBTHelper;

import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
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
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void onTooltip(ItemTooltipEvent event)
	{
		ArrayList<String> tooltip = (ArrayList<String>) event.getToolTip();
		ItemStack stack = event.getItemStack();
		Item item = event.getItemStack().getItem();
		
		if (stack != null)
		{
			if (item instanceof ItemSword || item instanceof ItemAxe || item instanceof ItemArmor)
			{
				NBTTagCompound nbt = NBTHelper.loadStackNBT(stack);
				
				if (nbt != null)
				{
					Rarity rarity = Rarity.getRarity(nbt);
					
					if (rarity != Rarity.DEFAULT)
					{
						addTooltips(tooltip, stack, nbt);
					}
				}
			}
		}
	}
	
	private void addTooltips(ArrayList<String> tooltip, ItemStack stack, NBTTagCompound nbt)
	{
		Rarity rarity = Rarity.getRarity(nbt);
		
		NBTTagList taglist = nbt.getTagList("AttributeModifiers", 10);
		NBTTagCompound damageNbt = taglist.getCompoundTagAt(0);
		NBTTagCompound speedNbt = taglist.getCompoundTagAt(1);
		DecimalFormat format = new DecimalFormat("#.##");
		
		tooltip.add("");
		
		if (stack.getItem() instanceof ItemSword || stack.getItem() instanceof ItemAxe)
		{
			// damage and attack speed
			tooltip.add(TextFormatting.BLUE + "+" + format.format(damageNbt.getDouble("Amount")) + " Damage");
			tooltip.add(TextFormatting.BLUE + "+" + format.format(speedNbt.getDouble("Amount") + 4) + " Attack Speed");
		}
		else if (stack.getItem() instanceof ItemArmor)
		{
			// armor and armor toughness
			tooltip.add(TextFormatting.BLUE + "+" + format.format(damageNbt.getDouble("Amount")) + " Armor");
			tooltip.add(TextFormatting.BLUE + "+" + format.format(speedNbt.getDouble("Amount")) + " Armor Toughness");
		}
		
		tooltip.add("");
		
		// rarity
		tooltip.add(rarity.getColor() + TextFormatting.ITALIC + rarity.getName()); // rarity
		
		// level
		if (Experience.getLevel(nbt) >= Config.maxLevel)
			tooltip.add(TextFormatting.GRAY + I18n.format("levels.misc.level") + ": " + I18n.format("levels.misc.max")); // max level
		else
			tooltip.add(TextFormatting.GRAY + I18n.format("levels.misc.level") + ": " + Experience.getLevel(nbt)); // level
		
		// experience
		if (Experience.getLevel(nbt) >= Config.maxLevel)
			tooltip.add(TextFormatting.GRAY + I18n.format("levels.misc.experience") + ": " + I18n.format("levels.misc.max"));
		else
			tooltip.add(TextFormatting.GRAY + I18n.format("levels.misc.experience") + ": " + Experience.getExperience(nbt) + " / " + Experience.getNextLevelExperience(Experience.getLevel(nbt)));

		// durability
		if (nbt.getInteger("Unbreakable") == 1)
			tooltip.add(TextFormatting.GRAY + I18n.format("levels.misc.durability") + ": " + I18n.format("levels.misc.durability.unlimited"));
		else
			tooltip.add(TextFormatting.GRAY + I18n.format("levels.misc.durability") + ": " + (stack.getMaxDamage() - stack.getItemDamage()) + " / " + stack.getMaxDamage());

		tooltip.add("");
		
		// attributes
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))
		{
			tooltip.add(TextFormatting.GRAY + "" + TextFormatting.ITALIC + I18n.format("levels.misc.attributes"));
			
			for (Attribute attribute : Attribute.values())
			{
				if (attribute.hasAttribute(nbt))
				{					
					if (attribute.isActive(nbt))
						tooltip.add(" " + attribute.getColor() + attribute.getName());
					else
						tooltip.add(" " + TextFormatting.DARK_GRAY + "" + TextFormatting.STRIKETHROUGH + attribute.getName());
				}
			}
		}
		else
		{
			tooltip.add(TextFormatting.GRAY + "" + TextFormatting.ITALIC + I18n.format("levels.misc.attributes.shift"));
		}
		
		tooltip.add("");
	}
}

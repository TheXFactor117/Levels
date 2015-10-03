package com.thexfactor117.levels.events;

import com.thexfactor117.levels.Reference;
import com.thexfactor117.levels.helpers.Ability;
import com.thexfactor117.levels.helpers.Experience;
import com.thexfactor117.levels.helpers.ItemType;
import com.thexfactor117.levels.helpers.Rarity;

import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
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
		Item item = stack.getItem();
		NBTTagCompound nbt = stack.getTagCompound();
		ItemType type = null;

		if (item instanceof ItemSword)
		{
			type = ItemType.WEAPON;
		}

		if (item instanceof ItemArmor)
		{
			type = ItemType.ARMOR;
		}

		if (type != null)
		{
			List<String> tooltip = event.toolTip;
			Rarity rarity = Rarity.getRarity(nbt);
			int level = Experience.getLevel(nbt);
			String exp;

			if (level == Reference.MAX_LEVEL)
			{
				exp = I18n.format("levels.experience.max");
			}
			else
			{
				exp = Experience.getExperience(nbt) + "/" + type.getMaxLevelExp(level);
			}

			tooltip.add("");
			tooltip.add(rarity.getColor() + EnumChatFormatting.ITALIC + I18n.format("levels.rarity." + rarity.ordinal()));
			tooltip.add(I18n.format("levels.level") + ": " + level);
			tooltip.add(I18n.format("levels.experience") + ": " + exp);
			tooltip.add(I18n.format("levels.durability." + type.toString().toLowerCase(), stack.getMaxDamage() - stack.getItemDamage()));
			tooltip.add("");

			for (Ability ability : type.getAbilities())
			{
				if (ability.hasAbility(nbt))
				{
					tooltip.add(ability.getColor() + I18n.format("levels.ability." + ability.toString().toLowerCase()));
				}
			}
		}
	}
}

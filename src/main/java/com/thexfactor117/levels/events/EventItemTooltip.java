package com.thexfactor117.levels.events;

import com.thexfactor117.levels.handlers.ConfigHandler;
import com.thexfactor117.levels.helpers.Ability;
import com.thexfactor117.levels.helpers.AbilityHelper;
import com.thexfactor117.levels.helpers.Experience;
import com.thexfactor117.levels.helpers.NBTHelper;
import com.thexfactor117.levels.helpers.Rarity;

import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * 
 * @author TheXFactor117
 *
 */
public class EventItemTooltip 
{
	/**
	 * Gets called whenever the tooltip for an item needs to appear.
	 * @param event
	 */
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void addInformation(ItemTooltipEvent event)
	{
		ItemStack stack = event.getItemStack();
		Item item = stack.getItem();

		if (item != null)
		{
			if (item instanceof ItemSword || item instanceof ItemBow)
			{
				NBTTagCompound nbt = NBTHelper.loadStackNBT(stack);
				
				if (nbt != null)
				{	
					AbilityHelper abilityHelper = AbilityHelper.ABILITIES;
					Rarity rarity = Rarity.getRarity(nbt);
					String exp;
					
					if (Experience.getLevel(nbt) == ConfigHandler.maxLevelCap) exp = I18n.format("levels.experience.max");
					else exp = Experience.getExperience(nbt) + " / " + Experience.getMaxLevelExp(Experience.getLevel(nbt));
					
					event.getToolTip().add("");
					event.getToolTip().add(rarity.getColor() + TextFormatting.ITALIC + I18n.format("levels.rarity." + rarity.ordinal()));
					event.getToolTip().add("Level: " + Experience.getLevel(nbt));
					event.getToolTip().add("Experience: " + exp);
					event.getToolTip().add("Durability: " + (stack.getMaxDamage() - stack.getItemDamage()) + " / " + stack.getMaxDamage());
					event.getToolTip().add("");
					
					for (Ability ability : abilityHelper.getAbilities())
					{	
						if (ability.hasAbility(nbt))
						{
							event.getToolTip().add(ability.getColor() + I18n.format("levels.ability." + ability.toString().toLowerCase()));
						}
					}
				}
			}
		}
	}
}

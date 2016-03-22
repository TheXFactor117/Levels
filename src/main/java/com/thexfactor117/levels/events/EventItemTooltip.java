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
		ItemStack stack = event.itemStack;
		Item item = stack.getItem();
		NBTTagCompound nbt = NBTHelper.loadStackNBT(stack);

		if (item != null)
		{
			/*****************
			 * MELEE WEAPONS *
			 *****************/
			if (item instanceof ItemSword)
			{
				if (nbt != null)
				{	
					Rarity rarity = Rarity.getRarity(nbt);
					AbilityHelper abilityHelper = AbilityHelper.ABILITIES;
					String exp;
					
					if (Experience.getLevel(nbt) == ConfigHandler.maxLevelCap) exp = I18n.format("levels.experience.max");
					else exp = Experience.getExperience(nbt) + " / " + Experience.getMaxLevelExp(Experience.getLevel(nbt));
					
					event.toolTip.add("");
					event.toolTip.add(rarity.getColor() + TextFormatting.ITALIC + I18n.format("levels.rarity." + rarity.ordinal()));
					event.toolTip.add("Level: " + Experience.getLevel(nbt));
					event.toolTip.add("Experience: " + exp);
					event.toolTip.add("Durability: " + (stack.getMaxDamage() - stack.getItemDamage()) + " / " + stack.getMaxDamage());
					event.toolTip.add("");
					
					for (Ability ability : abilityHelper.getAbilities())
					{	
						if (ability.hasAbility(nbt))
						{
							event.toolTip.add(ability.getColor() + I18n.format("levels.ability." + ability.toString().toLowerCase()));
						}
					}
				}
				else
				{
					nbt = new NBTTagCompound();
					stack.setTagCompound(nbt);
					
					Rarity.UNKNOWN.setRarity(nbt);
					Experience.setExperience(nbt, 0);
					Experience.setLevel(nbt, 1);
				}
			}
			
			/********
			 * BOWS *
			 *******/
			if (item instanceof ItemBow)
			{
				if (nbt != null)
				{
					Rarity rarity = Rarity.getRarity(nbt);
					AbilityHelper abilityHelper = AbilityHelper.ABILITIES;
					String exp;
					
					if (Experience.getLevel(nbt) == ConfigHandler.maxLevelCap) exp = I18n.format("levels.experience.max");
					else exp = Experience.getExperience(nbt) + " / " + Experience.getMaxLevelExp(Experience.getLevel(nbt));
					
					event.toolTip.add(rarity.getColor() + TextFormatting.ITALIC + I18n.format("levels.rarity." + rarity.ordinal()));
					event.toolTip.add("Level: " + Experience.getLevel(nbt));
					event.toolTip.add("Experience: " + exp);
					event.toolTip.add("Durability: " + (stack.getMaxDamage() - stack.getItemDamage()) + " / " + stack.getMaxDamage());
					event.toolTip.add("");
					
					for (Ability ability : abilityHelper.getAbilities())
					{	
						if (ability.hasAbility(nbt))
						{
							event.toolTip.add(ability.getColor() + I18n.format("levels.ability." + ability.toString().toLowerCase()));
						}
					}
				}
				else
				{
					nbt = new NBTTagCompound();
					stack.setTagCompound(nbt);
					
					Rarity.UNKNOWN.setRarity(nbt);
					Experience.setExperience(nbt, 0);
					Experience.setLevel(nbt, 1);
				}
			}
		}
	}
}

package com.thexfactor117.levels.event;

import java.util.ArrayList;
import java.util.Collection;

import org.lwjgl.input.Keyboard;

import com.google.common.collect.Multimap;
import com.thexfactor117.levels.leveling.Ability;
import com.thexfactor117.levels.leveling.Experience;
import com.thexfactor117.levels.leveling.Rarity;
import com.thexfactor117.levels.util.ConfigHandler;
import com.thexfactor117.levels.util.NBTHelper;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * 
 * @author TheXFactor117
 * 
 * Displays information about the weapon when hovered over in an inventory.
 *
 */
public class EventItemTooltip 
{
	/**
	 * Gets called whenever the tooltip for an item needs to appear.
	 * @param event
	 */
	@SideOnly(Side.CLIENT)
	@SubscribeEvent(priority = EventPriority.HIGH)
	public void addInformation(ItemTooltipEvent event)
	{
		ArrayList<String> tooltip = (ArrayList<String>) event.getToolTip();
		ItemStack stack = event.getItemStack();
		Item item = stack.getItem();

		if (item != null)
		{
			if (item instanceof ItemSword || item instanceof ItemAxe || item instanceof ItemArmor || item instanceof ItemBow)
			{
				NBTTagCompound nbt = NBTHelper.loadStackNBT(stack);
				
				if (nbt != null && Experience.isEnabled(nbt))
				{
					Rarity rarity = Rarity.getRarity(nbt);
					int level = Experience.getLevel(nbt);
					int experience = Experience.getExperience(nbt);
					int maxExperience = Experience.getMaxLevelExp(level);
					
					changeTooltips(tooltip, stack, rarity);
					
					// add tooltips
					// formatting
					tooltip.add("");
					tooltip.add(rarity.getColor() + "===============");
					tooltip.add("");
					
					// rarity
					tooltip.add(rarity.getColor() + TextFormatting.ITALIC + I18n.format("levels.rarity." + rarity.ordinal()));
					
					// level
					if (level >= ConfigHandler.MAX_LEVEL)
						tooltip.add(TextFormatting.GRAY + I18n.format("levels.misc.level") + ": " + I18n.format("levels.misc.max"));
					else
						tooltip.add(TextFormatting.GRAY + I18n.format("levels.misc.level") + ": " + level);
					
					// experience
					if (level >= ConfigHandler.MAX_LEVEL)
						tooltip.add(TextFormatting.GRAY + I18n.format("levels.misc.experience") + ": " + I18n.format("levels.misc.max"));
					else
						tooltip.add(TextFormatting.GRAY + I18n.format("levels.misc.experience") + ": " + experience + " / " + maxExperience);
					
					// durability
					if (ConfigHandler.SHOW_DURABILITY)
					{
						tooltip.add(TextFormatting.GRAY + I18n.format("levels.misc.durability") + ": " + (stack.getMaxDamage() - stack.getItemDamage()) + " / " + stack.getMaxDamage());
					}
					
					// abilities
					tooltip.add("");
					if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))
					{
						tooltip.add(TextFormatting.GRAY + "" + TextFormatting.ITALIC + I18n.format("levels.misc.abilities"));
						tooltip.add("");
						
						if (item instanceof ItemSword || item instanceof ItemAxe || item instanceof ItemBow)
						{
							for (Ability ability : Ability.WEAPONS)
							{
								if (ability.hasAbility(nbt))
								{
									tooltip.add(ability.getColor() + ability.getName(nbt));
								}
							}
						}
						else if (item instanceof ItemArmor)
						{
							for (Ability ability : Ability.ARMOR)
							{
								if (ability.hasAbility(nbt))
								{
									tooltip.add(ability.getColor() + ability.getName(nbt));
								}
							}
						}
					}
					else
						tooltip.add(TextFormatting.GRAY + "" + TextFormatting.ITALIC + I18n.format("levels.misc.abilities.shift"));
					
					// formatting
					tooltip.add("");
					tooltip.add(rarity.getColor() + "===============");
					tooltip.add("");
				}
			}
		}
	}
	
	private void changeTooltips(ArrayList<String> tooltip, ItemStack stack, Rarity rarity)
	{	
		if (tooltip.indexOf("When in main hand:") != -1)
		{
			Multimap<String, AttributeModifier> map = stack.getItem().getAttributeModifiers(EntityEquipmentSlot.MAINHAND, stack);
			Collection<AttributeModifier> damageCollection = map.get(SharedMonsterAttributes.ATTACK_DAMAGE.getAttributeUnlocalizedName());
			AttributeModifier damageModifier = (AttributeModifier) damageCollection.toArray()[0];
			
			double damage = damageModifier.getAmount();
			
			switch (rarity)
			{
				case COMMON:
					tooltip.set(tooltip.indexOf("When in main hand:") + 2, tooltip.get(tooltip.indexOf("When in main hand:") + 2) + rarity.getColor() + " (" + (ConfigHandler.COMMON_DAMAGE * (damage + 1F)) + ")");
					break;
				case UNCOMMON:
					tooltip.set(tooltip.indexOf("When in main hand:") + 2, tooltip.get(tooltip.indexOf("When in main hand:") + 2) + rarity.getColor() + " (" + (ConfigHandler.UNCOMMON_DAMAGE * (damage + 1F)) + ")");
					break;
				case RARE:
					tooltip.set(tooltip.indexOf("When in main hand:") + 2, tooltip.get(tooltip.indexOf("When in main hand:") + 2) + rarity.getColor() + " (" + (ConfigHandler.RARE_DAMAGE * (damage + 1F)) + ")");
					break;
				case ULTRA_RARE:
					tooltip.set(tooltip.indexOf("When in main hand:") + 2, tooltip.get(tooltip.indexOf("When in main hand:") + 2) + rarity.getColor() + " (" + (ConfigHandler.ULTRA_RARE_DAMAGE * (damage + 1F)) + ")");
					break;
				case LEGENDARY:
					tooltip.set(tooltip.indexOf("When in main hand:") + 2, tooltip.get(tooltip.indexOf("When in main hand:") + 2) + rarity.getColor() + " (" + (ConfigHandler.LEGENDARY_DAMAGE * (damage + 1F)) + ")");
					break;
				case ARCHAIC:
					tooltip.set(tooltip.indexOf("When in main hand:") + 2, tooltip.get(tooltip.indexOf("When in main hand:") + 2) + rarity.getColor() + " (" + (ConfigHandler.ARCHAIC_DAMAGE * (damage + 1F)) + ")");
					break;
				default:
					break;
			}
		}
	}
}

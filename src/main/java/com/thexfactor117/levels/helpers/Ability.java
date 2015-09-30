package com.thexfactor117.levels.helpers;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.resources.I18n;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;

import java.util.List;

/**
 *
 * @author MrIbby
 *
 */
public enum Ability
{
	FIRE(EnumChatFormatting.DARK_RED),
	FROST(EnumChatFormatting.AQUA),
	POISON(EnumChatFormatting.DARK_GREEN),
	STRENGTH(EnumChatFormatting.LIGHT_PURPLE),
	ETHEREAL(EnumChatFormatting.BLUE),
	VOID(EnumChatFormatting.DARK_PURPLE),
	HARDENED(EnumChatFormatting.WHITE),
	POISONED(EnumChatFormatting.DARK_GREEN),
	IMMUNIZATION(EnumChatFormatting.GOLD);
	
	private static final Ability[] ABILITIES = Ability.values();
	private final String color;
	
	Ability(Object color)
	{
		this.color = color.toString();
	}

	@SideOnly(Side.CLIENT)
	public static void addTooltip(NBTTagCompound nbt, List<String> tooltip)
	{
		for (Ability ability : ABILITIES)
		{
			if (ability.hasAbility(nbt))
			{
				ability.addTooltip(tooltip);
			}
		}
	}
	
	public boolean hasAbility(NBTTagCompound nbt)
	{
		return nbt != null && nbt.getBoolean(toString());
	}
	
	public void addAbility(NBTTagCompound nbt)
	{
		nbt.setBoolean(toString(), true);
	}
	
	public void removeAbility(NBTTagCompound nbt)
	{
		nbt.removeTag(toString());
	}

	@SideOnly(Side.CLIENT)
	private void addTooltip(List<String> tooltip)
	{
		tooltip.add(color + I18n.format("levels.ability." + toString().toLowerCase()));
	}
}

package com.thexfactor117.levels.tabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import com.thexfactor117.levels.init.ModItems;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * 
 * @author TheXFactor117
 *
 */
public class TabLevels extends CreativeTabs
{
	public String name;
	
	public TabLevels(int i, String name)
	{
		super(i, name);
		this.name = name;
	}
	
	@SideOnly(Side.CLIENT)
	public Item getTabIconItem()
	{
		if (this.name == "tabLevels")
		{
			return ModItems.ironSword;
		} 
			
		return null;
	}
}

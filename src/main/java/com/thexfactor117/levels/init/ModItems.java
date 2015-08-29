package com.thexfactor117.levels.init;

import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;

import com.thexfactor117.levels.api.ItemMelee;
import com.thexfactor117.levels.helpers.RegisterHelper;

/**
 * 
 * @author TheXFactor117
 *
 */
public class ModItems 
{
	// Materials
	public static ToolMaterial WOOD_SWORD = EnumHelper.addToolMaterial("WOOD_SWORD", 0, 59, 2.0F, 0.0F, 15);
	public static ToolMaterial GOLD_SWORD = EnumHelper.addToolMaterial("GOLD_SWORD", 0, 32, 12.0F, 0.0F, 22);
	public static ToolMaterial STONE_SWORD = EnumHelper.addToolMaterial("STONE_SWORD", 1, 131, 4.0F, 1.0F, 5);
	public static ToolMaterial IRON_SWORD = EnumHelper.addToolMaterial("IRON_SWORD", 2, 250, 6.0F, 2.0F, 14);
	public static ToolMaterial DIAMOND_SWORD = EnumHelper.addToolMaterial("DIAMOND_SWORD", 3, 1561, 8.0F, 3.0F, 10);
	
	// Items
	public static Item woodSword = new ItemMelee(WOOD_SWORD, "woodSword");
	public static Item goldSword = new ItemMelee(GOLD_SWORD, "goldSword");
	public static Item stoneSword = new ItemMelee(STONE_SWORD, "stoneSword");
	public static Item ironSword = new ItemMelee(IRON_SWORD, "ironSword");
	public static Item diamondSword = new ItemMelee(DIAMOND_SWORD, "diamondSword");
	
	public static void registerItems()
	{
		RegisterHelper.registerItem(woodSword);
		RegisterHelper.registerItem(goldSword);
		RegisterHelper.registerItem(stoneSword);
		RegisterHelper.registerItem(ironSword);
		RegisterHelper.registerItem(diamondSword);
	}
}

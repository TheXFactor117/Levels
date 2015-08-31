package com.thexfactor117.levels.init;

import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;

import com.thexfactor117.levels.api.ItemLevelSwordBase;
import com.thexfactor117.levels.helpers.LogHelper;
import com.thexfactor117.levels.helpers.RegisterHelper;

/**
 * 
 * @author TheXFactor117
 *
 */
public class ModItems 
{
	// Materials
	public static ToolMaterial WOOD_SWORD;
	public static ToolMaterial GOLD_SWORD;
	public static ToolMaterial STONE_SWORD;
	public static ToolMaterial IRON_SWORD;
	public static ToolMaterial DIAMOND_SWORD;
	if (ConfigHandler.enableEnchantments)
	{
		WOOD_SWORD = EnumHelper.addToolMaterial("WOOD_SWORD", 0, 59, 2.0F, 0.0F, 0);
		GOLD_SWORD = EnumHelper.addToolMaterial("GOLD_SWORD", 0, 32, 12.0F, 0.0F, 0);
		STONE_SWORD = EnumHelper.addToolMaterial("STONE_SWORD", 1, 131, 4.0F, 1.0F, 0);
		IRON_SWORD = EnumHelper.addToolMaterial("IRON_SWORD", 2, 250, 6.0F, 2.0F, 0);
		DIAMOND_SWORD = EnumHelper.addToolMaterial("DIAMOND_SWORD", 3, 1561, 8.0F, 3.0F, 0);
	}
	else
	{
		WOOD_SWORD = EnumHelper.addToolMaterial("WOOD_SWORD", 0, 59, 2.0F, 0.0F, 15);
		GOLD_SWORD = EnumHelper.addToolMaterial("GOLD_SWORD", 0, 32, 12.0F, 0.0F, 22);
		STONE_SWORD = EnumHelper.addToolMaterial("STONE_SWORD", 1, 131, 4.0F, 1.0F, 5);
		IRON_SWORD = EnumHelper.addToolMaterial("IRON_SWORD", 2, 250, 6.0F, 2.0F, 14);
		DIAMOND_SWORD = EnumHelper.addToolMaterial("DIAMOND_SWORD", 3, 1561, 8.0F, 3.0F, 10);
	}
	
	// Items
	public static Item woodSword = new ItemLevelSwordBase(WOOD_SWORD, "woodSword");
	public static Item goldSword = new ItemLevelSwordBase(GOLD_SWORD, "goldSword");
	public static Item stoneSword = new ItemLevelSwordBase(STONE_SWORD, "stoneSword");
	public static Item ironSword = new ItemLevelSwordBase(IRON_SWORD, "ironSword");
	public static Item diamondSword = new ItemLevelSwordBase(DIAMOND_SWORD, "diamondSword");
	
	public static void registerItems()
	{
		RegisterHelper.registerItem(woodSword);
		RegisterHelper.registerItem(goldSword);
		RegisterHelper.registerItem(stoneSword);
		RegisterHelper.registerItem(ironSword);
		RegisterHelper.registerItem(diamondSword);
		LogHelper.info("Custom weapons have been registered.");
	}
}

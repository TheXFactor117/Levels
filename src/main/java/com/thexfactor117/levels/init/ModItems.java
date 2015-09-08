package com.thexfactor117.levels.init;

import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

import com.thexfactor117.levels.helpers.LogHelper;
import com.thexfactor117.levels.helpers.RegisterHelper;
import com.thexfactor117.levels.items.ItemDiamondArmor;
import com.thexfactor117.levels.items.ItemGoldArmor;
import com.thexfactor117.levels.items.ItemIronArmor;
import com.thexfactor117.levels.items.ItemLeatherArmor;

/**
 * 
 * @author TheXFactor117
 *
 */
public class ModItems 
{
	// Materials
	public static ArmorMaterial LEATHER_ARMOR = EnumHelper.addArmorMaterial("LEATHER_ARMOR", 5, new int[]{1, 3, 2, 1}, 15);
	public static ArmorMaterial GOLD_ARMOR = EnumHelper.addArmorMaterial("GOLD_ARMOR", 7, new int[]{2, 5, 3, 1}, 25);
	//public static ArmorMaterial CHAIN_ARMOR = EnumHelper.addArmorMaterial("CHAIN_ARMOR", 15, new int[]{2, 5, 4, 1}, 12);
	public static ArmorMaterial IRON_ARMOR = EnumHelper.addArmorMaterial("IRON_ARMOR", 15, new int[]{2, 6, 5, 2}, 9);
	public static ArmorMaterial DIAMOND_ARMOR = EnumHelper.addArmorMaterial("DIAMOND_ARMOR", 33, new int[]{3, 8, 6, 3}, 10);
	
	public static Item leatherHelmet = new ItemLeatherArmor(LEATHER_ARMOR, 0, "leatherHelmet");
	public static Item leatherChestplate = new ItemLeatherArmor(LEATHER_ARMOR, 1, "leatherChestplate");
	public static Item leatherLeggings = new ItemLeatherArmor(LEATHER_ARMOR, 2, "leatherLeggings");
	public static Item leatherBoots = new ItemLeatherArmor(LEATHER_ARMOR, 3, "leatherBoots");
	public static Item goldHelmet = new ItemGoldArmor(GOLD_ARMOR, 0, "goldHelmet");
	public static Item goldChestplate = new ItemGoldArmor(GOLD_ARMOR, 1, "goldChestplate");
	public static Item goldLeggings = new ItemGoldArmor(GOLD_ARMOR, 2, "goldLeggings");
	public static Item goldBoots = new ItemGoldArmor(GOLD_ARMOR, 3, "goldBoots");
	public static Item ironHelmet = new ItemIronArmor(IRON_ARMOR, 0, "ironHelmet");
	public static Item ironChestplate = new ItemIronArmor(IRON_ARMOR, 1, "ironChestplate");
	public static Item ironLeggings = new ItemIronArmor(IRON_ARMOR, 2, "ironLeggings");
	public static Item ironBoots = new ItemIronArmor(IRON_ARMOR, 3, "ironBoots");
	public static Item diamondHelmet = new ItemDiamondArmor(DIAMOND_ARMOR, 0, "diamondHelmet");
	public static Item diamondChestplate = new ItemDiamondArmor(DIAMOND_ARMOR, 1, "diamondChestplate");
	public static Item diamondLeggings = new ItemDiamondArmor(DIAMOND_ARMOR, 2, "diamondLeggings");
	public static Item diamondBoots = new ItemDiamondArmor(DIAMOND_ARMOR, 3, "diamondBoots");
	
	public static void registerItems()
	{		
		RegisterHelper.registerItem(leatherHelmet);
		RegisterHelper.registerItem(leatherChestplate);
		RegisterHelper.registerItem(leatherLeggings);
		RegisterHelper.registerItem(leatherBoots);
		RegisterHelper.registerItem(goldHelmet);
		RegisterHelper.registerItem(goldChestplate);
		RegisterHelper.registerItem(goldLeggings);
		RegisterHelper.registerItem(goldBoots);
		RegisterHelper.registerItem(ironHelmet);
		RegisterHelper.registerItem(ironChestplate);
		RegisterHelper.registerItem(ironLeggings);
		RegisterHelper.registerItem(ironBoots);
		RegisterHelper.registerItem(diamondHelmet);
		RegisterHelper.registerItem(diamondChestplate);
		RegisterHelper.registerItem(diamondLeggings);
		RegisterHelper.registerItem(diamondBoots);
		LogHelper.info("Custom armors have been registered");
	}
}

package com.thexfactor117.levels.init;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * 
 * @author TheXFactor117
 *
 */
public class ModRecipes 
{
	public static void registerRecipes()
	{
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.woodSword), "A", "A", "B", 'A', Blocks.planks, 'B', Items.stick);
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.goldSword), "A", "A", "B", 'A', Items.gold_ingot, 'B', Items.stick);
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.stoneSword), "A", "A", "B", 'A', Blocks.cobblestone, 'B', Items.stick);
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.ironSword), "A", "A", "B", 'A', Items.iron_ingot, 'B', Items.stick);
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.diamondSword), "A", "A", "B", 'A', Items.diamond, 'B', Items.stick);
		//GameRegistry.addShapedRecipe(new ItemStack(ModItems.leatherHelmet), "AAA", "A A", 'A', Items.leather);
		//GameRegistry.addShapedRecipe(new ItemStack(ModItems.leatherChestplate), "A A", "AAA", "AAA", 'A', Items.leather);
		//GameRegistry.addShapedRecipe(new ItemStack(ModItems.leatherLeggings), "AAA", "A A", "A A", 'A', Items.leather);
		//GameRegistry.addShapedRecipe(new ItemStack(ModItems.leatherChestplate), "A A", "A A", 'A', Items.leather);
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.goldHelmet), "AAA", "A A", 'A', Items.gold_ingot);
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.goldChestplate), "A A", "AAA", "AAA", 'A', Items.gold_ingot);
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.goldLeggings), "AAA", "A A", "A A", 'A', Items.gold_ingot);
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.goldBoots), "A A", "A A", 'A', Items.gold_ingot);
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.ironHelmet), "AAA", "A A", 'A', Items.iron_ingot);
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.ironChestplate), "A A", "AAA", "AAA", 'A', Items.iron_ingot);
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.ironLeggings), "AAA", "A A", "A A", 'A', Items.iron_ingot);
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.ironBoots), "A A", "A A", 'A', Items.iron_ingot);
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.diamondHelmet), "AAA", "A A", 'A', Items.diamond);
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.diamondChestplate), "A A", "AAA", "AAA", 'A', Items.diamond);
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.diamondLeggings), "AAA", "A A", "A A", 'A', Items.diamond);
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.diamondBoots), "A A", "A A", 'A', Items.diamond);
		
		GameRegistry.addShapedRecipe(new ItemStack(Items.wooden_sword), "A", 'A', ModItems.woodSword);
		GameRegistry.addShapedRecipe(new ItemStack(Items.golden_sword), "A", 'A', ModItems.goldSword);
		GameRegistry.addShapedRecipe(new ItemStack(Items.stone_sword), "A", 'A', ModItems.stoneSword);
		GameRegistry.addShapedRecipe(new ItemStack(Items.iron_sword), "A", 'A', ModItems.ironSword);
		GameRegistry.addShapedRecipe(new ItemStack(Items.diamond_sword), "A", 'A', ModItems.diamondSword);
		GameRegistry.addShapedRecipe(new ItemStack(Items.leather_helmet), "A", 'A', ModItems.leatherHelmet);
		GameRegistry.addShapedRecipe(new ItemStack(Items.leather_chestplate), "A", 'A', ModItems.leatherChestplate);
		GameRegistry.addShapedRecipe(new ItemStack(Items.leather_leggings), "A", 'A', ModItems.leatherLeggings);
		GameRegistry.addShapedRecipe(new ItemStack(Items.leather_boots), "A", 'A', ModItems.leatherBoots);
		GameRegistry.addShapedRecipe(new ItemStack(Items.golden_helmet), "A", 'A', ModItems.goldHelmet);
		GameRegistry.addShapedRecipe(new ItemStack(Items.golden_chestplate), "A", 'A', ModItems.goldChestplate);
		GameRegistry.addShapedRecipe(new ItemStack(Items.golden_leggings), "A", 'A', ModItems.goldLeggings);
		GameRegistry.addShapedRecipe(new ItemStack(Items.golden_boots), "A", 'A', ModItems.goldBoots);
		GameRegistry.addShapedRecipe(new ItemStack(Items.iron_helmet), "A", 'A', ModItems.ironHelmet);
		GameRegistry.addShapedRecipe(new ItemStack(Items.iron_chestplate), "A", 'A', ModItems.ironChestplate);
		GameRegistry.addShapedRecipe(new ItemStack(Items.iron_leggings), "A", 'A', ModItems.ironLeggings);
		GameRegistry.addShapedRecipe(new ItemStack(Items.iron_boots), "A", 'A', ModItems.ironBoots);
		GameRegistry.addShapedRecipe(new ItemStack(Items.diamond_helmet), "A", 'A', ModItems.diamondHelmet);
		GameRegistry.addShapedRecipe(new ItemStack(Items.diamond_chestplate), "A", 'A', ModItems.diamondChestplate);
		GameRegistry.addShapedRecipe(new ItemStack(Items.diamond_leggings), "A", 'A', ModItems.diamondLeggings);
		GameRegistry.addShapedRecipe(new ItemStack(Items.diamond_boots), "A", 'A', ModItems.diamondBoots);
	}
}

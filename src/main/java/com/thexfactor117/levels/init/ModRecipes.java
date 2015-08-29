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
	}
}

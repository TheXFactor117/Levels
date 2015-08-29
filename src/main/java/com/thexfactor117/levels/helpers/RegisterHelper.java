package com.thexfactor117.levels.helpers;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * 
 * @author TheXFactor117
 *
 */
public class RegisterHelper 
{
	/**
	 * Registers the specified block.
	 * @param block - block to be registered.
	 */
	public static void registerBlock(Block block)
	{
		GameRegistry.registerBlock(block, block.getUnlocalizedName().substring(5));
	}

	/**
	 * Registers the specified item.
	 * @param item - item to be registered.
	 */
	public static void registerItem(Item item)
	{
		GameRegistry.registerItem(item, item.getUnlocalizedName().substring(5));
	}
}

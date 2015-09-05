package com.thexfactor117.levels.items;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;

import com.thexfactor117.levels.Reference;
import com.thexfactor117.levels.api.ItemLevelArmorBase;
import com.thexfactor117.levels.init.ModItems;

/**
 * 
 * @author TheXFactor117
 *
 */
public class ItemIronArmor extends ItemLevelArmorBase
{
	public ItemIronArmor(ArmorMaterial material, int type, String name)
	{
		super(material, type, name);
	}
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	{		
		if (stack.getItem() == ModItems.ironLeggings)
		{
			return Reference.MODID + ":models/armor/iron_layer_2.png";
		}
		else
		{
			return Reference.MODID + ":models/armor/iron_layer_1.png";
		}
	}
}

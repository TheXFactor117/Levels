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
public class ItemLeatherArmor extends ItemLevelArmorBase
{
	public ItemLeatherArmor(ArmorMaterial material, int type, String name)
	{
		super(material, type, name);
	}
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	{		
		if (stack.getItem() == ModItems.leatherLeggings)
		{
			return Reference.MODID + ":models/armor/leather_layer_2.png";
		}
		else
		{
			return Reference.MODID + ":models/armor/leather_layer_1.png";
		}
	}
}

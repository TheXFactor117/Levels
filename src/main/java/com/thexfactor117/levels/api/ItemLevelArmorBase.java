package com.thexfactor117.levels.api;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import com.thexfactor117.levels.Reference;
import com.thexfactor117.levels.handlers.ConfigHandler;
import com.thexfactor117.levels.helpers.AbilityHelper;
import com.thexfactor117.levels.init.ModTabs;

public class ItemLevelArmorBase extends ItemArmor
{
	public ItemLevelArmorBase(ArmorMaterial material, int type, String name)
	{
		super(material, 0, type);
		this.setUnlocalizedName(name);
		this.setTextureName(Reference.MODID + ":" + name);
		this.setCreativeTab(ModTabs.tabLevels);
	}
	
	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int index, boolean bool)
	{
		if (ConfigHandler.enableLevelingSystem)
		{
			NBTTagCompound nbt = stack.getTagCompound();
			
			if (nbt == null)
			{
				nbt = new NBTTagCompound();
				stack.setTagCompound(nbt);
				
				nbt.setBoolean("HARDENED", false);
				nbt.setBoolean("POISONED", false);
				nbt.setBoolean("STRENGTH", false);
				nbt.setBoolean("IMMUNIZATION", false);
				nbt.setBoolean("ETHEREAL", false);
				nbt.setBoolean("VOID", false);
				
				nbt.setInteger("LEVEL", 1);
				nbt.setInteger("EXPERIENCE", 0);
			}
			
			/*
			 * Leveling System
			 */
			if (nbt != null)
			{
				if (nbt.getInteger("LEVEL") == 1 && nbt.getInteger("EXPERIENCE") >= 1000)
				{
					nbt.setInteger("LEVEL", 2);
					AbilityHelper.drawLevelTwoArmorAbility(stack);
				}
				
				if (nbt.getInteger("LEVEL") == 2 && nbt.getInteger("EXPERIENCE") >= 2500)
				{
					nbt.setInteger("LEVEL", 3);
					AbilityHelper.drawLevelThreeArmorAbility(stack);
				}
				
				if (nbt.getInteger("LEVEL") == 3 && nbt.getInteger("EXPERIENCE") >= 5000)
				{
					nbt.setInteger("LEVEL", 4);
					AbilityHelper.drawLevelFourArmorAbility(stack);
				}
				
				if (nbt.getInteger("LEVEL") == 4 && nbt.getInteger("EXPERIENCE") >= 10000)
				{
					nbt.setInteger("LEVEL", 5);
					AbilityHelper.drawLevelFiveArmorAbility(stack);
				}
				
				if (nbt.getInteger("LEVEL") == 5 && nbt.getInteger("EXPERIENCE") >= 20000)
				{
					nbt.setInteger("LEVEL", 6);
					AbilityHelper.drawLevelSixArmorAbility(stack);
				}
			}
		}
	}
}

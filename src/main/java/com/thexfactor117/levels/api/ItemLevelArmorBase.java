package com.thexfactor117.levels.api;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

import com.thexfactor117.levels.Reference;
import com.thexfactor117.levels.handlers.ConfigHandler;
import com.thexfactor117.levels.helpers.AbilityHelper;
import com.thexfactor117.levels.init.ModTabs;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

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
		}
	}
	
	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack stack)
	{
		if (ConfigHandler.enableLevelingSystem)
		{
			NBTTagCompound nbt = stack.getTagCompound();
			
			if (nbt != null)
			{											
				if (nbt.getBoolean("IMMUNIZATION"))
				{
					if (player.isPotionActive(Potion.weakness.id)) player.removePotionEffect(Potion.weakness.id);
					if (player.isPotionActive(Potion.moveSlowdown.id)) player.removePotionEffect(Potion.moveSlowdown.id);
					if (player.isPotionActive(Potion.poison.id)) player.removePotionEffect(Potion.poison.id);
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
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean isHeld)
	{
		NBTTagCompound nbt = stack.getTagCompound();
		
		if (nbt != null)
		{
			list.add("Level: " + nbt.getInteger("LEVEL"));
			
			if (nbt.getInteger("LEVEL") == 1) list.add("Experience: " + nbt.getInteger("EXPERIENCE") + "/1000");
			if (nbt.getInteger("LEVEL") == 2) list.add("Experience: " + nbt.getInteger("EXPERIENCE") + "/2500");
			if (nbt.getInteger("LEVEL") == 3) list.add("Experience: " + nbt.getInteger("EXPERIENCE") + "/5000");
			if (nbt.getInteger("LEVEL") == 4) list.add("Experience: " + nbt.getInteger("EXPERIENCE") + "/10000");
			if (nbt.getInteger("LEVEL") == 5) list.add("Experience: " + nbt.getInteger("EXPERIENCE") + "/20000");
			if (nbt.getInteger("LEVEL") == 6) list.add("Experience: " + nbt.getInteger("EXPERIENCE"));
			
			list.add(stack.getMaxDamage() - stack.getItemDamage() + " Hits Remaining");
			list.add("");
			
			if (nbt.getBoolean("HARDENED")) list.add(EnumChatFormatting.WHITE + "Hardened");
			if (nbt.getBoolean("POISONED")) list.add(EnumChatFormatting.DARK_GREEN + "Poisoned");
			if (nbt.getBoolean("STRENGTH")) list.add(EnumChatFormatting.LIGHT_PURPLE + "Strength");
			if (nbt.getBoolean("IMMUNIZATION")) list.add(EnumChatFormatting.GOLD + "Immunization");
			if (nbt.getBoolean("ETHEREAL")) list.add(EnumChatFormatting.BLUE + "Ethereal");
			if (nbt.getBoolean("VOID")) list.add(EnumChatFormatting.DARK_PURPLE + "Void");
		}
	}
}

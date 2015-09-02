package com.thexfactor117.levels.api;

import java.util.List;
import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;

import com.thexfactor117.levels.Reference;
import com.thexfactor117.levels.handlers.ConfigHandler;
import com.thexfactor117.levels.helpers.AbilityHelper;
import com.thexfactor117.levels.helpers.LogHelper;
import com.thexfactor117.levels.init.ModTabs;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemLevelArmorBase extends ItemArmor implements ISpecialArmor
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

	/*
	 * ISpecialArmor implemented methods.
	 */
	@Override
	public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot) 
	{
		if (source.getSourceOfDamage() instanceof EntityLivingBase)
		{
			return new ArmorProperties(1, 1.0D, MathHelper.floor_double(damage * 20.0D));
		}
		else
		{
			return new ArmorProperties(1, 1.0D, 1);
		}
	}

	@Override
	public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) 
	{
		if(slot == 0)
		{
			return 2;
		}
		else if(slot == 1)
		{
			return 3;
		}
		else if(slot == 2)
		{
			return 4;
		}
		else
		{
			return 2;
		}
	}

	@Override
	public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot) 
	{
		stack.damageItem(damage, entity);
		
		if (ConfigHandler.enableLevelingSystem)
		{
			NBTTagCompound nbt = stack.getTagCompound();
			
			if (nbt != null)
			{
				Random rand = new Random();
				
				if (source.getSourceOfDamage() instanceof EntityLivingBase)
				{
					EntityLivingBase enemy = (EntityLivingBase) source.getSourceOfDamage();
					
					if (nbt.getBoolean("HARDENED"))
					{
						int var = rand.nextInt(5);
						if (var == 0)
						{
							enemy.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 20*2, 10));
						}
					}
					
					if (nbt.getBoolean("POISONED"))
					{
						int var = rand.nextInt(1);
						if (var == 0)
						{
							enemy.addPotionEffect(new PotionEffect(Potion.poison.id, 20*10, 0));
							LogHelper.info("Hello?");
						}
					}
				}
			}
		}
	}
}

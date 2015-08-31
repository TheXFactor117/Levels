package com.thexfactor117.levels.api;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
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
	
	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack stack)
	{
		if (ConfigHandler.enableLevelingSystem)
		{
			NBTTagCompound nbt = stack.getTagCompound();
			
			if (nbt != null)
			{				
				if (player.getLastAttackerTime() <= (double) 0.05D)
				{
					nbt.setInteger("EXPERIENCE", nbt.getInteger("EXPERIENCE") + 1);
				}
				
				for (int i = 0; i < 4; i++)
				{
					Random rand = new Random();
					
					if (player.getCurrentArmor(i).getTagCompound().getBoolean("HARDENED"))
					{
						if (player.getLastAttackerTime() <= (double) 0.05D)
						{
							int var = rand.nextInt(5);
							if (var == 0)
							{
								EntityLivingBase attacker = player.getLastAttacker();
								attacker.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 20*2, 10));
							}
						}
					}
					
					if (player.getCurrentArmor(i).getTagCompound().getBoolean("POISONED"))
					{
						if (player.getLastAttackerTime() <= (double) 0.05D)
						{
							int var = rand.nextInt(5);
							if (var == 0)
							{
								EntityLivingBase attacker = player.getLastAttacker();
								attacker.addPotionEffect(new PotionEffect(Potion.poison.id, 20*5, 0));
							}
						}
					}
					
					if (player.getCurrentArmor(i).getTagCompound().getBoolean("STRENGTH"))
					{
						if (player.getLastAttackerTime() <= (double) 0.05D)
						{
							int var = rand.nextInt(10);
							if (var == 0)
							{
								player.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 20*10, 0));
							}
						}
					}
					
					if (player.getCurrentArmor(i).getTagCompound().getBoolean("IMMUNIZATION"))
					{
						if (player.isPotionActive(Potion.weakness.id)) player.removePotionEffect(Potion.weakness.id);
						if (player.isPotionActive(Potion.moveSlowdown.id)) player.removePotionEffect(Potion.moveSlowdown.id);
						if (player.isPotionActive(Potion.poison.id)) player.removePotionEffect(Potion.poison.id);
					}
					
					if (player.getCurrentArmor(i).getTagCompound().getBoolean("ETHEREAL"))
					{
						if (player.getLastAttackerTime() <= (double) 0.05D)
						{
							int var = rand.nextInt(20);
							if (var == 0)
							{
								player.setHealth(20);
							}
						}
					}
					
					if (player.getCurrentArmor(i).getTagCompound().getBoolean("VOID"))
					{
						if (player.getLastAttackerTime() <= (double) 0.05D)
						{
							int var = rand.nextInt(20);
							if (var == 0)
							{
								EntityLivingBase attacker = player.getLastAttacker();
								attacker.setDead();
							}
						}
					}
				}
			}
		}
	}
}
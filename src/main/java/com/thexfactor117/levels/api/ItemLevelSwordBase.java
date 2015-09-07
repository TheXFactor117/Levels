package com.thexfactor117.levels.api;

import java.util.List;
import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

import com.thexfactor117.levels.Reference;
import com.thexfactor117.levels.handlers.ConfigHandler;
import com.thexfactor117.levels.helpers.AbilityHelper;
import com.thexfactor117.levels.init.ModTabs;

/**
 * 
 * @author TheXFactor117
 * 
 */
public class ItemLevelSwordBase extends ItemSword
{
	public final ToolMaterial material;
	
	public ItemLevelSwordBase(ToolMaterial material, String name)
	{
		super(material);
		this.material = material;
		this.setUnlocalizedName(name);
		this.setTextureName(Reference.MODID + ":" + name);
		this.setCreativeTab(ModTabs.tabLevels);
	}
	
	/**
	 * Called every tick the item is in the entities inventory. Used to create nbt tags
	 * and level up the weapon when possible.
	 */
	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int i, boolean b)
	{
		if (ConfigHandler.enableLevelingSystem)
		{
			NBTTagCompound nbt = stack.getTagCompound();
			
			if (nbt == null)
			{
				nbt = new NBTTagCompound();
				stack.setTagCompound(nbt);
				
				nbt.setBoolean("FIRE", false);
				nbt.setBoolean("FROST", false);
				nbt.setBoolean("POISON", false);
				nbt.setBoolean("STRENGTH", false);
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
					AbilityHelper.drawLevelTwoMeleeAbility(stack);
					Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_GREEN + "You've begun to master wielding this weapon."));
				}
				
				if (nbt.getInteger("LEVEL") == 2 && nbt.getInteger("EXPERIENCE") >= 2500)
				{
					nbt.setInteger("LEVEL", 3);
					AbilityHelper.drawLevelThreeMeleeAbility(stack);
				}
				
				if (nbt.getInteger("LEVEL") == 3 && nbt.getInteger("EXPERIENCE") >= 5000)
				{
					nbt.setInteger("LEVEL", 4);
					AbilityHelper.drawLevelFourMeleeAbility(stack);
				}
				
				if (nbt.getInteger("LEVEL") == 4 && nbt.getInteger("EXPERIENCE") >= 10000)
				{
					nbt.setInteger("LEVEL", 5);
					AbilityHelper.drawLevelFiveMeleeAbility(stack);
				}
				
				if (nbt.getInteger("LEVEL") == 5 && nbt.getInteger("EXPERIENCE") >= 20000)
				{
					nbt.setInteger("LEVEL", 6);
					AbilityHelper.drawLevelFiveMeleeAbility(stack);
				}
			}
		}
	}
	
	/**
	 * Called when the weapon hits a living entity. Used to apply abilities if available.
	 */
	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase enemy, EntityLivingBase player)
	{
		if (ConfigHandler.enableLevelingSystem)
		{
			NBTTagCompound nbt = stack.getTagCompound();
			Random rand = new Random();
			
			/*
			 * Damages the weapon, increases weapon experience. Amounts vary depending on weapon level.
			 */
			if (nbt.getInteger("LEVEL") == 1 || nbt.getInteger("LEVEL") == 2 || nbt.getInteger("LEVEL") == 3)
			{
				stack.damageItem(1, player);
				nbt.setInteger("EXPERIENCE", nbt.getInteger("EXPERIENCE") + 1);
			}
			else if (nbt.getInteger("LEVEL") == 4)
			{
				int i = rand.nextInt(9);
				if (i != 0)
				{
					stack.damageItem(1, player);
				}
				
				nbt.setInteger("EXPERIENCE", nbt.getInteger("EXPERIENCE") + 1);
			}
			else if (nbt.getInteger("LEVEL") == 5)
			{
				int i = rand.nextInt(9);
				int i1 = rand.nextInt(9);
				if (i != 0)
				{
					stack.damageItem(1, player);
				}
				
				if (i1 != 0)
				{
					nbt.setInteger("EXPERIENCE", nbt.getInteger("EXPERIENCE") + 1);
				}
				else
				{
					nbt.setInteger("EXPERIENCE", nbt.getInteger("EXPERIENCE") + 2);
				}
			}
			else
			{
				int i = rand.nextInt(4);
				int i1 = rand.nextInt(4);
				if (i != 0)
				{
					stack.damageItem(1, player);
				}
				
				if (i1 != 0)
				{
					nbt.setInteger("EXPERIENCE", nbt.getInteger("EXPERIENCE") + 1);
				}
				else
				{
					nbt.setInteger("EXPERIENCE", nbt.getInteger("EXPERIENCE") + 2);
				}
			}
			
			/*
			 * Abilities
			 */
			if (nbt.getBoolean("FIRE")) enemy.setFire(4);
			if (nbt.getBoolean("FROST")) enemy.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 20*4, 10));
			if (nbt.getBoolean("POISON")) enemy.addPotionEffect(new PotionEffect(Potion.poison.id, 20*4, 0));
			if (nbt.getBoolean("Strength")) 
			{
				int var = rand.nextInt(9);
				if (var == 0) player.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 20*10, 0));
			}
			
			if (nbt.getBoolean("ETHEREAL"))
			{
				float healthToBeAdded = material.getDamageVsEntity() + 4F;
				
				int var1 = rand.nextInt(3);
				if (var1 == 0) 
				{				
					player.setHealth(player.getHealth() + healthToBeAdded);
					
					if (player.getHealth() > 20)
					{
						player.setHealth(20);
					}
				}
			}
			
			if (nbt.getBoolean("VOID"))
			{
				int var2 = rand.nextInt(19);
				if (var2 == 0)
				{
					enemy.setHealth(0);
				}
			}
		}
		
		return true;
	}
	
	/**
	 * Sets the "lore" of an item.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean isHeld)
	{
		if (ConfigHandler.enableLevelingSystem)
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
				
				list.add("");
				
				if (nbt.getBoolean("FIRE")) list.add(EnumChatFormatting.DARK_RED + "Fire");
				if (nbt.getBoolean("FROST")) list.add(EnumChatFormatting.AQUA + "Frost");
				if (nbt.getBoolean("POISON")) list.add(EnumChatFormatting.DARK_GREEN + "Poison");
				if (nbt.getBoolean("STRENGTH")) list.add(EnumChatFormatting.LIGHT_PURPLE + "Strength");
				if (nbt.getBoolean("ETHEREAL")) list.add(EnumChatFormatting.BLUE + "Ethereal");
				if (nbt.getBoolean("VOID")) list.add(EnumChatFormatting.DARK_PURPLE + "Void");
			}
		}
	}
}

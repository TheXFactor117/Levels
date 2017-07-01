package com.thexfactor117.levels.client.gui;

import java.io.IOException;

import com.thexfactor117.levels.Levels;
import com.thexfactor117.levels.config.Config;
import com.thexfactor117.levels.leveling.Experience;
import com.thexfactor117.levels.leveling.Rarity;
import com.thexfactor117.levels.leveling.attributes.ArmorAttribute;
import com.thexfactor117.levels.leveling.attributes.BowAttribute;
import com.thexfactor117.levels.leveling.attributes.ShieldAttribute;
import com.thexfactor117.levels.leveling.attributes.WeaponAttribute;
import com.thexfactor117.levels.util.GuiHandler;
import com.thexfactor117.levels.util.NBTHelper;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemShield;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * 
 * @author TheXFactor117
 *
 */
public class GuiItemInformation extends GuiScreen
{
	private GuiButton selection;
	
	@SideOnly(Side.CLIENT)
	@Override
	public void initGui() 
	{
		selection = new GuiButton(0, this.width / 2 - 166, 125, 110, 20, "Attribute Selection");
		
		this.buttonList.add(selection);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) 
	{
		this.drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
		
		EntityPlayer player = this.mc.player;
		
		if (player != null)
		{
			ItemStack stack = player.inventory.getCurrentItem();
			
			if (stack != null)
			{
				NBTTagCompound nbt = NBTHelper.loadStackNBT(stack);
				
				if (nbt != null)
				{
					drawStrings(stack, nbt);
				}
			}
		}
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	protected void actionPerformed(GuiButton button) throws IOException 
	{
		EntityPlayerSP player = mc.player;
		
		if (player != null)
		{
			ItemStack stack = player.inventory.getCurrentItem();
			
			if (stack != null)
			{
				if (button == selection)
				{
					if (stack.getItem() instanceof ItemSword)
					{
						player.openGui(Levels.instance, GuiHandler.WEAPON_ATTRIBUTES, player.getEntityWorld(), (int) player.posX, (int) player.posY, (int) player.posZ);
					}
					else if (stack.getItem() instanceof ItemArmor)
					{
						player.openGui(Levels.instance, GuiHandler.ARMOR_ATTRIBUTES, player.getEntityWorld(), (int) player.posX, (int) player.posY, (int) player.posZ);
					}
					else if (stack.getItem() instanceof ItemBow)
					{
						player.openGui(Levels.instance, GuiHandler.BOW_ATTRIBUTES, player.getEntityWorld(), (int) player.posX, (int) player.posY, (int) player.posZ);
					}
					else if (stack.getItem() instanceof ItemShield)
					{
						player.openGui(Levels.instance, GuiHandler.SHIELD_ATTRIBUTES, player.getEntityWorld(), (int) player.posX, (int) player.posY, (int) player.posZ);
					}
					else if (stack.getItem() instanceof ItemTool)
					{
						//player.openGui(Levels.instance, GuiHandler., player.getEntityWorld(), (int) player.posX, (int) player.posY, (int) player.posZ);
					}
				}
			}
		}
	}
	
	/**
	 * Draws the strings for the ability selection screen.
	 * @param stack
	 * @param abilities
	 * @param nbt
	 */
	private void drawStrings(ItemStack stack, NBTTagCompound nbt)
	{
		Rarity rarity = Rarity.getRarity(nbt);
		
		drawCenteredString(fontRendererObj, stack.getDisplayName(), width / 2, 20, rarity.getHex());
		drawString(fontRendererObj, I18n.format("levels.misc.rarity") + ": " + rarity.getName(), width / 2 - 50, 40, rarity.getHex());
		drawCenteredString(fontRendererObj, I18n.format("levels.misc.attributes"), width / 2, 80, 0xFFFFFF);
		drawCenteredString(fontRendererObj, I18n.format("levels.misc.attributes.tokens") + ": " + Experience.getAttributeTokens(nbt), width / 2 - 112, 100, 0xFFFFFF);
		drawCenteredString(fontRendererObj, I18n.format("levels.misc.attributes.current"), width / 2 + 112, 100, 0xFFFFFF);
		
		if (Experience.getLevel(nbt) == Config.maxLevel)
		{
			drawString(fontRendererObj, I18n.format("levels.misc.level") + ": " + I18n.format("levels.misc.max"), width / 2 - 50, 50, 0xFFFFFF);
			drawString(fontRendererObj, I18n.format("levels.misc.experience") + ": " + I18n.format("levels.misc.max"), width / 2 - 50, 60, 0xFFFFFF);
		}
		else
		{
			drawString(fontRendererObj, I18n.format("levels.misc.level") + ": " + Experience.getLevel(nbt), width / 2 - 50, 50, 0xFFFFFF);
			drawString(fontRendererObj, I18n.format("levels.misc.experience") + ": " + Experience.getExperience(nbt) + " / " + Experience.getNextLevelExperience(Experience.getLevel(nbt)), width / 2 - 50, 60, 0xFFFFFF);
		}
		
		int k = -1;
		
		if (stack.getItem() instanceof ItemSword)
		{	
			for (int i = 0; i < WeaponAttribute.WEAPON_ATTRIBUTES.size(); i++)
			{
				if (WeaponAttribute.WEAPON_ATTRIBUTES.get(i).hasAttribute(nbt))
				{
					k++;
					drawCenteredString(fontRendererObj, WeaponAttribute.WEAPON_ATTRIBUTES.get(i).getName(nbt), width / 2 + 112, 115 + (10 * k), WeaponAttribute.WEAPON_ATTRIBUTES.get(i).getHex());
				}
			}
		}
		else if (stack.getItem() instanceof ItemTool)
		{
			
		}
		else if (stack.getItem() instanceof ItemBow)
		{
			for (int i = 0; i < BowAttribute.BOW_ATTRIBUTES.size(); i++)
			{
				if (BowAttribute.BOW_ATTRIBUTES.get(i).hasAttribute(nbt))
				{
					k++;
					drawCenteredString(fontRendererObj, BowAttribute.BOW_ATTRIBUTES.get(i).getName(nbt), width / 2 + 112, 115 + (10 * k), BowAttribute.BOW_ATTRIBUTES.get(i).getHex());
				}
			}
		}
		else if (stack.getItem() instanceof ItemArmor)
		{
			for (int i = 0; i < ArmorAttribute.ARMOR_ATTRIBUTES.size(); i++)
			{
				if (ArmorAttribute.ARMOR_ATTRIBUTES.get(i).hasAttribute(nbt))
				{
					k++;
					drawCenteredString(fontRendererObj, ArmorAttribute.ARMOR_ATTRIBUTES.get(i).getName(nbt), width / 2 + 112, 115 + (10 * k), ArmorAttribute.ARMOR_ATTRIBUTES.get(i).getHex());
				}
			}
		}
		else if (stack.getItem() instanceof ItemShield)
		{
			for (int i = 0; i < ShieldAttribute.SHIELD_ATTRIBUTES.size(); i++)
			{
				if (ShieldAttribute.SHIELD_ATTRIBUTES.get(i).hasAttribute(nbt))
				{
					k++;
					drawCenteredString(fontRendererObj, ShieldAttribute.SHIELD_ATTRIBUTES.get(i).getName(nbt), width / 2 + 112, 115 + (10 * k), ShieldAttribute.SHIELD_ATTRIBUTES.get(i).getHex());
				}
			}
		}
	}
	
	@Override
	public boolean doesGuiPauseGame()
	{
		return false;
	}
}

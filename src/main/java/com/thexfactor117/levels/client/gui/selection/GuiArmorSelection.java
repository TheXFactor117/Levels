package com.thexfactor117.levels.client.gui.selection;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.thexfactor117.levels.Levels;
import com.thexfactor117.levels.leveling.Experience;
import com.thexfactor117.levels.leveling.Rarity;
import com.thexfactor117.levels.leveling.attributes.ArmorAttribute;
import com.thexfactor117.levels.network.PacketAttributeSelection;
import com.thexfactor117.levels.util.NBTHelper;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.client.config.HoverChecker;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * 
 * @author TheXFactor117
 *
 */
public class GuiArmorSelection extends GuiScreen
{
	private GuiButton[] attributes;
	
	@SideOnly(Side.CLIENT)
	@Override
	public void initGui() 
	{
		EntityPlayer player = this.mc.player;
		ItemStack stack = player.inventory.getCurrentItem();
		NBTTagCompound nbt = NBTHelper.loadStackNBT(stack);
		
		if (player != null && stack != null && nbt != null && stack.getItem() instanceof ItemArmor)
		{
			attributes = new GuiButton[ArmorAttribute.ARMOR_ATTRIBUTES.size()];
			
			for (int i = 0; i < attributes.length; i++)
			{
				attributes[i] = new GuiButton(i, width / 2 - 147, 60 + (i * 20), 75, 20, ArmorAttribute.ARMOR_ATTRIBUTES.get(i).getName(nbt));
				this.buttonList.add(attributes[i]);
				attributes[i].enabled = false;
			}
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) 
	{
		this.drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
		
		EntityPlayer player = this.mc.player;
		ItemStack stack = player.inventory.getCurrentItem();
		NBTTagCompound nbt = NBTHelper.loadStackNBT(stack);
		
		if (player != null && stack != null &&  nbt != null && stack.getItem() instanceof ItemArmor)
		{
			drawCenteredString(fontRendererObj, I18n.format("levels.misc.attributes"), width / 2, 20, 0xFFFFFF);
			drawCenteredString(fontRendererObj, I18n.format("levels.misc.attributes.tokens") + ": " + Experience.getAttributeTokens(nbt), width / 2 - 112, 40, 0xFFFFFF);
			drawCenteredString(fontRendererObj, I18n.format("levels.misc.attributes.current"), width / 2 + 112, 40, 0xFFFFFF);
			
			int k = -1;
			
			for (int i = 0; i < ArmorAttribute.ARMOR_ATTRIBUTES.size(); i++)
			{
				if (ArmorAttribute.ARMOR_ATTRIBUTES.get(i).hasAttribute(nbt))
				{
					k++;
					drawCenteredString(fontRendererObj, ArmorAttribute.ARMOR_ATTRIBUTES.get(i).getName(nbt), width / 2 + 112, 60 + (10 * k), ArmorAttribute.ARMOR_ATTRIBUTES.get(i).getHex());
				}
			}
			
			displayButtons(nbt);
			drawTooltips(nbt, mouseX, mouseY);
		}
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	protected void actionPerformed(GuiButton button) throws IOException 
	{
		EntityPlayerSP player = mc.player;
		ItemStack stack = player.inventory.getCurrentItem();
		NBTTagCompound nbt = NBTHelper.loadStackNBT(stack);
		
		if (player != null && stack != null && nbt != null)
		{
			if (Experience.getAttributeTokens(nbt) > 0)
			{
				if (stack.getItem() instanceof ItemArmor)
				{
					for (int i = 0; i < attributes.length; i++)
					{
						if (button == attributes[i])
							Levels.network.sendToServer(new PacketAttributeSelection(i));
					}
				}
			}
		}
	}
	
	/**
	 * Determines which buttons need to be enabled.
	 * @param buttons
	 * @param nbt
	 */
	private void displayButtons(NBTTagCompound nbt)
	{
		if (Experience.getAttributeTokens(nbt) > 0)
		{
			for (int i = 0; i < attributes.length; i++)
			{
				ArrayList<ArmorAttribute> list = ArmorAttribute.ARMOR_ATTRIBUTES;
				
				/*
				 * Enable Uncommon attributes UNLESS already added to nbt AND are not already tier 3.
				 * Enable ALL attributes that have already been added UNLESS they are at tier 3.
				 */
				if (Experience.getAttributeTokens(nbt) == 1)
				{
					if (list.get(i).getRarity() == Rarity.UNCOMMON && list.get(i).getAttributeTier(nbt) != 3)
						attributes[i].enabled = true;
					
					if (list.get(i).hasAttribute(nbt) && list.get(i).getAttributeTier(nbt) != 3)
						attributes[i].enabled = true;
					else if (list.get(i).hasAttribute(nbt) && list.get(i).getAttributeTier(nbt) == 3)
						attributes[i].enabled = false;
				}
				
				/*
				 * Enable UNCOMMON AND RARE attributes UNLESS already added to nbt AND are not already tier 3.
				 * Enable ALL attributes that have already been added UNLESS they are at tier 3.
				 */
				if (Experience.getAttributeTokens(nbt) == 2)
				{
					if ((list.get(i).getRarity() == Rarity.RARE || list.get(i).getRarity() == Rarity.UNCOMMON) && !list.get(i).hasAttribute(nbt))
						attributes[i].enabled = true;
					
					if (list.get(i).hasAttribute(nbt) && list.get(i).getAttributeTier(nbt) != 3)
						attributes[i].enabled = true;
					else if (list.get(i).hasAttribute(nbt) && list.get(i).getAttributeTier(nbt) == 3)
						attributes[i].enabled = false;
				}
				else
				{
					if (list.get(i).getRarity() == Rarity.RARE && !list.get(i).hasAttribute(nbt))
						attributes[i].enabled = false;
					
					if (list.get(i).hasAttribute(nbt) && list.get(i).getAttributeTier(nbt) != 3)
						attributes[i].enabled = true;
					else if (list.get(i).hasAttribute(nbt) && list.get(i).getAttributeTier(nbt) == 3)
						attributes[i].enabled = false;
				}
				
				/*
				 * Enable ALL attributes UNLESS already added to nbt AND are not already tier 3.
				 * Enable ALL attributes that have already been added.
				 */
				if (Experience.getAttributeTokens(nbt) >= 3)
				{
					if (!list.get(i).hasAttribute(nbt))
						attributes[i].enabled = true;
					
					if (list.get(i).hasAttribute(nbt) && list.get(i).getAttributeTier(nbt) != 3)
						attributes[i].enabled = true;
					else if (list.get(i).hasAttribute(nbt) && list.get(i).getAttributeTier(nbt) == 3)
						attributes[i].enabled = false;
				}
				else
				{
					if (list.get(i).getRarity() == Rarity.LEGENDARY && !list.get(i).hasAttribute(nbt))
						attributes[i].enabled = false;
					
					if (list.get(i).hasAttribute(nbt) && list.get(i).getAttributeTier(nbt) != 3)
						attributes[i].enabled = true;
					else if (list.get(i).hasAttribute(nbt) && list.get(i).getAttributeTier(nbt) == 3)
						attributes[i].enabled = false;
				}
			}
		}
		else
		{
			for (int i = 0; i < attributes.length; i++)
			{
				attributes[i].enabled = false;
			}
		}
	}
	
	private void drawTooltips(NBTTagCompound nbt, int mouseX, int mouseY)
	{	
		for (int i = 0; i < attributes.length; i++)
		{
			HoverChecker checker = new HoverChecker(attributes[i], 0);

			if (checker.checkHover(mouseX, mouseY))
			{
				int cost = 1;
				
				if (ArmorAttribute.ARMOR_ATTRIBUTES.get(i).getRarity() == Rarity.UNCOMMON && !ArmorAttribute.ARMOR_ATTRIBUTES.get(i).hasAttribute(nbt)) cost = 1;
				else if (ArmorAttribute.ARMOR_ATTRIBUTES.get(i).getRarity() == Rarity.RARE && !ArmorAttribute.ARMOR_ATTRIBUTES.get(i).hasAttribute(nbt)) cost = 2;
				else if (ArmorAttribute.ARMOR_ATTRIBUTES.get(i).getRarity() == Rarity.LEGENDARY && !ArmorAttribute.ARMOR_ATTRIBUTES.get(i).hasAttribute(nbt)) cost = 3;
				else if (ArmorAttribute.ARMOR_ATTRIBUTES.get(i).getAttributeTier(nbt) == 3) cost = 0;
				
				List<String> list = new ArrayList<String>();
				list.add(ArmorAttribute.ARMOR_ATTRIBUTES.get(i).getColor() + ArmorAttribute.ARMOR_ATTRIBUTES.get(i).getName(nbt));
				list.add(TextFormatting.GRAY + "Cost: " + cost + " token(s)");
				list.add("");
				list.add(I18n.format("levels.attributes.armors.info." + ArmorAttribute.ARMOR_ATTRIBUTES.get(i).ordinal()));
				list.add("");
				list.add("Tiers:");
				list.add(" I - " + ArmorAttribute.ARMOR_ATTRIBUTES.get(i).getColor() + I18n.format("levels.attributes.armors.info." + ArmorAttribute.ARMOR_ATTRIBUTES.get(i).ordinal() + ".tier1"));
				list.add(" II - " + ArmorAttribute.ARMOR_ATTRIBUTES.get(i).getColor() + I18n.format("levels.attributes.armors.info." + ArmorAttribute.ARMOR_ATTRIBUTES.get(i).ordinal() + ".tier2"));
				list.add(" III - " + ArmorAttribute.ARMOR_ATTRIBUTES.get(i).getColor() + I18n.format("levels.attributes.armors.info." + ArmorAttribute.ARMOR_ATTRIBUTES.get(i).ordinal() + ".tier3"));
				drawHoveringText(list, mouseX + 3, mouseY + 3);
			}
		}
	}
	
	@Override
	public boolean doesGuiPauseGame()
	{
		return false;
	}
}

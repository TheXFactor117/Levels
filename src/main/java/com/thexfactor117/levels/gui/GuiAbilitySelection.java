package com.thexfactor117.levels.gui;

import java.io.IOException;

import com.thexfactor117.levels.Levels;
import com.thexfactor117.levels.leveling.Ability;
import com.thexfactor117.levels.leveling.Experience;
import com.thexfactor117.levels.leveling.Rarity;
import com.thexfactor117.levels.network.PacketGuiAbility;
import com.thexfactor117.levels.util.NBTHelper;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * 
 * @author TheXFactor117
 *
 */
public class GuiAbilitySelection extends GuiScreen
{
	private GuiButton[] weaponAbilities = new GuiButton[Ability.WEAPON_ABILITIES];;
	
	@SideOnly(Side.CLIENT)
	@Override
	public void initGui() 
	{	
		EntityPlayer player = this.mc.thePlayer;
	    
	    if (player != null)
	    {
	    	ItemStack stack = player.inventory.getCurrentItem();
	    	
	    	if (stack != null && stack.getItem() instanceof ItemSword)
	    	{
	    		NBTTagCompound nbt = stack.getTagCompound();
	    		
	    		if (nbt != null)
	    		{
	    			for (int i = 0; i < weaponAbilities.length - 3; i++)
	    			{
	    				weaponAbilities[i] = new GuiButton(i, width / 2 - 200, 100 + (i * 20), 75, 20, Ability.values()[i].getName() + " (" + Ability.values()[i].getTier() + ")");
	    				this.buttonList.add(weaponAbilities[i]);
	    				weaponAbilities[i].enabled = false;
	    			}
	    			
	    			for (int i = weaponAbilities.length - 3; i < weaponAbilities.length; i++)
	    			{
	    				weaponAbilities[i] = new GuiButton(i, width / 2 - 100, -20 + (i * 20), 75, 20, Ability.values()[i].getName() + " (" + Ability.values()[i].getTier() + ")");
	    				this.buttonList.add(weaponAbilities[i]);
	    				weaponAbilities[i].enabled = false;
	    			}
	    		}
	    	}
	    }
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) 
	{
		this.drawDefaultBackground();

		EntityPlayer player = this.mc.thePlayer;
	    
	    if (player != null)
	    {
	    	ItemStack stack = player.inventory.getCurrentItem();
	    	
	    	if (stack != null && stack.getItem() instanceof ItemSword)
	    	{
	    		NBTTagCompound nbt = NBTHelper.loadStackNBT(stack);
	    		
	    		if (nbt != null)
	    		{
	    			drawStrings(stack, nbt);
	    			displayButtons(nbt);
	    		}
	    	}
	    }
	    
	    super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	protected void actionPerformed(GuiButton button) throws IOException 
	{
		EntityPlayerSP player = mc.thePlayer;
		
		if (player != null)
		{
			ItemStack stack = player.inventory.getCurrentItem();
			
			if (stack != null)
			{
				NBTTagCompound nbt = NBTHelper.loadStackNBT(stack);
				
				if (nbt != null)
				{
					if (Experience.getAbilityTokens(nbt) > 0)
					{
						for (int i = 0; i < weaponAbilities.length; i++)
						{
							if (button == weaponAbilities[i])
							{
								Levels.network.sendToServer(new PacketGuiAbility(i));
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 * Draws the strings for the ability selection screen.
	 * @param stack
	 * @param nbt
	 */
	private void drawStrings(ItemStack stack, NBTTagCompound nbt)
	{
		Rarity rarity = Rarity.getRarity(nbt);
		
		drawCenteredString(fontRendererObj, stack.getDisplayName(), width / 2, 20, rarity.getHex());
		drawString(fontRendererObj, I18n.format("levels.misc.rarity") + ": " + rarity.getName(), width / 2 - 50, 40, rarity.getHex());
		drawString(fontRendererObj, I18n.format("levels.misc.level") + ": " + Experience.getLevel(nbt), width / 2 - 50, 50, 0xFFFFFF);
		drawString(fontRendererObj, I18n.format("levels.misc.experience") + ": " + Experience.getExperience(nbt) + " / " + Experience.getMaxLevelExp(Experience.getLevel(nbt)), width / 2 - 50, 60, 0xFFFFFF);
		drawCenteredString(fontRendererObj, I18n.format("levels.misc.abilities"), width / 2, 80, 0xFFFFFF);
		drawCenteredString(fontRendererObj, I18n.format("levels.misc.abilities.tokens") + ": " + Experience.getAbilityTokens(nbt), width / 2 - 112, 85, 0xFFFFFF);
		drawCenteredString(fontRendererObj, I18n.format("levels.misc.abilities.purchased"), width / 2 + 112, 100, 0xFFFFFF);
		drawCenteredString(fontRendererObj, I18n.format("levels.misc.abilities.active"), width / 2 + 75, 120, 0xFFFFFF);
		drawCenteredString(fontRendererObj, I18n.format("levels.misc.abilities.passive"), width / 2 + 150, 120, 0xFFFFFF);
		
		int j = -1;
		int k = -1;
		
		for (int i = 0; i < Ability.values().length - 3; i++)
		{	
			if (Ability.values()[i].hasAbility(nbt))
			{
				j++;
				drawCenteredString(fontRendererObj, Ability.values()[i].getName(nbt), width / 2 + 75, 135 + (j * 10), Ability.values()[i].getHex());
			}
		}
		
		for (int i = Ability.values().length - 3; i < Ability.values().length; i++)
		{
			if (Ability.values()[i].hasAbility(nbt))
			{
				k++;
				drawCenteredString(fontRendererObj, Ability.values()[i].getName(nbt), width / 2 + 150, 135 + (k * 10), Ability.values()[i].getHex());
			}
		}
	}
	
	/**
	 * Determines which buttons need to be enabled based on available ability tokens and if the
	 * weapon is of a high enough level to enable.
	 * @param nbt
	 */
	private void displayButtons(NBTTagCompound nbt)
	{
		if (Experience.getAbilityTokens(nbt) > 0)
		{
			for (int i = 0; i < weaponAbilities.length; i++)
			{	
				if (Experience.getAbilityTokens(nbt) == 1)
				{
					if (Ability.values()[i].getTier() == 1)
						weaponAbilities[i].enabled = true;
					
					if (Ability.values()[i].hasAbility(nbt) && Ability.values()[i].canUpgradeLevel(nbt))
						weaponAbilities[i].enabled = true;
				}
				
				if (Experience.getAbilityTokens(nbt) == 2)
				{
					if (Ability.values()[i].getTier() <= 2)
						weaponAbilities[i].enabled = true;
				}
				else
				{
					if (Ability.values()[i].getTier() == 2)
						weaponAbilities[i].enabled = false;
					
					if (Ability.values()[i].hasAbility(nbt) && Ability.values()[i].canUpgradeLevel(nbt))
						weaponAbilities[i].enabled = true;
				}
				
				if (Experience.getAbilityTokens(nbt) >= 3)
				{
					weaponAbilities[i].enabled = true;
				}
				else
				{
					if (Ability.values()[i].getTier() == 3)
						weaponAbilities[i].enabled = false;
					
					if (Ability.values()[i].hasAbility(nbt) && Ability.values()[i].canUpgradeLevel(nbt))
						weaponAbilities[i].enabled = true;
				}
			}
		}
		else
		{
			for (int i = 0; i < weaponAbilities.length; i++)
			{
				weaponAbilities[i].enabled = false;
			}
		}
	}
	
	@Override
	public boolean doesGuiPauseGame()
	{
		return false;
	}
}

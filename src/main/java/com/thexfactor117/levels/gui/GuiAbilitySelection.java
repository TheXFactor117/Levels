package com.thexfactor117.levels.gui;

import java.io.IOException;

import com.thexfactor117.levels.leveling.Ability;
import com.thexfactor117.levels.leveling.Experience;
import com.thexfactor117.levels.leveling.Rarity;
import com.thexfactor117.levels.util.ConfigHandler;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;

/**
 * 
 * @author TheXFactor117
 *
 */
public class GuiAbilitySelection extends GuiScreen
{
	private GuiButton[] weaponAbilities = new GuiButton[Ability.WEAPON_ABILITIES];;
	
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
	    			for (int i = 0; i < weaponAbilities.length / 2; i++)
	    			{
	    				weaponAbilities[i] = new GuiButton(i, width / 2 - 200, 100 + (i * 20), 75, 20, Ability.values()[i].getName());
	    				this.buttonList.add(weaponAbilities[i]);
	    				weaponAbilities[i].enabled = false;
	    			}
	    			
	    			for (int i = weaponAbilities.length / 2; i < weaponAbilities.length; i++)
	    			{
	    				weaponAbilities[i] = new GuiButton(i, width / 2 - 100, 20 + (i * 20), 75, 20, Ability.values()[i].getName());
	    				this.buttonList.add(weaponAbilities[i]);
	    				weaponAbilities[i].enabled = false;
	    			}
	    			
	    			if (Experience.getAbilityTokens(nbt) > 0)
	    			{
	    				for (int i = 0; i < weaponAbilities.length; i++)
		    			{
		    				if (Experience.getLevel(nbt) < (double) (ConfigHandler.MAX_LEVEL * (1.0 / 3.0)) && Ability.values()[i].getTier() == 1)
		    					weaponAbilities[i].enabled = true;
		    				else if (Experience.getLevel(nbt) < (double) (ConfigHandler.MAX_LEVEL * (2.0 / 3.0)) && Ability.values()[i].getTier() <= 2)
		    					weaponAbilities[i].enabled = true;
		    				else if (Experience.getLevel(nbt) == ConfigHandler.MAX_LEVEL)
		    					weaponAbilities[i].enabled = true;
		    			}
	    			}
	    		}
	    	}
	    }
	}
	
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
	    		NBTTagCompound nbt = stack.getTagCompound();
	    		
	    		if (nbt != null)
	    		{
	    			Rarity rarity = Rarity.getRarity(nbt);
	    			Ability.FIRE.addAbility(nbt, 1);
	    			Ability.FROST.addAbility(nbt, 1);
	    			Experience.setAbilityTokens(nbt, 1);
	    			Experience.setLevel(nbt, 6);
	    			
	    			drawCenteredString(fontRendererObj, stack.getDisplayName(), width / 2, 20, rarity.getHex());
	    			drawString(fontRendererObj, "Rarity: " + rarity.getName(), width / 2 - 50, 40, rarity.getHex());
	    			drawString(fontRendererObj, "Level: " + Experience.getLevel(nbt), width / 2 - 50, 50, 0xFFFFFF);
	    			drawString(fontRendererObj, "Experience: " + Experience.getExperience(nbt) + " / " + Experience.getMaxLevelExp(Experience.getLevel(nbt)), width / 2 - 50, 60, 0xFFFFFF);
	    			drawCenteredString(fontRendererObj, "Abilities", width / 2, 80, 0xFFFFFF);
	    			drawCenteredString(fontRendererObj, "Ability Tokens: " + Experience.getAbilityTokens(nbt), width / 2 - 112, 190, 0xFFFFFF);
	    			drawCenteredString(fontRendererObj, "Purchased Abilities", width / 2 + 112, 100, 0xFFFFFF);
	    			drawCenteredString(fontRendererObj, "Active", width / 2 + 75, 120, 0xFFFFFF);
	    			drawCenteredString(fontRendererObj, "Passive", width / 2 + 150, 120, 0xFFFFFF);
	    			
	    			int j = -1;
	    			
	    			for (int i = 0; i < Ability.values().length; i++)
	    			{	
	    				if (Ability.values()[i].hasAbility(nbt))
	    				{
	    					j++;
	    					drawCenteredString(fontRendererObj, Ability.values()[i].getName(), width / 2 + 150, 135 + (j * 10), Ability.values()[i].getHex());
	    				}
	    			}
	    		}
	    	}
	    }
	    
	    super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException 
	{
		EntityPlayer player = mc.thePlayer;
		
		if (player != null)
		{
			ItemStack stack = player.inventory.getCurrentItem();
			
			if (stack != null)
			{
				NBTTagCompound nbt = stack.getTagCompound();
				
				if (nbt != null)
				{
					if (Experience.getAbilityTokens(nbt) > 0)
					{
						for (int i = 0; i < weaponAbilities.length; i++)
						{
							if (button == weaponAbilities[i])
							{
								Ability.values()[i].addAbility(nbt, 1);
								Experience.setAbilityTokens(nbt, Experience.getAbilityTokens(nbt) - 1);
							}
						}
					}
				}
			}
		}
	}
}

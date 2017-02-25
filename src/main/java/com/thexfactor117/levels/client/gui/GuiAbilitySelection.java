package com.thexfactor117.levels.client.gui;

import java.io.IOException;
import java.util.ArrayList;

import com.thexfactor117.levels.Levels;
import com.thexfactor117.levels.config.Config;
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
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemBow;
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
	private GuiButton[] weaponAbilities;
	private GuiButton[] armorAbilities;
	
	@SideOnly(Side.CLIENT)
	@Override
	public void initGui() 
	{	
		EntityPlayer player = this.mc.thePlayer;
	    
	    if (player != null)
	    {
	    	ItemStack stack = player.inventory.getCurrentItem();
	    	
	    	if (stack != null)
	    	{
	    		if (stack.getItem() instanceof ItemSword || stack.getItem() instanceof ItemAxe || stack.getItem() instanceof ItemBow)
		    	{
		    		weaponAbilities = new GuiButton[Ability.WEAPON_ABILITIES];
		    		NBTTagCompound nbt = stack.getTagCompound();
		    		
		    		if (nbt != null)
		    		{
		    			int j = 0;
		    			
		    			for (int i = 0; i < weaponAbilities.length; i++)
		    			{
		    				if (Ability.WEAPONS.get(i).getType().equals("active"))
			    			{
		    					weaponAbilities[i] = new GuiButton(i, width / 2 - 200, 100 + (i * 20), 75, 20, Ability.WEAPONS.get(i).getName() + " (" + Ability.WEAPONS.get(i).getTier() + ")");
		    					j++;
			    			}
		    				else
			    				weaponAbilities[i] = new GuiButton(i, width / 2 - 100, 100 + ((i - j) * 20), 75, 20, Ability.WEAPONS.get(i).getName() + " (" + Ability.WEAPONS.get(i).getTier() + ")");
		    				
		    				this.buttonList.add(weaponAbilities[i]);
		    				weaponAbilities[i].enabled = false;
		    			}
		    		}
		    	}
		    	else if (stack.getItem() instanceof ItemArmor)
		    	{
		    		armorAbilities = new GuiButton[Ability.ARMOR_ABILITIES];
		    		NBTTagCompound nbt = stack.getTagCompound();
		    		
		    		if (nbt != null)
		    		{
		    			int j = 0;
		    			
		    			for (int i = 0; i < armorAbilities.length; i++)
		    			{
		    				if (Ability.ARMOR.get(i).getType().equals("active"))
			    			{
		    					armorAbilities[i] = new GuiButton(i, width / 2 - 200, 100 + (i * 20), 75, 20, Ability.ARMOR.get(i).getName() + " (" + Ability.ARMOR.get(i).getTier() + ")");
		    					j++;
			    			}
		    				else
		    					armorAbilities[i] = new GuiButton(i, width / 2 - 100, 100 + ((i - j) * 20), 75, 20, Ability.ARMOR.get(i).getName() + " (" + Ability.ARMOR.get(i).getTier() + ")");
		    				
		    				this.buttonList.add(armorAbilities[i]);
		    				armorAbilities[i].enabled = false;
		    			}
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
	    	
	    	if (stack != null)
	    	{
	    		if (stack.getItem() instanceof ItemSword || stack.getItem() instanceof ItemAxe || stack.getItem() instanceof ItemArmor || stack.getItem() instanceof ItemBow)
	    		{
	    			NBTTagCompound nbt = NBTHelper.loadStackNBT(stack);
		    		
		    		if (nbt != null)
		    		{	
		    			if (stack.getItem() instanceof ItemSword || stack.getItem() instanceof ItemAxe || stack.getItem() instanceof ItemBow)
		    			{
		    				drawStrings(stack, Ability.WEAPONS, nbt);
		    				displayButtons(weaponAbilities, Ability.WEAPONS, nbt);
		    			}
		    			else if (stack.getItem() instanceof ItemArmor)
		    			{
		    				drawStrings(stack, Ability.ARMOR, nbt);
		    				displayButtons(armorAbilities, Ability.ARMOR, nbt);
		    			}
		    		}
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
						if (stack.getItem() instanceof ItemSword || stack.getItem() instanceof ItemAxe || stack.getItem() instanceof ItemBow)
						{
							for (int i = 0; i < weaponAbilities.length; i++)
							{
								if (button == weaponAbilities[i])
								{
									Levels.network.sendToServer(new PacketGuiAbility(i));
								}
							}
						}
						else if (stack.getItem() instanceof ItemArmor)
						{
							for (int i = 0; i < armorAbilities.length; i++)
							{
								if (button == armorAbilities[i])
								{
									Levels.network.sendToServer(new PacketGuiAbility(i));
								}
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
	 * @param abilities
	 * @param nbt
	 */
	private void drawStrings(ItemStack stack, ArrayList<Ability> abilities, NBTTagCompound nbt)
	{
		Rarity rarity = Rarity.getRarity(nbt);
		
		drawCenteredString(fontRendererObj, stack.getDisplayName(), width / 2, 20, rarity.getHex());
		drawString(fontRendererObj, I18n.format("levels.misc.rarity") + ": " + rarity.getName(), width / 2 - 50, 40, rarity.getHex());
		drawCenteredString(fontRendererObj, I18n.format("levels.misc.abilities"), width / 2, 80, 0xFFFFFF);
		drawCenteredString(fontRendererObj, I18n.format("levels.misc.abilities.tokens") + ": " + Experience.getAbilityTokens(nbt), width / 2 - 112, 85, 0xFFFFFF);
		drawCenteredString(fontRendererObj, I18n.format("levels.misc.abilities.purchased"), width / 2 + 112, 100, 0xFFFFFF);
		drawCenteredString(fontRendererObj, I18n.format("levels.misc.abilities.active"), width / 2 + 75, 120, 0xFFFFFF);
		drawCenteredString(fontRendererObj, I18n.format("levels.misc.abilities.passive"), width / 2 + 150, 120, 0xFFFFFF);
		
		if (Experience.getLevel(nbt) == Config.maxLevel)
		{
			drawString(fontRendererObj, I18n.format("levels.misc.level") + ": " + I18n.format("levels.misc.max"), width / 2 - 50, 50, 0xFFFFFF);
			drawString(fontRendererObj, I18n.format("levels.misc.experience") + ": " + I18n.format("levels.misc.max"), width / 2 - 50, 60, 0xFFFFFF);
		}
		else
		{
			drawString(fontRendererObj, I18n.format("levels.misc.level") + ": " + Experience.getLevel(nbt), width / 2 - 50, 50, 0xFFFFFF);
			drawString(fontRendererObj, I18n.format("levels.misc.experience") + ": " + Experience.getExperience(nbt) + " / " + Experience.getMaxLevelExp(Experience.getLevel(nbt)), width / 2 - 50, 60, 0xFFFFFF);
		}
		
		int j = -1;
		int k = -1;
		
		for (int i = 0; i < abilities.size(); i++)
		{
			if (abilities.get(i).hasAbility(nbt))
			{
				if (abilities.get(i).getType().equals("active"))
				{
					j++;
					drawCenteredString(fontRendererObj, abilities.get(i).getName(nbt), width / 2 + 75, 135 + (j * 10), abilities.get(i).getHex());
				}
				else
				{
					k++;
					drawCenteredString(fontRendererObj, abilities.get(i).getName(nbt), width / 2 + 150, 135 + (k * 10), abilities.get(i).getHex());
				}
			}
		}
	}
	
	/**
	 * Determines which buttons need to be enabled based on available ability tokens and if the
	 * weapon is of a high enough level to enable.
	 * @param buttons
	 * @param abilities
	 * @param nbt
	 */
	private void displayButtons(GuiButton[] buttons, ArrayList<Ability> abilities, NBTTagCompound nbt)
	{
		if (Experience.getAbilityTokens(nbt) > 0)
		{
			for (int i = 0; i < buttons.length; i++)
			{	
				if (Experience.getAbilityTokens(nbt) == 1)
				{
					if (abilities.get(i).getTier() == 1)
						buttons[i].enabled = true;
					
					if (abilities.get(i).hasAbility(nbt) && abilities.get(i).canUpgradeLevel(nbt))
						buttons[i].enabled = true;
					else if (abilities.get(i).hasAbility(nbt))
						buttons[i].enabled = false;
				}
				
				if (Experience.getAbilityTokens(nbt) == 2)
				{
					if (abilities.get(i).getTier() <= 2)
						buttons[i].enabled = true;
				}
				else
				{
					if (abilities.get(i).getTier() == 2)
						buttons[i].enabled = false;
					
					if (abilities.get(i).hasAbility(nbt) && abilities.get(i).canUpgradeLevel(nbt))
						buttons[i].enabled = true;
					else if (abilities.get(i).hasAbility(nbt))
						buttons[i].enabled = false;
				}
				
				if (Experience.getAbilityTokens(nbt) >= 3)
				{
					buttons[i].enabled = true;
					
					if (abilities.get(i).hasAbility(nbt) && abilities.get(i).canUpgradeLevel(nbt))
						buttons[i].enabled = true;
					else if (abilities.get(i).hasAbility(nbt))
						buttons[i].enabled = false;
				}
				else
				{
					if (abilities.get(i).getTier() == 3)
						buttons[i].enabled = false;
					
					if (abilities.get(i).hasAbility(nbt) && abilities.get(i).canUpgradeLevel(nbt))
						buttons[i].enabled = true;
					else if (abilities.get(i).hasAbility(nbt))
						buttons[i].enabled = false;
				}
				
				if (abilities.get(i).hasAbility(nbt) && abilities.get(i).getType().equals("passive"))
				{
					buttons[i].enabled = false;
				}
			}
		}
		else
		{
			for (int i = 0; i < buttons.length; i++)
			{
				buttons[i].enabled = false;
			}
		}
	}
	
	@Override
	public boolean doesGuiPauseGame()
	{
		return false;
	}
}

package com.thexfactor117.levels.events;

import com.thexfactor117.levels.Levels;
import com.thexfactor117.levels.proxies.ClientProxy;
import com.thexfactor117.levels.util.GuiHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemShield;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * 
 * @author TheXFactor117
 *
 */
public class EventInput 
{
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onKeyPress(InputEvent.KeyInputEvent event)
	{
		KeyBinding l = ClientProxy.keyBindingL;
		Minecraft mc = Minecraft.getMinecraft();
		EntityPlayer player = mc.player;
		
		if (player != null)
		{
			ItemStack stack = player.inventory.getCurrentItem();
			
			if (stack != null)
			{
				Item current = stack.getItem();
				
				if (current != null)
				{
					if (current instanceof ItemSword || current instanceof ItemTool || current instanceof ItemArmor || current instanceof ItemBow || current instanceof ItemShield)
					{
						if (l.isPressed())
						{
							player.openGui(Levels.instance, GuiHandler.ITEM_INFORMATION, player.getEntityWorld(), (int) player.posX, (int) player.posY, (int) player.posZ);
						}
					}
				}
			}
		}
	}
}

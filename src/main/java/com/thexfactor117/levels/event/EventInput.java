package com.thexfactor117.levels.event;

import com.thexfactor117.levels.Levels;
import com.thexfactor117.levels.proxies.ClientProxy;
import com.thexfactor117.levels.util.GuiHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

/**
 * 
 * @author TheXFactor117
 *
 */
public class EventInput 
{
	@SubscribeEvent
	public void onKeyPress(InputEvent.KeyInputEvent event)
	{
		KeyBinding key = ClientProxy.keyBinding;
		Minecraft mc = Minecraft.getMinecraft();
		EntityPlayer player = mc.thePlayer;
		
		if (key.isPressed())
		{
			player.openGui(Levels.instance, GuiHandler.ABILITY_SELECTION, player.worldObj, (int) player.posX, (int) player.posY, (int) player.posZ);
		}
	}
}

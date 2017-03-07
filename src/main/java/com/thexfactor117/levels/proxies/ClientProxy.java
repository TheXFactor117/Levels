package com.thexfactor117.levels.proxies;

import org.lwjgl.input.Keyboard;

import com.thexfactor117.levels.config.Config;
import com.thexfactor117.levels.event.EventRenderOverlay;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;

/**
 * 
 * @author TheXFactor117
 *
 */
public class ClientProxy extends CommonProxy
{
	public static KeyBinding keyBinding;
	
	@Override
	public void preInit()
	{
		if (Config.enemyLeveling)
		{
			MinecraftForge.EVENT_BUS.register(new EventRenderOverlay());
		}
	}
	
	@Override
	public void init()
	{
		keyBinding = new KeyBinding("key.gui.weapon_interface", Keyboard.KEY_L, "key.levels");
		
		ClientRegistry.registerKeyBinding(keyBinding);
	}
}

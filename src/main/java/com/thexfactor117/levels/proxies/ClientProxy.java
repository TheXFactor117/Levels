package com.thexfactor117.levels.proxies;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.settings.KeyBinding;
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
	public void init()
	{
		keyBinding = new KeyBinding("key.gui.weapon_interface", Keyboard.KEY_L, "key.levels");
		
		ClientRegistry.registerKeyBinding(keyBinding);
	}
}

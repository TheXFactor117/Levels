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
	public static KeyBinding keyBindingL;
	public static KeyBinding keyBindingB;
	
	@Override
	public void preInit()
	{

	}
	
	@Override
	public void init()
	{
		keyBindingL = new KeyBinding("key.gui.weapon_interface", Keyboard.KEY_L, "key.levels");
		keyBindingB = new KeyBinding("key.blacksmithing", Keyboard.KEY_B, "key.levels");
		
		ClientRegistry.registerKeyBinding(keyBindingL);
		ClientRegistry.registerKeyBinding(keyBindingB);
	}
}

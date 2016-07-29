package com.thexfactor117.levels.init;

import com.thexfactor117.levels.commands.CommandSetAbilities;
import com.thexfactor117.levels.commands.CommandSetRarity;
import com.thexfactor117.levels.handlers.ConfigHandler;

import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

/**
 * 
 * @author TheXFactor117
 *
 */
public class ModCommands 
{
	public static void registerCommands(FMLServerStartingEvent event)
	{
		if (ConfigHandler.COMMANDS)
		{
			event.registerServerCommand(new CommandSetAbilities());
			event.registerServerCommand(new CommandSetRarity());
		}
	}
}

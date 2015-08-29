package com.thexfactor117.levels.handlers;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

/**
 * 
 * @author TheXFactor117
 *
 */
public class ConfigHandler 
{
	public static boolean enableLevelingSystem;
	
	public static void registerConfig(File file)
	{
		File mainFile = new File(file + "/Levels.cfg");
		
		Configuration config = new Configuration(mainFile);
		config.load();
		
		enableLevelingSystem = config.get("general", "enableLevelingSystem", true, "Enable or disable the leveling system.").getBoolean();
		
		if (config.hasChanged())
		{
			config.save();
		}
	}
}

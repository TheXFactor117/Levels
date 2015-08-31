package com.thexfactor117.levels.handlers;

import java.io.File;

import com.thexfactor117.levels.helpers.LogHelper;

import net.minecraftforge.common.config.Configuration;

/**
 * 
 * @author TheXFactor117
 *
 */
public class ConfigHandler 
{
	public static boolean enableLevelingSystem;
	public static boolean enableEnchantments;
	
	public static void registerConfig(File file)
	{
		File mainFile = new File(file + "/Levels.cfg");
		
		Configuration config = new Configuration(mainFile);
		LogHelper.info("Loading config file...");
		config.load();
		
		enableLevelingSystem = config.get("general", "enableLevelingSystem", true, "Enable or disable the leveling system.").getBoolean();
		enableEnchantments = config.get("general", "enableEnchantments", true, "Enable or disable enchantments.").getBoolean();
		
		if (config.hasChanged())
		{
			LogHelper.info("Config file saving...");
			config.save();
			LogHelper.info("Config file saved.");
		}
	}
}

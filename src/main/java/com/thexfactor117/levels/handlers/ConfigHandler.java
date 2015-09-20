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
	// general
	public static boolean enableDevFeatures;
	
	public static void registerConfig(File file)
	{
		File mainFile = new File(file + "/Levels.cfg");
		
		Configuration config = new Configuration(mainFile);
		LogHelper.info("Loading config file...");
		config.load();
		
		enableDevFeatures = config.get("general", "enableDevFeatures", false, "Enable dev features. Things could go wrong; only use in test worlds.").getBoolean();
		
		if (config.hasChanged())
		{
			LogHelper.info("Config file saving...");
			config.save();
			LogHelper.info("Config file saved.");
		}
	}
}

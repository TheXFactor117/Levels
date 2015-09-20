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
	
	// leveling system
	public static boolean voidInstantKill;
	public static double voidDamageAmount;
	
	public static void registerConfig(File file)
	{
		File mainFile = new File(file + "/Levels.cfg");
		
		Configuration config = new Configuration(mainFile);
		LogHelper.info("Loading config file...");
		config.load();
		
		// general
		enableDevFeatures = config.get("general", "enableDevFeatures", false, "Enable dev features. Things could go wrong; only use in test worlds.").getBoolean();
		
		// leveling system
		voidInstantKill = config.get("levelingSystem", "voidInstantKill", true, "Disable to where void just adds extra damage (amount configurable from voidDamageAmount).").getBoolean();
		voidDamageAmount = config.get("levelingSystem", "voidDamageAmount", 30.0D, "Amount of extra damage the Void ability inflicts (only effective if the above is disabled).").getDouble();
		
		if (config.hasChanged())
		{
			LogHelper.info("Config file saving...");
			config.save();
			LogHelper.info("Config file saved.");
		}
	}
}

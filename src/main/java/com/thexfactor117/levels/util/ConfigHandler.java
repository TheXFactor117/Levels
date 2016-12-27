package com.thexfactor117.levels.util;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

/**
 * 
 * @author TheXFactor117
 *
 */
public class ConfigHandler 
{
	private static Configuration config;
	
	// features
	
	// abilities
	// rarities
	
	// experience
	public static int MAX_LEVEL;
	
	// miscellaneous
	public static boolean SHOW_DURABILITY;
	
	public static void init(File file)
	{
		config = new Configuration(file);
		syncConfig();
	}
	
	private static void syncConfig()
	{
		String category;
		
		// experience
		category = "Experience";
		config.addCustomCategoryComment(category, "Experience settings");
		MAX_LEVEL = config.getInt("maxLevel", category, 6, 1, 100, "configures the maximum level cap.");
		
		// miscellaneous
		category = "Miscellaneous";
		config.addCustomCategoryComment(category, "Miscellaneous settings");
		SHOW_DURABILITY = config.getBoolean("showDurability", category, true, "determines whether or not to show durability in tooltips.");
		
		config.save();
	}
}

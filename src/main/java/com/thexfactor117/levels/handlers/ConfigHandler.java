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
	public static Configuration config;
	
	// features
	public static boolean LEVELING_SYSTEM;
	public static boolean DURABILITY;
	public static boolean COMMANDS;
	public static boolean ENEMY_LEVELING;
	
	// enemy leveling
	public static String STRING_POSITION;
	public static boolean ENEMY_ABILITIES;

	// experience
	public static int MAX_LEVEL_CAP;
	
	// development
	public static boolean DEV_FEATURES;
	
	public static void init(File file)
	{
		config = new Configuration(file);
		syncconfig();
	}
	
	public static void syncconfig()
	{
		String category;
		
		// features
		category = "Features";
		config.addCustomCategoryComment(category, "Leveling settings");
		LEVELING_SYSTEM = config.getBoolean("levelingSystem", category, true, "Enable/disable the entire leveling systems.");
		DURABILITY = config.getBoolean("durability", category, true, "Enable/disable durability. By default all weapons, armors, and bows WILL lose durability.");
		COMMANDS = config.getBoolean("commands", category, false, "Enable/disable commands used to force rarities, abilities, levels, and experience.");
		ENEMY_LEVELING = config.getBoolean("enemyLeveling", category, true, "Enable/disable the enemy leveling system. This will give enemies rarities as well.");
		
		// enemy leveling
		category = "Enemy Leveling";
		config.addCustomCategoryComment(category, "Enemy leveling settings");
		STRING_POSITION = config.getString("stringPosition", category, "default", "Sets the enemy rarity positioning on the screen. Use default, topright, topleft, bottomleft, bottomright, or cursor.");
		ENEMY_ABILITIES = config.getBoolean("enemyAbilities", category, true, "Enable/disable higher rarity enemies to have abilities, such as fire, stun, or poison.");
		
		// experience
		category = "Experience";
		config.addCustomCategoryComment(category, "Experience settings");
		MAX_LEVEL_CAP = config.getInt("maxLevelCap", category, 10, 1, 100, "configures the maximum level cap.");
		
		// development
		category = "Development";
		config.addCustomCategoryComment(category, "Development settings. USE AT YOUR OWN RISK!");
		DEV_FEATURES = config.getBoolean("devFeatures", category, false, "Enables/disables development features. USE AT YOUR OWN RISK. Enabling this could break things - only use when TESTING new features.");
		
		config.save();
	}
}

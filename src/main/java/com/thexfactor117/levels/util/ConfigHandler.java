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
	public static boolean ENEMY_LEVELING;
	
	// abilities
	public static int MAX_ABILITIES;
	
	// rarities
	
	// experience
	public static int MAX_LEVEL;
	
	// miscellaneous
	public static boolean SHOW_DURABILITY;
	public static String[] ITEM_BLACKLIST;
	public static String STRING_POSITION;
	
	public static void init(File file)
	{
		config = new Configuration(file);
		syncConfig();
	}
	
	private static void syncConfig()
	{
		String category;
		
		// features
		category = "Features";
		config.addCustomCategoryComment(category, "Control overall features of the mod");
		ENEMY_LEVELING = config.getBoolean("enemyLeveling", category, true, "enable/disable enemies from getting leveling systems.");
		
		// abilities
		category = "Abilities";
		config.addCustomCategoryComment(category, "Ability settings");
		MAX_ABILITIES = config.getInt("maxAbilities", category, 3, 1, 5, "configures the maximum number of abilities an item can have.");
		
		// experience
		category = "Experience";
		config.addCustomCategoryComment(category, "Experience settings");
		MAX_LEVEL = config.getInt("maxLevel", category, 7, 1, 100, "configures the maximum level cap.");
		
		// miscellaneous
		category = "Miscellaneous";
		config.addCustomCategoryComment(category, "Miscellaneous settings");
		SHOW_DURABILITY = config.getBoolean("showDurability", category, true, "determines whether or not to show durability in tooltips.");
		ITEM_BLACKLIST = config.getStringList("itemBlacklist", category, new String[] { "examplemodid:exampleitem" }, "items in this list will not have any leveling systems");
		STRING_POSITION = config.getString("stringPosition", category, "default", "Sets the enemy rarity positioning on the screen. Use default, topright, topleft, bottomleft, bottomright, or cursor.");
		
		config.save();
	}
}

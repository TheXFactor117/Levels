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
	public static Configuration CONFIG;
	
	// features
	public static boolean LEVELING_SYSTEM;
	public static boolean DURABILITY;
	public static boolean MOB_DROPS;

	// experience
	public static int MAX_LEVEL_CAP;
	public static int MONSTER_BONUS_XP;
	public static int ANIMAL_BONUS_XP;
	
	// development
	public static boolean DEV_FEATURES;
	
	public static void init(File file)
	{
		CONFIG = new Configuration(file);
		syncConfig();
	}
	
	public static void syncConfig()
	{
		String category;
		
		// features
		category = "Features";
		CONFIG.addCustomCategoryComment(category, "Leveling settings");
		LEVELING_SYSTEM = CONFIG.getBoolean("levelingSystem", category, true, "Enable/disable the entire leveling systems. Default value: true.");
		DURABILITY = CONFIG.getBoolean("durability", category, false, "Enable/disable durability. By default all weapons, armors, and bows do NOT lose durability. Default value: false.");
		MOB_DROPS = CONFIG.getBoolean("mobDrops", category, true, "Enable/disable customized mob droppings. This will add vanilla weapons to mob drops. Default value: true.");
		
		// experience
		category = "Experience";
		CONFIG.addCustomCategoryComment(category, "Experience settings");
		MAX_LEVEL_CAP = CONFIG.getInt("maxLevelCap", category, 6, 1, 100, "Configures the maximum level cap. Can be anywhere between 1 and 100. Default value: 6.");
		MONSTER_BONUS_XP = CONFIG.getInt("monsterBonusXP", category, 10, 0, 100, "Configures the bonus experience awarded when killing a monster. Can be anywhere between 0 and 100 (for balance purposes). Default value: 10");
		ANIMAL_BONUS_XP = CONFIG.getInt("animalBonusXP", category, 2, 0, 20, "Configures the bonus experience awarded when killing an animal. Can be anywhere between 0 and 20 (for balance purposes). Default value: 2");
		
		// development
		category = "Development";
		CONFIG.addCustomCategoryComment(category, "Development settings. USE AT YOUR OWN RISK!");
		DEV_FEATURES = CONFIG.getBoolean("devFeatures", category, false, "Enables/disables development features. USE AT YOUR OWN RISK. Enabling this could break things - only use when TESTING new features. Default value: false.");
		
		CONFIG.save();
	}
}

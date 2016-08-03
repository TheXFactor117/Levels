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
	public static boolean MOB_DROPS;
	public static boolean COMMANDS;
	public static boolean ENEMY_LEVELING;
	
	// enemy leveling
	public static String STRING_POSITION;
	public static boolean ENEMY_ABILITIES;

	// experience
	public static int MAX_LEVEL_CAP;
	public static int MONSTER_BONUS_XP;
	public static int ANIMAL_BONUS_XP;
	
	// rarities
	public static double BASIC_WEIGHT;
	public static double UNCOMMON_WEIGHT;
	public static double RARE_WEIGHT;
	public static double LEGENDARY_WEIGHT;
	public static double ANCIENT_WEIGHT;
	
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
		DURABILITY = config.getBoolean("durability", category, false, "Enable/disable durability. By default all weapons, armors, and bows do NOT lose durability.");
		MOB_DROPS = config.getBoolean("mobDrops", category, true, "Enable/disable customized mob droppings. This will add vanilla weapons to mob drops.");
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
		MONSTER_BONUS_XP = config.getInt("monsterBonusXP", category, 10, 0, 100, "configures the bonus experience awarded when killing a monster.");
		ANIMAL_BONUS_XP = config.getInt("animalBonusXP", category, 2, 0, 20, "configures the bonus experience awarded when killing an animal.");
		
		// rarities
		category = "Rarities";
		config.addCustomCategoryComment(category, "Rarity settings. ALL OF THESE VALUES MUST ADD UP TO 1!");
		BASIC_WEIGHT = config.getFloat("basicWeight", category, 0.5F, 0F, 1F, "The weight of the Basic rarity. In other words, percentage. (e.g. 0.5F = 50%)");
		UNCOMMON_WEIGHT = config.getFloat("uncommonWeight", category, 0.25F, 0F, 1F, "The weight of the Uncommon rarity. In other words, percentage. (e.g. 0.5F = 50%)");
		RARE_WEIGHT = config.getFloat("rareWeight", category, 0.15F, 0F, 1F, "The weight of the Rare rarity. In other words, percentage. (e.g. 0.5F = 50%)");
		LEGENDARY_WEIGHT = config.getFloat("legendaryWeight", category, 0.07F, 0F, 1F, "The weight of the Legendary rarity. In other words, percentage. (e.g. 0.5F = 50%)");
		ANCIENT_WEIGHT = config.getFloat("ancientWeight", category, 0.03F, 0F, 1F, "The weight of the Ancient rarity. In other words, percentage. (e.g. 0.5F = 50%)");
		
		// development
		category = "Development";
		config.addCustomCategoryComment(category, "Development settings. USE AT YOUR OWN RISK!");
		DEV_FEATURES = config.getBoolean("devFeatures", category, false, "Enables/disables development features. USE AT YOUR OWN RISK. Enabling this could break things - only use when TESTING new features.");
		
		config.save();
	}
}

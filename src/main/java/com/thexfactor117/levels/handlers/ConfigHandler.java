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
	
	// abilities
	public static int BURN_TIME;
	public static int STUN_TIME;
	public static int POISON_TIME;
	public static int STRENGTH_PERCENTAGE;
	public static int STRENGTH_TIME;
	public static int ELEMENTAL_PERCENTAGE;
	public static int DARKNESS_PERCENTAGE;
	public static int DARKNESS_TIME;
	public static int LIGHT_TIME;
	public static int LIGHT_PERCENTAGE_BONUS;
	public static int LIGHT_TIME_BONUS;
	public static int BLOODLUST_PERCENTAGE;
	public static int BLOODLUST_BONUS;
	public static int STING_PERCENTAGE;
	public static int STING_AMOUNT;
	public static int ETHEREAL_PERCENTAGE;
	public static int CHAINED_PERCENTAGE;
	public static int CHAINED_RADIUS;
	public static int VOID_PERCENTAGE;
	public static int BASIC_ARMOR_PERCENTAGE;
	public static float BEASTIAL_MIN_HEALTH;
	public static int BEASTIAL_TIME;
	public static int ENLIGHTENED_PERCENTAGE;
	public static int ABSORB_PERCENTAGE;
	public static int ABSORB_TIME;
	public static int HARDENED_PERCENTAGE;
	public static int INVISIBILITY_PERCENTAGE;
	public static int INVISIBILITY_TIME;
	
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
		
		// abilities
		category = "Abilities";
		config.addCustomCategoryComment(category, "Ability settings");
		BURN_TIME = config.getInt("burnTime", category, 4, 0, 60, "The amount of seconds enemies will burn from abilities. Affects any abilities in which cause the enemy to catch on fire.");
		STUN_TIME = config.getInt("stunTime", category, 4, 0, 60, "The amount of seconds enemies will be frozen from abilities. Affects Frost and Frozen abilities.");
		POISON_TIME = config.getInt("poisonTime", category, 7, 0, 60, "The amount of seconds enemies will be poisoned from abilities. Affects Poison and Toxic abilities.");
		STRENGTH_PERCENTAGE = config.getInt("strengthPercentageChance", category, 10, 1, 100, "The percentage chance at using the Strength ability. Divide 1 by the number here to retrieve the actual percentage. (e.g. 1/10 = 10%)");
		STRENGTH_TIME = config.getInt("strengthTime", category, 5, 0, 60, "The amount of seconds you will be able to have the Strength buff.");
		ELEMENTAL_PERCENTAGE = config.getInt("elementalPercentageChance", category, 1, 1, 100, "The percentage chance at using the Elemental ability. Divide 1 by the number here to retrieve the actual percentage. (e.g. 1/10 = 10%)");
		DARKNESS_PERCENTAGE = config.getInt("darknessPercentageChance", category, 6, 1, 100, "The percentage chance at using the Darkness ability. Divide 1 by the number here to retrieve the actual percentage. (e.g. 1/10 = 10%)");
		DARKNESS_TIME = config.getInt("darknessTime", category, 5, 0, 60, "The amount of seconds the enemy will be inflicted by blindness. Affects Darkness and Light abilities.");		
		LIGHT_TIME = config.getInt("lightTime", category, 5, 0, 60, "The amount of seconds the enemy will be inflicted with weakness. Affects the Light ability.");
		LIGHT_PERCENTAGE_BONUS = config.getInt("lightBonusPercentageChance", category, 6, 1, 100, "The percentage chance at using the Darkness (in this instance, Light's bonus ability is blindness [darkness]) ability. Divide 1 by the number here to retrieve the actual percentage. (e.g. 1/10 = 10%)");
		LIGHT_TIME_BONUS = config.getInt("lightTimeBonus", category, 3, 0, 60, "The amount of seconds the enemy will be inflicted with blindness. Affects the Light ability.");
		BLOODLUST_PERCENTAGE = config.getInt("bloodlustPercentageChance", category, 10, 1, 100, "The percentage chance at using the Bloodlust ability. Divide 1 by the number here to retrieve the actual percentage. (e.g. 1/10 = 10%)");
		BLOODLUST_BONUS = config.getInt("bloodlustBonus", category, 10, 1, 20, "Picks a random number from this amount and subtracts from enemy health.");
		STING_PERCENTAGE = config.getInt("stingPercentageChance", category, 10, 1, 100, "The percentage chance at using the Sting ability. Divide 1 by the number here to retrieve the actual percentage. (e.g. 1/10 = 10%)");
		STING_AMOUNT = config.getInt("stingAmount", category, 10, 1, 50, "The amount of damage to be subtracted from enemy health.");
		ETHEREAL_PERCENTAGE = config.getInt("etherealPercentageChance", category, 10, 1, 100, "The percentage chance at using the Ethereal ability. Divide 1 by the number here to retrieve the actual percentage. (e.g. 1/10 = 10%)");
		CHAINED_PERCENTAGE = config.getInt("chainedPercentageChance", category, 10, 1, 100, "The percentage chance at using the Chained ability. Divide 1 by the number here to retrieve the actual percentage. (e.g. 1/10 = 10%)");
		CHAINED_RADIUS = config.getInt("chainedRadius", category, 10, 1, 50, "The radius (in blocks) around the player in which enemies will be affected by the Chained ability.");
		VOID_PERCENTAGE = config.getInt("voidPercentageChance", category, 50, 1, 100, "The percentage chance at using the Void ability. Divide 1 by the number here to retrieve the actual percentage. (e.g. 1/10 = 10%)");
		BASIC_ARMOR_PERCENTAGE = config.getInt("basicPercentageChance", category, 5, 1, 100, "The percentage chance at using the Molten, Frozen, or Toxic abilities on Armors. Divide 1 by the number here to retrieve the actual percentage. (e.g. 1/10 = 10%)");
		BEASTIAL_MIN_HEALTH = config.getFloat("beastialMinimumHealthRequired", category, 4F, 1F, 20F, "Minimum amount of health needed to enabled the Beastial ability.");
		BEASTIAL_TIME = config.getInt("beastialTime", category, 1, 0, 60, "The amount of seconds of Strength will be applied once Beastial has been activated (really just changes how many seconds after Beastial has gone away the buff will remain).");
		ENLIGHTENED_PERCENTAGE = config.getInt("enlightenedPercentageChance", category, 7, 1, 100, "The percentage chance at using the Enlightened ability. Divide 1 by the number here to retrieve the actual percentage. (e.g. 1/10 = 10%)");
		ABSORB_PERCENTAGE = config.getInt("absorbPercentageChance", category, 7, 1, 100, "The percentage chance at using the Absorb ability. Divide 1 by the number here to retrieve the actual percentage. (e.g. 1/10 = 10%)");
		ABSORB_TIME = config.getInt("absorbTime", category, 10, 0, 60, "The amount of seconds Absorb will be applied to the player.");
		HARDENED_PERCENTAGE = config.getInt("hardenedPercentageChance", category, 10, 1, 100, "The percentage chance at using the Hardned ability. Divide 1 by the number here to retrieve the actual percentage. (e.g. 1/10 = 10%)");
		INVISIBILITY_PERCENTAGE = config.getInt("invisibilityPercentageChance", category, 10, 1, 100, "The percentage chance at using the Invisbility ability. Divide 1 by the number here to retrieve the actual percentage. (e.g. 1/10 = 10%)");
		INVISIBILITY_TIME = config.getInt("invisibilityTime", category, 5, 0, 60, "The amount of seconds Invisibility will be applied to the player.");
		
		// development
		category = "Development";
		config.addCustomCategoryComment(category, "Development settings. USE AT YOUR OWN RISK!");
		DEV_FEATURES = config.getBoolean("devFeatures", category, false, "Enables/disables development features. USE AT YOUR OWN RISK. Enabling this could break things - only use when TESTING new features.");
		
		config.save();
	}
}

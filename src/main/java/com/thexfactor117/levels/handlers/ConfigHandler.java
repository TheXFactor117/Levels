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
		CONFIG = new Configuration(file);
		syncConfig();
	}
	
	public static void syncConfig()
	{
		String category;
		
		// features
		category = "Features";
		CONFIG.addCustomCategoryComment(category, "Leveling settings");
		LEVELING_SYSTEM = CONFIG.getBoolean("levelingSystem", category, true, "Enable/disable the entire leveling systems.");
		DURABILITY = CONFIG.getBoolean("durability", category, false, "Enable/disable durability. By default all weapons, armors, and bows do NOT lose durability.");
		MOB_DROPS = CONFIG.getBoolean("mobDrops", category, true, "Enable/disable customized mob droppings. This will add vanilla weapons to mob drops.");
		
		// experience
		category = "Experience";
		CONFIG.addCustomCategoryComment(category, "Experience settings");
		MAX_LEVEL_CAP = CONFIG.getInt("maxLevelCap", category, 6, 1, 100, "Configures the maximum level cap.");
		MONSTER_BONUS_XP = CONFIG.getInt("monsterBonusXP", category, 10, 0, 100, "Configures the bonus experience awarded when killing a monster.");
		ANIMAL_BONUS_XP = CONFIG.getInt("animalBonusXP", category, 2, 0, 20, "Configures the bonus experience awarded when killing an animal.");
		
		// abilities
		category = "Abilities";
		CONFIG.addCustomCategoryComment(category, "Ability settings");
		BURN_TIME = CONFIG.getInt("burnTime", category, 4, 0, 60, "The amount of seconds enemies will burn from abilities. Affects any abilities in which cause the enemy to catch on fire.");
		STUN_TIME = CONFIG.getInt("stunTime", category, 4, 0, 60, "The amount of seconds enemies will be frozen from abilities. Affects Frost and Frozen abilities.");
		POISON_TIME = CONFIG.getInt("poisonTime", category, 7, 0, 60, "The amount of seconds enemies will be poisoned from abilities. Affects Poison and Toxic abilities.");
		STRENGTH_PERCENTAGE = CONFIG.getInt("strengthPercentageChance", category, 10, 1, 100, "The percentage chance at using the Strength ability. Divide 1 by the number here to retrieve the actual percentage. (e.g. 1/10 = 10%)");
		STRENGTH_TIME = CONFIG.getInt("strengthTime", category, 5, 0, 60, "The amount of seconds you will be able to have the Strength buff.");
		ELEMENTAL_PERCENTAGE = CONFIG.getInt("elementalPercentageChance", category, 1, 1, 100, "The percentage chance at using the Elemental ability. Divide 1 by the number here to retrieve the actual percentage. (e.g. 1/10 = 10%)");
		DARKNESS_PERCENTAGE = CONFIG.getInt("darknessPercentageChance", category, 6, 1, 100, "The percentage chance at using the Darkness ability. Divide 1 by the number here to retrieve the actual percentage. (e.g. 1/10 = 10%)");
		DARKNESS_TIME = CONFIG.getInt("darknessTime", category, 5, 0, 60, "The amount of seconds the enemy will be inflicted by blindness. Affects Darkness and Light abilities.");		
		LIGHT_TIME = CONFIG.getInt("lightTime", category, 5, 0, 60, "The amount of seconds the enemy will be inflicted with weakness. Affects the Light ability.");
		LIGHT_PERCENTAGE_BONUS = CONFIG.getInt("lightBonusPercentageChance", category, 6, 1, 100, "The percentage chance at using the Darkness (in this instance, Light's bonus ability is blindness [darkness]) ability. Divide 1 by the number here to retrieve the actual percentage. (e.g. 1/10 = 10%)");
		LIGHT_TIME_BONUS = CONFIG.getInt("lightTimeBonus", category, 3, 0, 60, "The amount of seconds the enemy will be inflicted with blindness. Affects the Light ability.");
		BLOODLUST_PERCENTAGE = CONFIG.getInt("bloodlustPercentageChance", category, 10, 1, 100, "The percentage chance at using the Bloodlust ability. Divide 1 by the number here to retrieve the actual percentage. (e.g. 1/10 = 10%)");
		BLOODLUST_BONUS = CONFIG.getInt("bloodlustBonus", category, 10, 1, 20, "Picks a random number from this amount and subtracts from enemy health.");
		STING_PERCENTAGE = CONFIG.getInt("stingPercentageChance", category, 10, 1, 100, "The percentage chance at using the Sting ability. Divide 1 by the number here to retrieve the actual percentage. (e.g. 1/10 = 10%)");
		STING_AMOUNT = CONFIG.getInt("stingAmount", category, 10, 1, 50, "The amount of damage to be subtracted from enemy health.");
		ETHEREAL_PERCENTAGE = CONFIG.getInt("etherealPercentageChance", category, 10, 1, 100, "The percentage chance at using the Ethereal ability. Divide 1 by the number here to retrieve the actual percentage. (e.g. 1/10 = 10%)");
		CHAINED_PERCENTAGE = CONFIG.getInt("chainedPercentageChance", category, 10, 1, 100, "The percentage chance at using the Chained ability. Divide 1 by the number here to retrieve the actual percentage. (e.g. 1/10 = 10%)");
		CHAINED_RADIUS = CONFIG.getInt("chainedRadius", category, 10, 1, 50, "The radius (in blocks) around the player in which enemies will be affected by the Chained ability.");
		VOID_PERCENTAGE = CONFIG.getInt("voidPercentageChance", category, 20, 1, 100, "The percentage chance at using the Void ability. Divide 1 by the number here to retrieve the actual percentage. (e.g. 1/10 = 10%)");
		BASIC_ARMOR_PERCENTAGE = CONFIG.getInt("basicPercentageChance", category, 5, 1, 100, "The percentage chance at using the Molten, Frozen, or Toxic abilities on Armors. Divide 1 by the number here to retrieve the actual percentage. (e.g. 1/10 = 10%)");
		BEASTIAL_MIN_HEALTH = CONFIG.getFloat("beastialMinimumHealthRequired", category, 4F, 1F, 20F, "Minimum amount of health needed to enabled the Beastial ability.");
		BEASTIAL_TIME = CONFIG.getInt("beastialTime", category, 1, 0, 60, "The amount of seconds of Strength will be applied once Beastial has been activated (really just changes how many seconds after Beastial has gone away the buff will remain).");
		ENLIGHTENED_PERCENTAGE = CONFIG.getInt("enlightenedPercentageChance", category, 7, 1, 100, "The percentage chance at using the Enlightened ability. Divide 1 by the number here to retrieve the actual percentage. (e.g. 1/10 = 10%)");
		ABSORB_PERCENTAGE = CONFIG.getInt("absorbPercentageChance", category, 7, 1, 100, "The percentage chance at using the Absorb ability. Divide 1 by the number here to retrieve the actual percentage. (e.g. 1/10 = 10%)");
		ABSORB_TIME = CONFIG.getInt("absorbTime", category, 10, 0, 60, "The amount of seconds Absorb will be applied to the player.");
		HARDENED_PERCENTAGE = CONFIG.getInt("hardenedPercentageChance", category, 10, 1, 100, "The percentage chance at using the Hardned ability. Divide 1 by the number here to retrieve the actual percentage. (e.g. 1/10 = 10%)");
		INVISIBILITY_PERCENTAGE = CONFIG.getInt("invisibilityPercentageChance", category, 10, 1, 100, "The percentage chance at using the Invisbility ability. Divide 1 by the number here to retrieve the actual percentage. (e.g. 1/10 = 10%)");
		INVISIBILITY_TIME = CONFIG.getInt("invisibilityTime", category, 5, 0, 60, "The amount of seconds Invisibility will be applied to the player.");
		
		// development
		category = "Development";
		CONFIG.addCustomCategoryComment(category, "Development settings. USE AT YOUR OWN RISK!");
		DEV_FEATURES = CONFIG.getBoolean("devFeatures", category, false, "Enables/disables development features. USE AT YOUR OWN RISK. Enabling this could break things - only use when TESTING new features.");
		
		CONFIG.save();
	}
}

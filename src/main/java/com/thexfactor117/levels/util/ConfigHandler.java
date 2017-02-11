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
	public static float COMMON_CHANCE;
	public static float UNCOMMON_CHANCE;
	public static float RARE_CHANCE;
	public static float ULTRA_RARE_CHANCE;
	public static float LEGENDARY_CHANCE;
	public static float ARCHAIC_CHANCE;
	
	public static float COMMON_DAMAGE;
	public static float UNCOMMON_DAMAGE;
	public static float RARE_DAMAGE;
	public static float ULTRA_RARE_DAMAGE;
	public static float LEGENDARY_DAMAGE;
	public static float ARCHAIC_DAMAGE;
	
	// experience
	public static int MAX_LEVEL;
	public static int LEVEL_1_EXP;
	public static float EXP_EXPONENT;
	public static int EXP_MULTIPLIER;
	
	// miscellaneous
	public static boolean SHOW_DURABILITY;
	public static String[] ITEM_BLACKLIST;
	public static String STRING_POSITION;
	public static boolean LOGIN_MESSAGE;
	
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

		// rarities
		COMMON_CHANCE = config.getFloat("commonChance", category, 0.5F, 0F, 1F, "change the percentage chance of obtaining the specified rarity.");
		UNCOMMON_CHANCE = config.getFloat("uncommonChance", category, 0.25F, 0F, 1F, "change the percentage chance of obtaining the specified rarity.");
		RARE_CHANCE = config.getFloat("rareChance", category, 0.13F, 0F, 1F, "change the percentage chance of obtaining the specified rarity.");
		ULTRA_RARE_CHANCE = config.getFloat("ultraRareChance", category, 0.07F, 0F, 1F, "change the percentage chance of obtaining the specified rarity.");
		LEGENDARY_CHANCE = config.getFloat("legendaryChance", category, 0.035F, 0F, 1F, "change the percentage chance of obtaining the specified rarity.");
		ARCHAIC_CHANCE = config.getFloat("archaicChance", category, 0.015F, 0F, 1F, "change the percentage chance of obtaining the specified rarity.");
		
		COMMON_DAMAGE = config.getFloat("commonDamage", category, 0F, 0F, 100F, "amount of bonus damage the rarity deals.");
		UNCOMMON_DAMAGE = config.getFloat("uncommonDamage", category, 1F, 0F, 100F, "amount of bonus damage the rarity deals.");
		RARE_DAMAGE = config.getFloat("rareDamage", category, 2F, 0F, 100F, "amount of bonus damage the rarity deals.");
		ULTRA_RARE_DAMAGE = config.getFloat("ultraRareDamage", category, 3F, 0F, 100F, "amount of bonus damage the rarity deals.");
		LEGENDARY_DAMAGE = config.getFloat("legendaryDamage", category, 4F, 0F, 100F, "amount of bonus damage the rarity deals.");
		ARCHAIC_DAMAGE = config.getFloat("archaicDamage", category, 6F, 0F, 100F, "amount of bonus damage the rarity deals.");
		
		// experience
		category = "Experience";
		config.addCustomCategoryComment(category, "Experience settings");
		MAX_LEVEL = config.getInt("maxLevel", category, 7, 1, 100, "configures the maximum level cap.");
		LEVEL_1_EXP = config.getInt("level1Exp", category, 50, 0, 100000, "configures how much experience is needed to level up the FIRST time.");
		EXP_EXPONENT = config.getFloat("expExponent", category, 2.4F, 0, 10, "configures the exponent in the experience algorithm: level^exponent * multiplier");
		EXP_MULTIPLIER = config.getInt("expMultiplier", category, 20, 0, 100, "configures the multiplier in the experience algorithm: level^exponent * multiplier");
		
		// miscellaneous
		category = "Miscellaneous";
		config.addCustomCategoryComment(category, "Miscellaneous settings");
		SHOW_DURABILITY = config.getBoolean("showDurability", category, true, "determines whether or not to show durability in tooltips.");
		ITEM_BLACKLIST = config.getStringList("itemBlacklist", category, new String[] { "examplemodid:exampleitem" }, "items in this list will not have any leveling systems");
		STRING_POSITION = config.getString("stringPosition", category, "default", "Sets the enemy rarity positioning on the screen. Use default, topright, topleft, bottomleft, bottomright, or cursor.");
		LOGIN_MESSAGE = config.getBoolean("loginMessage", category, true, "determines whether or not to show the login message on start. Modpack creators: recommmended to leave this on (and let players manually disable).");
		
		config.save();
	}
}

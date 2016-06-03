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
	public static boolean enableDevFeatures;
	public static boolean enableWeaponLeveling;
	//public static boolean enableEnemyLeveling;
	public static boolean enableDurability;
	public static boolean enableMobDrops;
	public static boolean enableVersionChecker;
	public static int maxLevelCap;
	public static int monsterBonusExp;
	public static int animalBonusExp;
	
	/*public static double weakMaxHealth;
	public static double weakDamage;
	public static double hardenedMaxHealth;
	public static double superiorMaxHealth;
	public static double superiorDamage;
	public static double eliteMaxHealth;
	public static double eliteDamage;
	public static double legendaryMaxHealth;
	public static double legendaryDamage;*/
	
	public static void registerConfig(File file)
	{
		File mainFile = new File(file, "Levels.cfg");
		
		Configuration config = new Configuration(mainFile);
		LogHelper.info("Loading config file...");
		config.load();
		
		enableDevFeatures = config.get("general", "enableDevFeatures", false, "Enable dev features. Things could go wrong; only use in test worlds. Default false").getBoolean();
		enableWeaponLeveling = config.get("general", "enableWeaponLeveling", true, "Enables the weapon leveling system. Default true").getBoolean();
		//enableEnemyLeveling = config.get("general", "enableEnemyLeveling", true, "Enables the enemy leveling system. Default true").getBoolean();
		enableDurability = config.get("general", "enableDurability", false, "Enables durability. Weapons will eventually run out of durability. Default false").getBoolean();
		enableMobDrops = config.get("general", "enableMobDrops", true, "Enables mob drops. Mobs will now randomly drop weapons and armor. Default true").getBoolean();
		enableVersionChecker = config.get("general", "enableVersionChecker", true, "Enables the version checker. It is not recommneded to make this false. Default true").getBoolean();
		maxLevelCap = config.get("leveling", "maxLevelCap", 6, "Determines what the max level is for weapons to reach. Default 6").getInt();
		monsterBonusExp = config.get("leveling", "monsterBonusExp", 10, "Determines the extra amount of experience for killing a monster. Default 10").getInt();
		animalBonusExp = config.get("leveling", "animalBonusExp", 2, "Determines the extra amount of experience for killing an animal. Default 2").getInt();
		
		//enemy leveling
		/*weakMaxHealth = config.get("enemy leveling", "weakendMaxHealth", -10D, "Determines the weakened entity health. The new value works like this: (default max health) - (this value) = new max health. Default -10D").getDouble();
		weakDamage = config.get("enemy leveling", "weakenedDamageBoost", -2D, "Determines the weakened entity damage. The new value works like this: (default damage) - (this value) = new damage. Default -2D").getDouble();
		hardenedMaxHealth = config.get("enemy leveling", "hardenedMaxHealth", 0.2D, "Determines the hardened entity health. The new value works like this: [(default max health) * (this value)] + (default max health) = new max health. Default 0.2D").getDouble();
		superiorMaxHealth = config.get("enemy leveling", "superiorMaxHealth", 0.4D, "Determines the superior entity health. The new value works like this: [(default max health) * (this value)] + (default max health) = new max health. Default 0.4D").getDouble();
		superiorDamage = config.get("enemy leveling", "superiorDamageBoost", 0.4D, "Determines the superior entity damage. The new value works like this: [(default damage) * (this value)] + (default damage) = new damage. Default 0.4D").getDouble();
		eliteMaxHealth = config.get("enemy leveling", "eliteMaxHealth", 0.6D, "Determines the elite entity health. The new value works like this: [(default max health) * (this value)] + (default max health) = new max health. Default 0.6D").getDouble();
		eliteDamage = config.get("enemy leveling", "eliteDamageBoost", 0.6D, "Determines the elite entity damage. The new value works like this: [(default damage) * (this value)] + (default damage) = new damage. Default 0.6D").getDouble();
		legendaryMaxHealth = config.get("enemy leveling", "legendaryMaxHealth", 0.9D, "Determines the legendary entity health. The new value works like this: [(default max health) * (this value)] + (default max health) = new max health. Default 0.9D").getDouble();
		legendaryDamage = config.get("enemy leveling", "legendaryDamageBoost", 0.9D, "Determines the legendary entity damage. The new value works like this: [(default damage) * (this value)] + (default damage) = new damage. Default 0.9D").getDouble();*/
		
		if (config.hasChanged())
		{
			LogHelper.info("Config file saving...");
			config.save();
			LogHelper.info("Config file saved.");
		}
	}
}

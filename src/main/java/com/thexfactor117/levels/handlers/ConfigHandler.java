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
	public static boolean enableEnemyLeveling;
	public static boolean enableDurability;
	public static boolean enableMobDrops;
	public static boolean enableVersionChecker;
	public static int maxLevelCap;
	public static int monsterBonusExp;
	public static int animalBonusExp;
	
	public static void registerConfig(File file)
	{
		File mainFile = new File(file, "Levels.cfg");
		
		Configuration config = new Configuration(mainFile);
		LogHelper.info("Loading config file...");
		config.load();
		
		enableDevFeatures = config.get("general", "enableDevFeatures", false, "Enable dev features. Things could go wrong; only use in test worlds. Default false").getBoolean();
		enableWeaponLeveling = config.get("general", "enableWeaponLeveling", true, "Enables the weapon leveling system. Default true").getBoolean();
		enableEnemyLeveling = config.get("general", "enableEnemyLeveling", true, "Enables the enemy leveling system. Default true").getBoolean();
		enableDurability = config.get("general", "enableDurability", false, "Enables durability. Weapons will eventually run out of durability. Default false").getBoolean();
		enableMobDrops = config.get("general", "enableMobDrops", true, "Enables mob drops. Mobs will now randomly drop weapons and armor. Default true").getBoolean();
		enableVersionChecker = config.get("general", "enableVersionChecker", true, "Enables the version checker. It is not recommneded to make this false. Default true").getBoolean();
		maxLevelCap = config.get("leveling", "maxLevelCap", 20, "Determines what the max level is for weapons to reach. Default 20").getInt();
		monsterBonusExp = config.get("leveling", "monsterBonusExp", 10, "Determines the extra amount of experience for killing a monster. Default 10").getInt();
		animalBonusExp = config.get("leveling", "animalBonusExp", 2, "Determines the extra amount of experience for killing an animal. Default 2").getInt();
		
		if (config.hasChanged())
		{
			LogHelper.info("Config file saving...");
			config.save();
			LogHelper.info("Config file saved.");
		}
	}
}

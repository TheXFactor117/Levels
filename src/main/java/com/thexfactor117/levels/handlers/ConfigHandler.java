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
	// general
	public static boolean enableDevFeatures;
	
	// leveling system
	public static int weaponMaxLevel2Exp;
	public static int weaponMaxLevel3Exp;
	public static int weaponMaxLevel4Exp;
	public static int weaponMaxLevel5Exp;
	public static int weaponMaxLevel6Exp;
	public static int armorMaxLevel2Exp;
	public static int armorMaxLevel3Exp;
	public static int armorMaxLevel4Exp;
	public static int armorMaxLevel5Exp;
	public static int armorMaxLevel6Exp;
	public static int weaponMonsterExpBonus;
	public static int weaponAnimalExpBonus;
	
	// weapon abilities
	public static int fireAbilityDuration;
	public static int frostAbilityDuration;
	public static int poisonAbilityDuration;
	public static int strengthAbilityDuration;
	public static int strengthAbilityProbability;
	public static int etherealAbilityAmountHealed;
	public static boolean voidInstantKill;
	public static double voidDamageAmount;
	
	// armor abilities
	public static int hardenedAbilityDuration;
	public static int poisonedAbilityDuration;
	public static int armorStrengthAbilityDuration;
	public static int armorStrengthAbilityProbability;
	public static int armorEtherealAbilityAmountHealed;
	
	public static void registerConfig(File file)
	{
		File mainFile = new File(file + "/Levels.cfg");
		
		Configuration config = new Configuration(mainFile);
		LogHelper.info("Loading config file...");
		config.load();
		
		// general
		enableDevFeatures = config.get("general", "enableDevFeatures", false, "Enable dev features. Things could go wrong; only use in test worlds.").getBoolean();
		
		// leveling system
		weaponMaxLevel2Exp = config.get("levelingSystem", "weaponMaxLevel2Exp", 1000, "Controls how much experience is needed to level up from 1 to 2.").getInt();
		weaponMaxLevel3Exp = config.get("levelingSystem", "weaponMaxLevel3Exp", 2500, "Controls how much experience is needed to level up from 2 to 3.").getInt();
		weaponMaxLevel4Exp = config.get("levelingSystem", "weaponMaxLevel4Exp", 5000, "Controls how much experience is needed to level up from 3 to 4.").getInt();
		weaponMaxLevel5Exp = config.get("levelingSystem", "weaponMaxLevel5Exp", 10000, "Controls how much experience is needed to level up from 4 to 5.").getInt();
		weaponMaxLevel6Exp = config.get("levelingSystem", "weaponMaxLevel6Exp", 20000, "Controls how much experience is needed to level up from 5 to 6.").getInt();
		armorMaxLevel2Exp = config.get("levelingSystem", "armorMaxLevel2Exp", 100, "Controls how much experience is needed to level up from 1 to 2.").getInt();
		armorMaxLevel3Exp = config.get("levelingSystem", "armorMaxLevel3Exp", 250, "Controls how much experience is needed to level up from 2 to 3.").getInt();
		armorMaxLevel4Exp = config.get("levelingSystem", "armorMaxLevel4Exp", 500, "Controls how much experience is needed to level up from 3 to 4.").getInt();
		armorMaxLevel5Exp = config.get("levelingSystem", "armorMaxLevel5Exp", 1000, "Controls how much experience is needed to level up from 4 to 5.").getInt();
		armorMaxLevel6Exp = config.get("levelingSystem", "armorMaxLevel6Exp", 2000, "Controls how much experience is needed to level up from 5 to 6.").getInt();
		weaponMonsterExpBonus = config.get("levelingSystem", "weaponMonsterExpBonus", 10, "Determines the amount of bonus experience for killing monsters.").getInt();
		weaponAnimalExpBonus = config.get("levelingSystem", "weaponAnimalExpBonus", 2, "Determines the amount of bonus experience for killing animals.").getInt();
		voidInstantKill = config.get("levelingSystem", "voidInstantKill", true, "Disable to where void just adds extra damage (amount configurable from voidDamageAmount).").getBoolean();
		voidDamageAmount = config.get("levelingSystem", "voidDamageAmount", 30.0D, "Amount of extra damage the Void ability inflicts (only effective if the above is disabled).").getDouble();
		
		if (config.hasChanged())
		{
			LogHelper.info("Config file saving...");
			config.save();
			LogHelper.info("Config file saved.");
		}
	}
}

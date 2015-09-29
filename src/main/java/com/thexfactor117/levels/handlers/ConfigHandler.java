package com.thexfactor117.levels.handlers;

import java.io.File;

import com.thexfactor117.levels.helpers.ItemType;
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
	public static int weaponMonsterExpBonus;
	public static int weaponAnimalExpBonus;
	
	// abilities
	public static int fireAbilityDuration;
	public static int frostAbilityDuration;
	public static int poisonAbilityDuration;
	public static int strengthAbilityDuration;
	public static int strengthAbilityProbability;
	public static int etherealAbilityAmountHealed;
	public static boolean voidInstantKill;
	public static double voidDamageAmount;
	public static int hardenedAbilityDuration;
	public static int poisonedAbilityDuration;
	
	public static void registerConfig(File file)
	{
		File mainFile = new File(file, "Levels.cfg");
		
		Configuration config = new Configuration(mainFile);
		LogHelper.info("Loading config file...");
		config.load();
		
		// general
		enableDevFeatures = config.get("general", "enableDevFeatures", false, "Enable dev features. Things could go wrong; only use in test worlds.").getBoolean();
		ItemType.WEAPON.setMaxLevelExp(1, config.get("levelingSystem", "weaponMaxLevel2Exp", 1000, "Controls how much experience is needed to level up from 1 to 2.").getInt());
		ItemType.WEAPON.setMaxLevelExp(2, config.get("levelingSystem", "weaponMaxLevel3Exp", 2500, "Controls how much experience is needed to level up from 2 to 3.").getInt());
		ItemType.WEAPON.setMaxLevelExp(3, config.get("levelingSystem", "weaponMaxLevel4Exp", 5000, "Controls how much experience is needed to level up from 3 to 4.").getInt());
		ItemType.WEAPON.setMaxLevelExp(4, config.get("levelingSystem", "weaponMaxLevel5Exp", 10000, "Controls how much experience is needed to level up from 4 to 5.").getInt());
		ItemType.WEAPON.setMaxLevelExp(5, config.get("levelingSystem", "weaponMaxLevel6Exp", 20000, "Controls how much experience is needed to level up from 5 to 6.").getInt());
		ItemType.ARMOR.setMaxLevelExp(1, config.get("levelingSystem", "armorMaxLevel2Exp", 100, "Controls how much experience is needed to level up from 1 to 2.").getInt());
		ItemType.ARMOR.setMaxLevelExp(2, config.get("levelingSystem", "armorMaxLevel3Exp", 250, "Controls how much experience is needed to level up from 2 to 3.").getInt());
		ItemType.ARMOR.setMaxLevelExp(3, config.get("levelingSystem", "armorMaxLevel4Exp", 500, "Controls how much experience is needed to level up from 3 to 4.").getInt());
		ItemType.ARMOR.setMaxLevelExp(4, config.get("levelingSystem", "armorMaxLevel5Exp", 1000, "Controls how much experience is needed to level up from 4 to 5.").getInt());
		ItemType.ARMOR.setMaxLevelExp(5, config.get("levelingSystem", "armorMaxLevel6Exp", 2000, "Controls how much experience is needed to level up from 5 to 6.").getInt());
		weaponMonsterExpBonus = config.get("levelingSystem", "weaponMonsterExpBonus", 10, "Determines the amount of bonus experience for killing monsters.").getInt();
		weaponAnimalExpBonus = config.get("levelingSystem", "weaponAnimalExpBonus", 2, "Determines the amount of bonus experience for killing animals.").getInt();
		fireAbilityDuration = config.get("abilities", "fireAbilityDuration", 4, "Determines how long enemies will be set on fire (in seconds).").getInt();
		frostAbilityDuration = config.get("abilities", "frostAbilityDuration", 4, "Determines how long enemies will be stunned (in seconds).").getInt();
		poisonAbilityDuration = config.get("abilities", "poisonAbilityDuration", 4, "Determines how long enemies will be poisoned (in seconds).").getInt();
		strengthAbilityDuration = config.get("abilities", "strengthAbilityDuration", 10, "Determines how long the user will have a strength buff (in seconds).").getInt();
		strengthAbilityProbability = config.get("abilities", "strengthAbilityProbability", 10, "Determines the probability of the strength ability occurring. Defaults at 1/10. Changing this to 5 will result in a 20% chance instead of 10%.").getInt();
		etherealAbilityAmountHealed = config.get("abilities", "etherealAbilityAmountHealed", 5, "Determines the amount of health being returned to user. Number is in points; not hearts.").getInt();
		voidInstantKill = config.get("abilities", "voidInstantKill", true, "Disable to where void just adds extra damage (amount configurable from voidDamageAmount).").getBoolean();
		voidDamageAmount = config.get("abilities", "voidDamageAmount", 30.0D, "Amount of extra damage the Void ability inflicts (only effective if the above is disabled).").getDouble();
		hardenedAbilityDuration = config.get("abilities", "hardenedAbilityDuration", 2, "Determines how long enemies will be stunned (in seconds).").getInt();
		poisonedAbilityDuration = config.get("abilities", "poisonedAbilityDuration", 10, "Determines how long enemies will be poisoned (in seconds).").getInt();
		
		if (config.hasChanged())
		{
			LogHelper.info("Config file saving...");
			config.save();
			LogHelper.info("Config file saved.");
		}
	}
}

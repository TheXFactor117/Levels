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
	
	public static int monsterBonusExp;
	public static int animalBonusExp;
	
	public static int l2Exp;
	public static int l3Exp;
	public static int l4Exp;
	public static int l5Exp;
	public static int l6Exp;
	public static int l7Exp;
	public static int l8Exp;
	public static int l9Exp;
	public static int l10Exp;
	public static int l11Exp;
	public static int l12Exp;
	public static int l13Exp;
	public static int l14Exp;
	public static int l15Exp;
	public static int l16Exp;
	public static int l17Exp;
	public static int l18Exp;
	public static int l19Exp;
	public static int l20Exp;
	
	public static void registerConfig(File file)
	{
		File mainFile = new File(file, "Levels.cfg");
		
		Configuration config = new Configuration(mainFile);
		LogHelper.info("Loading config file...");
		config.load();
		
		enableDevFeatures = config.get("general", "enableDevFeatures", false, "Enable dev features. Things could go wrong; only use in test worlds.").getBoolean();
		l2Exp = config.get("leveling", "l2Exp", 50).getInt();
		l3Exp = config.get("leveling", "l3Exp", 125).getInt();
		l4Exp = config.get("leveling", "l4Exp", 225).getInt();
		l5Exp = config.get("leveling", "l5Exp", 375).getInt();
		l6Exp = config.get("leveling", "l6Exp", 600).getInt();
		l7Exp = config.get("leveling", "l7Exp", 925).getInt();
		l8Exp = config.get("leveling", "l8Exp", 1375).getInt();
		l9Exp = config.get("leveling", "l9Exp", 2000).getInt();
		l10Exp = config.get("leveling", "l10Exp", 2850).getInt();
		l11Exp = config.get("leveling", "l11Exp", 4000).getInt();
		l12Exp = config.get("leveling", "l12Exp", 5550).getInt();
		l13Exp = config.get("leveling", "l13Exp", 7625).getInt();
		l14Exp = config.get("leveling", "l14Exp", 10400).getInt();
		l15Exp = config.get("leveling", "l15Exp", 14100).getInt();
		l16Exp = config.get("leveling", "l16Exp", 19025).getInt();
		l17Exp = config.get("leveling", "l17Exp", 25575).getInt();
		l18Exp = config.get("leveling", "l18Exp", 34275).getInt();
		l19Exp = config.get("leveling", "l19Exp", 45825).getInt();
		l20Exp = config.get("leveling", "l20Exp", 61150).getInt();
		monsterBonusExp = config.get("leveling", "monsterBonusExp", 10, "Determines the extra amount of experience for killing a monster.").getInt();
		animalBonusExp = config.get("leveling", "animalBonusExp", 2, "Determines the extra amount of experience for killing an animal.").getInt();
		
		if (config.hasChanged())
		{
			LogHelper.info("Config file saving...");
			config.save();
			LogHelper.info("Config file saved.");
		}
	}
}

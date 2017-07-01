package com.thexfactor117.levels.config;

import java.io.File;
import java.util.List;

import com.google.common.collect.Lists;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

/**
 * 
 * @author TheXFactor117
 *
 */
public class Config 
{
	// configuration files
	private static Configuration main;
	private static Configuration attributes;

	/*
	 * MAIN
	 */
	
	// experience
	public static double maxLevel = 10;
	public static double expExponent = 2.1F;
	public static int expMultiplier = 20;
	
	// miscellaneous
	public static String[] itemBlacklist = new String[] { "modid:item" };
	public static boolean unlimitedDurability = false;
	
	/*
	 * ATTRIBUTES
	 */
	
	// weapon
	public static boolean weaponFire = true;
	public static boolean weaponFrost = true;
	public static boolean weaponPoison = true;
	public static boolean weaponDurable = true;
	public static boolean weaponAbsorb = true;
	public static boolean weaponSoulBound = true;
	public static boolean weaponBloodlust = true;
	public static boolean weaponCritical = true;
	public static boolean weaponChained = true;
	public static boolean weaponUnbreakable = true;
	public static boolean weaponVoid = true;
	
	// armor
	public static boolean armorFire = true;
	public static boolean armorFrost = true;
	public static boolean armorPoison = true;
	public static boolean armorDurable = true;
	public static boolean armorMagical = true;
	public static boolean armorSoulBound = true;
	public static boolean armorUnbreakable = true;
	
	// bow
	public static boolean bowFire = true;
	public static boolean bowFrost = true;
	public static boolean bowPoison = true;
	public static boolean bowDurable = true;
	public static boolean bowAbsorb = true;
	public static boolean bowSoulBound = true;
	public static boolean bowCritical = true;
	public static boolean bowRecover = true;
	public static boolean bowBarrage = true;
	public static boolean bowUnbreakable = true;
	public static boolean bowVoid = true;
	
	// shield
	public static boolean shieldFire = true;
	public static boolean shieldFrost = true;
	public static boolean shieldPoison = true;
	public static boolean shieldDurable = true;
	public static boolean shieldSoulBound = true;
	public static boolean shieldUnbreakable = true;
	
	public static void init(File dir)
	{
		main = new Configuration(new File(dir.getPath(), "levels.cfg"));
		attributes = new Configuration(new File(dir.getPath(), "levels_attributes.cfg"));
		sync();
	}
	
	private static void sync()
	{
		syncMain();
		syncAttributes();
	}
	
	private static void syncMain()
	{
		String category = "main";
		List<String> propOrder = Lists.newArrayList();
		Property prop;
		
		/*
		 * Experience
		 */
		prop = main.get(category, "maxLevel", maxLevel);
		prop.setComment("Determines the max level of weapons and armor.");
		maxLevel = prop.getDouble();
		propOrder.add(prop.getName());
		
		prop = main.get(category, "experienceExponent", expExponent);
		prop.setComment("Sets the exponent of the experience algorithm.");
		expExponent = prop.getDouble();
		propOrder.add(prop.getName());
		
		prop = main.get(category, "experienceMultiplier", expMultiplier);
		prop.setComment("Sets the multiplier of the experience algorithm.");
		expMultiplier = prop.getInt();
		propOrder.add(prop.getName());
		
		/*
		 * Miscellaneous
		 */
		prop = main.get(category, "itemBlacklist", itemBlacklist);
		prop.setComment("Items in this blacklist will not gain the leveling systems. Useful for very powerful items or potential conflicts. Style should be 'modid:item'");
		itemBlacklist = prop.getStringList();
		propOrder.add(prop.getName());
		
		prop = main.get(category, "unlimitedDurability", unlimitedDurability);
		prop.setComment("Determines whether or not weapons and armor will lose durability.");
		unlimitedDurability = prop.getBoolean();
		propOrder.add(prop.getName());
		
		main.setCategoryPropertyOrder(category, propOrder);
		main.save();
	}
	
	private static void syncAttributes()
	{
		String category = "attributes";
		List<String> propOrder = Lists.newArrayList();
		Property prop;
		
		/*
		 * Weapon
		 */
		prop = attributes.get(category, "weaponFire", weaponFire);
		prop.setComment("Enables/disables the specific attribute on weapons (swords, etc...)");
		weaponFire = prop.getBoolean();
		propOrder.add(prop.getName());
		
		prop = attributes.get(category, "weaponFrost", weaponFrost);
		prop.setComment("Enables/disables the specific attribute on weapons (swords, etc...)");
		weaponFrost = prop.getBoolean();
		propOrder.add(prop.getName());
		
		prop = attributes.get(category, "weaponPoison", weaponPoison);
		prop.setComment("Enables/disables the specific attribute on weapons (swords, etc...)");
		weaponPoison = prop.getBoolean();
		propOrder.add(prop.getName());
		
		prop = attributes.get(category, "weaponDurable", weaponDurable);
		prop.setComment("Enables/disables the specific attribute on weapons (swords, etc...)");
		weaponDurable = prop.getBoolean();
		propOrder.add(prop.getName());
		
		prop = attributes.get(category, "weaponAbsorb", weaponAbsorb);
		prop.setComment("Enables/disables the specific attribute on weapons (swords, etc...)");
		weaponAbsorb = prop.getBoolean();
		propOrder.add(prop.getName());
		
		prop = attributes.get(category, "weaponSoulBound", weaponSoulBound);
		prop.setComment("Enables/disables the specific attribute on weapons (swords, etc...)");
		weaponSoulBound = prop.getBoolean();
		propOrder.add(prop.getName());
		
		prop = attributes.get(category, "weaponBloodlust", weaponBloodlust);
		prop.setComment("Enables/disables the specific attribute on weapons (swords, etc...)");
		weaponBloodlust = prop.getBoolean();
		propOrder.add(prop.getName());
		
		prop = attributes.get(category, "weaponCritical", weaponCritical);
		prop.setComment("Enables/disables the specific attribute on weapons (swords, etc...)");
		weaponCritical = prop.getBoolean();
		propOrder.add(prop.getName());
		
		prop = attributes.get(category, "weaponChained", weaponChained);
		prop.setComment("Enables/disables the specific attribute on weapons (swords, etc...)");
		weaponChained = prop.getBoolean();
		propOrder.add(prop.getName());
		
		prop = attributes.get(category, "weaponUnbreakable", weaponUnbreakable);
		prop.setComment("Enables/disables the specific attribute on weapons (swords, etc...)");
		weaponUnbreakable = prop.getBoolean();
		propOrder.add(prop.getName());
		
		prop = attributes.get(category, "weaponVoid", weaponVoid);
		prop.setComment("Enables/disables the specific attribute on weapons (swords, etc...)");
		weaponVoid = prop.getBoolean();
		propOrder.add(prop.getName());
		
		
		/*
		 * Armor
		 */
		prop = attributes.get(category, "armorFire", armorFire);
		prop.setComment("Enables/disables the specific attribute on armors.");
		armorFire = prop.getBoolean();
		propOrder.add(prop.getName());
		
		prop = attributes.get(category, "armorFrost", armorFrost);
		prop.setComment("Enables/disables the specific attribute on armors.");
		armorFrost = prop.getBoolean();
		propOrder.add(prop.getName());
		
		prop = attributes.get(category, "armorPoison", armorPoison);
		prop.setComment("Enables/disables the specific attribute on armors.");
		armorPoison = prop.getBoolean();
		propOrder.add(prop.getName());
		
		prop = attributes.get(category, "armorDurable", armorDurable);
		prop.setComment("Enables/disables the specific attribute on armors.");
		armorFire = prop.getBoolean();
		propOrder.add(prop.getName());
		
		prop = attributes.get(category, "armorSoulBound", armorSoulBound);
		prop.setComment("Enables/disables the specific attribute on armors.");
		armorSoulBound = prop.getBoolean();
		propOrder.add(prop.getName());
		
		prop = attributes.get(category, "armorUnbreakable", armorUnbreakable);
		prop.setComment("Enables/disables the specific attribute on armors.");
		armorUnbreakable = prop.getBoolean();
		propOrder.add(prop.getName());
		
		
		/*
		 * Bow
		 */
		prop = attributes.get(category, "bowFire", bowFire);
		prop.setComment("Enables/disables the specific attribute on bows.");
		bowFire = prop.getBoolean();
		propOrder.add(prop.getName());
		
		prop = attributes.get(category, "bowFrost", bowFrost);
		prop.setComment("Enables/disables the specific attribute on bows.");
		bowFrost = prop.getBoolean();
		propOrder.add(prop.getName());
		
		prop = attributes.get(category, "bowPoison", bowPoison);
		prop.setComment("Enables/disables the specific attribute on bows.");
		bowPoison = prop.getBoolean();
		propOrder.add(prop.getName());
		
		prop = attributes.get(category, "bowDurable", bowDurable);
		prop.setComment("Enables/disables the specific attribute on bows.");
		bowDurable = prop.getBoolean();
		propOrder.add(prop.getName());
		
		prop = attributes.get(category, "bowAbsorb", bowAbsorb);
		prop.setComment("Enables/disables the specific attribute on bows.");
		bowAbsorb = prop.getBoolean();
		propOrder.add(prop.getName());
		
		prop = attributes.get(category, "bowSoulBound", bowSoulBound);
		prop.setComment("Enables/disables the specific attribute on bows.");
		bowSoulBound = prop.getBoolean();
		propOrder.add(prop.getName());
		
		prop = attributes.get(category, "bowCritical", bowCritical);
		prop.setComment("Enables/disables the specific attribute on bows.");
		bowCritical = prop.getBoolean();
		propOrder.add(prop.getName());
		
		prop = attributes.get(category, "bowRecover", bowRecover);
		prop.setComment("Enables/disables the specific attribute on bows.");
		bowRecover = prop.getBoolean();
		propOrder.add(prop.getName());
		
		prop = attributes.get(category, "bowBarrage", bowBarrage);
		prop.setComment("Enables/disables the specific attribute on bows.");
		bowBarrage = prop.getBoolean();
		propOrder.add(prop.getName());
		
		prop = attributes.get(category, "bowUnbreakable", bowUnbreakable);
		prop.setComment("Enables/disables the specific attribute on bows.");
		bowFire = prop.getBoolean();
		propOrder.add(prop.getName());
		
		prop = attributes.get(category, "bowVoid", bowVoid);
		prop.setComment("Enables/disables the specific attribute on bows.");
		bowVoid = prop.getBoolean();
		propOrder.add(prop.getName());
		
		
		/*
		 * Shield
		 */
		prop = attributes.get(category, "shieldFire", shieldFire);
		prop.setComment("Enables/disables the specific attribute on shields.");
		shieldFire = prop.getBoolean();
		propOrder.add(prop.getName());
		
		prop = attributes.get(category, "shieldFrost", shieldFrost);
		prop.setComment("Enables/disables the specific attribute on shields.");
		shieldFrost = prop.getBoolean();
		propOrder.add(prop.getName());
		
		prop = attributes.get(category, "shieldPoison", shieldPoison);
		prop.setComment("Enables/disables the specific attribute on shields.");
		shieldPoison = prop.getBoolean();
		propOrder.add(prop.getName());
		
		prop = attributes.get(category, "shieldDurable", shieldDurable);
		prop.setComment("Enables/disables the specific attribute on shields.");
		shieldDurable = prop.getBoolean();
		propOrder.add(prop.getName());
		
		prop = attributes.get(category, "shieldSoulBound", shieldSoulBound);
		prop.setComment("Enables/disables the specific attribute on shields.");
		shieldSoulBound = prop.getBoolean();
		propOrder.add(prop.getName());
		
		prop = attributes.get(category, "shieldUnbreakable", shieldUnbreakable);
		prop.setComment("Enables/disables the specific attribute on shields.");
		shieldUnbreakable = prop.getBoolean();
		propOrder.add(prop.getName());
	}
}

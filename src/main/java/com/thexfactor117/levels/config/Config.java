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
 * Saves and loads the configuration file. Information is held in specific files and is alphabatized.
 *
 */
public class Config 
{
	// configuration files
	private static Configuration main;
	
	// rarity chances
	public static double commonChance = 0.6;
	public static double uncommonChance = 0.2;
	public static double rareChance = 0.1;
	public static double legendaryChance = 0.07;
	public static double mythicChance = 0.03;

	// experience
	public static double maxLevel = 10;
	public static double expExponent = 2.1F;
	public static int expMultiplier = 20;
	
	// miscellaneous
	public static String[] itemBlacklist = new String[] { "modid:item" };
	public static boolean unlimitedDurability = false;
	
	public static void init(File dir)
	{
		main = new Configuration(new File(dir.getPath(), "augmented_weaponry.cfg"));
		sync();
	}
	
	private static void sync()
	{
		syncMain();
	}
	
	private static void syncMain()
	{
		String category = "main";
		List<String> propOrder = Lists.newArrayList();
		Property prop;
		
		/*
		 *  Rarity Chances
		 */
		prop = main.get(category, "commonChance", commonChance);
		prop.setComment("Determines the chance a weapon/armor will have this rarity.");
		commonChance = prop.getDouble();
		propOrder.add(prop.getName());
		
		prop = main.get(category, "uncommonChance", uncommonChance);
		prop.setComment("Determines the chance a weapon/armor will have this rarity.");
		uncommonChance = prop.getDouble();
		propOrder.add(prop.getName());
		
		prop = main.get(category, "rareChance", rareChance);
		prop.setComment("Determines the chance a weapon/armor will have this rarity.");
		rareChance = prop.getDouble();
		propOrder.add(prop.getName());
		
		prop = main.get(category, "legendaryChance", legendaryChance);
		prop.setComment("Determines the chance a weapon/armor will have this rarity.");
		legendaryChance = prop.getDouble();
		propOrder.add(prop.getName());
		
		prop = main.get(category, "mythicChance", mythicChance);
		prop.setComment("Determines the chance a weapon/armor will have this rarity.");
		mythicChance = prop.getDouble();
		propOrder.add(prop.getName());
		
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
}

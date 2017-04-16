package com.thexfactor117.levels.util;

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
	
	// attribute chances
	public static double fireChance = 0.333;
	public static double frostChance = 0.333;
	public static double durableChance = 0.334;
	public static double absorbChance = 0.5;
	public static double soulBoundChance = 0.5;
	public static double voidChance = 0.5;
	public static double unbreakableChance = 0.5;
	
	// experience
	public static double maxLevel = 10;
	public static double expExponent = 2.4F;
	public static int expMultiplier = 20;
	
	// miscellaneous
	public static String[] itemBlacklist = new String[] { "modid:item" };
	
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
		 * Attribute Chances
		 */
		prop = main.get(category, "fireChance", fireChance);
		prop.setComment("Determines the chance a weapon/armor will have this attribute.");
		fireChance = prop.getDouble();
		propOrder.add(prop.getName());
		
		prop = main.get(category, "frostChance", frostChance);
		prop.setComment("Determines the chance a weapon/armor will have this attribute.");
		frostChance = prop.getDouble();
		propOrder.add(prop.getName());
		
		prop = main.get(category, "durableChance", durableChance);
		prop.setComment("Determines the chance a weapon/armor will have this attribute.");
		durableChance = prop.getDouble();
		propOrder.add(prop.getName());
		
		prop = main.get(category, "absorbChance", absorbChance);
		prop.setComment("Determines the chance a weapon/armor will have this attribute.");
		absorbChance = prop.getDouble();
		propOrder.add(prop.getName());
		
		prop = main.get(category, "soulBoundChance", soulBoundChance);
		prop.setComment("Determines the chance a weapon/armor will have this attribute.");
		soulBoundChance = prop.getDouble();
		propOrder.add(prop.getName());
		
		prop = main.get(category, "voidChance", voidChance);
		prop.setComment("Determines the chance a weapon/armor will have this attribute.");
		voidChance = prop.getDouble();
		propOrder.add(prop.getName());
		
		prop = main.get(category, "unbreakableChance", unbreakableChance);
		prop.setComment("Determines the chance a weapon/armor will have this attribute.");
		unbreakableChance = prop.getDouble();
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
		
		main.setCategoryPropertyOrder(category, propOrder);
		main.save();
	}
}

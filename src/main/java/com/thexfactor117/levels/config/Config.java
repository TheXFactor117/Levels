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
	private static Configuration abilities;
	private static Configuration rarities;
	
	/*
	 * MAIN
	 */
	
	// overall
	public static boolean enemyLeveling = true;
	
	// experience
	public static int maxLevel = 7;
	public static int level1Exp = 50;
	public static double expExponent = 2.4F;
	public static int expMultiplier = 20;
	
	// misc
	public static boolean showDurability = true;
	public static String[] itemBlacklist = new String[] { "modid:item" };
	public static String stringPosition = "default";
	
	/*
	 * ABILITIES
	 */

	public static int maxAbilities = 3;
	
	// abilities
	public static boolean fire = true;
	public static boolean frost = true;
	public static boolean poison = true;
	public static boolean bloodlust = true;
	public static boolean chained = true;
	public static boolean voida = true;
	public static boolean light = true;
	public static boolean ethereal = true;
	public static boolean soulBound = true;
	
	public static boolean molten = true;
	public static boolean frozen = true;
	public static boolean toxic = true;
	public static boolean absorb = true;
	public static boolean voidArmor = true;
	public static boolean beastial = true;
	public static boolean enlightened = true;
	public static boolean hardened = true;
	public static boolean soulBoundArmor = true;
	
	/*
	 * RARITIES
	 */
	
	public static double commonChance = 0.5F;
	public static double uncommonChance = 0.25F;
	public static double rareChance = 0.13F;
	public static double ultraRareChance = 0.07F;
	public static double legendaryChance = 0.035F;
	public static double archaicChance = 0.015F;
	
	public static double commonDamage = 1F;
	public static double uncommonDamage = 1.5F;
	public static double rareDamage = 2F;
	public static double ultraRareDamage = 2.5F;
	public static double legendaryDamage = 3F;
	public static double archaicDamage = 4F;
	
	public static void init(File dir)
	{
		main = new Configuration(new File(dir.getPath(), "levels.cfg"));
		abilities = new Configuration(new File(dir.getPath(), "abilities.cfg"));
		rarities = new Configuration(new File(dir.getPath(), "rarities.cfg"));
		
		sync();
	}
	
	private static void sync()
	{
		syncMain();
		syncAbilities();
		syncRarities();
	}
	
	private static void syncMain()
	{
		String category = "main";
		List<String> propOrder = Lists.newArrayList();
		Property prop;
		
		/*
		 * Overall
		 */
		prop = main.get(category, "enemyLeveling", enemyLeveling);
		prop.setComment("Determines whether or not Enemy Leveling will be enabled. Default: true");
		enemyLeveling = prop.getBoolean();
		propOrder.add(prop.getName());
		
		/*
		 * Experience
		 */
		prop = main.get(category, "maxLevel", maxLevel);
		prop.setComment("Sets the maximum level cap for weapons and armor. Default: 7");
		maxLevel = prop.getInt();
		propOrder.add(prop.getName());
		
		prop = main.get(category, "level1Experience", level1Exp);
		prop.setComment("Sets the amount of experience needed to level up the FIRST time. Default: 50");
		level1Exp = prop.getInt();
		propOrder.add(prop.getName());
		
		prop = main.get(category, "experienceExponent", expExponent);
		prop.setComment("Sets the exponent of the experience algorithm. Default: 2.4");
		expExponent = prop.getDouble();
		propOrder.add(prop.getName());
		
		prop = main.get(category, "experienceMultiplier", expMultiplier);
		prop.setComment("Sets the multiplier of the experience algorithm: Default: 20");
		expMultiplier = prop.getInt();
		propOrder.add(prop.getName());
		
		/*
		 * Miscellaneous
		 */
		prop = main.get(category, "showDurabilityInTooltip", showDurability);
		prop.setComment("Determines whether or not durability will be displayed in tooltips. Default: true");
		showDurability = prop.getBoolean();
		propOrder.add(prop.getName());
		
		prop = main.get(category, "itemBlacklist", itemBlacklist);
		prop.setComment("Items in this blacklist will not gain the leveling systems. Useful for very powerful items or potential conflicts. Style should be 'modid:item'");
		itemBlacklist = prop.getStringList();
		propOrder.add(prop.getName());
		
		prop = main.get(category, "enemyLevelStringPosition", stringPosition);
		prop.setComment("Determines the location of the enemy level display. Can either be 'default', 'topleft', 'topright', 'bottomleft', 'bottomright', or 'cursor'. Default: 'default'");
		stringPosition = prop.getString();
		propOrder.add(prop.getName());
		
		main.setCategoryPropertyOrder(category, propOrder);
		main.save();
	}
	
	private static void syncAbilities()
	{
		String category = "abilities";
		List<String> propOrder = Lists.newArrayList();
		Property prop;

		prop = abilities.get(category, "maxAbilitiesPerItem", maxAbilities);
		prop.setComment("Sets the maximum amount of weapons that can be applied on a given item. Default: 3");
		maxAbilities = prop.getInt();
		propOrder.add(prop.getName());
		
		/*
		 * Abilities
		 */
		// weapons
		prop = abilities.get(category, "fireAbility", fire);
		prop.setComment("Determines whether or not the specific ability will be present in-game. Default: true");
		fire = prop.getBoolean();
		propOrder.add(prop.getName());
		
		prop = abilities.get(category, "frostAbility", frost);
		prop.setComment("Determines whether or not the specific ability will be present in-game. Default: true");
		frost = prop.getBoolean();
		propOrder.add(prop.getName());
		
		prop = abilities.get(category, "poisonAbility", poison);
		prop.setComment("Determines whether or not the specific ability will be present in-game. Default: true");
		poison = prop.getBoolean();
		propOrder.add(prop.getName());
		
		prop = abilities.get(category, "bloodlustAbility", bloodlust);
		prop.setComment("Determines whether or not the specific ability will be present in-game. Default: true");
		bloodlust = prop.getBoolean();
		propOrder.add(prop.getName());
		
		prop = abilities.get(category, "chainedAbility", chained);
		prop.setComment("Determines whether or not the specific ability will be present in-game. Default: true");
		chained = prop.getBoolean();
		propOrder.add(prop.getName());
		
		prop = abilities.get(category, "voidAbility", voida);
		prop.setComment("Determines whether or not the specific ability will be present in-game. Default: true");
		voida = prop.getBoolean();
		propOrder.add(prop.getName());
		
		prop = abilities.get(category, "lightAbility", light);
		prop.setComment("Determines whether or not the specific ability will be present in-game. Default: true");
		light = prop.getBoolean();
		propOrder.add(prop.getName());
		
		prop = abilities.get(category, "etherealAbility", ethereal);
		prop.setComment("Determines whether or not the specific ability will be present in-game. Default: true");
		ethereal = prop.getBoolean();
		propOrder.add(prop.getName());
		
		prop = abilities.get(category, "soulBoundAbility", soulBound);
		prop.setComment("Determines whether or not the specific ability will be present in-game. Default: true");
		soulBound = prop.getBoolean();
		propOrder.add(prop.getName());
		
		// armor
		prop = abilities.get(category, "moltenAbility", molten);
		prop.setComment("Determines whether or not the specific ability will be present in-game. Default: true");
		molten = prop.getBoolean();
		propOrder.add(prop.getName());
		
		prop = abilities.get(category, "frozenAbility", frozen);
		prop.setComment("Determines whether or not the specific ability will be present in-game. Default: true");
		frozen = prop.getBoolean();
		propOrder.add(prop.getName());
		
		prop = abilities.get(category, "toxicAbility", toxic);
		prop.setComment("Determines whether or not the specific ability will be present in-game. Default: true");
		toxic = prop.getBoolean();
		propOrder.add(prop.getName());
		
		prop = abilities.get(category, "absorbAbility", absorb);
		prop.setComment("Determines whether or not the specific ability will be present in-game. Default: true");
		absorb = prop.getBoolean();
		propOrder.add(prop.getName());
		
		prop = abilities.get(category, "voidArmorAbility", voidArmor);
		prop.setComment("Determines whether or not the specific ability will be present in-game. Default: true");
		voidArmor = prop.getBoolean();
		propOrder.add(prop.getName());
		
		prop = abilities.get(category, "beastialAbility", beastial);
		prop.setComment("Determines whether or not the specific ability will be present in-game. Default: true");
		beastial = prop.getBoolean();
		propOrder.add(prop.getName());
		
		prop = abilities.get(category, "enlightenedAbility", enlightened);
		prop.setComment("Determines whether or not the specific ability will be present in-game. Default: true");
		enlightened = prop.getBoolean();
		propOrder.add(prop.getName());
		
		prop = abilities.get(category, "hardenedAbility", hardened);
		prop.setComment("Determines whether or not the specific ability will be present in-game. Default: true");
		hardened = prop.getBoolean();
		propOrder.add(prop.getName());
		
		prop = abilities.get(category, "soulBoundArmorAbility", soulBoundArmor);
		prop.setComment("Determines whether or not the specific ability will be present in-game. Default: true");
		soulBoundArmor = prop.getBoolean();
		propOrder.add(prop.getName());
		
		abilities.setCategoryPropertyOrder(category, propOrder);
		abilities.save();
	}
	
	private static void syncRarities()
	{
		String category = "rarities";
		List<String> propOrder = Lists.newArrayList();
		Property prop;

		/*
		 * Chances 
		 */
		prop = rarities.get(category, "commonChance", commonChance);
		prop.setComment("Sets the chance the given rarity will be applied. Default: 0.5");
		commonChance = prop.getDouble();
		propOrder.add(prop.getName());
		
		prop = rarities.get(category, "uncommonChance", uncommonChance);
		prop.setComment("Sets the chance the given rarity will be applied. Default: 0.25");
		uncommonChance = prop.getDouble();
		propOrder.add(prop.getName());
		
		prop = rarities.get(category, "rareChance", rareChance);
		prop.setComment("Sets the chance the given rarity will be applied. Default: 0.13");
		rareChance = prop.getDouble();
		propOrder.add(prop.getName());
		
		prop = rarities.get(category, "ultraRareChance", ultraRareChance);
		prop.setComment("Sets the chance the given rarity will be applied. Default: 0.07");
		ultraRareChance = prop.getDouble();
		propOrder.add(prop.getName());
		
		prop = rarities.get(category, "legendaryChance", legendaryChance);
		prop.setComment("Sets the chance the given rarity will be applied. Default: 0.035");
		legendaryChance = prop.getDouble();
		propOrder.add(prop.getName());
		
		prop = rarities.get(category, "archaicChance", archaicChance);
		prop.setComment("Sets the chance the given rarity will be applied. Default: 0.015");
		archaicChance = prop.getDouble();
		propOrder.add(prop.getName());
		
		/*
		 * Damage Multipliers
		 */
		prop = rarities.get(category, "commonDamage", commonDamage);
		prop.setComment("Sets the damage multiplier for the given rarity. Default: 1");
		commonDamage = prop.getDouble();
		propOrder.add(prop.getName());
		
		prop = rarities.get(category, "uncommonDamage", uncommonDamage);
		prop.setComment("Sets the damage multiplier for the given rarity. Default: 1.5");
		uncommonDamage = prop.getDouble();
		propOrder.add(prop.getName());
		
		prop = rarities.get(category, "rareDamage", rareDamage);
		prop.setComment("Sets the damage multiplier for the given rarity. Default: 2");
		rareDamage = prop.getDouble();
		propOrder.add(prop.getName());
		
		prop = rarities.get(category, "ultraRareDamage", ultraRareDamage);
		prop.setComment("Sets the damage multiplier for the given rarity. Default: 2.5");
		ultraRareDamage = prop.getDouble();
		propOrder.add(prop.getName());
		
		prop = rarities.get(category, "legendaryDamage", legendaryDamage);
		prop.setComment("Sets the damage multiplier for the given rarity. Default: 3");
		legendaryDamage = prop.getDouble();
		propOrder.add(prop.getName());
		
		prop = rarities.get(category, "archaicDamage", archaicDamage);
		prop.setComment("Sets the damage multiplier for the given rarity. Default: 4");
		archaicDamage = prop.getDouble();
		propOrder.add(prop.getName());
		
		rarities.setCategoryPropertyOrder(category, propOrder);
		rarities.save();
	}
}

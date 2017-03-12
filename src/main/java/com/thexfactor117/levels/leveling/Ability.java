package com.thexfactor117.levels.leveling;

import java.util.ArrayList;

import com.thexfactor117.levels.config.Config;

import net.minecraft.client.resources.I18n;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;

/**
 * 
 * @author TheXFactor117
 *
 */
public enum Ability
{
	// weapon abilities (type, color, color code, tier, multiplier)
	// active
	FIRE("weapon", "active", Config.fire, TextFormatting.RED, 0xFF5555, 1, 1.5),
	FROST("weapon", "active", Config.frost, TextFormatting.AQUA, 0x55FFFF, 1, 1.5),
	POISON("weapon", "active", Config.poison, TextFormatting.DARK_GREEN, 0x00AA00, 1, 1.5),
	BLOODLUST("weapon", "active", Config.bloodlust, TextFormatting.DARK_RED, 0xAA0000, 2, 1.5),
	CHAINED("weapon", "active", Config.chained, TextFormatting.GRAY, 0xAAAAAA, 3, 1.5),
	VOID("weapon", "active", Config.voida, TextFormatting.DARK_GRAY, 0x555555, 3, 1),
	// passive
	LIGHT("weapon", "passive", Config.light, TextFormatting.YELLOW, 0xFFFF55, 2, 1),
	ETHEREAL("weapon", "passive", Config.ethereal, TextFormatting.GREEN, 0x55FF55, 2, 2),
	SOUL_BOUND("weapon", "passive", Config.soulBound, TextFormatting.DARK_PURPLE, 0xAA00AA, 3, 1),
	
	// armor abilities
	// active
	MOLTEN("armor", "active", Config.molten, TextFormatting.RED, 0xFF5555, 1, 1.5),
	FROZEN("armor", "active", Config.frozen, TextFormatting.AQUA, 0x55FFFF, 1, 1.5),
	TOXIC("armor", "active", Config.toxic, TextFormatting.DARK_GREEN, 0x00AA00, 1, 1.5),
	ABSORB("armor", "active", Config.absorb, TextFormatting.GREEN, 0x55FF55, 2, 1.5),
	VOID_ARMOR("armor", "active", Config.voidArmor, TextFormatting.DARK_GRAY, 0x555555, 3, 1),
	// passive
	BEASTIAL("armor", "passive", Config.beastial, TextFormatting.DARK_RED, 0xAA0000, 1, 1.5),
	ENLIGHTENED("armor", "passive", Config.enlightened, TextFormatting.YELLOW, 0xFFFF55, 2, 2),
	HARDENED("armor", "passive", Config.hardened, TextFormatting.GRAY, 0xAAAAAA, 2, 1),
	SOUL_BOUND_ARMOR("armor", "passive", Config.soulBoundArmor, TextFormatting.DARK_PURPLE, 0xAA00AA, 3, 1);
	
	public static int WEAPON_ABILITIES;
	public static int ARMOR_ABILITIES;
	public static final ArrayList<Ability> WEAPONS = new ArrayList<Ability>();
	public static final ArrayList<Ability> ARMOR = new ArrayList<Ability>();
	
	private String category;
	private String type;
	private boolean enabled;
	private String color;
	private int hex;
	private int tier;
	private double multiplier;
	
	Ability(String category, String type, boolean enabled, Object color, int hex, int tier, double multiplier)
	{
		this.category = category;
		this.type = type;
		this.enabled = enabled;
		this.color = color.toString();
		this.hex = hex;
		this.tier = tier;
		this.multiplier = multiplier;
	}
	
	/**
	 * Returns true if the stack has the ability.
	 * @param nbt
	 * @return
	 */
	public boolean hasAbility(NBTTagCompound nbt)
	{
		return nbt != null && nbt.getBoolean(toString());
	}
	
	/**
	 * Adds the specified ability to the stack.
	 * @param nbt
	 */
	public void addAbility(NBTTagCompound nbt, int level)
	{
		nbt.setBoolean(toString(), true);
		setLevel(nbt, level);
	}
	
	/**
	 * Removes the specified ability from the stack.
	 * @param nbt
	 */
	public void removeAbility(NBTTagCompound nbt)
	{
		nbt.removeTag(toString());
		nbt.removeTag(toString() + "_level");
	}
	
	/**
	 * Sets the level of the specified ability.
	 * @param nbt
	 * @param level
	 */
	public void setLevel(NBTTagCompound nbt, int level)
	{
		if (level <= 3)
		{
			nbt.setInteger(toString() + "_level", level);
		}
	}
	
	/**
	 * Returns the level of the specified ability.
	 * @param nbt
	 * @return
	 */
	public int getLevel(NBTTagCompound nbt)
	{
		if (nbt != null) return nbt.getInteger(toString() + "_level");
		else return 0;
	}
	
	public boolean canUpgradeLevel(NBTTagCompound nbt)
	{
		if (getType().equals("active"))
		{
			if (getLevel(nbt) < 3)
				return true;
			else
				return false;
		}
		else
			return false;
	}
	
	public double getMultiplier(int level)
	{
		if (level == 1) return 1;
		else if (level == 2) return multiplier;
		else if (level == 3) return multiplier + (multiplier / 2);
		else return 1;
	}
	
	public int getTier()
	{
		return tier;
	}
	
	public String getColor()
	{
		return color;
	}
	
	public int getHex()
	{
		return hex;
	}
	
	public String getName()
	{
		return I18n.format("levels.ability." + this.ordinal());
	}
	
	public String getName(NBTTagCompound nbt)
	{
		if (getLevel(nbt) == 1)
			return I18n.format("levels.ability." + this.ordinal());
		else if (getLevel(nbt) == 2)
			return I18n.format("levels.ability." + this.ordinal()) + " II";
		else if (getLevel(nbt) == 3)
			return I18n.format("levels.ability." + this.ordinal()) + " III";
		
		return I18n.format("levels.ability." + this.ordinal());
	}
	
	public String getType()
	{
		return type;
	}
	
	public String getCategory()
	{
		return category;
	}
	
	static
	{
		int j = 0;
		int k = 0;
		
		for (int i = 0; i < Ability.values().length; i++)
		{		
			if (Ability.values()[i].getCategory().equals("weapon") && Ability.values()[i].enabled)
			{
				Ability.WEAPONS.add(Ability.values()[i]);
				Ability.WEAPON_ABILITIES++;
			}
			else if (Ability.values()[i].getCategory().equals("armor") && Ability.values()[i].enabled)
			{
				j = Ability.WEAPON_ABILITIES + 1;
				Ability.ARMOR.add(Ability.values()[k + j]);
				Ability.ARMOR_ABILITIES++;
				k++;
			}
		}
	}
}

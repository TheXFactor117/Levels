package com.thexfactor117.levels.leveling;

import com.thexfactor117.levels.util.Config;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * 
 * @author TheXFactor117
 *
 * Class for handling the experience of weapons and such.
 * 
 */
public class Experience 
{
	/**
	 * Levels up the current weapon/armor to the next level, assuming it is not at max level.
	 * @param player
	 * @param stack
	 */
	public static void levelUp(EntityPlayer player, ItemStack stack)
	{
		NBTTagCompound nbt = stack.getTagCompound();
		
		if (nbt != null)
		{
			while (Experience.getLevel(nbt) < Config.maxLevel && Experience.getExperience(nbt) >= Experience.getNextLevelExperience(Experience.getLevel(nbt)))
			{
				Experience.setLevel(nbt, Experience.getLevel(nbt) + 1);
				
				// update multipliers
				
				// send audio to client if Mythic
			}
		}
	}
	
	/**
	 * Returns the level of the current weapon/armor.
	 * @param nbt
	 * @return
	 */
	public static int getLevel(NBTTagCompound nbt)
	{
		return nbt != null ? nbt.getInteger("LEVEL") : 1;
	}
	
	/**
	 * Sets the level of the current weapon/armor.
	 * @param nbt
	 * @param level
	 */
	public static void setLevel(NBTTagCompound nbt, int level)
	{
		if (nbt != null)
		{
			if (level > 0)
				nbt.setInteger("LEVEL", level);
			else
				nbt.removeTag("LEVEL");
		}
	}
	
	/**
	 * Returns the experience of the current weapon/armor.
	 * @param nbt
	 * @return
	 */
	public static int getExperience(NBTTagCompound nbt)
	{
		return nbt != null ? nbt.getInteger("EXPERIENCE") : 1;
	}
	
	/**
	 * Sets the experience of the current weapon/armor.
	 * @param nbt
	 * @param level
	 */
	public static void setExperience(NBTTagCompound nbt, int experience)
	{
		if (nbt != null)
		{
			if (experience > 0)
				nbt.setInteger("EXPERIENCE", experience);
			else
				nbt.removeTag("EXPERIENCE");
		}
	}
	
	/**
	 * Returns the amount of experience to level up.
	 * @param currentLevel
	 * @return
	 */
	public static int getNextLevelExperience(int currentLevel)
	{
		return (int) Math.pow(currentLevel, Config.expExponent) * Config.expMultiplier;
	}
}

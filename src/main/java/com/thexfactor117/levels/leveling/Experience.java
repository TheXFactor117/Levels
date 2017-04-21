package com.thexfactor117.levels.leveling;

import com.thexfactor117.levels.Levels;
import com.thexfactor117.levels.util.Config;
import com.thexfactor117.levels.util.NBTHelper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

/**
 * 
 * @author TheXFactor117
 *
 * Handles the experience of weapons and armor.
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
				Experience.setLevel(nbt, Experience.getLevel(nbt) + 1); // increase level by one
				
				player.sendMessage(new TextComponentString(stack.getDisplayName() + TextFormatting.GRAY + " has leveled up to level " + TextFormatting.GOLD + Experience.getLevel(nbt) + TextFormatting.GRAY + "!"));
				
				// update damage and attack speed values
				NBTTagList taglist = nbt.getTagList("AttributeModifiers", 10); // retrieves our custom Attribute Modifier implementation
				NBTTagCompound damageNbt = taglist.getCompoundTagAt(0);
				NBTTagCompound speedNbt = taglist.getCompoundTagAt(1);
				double newDamage = damageNbt.getDouble("Amount") + ((damageNbt.getDouble("Amount") * nbt.getDouble("Multiplier")) / 2);
				double newSpeed = speedNbt.getDouble("Amount") - ((speedNbt.getDouble("Amount") * nbt.getDouble("Multiplier")) / 2);		
				damageNbt.setDouble("Amount", newDamage);
				speedNbt.setDouble("Amount", newSpeed);
				
				// update attributes
				for (Attribute attribute : Attribute.values())
				{
					if (attribute.hasAttribute(nbt))
					{
						Levels.LOGGER.info(attribute.getActiveAt(nbt));
						
						if (!attribute.isActive(nbt) && Experience.getLevel(nbt) >= attribute.getActiveAt(nbt))
						{
							attribute.activate(nbt);
							player.sendMessage(new TextComponentString(TextFormatting.GRAY + " The " + attribute.getColor() + attribute.getName() + TextFormatting.GRAY + " attribute has been unlocked!"));
						}
					}
				}

				NBTHelper.saveStackNBT(stack, nbt);
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

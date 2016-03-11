package com.thexfactor117.levels.helpers;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * 
 * @author TheXFactor117
 * @author MrIbby
 *
 */
public class NBTHelper 
{
	public static NBTTagCompound loadStackNBT(ItemStack stack)
	{
		return stack.hasTagCompound() ? stack.getTagCompound() : new NBTTagCompound();
	}

	public static void saveStackNBT(ItemStack stack, NBTTagCompound nbt)
	{
		if (!stack.hasTagCompound() && !nbt.hasNoTags())
		{
			stack.setTagCompound(nbt);
		}
	}
	
	public static NBTTagCompound loadEntityNBT(Entity entity)
	{
		return entity.getEntityData();
	}

	public static void saveEntityNBT(Entity entity, NBTTagCompound nbt)
	{
		if (!entity.getEntityData().hasNoTags() && !nbt.hasNoTags())
		{
			entity.writeToNBT(nbt);
		}
	}
}

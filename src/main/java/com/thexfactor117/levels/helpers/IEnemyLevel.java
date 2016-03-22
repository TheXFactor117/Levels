package com.thexfactor117.levels.helpers;

import net.minecraft.nbt.NBTTagCompound;

public interface IEnemyLevel 
{
	public NBTTagCompound saveNBTData();
	public void loadNBTData(NBTTagCompound nbt);
	public int getEnemyLevel();
}

package com.thexfactor117.levels.helpers;

import java.util.Random;

import net.minecraft.nbt.NBTTagCompound;

public class EnemyLevelCapability implements IEnemyLevel
{
	private int level;
	private Random rand = new Random();
	
	public EnemyLevelCapability()
	{
		this.level = EnemyLevel.getRandomLevel(rand).ordinal();
	}
	
	@Override
	public NBTTagCompound saveNBTData() 
	{
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setInteger("ENEMY_LEVEL", level);
		return nbt;
	}

	@Override
	public void loadNBTData(NBTTagCompound nbt) 
	{
		level = nbt.getInteger("ENEMY_LEVEL");
	}

	@Override
	public int getEnemyLevel() 
	{
		return level;
	}

}

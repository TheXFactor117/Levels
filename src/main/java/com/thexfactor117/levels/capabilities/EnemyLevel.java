package com.thexfactor117.levels.capabilities;

import javax.annotation.Nullable;

import net.minecraft.entity.EntityLivingBase;

/**
 * 
 * @author TheXFactor117
 *
 */
public class EnemyLevel implements IEnemyLevel
{
	private int level;
	@SuppressWarnings("unused")
	private final EntityLivingBase entity;
	
	public EnemyLevel(@Nullable EntityLivingBase entity)
	{
		this.entity = entity;
	}
	
	@Override
	public int getEnemyLevel() 
	{
		return level;
	}

	@Override
	public void setEnemyLevel(int level) 
	{
		this.level = level;
	}
}

package com.thexfactor117.levels.capabilities;

import javax.annotation.Nullable;

import net.minecraft.entity.EntityLivingBase;

/**
 * 
 * @author TheXFactor117
 *
 */
public class Blacksmithing implements IBlacksmithing
{
	private int rank;
	private int experience;
	@SuppressWarnings("unused")
	private final EntityLivingBase entity;
	
	public Blacksmithing(@Nullable EntityLivingBase entity)
	{
		this.entity = entity;
	}

	@Override
	public int getBlacksmithingRank() 
	{
		return rank;
	}

	@Override
	public void setBlacksmithingRank(int rank) 
	{
		this.rank = rank;
	}

	@Override
	public int getBlacksmithingExperience() 
	{
		return experience;
	}

	@Override
	public void setBlacksmithingExperience(int experience) 
	{
		this.experience = experience;
	}
	
	@Override
	public int getExperienceRemaining()
	{
		return (int) (75 * Math.pow(this.getBlacksmithingRank(), 2.5));
	}
}

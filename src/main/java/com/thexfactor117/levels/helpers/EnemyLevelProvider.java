package com.thexfactor117.levels.helpers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;

public class EnemyLevelProvider implements ICapabilityProvider, INBTSerializable<NBTTagCompound>
{
	@CapabilityInject(IEnemyLevel.class)
	public static Capability<IEnemyLevel> EXTENDEDMOB = null;
	
	private IEnemyLevel enemyLevel = null;
	
	public EnemyLevelProvider()
	{
		enemyLevel = new EnemyLevelCapability();
	}
	
	public EnemyLevelProvider(IEnemyLevel enemyLevel)
	{
		this.enemyLevel = enemyLevel;
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) 
	{
		return EXTENDEDMOB != null && capability == EXTENDEDMOB;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) 
	{
		if (EXTENDEDMOB != null && capability == EXTENDEDMOB) return (T)enemyLevel;
		return null;
	}

	public static IEnemyLevel get(EntityPlayer player) 
	{
		return player.hasCapability(EXTENDEDMOB, null)? player.getCapability(EXTENDEDMOB, null): null;
	}

	@Override
	public NBTTagCompound serializeNBT() 
	{
		return enemyLevel.saveNBTData();
	}

	@Override
	public void deserializeNBT(NBTTagCompound nbt) 
	{
		enemyLevel.loadNBTData(nbt);
	}
}

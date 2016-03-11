package com.thexfactor117.levels.handlers;

import java.util.Random;

import com.thexfactor117.levels.helpers.EnemyLevel;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

/**
 * 
 * @author TheXFactor117
 *
 */
public class ExtendedMob implements IExtendedEntityProperties
{
	public static final String EXTENDED_PROPERTIES = "ExtendedMob";
	@SuppressWarnings("unused")
	private final EntityMob entity;
	private EnemyLevel level;
	
	/**
	 * Constructor for the extended mob. Used to set a random level.
	 * @param mob
	 */
	public ExtendedMob(EntityMob mob)
	{
		this.entity = mob;
		NBTTagCompound nbt = mob.getEntityData();
		
		if (mob != null)
		{
			if (nbt != null)
			{
				this.level = EnemyLevel.getEnemyLevel(nbt);
				Random rand = mob.worldObj.rand;
				
				if (this.level == EnemyLevel.DEFAULT)
				{
					level = EnemyLevel.getRandomLevel(rand);
				}
			}
		}
	}
	
	/**
	 * Registers the mob to have extended properties
	 * @param mob
	 */
	public static final void register(EntityMob mob)
	{
		mob.registerExtendedProperties(EXTENDED_PROPERTIES, new ExtendedMob(mob));
	}
	
	/**
	 * Returns the extended mob with its properties
	 * @param mob
	 * @return
	 */
	public static final ExtendedMob get(EntityMob mob)
	{
		return (ExtendedMob) mob.getExtendedProperties(EXTENDED_PROPERTIES);
	}
	
	@Override
	public void saveNBTData(NBTTagCompound nbt)
	{
		nbt = new NBTTagCompound();
		level.setEnemyLevel(nbt);
		nbt.setTag(EXTENDED_PROPERTIES, nbt);
	}
	
	@Override
	public void loadNBTData(NBTTagCompound compound)
	{
		NBTTagCompound nbt = (NBTTagCompound) compound.getTag(EXTENDED_PROPERTIES);
		level = EnemyLevel.getEnemyLevel(nbt);
	}
	
	@Override
	public void init(Entity entity, World world) {}
	
	/**
	 * Returns the enemy level as saved in its properties
	 * @return
	 */
	public EnemyLevel getEnemyLevelFromProps()
	{
		return level;
	}
}

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
@Deprecated
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
		this.level = EnemyLevel.getEnemyLevel(nbt);
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
		return (ExtendedMob) mob;
	}
	
	@Override
	public void saveNBTData(NBTTagCompound compound)
	{
		NBTTagCompound nbt = new NBTTagCompound();
		this.level.setEnemyLevel(nbt);
		compound.setTag(EXTENDED_PROPERTIES, nbt);
	}
	
	@Override
	public void loadNBTData(NBTTagCompound compound)
	{
		NBTTagCompound nbt = (NBTTagCompound) compound.getTag(EXTENDED_PROPERTIES);
		this.level = EnemyLevel.getEnemyLevel(nbt);
	}
	
	@Override
	public void init(Entity entity, World world) {}
	
	/**
	 * Returns the enemy level as saved in its properties
	 * @return
	 */
	public EnemyLevel getEnemyLevelFromProps()
	{
		Random rand = new Random();
		
		if (level == EnemyLevel.DEFAULT)
		{
			level = EnemyLevel.getRandomLevel(rand);
		}
		
		return level;
	}
}

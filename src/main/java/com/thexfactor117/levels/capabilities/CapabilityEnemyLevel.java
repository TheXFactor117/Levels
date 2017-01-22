package com.thexfactor117.levels.capabilities;

import javax.annotation.Nullable;

import com.thexfactor117.levels.util.CapabilityUtils;
import com.thexfactor117.levels.util.Reference;
import com.thexfactor117.levels.util.SimpleCapabilityProvider;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CapabilityEnemyLevel 
{
	@CapabilityInject(IEnemyLevel.class)
	public static final Capability<IEnemyLevel> ENEMY_LEVEL_CAP = null;
	public static final EnumFacing DEFAULT_FACING = null;
	public static final ResourceLocation ID = new ResourceLocation(Reference.MODID, "EnemyLevel");
	
	public static void register() 
	{
		CapabilityManager.INSTANCE.register(IEnemyLevel.class, new Capability.IStorage<IEnemyLevel>() 
		{
			@Override
			public NBTBase writeNBT(Capability<IEnemyLevel> capability, IEnemyLevel instance, EnumFacing side) 
			{
				return new NBTTagInt(instance.getEnemyLevel());
			}

			@Override
			public void readNBT(Capability<IEnemyLevel> capability, IEnemyLevel instance, EnumFacing side, NBTBase nbt) 
			{
				instance.setEnemyLevel(((NBTTagInt) nbt).getInt());
			}
		}, () -> new EnemyLevel(null));

		MinecraftForge.EVENT_BUS.register(new EventHandler());
	}
	
	@Nullable
	public static IEnemyLevel getEnemyLevel(EntityLivingBase entity) 
	{
		return CapabilityUtils.getCapability(entity, ENEMY_LEVEL_CAP, DEFAULT_FACING);
	}
	
	public static ICapabilityProvider createProvider(IEnemyLevel level) 
	{
		return new SimpleCapabilityProvider<>(ENEMY_LEVEL_CAP, DEFAULT_FACING, level);
	}
	
	public static class EventHandler 
	{
		@SubscribeEvent
		public void attachCapabilities(AttachCapabilitiesEvent<Entity> event) 
		{
			if (event.getObject() instanceof EntityMob) 
			{
				final EnemyLevel enemyLevel = new EnemyLevel((EntityMob) event.getObject());
				event.addCapability(ID, createProvider(enemyLevel));
			}
		}
	}
}

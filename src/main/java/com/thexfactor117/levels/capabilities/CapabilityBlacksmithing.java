package com.thexfactor117.levels.capabilities;

import javax.annotation.Nullable;

import com.thexfactor117.levels.util.CapabilityUtils;
import com.thexfactor117.levels.util.Reference;
import com.thexfactor117.levels.util.SimpleCapabilityProvider;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * 
 * @author TheXFactor117
 *
 */
public class CapabilityBlacksmithing 
{
	@CapabilityInject(IBlacksmithing.class)
	public static final Capability<IBlacksmithing> BLACKSMITHING_CAP = null;
	public static final EnumFacing DEFAULT_FACING = null;
	public static final ResourceLocation ID = new ResourceLocation(Reference.MODID, "Blacksmithing");
	
	public static void register() 
	{
		CapabilityManager.INSTANCE.register(IBlacksmithing.class, new Capability.IStorage<IBlacksmithing>() 
		{
			@Override
			public NBTBase writeNBT(Capability<IBlacksmithing> capability, IBlacksmithing instance, EnumFacing side) 
			{
				NBTTagCompound nbt = new NBTTagCompound();
				
				nbt.setInteger("RANK", instance.getBlacksmithingRank());
				nbt.setInteger("EXPERIENCE", instance.getBlacksmithingExperience());
				
				return nbt;
			}

			@Override
			public void readNBT(Capability<IBlacksmithing> capability, IBlacksmithing instance, EnumFacing side, NBTBase nbt) 
			{
				NBTTagCompound compound = (NBTTagCompound) nbt;
				
				instance.setBlacksmithingRank(compound.getInteger("RANK"));
				instance.setBlacksmithingExperience(compound.getInteger("EXPERIENCE"));
			}
		}, () -> new Blacksmithing(null));

		MinecraftForge.EVENT_BUS.register(new EventHandler());
	}
	
	@Nullable
	public static IBlacksmithing getBlacksmithing(EntityLivingBase entity) 
	{
		return CapabilityUtils.getCapability(entity, BLACKSMITHING_CAP, DEFAULT_FACING);
	}
	
	public static ICapabilityProvider createProvider(IBlacksmithing level) 
	{
		return new SimpleCapabilityProvider<>(BLACKSMITHING_CAP, DEFAULT_FACING, level);
	}
	
	public static class EventHandler 
	{
		@SubscribeEvent
		public void attachCapabilities(AttachCapabilitiesEvent<Entity> event) 
		{
			if (event.getObject() instanceof EntityPlayer) 
			{
				final Blacksmithing blacksmithing = new Blacksmithing((EntityPlayer) event.getObject());
				event.addCapability(ID, createProvider(blacksmithing));
			}
		}
		
		@SubscribeEvent
		public void playerClone(PlayerEvent.Clone event) 
		{
			IBlacksmithing oldBlacksmithing = getBlacksmithing(event.getOriginal());
			IBlacksmithing newBlacksmithing = getBlacksmithing(event.getEntityPlayer());
			
			if (newBlacksmithing != null && oldBlacksmithing != null)
			{
				newBlacksmithing.setBlacksmithingRank(oldBlacksmithing.getBlacksmithingRank());
				newBlacksmithing.setBlacksmithingExperience(oldBlacksmithing.getBlacksmithingExperience());
			}
		}
	}
}

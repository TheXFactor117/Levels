package com.thexfactor117.levels.utils;

import javax.annotation.Nullable;

import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

/**
 * 
 * @author TheXFactor117
 * @author Choonster
 *
 */
public class CapabilityUtils 
{
	@Nullable
	public static <T> T getCapability(@Nullable ICapabilityProvider provider, Capability<T> capability, @Nullable EnumFacing facing) 
	{
		return provider != null && provider.hasCapability(capability, facing) ? provider.getCapability(capability, facing) : null;
	}
}

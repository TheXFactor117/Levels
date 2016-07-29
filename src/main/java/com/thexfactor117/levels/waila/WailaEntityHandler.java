package com.thexfactor117.levels.waila;

import java.util.List;

import com.thexfactor117.levels.capabilities.CapabilityEnemyLevel;
import com.thexfactor117.levels.capabilities.IEnemyLevel;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaEntityAccessor;
import mcp.mobius.waila.api.IWailaEntityProvider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class WailaEntityHandler implements IWailaEntityProvider 
{
	@Override
	public NBTTagCompound getNBTData(EntityPlayerMP arg0, Entity arg1, NBTTagCompound arg2, World arg3) 
	{
		return arg2;
	}

	@Override
	public List<String> getWailaBody(Entity entity, List<String> list, IWailaEntityAccessor arg2, IWailaConfigHandler arg3) 
	{
		if (entity instanceof EntityMob)
		{
			if (entity.hasCapability(CapabilityEnemyLevel.ENEMY_LEVEL_CAP, null))
			{
				IEnemyLevel enemyLevel = entity.getCapability(CapabilityEnemyLevel.ENEMY_LEVEL_CAP, null);
				
				if (enemyLevel.getEnemyLevel() > 0)
				{
					int level = enemyLevel.getEnemyLevel();
					
					switch (level)
					{
						case 0: 
							list.add("Default");
							break;
						case 1: 
							list.add(TextFormatting.GRAY + "Weakened");
							break;
						case 2: 
							list.add(TextFormatting.WHITE + "Normal");
							break;
						case 3: 
							list.add(TextFormatting.DARK_GREEN + "Hardened");
							break;
						case 4: 
							list.add(TextFormatting.AQUA + "Superior");
							break;
						case 5: 
							list.add(TextFormatting.DARK_PURPLE + "Elite");
							break;
						case 6: 
							list.add(TextFormatting.GOLD + "Legendary");
							break;
					}
				}
			}
		}
		
		return list;
	}

	@Override
	public List<String> getWailaHead(Entity arg0, List<String> arg1, IWailaEntityAccessor arg2, IWailaConfigHandler arg3) 
	{
		return arg1;
	}

	@Override
	public Entity getWailaOverride(IWailaEntityAccessor arg0, IWailaConfigHandler arg1) 
	{
		return arg0.getEntity();
	}

	@Override
	public List<String> getWailaTail(Entity arg0, List<String> arg1, IWailaEntityAccessor arg2, IWailaConfigHandler arg3) 
	{
		return arg1;
	}
}

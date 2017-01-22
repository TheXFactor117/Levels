package com.thexfactor117.levels.network;

import com.thexfactor117.levels.capabilities.CapabilityEnemyLevel;
import com.thexfactor117.levels.capabilities.IEnemyLevel;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.util.IThreadListener;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * 
 * @author TheXFactor117
 *
 */
public class PacketEnemyLevel implements IMessage
{
	private int level;
	private int entityID;
	
	public PacketEnemyLevel() {}
	
	public PacketEnemyLevel(int level, int entityID)
	{
		this.level = level;
		this.entityID = entityID;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) 
	{
		this.level = buf.readInt();
		this.entityID = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) 
	{
		buf.writeInt(level);
		buf.writeInt(entityID);
	}
	
	public static class Handler implements IMessageHandler<PacketEnemyLevel, IMessage>
	{
		@Override
		public IMessage onMessage(final PacketEnemyLevel message, MessageContext ctx) 
		{			
			IThreadListener mainThread = Minecraft.getMinecraft();
			mainThread.addScheduledTask(new Runnable()
			{
				@Override
				public void run() 
				{	
					Entity entity = Minecraft.getMinecraft().theWorld.getEntityByID(message.entityID);
					
					if (entity instanceof EntityMob)
					{
						final IEnemyLevel enemyLevel = CapabilityEnemyLevel.getEnemyLevel((EntityMob) entity);
						enemyLevel.setEnemyLevel(message.level);
					}
				}
			});
			
			return null;
		}
	}
}

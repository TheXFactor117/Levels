package com.thexfactor117.levels.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.IThreadListener;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * 
 * @author TheXFactor117
 *
 */
public class PacketMythicSound implements IMessage
{
	public PacketMythicSound() {}

	@Override
	public void fromBytes(ByteBuf buf) 
	{

	}

	@Override
	public void toBytes(ByteBuf buf) 
	{

	}
	
	public static class Handler implements IMessageHandler<PacketMythicSound, IMessage>
	{
		@Override
		public IMessage onMessage(final PacketMythicSound message, final MessageContext ctx) 
		{			
			IThreadListener mainThread = Minecraft.getMinecraft();
			mainThread.addScheduledTask(new Runnable()
			{
				@Override
				public void run() 
				{
					EntityPlayer player = Minecraft.getMinecraft().player;
					player.playSound(SoundEvents.ENTITY_ENDERDRAGON_DEATH, 1.0F, 1.0F);
				}
			});
			
			return null;
		}
	}
}

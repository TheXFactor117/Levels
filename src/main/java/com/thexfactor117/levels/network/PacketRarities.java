package com.thexfactor117.levels.network;

import com.thexfactor117.levels.helpers.LogHelper;

import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class PacketRarities implements IMessage
{
	private String text;
	private NBTTagCompound nbt;
	
	public PacketRarities()
	{
		
	}
	
	public PacketRarities(String rarity, NBTTagCompound nbt)
	{
		this.nbt = nbt;
		this.text = rarity;
	}
	
	@Override
    public void fromBytes(ByteBuf buf) 
	{
        text = ByteBufUtils.readUTF8String(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) 
    {
        ByteBufUtils.writeUTF8String(buf, text);
    }
    
    public static class HandlerRarities implements IMessageHandler<PacketRarities, IMessage> 
    {
        @Override
        public IMessage onMessage(PacketRarities message, MessageContext ctx) 
        {
        	LogHelper.info("Did we make it here?");
        	LogHelper.info(message.text);
        	if (message.text == "basic") LogHelper.info("BASIC!");
        	if (message.text == "uncommon") message.nbt.setInteger("RARITY", 2);
        	if (message.text == "rare") message.nbt.setInteger("RARITY", 3);
        	if (message.text == "legendary") message.nbt.setInteger("RARITY", 4);
        	if (message.text == "ancient") message.nbt.setInteger("RARITY", 5);
        	
        	return null;
        }
    }
}

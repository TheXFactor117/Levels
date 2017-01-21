package com.thexfactor117.levels.network;

import com.thexfactor117.levels.leveling.Ability;
import com.thexfactor117.levels.leveling.Experience;
import com.thexfactor117.levels.util.NBTHelper;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * 
 * @author TheXFactor117
 *
 */
public class PacketGuiAbility implements IMessage
{
	private int index;
	
	public PacketGuiAbility() {}
	
	public PacketGuiAbility(int index)
	{
		this.index = index;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) 
	{
		index = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) 
	{
		buf.writeInt(index);
	}
	
	public static class Handler implements IMessageHandler<PacketGuiAbility, IMessage>
	{
		@Override
		public IMessage onMessage(final PacketGuiAbility message, final MessageContext ctx) 
		{			
			IThreadListener mainThread = (WorldServer) ctx.getServerHandler().playerEntity.worldObj;
			mainThread.addScheduledTask(new Runnable()
			{
				@Override
				public void run() 
				{
					EntityPlayer player = ctx.getServerHandler().playerEntity;
					
					if (player != null)
					{
						ItemStack stack = player.inventory.getCurrentItem();
						
						if (stack != null)
						{
							NBTTagCompound nbt = NBTHelper.loadStackNBT(stack);
							
							if (stack.getItem() instanceof ItemSword || stack.getItem() instanceof ItemAxe || stack.getItem() instanceof ItemBow)
							{	
								if (Ability.WEAPONS[message.index].hasAbility(nbt))
								{
									Ability.WEAPONS[message.index].setLevel(nbt, Ability.WEAPONS[message.index].getLevel(nbt) + 1);
									Experience.setAbilityTokens(nbt, Experience.getAbilityTokens(nbt) - 1);
								}
								else
								{
									Ability.WEAPONS[message.index].addAbility(nbt, 1);
									Experience.setAbilityTokens(nbt, Experience.getAbilityTokens(nbt) - Ability.WEAPONS[message.index].getTier());
								}
							}
							else if (stack.getItem() instanceof ItemArmor)
							{
								if (Ability.ARMOR[message.index].hasAbility(nbt))
								{
									Ability.ARMOR[message.index].setLevel(nbt, Ability.ARMOR[message.index].getLevel(nbt) + 1);
									Experience.setAbilityTokens(nbt, Experience.getAbilityTokens(nbt) - 1);
								}
								else
								{
									Ability.ARMOR[message.index].addAbility(nbt, 1);
									Experience.setAbilityTokens(nbt, Experience.getAbilityTokens(nbt) - Ability.ARMOR[message.index].getTier());
								}
							}
						}
					}
				}
			});
			
			return null;
		}
	}
}

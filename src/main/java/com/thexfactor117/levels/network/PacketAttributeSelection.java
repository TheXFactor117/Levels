package com.thexfactor117.levels.network;

import com.thexfactor117.levels.leveling.Experience;
import com.thexfactor117.levels.leveling.Rarity;
import com.thexfactor117.levels.leveling.attributes.ArmorAttribute;
import com.thexfactor117.levels.leveling.attributes.BowAttribute;
import com.thexfactor117.levels.leveling.attributes.ShieldAttribute;
import com.thexfactor117.levels.leveling.attributes.WeaponAttribute;
import com.thexfactor117.levels.util.NBTHelper;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemShield;
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
public class PacketAttributeSelection implements IMessage
{
	private int index;
	
	public PacketAttributeSelection() {}
	
	public PacketAttributeSelection(int index)
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
	
	public static class Handler implements IMessageHandler<PacketAttributeSelection, IMessage>
	{
		@Override
		public IMessage onMessage(final PacketAttributeSelection message, final MessageContext ctx) 
		{			
			IThreadListener mainThread = (WorldServer) ctx.getServerHandler().playerEntity.getEntityWorld();
			mainThread.addScheduledTask(new Runnable()
			{
				@Override
				public void run() 
				{
					EntityPlayer player = ctx.getServerHandler().playerEntity;
					ItemStack stack = player.inventory.getCurrentItem();
					NBTTagCompound nbt = NBTHelper.loadStackNBT(stack);
					
					if (player != null && stack != null && nbt != null)
					{
						if (stack.getItem() instanceof ItemSword)
						{
							WeaponAttribute attribute = WeaponAttribute.WEAPON_ATTRIBUTES.get(message.index);
							
							if (attribute.hasAttribute(nbt))
							{
								attribute.setAttributeTier(nbt, attribute.getAttributeTier(nbt) + 1);
								Experience.setAttributeTokens(nbt, Experience.getAttributeTokens(nbt) - 1);
							}
							else
							{
								int cost = 1;
								
								if (attribute.getRarity() == Rarity.UNCOMMON)
									cost = 1;
								else if (attribute.getRarity() == Rarity.RARE)
									cost = 2;
								else if (attribute.getRarity() == Rarity.LEGENDARY)
									cost = 3;
								
								attribute.addAttribute(nbt);
								Experience.setAttributeTokens(nbt, Experience.getAttributeTokens(nbt) - cost);
								
								if (attribute == WeaponAttribute.UNBREAKABLE)
									nbt.setInteger("Unbreakable", 1);
							}
						}
						else if (stack.getItem() instanceof ItemArmor)
						{
							ArmorAttribute attribute = ArmorAttribute.ARMOR_ATTRIBUTES.get(message.index);
							
							if (attribute.hasAttribute(nbt))
							{
								attribute.setAttributeTier(nbt, attribute.getAttributeTier(nbt) + 1);
								Experience.setAttributeTokens(nbt, Experience.getAttributeTokens(nbt) - 1);
							}
							else
							{
								int cost = 1;
								
								if (attribute.getRarity() == Rarity.UNCOMMON)
									cost = 1;
								else if (attribute.getRarity() == Rarity.RARE)
									cost = 2;
								else if (attribute.getRarity() == Rarity.LEGENDARY)
									cost = 3;
								
								attribute.addAttribute(nbt);
								Experience.setAttributeTokens(nbt, Experience.getAttributeTokens(nbt) - cost);
								
								if (attribute == ArmorAttribute.UNBREAKABLE)
									nbt.setInteger("Unbreakable", 1);
							}	
						}
						else if (stack.getItem() instanceof ItemBow)
						{
							BowAttribute attribute = BowAttribute.BOW_ATTRIBUTES.get(message.index);
							
							if (attribute.hasAttribute(nbt))
							{
								attribute.setAttributeTier(nbt, attribute.getAttributeTier(nbt) + 1);
								Experience.setAttributeTokens(nbt, Experience.getAttributeTokens(nbt) - 1);
							}
							else
							{
								int cost = 1;
								
								if (attribute.getRarity() == Rarity.UNCOMMON)
									cost = 1;
								else if (attribute.getRarity() == Rarity.RARE)
									cost = 2;
								else if (attribute.getRarity() == Rarity.LEGENDARY)
									cost = 3;
								
								attribute.addAttribute(nbt);
								Experience.setAttributeTokens(nbt, Experience.getAttributeTokens(nbt) - cost);
								
								if (attribute == BowAttribute.UNBREAKABLE)
									nbt.setInteger("Unbreakable", 1);
							}
						}
						else if (stack.getItem() instanceof ItemShield)
						{
							ShieldAttribute attribute = ShieldAttribute.SHIELD_ATTRIBUTES.get(message.index);
							
							if (attribute.hasAttribute(nbt))
							{
								attribute.setAttributeTier(nbt, attribute.getAttributeTier(nbt) + 1);
								Experience.setAttributeTokens(nbt, Experience.getAttributeTokens(nbt) - 1);
							}
							else
							{
								int cost = 1;
								
								if (attribute.getRarity() == Rarity.UNCOMMON)
									cost = 1;
								else if (attribute.getRarity() == Rarity.RARE)
									cost = 2;
								else if (attribute.getRarity() == Rarity.LEGENDARY)
									cost = 3;
								
								attribute.addAttribute(nbt);
								Experience.setAttributeTokens(nbt, Experience.getAttributeTokens(nbt) - cost);
								
								if (attribute == ShieldAttribute.UNBREAKABLE)
									nbt.setInteger("Unbreakable", 1);
							}
						}
					}
				}
			});
			
			return null;
		}
	}
}

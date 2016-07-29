package com.thexfactor117.levels.commands;

import java.util.ArrayList;
import java.util.List;

import com.thexfactor117.levels.leveling.Ability;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

/**
 * 
 * @author TheXFactor117
 *
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class CommandSetAbilities implements ICommand
{
	private final List aliases;
	
	public CommandSetAbilities()
	{
		aliases = new ArrayList();
		aliases.add("setAbilities");
		aliases.add("sa");
	}
	
	@Override
	public int compareTo(ICommand o) 
	{
		return 0;
	}

	@Override
	public String getCommandName() 
	{
		return "setAbilities";
	}

	@Override
	public String getCommandUsage(ICommandSender sender)
	{
		return "setAbilities <Ability...>";
	}

	@Override
	public List<String> getCommandAliases() 
	{
		return this.aliases;
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException 
	{	
		if (!sender.getEntityWorld().isRemote)
		{
			if (args.length == 0) 
			{
				sender.addChatMessage(new TextComponentString(TextFormatting.GRAY + "Invalid arguments."));
				return;
			}
			
			EntityPlayer player = (EntityPlayer) sender.getCommandSenderEntity();
			
			if (player != null)
			{
				if (player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().getItem() instanceof ItemSword)
				{
					ItemStack stack = player.inventory.getCurrentItem();
					
					if (stack != null)
					{
						NBTTagCompound nbt = stack.getTagCompound();
						
						if (nbt != null)
						{
							for (String arg : args)
							{
								Ability.setAbility(nbt, arg, 2);
							}
						}
					}
				}
				else
				{
					sender.addChatMessage(new TextComponentString(TextFormatting.GRAY + "You are not holding a sword!"));
				}
			}
		}
	}

	@Override
	public boolean checkPermission(MinecraftServer server, ICommandSender sender) 
	{
		return true;
	}

	@Override
	public List<String> getTabCompletionOptions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos pos) 
	{
		return null;
	}

	@Override
	public boolean isUsernameIndex(String[] args, int index) 
	{
		return false;
	}
}

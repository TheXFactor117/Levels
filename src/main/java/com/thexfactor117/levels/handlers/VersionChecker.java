package com.thexfactor117.levels.handlers;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import net.minecraft.event.ClickEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;

import org.apache.commons.io.IOUtils;

import com.thexfactor117.levels.Levels;
import com.thexfactor117.levels.Reference;
import com.thexfactor117.levels.helpers.LogHelper;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class VersionChecker implements Runnable
{
	private static boolean isLatestVersion = false;
	private static String latestVersion = "";
	private boolean hasUpdateMessageAppeared = false;
	
	@Override
	public void run() 
	{
		InputStream in = null;
		
		try
		{
			in = new URL("https://raw.githubusercontent.com/TheXFactor117/Levels/master/version_checker.txt").openStream();
		}
		catch (MalformedURLException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		try 
        {
            latestVersion = IOUtils.readLines(in).get(0);
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        } 
        finally 
        {
            IOUtils.closeQuietly(in);
        }
        LogHelper.info("Latest mod version = " + latestVersion);
        isLatestVersion = Reference.VERSION.equals(latestVersion);
        LogHelper.info("Are you running the latest version = " + isLatestVersion + " ?");
	}
	
	public boolean isLatestVersion()
    {
		return isLatestVersion;
    }
    
    public String getLatestVersion()
    {
    	return latestVersion;
    }
    
    @SubscribeEvent
    public void onPlayerTick(PlayerTickEvent event)
    {
    	if (event.player.worldObj.isRemote && !Levels.versionChecker.isLatestVersion() && !this.hasUpdateMessageAppeared)
    	{
    		ClickEvent versionCheckChatClickEvent = new ClickEvent(ClickEvent.Action.OPEN_URL, "http://minecraft.curseforge.com/mc-mods/235552-levels/files");
    	    ChatStyle clickableChatStyle = new ChatStyle().setChatClickEvent(versionCheckChatClickEvent);
    	    ChatComponentText versionWarningChatComponent = new ChatComponentText(Reference.UPDATE_STRING);
    	    versionWarningChatComponent.setChatStyle(clickableChatStyle);
    	    event.player.addChatMessage(versionWarningChatComponent);
    	    this.hasUpdateMessageAppeared = true;
    	}
    }
}

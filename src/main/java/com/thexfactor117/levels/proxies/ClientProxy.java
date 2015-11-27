package com.thexfactor117.levels.proxies;

import com.thexfactor117.levels.Levels;
import com.thexfactor117.levels.handlers.VersionChecker;

/**
 * 
 * @author TheXFactor117
 *
 */
public class ClientProxy extends CommonProxy
{
	@Override
	public void postInit()
	{
		Levels.versionChecker = new VersionChecker();
		Thread versionCheckThread = new Thread(Levels.versionChecker, "Version Check");
		versionCheckThread.start();
	}
}
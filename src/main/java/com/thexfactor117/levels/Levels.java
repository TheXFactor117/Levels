package com.thexfactor117.levels;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.thexfactor117.levels.capabilities.CapabilityEnemyLevel;
import com.thexfactor117.levels.config.Config;
import com.thexfactor117.levels.init.ModEvents;
import com.thexfactor117.levels.network.PacketEnemyLevel;
import com.thexfactor117.levels.network.PacketGuiAbility;
import com.thexfactor117.levels.proxies.CommonProxy;
import com.thexfactor117.levels.util.GuiHandler;
import com.thexfactor117.levels.util.Reference;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

/**
 * 
 * @author TheXFactor117
 *
 * A simple Minecraft mod focused on the aspect of leveling certain areas
 * of the game. In its simplest state, it will add weapon leveling systems
 * on top of armor, bow, and enemy leveling in the near future. On top of
 * that, other interesting leveling systems are planned to enhance the
 * overall feel of Minecraft, while sticking to a primarily vanilla feel.
 */
@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION, updateJSON = Reference.UPDATE_URL)
public class Levels 
{
	@SidedProxy(clientSide = Reference.CLIENT, serverSide = Reference.COMMON)
	public static CommonProxy proxy;
	@Instance(Reference.MODID)
	public static Levels instance;
	public static final Logger LOGGER = LogManager.getLogger("Levels");
	public static SimpleNetworkWrapper network;
	private static File configDir;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		configDir = new File(event.getModConfigurationDirectory() + "/" + Reference.MODID);
		configDir.mkdirs();
		Config.init(configDir);
		
		ModEvents.registerEvents();
		proxy.preInit();
		
		if (Config.enemyLeveling)
			CapabilityEnemyLevel.register();
		
		network = NetworkRegistry.INSTANCE.newSimpleChannel("levels");
		network.registerMessage(PacketGuiAbility.Handler.class, PacketGuiAbility.class, 0, Side.SERVER);
		network.registerMessage(PacketEnemyLevel.Handler.class, PacketEnemyLevel.class, 1, Side.CLIENT);
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		proxy.init();
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {}
	
	public static File getConfigDir()
	{
		return configDir;
	}
}

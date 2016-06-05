package com.thexfactor117.levels;

import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;

/**
 * 
 * @author TheXFactor117
 *
 */
@SuppressWarnings("deprecation")
public class Reference 
{
	public static final String MODID = "levels";
	public static final String NAME = "Levels";
	public static final String VERSION = "r3.0.0";
	public static final String DEPENDENCIES = "required-after:xlib";
	public static final String COMMON_PROXY = "com.thexfactor117.levels.proxies.CommonProxy";
	public static final String CLIENT_PROXY = "com.thexfactor117.levels.proxies.ClientProxy";
	public static final String UPDATE_MESSAGE = TextFormatting.GOLD + "[Levels] " + TextFormatting.GRAY + I18n.translateToLocal("levels.update");
	public static final int MAX_ABILITY_LEVEL = 5;
	public static final String VERSION_CHECKER_URL = "https://raw.githubusercontent.com/TheXFactor117/Levels/master/version_checker.txt";
	public static final String UPDATE_URL = "http://minecraft.curseforge.com/mc-mods/235552-levels/files";
}

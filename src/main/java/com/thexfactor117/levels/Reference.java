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
	public static final String VERSION = "2.2.3.1";
	public static final String COMMON_PROXY = "com.thexfactor117.levels.proxies.CommonProxy";
	public static final String CLIENT_PROXY = "com.thexfactor117.levels.proxies.ClientProxy";
	public static final String UPDATE_STRING = TextFormatting.GOLD + "[Levels] " + TextFormatting.GRAY + I18n.translateToLocal("levels.update");
	public static final int MAX_ABILITY_LEVEL = 3;
}

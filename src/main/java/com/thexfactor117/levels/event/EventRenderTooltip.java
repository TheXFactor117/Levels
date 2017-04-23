package com.thexfactor117.levels.event;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import com.thexfactor117.levels.leveling.Rarity;
import com.thexfactor117.levels.util.NBTHelper;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.fml.client.config.GuiUtils;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * 
 * @author TheXFactor117
 *
 * Hooks into RenderTooltipEvent in order to change the background color of certain tooltips.
 *
 */
public class EventRenderTooltip 
{
	@SubscribeEvent
	public void renderTooltip(RenderTooltipEvent.Pre event)
	{
		if (event.getStack() != null && (event.getStack().getItem() instanceof ItemSword || event.getStack().getItem() instanceof ItemAxe || event.getStack().getItem() instanceof ItemArmor))
		{
			NBTTagCompound nbt = NBTHelper.loadStackNBT(event.getStack());
			
			if (nbt != null)
			{
				event.setCanceled(true);
				
				renderTooltip(event.getStack(), event.getLines(), event.getX(), event.getY(), event.getScreenWidth(), event.getScreenHeight(), event.getMaxWidth(), event.getFontRenderer(), Rarity.getRarity(nbt).getBackgroundColor(), Rarity.getRarity(nbt).getBorderColor());
			}
		}
	}
	
	/**
	 * Renders a tooltip. Modified from GuiUtils.
	 * @param stack
	 * @param textLines
	 * @param mouseX
	 * @param mouseY
	 * @param screenWidth
	 * @param screenHeight
	 * @param maxTextWidth
	 * @param font
	 * @param backgroundColor
	 * @param borderColor
	 */
	private void renderTooltip(@Nonnull final ItemStack stack, List<String> textLines, int mouseX, int mouseY, int screenWidth, int screenHeight, int maxTextWidth, FontRenderer font, int backgroundColor, int borderColor)
	{
		GlStateManager.disableRescaleNormal();
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableLighting();
        GlStateManager.disableDepth();
        int tooltipTextWidth = 0;

        for (String textLine : textLines)
        {
            int textLineWidth = font.getStringWidth(textLine);

            if (textLineWidth > tooltipTextWidth)
            {
                tooltipTextWidth = textLineWidth;
            }
        }

        boolean needsWrap = false;

        int titleLinesCount = 1;
        int tooltipX = mouseX + 12;
        if (tooltipX + tooltipTextWidth + 4 > screenWidth)
        {
            tooltipX = mouseX - 16 - tooltipTextWidth;
            if (tooltipX < 4) // if the tooltip doesn't fit on the screen
            {
                if (mouseX > screenWidth / 2)
                {
                    tooltipTextWidth = mouseX - 12 - 8;
                }
                else
                {
                    tooltipTextWidth = screenWidth - 16 - mouseX;
                }
                needsWrap = true;
            }
        }

        if (maxTextWidth > 0 && tooltipTextWidth > maxTextWidth)
        {
            tooltipTextWidth = maxTextWidth;
            needsWrap = true;
        }

        if (needsWrap)
        {
            int wrappedTooltipWidth = 0;
            List<String> wrappedTextLines = new ArrayList<String>();
            for (int i = 0; i < textLines.size(); i++)
            {
                String textLine = textLines.get(i);
                List<String> wrappedLine = font.listFormattedStringToWidth(textLine, tooltipTextWidth);
                if (i == 0)
                {
                    titleLinesCount = wrappedLine.size();
                }

                for (String line : wrappedLine)
                {
                    int lineWidth = font.getStringWidth(line);
                    if (lineWidth > wrappedTooltipWidth)
                    {
                        wrappedTooltipWidth = lineWidth;
                    }
                    wrappedTextLines.add(line);
                }
            }
            tooltipTextWidth = wrappedTooltipWidth;
            textLines = wrappedTextLines;

            if (mouseX > screenWidth / 2)
            {
                tooltipX = mouseX - 16 - tooltipTextWidth;
            }
            else
            {
                tooltipX = mouseX + 12;
            }
        }

        int tooltipY = mouseY - 12;
        int tooltipHeight = 8;

        if (textLines.size() > 1)
        {
            tooltipHeight += (textLines.size() - 1) * 10;
            if (textLines.size() > titleLinesCount) {
                tooltipHeight += 2; // gap between title lines and next lines
            }
        }

        if (tooltipY + tooltipHeight + 6 > screenHeight)
        {
            tooltipY = screenHeight - tooltipHeight - 6;
        }

        final int zLevel = 300;
        GuiUtils.drawGradientRect(zLevel, tooltipX - 3, tooltipY - 4, tooltipX + tooltipTextWidth + 3, tooltipY - 3, backgroundColor, backgroundColor);
        GuiUtils.drawGradientRect(zLevel, tooltipX - 3, tooltipY + tooltipHeight + 3, tooltipX + tooltipTextWidth + 3, tooltipY + tooltipHeight + 4, backgroundColor, backgroundColor);
        GuiUtils.drawGradientRect(zLevel, tooltipX - 3, tooltipY - 3, tooltipX + tooltipTextWidth + 3, tooltipY + tooltipHeight + 3, backgroundColor, backgroundColor);
        GuiUtils.drawGradientRect(zLevel, tooltipX - 4, tooltipY - 3, tooltipX - 3, tooltipY + tooltipHeight + 3, backgroundColor, backgroundColor);
        GuiUtils.drawGradientRect(zLevel, tooltipX + tooltipTextWidth + 3, tooltipY - 3, tooltipX + tooltipTextWidth + 4, tooltipY + tooltipHeight + 3, backgroundColor, backgroundColor);
        final int borderColorEnd = (borderColor & 0xFEFEFE) >> 1 | borderColor & 0xFF000000;
        GuiUtils.drawGradientRect(zLevel, tooltipX - 3, tooltipY - 3 + 1, tooltipX - 3 + 1, tooltipY + tooltipHeight + 3 - 1, borderColor, borderColorEnd);
        GuiUtils.drawGradientRect(zLevel, tooltipX + tooltipTextWidth + 2, tooltipY - 3 + 1, tooltipX + tooltipTextWidth + 3, tooltipY + tooltipHeight + 3 - 1, borderColor, borderColorEnd);
        GuiUtils.drawGradientRect(zLevel, tooltipX - 3, tooltipY - 3, tooltipX + tooltipTextWidth + 3, tooltipY - 3 + 1, borderColor, borderColor);
        GuiUtils.drawGradientRect(zLevel, tooltipX - 3, tooltipY + tooltipHeight + 2, tooltipX + tooltipTextWidth + 3, tooltipY + tooltipHeight + 3, borderColorEnd, borderColorEnd);
        
        //int tooltipTop = tooltipY;
        
        for (int lineNumber = 0; lineNumber < textLines.size(); ++lineNumber)
        {
            String line = textLines.get(lineNumber);
            font.drawStringWithShadow(line, (float)tooltipX, (float)tooltipY, -1);

            if (lineNumber + 1 == titleLinesCount)
            {
                tooltipY += 2;
            }

            tooltipY += 10;
        }
        
        GlStateManager.enableLighting();
        GlStateManager.enableDepth();
        RenderHelper.enableStandardItemLighting();
        GlStateManager.enableRescaleNormal();
	}
}

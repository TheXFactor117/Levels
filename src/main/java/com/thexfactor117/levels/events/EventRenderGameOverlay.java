package com.thexfactor117.levels.events;

import org.lwjgl.opengl.GL11;

import com.thexfactor117.levels.handlers.ExtendedMob;
import com.thexfactor117.levels.helpers.EnemyLevel;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

/**
 * 
 * @author TheXFactor117
 *
 */
public class EventRenderGameOverlay
{
	private final double NAME_VISION_DISTANCE = 32D;
	
	/**
	 * Called whenever the mouse is hovering over an entity to determine its level. Renders the level on
	 * the top of the screen.
	 * @param event
	 */
	@SubscribeEvent
	public void onRenderGameOverlay(RenderGameOverlayEvent.Pre event)
	{
		Minecraft mc = FMLClientHandler.instance().getClient();
		Entity ent = getEntityCrosshairOver(event.partialTicks, mc);

        if (ent != null && ent instanceof EntityMob)
        {
        	EntityMob mob = (EntityMob) ent;
        	ExtendedMob props = ExtendedMob.get(mob);
        	EnemyLevel enemyLevel = props.getEnemyLevelFromProps();
        	String level = enemyLevel.toString();
        	
            if (level != null)
            {
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                GL11.glDisable(GL11.GL_BLEND);

                ScaledResolution resolution = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
                int screenwidth = resolution.getScaledWidth();
                FontRenderer fontR = mc.fontRenderer;

                int yCoord = 10;
                fontR.drawStringWithShadow(level, screenwidth / 2 - fontR.getStringWidth(level) / 2, yCoord, enemyLevel.getColor());

                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            }
        }
	}
	
	private Entity getEntityCrosshairOver(float renderTick, Minecraft mc)
    {
        Entity returnedEntity = null;
        mc = FMLClientHandler.instance().getClient();
        
        if (mc.renderViewEntity != null)
        {
            if (mc.theWorld != null)
            {
                double reachDistance = NAME_VISION_DISTANCE;
                final MovingObjectPosition mopos = mc.renderViewEntity.rayTrace(reachDistance, renderTick);
                double reachDist2 = reachDistance;
                final Vec3 viewEntPositionVec = mc.renderViewEntity.getPosition(renderTick);

                if (mopos != null)
                {
                    reachDist2 = mopos.hitVec.squareDistanceTo(viewEntPositionVec);
                }

                final Vec3 viewEntityLookVec = mc.renderViewEntity.getLook(renderTick);
                final Vec3 actualReachVector =
                        viewEntPositionVec.addVector(viewEntityLookVec.xCoord * reachDistance, viewEntityLookVec.yCoord * reachDistance,
                                viewEntityLookVec.zCoord * reachDistance);
                float expandBBvalue = 1.0F;
                double lowestDistance = reachDist2;
                Entity iterEnt;
                Entity pointedEntity = null;
                for (Object obj : mc.theWorld.getEntitiesWithinAABBExcludingEntity(
                		mc.renderViewEntity,
                		mc.renderViewEntity.boundingBox.addCoord(viewEntityLookVec.xCoord * reachDistance, viewEntityLookVec.yCoord * reachDistance,
                                viewEntityLookVec.zCoord * reachDistance).expand((double) expandBBvalue, (double) expandBBvalue,
                                (double) expandBBvalue)))
                {
                    iterEnt = (Entity) obj;
                    if (iterEnt.canBeCollidedWith())
                    {
                        float entBorderSize = iterEnt.getCollisionBorderSize();
                        AxisAlignedBB entHitBox = iterEnt.boundingBox.expand((double) entBorderSize, (double) entBorderSize, (double) entBorderSize);
                        MovingObjectPosition interceptObjectPosition = entHitBox.calculateIntercept(viewEntPositionVec, actualReachVector);

                        if (entHitBox.isVecInside(viewEntPositionVec))
                        {
                            if (0.0D < lowestDistance || lowestDistance == 0.0D)
                            {
                                pointedEntity = iterEnt;
                                lowestDistance = 0.0D;
                            }
                        }
                        else if (interceptObjectPosition != null)
                        {
                            double distanceToEnt = viewEntPositionVec.distanceTo(interceptObjectPosition.hitVec);

                            if (distanceToEnt < lowestDistance || lowestDistance == 0.0D)
                            {
                                pointedEntity = iterEnt;
                                lowestDistance = distanceToEnt;
                            }
                        }
                    }
                }

                if (pointedEntity != null && (lowestDistance < reachDist2 || mopos == null))
                {
                    returnedEntity = pointedEntity;
                }
            }
        }

        return returnedEntity;
    }
}

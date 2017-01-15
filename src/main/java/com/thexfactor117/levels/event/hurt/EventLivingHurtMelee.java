package com.thexfactor117.levels.event.hurt;

import java.util.Iterator;
import java.util.List;

import com.thexfactor117.levels.leveling.Ability;
import com.thexfactor117.levels.leveling.Experience;
import com.thexfactor117.levels.leveling.Rarity;
import com.thexfactor117.levels.util.ConfigHandler;
import com.thexfactor117.levels.util.NBTHelper;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * 
 * @author TheXFactor117
 *
 */
public class EventLivingHurtMelee 
{
	@SubscribeEvent
	public void onHurt(LivingHurtEvent event)
	{
		if (event.getSource().getSourceOfDamage() instanceof EntityPlayer && !(event.getSource().getSourceOfDamage() instanceof FakePlayer))
		{
			EntityPlayer player = (EntityPlayer) event.getSource().getSourceOfDamage();
			EntityLivingBase enemy = event.getEntityLiving();
			ItemStack stack = player.inventory.getCurrentItem();
			
			if (stack != null && (stack.getItem() instanceof ItemSword || stack.getItem() instanceof ItemAxe))
			{
				NBTTagCompound nbt = NBTHelper.loadStackNBT(stack);
				
				if (nbt != null)
				{
					updateExperience(nbt);
					useRarity(event, stack, nbt);
					useAbilities(event, player, enemy, nbt);
					updateLevel(player, stack, nbt);
				}
			}
		}
	}
	
	/**
	 * Called everytime an enemy is hurt. Used to add experience to weapons dealing damage.
	 * @param nbt
	 */
	private void updateExperience(NBTTagCompound nbt)
	{
		if (Experience.getLevel(nbt) < ConfigHandler.MAX_LEVEL)
		{
			boolean isDev = (Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");
			
			if (isDev)
				Experience.setExperience(nbt, Experience.getExperience(nbt) + 100);
			else
				Experience.setExperience(nbt, Experience.getExperience(nbt) + 1);
		}
	}
	
	/**
	 * Called everytime an enemy is hurt. Used to add rarity bonuses, such as dealing more damage or adding more experience.
	 * @param nbt
	 */
	private void useRarity(LivingHurtEvent event, ItemStack stack, NBTTagCompound nbt)
	{
		Rarity rarity = Rarity.getRarity(nbt);
		float damageMultiplier = 1.0F;
		
		if (rarity != Rarity.DEFAULT)
		{
			int var; // damage multiplier chance
			int var1; // bonus experience chance
			int var2; // bonus experience amount
			int var3; // durability mitigation chance
			int var4; // durability mitigated amount
			
			// damage boosts and bonus experience
			switch (rarity)
			{
				// 5% chance of dealing 1.25x damage and 5% chance of gaining additional points of experience
				case UNCOMMON:
					var = (int) (Math.random() * 20);
					var1 = (int) (Math.random() * 20);
					var2 = (int) (Math.random() * 1);
					if (var == 0) damageMultiplier = 1.25F;
					if (var1 == 0) Experience.setExperience(nbt, Experience.getExperience(nbt) + var2);
					// durability
					var3 = (int) (Math.random() * 20);
					var4 = (int) (Math.random() * 1);
					if (var3 == 0) stack.setItemDamage(stack.getItemDamage() - var4);
					break;
				// 7.7% chance of dealing 1.5x damage and 7.7% chance of gaining additional points of experience
				case RARE:
					var = (int) (Math.random() * 13);
					var1 = (int) (Math.random() * 13);
					var2 = (int) (Math.random() * 2);
					if (var == 0) damageMultiplier = 1.5F;
					if (var1 == 0) Experience.setExperience(nbt, Experience.getExperience(nbt) + var2);
					// durability
					var3 = (int) (Math.random() * 13);
					var4 = (int) (Math.random() * 2);
					if (var3 == 0) stack.setItemDamage(stack.getItemDamage() - var4);
					break;
				// 10% chance of dealing 2x damage and 10% chance of gaining additional points of experience
				case ULTRA_RARE:
					var = (int) (Math.random() * 10);
					var1 = (int) (Math.random() * 10);
					var2 = (int) (Math.random() * 3);
					if (var == 0) damageMultiplier = 2F;
					if (var1 == 0) Experience.setExperience(nbt, Experience.getExperience(nbt) + var2);
					// durability
					var3 = (int) (Math.random() * 10);
					var4 = (int) (Math.random() * 3);
					if (var3 == 0) stack.setItemDamage(stack.getItemDamage() - var4);
					break;
				// 14% chance of dealing 2.5x damage and 14% chance of gaining additional points of experience
				case LEGENDARY:
					var = (int) (Math.random() * 7);
					var1 = (int) (Math.random() * 7);
					var2 = (int) (Math.random() * 5);
					if (var == 0) damageMultiplier = 2.5F;
					if (var1 == 0) Experience.setExperience(nbt, Experience.getExperience(nbt) + var2);
					// durability
					var3 = (int) (Math.random() * 7);
					var4 = (int) (Math.random() * 5);
					if (var3 == 0) stack.setItemDamage(stack.getItemDamage() - var4);
					break;
				// 20% chance of dealing 3x damage and 20% chance of gaining additional points of experience
				case ARCHAIC:
					var = (int) (Math.random() * 5);
					var1 = (int) (Math.random() * 5);
					var2 = (int) (Math.random() * 10);
					if (var == 0) damageMultiplier = 3F;
					if (var1 == 0) Experience.setExperience(nbt, Experience.getExperience(nbt) + var2);
					// durability
					var3 = (int) (Math.random() * 5);
					var4 = (int) (Math.random() * 10);
					if (var3 == 0) stack.setItemDamage(stack.getItemDamage() - var4);
					break;
				default:
					break;
			}
			
			event.setAmount(event.getAmount() * damageMultiplier);
		}
	}
	
	/**
	 * Called everytime an enemy is hurt. Used to use the current abilities a weapon might have.
	 * @param event
	 * @param player
	 * @param enemy
	 * @param nbt
	 */
	private void useAbilities(LivingHurtEvent event, EntityPlayer player, EntityLivingBase enemy, NBTTagCompound nbt)
	{
		if (enemy != null)
		{
			// tier 1
			if (Ability.FIRE.hasAbility(nbt) && (int) (Math.random() * 4) == 0)
			{
				double multiplier = Ability.FIRE.getMultiplier(Ability.FIRE.getLevel(nbt));
				enemy.setFire((int) (4 * multiplier));
			}
			
			if (Ability.FROST.hasAbility(nbt) && (int) (Math.random() * 4) == 0)
			{
				double multiplier = Ability.FROST.getMultiplier(Ability.FROST.getLevel(nbt));
				enemy.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, (int) (20 * (2 * multiplier)), 10));
			}
			
			if (Ability.POISON.hasAbility(nbt) && (int) (Math.random() * 4) == 0)
			{
				double multiplier = Ability.POISON.getMultiplier(Ability.POISON.getLevel(nbt));
				enemy.addPotionEffect(new PotionEffect(MobEffects.POISON, (int) (20 * (7 * multiplier)), Ability.POISON.getLevel(nbt)));
			}
			
			// tier 2
			if (Ability.LIGHT.hasAbility(nbt) && (int) (Math.random() * 7) == 0)
			{
				double multiplier = Ability.LIGHT.getMultiplier(Ability.LIGHT.getLevel(nbt));
				enemy.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, (int) (20 * (4 * multiplier)), Ability.LIGHT.getLevel(nbt)));
				enemy.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, (int) (20 * (4 * multiplier)), Ability.LIGHT.getLevel(nbt)));
			}
			
			if (Ability.BLOODLUST.hasAbility(nbt) && (int) (Math.random() * 7) == 0)
			{
				double multiplier = Ability.BLOODLUST.getMultiplier(Ability.BLOODLUST.getLevel(nbt));
				enemy.addPotionEffect(new PotionEffect(MobEffects.WITHER, (int) (20 * (4 * multiplier)), Ability.BLOODLUST.getLevel(nbt)));
			}
			
			if (Ability.ETHEREAL.hasAbility(nbt) && (int) (Math.random() * 7) == 0)
			{
				double multiplier = Ability.ETHEREAL.getMultiplier(Ability.ETHEREAL.getLevel(nbt));
				float health = (float) (player.getHealth() + (event.getAmount() / (4 / multiplier)));
				player.setHealth(health);
			}
			
			// tier 3
			if (Ability.CHAINED.hasAbility(nbt) && (int) (Math.random() * 10) == 0)
			{
				double multiplier = Ability.CHAINED.getMultiplier(Ability.CHAINED.getLevel(nbt));
				double radius = 10 * multiplier;
				World world = enemy.getEntityWorld();
				List<EntityMob> entityList = world.getEntitiesWithinAABB(EntityMob.class, new AxisAlignedBB(player.posX - radius, player.posY - radius, player.posZ - radius, player.posX + radius, player.posY + radius, player.posZ + radius));
				Iterator<EntityMob> iterator = entityList.iterator();
				
				while (iterator.hasNext())
				{
                    Entity entity = (Entity) iterator.next();
					
					if (entity instanceof EntityLivingBase)
					{
						entity.attackEntityFrom(DamageSource.causePlayerDamage(player), event.getAmount());
					}
				}
			}
			
			if (Ability.VOID.hasAbility(nbt) && (int) (Math.random() * 20) == 0)
			{
				float multiplier = 0F;
				
				if (Ability.VOID.getLevel(nbt) == 1) multiplier = 0.4F;
				else if (Ability.VOID.getLevel(nbt) == 2) multiplier = 0.6F;
				else if (Ability.VOID.getLevel(nbt) == 3) multiplier = 0.8F;

				float damage = enemy.getMaxHealth() * multiplier;
				event.setAmount(damage);
			}
		}
	}
	
	/**
	 * Called everytime an enemy is hurt. Used to check whether or not the weapon should level up.
	 * @param player
	 * @param stack
	 * @param nbt
	 */
	private void updateLevel(EntityPlayer player, ItemStack stack, NBTTagCompound nbt)
	{
		int level = Experience.getNextLevel(player, stack, nbt, Experience.getLevel(nbt), Experience.getExperience(nbt));
		Experience.setLevel(nbt, level);
		
		NBTHelper.saveStackNBT(stack, nbt);
	}
}

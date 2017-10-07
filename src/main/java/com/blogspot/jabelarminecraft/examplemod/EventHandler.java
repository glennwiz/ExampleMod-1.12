/**
    Copyright (C) 2017 by jabelar

    This file is part of jabelar's Minecraft Forge modding examples; as such,
    you can redistribute it and/or modify it under the terms of the GNU
    General Public License as published by the Free Software Foundation,
    either version 3 of the License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
    GNU General Public License for more details.

    For a copy of the GNU General Public License see <http://www.gnu.org/licenses/>.
*/

package com.blogspot.jabelarminecraft.examplemod;

import java.awt.Color;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.blogspot.jabelarminecraft.examplemod.client.gui.GuiCompactor;
import com.blogspot.jabelarminecraft.examplemod.init.ModBlocks;
import com.blogspot.jabelarminecraft.examplemod.init.ModItems;
import com.blogspot.jabelarminecraft.examplemod.init.ModMaterials;
import com.blogspot.jabelarminecraft.examplemod.items.IExtendedReach;
import com.blogspot.jabelarminecraft.examplemod.networking.MessageExtendedReachAttack;
import com.blogspot.jabelarminecraft.examplemod.utilities.Utilities;
import com.google.common.base.Objects;

import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.enchantment.EnchantmentFrostWalker;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.SPacketEntityEquipment;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.world.WorldServer;
import net.minecraftforge.client.event.EntityViewRenderEvent.FogColors;
import net.minecraftforge.client.event.EntityViewRenderEvent.FogDensity;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.LoadFromFile;
import net.minecraftforge.event.entity.player.PlayerEvent.NameFormat;
import net.minecraftforge.event.entity.player.PlayerEvent.SaveToFile;
import net.minecraftforge.fluids.FluidEvent.FluidDrainingEvent;
import net.minecraftforge.fluids.FluidEvent.FluidFillingEvent;
import net.minecraftforge.fluids.FluidRegistry.FluidRegisterEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

// TODO: Auto-generated Javadoc
@Mod.EventBusSubscriber(modid = MainMod.MODID)
public class EventHandler 
{
	/*
	 * Registry events
	 */
	
  /**
	 * On event.
	 *
	 * @param event the event
	 */
	@SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
  public static void onEvent(RegistryEvent.NewRegistry event)
  {
	  // can create registries here if needed
  }
	
    /*
     * Miscellaneous events
     */    

//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(ForceChunkEvent event)
//    {
//        
//    }
//
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(UnforceChunkEvent event)
//    {
//        
//    }
//
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(AnvilUpdateEvent event)
//    {
//        
//    }
//
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(CommandEvent event)
//    {
//        
//    }
//
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(ServerChatEvent event)
//    {
//        
//    }
//    
//    /*
//     * Brewing events
//     */
//        
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(PotionBrewedEvent event)
//    {
//        
//    }
//    
//    /*
//     * Entity related events
//     */
//    
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(EnteringChunk event)
//    {
//        
//    }
//
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(EntityConstructing event)
//    {
//        // Register extended entity properties
//    }
//    
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(EntityJoinWorldEvent event)
//    {
//
//    }
//    
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(EntityStruckByLightningEvent event)
//    {
//        
//    }
//    
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(PlaySoundAtEntityEvent event)
//    {
//        
//    }
//
//    /*
//     * Item events (these extend EntityEvent)
//     */
//    
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(ItemExpireEvent event)
//    {
//        
//    }
//
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(ItemTossEvent event)
//    {
//        
//    }
//    
//    /*
//     * Living events (extend EntityEvent)
//     */
//
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(LivingJumpEvent event)
//    {
//
//    }
//
	public static Field activeItemStackUseCount = ReflectionHelper.findField(EntityLivingBase.class, "activeItemStackUseCount", "field_184628_bn");
	public static Field handInventory = ReflectionHelper.findField(EntityLivingBase.class, "handInventory", "field_184630_bs");
	public static Field armorArray = ReflectionHelper.findField(EntityLivingBase.class, "armorArray", "field_184631_bt");
	public static Field ticksElytraFlying = ReflectionHelper.findField(EntityLivingBase.class, "ticksElytraFlying", "field_184629_bo");
	public static Field rideCooldown = ReflectionHelper.findField(Entity.class, "rideCooldown", "field_184245_j");
	public static Field portalCounter = ReflectionHelper.findField(Entity.class, "portalCounter", "field_82153_h");
	public static Field inPortal = ReflectionHelper.findField(Entity.class, "inPortal", "field_71087_bX");
	public static Field fire = ReflectionHelper.findField(Entity.class, "fire", "field_190534_ay");
	public static Field prevBlockpos = ReflectionHelper.findField(EntityLivingBase.class, "prevBlockpos", "field_184620_bC");
	public static Field firstUpdate = ReflectionHelper.findField(Entity.class, "firstUpdate", "field_70148_d");
	public static Field attackingPlayer = ReflectionHelper.findField(EntityLivingBase.class, "attackingPlayer", "field_70717_bb");
	public static Field recentlyHit = ReflectionHelper.findField(EntityLivingBase.class, "recentlyHit", "field_70718_bc");
	
	public static Method setFlag = ReflectionHelper.findMethod(Entity.class, "setFlag", "func_70052_a", Integer.TYPE, Boolean.TYPE); 
	public static Method getFlag = ReflectionHelper.findMethod(Entity.class, "getFlag", "func_70083_f", Integer.TYPE); 
	public static Method decrementTimeUntilPortal = ReflectionHelper.findMethod(Entity.class, "decrementTimeUntilPortal", "func_184173_H", new Class[] {});
	public static Method updatePotionEffects = ReflectionHelper.findMethod(EntityLivingBase.class, "updatePotionEffects", "func_70679_bo", new Class[] {});
	public static Method onDeathUpdate = ReflectionHelper.findMethod(EntityLivingBase.class, "onDeathUpdate", "func_70609_aI", new Class[] {});
	
    /**
     * In order to allow custom fluids to cause reduction of air supply
     * and drowning damage, need to copy almost entirety of vanilla functions
     * and modify the hard-coded WATER to accept other fluids.
     *
     * @param event the event
     * @throws IllegalArgumentException the illegal argument exception
     * @throws IllegalAccessException the illegal access exception
     */
    @SuppressWarnings("unchecked")
	@SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
    public static void onEvent(LivingUpdateEvent event) throws IllegalArgumentException, IllegalAccessException
    {
    	event.setCanceled(true);
    	
    	EntityLivingBase theEntity = event.getEntityLiving();
     	
        // super.onUpdate() expanded
        if (!theEntity.world.isRemote)
        {
            try {
				setFlag.invoke(theEntity, 6, theEntity.isGlowing());
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
        }

        // theEntity.onEntityUpdate();
        // onEntityUpdate() expanded
        theEntity.prevSwingProgress = theEntity.swingProgress;
        // super.onEntityUpdate();
        // super onEntityUpdage() expanded
        theEntity.world.profiler.startSection("entityBaseTick");

        if (theEntity.isRiding() && theEntity.getRidingEntity().isDead)
        {
            theEntity.dismountRidingEntity();
        }

        if (rideCooldown.getInt(theEntity) > 0)
        {
        	rideCooldown.setInt(theEntity, rideCooldown.getInt(theEntity) - 1);
        }

        theEntity.prevDistanceWalkedModified = theEntity.distanceWalkedModified;
        theEntity.prevPosX = theEntity.posX;
        theEntity.prevPosY = theEntity.posY;
        theEntity.prevPosZ = theEntity.posZ;
        theEntity.prevRotationPitch = theEntity.rotationPitch;
        theEntity.prevRotationYaw = theEntity.rotationYaw;

        if (!theEntity.world.isRemote && theEntity.world instanceof WorldServer)
        {
            theEntity.world.profiler.startSection("portal");

            if (inPortal.getBoolean(theEntity))
            {
                MinecraftServer minecraftserver = theEntity.world.getMinecraftServer();

                if (minecraftserver.getAllowNether())
                {
                    if (!theEntity.isRiding())
                    {
                        int i = theEntity.getMaxInPortalTime();

                        portalCounter.setInt(theEntity, portalCounter.getInt(theEntity) + 1);
                        if (portalCounter.getInt(theEntity) >= i)
                        {
                            portalCounter.set(theEntity, i);
                            theEntity.timeUntilPortal = theEntity.getPortalCooldown();
                            int j;

                            if (theEntity.world.provider.getDimensionType().getId() == -1)
                            {
                                j = 0;
                            }
                            else
                            {
                                j = -1;
                            }

                            theEntity.changeDimension(j);
                        }
                    }

                    inPortal.set(theEntity, false);
                }
            }
            else
            {
                if (portalCounter.getInt(theEntity) > 0)
                {
                    portalCounter.setInt(theEntity, portalCounter.getModifiers() - 4);
                }

                if (portalCounter.getInt(theEntity) < 0)
                {
                    portalCounter.setInt(theEntity, 0);
                }
            }

            try {
				decrementTimeUntilPortal.invoke(theEntity, new Object[] {});
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
            theEntity.world.profiler.endSection();
        }

        theEntity.spawnRunningParticles();
        theEntity.handleWaterMovement();

        if (theEntity.world.isRemote)
        {
            theEntity.extinguish();
        }
        else if (fire.getInt(theEntity) > 0)
        {
            if (theEntity.isImmuneToFire())
            {
                fire.setInt(theEntity, fire.getInt(theEntity) - 4);

                if (fire.getInt(theEntity) < 0)
                {
                    theEntity.extinguish();
                }
            }
            else
            {
                if (fire.getInt(theEntity) % 20 == 0)
                {
                    theEntity.attackEntityFrom(DamageSource.ON_FIRE, 1.0F);
                }

                theEntity.setFire(fire.getInt(theEntity));
            }
        }

        if (theEntity.isInLava())
        {
        	// setOnFireFromLava expanded
            // theEntity.setOnFireFromLava();
            if (!theEntity.isImmuneToFire())
            {
                theEntity.attackEntityFrom(DamageSource.LAVA, 4.0F);
                theEntity.setFire(15);
            }

            theEntity.fallDistance *= 0.5F;
        }

        if (theEntity.posY < -64.0D)
        {
        	// onDeathUpdate expanded
            theEntity.attackEntityFrom(DamageSource.OUT_OF_WORLD, 4.0F);
        }

        if (!theEntity.world.isRemote)
        {
            try {
				setFlag.invoke(theEntity, 0, fire.getInt(theEntity) > 0);
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
        }

        firstUpdate.setBoolean(theEntity, false);
        theEntity.world.profiler.endSection();
        theEntity.world.profiler.startSection("livingEntityBaseTick");
        boolean flag = theEntity instanceof EntityPlayer;

        if (theEntity.isEntityAlive())
        {
            if (theEntity.isEntityInsideOpaqueBlock())
            {
                theEntity.attackEntityFrom(DamageSource.IN_WALL, 1.0F);
            }
            else if (flag && !theEntity.world.getWorldBorder().contains(theEntity.getEntityBoundingBox()))
            {
                double d0 = theEntity.world.getWorldBorder().getClosestDistance(theEntity) + theEntity.world.getWorldBorder().getDamageBuffer();

                if (d0 < 0.0D)
                {
                    double d1 = theEntity.world.getWorldBorder().getDamageAmount();

                    if (d1 > 0.0D)
                    {
                        theEntity.attackEntityFrom(DamageSource.IN_WALL, Math.max(1, MathHelper.floor(-d0 * d1)));
                    }
                }
            }
        }

        if (theEntity.isImmuneToFire() || theEntity.world.isRemote)
        {
            theEntity.extinguish();
        }

        boolean flag1 = flag && ((EntityPlayer)theEntity).capabilities.disableDamage;

        if (theEntity.isEntityAlive())
        {
        	/*
        	 * Modified this so that custom fluids can suffocate
        	 */
            if (!theEntity.isInsideOfMaterial(Material.WATER) && !theEntity.isInsideOfMaterial(ModMaterials.SLIME))
            {
                theEntity.setAir(300);
            }
            else
            {
                if (!theEntity.canBreatheUnderwater() && !theEntity.isPotionActive(MobEffects.WATER_BREATHING) && !flag1)
                {
//                	// DEBUG
//                	Debug.print("Entity "+theEntity.getName()+" is drowning in fluid");
                	
                	// decreaseAirSupply() expanded
                    theEntity.setAir(EnchantmentHelper.getRespirationModifier(theEntity) > 0 && theEntity.getRNG().nextInt(EnchantmentHelper.getRespirationModifier(theEntity) + 1) > 0 ? theEntity.getAir() : theEntity.getAir() - 1);

                    if (theEntity.getAir() == -20)
                    {
                        theEntity.setAir(0);

                        for (int i = 0; i < 8; ++i)
                        {
                            float f2 = theEntity.getRNG().nextFloat() - theEntity.getRNG().nextFloat();
                            float f = theEntity.getRNG().nextFloat() - theEntity.getRNG().nextFloat();
                            float f1 = theEntity.getRNG().nextFloat() - theEntity.getRNG().nextFloat();
                            theEntity.world.spawnParticle(EnumParticleTypes.WATER_BUBBLE, theEntity.posX + f2, theEntity.posY + f, theEntity.posZ + f1, theEntity.motionX, theEntity.motionY, theEntity.motionZ);
                        }

                        theEntity.attackEntityFrom(DamageSource.DROWN, 2.0F);
                    }
                }

                if (!theEntity.world.isRemote && theEntity.isRiding() && theEntity.getRidingEntity() != null && theEntity.getRidingEntity().shouldDismountInWater(theEntity))
                {
                    theEntity.dismountRidingEntity();
                }
            }

            if (!theEntity.world.isRemote)
            {
                BlockPos blockpos = new BlockPos(theEntity);

                if (!Objects.equal(prevBlockpos.get(theEntity), blockpos))
                {
                    prevBlockpos.set(theEntity, blockpos);
                    // theEntity.frostWalk(blockpos);
                    // frostWalk() expanded
                    int i = EnchantmentHelper.getMaxEnchantmentLevel(Enchantments.FROST_WALKER, theEntity);

                    if (i > 0)
                    {
                        EnchantmentFrostWalker.freezeNearby(theEntity, theEntity.world, blockpos, i);
                    }
                }
            }
        }

        if (theEntity.isEntityAlive() && theEntity.isWet())
        {
            theEntity.extinguish();
        }

        theEntity.prevCameraPitch = theEntity.cameraPitch;

        if (theEntity.hurtTime > 0)
        {
            --theEntity.hurtTime;
        }

        if (theEntity.hurtResistantTime > 0 && !(theEntity instanceof EntityPlayerMP))
        {
            --theEntity.hurtResistantTime;
        }

        if (theEntity.getHealth() <= 0.0F)
        {
            try {
				onDeathUpdate.invoke(theEntity, new Object[] {});
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
        }

        if (recentlyHit.getInt(theEntity) > 0)
        {
            recentlyHit.setInt(theEntity, recentlyHit.getInt(theEntity) - 1);;
        }
        else
        {
            attackingPlayer.set(theEntity, null);
        }

        if (theEntity.getLastAttackedEntity() != null && !theEntity.getLastAttackedEntity().isEntityAlive())
        {
            attackingPlayer.set(theEntity, null);
        }

        if (theEntity.getRevengeTarget() != null)
        {
            if (!theEntity.getRevengeTarget().isEntityAlive())
            {
                theEntity.setRevengeTarget((EntityLivingBase)null);
            }
            else if (theEntity.ticksExisted - theEntity.getRevengeTimer() > 100)
            {
                theEntity.setRevengeTarget((EntityLivingBase)null);
            }
        }

        try {
			updatePotionEffects.invoke(theEntity, new Object[] {});
		} catch (InvocationTargetException e1) {
			e1.printStackTrace();
		}
        // theEntity.prevMovedDistance = theEntity.movedDistance;
        theEntity.prevRenderYawOffset = theEntity.renderYawOffset;
        theEntity.prevRotationYawHead = theEntity.rotationYawHead;
        theEntity.prevRotationYaw = theEntity.rotationYaw;
        theEntity.prevRotationPitch = theEntity.rotationPitch;
        theEntity.world.profiler.endSection();

        // updateActiveHand() method expanded
        if (theEntity.isHandActive())
        {
            ItemStack itemstack = theEntity.getHeldItem(theEntity.getActiveHand());

            if (itemstack == theEntity.getActiveItemStack())
            {
                if (!theEntity.getActiveItemStack().isEmpty())
                {

                    try {
						activeItemStackUseCount.setInt(theEntity, net.minecraftforge.event.ForgeEventFactory.onItemUseTick(theEntity, theEntity.getActiveItemStack(), activeItemStackUseCount.getInt(theEntity)));
					} catch (IllegalArgumentException | IllegalAccessException e) {
						e.printStackTrace();
					}
                    try {
						if (activeItemStackUseCount.getInt(theEntity) > 0)
						    theEntity.getActiveItemStack().getItem().onUsingTick(theEntity.getActiveItemStack(), theEntity, activeItemStackUseCount.getInt(theEntity));
					} catch (IllegalArgumentException | IllegalAccessException e) {
						e.printStackTrace();
					}
                }

                if (theEntity.getItemInUseCount() <= 25 && theEntity.getItemInUseCount() % 4 == 0)
                {
                    // theEntity.updateItemUse(theEntity.getActiveItemStack(), 5);
                    // updateItemUse() expanded
                    ItemStack stack = theEntity.getActiveItemStack();
                    int eatingParticleCount = 5;
                    if (!stack.isEmpty() && theEntity.isHandActive())
                    {
                        if (stack.getItemUseAction() == EnumAction.DRINK)
                        {
                            theEntity.playSound(SoundEvents.ENTITY_GENERIC_DRINK, 0.5F, theEntity.world.rand.nextFloat() * 0.1F + 0.9F);
                        }

                        if (stack.getItemUseAction() == EnumAction.EAT)
                        {
                            for (int i = 0; i < eatingParticleCount; ++i)
                            {
                                Vec3d vec3d = new Vec3d((theEntity.world.rand.nextFloat() - 0.5D) * 0.1D, Math.random() * 0.1D + 0.1D, 0.0D);
                                vec3d = vec3d.rotatePitch(-theEntity.rotationPitch * 0.017453292F);
                                vec3d = vec3d.rotateYaw(-theEntity.rotationYaw * 0.017453292F);
                                double d0 = (-theEntity.world.rand.nextFloat()) * 0.6D - 0.3D;
                                Vec3d vec3d1 = new Vec3d((theEntity.world.rand.nextFloat() - 0.5D) * 0.3D, d0, 0.6D);
                                vec3d1 = vec3d1.rotatePitch(-theEntity.rotationPitch * 0.017453292F);
                                vec3d1 = vec3d1.rotateYaw(-theEntity.rotationYaw * 0.017453292F);
                                vec3d1 = vec3d1.addVector(theEntity.posX, theEntity.posY + theEntity.getEyeHeight(), theEntity.posZ);

                                if (stack.getHasSubtypes())
                                {
                                    theEntity.world.spawnParticle(EnumParticleTypes.ITEM_CRACK, vec3d1.x, vec3d1.y, vec3d1.z, vec3d.x, vec3d.y + 0.05D, vec3d.z, Item.getIdFromItem(stack.getItem()), stack.getMetadata());
                                }
                                else
                                {
                                    theEntity.world.spawnParticle(EnumParticleTypes.ITEM_CRACK, vec3d1.x, vec3d1.y, vec3d1.z, vec3d.x, vec3d.y + 0.05D, vec3d.z, Item.getIdFromItem(stack.getItem()));
                                }
                            }

                            theEntity.playSound(SoundEvents.ENTITY_GENERIC_EAT, 0.5F + 0.5F * theEntity.world.rand.nextInt(2), (theEntity.world.rand.nextFloat() - theEntity.world.rand.nextFloat()) * 0.2F + 1.0F);
                        }
                    }                 
                }
                try {
					activeItemStackUseCount.setInt(theEntity, activeItemStackUseCount.getInt(theEntity) - 1);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
                try {
					if (activeItemStackUseCount.getInt(theEntity) - 1 <= 0 && !theEntity.world.isRemote)
					{
					    // theEntity.onItemUseFinish();
						// onITemUseFinish() expanded
					    if (!theEntity.getActiveItemStack().isEmpty() && theEntity.isHandActive())
					    {
					    	{
					    		// theEntity.updateItemUse(theEntity.getActiveItemStack(), 16);
					    		// updateItemUse expanded
					            ItemStack stack = theEntity.getActiveItemStack();
					            int eatingParticleCount = 16;
					            if (!stack.isEmpty() && theEntity.isHandActive())
					            {
					                if (stack.getItemUseAction() == EnumAction.DRINK)
					                {
					                    theEntity.playSound(SoundEvents.ENTITY_GENERIC_DRINK, 0.5F, theEntity.world.rand.nextFloat() * 0.1F + 0.9F);
					                }

					                if (stack.getItemUseAction() == EnumAction.EAT)
					                {
					                    for (int i = 0; i < eatingParticleCount; ++i)
					                    {
					                        Vec3d vec3d = new Vec3d((theEntity.world.rand.nextFloat() - 0.5D) * 0.1D, Math.random() * 0.1D + 0.1D, 0.0D);
					                        vec3d = vec3d.rotatePitch(-theEntity.rotationPitch * 0.017453292F);
					                        vec3d = vec3d.rotateYaw(-theEntity.rotationYaw * 0.017453292F);
					                        double d0 = (-theEntity.world.rand.nextFloat()) * 0.6D - 0.3D;
					                        Vec3d vec3d1 = new Vec3d((theEntity.world.rand.nextFloat() - 0.5D) * 0.3D, d0, 0.6D);
					                        vec3d1 = vec3d1.rotatePitch(-theEntity.rotationPitch * 0.017453292F);
					                        vec3d1 = vec3d1.rotateYaw(-theEntity.rotationYaw * 0.017453292F);
					                        vec3d1 = vec3d1.addVector(theEntity.posX, theEntity.posY + theEntity.getEyeHeight(), theEntity.posZ);

					                        if (stack.getHasSubtypes())
					                        {
					                            theEntity.world.spawnParticle(EnumParticleTypes.ITEM_CRACK, vec3d1.x, vec3d1.y, vec3d1.z, vec3d.x, vec3d.y + 0.05D, vec3d.z, Item.getIdFromItem(stack.getItem()), stack.getMetadata());
					                        }
					                        else
					                        {
					                            theEntity.world.spawnParticle(EnumParticleTypes.ITEM_CRACK, vec3d1.x, vec3d1.y, vec3d1.z, vec3d.x, vec3d.y + 0.05D, vec3d.z, Item.getIdFromItem(stack.getItem()));
					                        }
					                    }

					                    theEntity.playSound(SoundEvents.ENTITY_GENERIC_EAT, 0.5F + 0.5F * theEntity.world.rand.nextInt(2), (theEntity.world.rand.nextFloat() - theEntity.world.rand.nextFloat()) * 0.2F + 1.0F);
					                }
					            }                 

					    	}
					    	ItemStack itemstack2 = theEntity.getActiveItemStack().onItemUseFinish(theEntity.world, theEntity);
					        itemstack2 = net.minecraftforge.event.ForgeEventFactory.onItemUseFinish(theEntity, theEntity.getActiveItemStack(), theEntity.getItemInUseCount(), itemstack2);
					        theEntity.setHeldItem(theEntity.getActiveHand(), itemstack2);
					        theEntity.resetActiveHand();
					    }

					}
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
            }
            else
            {
                theEntity.resetActiveHand();
            }
        }


        if (!theEntity.world.isRemote)
        {
            int i = theEntity.getArrowCountInEntity();

            if (i > 0)
            {
                if (theEntity.arrowHitTimer <= 0)
                {
                    theEntity.arrowHitTimer = 20 * (30 - i);
                }

                --theEntity.arrowHitTimer;

                if (theEntity.arrowHitTimer <= 0)
                {
                    theEntity.setArrowCountInEntity(i - 1);
                }
            }

            for (EntityEquipmentSlot entityequipmentslot : EntityEquipmentSlot.values())
            {
                ItemStack itemstack = ItemStack.EMPTY;

                switch (entityequipmentslot.getSlotType())
                {
                    case HAND:
					try {
						itemstack = ((NonNullList<ItemStack>)handInventory.get(theEntity)).get(entityequipmentslot.getIndex());
					} catch (IllegalArgumentException | IllegalAccessException e) {
						e.printStackTrace();
					}
                        break;
                    case ARMOR:
					try {
						itemstack = ((NonNullList<ItemStack>)armorArray.get(theEntity)).get(entityequipmentslot.getIndex());
					} catch (IllegalArgumentException | IllegalAccessException e) {
						e.printStackTrace();
					}
                        break;
                    default:
                        continue;
                }

                ItemStack itemstack1 = theEntity.getItemStackFromSlot(entityequipmentslot);

                if (!ItemStack.areItemStacksEqual(itemstack, itemstack1))
                {
                    if (!ItemStack.areItemStacksEqualUsingNBTShareTag(itemstack1, itemstack))
                    ((WorldServer)theEntity.world).getEntityTracker().sendToTracking(theEntity, new SPacketEntityEquipment(theEntity.getEntityId(), entityequipmentslot, itemstack1));
                    net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(new net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent(theEntity, entityequipmentslot, itemstack, itemstack1));

                    if (!itemstack.isEmpty())
                    {
                        theEntity.getAttributeMap().removeAttributeModifiers(itemstack.getAttributeModifiers(entityequipmentslot));
                    }

                    if (!itemstack1.isEmpty())
                    {
                        theEntity.getAttributeMap().applyAttributeModifiers(itemstack1.getAttributeModifiers(entityequipmentslot));
                    }

                    switch (entityequipmentslot.getSlotType())
                    {
                        case HAND:
                            ((NonNullList<ItemStack>)handInventory.get(theEntity)).set(entityequipmentslot.getIndex(), itemstack1.isEmpty() ? ItemStack.EMPTY : itemstack1.copy());
                            break;
                        case ARMOR:
                            ((NonNullList<ItemStack>)armorArray.get(theEntity)).set(entityequipmentslot.getIndex(), itemstack1.isEmpty() ? ItemStack.EMPTY : itemstack1.copy());
                    }
                }
            }

            if (theEntity.ticksExisted % 20 == 0)
            {
                theEntity.getCombatTracker().reset();
            }

            if (!theEntity.isGlowing())
            {
                try {
					if (((boolean)getFlag.invoke(theEntity, 6)) != theEntity.isPotionActive(MobEffects.GLOWING))
					{
					    setFlag.invoke(theEntity, 6, theEntity.isPotionActive(MobEffects.GLOWING));
					}
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}
            }
        }

        theEntity.onLivingUpdate();
        double d0 = theEntity.posX - theEntity.prevPosX;
        double d1 = theEntity.posZ - theEntity.prevPosZ;
        float f3 = (float)(d0 * d0 + d1 * d1);
        float f4 = theEntity.renderYawOffset;
        float f5 = 0.0F;
        if (f3 > 0.0025000002F)
        {
            f5 = (float)Math.sqrt(f3) * 3.0F;
            float f1 = (float)MathHelper.atan2(d1, d0) * (180F / (float)Math.PI) - 90.0F;
            float f2 = MathHelper.abs(MathHelper.wrapDegrees(theEntity.rotationYaw) - f1);

            if (95.0F < f2 && f2 < 265.0F)
            {
                f4 = f1 - 180.0F;
            }
            else
            {
                f4 = f1;
            }
        }

        if (theEntity.swingProgress > 0.0F)
        {
            f4 = theEntity.rotationYaw;
        }

        if (!theEntity.onGround)
        {
        }

        // theEntity.onGroundSpeedFactor += (f - theEntity.onGroundSpeedFactor) * 0.3F;
        theEntity.world.profiler.startSection("headTurn");
        // f5 = theEntity.updateDistance(f4, f5);
        // updateDistance expanded
        float p_110146_1_ = f4;
        float p_110146_2_ = f5;
        theEntity.renderYawOffset += MathHelper.wrapDegrees(p_110146_1_ - theEntity.renderYawOffset) * 0.3F;
        float f1 = MathHelper.wrapDegrees(theEntity.rotationYaw - theEntity.renderYawOffset);
        boolean flagx = f1 < -90.0F || f1 >= 90.0F;

        if (f1 < -75.0F)
        {
            f1 = -75.0F;
        }

        if (f1 >= 75.0F)
        {
            f1 = 75.0F;
        }

        theEntity.renderYawOffset = theEntity.rotationYaw - f1;

        if (f1 * f1 > 2500.0F)
        {
            theEntity.renderYawOffset += f1 * 0.2F;
        }

        if (flagx)
        {
            p_110146_2_ *= -1.0F;
        }

        f5 = p_110146_2_;

        theEntity.world.profiler.endSection();
        theEntity.world.profiler.startSection("rangeChecks");

        while (theEntity.rotationYaw - theEntity.prevRotationYaw < -180.0F)
        {
            theEntity.prevRotationYaw -= 360.0F;
        }

        while (theEntity.rotationYaw - theEntity.prevRotationYaw >= 180.0F)
        {
            theEntity.prevRotationYaw += 360.0F;
        }

        while (theEntity.renderYawOffset - theEntity.prevRenderYawOffset < -180.0F)
        {
            theEntity.prevRenderYawOffset -= 360.0F;
        }

        while (theEntity.renderYawOffset - theEntity.prevRenderYawOffset >= 180.0F)
        {
            theEntity.prevRenderYawOffset += 360.0F;
        }

        while (theEntity.rotationPitch - theEntity.prevRotationPitch < -180.0F)
        {
            theEntity.prevRotationPitch -= 360.0F;
        }

        while (theEntity.rotationPitch - theEntity.prevRotationPitch >= 180.0F)
        {
            theEntity.prevRotationPitch += 360.0F;
        }

        while (theEntity.rotationYawHead - theEntity.prevRotationYawHead < -180.0F)
        {
            theEntity.prevRotationYawHead -= 360.0F;
        }

        while (theEntity.rotationYawHead - theEntity.prevRotationYawHead >= 180.0F)
        {
            theEntity.prevRotationYawHead += 360.0F;
        }

        theEntity.world.profiler.endSection();
        // theEntity.movedDistance += f5;

        if (theEntity.isElytraFlying())
        {
        	try {
				ticksElytraFlying.setInt(theEntity, ticksElytraFlying.getInt(theEntity) + 1);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
        }
        else
        {
            try {
				ticksElytraFlying.setInt(theEntity, 0);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
        }
    }
//
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(EnderTeleportEvent event)
//    {
//        
//    }
//
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(LivingAttackEvent event)
//    {
//
//    }
//
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(LivingDeathEvent event)
//    {
//        
//    }

    /**
     * On event.
     *
     * @param event the event
     */
    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
    public static void onEvent(LivingDropsEvent event)
    {
		for (EntityItem dropItem: event.getDrops())
		{
			if (dropItem.getItem().getItem() == Items.LEATHER)
			{
				int stackSize = dropItem.getItem().getCount();

				if (event.getEntityLiving() instanceof EntityCow)
				{
					dropItem.setItem(new ItemStack(ModItems.COW_HIDE, stackSize));
				}
				if (event.getEntityLiving() instanceof EntityHorse)
				{
					dropItem.setItem(new ItemStack(ModItems.HORSE_HIDE, stackSize));
				}
				if (event.getEntityLiving() instanceof EntityMooshroom)
				{
					dropItem.setItem(new ItemStack(ModItems.COW_HIDE, stackSize));
				}
			}
    	}
    	
		if (event.getEntityLiving() instanceof EntityPig)
		{
			event.getDrops().add(new EntityItem(
			        event.getEntityLiving().world, 
			        event.getEntityLiving().posX, event.getEntityLiving().posY, event.getEntityLiving().posZ, 
					new ItemStack(ModItems.PIG_SKIN)));
		}
		else if (event.getEntityLiving() instanceof EntitySheep)
		{
			event.getDrops().add(new EntityItem(
			        event.getEntityLiving().world, 
			        event.getEntityLiving().posX, event.getEntityLiving().posY, event.getEntityLiving().posZ, 
					new ItemStack(ModItems.SHEEP_SKIN)));
		}
    }
    
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(LivingFallEvent event)
//    {
//
//    }
//
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(LivingHurtEvent event)
//    {
//
//    }
//
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(LivingPackSizeEvent event)
//    {
//        
//    }
//
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(LivingSetAttackTargetEvent event)
//    {
//        
//    }
//
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(ZombieEvent event)
//    {
//        
//    }
//    
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(CheckSpawn event)
//    {  	
//
//    }
//    
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(SpecialSpawn event)
//    {
//        
//    }
//    
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(AllowDespawn event)
//    {
//        
//    }
//    
//    /*
//     * Player events (extend LivingEvent)
//     */
//    
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(BreakSpeed event)
//    {
//        
//    }
//    
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(Clone event)
//    {
//        
//    }
//    
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(HarvestCheck event)
//    {
//        
//    }
    
    /**
	 * On event.
	 *
	 * @param event the event
	 */
    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
    public static void onEvent(NameFormat event)
    {
    	// DEBUG
    	System.out.println("NameFormat event for username = "+event.getUsername());
        if (event.getUsername().equalsIgnoreCase("jnaejnae"))
        {
            event.setDisplayname(event.getUsername()+" the Great and Powerful");
        }        
        else if (event.getUsername().equalsIgnoreCase("MistMaestro"))
        {
            event.setDisplayname(event.getUsername()+" the Wise");
        }    
        else if (event.getUsername().equalsIgnoreCase("Taliaailat"))
        {
            event.setDisplayname(event.getUsername()+" the Beautiful");
        }    
        else
        {
            event.setDisplayname(event.getUsername()+" the Ugly");            
        }
    }
    
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(ArrowLooseEvent event)
//    {
//        
//    }
//    
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(ArrowNockEvent event)
//    {
//        
//    }
//    
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(AttackEntityEvent event)
//    {
//
//    }
//    
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(BonemealEvent event)
//    {
//        
//    }
//    
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(EntityInteractEvent event)
//    {
//
//    }
//    
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(EntityItemPickupEvent event)
//    {
//
//    }
//    
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(FillBucketEvent event)
//    {
//        
//    }
//    
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(ItemTooltipEvent event)
//    {
//        
//    }
//    
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(PlayerDestroyItemEvent event)
//    {
//        
//    }
//    
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(PlayerDropsEvent event)
//    {
//        
//    }
//    
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(PlayerFlyableFallEvent event)
//    {
//        
//    }
//    
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(PlayerInteractEvent event)
//    {
//
//    }
//    
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(PlayerOpenContainerEvent event)
//    {
//        
//    }
//    
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(PlayerPickupXpEvent event)
//    {
//        
//    }
//    
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(PlayerSleepInBedEvent event)
//    {
//        
//    }
//    
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(PlayerUseItemEvent.Finish event)
//    {
//        
//    }
//    
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(PlayerUseItemEvent.Start event)
//    {
//
//    }
//    
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(PlayerUseItemEvent.Stop event)
//    {
//        
//    }
//    
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(PlayerUseItemEvent.Tick event)
//    {
//        
//    }
//    
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(UseHoeEvent event)
//    {
//        
//    }
//    
//    /*
//     * Minecart events (extends EntityEvent)
//     */
//    
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(MinecartCollisionEvent event)
//    {
//        
//    }
//    
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(MinecartInteractEvent event)
//    {
//        
//    }
//    
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(MinecartUpdateEvent event)
//    {
//        
//    }
//    
//    /*
//     * World events
//     */
//
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(WorldEvent.Load event)
//    {
//        
//    }
//
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(WorldEvent.PotentialSpawns event)
//    {
//        
//    }
//
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(WorldEvent.Unload event)
//    {
//        
//    }
//
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(BlockEvent event)
//    {
//        
//    }
//
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(BlockEvent.BreakEvent event)
//    {
//        
//    }
//
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(BlockEvent.HarvestDropsEvent event)
//    {
//        
//    }
//    
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(ChunkEvent event)
//    {
//        
//    }
//    
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(ChunkEvent.Save event)
//    {
//        
//    }
//    
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(ChunkEvent.Unload event)
//    {
//        
//    }
//    
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(ChunkDataEvent event)
//    {
//        
//    }
//    
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(ChunkDataEvent.Load event)
//    {
//        
//    }
//    
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(ChunkDataEvent.Save event)
//    {
//        
//    }
//    
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(ChunkWatchEvent event)
//    {
//        
//    }
//    
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(ChunkWatchEvent.Watch event)
//    {
//        
//    }
//    
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(ChunkWatchEvent.UnWatch event)
//    {
//        
//    }
//
//
//    /*
//     * Client events
//     */    
//
//    @SideOnly(Side.CLIENT)
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(ClientChatReceivedEvent event)
//    {
//        
//    }
//
//    @SideOnly(Side.CLIENT)
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(DrawBlockHighlightEvent event)
//    {
//        
//    }
//
//    @SideOnly(Side.CLIENT)
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(RenderFogEvent event)
//    {
//        
//    }
    
    /**
	 * Use fog density to create the effect of being under custom fluid, similar
	 * to how being under water does it.
	 *
	 * @param event the event
	 */
    @SideOnly(Side.CLIENT)
    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
    public static void onEvent(FogDensity event)
    {
		// EntityPlayer thePlayer = Minecraft.getMinecraft().player;
		
		if (event.getEntity().isInsideOfMaterial(ModMaterials.SLIME))
		{
			event.setDensity(0.5F);
		}	
		else
		{
			event.setDensity(0.0001F);
		}
		
		event.setCanceled(true); // must cancel event for event handler to take effect
    }

    
    /**
     * Use fog color to color the view when submerged in a custom fluid.
     *
     * @param event the event
     */
    @SideOnly(Side.CLIENT)
    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
    public static void onEvent(FogColors event)
    {
		if (event.getEntity().isInsideOfMaterial(ModMaterials.SLIME))
		{
			Color theColor = Color.GREEN;
			event.setRed(theColor.getRed());
	    	event.setGreen(theColor.getGreen());
	    	event.setBlue(theColor.getBlue());
		}			
	}

//    @SideOnly(Side.CLIENT)
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(FOVUpdateEvent event)
//    {
//        
//    }

    /**
 * On event.
 *
 * @param event the event
 */
@SideOnly(Side.CLIENT)
    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
    public static void onEvent(GuiOpenEvent event)
    { 
    	if (event.getGui() instanceof GuiCompactor)
    	{
    		// DEBUG
    		System.out.println("GuiOpenEvent for GuiCompactor");
    	}
//        if (event.getGui() instanceof GuiIngameMenu)
//        {
//            Debug.print("GuiOpenEvent for GuiIngameModOptions");
//            event.setGui(new GuiConfig(null));        
//        }
    }

//    @SideOnly(Side.CLIENT)
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(GuiScreenEvent.ActionPerformedEvent event)
//    {
//        
//    }
//
//    @SideOnly(Side.CLIENT)
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(GuiScreenEvent.DrawScreenEvent event)
//    {
//        
//    }
//
//    @SideOnly(Side.CLIENT)
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(GuiScreenEvent.InitGuiEvent event)
//    {
//        
//    }

    /**
	 * Process an extended reach weapon.
	 *
	 * @param event the event
	 */
	@SideOnly(Side.CLIENT)
    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
    public static void onEvent(MouseEvent event)
    { 
        if (event.getButton() == 0 && event.isButtonstate())
        {
            Minecraft mc = Minecraft.getMinecraft();
            EntityPlayer thePlayer = mc.player;
            if (thePlayer != null)
            {
                ItemStack itemstack = thePlayer.getHeldItemMainhand();
                IExtendedReach ieri;
                if (itemstack != null)
                {
                    if (itemstack.getItem() instanceof IExtendedReach)
                    {
                        ieri = (IExtendedReach) itemstack.getItem();
                    } else
                    {
                        ieri = null;
                    }
    
                    if (ieri != null)
                    {
                        float reach = ieri.getReach();
                        RayTraceResult mov = Utilities.getMouseOverExtended(reach); 
                        
                        if (mov != null)
                        {
                            if (mov.entityHit != null && mov.entityHit.hurtResistantTime == 0)
                            {
                                if (mov.entityHit != thePlayer )
                                {
                                    MainMod.network.sendToServer(new MessageExtendedReachAttack(mov.entityHit.getEntityId()));
                                }
                            }
                        }
                    }
                }
            }
        }
   }
    
    /**
     * Render the air indicator when submerged in a liquid.
     *
     * @param event the event
     */
    @SideOnly(Side.CLIENT)
    @SubscribeEvent(priority=EventPriority.HIGHEST, receiveCanceled=true)
    public static void onEvent(RenderGameOverlayEvent event)
    {
    	Minecraft mc = Minecraft.getMinecraft();
    	GuiIngame ingameGUI = mc.ingameGUI;
        ScaledResolution scaledRes = event.getResolution();
     	
       if (mc.getRenderViewEntity() instanceof EntityPlayer)
        {
            EntityPlayer entityplayer = (EntityPlayer)mc.getRenderViewEntity();

            int airIndicatorX = scaledRes.getScaledWidth() / 2 + 91;
            int airIndicatorBottom = scaledRes.getScaledHeight() - 39;
            int airIndicatorTop = airIndicatorBottom - 10;

            if (entityplayer.isInsideOfMaterial(Material.WATER) || entityplayer.isInsideOfMaterial(ModMaterials.SLIME))
            {
                int airAmount = mc.player.getAir();
                int airLostPercent = MathHelper.ceil((airAmount - 2) * 10.0D / 300.0D);
                int airLeftPercent = MathHelper.ceil(airAmount * 10.0D / 300.0D) - airLostPercent;

                for (int airUnitIndex = 0; airUnitIndex < airLostPercent + airLeftPercent; ++airUnitIndex)
                {
                    if (airUnitIndex < airLostPercent)
                    {
                        ingameGUI.drawTexturedModalRect(airIndicatorX - airUnitIndex * 8 - 9, airIndicatorTop, 16, 18, 9, 9);
                    }
                    else
                    {
                        ingameGUI.drawTexturedModalRect(airIndicatorX - airUnitIndex * 8 - 9, airIndicatorTop, 25, 18, 9, 9);
                    }
                }
            }
        }
    }        

//    
//    @SideOnly(Side.CLIENT)
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(RenderGameOverlayEvent.Chat event)
//    {
//    	// this event actually extends Pre
//
//    }
//    
//    @SideOnly(Side.CLIENT)
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(RenderGameOverlayEvent.Post event)
//    {
//        
//    }
//    
//    @SideOnly(Side.CLIENT)
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(RenderGameOverlayEvent.Pre event)
//    {
//    	// you can check which elements of the GUI are being rendered
//    	// by checking event.type against things like ElementType.CHAT, ElementType.CROSSHAIRS, etc.
//    	// Note that ElementType.All is fired first apparently, then individual elements
//    }
//    
//	@SideOnly(Side.CLIENT)
//	@SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//	public static void onEvent(RenderGameOverlayEvent.Pre event)
//	{
//		if (event.getType() == ElementType.ALL)
//		{
////			// DEBUG
////			Debug.print("render game overlay");
//			
//			EntityPlayer thePlayer = Minecraft.getMinecraft().player;
//			
//			if (thePlayer.isInsideOfMaterial(ModMaterials.SLIME))
//			{
////				// DEBUG
////				Debug.print("player is inside of material");
//				
//				drawFluidOverlay(ModFluids.SLIME.getColor(), 0.2F);
//			}			
//		}
//	}
	
//	@SideOnly(Side.CLIENT)
//	@SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//	public static void onEvent(RenderBlockOverlayEvent event)
//	{
//		if (event.getOverlayType() == OverlayType.WATER)
//		{
//			IBlockState theBlock = event.getBlockForOverlay();
//			if (theBlock instanceof ModBlockFluidClassic)
//			{
//				// DEBUG
//				Debug.print("rendering fluid overlay");
//				renderFluidOverlay(event.getRenderPartialTicks());
//			}
//		}
//	}
//
//    @SideOnly(Side.CLIENT)
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(RenderHandEvent event)
//    {
//        
//    }
//
//    @SideOnly(Side.CLIENT)
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(RenderLivingEvent.Post event)
//    {
//        
//    }
//
//    @SideOnly(Side.CLIENT)
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(RenderLivingEvent.Pre event)
//    {
//        
//    }
//
//    @SideOnly(Side.CLIENT)
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(RenderPlayerEvent.Post event)
//    {
//        
//    }
//
//    @SideOnly(Side.CLIENT)
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(RenderPlayerEvent.Pre event)
//    {
//
//    }
//
//    @SideOnly(Side.CLIENT)
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(RenderPlayerEvent.SetArmorModel event)
//    {
//        
//    }
//
//    @SideOnly(Side.CLIENT)
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(RenderWorldEvent.Post event)
//    {
//        
//    }
//
//    @SideOnly(Side.CLIENT)
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(RenderWorldEvent.Pre event)
//    {
//    	Minecraft.getMinecraft().gameSettings.thirdPersonView = 2;
//    }
//
//    @SideOnly(Side.CLIENT)
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(RenderWorldLastEvent event)
//    {
//        
//    }
//
//    @SideOnly(Side.CLIENT)
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(TextureStitchEvent.Post event)
//    {
//        
//    }
//
//    @SideOnly(Side.CLIENT)
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(TextureStitchEvent.Pre event)
//    {
//        
//    }
//    
//    /*
//     * Fluid events
//     */
//
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(FluidEvent event)
//    {
//        
//    }

//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//	public static void onEvent(FluidContainerRegisterEvent event)
//	{
//    	// DEBUG
//	    Debug.print("Registering fluid container");
//	}

    /**
 * On event.
 *
 * @param event the event
 */
@SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
    public static void onEvent(FluidDrainingEvent event)
    {
    	// DEBUG
    	System.out.println("On client = "+event.getWorld().isRemote+" Draining fluid = "+event.getFluid().getFluid()+" with amount = "+event.getAmount());     
    }

    /**
     * On event.
     *
     * @param event the event
     */
    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
    public static void onEvent(FluidFillingEvent event)
    {
    	// DEBUG
    	System.out.println("On client = "+event.getWorld().isRemote+" Filling fluid = "+event.getFluid().getFluid()+" with amount = "+event.getAmount());     
    }
//
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(FluidMotionEvent event)
//    {
//        
//    }

    /**
 * On event.
 *
 * @param event the event
 */
@SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
    public static void onEvent(FluidRegisterEvent event)
    {
    	// DEBUG
    	System.out.println("Registering fluid");
    }
//
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(FluidSpilledEvent event)
//    {
//        
//    }
//
//    /*
//     * Ore dictionary events
//     */
//
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(OreRegisterEvent event)
//    {
//        
//    }
//    
//	@SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//	public static void onEvent(PopulateChunkEvent event)
//	{
//		
//	}
//	
//	// for some reason the PopulateChunkEvents are fired on the main EVENT_BUT
//	// even though they are in the terraingen package
//	@SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//	public static void onEvent(PopulateChunkEvent.Populate event)
//	{
//		
//	}
//	
//	// for some reason the PopulateChunkEvents are fired on the main EVENT_BUT
//	// even though they are in the terraingen package
//	@SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//	public static void onEvent(PopulateChunkEvent.Post event)
//	{ 
//		
//	}
//	
//	// for some reason the PopulateChunkEvents are fired on the main EVENT_BUT
//	// even though they are in the terraingen package
//	@SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//	public static void onEvent(PopulateChunkEvent.Pre event)
//	{
//		
//	}
    /*
     * Common events
     */

    // events in the cpw.mods.fml.common.event package are actually handled with
    // @EventHandler annotation in the main mod class or the proxies.
    
    /*
     * Game input events
     */

//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(InputEvent event)
//    {
//        
//    }
//
//    @SideOnly(Side.CLIENT)
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(KeyInputEvent event)
//    {
//
//    }
//
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(MouseInputEvent event)
//    {
//
//    }
//    
//    /*
//     * Player events
//     */
//
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(PlayerEvent event)
//    {
//        
//    }
//
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(ItemCraftedEvent event)
//    {
//        
//    }
//
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(ItemPickupEvent event)
//    {
//        
//    }
//
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(ItemSmeltedEvent event)
//    {
//        
//    }
//
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(PlayerChangedDimensionEvent event)
//    {
//        
//    }
//
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(PlayerLoggedInEvent event)
//    {
//        
//    }

    /**
 * On event.
 *
 * @param event the event
 */
@SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
    public static void onEvent(PlayerLoggedOutEvent event)
    {
        // DEBUG
        System.out.println("Player logged out");
        
    }

//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(PlayerRespawnEvent event)
//    {
//        // DEBUG
//        Debug.print("The memories of past existences are but glints of light.");
//        
//    }
//
//    /*
//     * Tick events
//     */
//
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(ClientTickEvent event) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException
//    {
//        if (event.phase == TickEvent.Phase.END) // only proceed if START phase otherwise, will execute twice per tick
//        {
//            return;
//        }    
//
//    }

//    boolean haveRequestedItemStackRegistry = false;
//    boolean haveGivenGift = false;
            
    /**
	 * On event.
	 *
	 * @param event the event
	 */
	@SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
    public static void onEvent(PlayerTickEvent event)
    {       
		// do client side stuff
        if (event.phase == TickEvent.Phase.START && event.player.world.isRemote) // only proceed if START phase otherwise, will execute twice per tick
        {
            EntityPlayer thePlayer = event.player;
            versionCheckWarning(thePlayer);
  	        MainMod.proxy.handleMaterialAcceleration(thePlayer, ModBlocks.SLIME_BLOCK.getDefaultState().getMaterial());           
        }
        else if (event.phase == TickEvent.Phase.START && !event.player.world.isRemote)
        {
        	// do server side stuff
        }
    }

	/**
	 * Version check warning.
	 *
	 * @param parPlayer the par player
	 * @return true, if successful
	 */
	protected static boolean versionCheckWarning(EntityPlayer parPlayer)
	{
        if (!MainMod.haveWarnedVersionOutOfDate && !MainMod.versionChecker.isLatestVersion())
        {
            ClickEvent versionCheckChatClickEvent = new ClickEvent(ClickEvent.Action.OPEN_URL, "http://jabelarminecraft.blogspot.com");
            Style clickableStyle = new Style().setClickEvent(versionCheckChatClickEvent);
            TextComponentString versionWarningChatComponent = new TextComponentString("Your Magic Beans Mod is not latest version!  Click here to update.");
            versionWarningChatComponent.setStyle(clickableStyle);
            parPlayer.sendMessage(versionWarningChatComponent);
            MainMod.haveWarnedVersionOutOfDate = true;
        }
        return MainMod.haveWarnedVersionOutOfDate;
	}
	
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(RenderTickEvent event)
//    {
//        if (event.phase == TickEvent.Phase.END) // only proceed if START phase otherwise, will execute twice per tick
//        {
//            return;
//        }
//        
//    }
//
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(ServerTickEvent event)
//    {
//        if (event.phase == TickEvent.Phase.END) // only proceed if START phase otherwise, will execute twice per tick
//        {
//            return;
//        }    
//        
//    }

  /**
 * On event.
 *
 * @param event the event
 */
@SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
  public static void onEvent(WorldTickEvent event)
  {
      if (event.phase == TickEvent.Phase.END) // only proceed if START phase otherwise, will execute twice per tick
      {
          return;
      }   
      
      List<Entity> entityList = event.world.loadedEntityList;
      Iterator<Entity> iterator = entityList.iterator();
      
      while(iterator.hasNext())
      {
    	  Entity theEntity = iterator.next();
    	  
    	  /* 
    	   * Update all motion of all entities except players that may be inside your fluid
    	   */
	      MainMod.proxy.handleMaterialAcceleration(theEntity, ModBlocks.SLIME_BLOCK.getDefaultState().getMaterial());
      }
  }
  
  /**
   * Update air supply.
   *
   * @param parEntity the par entity
   * @param parMaterial the par material
   */
  public static void updateAirSupply(Entity parEntity, Material parMaterial)
  {
	  if (!(parEntity instanceof EntityLivingBase))
	  {
		  return;
	  }
	  
	  EntityLivingBase theEntityLiving = (EntityLivingBase) parEntity;
      boolean immuneToDamage = false;
      
      if (theEntityLiving instanceof EntityPlayer)
	  {
    	  immuneToDamage = ((EntityPlayer)theEntityLiving).capabilities.disableDamage;
	  }

      if (theEntityLiving.isEntityAlive())
      {
          if (!theEntityLiving.isInsideOfMaterial(parMaterial))
          {
              theEntityLiving.setAir(300);
          }
          else
          {
        	  // DEBUG
        	  System.out.println("Living entity "+theEntityLiving.getName()+" is drowing in a fluid and can breath underwater = "
        			  +(theEntityLiving.canBreatheUnderwater() || theEntityLiving.isPotionActive(MobEffects.WATER_BREATHING) || immuneToDamage));
        	  
              if (!theEntityLiving.canBreatheUnderwater() && !theEntityLiving.isPotionActive(MobEffects.WATER_BREATHING) && !immuneToDamage)
              {
            	  // DEBUG
            	  System.out.println("The entity cannot breath underwater");
            	  
                  int respirationModifier = EnchantmentHelper.getRespirationModifier(theEntityLiving);
                  int air = theEntityLiving.getAir();
                  Random rand = theEntityLiving.world.rand;
                  if (!(respirationModifier > 0 && rand.nextInt(respirationModifier + 1) > 0))
                  {             	  
                	  theEntityLiving.setAir(air - 1);
                	  
                	  // DEBUG
                	  System.out.println("Reducing air to "+theEntityLiving.getAir());
                  }
                  if (theEntityLiving.getAir() == -20)
                  {
                	  // DEBUG
                	  System.out.println("Entity getting damaged due to drowning");
                	  
                      theEntityLiving.setAir(0);

                      for (int i = 0; i < 8; ++i)
                      {
                          float f2 = rand.nextFloat() - rand.nextFloat();
                          float f = rand.nextFloat() - rand.nextFloat();
                          float f1 = rand.nextFloat() - rand.nextFloat();
                          theEntityLiving.world.spawnParticle(EnumParticleTypes.WATER_BUBBLE, theEntityLiving.posX + f2, theEntityLiving.posY + f, theEntityLiving.posZ + f1, theEntityLiving.motionX, theEntityLiving.motionY, theEntityLiving.motionZ);
                      }

                      theEntityLiving.attackEntityFrom(DamageSource.DROWN, 2.0F);
                  }
              }

              if (!theEntityLiving.world.isRemote && theEntityLiving.isRiding() && theEntityLiving.getRidingEntity() != null && theEntityLiving.getRidingEntity().shouldDismountInWater(theEntityLiving))
              {
                  theEntityLiving.dismountRidingEntity();
              }
          }
      }
  }

    /**
 * On event.
 *
 * @param eventArgs the event args
 */
@SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
    public static void onEvent(OnConfigChangedEvent eventArgs) 
    {
        // DEBUG
        System.out.println("OnConfigChangedEvent");
        if(eventArgs.getModID().equals(MainMod.MODID))
        {
            System.out.println("Syncing config for mod ="+eventArgs.getModID());
            MainMod.config.save();
            MainMod.proxy.syncConfig();
        }
    }

	/**
	 * On event.
	 *
	 * @param event the event
	 */
	@SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
	public static void onEvent(SaveToFile event)
	{
		EntityPlayer thePlayer = event.getEntityPlayer();
		InventoryPlayer theInventory = thePlayer.inventory;
		// DEBUG
		System.out.print("Saving Player To File With main inventory: ");
		Iterator<ItemStack> iterator = theInventory.mainInventory.iterator();
		while (iterator.hasNext())
		{
			ItemStack stack = iterator.next();
			System.out.print(stack+" "+stack.getTagCompound()+" ");
		}
		System.out.println(" ");
	}
	
	/**
	 * On event.
	 *
	 * @param event the event
	 */
	@SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
	public static void onEvent(LoadFromFile event)
	{
		EntityPlayer thePlayer = event.getEntityPlayer();
		InventoryPlayer theInventory = thePlayer.inventory;
		// DEBUG
		System.out.print("Loading Player from File With main inventory: ");
		Iterator<ItemStack> iterator = theInventory.mainInventory.iterator();
		while (iterator.hasNext())
		{
			ItemStack stack = iterator.next();
			System.out.print(stack+" "+stack.getTagCompound()+" ");
		}
		System.out.println(" ");
	}

//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public static void onEvent(PostConfigChangedEvent eventArgs) 
//    {
//        // useful for doing something if another mod's config has changed
//        // if(eventArgs.modID.equals(MagicBeans.MODID))
//        // {
//        //        // do whatever here
//        // }
//    }
}



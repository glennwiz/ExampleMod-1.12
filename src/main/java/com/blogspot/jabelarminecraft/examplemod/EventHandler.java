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
import com.blogspot.jabelarminecraft.examplemod.init.ModFluids;
import com.blogspot.jabelarminecraft.examplemod.init.ModItems;
import com.blogspot.jabelarminecraft.examplemod.init.ModMaterials;
import com.blogspot.jabelarminecraft.examplemod.items.IExtendedReach;
import com.blogspot.jabelarminecraft.examplemod.networking.MessageExtendedReachAttack;
import com.blogspot.jabelarminecraft.examplemod.utilities.Utilities;

import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
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
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.SPacketEntityEquipment;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.world.WorldServer;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.NameFormat;
import net.minecraftforge.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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
  public void onEvent(RegistryEvent.NewRegistry event)
  {
	  // can create registries here if needed
  }
	
    /*
     * Miscellaneous events
     */    

//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(ForceChunkEvent event)
//    {
//        
//    }
//
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(UnforceChunkEvent event)
//    {
//        
//    }
//
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(AnvilUpdateEvent event)
//    {
//        
//    }
//
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(CommandEvent event)
//    {
//        
//    }
//
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(ServerChatEvent event)
//    {
//        
//    }
//    
//    /*
//     * Brewing events
//     */
//        
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(PotionBrewedEvent event)
//    {
//        
//    }
//    
//    /*
//     * Entity related events
//     */
//    
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(EnteringChunk event)
//    {
//        
//    }
//
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(EntityConstructing event)
//    {
//        // Register extended entity properties
//    }
//    
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(EntityJoinWorldEvent event)
//    {
//
//    }
//    
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(EntityStruckByLightningEvent event)
//    {
//        
//    }
//    
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(PlaySoundAtEntityEvent event)
//    {
//        
//    }
//
//    /*
//     * Item events (these extend EntityEvent)
//     */
//    
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(ItemExpireEvent event)
//    {
//        
//    }
//
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(ItemTossEvent event)
//    {
//        
//    }
//    
//    /*
//     * Living events (extend EntityEvent)
//     */
//
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(LivingJumpEvent event)
//    {
//
//    }
//
	
    @SuppressWarnings("unchecked")
	@SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
    public void onEvent(LivingUpdateEvent event) throws IllegalArgumentException, IllegalAccessException
    {
    	event.setCanceled(true);
    	
    	EntityLivingBase theEntity = event.getEntityLiving();
    	    	
    	Field activeItemStackUseCount = ReflectionHelper.findField(EntityLivingBase.class, "activeItemStackUseCount", "field_184628_bn");
    	Field handInventory = ReflectionHelper.findField(EntityLivingBase.class, "handInventory", "field_184630_bs");
       	Field armorArray = ReflectionHelper.findField(EntityLivingBase.class, "armorArray", "field_184631_bt");
    	Field ticksElytraFlying = ReflectionHelper.findField(EntityLivingBase.class, "ticksElytraFlying", "field_184629_bo");
    	
    	Method setFlag = ReflectionHelper.findMethod(Entity.class, "setFlag", "setFlag", Integer.TYPE, Boolean.TYPE); // "func_70052_a"
    	Method getFlag = ReflectionHelper.findMethod(Entity.class, "getFlag", "getFlag", Integer.TYPE); // "func_70083_f"

    	// DEBUG
    	if (theEntity instanceof EntityPlayer)
    	{
    		System.out.println("Air before living update = "+theEntity.getAir());
    	}
    	
        // super.onUpdate() expanded
        if (!theEntity.world.isRemote)
        {
            try {
				setFlag.invoke(theEntity, 6, theEntity.isGlowing());
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
        }

        theEntity.onEntityUpdate();

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
                boolean flag = theEntity.isPotionActive(MobEffects.GLOWING);

                try {
					if (((boolean)getFlag.invoke(theEntity, 6)) != flag)
					{
					    setFlag.invoke(theEntity, 6, flag);
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
        boolean flag = f1 < -90.0F || f1 >= 90.0F;

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

        if (flag)
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
//    public void onEvent(EnderTeleportEvent event)
//    {
//        
//    }
//
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(LivingAttackEvent event)
//    {
//
//    }
//
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(LivingDeathEvent event)
//    {
//        
//    }

    /**
     * On event.
     *
     * @param event the event
     */
    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
    public void onEvent(LivingDropsEvent event)
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
//    public void onEvent(LivingFallEvent event)
//    {
//
//    }
//
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(LivingHurtEvent event)
//    {
//
//    }
//
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(LivingPackSizeEvent event)
//    {
//        
//    }
//
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(LivingSetAttackTargetEvent event)
//    {
//        
//    }
//
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(ZombieEvent event)
//    {
//        
//    }
//    
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(CheckSpawn event)
//    {  	
//
//    }
//    
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(SpecialSpawn event)
//    {
//        
//    }
//    
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(AllowDespawn event)
//    {
//        
//    }
//    
//    /*
//     * Player events (extend LivingEvent)
//     */
//    
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(BreakSpeed event)
//    {
//        
//    }
//    
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(Clone event)
//    {
//        
//    }
//    
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(HarvestCheck event)
//    {
//        
//    }
    
    /**
 * On event.
 *
 * @param event the event
 */
@SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
    public void onEvent(NameFormat event)
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
//    public void onEvent(ArrowLooseEvent event)
//    {
//        
//    }
//    
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(ArrowNockEvent event)
//    {
//        
//    }
//    
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(AttackEntityEvent event)
//    {
//
//    }
//    
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(BonemealEvent event)
//    {
//        
//    }
//    
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(EntityInteractEvent event)
//    {
//
//    }
//    
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(EntityItemPickupEvent event)
//    {
//
//    }
//    
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(FillBucketEvent event)
//    {
//        
//    }
//    
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(ItemTooltipEvent event)
//    {
//        
//    }
//    
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(PlayerDestroyItemEvent event)
//    {
//        
//    }
//    
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(PlayerDropsEvent event)
//    {
//        
//    }
//    
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(PlayerFlyableFallEvent event)
//    {
//        
//    }
//    
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(PlayerInteractEvent event)
//    {
//
//    }
//    
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(PlayerOpenContainerEvent event)
//    {
//        
//    }
//    
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(PlayerPickupXpEvent event)
//    {
//        
//    }
//    
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(PlayerSleepInBedEvent event)
//    {
//        
//    }
//    
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(PlayerUseItemEvent.Finish event)
//    {
//        
//    }
//    
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(PlayerUseItemEvent.Start event)
//    {
//
//    }
//    
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(PlayerUseItemEvent.Stop event)
//    {
//        
//    }
//    
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(PlayerUseItemEvent.Tick event)
//    {
//        
//    }
//    
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(UseHoeEvent event)
//    {
//        
//    }
//    
//    /*
//     * Minecart events (extends EntityEvent)
//     */
//    
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(MinecartCollisionEvent event)
//    {
//        
//    }
//    
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(MinecartInteractEvent event)
//    {
//        
//    }
//    
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(MinecartUpdateEvent event)
//    {
//        
//    }
//    
//    /*
//     * World events
//     */
//
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(WorldEvent.Load event)
//    {
//        
//    }
//
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(WorldEvent.PotentialSpawns event)
//    {
//        
//    }
//
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(WorldEvent.Unload event)
//    {
//        
//    }
//
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(BlockEvent event)
//    {
//        
//    }
//
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(BlockEvent.BreakEvent event)
//    {
//        
//    }
//
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(BlockEvent.HarvestDropsEvent event)
//    {
//        
//    }
//    
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(ChunkEvent event)
//    {
//        
//    }
//    
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(ChunkEvent.Save event)
//    {
//        
//    }
//    
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(ChunkEvent.Unload event)
//    {
//        
//    }
//    
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(ChunkDataEvent event)
//    {
//        
//    }
//    
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(ChunkDataEvent.Load event)
//    {
//        
//    }
//    
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(ChunkDataEvent.Save event)
//    {
//        
//    }
//    
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(ChunkWatchEvent event)
//    {
//        
//    }
//    
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(ChunkWatchEvent.Watch event)
//    {
//        
//    }
//    
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(ChunkWatchEvent.UnWatch event)
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
//    public void onEvent(ClientChatReceivedEvent event)
//    {
//        
//    }
//
//    @SideOnly(Side.CLIENT)
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(DrawBlockHighlightEvent event)
//    {
//        
//    }
//
//    @SideOnly(Side.CLIENT)
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(RenderFogEvent event)
//    {
//        
//    }
//    
//    @SideOnly(Side.CLIENT)
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(FogDensity event)
//    {
//
//    }
//
//    
//    @SideOnly(Side.CLIENT)
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(FogColors event)
//    {
//
//    }
//
//    @SideOnly(Side.CLIENT)
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(FOVUpdateEvent event)
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
    public void onEvent(GuiOpenEvent event)
    { 
    	if (event.getGui() instanceof GuiCompactor)
    	{
    		// DEBUG
    		System.out.println("GuiOpenEvent for GuiCompactor");
    	}
//        if (event.getGui() instanceof GuiIngameMenu)
//        {
//            System.out.println("GuiOpenEvent for GuiIngameModOptions");
//            event.setGui(new GuiConfig(null));        
//        }
    }

//    @SideOnly(Side.CLIENT)
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(GuiScreenEvent.ActionPerformedEvent event)
//    {
//        
//    }
//
//    @SideOnly(Side.CLIENT)
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(GuiScreenEvent.DrawScreenEvent event)
//    {
//        
//    }
//
//    @SideOnly(Side.CLIENT)
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(GuiScreenEvent.InitGuiEvent event)
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
    public void onEvent(MouseEvent event)
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

//    
//    @SideOnly(Side.CLIENT)
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(RenderGameOverlayEvent event)
//    {
//        
//    }
//    
//    @SideOnly(Side.CLIENT)
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(RenderGameOverlayEvent.Chat event)
//    {
//    	// This event actually extends Pre
//
//    }
//    
//    @SideOnly(Side.CLIENT)
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(RenderGameOverlayEvent.Post event)
//    {
//        
//    }
//    
//    @SideOnly(Side.CLIENT)
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(RenderGameOverlayEvent.Pre event)
//    {
//    	// you can check which elements of the GUI are being rendered
//    	// by checking event.type against things like ElementType.CHAT, ElementType.CROSSHAIRS, etc.
//    	// Note that ElementType.All is fired first apparently, then individual elements
//    }
//    
	@SideOnly(Side.CLIENT)
	@SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
	public void onEvent(RenderGameOverlayEvent.Pre event)
	{
		if (event.getType() == ElementType.ALL)
		{
//			// DEBUG
//			System.out.println("render game overlay");
			
			EntityPlayer thePlayer = Minecraft.getMinecraft().player;
			
			if (thePlayer.isInsideOfMaterial(ModMaterials.SLIME))
			{
//				// DEBUG
//				System.out.println("player is inside of material");
				
				drawFluidOverlay(ModFluids.SLIME.getColor(), 0.2F);
			}			
		}
	}
	
//	@SideOnly(Side.CLIENT)
//	@SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//	public void onEvent(RenderBlockOverlayEvent event)
//	{
//		if (event.getOverlayType() == OverlayType.WATER)
//		{
//			IBlockState theBlock = event.getBlockForOverlay();
//			if (theBlock instanceof ModBlockFluidClassic)
//			{
//				// DEBUG
//				System.out.println("rendering fluid overlay");
//				renderFluidOverlay(event.getRenderPartialTicks());
//			}
//		}
//	}
  		
    /**
     * Draws a rectangle with the specified color
     */
	@SideOnly(Side.CLIENT)
     public static void drawFluidOverlay(int parColor, float parAlpha)
    {	
		int left = 0;
		int top = 0;
		int right = Minecraft.getMinecraft().displayWidth;
		int bottom = Minecraft.getMinecraft().displayHeight;

		Color color = Color.GREEN;
//        int red = color.getRed();
//        int green = color.getGreen();
//        int blue = color.getBlue();
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.color(color.getRed(), color.getGreen(), color.getBlue(), parAlpha);
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION);
        bufferbuilder.pos(left, bottom, 0.0D).endVertex();
        bufferbuilder.pos(right, bottom, 0.0D).endVertex();
        bufferbuilder.pos(right, top, 0.0D).endVertex();
        bufferbuilder.pos(left, top, 0.0D).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

//
//    @SideOnly(Side.CLIENT)
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(RenderHandEvent event)
//    {
//        
//    }
//
//    @SideOnly(Side.CLIENT)
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(RenderLivingEvent.Post event)
//    {
//        
//    }
//
//    @SideOnly(Side.CLIENT)
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(RenderLivingEvent.Pre event)
//    {
//        
//    }
//
//    @SideOnly(Side.CLIENT)
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(RenderPlayerEvent.Post event)
//    {
//        
//    }
//
//    @SideOnly(Side.CLIENT)
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(RenderPlayerEvent.Pre event)
//    {
//
//    }
//
//    @SideOnly(Side.CLIENT)
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(RenderPlayerEvent.SetArmorModel event)
//    {
//        
//    }
//
//    @SideOnly(Side.CLIENT)
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(RenderWorldEvent.Post event)
//    {
//        
//    }
//
//    @SideOnly(Side.CLIENT)
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(RenderWorldEvent.Pre event)
//    {
//    	Minecraft.getMinecraft().gameSettings.thirdPersonView = 2;
//    }
//
//    @SideOnly(Side.CLIENT)
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(RenderWorldLastEvent event)
//    {
//        
//    }
//
//    @SideOnly(Side.CLIENT)
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(TextureStitchEvent.Post event)
//    {
//        
//    }
//
//    @SideOnly(Side.CLIENT)
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(TextureStitchEvent.Pre event)
//    {
//        
//    }
//    
//    /*
//     * Fluid events
//     */
//
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(FluidEvent event)
//    {
//        
//    }
//
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(FluidContainerRegisterEvent event)
//    {
//        
//    }
//
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(FluidDrainingEvent event)
//    {
//        
//    }
//
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(FluidFillingEvent event)
//    {
//        
//    }
//
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(FluidMotionEvent event)
//    {
//        
//    }
//
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(FluidRegisterEvent event)
//    {
//        
//    }
//
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(FluidSpilledEvent event)
//    {
//        
//    }
//
//    /*
//     * Ore dictionary events
//     */
//
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(OreRegisterEvent event)
//    {
//        
//    }
//    
//	@SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//	public void onEvent(PopulateChunkEvent event)
//	{
//		
//	}
//	
//	// for some reason the PopulateChunkEvents are fired on the main EVENT_BUT
//	// even though they are in the terraingen package
//	@SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//	public void onEvent(PopulateChunkEvent.Populate event)
//	{
//		
//	}
//	
//	// for some reason the PopulateChunkEvents are fired on the main EVENT_BUT
//	// even though they are in the terraingen package
//	@SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//	public void onEvent(PopulateChunkEvent.Post event)
//	{ 
//		
//	}
//	
//	// for some reason the PopulateChunkEvents are fired on the main EVENT_BUT
//	// even though they are in the terraingen package
//	@SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//	public void onEvent(PopulateChunkEvent.Pre event)
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
//    public void onEvent(InputEvent event)
//    {
//        
//    }
//
//    @SideOnly(Side.CLIENT)
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(KeyInputEvent event)
//    {
//
//    }
//
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(MouseInputEvent event)
//    {
//
//    }
//    
//    /*
//     * Player events
//     */
//
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(PlayerEvent event)
//    {
//        
//    }
//
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(ItemCraftedEvent event)
//    {
//        
//    }
//
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(ItemPickupEvent event)
//    {
//        
//    }
//
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(ItemSmeltedEvent event)
//    {
//        
//    }
//
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(PlayerChangedDimensionEvent event)
//    {
//        
//    }
//
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(PlayerLoggedInEvent event)
//    {
//        
//    }

    /**
 * On event.
 *
 * @param event the event
 */
@SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
    public void onEvent(PlayerLoggedOutEvent event)
    {
        // DEBUG
        System.out.println("Player logged out");
        
    }

//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(PlayerRespawnEvent event)
//    {
//        // DEBUG
//        System.out.println("The memories of past existences are but glints of light.");
//        
//    }
//
//    /*
//     * Tick events
//     */
//
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(ClientTickEvent event) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException
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
    public void onEvent(PlayerTickEvent event)
    {       
		// do client side stuff
        if (event.phase == TickEvent.Phase.START && event.player.world.isRemote) // only proceed if START phase otherwise, will execute twice per tick
        {
            EntityPlayer thePlayer = event.player;
            versionCheckWarning(thePlayer);
            processFluidPush(thePlayer);
            
        }
        else if (event.phase == TickEvent.Phase.START && !event.player.world.isRemote)
        {
        	// do server side stuff
        }
    }

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

	protected static void processFluidPush(EntityPlayer parPlayer)
	{
	      if (MainMod.proxy.handleMaterialAcceleration(parPlayer, ModBlocks.SLIME_BLOCK.getDefaultState().getMaterial()));
	      {
	    	  parPlayer.fallDistance = 0.0F;
	          parPlayer.extinguish();
	      }
	}
	
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(RenderTickEvent event)
//    {
//        if (event.phase == TickEvent.Phase.END) // only proceed if START phase otherwise, will execute twice per tick
//        {
//            return;
//        }
//        
//    }
//
//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(ServerTickEvent event)
//    {
//        if (event.phase == TickEvent.Phase.END) // only proceed if START phase otherwise, will execute twice per tick
//        {
//            return;
//        }    
//        
//    }

  @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
  public void onEvent(WorldTickEvent event)
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
    	   * Update all motion of all entities that may be inside your fluid
    	   */
	      MainMod.proxy.handleMaterialAcceleration(theEntity, ModBlocks.SLIME_BLOCK.getDefaultState().getMaterial());
	      
	      /*
	       * Update air supply for living entities that may be inside your fluid
	       */
	      updateAirSupply(theEntity, ModBlocks.SLIME_BLOCK.getDefaultState().getMaterial());
      }
  }
  
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
    public void onEvent(OnConfigChangedEvent eventArgs) 
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

//    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(PostConfigChangedEvent eventArgs) 
//    {
//        // useful for doing something if another mod's config has changed
//        // if(eventArgs.modID.equals(MagicBeans.MODID))
//        // {
//        //        // do whatever here
//        // }
//    }
}



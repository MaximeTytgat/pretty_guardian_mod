package com.max.prettyguardian.event.custom;

import com.max.prettyguardian.PrettyGuardian;
import com.max.prettyguardian.entity.ModEntities;
import com.max.prettyguardian.entity.custom.CelestialRabbitEntity;
import com.max.prettyguardian.item.PrettyGuardianItem;
import com.max.prettyguardian.item.custom.food.ClassicDonut;
import com.max.prettyguardian.worldgen.entity.ModEntityType;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

import java.awt.*;
import java.util.Date;
import java.util.function.Predicate;

@Mod.EventBusSubscriber(modid = PrettyGuardian.MOD_ID, value = Dist.CLIENT)
public class SecretDonutEvent {
    @SubscribeEvent
    public static void onPlayerInteract(PlayerInteractEvent.EntityInteractSpecific event) {
        Player player = event.getEntity();
        ItemStack itemstack = player.getMainHandItem();

        if (itemstack.getItem() instanceof ClassicDonut && event.getTarget() instanceof Player) {
            player.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(PrettyGuardianItem.SECRET_DONUT.get()));
        }
    }

    @SubscribeEvent
    public static void onEntityInteract(PlayerInteractEvent.EntityInteract event) {
        Player player = event.getEntity();
        if (event.getTarget() instanceof LivingEntity livingEntity) {
            if (livingEntity instanceof CelestialRabbitEntity celestialRabbit && player.isShiftKeyDown()) {
                if (celestialRabbit.isTame() && celestialRabbit.getOwnerUUID() == player.getUUID() ) {

                    BlockPos pos = celestialRabbit.blockPosition();
                    DyeColor collarColor = celestialRabbit.getCollarColor();
                    Component name = celestialRabbit.hasCustomName() ? celestialRabbit.getCustomName() : null;

                    player.addAdditionalSaveData(celestialRabbit.saveWithoutId(new CompoundTag()));

                    CelestialRabbitEntity newRabbit = new CelestialRabbitEntity(ModEntities.CELESTIAL_RABBIT.get(), player.level());

                    if (!player.level().isClientSide) {
                        newRabbit.setPos(pos.getX(), pos.getY(), pos.getZ());
                        newRabbit.setCollarColor(collarColor);
                        if (name != null) newRabbit.setCustomName(name);
                        newRabbit.tame(player);
                    }

                    player.level().addFreshEntity(newRabbit);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onIteractWithBlock(PlayerInteractEvent.RightClickBlock event) {
        if (!event.getSide().isClient()) {
            Player player = event.getEntity();

            if (player == null || player.isSpectator() || player.getVehicle() != null) {
                return;
            }

            if (player.isShiftKeyDown()) {
                ItemStack itemstack = player.getMainHandItem();

            }
        }
    }
}

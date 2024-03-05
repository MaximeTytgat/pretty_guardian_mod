package com.max.prettyguardian.event;

import com.max.prettyguardian.PrettyGuardian;
import com.max.prettyguardian.entity.ModEntities;
import com.max.prettyguardian.entity.custom.CelestialRabbitEntity;
import com.max.prettyguardian.entityOnShoulder.PlayerEntityOnShoulder;
import com.max.prettyguardian.entityOnShoulder.PlayerEntityOnShoulderProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = PrettyGuardian.MOD_ID)
public class PlayerEntityOnShoulderEvents {
    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if(event.getObject() instanceof Player) {
            if(!event.getObject().getCapability(PlayerEntityOnShoulderProvider.PLAYER_ENTITY_ON_SHOULDER_CAPABILITY).isPresent()) {
                event.addCapability(new ResourceLocation(PrettyGuardian.MOD_ID, "properties"), new PlayerEntityOnShoulderProvider());
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        if(event.isWasDeath()) {
            event.getOriginal().getCapability(PlayerEntityOnShoulderProvider.PLAYER_ENTITY_ON_SHOULDER_CAPABILITY).ifPresent(oldStore -> {
                event.getOriginal().getCapability(PlayerEntityOnShoulderProvider.PLAYER_ENTITY_ON_SHOULDER_CAPABILITY).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);
                });
            });
        }
    }

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(PlayerEntityOnShoulder.class);
    }

//    @SubscribeEvent
//    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
//        if(event.side == LogicalSide.SERVER) {
//            event.player.getCapability(PlayerEntityOnShoulderProvider.PLAYER_ENTITY_ON_SHOULDER_CAPABILITY).ifPresent(thirst -> {
//                if(thirst.getThirst() > 0 && event.player.getRandom().nextFloat() < 0.005f) { // Once Every 10 Seconds on Avg
//                    thirst.subThirst(1);
//                    event.player.sendSystemMessage(Component.literal("Subtracted Thirst"));
//                }
//            });
//        }
//    }

    @SubscribeEvent
    public static void onIteractWithBlock(PlayerInteractEvent.RightClickBlock event) {
        if (!event.getSide().isClient()) {
            Player player = event.getEntity();

            if (player == null || player.isSpectator() || player.getVehicle() != null) {
                return;
            }

            if (player.isShiftKeyDown()) {
                if(event.getSide() == LogicalSide.SERVER) {
                    player.getCapability(PlayerEntityOnShoulderProvider.PLAYER_ENTITY_ON_SHOULDER_CAPABILITY).ifPresent(entityOnShoulder -> {
                        if(entityOnShoulder.getEntityType() != null && entityOnShoulder.getEntityType() == ModEntities.CELESTIAL_RABBIT.get()) {
                            CelestialRabbitEntity newRabbit = new CelestialRabbitEntity(ModEntities.CELESTIAL_RABBIT.get(), player.level());

                            newRabbit.setPos(event.getPos().getX(), event.getPos().getY(), event.getPos().getZ());
                            newRabbit.setCollarColor(entityOnShoulder.getCollarColor());
                            if (entityOnShoulder.getName() != null) newRabbit.setCustomName(entityOnShoulder.getName());
                            newRabbit.tame(player);

                            player.level().addFreshEntity(newRabbit);

                            player.sendSystemMessage(Component.literal("ENTITY POSED"));
                        }
                    });
                }
            }
        }
    }

    @SubscribeEvent
    public static void onEntityInteract(PlayerInteractEvent.EntityInteract event) {
        Player player = event.getEntity();
        if (event.getTarget() instanceof LivingEntity livingEntity) {
            if (livingEntity instanceof CelestialRabbitEntity celestialRabbit && player.isShiftKeyDown()) {
                if (celestialRabbit.isTame() && celestialRabbit.getOwnerUUID() == player.getUUID() ) {
                    DyeColor collarColor = celestialRabbit.getCollarColor();
                    Component name = celestialRabbit.hasCustomName() ? celestialRabbit.getCustomName() : null;

                    if(event.getSide() == LogicalSide.SERVER) {
                        player.getCapability(PlayerEntityOnShoulderProvider.PLAYER_ENTITY_ON_SHOULDER_CAPABILITY).ifPresent(entityOnShoulder -> {
                            if(entityOnShoulder.getEntityType() == null) {
                                entityOnShoulder.setEntityOnShoulder(ModEntities.CELESTIAL_RABBIT.get(), collarColor, name);

                                livingEntity.setHealth(0.0f);
                                player.sendSystemMessage(Component.literal("ENTITY ON PLAYER"));
                            }
                        });
                    }

                }
            }
        }
    }
}

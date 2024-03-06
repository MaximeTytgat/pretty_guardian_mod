package com.max.prettyguardian.item.custom.tool;

import com.max.prettyguardian.PrettyGuardian;
import com.max.prettyguardian.util.BaseTeleporter;
import com.max.prettyguardian.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.PlayerRespawnLogic;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.util.ITeleporter;

import java.util.function.Predicate;

public class PlutonsKey extends Item {

    public static final Predicate<ItemStack> HEART_ARROW_ONLY = (itemStack) -> {
        return itemStack.is(ModTags.Items.HEART_ARROWS);
    };

    public PlutonsKey(Properties properties) {
        super(properties.rarity(Rarity.EPIC));
    }

    public boolean isFoil(ItemStack itemStack) {
        return true;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        if (interactionHand == InteractionHand.MAIN_HAND) {
            if (player instanceof ServerPlayer serverPlayer) {
                ResourceKey<Level> dimension = serverPlayer.getRespawnDimension();
                ServerLevel serverLevel = null;
                if (level instanceof ServerLevel) {
                    if (dimension == level.dimension()) {
                        serverLevel = (ServerLevel) level;
                    } else {
                        serverLevel = serverPlayer.getServer().getLevel(Level.OVERWORLD);
                    }
                }

                BlockPos respawnPoint = serverPlayer.getRespawnPosition();

                if (serverLevel != null ) {
                    if (respawnPoint != null) {
                        BlockState blockAtRespawn = serverLevel.getBlockState(respawnPoint);
                        if (blockAtRespawn.getBlock() instanceof BedBlock) {
                            if (dimension != level.dimension()) {
                                serverPlayer.changeDimension(serverLevel, new BaseTeleporter(respawnPoint.getX(), respawnPoint.getY()+0.5, respawnPoint.getZ()));
                                return InteractionResultHolder.success(player.getItemInHand(interactionHand));
                            } else {
                                serverPlayer.teleportTo(respawnPoint.getX(), respawnPoint.getY()+0.5, respawnPoint.getZ());
                                return InteractionResultHolder.success(player.getItemInHand(interactionHand));
                            }
                        }
                    }

                    BlockPos worldSpawn = serverLevel.getSharedSpawnPos();

                    if (dimension != level.dimension()) {
                        serverPlayer.changeDimension(serverLevel, new BaseTeleporter(worldSpawn.getX(), worldSpawn.getY(), worldSpawn.getZ()));
                        return InteractionResultHolder.success(player.getItemInHand(interactionHand));
                    } else {
                        serverPlayer.teleportTo(worldSpawn.getX(), worldSpawn.getY(), worldSpawn.getZ());
                        return InteractionResultHolder.success(player.getItemInHand(interactionHand));
                    }

                } else {
                    return InteractionResultHolder.fail(player.getItemInHand(interactionHand));
                }
            }
        }

        player.getCooldowns().addCooldown(this, 600);
        return super.use(level, player, interactionHand);
    }

    public UseAnim getUseAnimation(ItemStack p_40678_) {
        return UseAnim.SPEAR;
    }
}

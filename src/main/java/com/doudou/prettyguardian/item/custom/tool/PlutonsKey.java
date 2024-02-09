package com.doudou.prettyguardian.item.custom.tool;

import com.doudou.prettyguardian.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.state.BlockState;

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
            if (player instanceof ServerPlayer) {
                ServerPlayer serverPlayer = (ServerPlayer) player;
                BlockPos respawnPoint = serverPlayer.getRespawnPosition();

                if (respawnPoint != null) {
                    BlockState blockAtRespawn = level.getBlockState(respawnPoint);
                    if (!(blockAtRespawn.getBlock() instanceof BedBlock)) {
                        BlockPos worldSpawn = level.getSharedSpawnPos();
                        serverPlayer.teleportTo(worldSpawn.getX(), worldSpawn.getY(), worldSpawn.getZ());
                    } else {
                        serverPlayer.teleportTo(respawnPoint.getX(), respawnPoint.getY(), respawnPoint.getZ());
                    }
                } else {
                    serverPlayer.teleportTo(0, 70, 0);
                }
            }
        }

        (player).getCooldowns().addCooldown(this, 600);
        return super.use(level, player, interactionHand);
    }

    public UseAnim getUseAnimation(ItemStack p_40678_) {
        return UseAnim.SPEAR;
    }
}

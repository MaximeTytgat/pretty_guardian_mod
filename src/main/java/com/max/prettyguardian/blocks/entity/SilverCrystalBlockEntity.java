package com.max.prettyguardian.blocks.entity;

import com.max.prettyguardian.PrettyGuardian;
import com.max.prettyguardian.effect.ModEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import java.util.Iterator;

public class SilverCrystalBlockEntity extends BlockEntity {
    public SilverCrystalBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.SILVER_CRYSTAL_BE.get(), blockPos, blockState);
    }

    public void tick(Level level, BlockPos pPos) {
        if (level.isClientSide()) {
            return;
        }

        if (level.getGameTime() % 60 == 0) {
            int x = pPos.getX();
            int y = pPos.getY();
            int z = pPos.getZ();
            Iterator var17 = level.getEntitiesOfClass(ServerPlayer.class, (new AABB((double)x, (double)y, (double)z, (double)x, (double)(y - 4), (double)z)).inflate(50.0, 5.0, 50.0)).iterator();

            while(var17.hasNext()) {
                ServerPlayer serverplayer = (ServerPlayer)var17.next();
                serverplayer.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 270, 1));
            }
        }
    }
}

package com.max.prettyguardian.event;

import com.max.prettyguardian.PrettyGuardian;
import com.max.prettyguardian.blocks.custom.furniture.JapChairBlock;
import com.max.prettyguardian.item.PrettyGuardianItem;
import com.max.prettyguardian.item.custom.food.ClassicDonut;
import com.max.prettyguardian.worldgen.entity.ModEntityType;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = PrettyGuardian.MOD_ID, value = Dist.CLIENT)
public class JapChairEvent {
    @SubscribeEvent
    public static void onIteractWithBlock(PlayerInteractEvent.RightClickBlock event) {
        if (event.getSide().isClient()) {
            PrettyGuardian.LOGGER.info("onIteractWithBlock");
            Player player = event.getEntity();

            if (player == null || player.isSpectator() || player.getVehicle() != null) {
                return;
            }

            Level level = event.getLevel();
            BlockPos pos = event.getPos();
            BlockState blockState = level.getBlockState(pos);

            PrettyGuardian.LOGGER.info("Pos: " + pos);

            if (blockState.getBlock() instanceof JapChairBlock) {
                SeatJapChairEntity seatJapChairEntity = new SeatJapChairEntity(ModEntityType.SEAT_JAP_CHAIR.get(), level, pos);
                level.addFreshEntity(seatJapChairEntity);
                player.startRiding(seatJapChairEntity);
            }
        }
    }

    public static class SeatJapChairEntity extends Entity {

        public SeatJapChairEntity(EntityType<?> entityType, Level level) {
            super(entityType, level);
        }

        public SeatJapChairEntity(EntityType<?> entityType, Level level, BlockPos pos) {
            super(entityType, level);
            setPos(pos.getX() + 0.5D, pos.getY() + 0.2D, pos.getZ() + 0.5D);
        }

        @Override
        public void tick() {
            super.tick();

            BlockPos pos = this.blockPosition();
            if (!(this.level().getBlockState(pos).getBlock() instanceof JapChairBlock)) {
                this.ejectPassengers();
                this.discard();
            }

            List<Entity> passengers = getPassengers();
            if (passengers.isEmpty()) {
                this.ejectPassengers();
                this.discard();
            }

            for (Entity entity: passengers) {
                if (entity instanceof Player player) {
                    if (player.isShiftKeyDown()) {
                        this.ejectPassengers();
                        this.discard();
                    }
                }
            }
        }

        @Override
        protected void defineSynchedData() {

        }

        @Override
        protected void readAdditionalSaveData(CompoundTag compoundTag) {

        }

        @Override
        protected void addAdditionalSaveData(CompoundTag compoundTag) {

        }
    }
}

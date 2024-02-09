package com.doudou.prettyguardian.item.custom;

import com.doudou.prettyguardian.entity.ModEntities;
import com.doudou.prettyguardian.entity.custom.ButterflyEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ButterflyEggItem extends Item {
    private final Supplier<? extends EntityType<? extends Mob>> type;
    private final ButterflyEntity.Variant variant;
    public ButterflyEggItem(Supplier<? extends EntityType<? extends Mob>> type, ButterflyEntity.Variant variant, Item.Properties props) {
        super(props);
        this.type = type;
        this.variant = variant;
    }
    public InteractionResult useOn(UseOnContext useOnContext) {
        Level level = useOnContext.getLevel();
        if (!(level instanceof ServerLevel)) {
            return InteractionResult.SUCCESS;
        } else {
            ItemStack itemstack = useOnContext.getItemInHand();
            BlockPos blockpos = useOnContext.getClickedPos();
            Direction direction = useOnContext.getClickedFace();
            BlockState blockstate = level.getBlockState(blockpos);

            BlockPos blockpos1;
            if (blockstate.getCollisionShape(level, blockpos).isEmpty()) {
                blockpos1 = blockpos;
            } else {
                blockpos1 = blockpos.relative(direction);
            }

            EntityType<?> entitytype = this.type.get();

            if (this.spawn((ServerLevel)level, itemstack, useOnContext.getPlayer(), blockpos1, MobSpawnType.SPAWN_EGG, true, !Objects.equals(blockpos, blockpos1) && direction == Direction.UP) != null) {
                itemstack.shrink(1);
                level.gameEvent(useOnContext.getPlayer(), GameEvent.ENTITY_PLACE, blockpos);
            }

            return InteractionResult.CONSUME;
        }
    }

    @Nullable
    public ButterflyEntity spawn(ServerLevel p_20593_, @Nullable ItemStack p_20594_, @Nullable Player p_20595_, BlockPos p_20596_, MobSpawnType p_20597_, boolean p_20598_, boolean p_20599_) {
        Consumer<ButterflyEntity> consumer;
        CompoundTag compoundtag;
        if (p_20594_ != null) {
            compoundtag = p_20594_.getTag();
            consumer = createDefaultStackConfig(p_20593_, p_20594_, p_20595_);
        } else {
            consumer = (p_263563_) -> {
            };
            compoundtag = null;
        }

        return this.spawn(p_20593_, compoundtag, consumer, p_20596_, p_20597_, p_20598_, p_20599_);
    }

    public static <T extends Entity> Consumer<T> createDefaultStackConfig(ServerLevel p_263583_, ItemStack p_263568_, @Nullable Player p_263575_) {
        return appendDefaultStackConfig((p_262561_) -> {
        }, p_263583_, p_263568_, p_263575_);
    }
    public static <T extends Entity> Consumer<T> appendDefaultStackConfig(Consumer<T> p_265154_, ServerLevel p_265733_, ItemStack p_265598_, @Nullable Player p_265666_) {
        return appendCustomEntityStackConfig(appendCustomNameConfig(p_265154_, p_265598_), p_265733_, p_265598_, p_265666_);
    }

    public static <T extends Entity> Consumer<T> appendCustomNameConfig(Consumer<T> p_263567_, ItemStack p_263564_) {
        return p_263564_.hasCustomHoverName() ? p_263567_.andThen((p_262560_) -> {
            p_262560_.setCustomName(p_263564_.getHoverName());
        }) : p_263567_;
    }

    public static <T extends Entity> Consumer<T> appendCustomEntityStackConfig(Consumer<T> p_263579_, ServerLevel p_263571_, ItemStack p_263582_, @Nullable Player p_263574_) {
        CompoundTag compoundtag = p_263582_.getTag();
        return compoundtag != null ? p_263579_.andThen((p_262558_) -> {
            updateCustomEntityTag(p_263571_, p_263574_, p_262558_, compoundtag);
        }) : p_263579_;
    }

    public static void updateCustomEntityTag(Level p_20621_, @Nullable Player p_20622_, @Nullable Entity p_20623_, @Nullable CompoundTag p_20624_) {
        if (p_20624_ != null && p_20624_.contains("EntityTag", 10)) {
            MinecraftServer minecraftserver = p_20621_.getServer();
            if (minecraftserver != null && p_20623_ != null) {
                if (p_20621_.isClientSide || !p_20623_.onlyOpCanSetNbt() || p_20622_ != null && minecraftserver.getPlayerList().isOp(p_20622_.getGameProfile())) {
                    CompoundTag compoundtag = p_20623_.saveWithoutId(new CompoundTag());
                    UUID uuid = p_20623_.getUUID();
                    compoundtag.merge(p_20624_.getCompound("EntityTag"));
                    p_20623_.setUUID(uuid);
                    p_20623_.load(compoundtag);
                }
            }
        }
    }


    @Nullable
    public ButterflyEntity spawn(ServerLevel p_262704_, @Nullable CompoundTag p_262603_, @Nullable Consumer<ButterflyEntity> p_262621_, BlockPos p_262672_, MobSpawnType p_262644_, boolean p_262690_, boolean p_262590_) {
        ButterflyEntity t = this.create(p_262704_, p_262603_, p_262621_, p_262672_, p_262644_, p_262690_, p_262590_);
        if (t != null) {
            p_262704_.addFreshEntityWithPassengers(t);
        }

        return t;
    }

    protected static double getYOffset(LevelReader p_20626_, BlockPos p_20627_, boolean p_20628_, AABB p_20629_) {
        AABB aabb = new AABB(p_20627_);
        if (p_20628_) {
            aabb = aabb.expandTowards(0.0D, -1.0D, 0.0D);
        }

        Iterable<VoxelShape> iterable = p_20626_.getCollisions((Entity)null, aabb);
        return 1.0D + Shapes.collide(Direction.Axis.Y, p_20629_, iterable, p_20628_ ? -2.0D : -1.0D);
    }

    @Nullable
    public ButterflyEntity create(ServerLevel p_262637_, @Nullable CompoundTag p_262687_, @Nullable Consumer<ButterflyEntity> p_262629_, BlockPos p_262595_, MobSpawnType p_262666_, boolean p_262685_, boolean p_262588_) {
        ButterflyEntity t = this.create(p_262637_);
        if (t == null) {
            return (ButterflyEntity)null;
        } else {
            double d0;
            if (p_262685_) {
                t.setPos((double)p_262595_.getX() + 0.5D, (double)(p_262595_.getY() + 1), (double)p_262595_.getZ() + 0.5D);
                d0 = getYOffset(p_262637_, p_262595_, p_262588_, t.getBoundingBox());
            } else {
                d0 = 0.0D;
            }

            t.moveTo((double)p_262595_.getX() + 0.5D, (double)p_262595_.getY() + d0, (double)p_262595_.getZ() + 0.5D, Mth.wrapDegrees(p_262637_.random.nextFloat() * 360.0F), 0.0F);
            if (t instanceof ButterflyEntity) {
                ButterflyEntity mob = (ButterflyEntity)t;
                mob.yHeadRot = mob.getYRot();
                mob.yBodyRot = mob.getYRot();
                ButterflyEntity.ButterflyGroupData mobgroupdata = new ButterflyEntity.ButterflyGroupData(this.variant);
                mob.finalizeSpawn(p_262637_, p_262637_.getCurrentDifficultyAt(mob.blockPosition()), p_262666_, mobgroupdata, p_262687_);
                mob.playAmbientSound();
            }

            if (p_262629_ != null) {
                p_262629_.accept(t);
            }

            return t;
        }
    }

    @Nullable
    public ButterflyEntity create(Level p_20616_) {
        return (ButterflyEntity)(!this.isEnabled(p_20616_.enabledFeatures()) ? null : ModEntities.BUTTERFLY.get().create(p_20616_));
    }


    public interface EntityFactory<T extends Entity> {
        T create(EntityType<T> p_20722_, Level p_20723_);
    }
}

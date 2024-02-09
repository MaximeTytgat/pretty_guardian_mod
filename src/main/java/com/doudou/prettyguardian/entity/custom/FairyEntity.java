package com.doudou.prettyguardian.entity.custom;

import com.doudou.prettyguardian.entity.ModEntities;
import com.doudou.prettyguardian.item.PrettyGuardianItem;
import com.doudou.prettyguardian.item.custom.tool.ButterflyNetItem;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.*;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.monster.Strider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.function.IntFunction;

public class FairyEntity extends Animal implements FlyingAnimal, VariantHolder<FairyEntity.Variant> {
    private static final EntityDataAccessor<Integer> DATA_VARIANT = SynchedEntityData.defineId(FairyEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DATA_REMAINING_TIME_BEFORE_DUST = SynchedEntityData.defineId(Strider.class, EntityDataSerializers.INT);
    private static final UniformInt DUST_TIME = TimeUtil.rangeOfSeconds(300, 500);;


    public FairyEntity(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
        this.setMaxUpStep(5.0F);
        this.moveControl = new FairyEntity.FairyMoveControl(this);
    }

    public static AnimationState idleAnimationState = new AnimationState();

    public boolean isPushable() { return false; }
    protected void doPush(Entity p_27415_) {}
    protected void pushEntities() {}
    @Override
    protected void checkFallDamage(double p_27754_, boolean p_27755_, BlockState p_27756_, BlockPos p_27757_) {}
    @Override
    public float getWalkTargetValue(BlockPos p_21693_) {
        return 0.0F;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(5, new FairyEntity.RandomFloatAroundGoal(this));
        this.goalSelector.addGoal(7, new FairyEntity.GhastLookGoal(this));

        // ???
        // this.goalSelector.addGoal(9, new RandomLookAroundGoal(this));
        // this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Player.class, 12.0F));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 2.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.FLYING_SPEED, 1D)
                .add(Attributes.FOLLOW_RANGE, 10.0D);
    }


    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor serverLevelAccessor, DifficultyInstance difficultyInstance, MobSpawnType mobSpawnType, @javax.annotation.Nullable SpawnGroupData spawnGroupData, @javax.annotation.Nullable CompoundTag tag) {
        boolean flag = false;
        if (mobSpawnType == MobSpawnType.BUCKET) {
            return spawnGroupData;
        } else {
            RandomSource randomsource = serverLevelAccessor.getRandom();
            if (spawnGroupData instanceof FairyGroupData) {
                if (((FairyGroupData)spawnGroupData).getGroupSize() >= 2) {
                    flag = true;
                }
            } else {
                spawnGroupData = new FairyGroupData(Variant.getSpawnVariant(randomsource), Variant.getSpawnVariant(randomsource));
            }

            this.setVariant(((FairyGroupData)spawnGroupData).getVariant(randomsource));
            if (flag) {
                this.setAge(-24000);
            }
            ((FairyGroupData) spawnGroupData).getVariant(randomsource);

            return super.finalizeSpawn(serverLevelAccessor, difficultyInstance, mobSpawnType, spawnGroupData, tag);
        }
    }

    public boolean hasDust() {
        return this.getRemainingTimeBeforeDust() == 0;
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.level().isClientSide) {
            if (this.getRemainingTimeBeforeDust() > 0) {
                this.setRemainingTimeBeforeDust(this.getRemainingTimeBeforeDust() - 1);
            }
        }
        if (this.level().isClientSide) {
            setupAnimationStates();
        }
    }

    private void setupAnimationStates() {
        if (this.walkAnimation.isMoving()) {
            idleAnimationState.stop();
        } else if (!idleAnimationState.isStarted()) {
            idleAnimationState.start(40);
        }
    }

    @Override
    protected void updateWalkAnimation(float p_268283_) {
        float f;
        if (this.getPose() == Pose.STANDING) {
            f = Math.min(p_268283_ * 6, 1f);
        } else {
            f = 0;
        }

        this.walkAnimation.update(f, 0.2f);
    }

    @Override
    protected PathNavigation createNavigation(Level p_21480_) {
        FlyingPathNavigation flyingpathnavigation = new FlyingPathNavigation(this, p_21480_) {
            public boolean isStableDestination(BlockPos p_27947_) {
                return !this.level.getBlockState(p_27947_.below()).isAir();
            }

            public void tick() {
                super.tick();
            }
        };
        flyingpathnavigation.setCanOpenDoors(false);
        flyingpathnavigation.setCanFloat(false);
        flyingpathnavigation.setCanPassDoors(true);
        return flyingpathnavigation;
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand interactionHand) {
        ItemStack itemstack = player.getItemInHand(interactionHand);

        if (itemstack.getItem() instanceof ButterflyNetItem butterflyNet && !this.isBaby() ){
            if (!this.level().isClientSide) {
                if (this.hasDust()) {
                    this.startDustTime();
                    ItemStack fairyDust = new ItemStack(PrettyGuardianItem.FAIRY_DUST.get());
                    player.addItem(fairyDust);
                    butterflyNet.setDamage(itemstack, butterflyNet.getDamage(itemstack) + 1);
                    return InteractionResult.sidedSuccess(this.level().isClientSide);
                }
            }
        }

        return super.mobInteract(player, interactionHand);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(@NotNull ServerLevel serverLevel, @NotNull AgeableMob ageableMob) {
        return ModEntities.FAIRY.get().create(serverLevel);
    }
    @Override
    public boolean isFlying() {
        return !this.onGround();
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_VARIANT, 0);
        this.entityData.define(DATA_REMAINING_TIME_BEFORE_DUST, DUST_TIME.sample(this.random));
    }

    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("Variant", this.getVariant().getId());

    }

    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.setVariant(Variant.byId(tag.getInt("Variant")));
    }


    public int getRemainingTimeBeforeDust() {
        return this.entityData.get(DATA_REMAINING_TIME_BEFORE_DUST);
    }

    public void setRemainingTimeBeforeDust(int i) {
        this.entityData.set(DATA_REMAINING_TIME_BEFORE_DUST, i);
    }

    public void startDustTime() {
        this.setRemainingTimeBeforeDust(DUST_TIME.sample(this.random));
    }

    @Override
    public void setVariant(Variant p_149118_) {
        this.entityData.set(DATA_VARIANT, p_149118_.getId());
    }

    @Override
    public Variant getVariant() {
        return Variant.byId(this.entityData.get(DATA_VARIANT));
    }

    public static class FairyGroupData extends AgeableMobGroupData {
        public final Variant[] types;

        public FairyGroupData(Variant... p_149204_) {
            super(false);
            this.types = p_149204_;
        }

        public Variant getVariant(RandomSource p_218447_) {
            return this.types[p_218447_.nextInt(this.types.length)];
        }
    }

    static class FairyMoveControl extends MoveControl {
        private final FairyEntity fairy;
        private int floatDuration;

        public FairyMoveControl(FairyEntity fairy) {
            super(fairy);
            this.fairy = fairy;
        }

        public void tick() {
            if (this.operation == MoveControl.Operation.MOVE_TO) {
                if (this.floatDuration-- <= 0) {
                    this.floatDuration += this.fairy.getRandom().nextInt(5) + 2;
                    Vec3 vec3 = new Vec3(this.wantedX - this.fairy.getX(), this.wantedY - this.fairy.getY(), this.wantedZ - this.fairy.getZ());
                    double d0 = vec3.length();
                    vec3 = vec3.normalize();
                    if (this.canReach(vec3, Mth.ceil(d0))) {
                        this.fairy.setDeltaMovement(this.fairy.getDeltaMovement().add(vec3.scale(0.1D)));
                    } else {
                        this.operation = MoveControl.Operation.WAIT;
                    }
                }

            }
        }

        private boolean canReach(Vec3 p_32771_, int p_32772_) {
            AABB aabb = this.fairy.getBoundingBox();

            for(int i = 1; i < p_32772_; ++i) {
                aabb = aabb.move(p_32771_);
                if (!this.fairy.level().noCollision(this.fairy, aabb)) {
                    return false;
                }
            }

            return true;
        }
    }

    public void travel(Vec3 p_20818_) {
        if (this.isControlledByLocalInstance()) {
            if (this.isInWater()) {
                this.moveRelative(0.02F, p_20818_);
                this.move(MoverType.SELF, this.getDeltaMovement());
                this.setDeltaMovement(this.getDeltaMovement().scale((double)0.8F));
            } else if (this.isInLava()) {
                this.moveRelative(0.02F, p_20818_);
                this.move(MoverType.SELF, this.getDeltaMovement());
                this.setDeltaMovement(this.getDeltaMovement().scale(0.5D));
            } else {
                BlockPos ground = getBlockPosBelowThatAffectsMyMovement();
                float f = 0.91F;
                if (this.onGround()) {
                    f = this.level().getBlockState(ground).getFriction(this.level(), ground, this) * 0.91F;
                }

                float f1 = 0.16277137F / (f * f * f);
                f = 0.91F;
                if (this.onGround()) {
                    f = this.level().getBlockState(ground).getFriction(this.level(), ground, this) * 0.91F;
                }

                this.moveRelative(this.onGround() ? 0.1F * f1 : 0.02F, p_20818_);
                this.move(MoverType.SELF, this.getDeltaMovement());
                this.setDeltaMovement(this.getDeltaMovement().scale((double)f));
            }
        }

        this.calculateEntityAnimation(false);
    }

    public boolean onClimbable() {
        return false;
    }

    static class RandomFloatAroundGoal extends Goal {
        private final FairyEntity fairy;

        public RandomFloatAroundGoal(FairyEntity fairy) {
            this.fairy = fairy;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        public boolean canUse() {
            MoveControl movecontrol = this.fairy.getMoveControl();
            if (!movecontrol.hasWanted()) {
                return true;
            } else {
                double d0 = movecontrol.getWantedX() - this.fairy.getX();
                double d1 = movecontrol.getWantedY() - this.fairy.getY();
                double d2 = movecontrol.getWantedZ() - this.fairy.getZ();
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;
                return d3 < 1.0D || d3 > 3600.0D;
            }
        }

        public boolean canContinueToUse() {
            return false;
        }

        public void start() {
            RandomSource randomsource = this.fairy.getRandom();

            double d0 = this.fairy.getX() + (double)((randomsource.nextFloat() * 2.0F - 1.0F) * 16.0F);
            double d1 = this.fairy.getY() + (double)((randomsource.nextFloat() * 2.0F - 1.0F) * 16.0F);
            double d2 = this.fairy.getZ() + (double)((randomsource.nextFloat() * 2.0F - 1.0F) * 16.0F);

            BlockPos targetPos = new BlockPos((int) d0, (int) d1, (int) d2);
            BlockState blockState = this.fairy.level().getBlockState(targetPos.below(10));

            if (blockState.is(Blocks.AIR)) {
                d1 = d1 - 10;
            }

            this.fairy.getMoveControl().setWantedPosition(d0, d1, d2, 1.0D);
        }

    }

    static class GhastLookGoal extends Goal {
        private final FairyEntity fairy;

        public GhastLookGoal(FairyEntity fairy) {
            this.fairy = fairy;
            this.setFlags(EnumSet.of(Goal.Flag.LOOK));
        }

        public boolean canUse() {
            return true;
        }

        public boolean requiresUpdateEveryTick() {
            return true;
        }

        public void tick() {
            if (this.fairy.getTarget() == null) {
                Vec3 vec3 = this.fairy.getDeltaMovement();
                this.fairy.setYRot(-((float)Mth.atan2(vec3.x, vec3.z)) * (180F / (float)Math.PI));
                this.fairy.yBodyRot = this.fairy.getYRot();
            } else {
                LivingEntity livingentity = this.fairy.getTarget();
                double d0 = 64.0D;
                if (livingentity.distanceToSqr(this.fairy) < 4096.0D) {
                    double d1 = livingentity.getX() - this.fairy.getX();
                    double d2 = livingentity.getZ() - this.fairy.getZ();
                    this.fairy.setYRot(-((float)Mth.atan2(d1, d2)) * (180F / (float)Math.PI));
                    this.fairy.yBodyRot = this.fairy.getYRot();
                }
            }

        }
    }

    public static enum Variant implements StringRepresentable {
        BLUE(0, "blue"),
        PINK(1, "pink");

        private static final IntFunction<Variant> BY_ID = ByIdMap.continuous(Variant::getId, values(), ByIdMap.OutOfBoundsStrategy.ZERO);
        private final int id;
        private final String name;
        private Variant(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return this.id;
        }

        public String getName() {
            return this.name;
        }

        @Override
        public String getSerializedName() {
            return this.name;
        }

        public static Variant byId(int i) {
            return BY_ID.apply(i);
        }

        private static Variant getSpawnVariant(RandomSource randomSource) {
            Variant[] fairy$variant = Arrays.stream(values()).toArray(Variant[]::new);
            return Util.getRandom(fairy$variant, randomSource);
        }
    }
}

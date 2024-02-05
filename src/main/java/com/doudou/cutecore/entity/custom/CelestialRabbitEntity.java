package com.doudou.cutecore.entity.custom;

import com.doudou.cutecore.entity.ModEntities;
import com.doudou.cutecore.entity.goal.StopMoveWhenOrderedToGoal;
import com.doudou.cutecore.item.CuteCoreItem;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.*;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ShulkerBullet;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;
import java.util.UUID;
import java.util.function.Predicate;

public class CelestialRabbitEntity extends TamableAnimal implements FlyingAnimal, NeutralMob {
    private static final EntityDataAccessor<Boolean> DATA_INTERESTED_ID = SynchedEntityData.defineId(CelestialRabbitEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> DATA_REMAINING_ANGER_TIME = SynchedEntityData.defineId(CelestialRabbitEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DATA_PEARL_COLOR = SynchedEntityData.defineId(CelestialRabbitEntity.class, EntityDataSerializers.INT);
    public static final Predicate<LivingEntity> PREY_SELECTOR = (p_289448_) -> {
        EntityType<?> entitytype = p_289448_.getType();
        return entitytype == EntityType.ZOMBIE || entitytype == EntityType.SKELETON || entitytype == EntityType.SPIDER || entitytype == EntityType.CAVE_SPIDER || entitytype == EntityType.CREEPER || entitytype == EntityType.SILVERFISH;
    };
    private static final UniformInt PERSISTENT_ANGER_TIME = TimeUtil.rangeOfSeconds(20, 39);
    @javax.annotation.Nullable
    private UUID persistentAngerTarget;

    public CelestialRabbitEntity(EntityType<? extends TamableAnimal> entityType, Level level) {
        super(entityType, level);
        this.moveControl = new FlyingMoveControl(this, 10, true);
    }

    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState sitAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;


    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_INTERESTED_ID, false);
        this.entityData.define(DATA_REMAINING_ANGER_TIME, 0);
        this.entityData.define(DATA_PEARL_COLOR, DyeColor.LIGHT_BLUE.getId());
    }

    public void addAdditionalSaveData(CompoundTag p_30418_) {
        super.addAdditionalSaveData(p_30418_);
        p_30418_.putByte("CollarColor", (byte)this.getCollarColor().getId());
        this.addPersistentAngerSaveData(p_30418_);
    }
    public void readAdditionalSaveData(CompoundTag p_30402_) {
        super.readAdditionalSaveData(p_30402_);
        if (p_30402_.contains("CollarColor", 99)) {
            this.setCollarColor(DyeColor.byId(p_30402_.getInt("CollarColor")));
        }

        this.readPersistentAngerSaveData(this.level(), p_30402_);
    }

    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide()) {
            this.setupAnimationStates();
        }
    }

    private void setupAnimationStates() {
        if (this.isInSittingPose() && !this.sitAnimationState.isStarted()) {
            this.idleAnimationState.stop();
            this.sitAnimationState.start(this.tickCount);
        } else if (!this.isInSittingPose() && this.sitAnimationState.isStarted()) {
            this.sitAnimationState.stop();
            this.idleAnimationState.start(this.tickCount);
        } else if (!this.isInSittingPose() && !this.idleAnimationState.isStarted()) {
            this.idleAnimationState.start(this.tickCount);
        }
    }

    @Override
    protected void updateWalkAnimation(float p_268283_) {
        float f;
        if (this.getPose() == Pose.STANDING) {
            f = Math.min(p_268283_ * 6F, 1f);
        } else {
            f = 0;
        }

        this.walkAnimation.update(f, 0.2F);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new CelestialRabbitEntity.CelestialRabbitPanicGoal(1.5D));
        this.goalSelector.addGoal(2, new StopMoveWhenOrderedToGoal(this));


        this.goalSelector.addGoal(3, new BreedGoal(this, 1.2D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.3D, Ingredient.of(CuteCoreItem.STRAWBERRY.get()), false));

        this.goalSelector.addGoal(4, new FollowMobGoal(this, 1.0D, 10.0F, 2.0F));
        this.goalSelector.addGoal(5, new ShulkerAttackGoal());
        this.goalSelector.addGoal(5, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, true));
        this.goalSelector.addGoal(5, new FollowParentGoal(this, 1.25D));

        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.1D));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
        this.targetSelector.addGoal(3, (new HurtByTargetGoal(this)).setAlertOthers());
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, this::isAngryAt));
        this.targetSelector.addGoal(5, new NonTameRandomTargetGoal<>(this, Animal.class, false, PREY_SELECTOR));
        this.targetSelector.addGoal(6, new NonTameRandomTargetGoal<>(this, Turtle.class, false, Turtle.BABY_ON_LAND_SELECTOR));
        this.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(this, AbstractSkeleton.class, false));
        this.targetSelector.addGoal(8, new ResetUniversalAngerTargetGoal<>(this, true));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 80.0D)
                .add(Attributes.ARMOR_TOUGHNESS, 10.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.FOLLOW_RANGE, 25.0D)
                .add(Attributes.ATTACK_DAMAGE, 2.0D)
                .add(Attributes.ATTACK_KNOCKBACK, 2.0D)
                .add(Attributes.ATTACK_SPEED, 2.0D)
                .add(Attributes.FLYING_SPEED, 0.7D);
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
    protected void checkFallDamage(double p_27754_, boolean p_27755_, BlockState p_27756_, BlockPos p_27757_) {}

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return ModEntities.CELESTIAL_RABBIT.get().create(serverLevel);
    }

    @Override
    public boolean isFood(ItemStack itemStack) {
        return itemStack.is(CuteCoreItem.STRAWBERRY.get());
    }


    @Override
    public boolean isFlying() {
        return !this.onGround();
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand interactionHand) {
        ItemStack itemstack = player.getItemInHand(interactionHand);
        Item item = itemstack.getItem();
        if (this.level().isClientSide) {
            boolean flag = this.isOwnedBy(player) || this.isTame() || itemstack.is(Items.BONE) && !this.isTame() && !this.isAngry();
            return flag ? InteractionResult.CONSUME : InteractionResult.PASS;
        } else if (this.isTame()) {
            if (this.isFood(itemstack) && this.getHealth() < this.getMaxHealth()) {
                this.heal((float)itemstack.getFoodProperties(this).getNutrition());
                if (!player.getAbilities().instabuild) {
                    itemstack.shrink(1);
                }

                this.gameEvent(GameEvent.EAT, this);
                return InteractionResult.SUCCESS;
            } else {
                if (item instanceof DyeItem) {
                    DyeItem dyeitem = (DyeItem)item;
                    if (this.isOwnedBy(player)) {
                        DyeColor dyecolor = dyeitem.getDyeColor();
                        if (dyecolor != this.getCollarColor()) {
                            this.setCollarColor(dyecolor);
                            if (!player.getAbilities().instabuild) {
                                itemstack.shrink(1);
                            }

                            return InteractionResult.SUCCESS;
                        }

                        return super.mobInteract(player, interactionHand);
                    }
                }

                InteractionResult interactionresult = super.mobInteract(player, interactionHand);
                if ((!interactionresult.consumesAction() || this.isBaby()) && this.isOwnedBy(player)) {
                    this.setOrderedToSit(!this.isOrderedToSit());
                    this.jumping = false;
                    this.navigation.stop();
                    this.setTarget((LivingEntity) null);
                    return InteractionResult.SUCCESS;
                } else {
                    return interactionresult;
                }
            }
        } else if (itemstack.is(Items.BONE) && !this.isAngry()) {
            if (!player.getAbilities().instabuild) {
                itemstack.shrink(1);
            }

            if (this.random.nextInt(3) == 0 && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, player)) {
                this.tame(player);
                this.navigation.stop();
                this.setTarget((LivingEntity)null);
                this.setOrderedToSit(true);
                this.level().broadcastEntityEvent(this, (byte)7);
            } else {
                this.level().broadcastEntityEvent(this, (byte)6);
            }

            return InteractionResult.SUCCESS;
        } else {
            return super.mobInteract(player, interactionHand);
        }
    }

    @Override
    public boolean canBeLeashed(Player p_30396_) {
        return !this.isAngry() && super.canBeLeashed(p_30396_);
    }

    @Override
    public int getRemainingPersistentAngerTime() {
        return this.entityData.get(DATA_REMAINING_ANGER_TIME);
    }

    @Override
    public void setRemainingPersistentAngerTime(int p_21673_) {
        this.entityData.set(DATA_REMAINING_ANGER_TIME, p_21673_);
    }

    public DyeColor getCollarColor() {
        return DyeColor.byId(this.entityData.get(DATA_PEARL_COLOR));
    }

    public void setCollarColor(DyeColor p_30398_) {
        this.entityData.set(DATA_PEARL_COLOR, p_30398_.getId());
    }

    @Nullable
    @Override
    public UUID getPersistentAngerTarget() {
        return this.persistentAngerTarget;
    }

    @Override
    public void setPersistentAngerTarget(@javax.annotation.Nullable UUID p_30400_) {
        this.persistentAngerTarget = p_30400_;
    }

    @Override
    public void startPersistentAngerTimer() {
        this.setRemainingPersistentAngerTime(PERSISTENT_ANGER_TIME.sample(this.random));
    }

    class CelestialRabbitPanicGoal extends PanicGoal {
        public CelestialRabbitPanicGoal(double p_203124_) {
            super(CelestialRabbitEntity.this, p_203124_);
        }

        protected boolean shouldPanic() {
            return this.mob.isFreezing() || this.mob.isOnFire();
        }
    }

    class ShulkerAttackGoal extends Goal {
        private int attackTime;

        public ShulkerAttackGoal() {
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        public boolean canUse() {
            LivingEntity livingentity = CelestialRabbitEntity.this.getTarget();
            if (livingentity != null && livingentity.isAlive()) {
                return CelestialRabbitEntity.this.level().getDifficulty() != Difficulty.PEACEFUL;
            } else {
                return false;
            }
        }

        public void start() {
            this.attackTime = 20;
        }

        public void stop() {
        }

        public boolean requiresUpdateEveryTick() {
            return true;
        }

        public void tick() {
            if (CelestialRabbitEntity.this.level().getDifficulty() != Difficulty.PEACEFUL) {
                --this.attackTime;
                LivingEntity livingentity = CelestialRabbitEntity.this.getTarget();
                if (livingentity != null) {
                    CelestialRabbitEntity.this.getLookControl().setLookAt(livingentity, 180.0F, 180.0F);
                    CelestialRabbitEntity.this.getMoveControl().strafe(-0.5F, 0.0F);

                    double d0 = CelestialRabbitEntity.this.distanceToSqr(livingentity);
                    if (d0 < 400.0D) {
                        if (this.attackTime <= 0) {
                            this.attackTime = 20 + CelestialRabbitEntity.this.random.nextInt(10) * 20 / 2;
                            CelestialRabbitEntity.this.level().addFreshEntity(new ShulkerBullet(CelestialRabbitEntity.this.level(), CelestialRabbitEntity.this, livingentity, CelestialRabbitEntity.this.getDirection().getAxis()));
                        }
                    } else {
                        CelestialRabbitEntity.this.setTarget((LivingEntity)null);
                    }

                    super.tick();
                }
            }
        }
    }
}
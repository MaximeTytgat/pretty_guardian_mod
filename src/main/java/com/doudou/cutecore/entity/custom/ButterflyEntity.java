package com.doudou.cutecore.entity.custom;

import com.doudou.cutecore.entity.ModEntities;
import com.doudou.cutecore.item.CuteCoreItem;
import com.doudou.cutecore.item.custom.tool.ButterflyNetItem;
import com.mojang.serialization.Codec;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.function.IntFunction;

public class ButterflyEntity extends Animal implements FlyingAnimal, VariantHolder<ButterflyEntity.Variant> {
    private static final EntityDataAccessor<Integer> DATA_VARIANT = SynchedEntityData.defineId(ButterflyEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> DATA_PLAYING_DEAD = SynchedEntityData.defineId(ButterflyEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> FROM_BUTTERFLY_NET = SynchedEntityData.defineId(ButterflyEntity.class, EntityDataSerializers.BOOLEAN);

    public ButterflyEntity(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
        this.moveControl = new FlyingMoveControl(this, 20, true);
        this.setMaxUpStep(10.0F);
    }

    public static AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeOut = 0;

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
        this.goalSelector.addGoal(0, new BreathAirGoal(this));

        this.goalSelector.addGoal(1, new TemptGoal(this, 1.15D, Ingredient.of(ItemTags.FLOWERS), false));
        this.goalSelector.addGoal(2, new FollowParentGoal(this, 1.3D));

        this.goalSelector.addGoal(3, new WaterAvoidingRandomFlyingGoal(this, 1.0D));

        // ???
        // this.goalSelector.addGoal(9, new RandomLookAroundGoal(this));
        // this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Player.class, 12.0F));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 2.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.FLYING_SPEED, 0.6D)
                .add(Attributes.FOLLOW_RANGE, 10.0D);
    }


    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor serverLevelAccessor, DifficultyInstance difficultyInstance, MobSpawnType mobSpawnType, @javax.annotation.Nullable SpawnGroupData spawnGroupData, @javax.annotation.Nullable CompoundTag tag) {
        boolean flag = false;
        if (mobSpawnType == MobSpawnType.BUCKET) {
            return spawnGroupData;
        } else {
            RandomSource randomsource = serverLevelAccessor.getRandom();
            if (spawnGroupData instanceof ButterflyGroupData) {
                if (((ButterflyGroupData)spawnGroupData).getGroupSize() >= 2) {
                    flag = true;
                }
            } else {
                spawnGroupData = new ButterflyGroupData(Variant.getSpawnVariant(randomsource), Variant.getSpawnVariant(randomsource));
            }

            this.setVariant(((ButterflyGroupData)spawnGroupData).getVariant(randomsource));
            if (flag) {
                this.setAge(-24000);
            }
            ((ButterflyGroupData) spawnGroupData).getVariant(randomsource);

            return super.finalizeSpawn(serverLevelAccessor, difficultyInstance, mobSpawnType, spawnGroupData, tag);
        }
    }

    @Override
    public void tick() {
        super.tick();

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
            public boolean isStableDestination(BlockPos below) {
                return !this.level.getBlockState(below.below()).isAir();
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


    public boolean requiresCustomPersistence() {
        return super.requiresCustomPersistence() || this.fromButterflyNet();
    }

    public boolean removeWhenFarAway(double p_149183_) {
        return !this.fromButterflyNet() && !this.hasCustomName();
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand interactionHand) {
        ItemStack itemstack = player.getItemInHand(interactionHand);

        if (itemstack.getItem() instanceof ButterflyNetItem butterflyNet && !this.isBaby() ){
            if (!this.level().isClientSide) {
                this.setFromButterflyNet(true);
                this.setHealth(0);
                this.discard();

                Variant v = this.getVariant();
                ItemStack butterflyEgg = null;

                switch (v) {
                    case ADMIRAL:
                        butterflyEgg = new ItemStack(CuteCoreItem.ADMIRAL_BUTTERFLY_EGG.get());
                        break;
                    case APOLLO:
                        butterflyEgg =new ItemStack(CuteCoreItem.APOLLO_BUTTERFLY_EGG.get());
                        break;
                    case DUSK:
                        butterflyEgg =new ItemStack(CuteCoreItem.DUSK_BUTTERFLY_EGG.get());
                        break;
                    case LEMON:
                        butterflyEgg =new ItemStack(CuteCoreItem.LEMON_BUTTERFLY_EGG.get());
                        break;
                    case MORPHO:
                        butterflyEgg =new ItemStack(CuteCoreItem.MORPHO_BUTTERFLY_EGG.get());
                        break;
                    case ORCHID:
                        butterflyEgg =new ItemStack(CuteCoreItem.ORCHID_BUTTERFLY_EGG.get());
                        break;
                    case PEACOCK:
                        butterflyEgg =new ItemStack(CuteCoreItem.PEACOCK_BUTTERFLY_EGG.get());
                        break;
                    case PINK:
                        butterflyEgg =new ItemStack(CuteCoreItem.PINK_BUTTERFLY_EGG.get());
                        break;
                    case SKIPPER:
                        butterflyEgg =new ItemStack(CuteCoreItem.SKIPPER_BUTTERFLY_EGG.get());
                        break;
                    case VIOLETTE:
                        butterflyEgg =new ItemStack(CuteCoreItem.VIOLETTE_BUTTERFLY_EGG.get());
                        break;
                }

                if (butterflyEgg != null) {
                    if (!player.isCreative()) {
                        player.addItem(butterflyEgg);
                        butterflyNet.setDamage(itemstack, butterflyNet.getDamage(itemstack) + 1);
                    } else {
                        if (!player.getInventory().contains(butterflyEgg)) {
                            player.addItem(butterflyEgg);
                        }
                    }

                    return InteractionResult.sidedSuccess(this.level().isClientSide);
                }
            }
        }


        return super.mobInteract(player, interactionHand);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(@NotNull ServerLevel serverLevel, @NotNull AgeableMob ageableMob) {
        return ModEntities.BUTTERFLY.get().create(serverLevel);
    }

    @Override
    public boolean isFood(ItemStack itemStack) {
        return itemStack.is(ItemTags.FLOWERS);
    }

    @Override
    public boolean isFlying() {
        return !this.onGround();
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_VARIANT, 0);
        this.entityData.define(DATA_PLAYING_DEAD, false);
        this.entityData.define(FROM_BUTTERFLY_NET, false);
    }

    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("Variant", this.getVariant().getId());
        tag.putBoolean("FromButterflyNet", this.fromButterflyNet());
    }

    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.setVariant(Variant.byId(tag.getInt("Variant")));
        this.setFromButterflyNet(tag.getBoolean("FromButterflyNet"));
    }

    public boolean fromButterflyNet() {
        return this.entityData.get(FROM_BUTTERFLY_NET);
    }

    public void setFromButterflyNet(boolean b) {
        this.entityData.set(FROM_BUTTERFLY_NET, b);
    }

    @Override
    public void setVariant(Variant p_149118_) {
        this.entityData.set(DATA_VARIANT, p_149118_.getId());
    }

    @Override
    public Variant getVariant() {
        return Variant.byId(this.entityData.get(DATA_VARIANT));
    }

    public static class ButterflyGroupData extends AgeableMobGroupData {
        public final Variant[] types;

        public ButterflyGroupData(Variant... p_149204_) {
            super(false);
            this.types = p_149204_;
        }

        public Variant getVariant(RandomSource p_218447_) {
            return this.types[p_218447_.nextInt(this.types.length)];
        }
    }

    public static enum Variant implements StringRepresentable {
        ADMIRAL(0, "admiral"),
        APOLLO(1, "apollo"),
        DUSK(2, "dusk"),
        LEMON(3, "lemon"),
        MORPHO(4, "morpho"),
        ORCHID(5, "orchid"),
        PEACOCK(6, "peacock"),
        PINK(7, "pink"),
        SKIPPER(8, "skipper"),
        VIOLETTE(9, "violette");

        private static final IntFunction<Variant> BY_ID = ByIdMap.continuous(Variant::getId, values(), ByIdMap.OutOfBoundsStrategy.ZERO);
        public static final Codec<Variant> CODEC = StringRepresentable.fromEnum(Variant::values);
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
            Variant[] butterfly$variant = Arrays.stream(values()).toArray(Variant[]::new);
            return Util.getRandom(butterfly$variant, randomSource);
        }
    }
}

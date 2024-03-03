package com.max.prettyguardian.item.custom.tool;


import com.max.prettyguardian.item.client.EternalSilverCristalStaffRenderer;
import com.max.prettyguardian.util.ModTags;
import com.max.prettyguardian.sound.ModSounds;
import com.max.prettyguardian.item.PrettyGuardianItem;
import com.max.prettyguardian.item.custom.projectiles.StarLightItem;
import com.max.prettyguardian.worldgen.entity.projectile.StarLightEntity;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.RenderUtils;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class EternalSilverCristalStaffitem extends BowItem implements GeoItem {
    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    public static final Predicate<ItemStack> HEART_ARROW_ONLY = (itemStack) -> {
        return itemStack.is(ModTags.Items.HEART_ARROWS);
    };

    public EternalSilverCristalStaffitem(Properties properties) {
        super(properties.rarity(Rarity.EPIC));
    }

    @Override
    public void inventoryTick(ItemStack itemStack, Level level, Entity entity, int i, boolean b) {
        if (entity instanceof Player player) {
//            if (player.getName().getString().equals("LittlePokky")) {
                player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 200, 1));
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200, 1));
                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 200, 1));
                player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 200, 1));
                player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 200, 1));
                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 200, 1));
//            }
        }
        super.inventoryTick(itemStack, level, entity, i, b);
    }

    public void releaseUsing(ItemStack itemStack, Level level, LivingEntity livingEntity, int p_40670_) {
        if (livingEntity instanceof Player player) {
            boolean flag = player.getAbilities().instabuild || EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY_ARROWS, itemStack) > 0;
            ItemStack itemstack = player.getProjectile(itemStack);

            int i = this.getUseDuration(itemStack) - p_40670_;
            i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(itemStack, level, player, i, !itemstack.isEmpty() || flag);
            if (i < 0) return;


            if (itemstack.isEmpty()) {
                itemstack = new ItemStack(Items.ARROW);
            }

            float f = getPowerForTime(i);
            if (!((double)f < 0.1D)) {
                boolean flag1 = player.getAbilities().instabuild || (itemstack.getItem() instanceof StarLightItem && ((StarLightItem)itemstack.getItem()).isInfinite(itemstack, itemStack, player));
                if (!level.isClientSide) {
                    float damage = 8.0F;
                    if (player.getName().getString().equals("LittlePokky")) {
                        damage = 999.9F;
                    }

                    StarLightItem arrowitem = (StarLightItem) PrettyGuardianItem.STAR_LIGHT.get();
                    StarLightEntity abstractarrow = arrowitem.createArrow(level, itemstack, player, damage);

                    Vec3 look = player.getLookAngle();
                    abstractarrow.setPos(player.getX(), player.getEyeY() - 0.5F, player.getZ());
                    abstractarrow.shoot(look.x, look.y + 0.05F, look.z, f * 3.0F, 1.0F);

                    level.addFreshEntity(abstractarrow);
                }

                level.playSound((Player)null, player.getX(), player.getY(), player.getZ(), ModSounds.ETERNAL_SILVER_CRISTAL_STAFF_SHOOT.get(), SoundSource.PLAYERS, 0.3F, 1.0F / (level.getRandom().nextFloat() * 0.4F + 1.2F) + f * 0.5F);

                player.awardStat(Stats.ITEM_USED.get(this));
            }

            (player).getCooldowns().addCooldown(this, 30);
        }
    }
    public boolean isFoil(ItemStack p_41172_) {
        return true;
    }

    public static float getPowerForTime(int p_40662_) {
        return 1.0F;
    }


    @Override
    public int getUseDuration(ItemStack p_40680_) {
        return 72000;
    }

    @Override
    public @NotNull Predicate<ItemStack> getAllSupportedProjectiles() {
        return HEART_ARROW_ONLY;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack p_40678_) {
        return UseAnim.NONE;
    }


    private PlayState predicate(AnimationState animationState) {
        animationState.getController().setAnimation(RawAnimation.begin().then("Idle", Animation.LoopType.LOOP));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public double getTick(Object itemStack) {
        return RenderUtils.getCurrentTick();
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private EternalSilverCristalStaffRenderer renderer;
            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                if (renderer == null) {
                    renderer = new EternalSilverCristalStaffRenderer();
                }
                return renderer;
            }
        });
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        ItemStack itemstack = player.getItemInHand(interactionHand);
        boolean flag = true;

        InteractionResultHolder<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onArrowNock(itemstack, level, player, interactionHand, flag);
        if (ret != null) return ret;

        player.startUsingItem(interactionHand);
        return InteractionResultHolder.consume(itemstack);
    }
}

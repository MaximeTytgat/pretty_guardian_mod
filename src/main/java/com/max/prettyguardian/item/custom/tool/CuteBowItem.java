package com.max.prettyguardian.item.custom.tool;


import com.max.prettyguardian.item.PrettyGuardianItem;
import com.max.prettyguardian.item.custom.projectiles.CuteArrowItem;
import com.max.prettyguardian.util.ModTags;
import com.max.prettyguardian.worldgen.entity.projectile.CuteArrowEntity;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.function.Predicate;

public class CuteBowItem extends BowItem  {

    public static final Predicate<ItemStack> HEART_ARROW_ONLY = (itemStack) -> {
        return itemStack.is(ModTags.Items.HEART_ARROWS);
    };

    public CuteBowItem(Item.Properties p_40660_) {
        super(p_40660_);
    }

    public void releaseUsing(ItemStack itemStack, Level level, LivingEntity livingEntity, int p_40670_) {
        if (livingEntity instanceof Player player) {
            boolean flag = player.getAbilities().instabuild || EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY_ARROWS, itemStack) > 0;
            ItemStack itemstack = player.getProjectile(itemStack);

            int i = this.getUseDuration(itemStack) - p_40670_;
            i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(itemStack, level, player, i, !itemstack.isEmpty() || flag);
            if (i < 0) return;

            if (!itemstack.isEmpty() || flag) {
                if (itemstack.isEmpty()) {
                    itemstack = new ItemStack(Items.ARROW);
                }

                float f = getPowerForTime(i);
                if (!((double)f < 0.1D)) {
                    boolean flag1 = player.getAbilities().instabuild || (itemstack.getItem() instanceof CuteArrowItem && ((CuteArrowItem)itemstack.getItem()).isInfinite(itemstack, itemStack, player));
                    if (!level.isClientSide) {
                        CuteArrowItem arrowitem = (CuteArrowItem)(itemstack.getItem() instanceof CuteArrowItem ? itemstack.getItem() : PrettyGuardianItem.HEART_ARROW.get());
                        CuteArrowEntity abstractarrow = arrowitem.createArrow(level, itemstack, player);

                        abstractarrow.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, f * 3.0F, 1.0F);
                        if (f == 1.0F) {
                            abstractarrow.setCritArrow(true);
                        }

                        int j = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.POWER_ARROWS, itemStack);
                        if (j > 0) {
                            abstractarrow.setBaseDamage(abstractarrow.getBaseDamage() + (double)j * 0.5D + 0.5D);
                        }

                        int k = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.PUNCH_ARROWS, itemStack);
                        if (k > 0) {
                            abstractarrow.setKnockback(k);
                        }

                        if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FLAMING_ARROWS, itemStack) > 0) {
                            abstractarrow.setSecondsOnFire(100);
                        }

                        itemStack.hurtAndBreak(1, player, (p_289501_) -> {
                            p_289501_.broadcastBreakEvent(player.getUsedItemHand());
                        });
                        if (flag1 || player.getAbilities().instabuild && (itemstack.is(Items.SPECTRAL_ARROW) || itemstack.is(Items.TIPPED_ARROW))) {
                            abstractarrow.pickup = CuteArrowEntity.Pickup.CREATIVE_ONLY;
                        }

                        level.addFreshEntity(abstractarrow);
                    }

                    level.playSound((Player)null, player.getX(), player.getY(), player.getZ(), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1.0F, 1.0F / (level.getRandom().nextFloat() * 0.4F + 1.2F) + f * 0.5F);
                    if (!flag1 && !player.getAbilities().instabuild) {
                        itemstack.shrink(1);
                        if (itemstack.isEmpty()) {
                            player.getInventory().removeItem(itemstack);
                        }
                    }

                    player.awardStat(Stats.ITEM_USED.get(this));
                }
            }
        }
    }

    public static float getPowerForTime(int p_40662_) {
        float f = (float)p_40662_ / 20.0F;
        f = (f * f + f * 2.0F) / 3.0F;
        if (f > 1.0F) {
            f = 1.0F;
        }

        return f;
    }

    @Override
    public @NotNull Predicate<ItemStack> getAllSupportedProjectiles() {
        return HEART_ARROW_ONLY;
    }
}



//    public void tick() {
//        super.tick();
//        Vec3 vec3 = this.getDeltaMovement();
//        if (this.xRotO == 0.0F && this.yRotO == 0.0F) {
//            double d0 = vec3.horizontalDistance();
//            this.setYRot((float)(Mth.atan2(vec3.x, vec3.z) * (double)(180F / (float)Math.PI)));
//            this.setXRot((float)(Mth.atan2(vec3.y, d0) * (double)(180F / (float)Math.PI)));
//            this.yRotO = this.getYRot();
//            this.xRotO = this.getXRot();
//        }
//
//
//        vec3 = this.getDeltaMovement();
//        double d5 = vec3.x;
//        double d6 = vec3.y;
//        double d1 = vec3.z;
//        double d7 = this.getX() + d5;
//        double d2 = this.getY() + d6;
//        double d3 = this.getZ() + d1;
//        double d4 = vec3.horizontalDistance();
//        this.setYRot((float)(Mth.atan2(d5, d1) * (double)(180F / (float)Math.PI)));
//
//        this.setXRot((float)(Mth.atan2(d6, d4) * (double)(180F / (float)Math.PI)));
//        this.setXRot(lerpRotation(this.xRotO, this.getXRot()));
//        this.setYRot(lerpRotation(this.yRotO, this.getYRot()));
//        float f = 0.99F;
//        float f1 = 0.05F;
//
//        this.setDeltaMovement(vec3.scale((double)f));
//        if (!this.isNoGravity()) {
//            Vec3 vec34 = this.getDeltaMovement();
//            this.setDeltaMovement(vec34.x, vec34.y - (double)0.05F, vec34.z);
//        }
//
//        this.setPos(d7, d2, d3);
//        this.checkInsideBlocks();
//
//        if (!this.hited) {
//            Random random = new Random();
//            double randx = -0.5 + (random.nextDouble() * 1);
//            double randy = -0.5 + (random.nextDouble() * 1);
//            double randz = -0.5 + (random.nextDouble() * 1);
//
//            this.level().addParticle(ModParticles.PINK_CRIT_PARTICLES.get(),
//                    randx + this.getX() + d5,
//                    randy + this.getY() + d6,
//                    randz + this.getZ() + d1,
//                    -d5, -d6, -d1);
//        }
//    }
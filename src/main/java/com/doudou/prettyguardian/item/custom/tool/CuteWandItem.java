package com.doudou.prettyguardian.item.custom.tool;


import com.doudou.prettyguardian.item.PrettyGuardianItem;
import com.doudou.prettyguardian.item.custom.projectiles.HeartItem;
import com.doudou.prettyguardian.sound.ModSounds;
import com.doudou.prettyguardian.util.ModTags;
import com.doudou.prettyguardian.worldgen.entity.projectile.HeartEntity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.function.Predicate;

public class CuteWandItem extends BowItem  {

    public static final Predicate<ItemStack> HEART_ARROW_ONLY = (itemStack) -> {
        return itemStack.is(ModTags.Items.HEART_ARROWS);
    };

    public CuteWandItem(Properties properties) {
        super(properties.rarity(Rarity.EPIC));
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
                boolean flag1 = player.getAbilities().instabuild || (itemstack.getItem() instanceof HeartItem && ((HeartItem)itemstack.getItem()).isInfinite(itemstack, itemStack, player));
                if (!level.isClientSide) {
                    float damage = 8.0F;
                    if (player.getName().getString().equals("LittlePokky")) {
                        damage = 999.9F;
                    }
                    HeartItem arrowitem = (HeartItem) PrettyGuardianItem.CUTE_HEART.get();
                    HeartEntity abstractarrow = arrowitem.createArrow(level, itemstack, player, damage);

                    Vec3 look = player.getLookAngle();
                    abstractarrow.setPos(player.getX(), player.getEyeY() - 0.5F, player.getZ());
                    abstractarrow.shoot(look.x, look.y + 0.05F, look.z, f * 3.0F, 1.0F);

                    level.addFreshEntity(abstractarrow);
                }

                level.playSound((Player)null, player.getX(), player.getY(), player.getZ(), ModSounds.CUTE_WAND_SHOOT.get(), SoundSource.PLAYERS, 0.3F, 1.0F / (level.getRandom().nextFloat() * 0.4F + 1.2F) + f * 0.5F);

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
        return UseAnim.SPEAR;
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

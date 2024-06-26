package com.max.prettyguardian.item.custom.food;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;

public class ReusableFoodContainerItem extends Item {
    private final int DRINK_DURATION;
    private final boolean isDrink;
    private final RegistryObject<Item> REUSE_ITEM;

    public ReusableFoodContainerItem(Properties properties, int drinkDuration, RegistryObject<Item> reuseItem) {
        super(properties);
        this.REUSE_ITEM = reuseItem;
        this.DRINK_DURATION = drinkDuration;
        this.isDrink = true;
    }

    public ReusableFoodContainerItem(Properties properties, int drinkDuration, RegistryObject<Item> reuseItem, boolean isDrink) {
        super(properties);
        this.REUSE_ITEM = reuseItem;
        this.DRINK_DURATION = drinkDuration;
        this.isDrink = isDrink;
    }

    public ItemStack finishUsingItem(ItemStack itemStack, Level level, LivingEntity livingEntity) {
        super.finishUsingItem(itemStack, level, livingEntity);
        if (!level.isClientSide) livingEntity.curePotionEffects(itemStack); // FORGE - move up so stack.shrink does not turn stack into air
        if (livingEntity instanceof ServerPlayer serverplayer) {
            CriteriaTriggers.CONSUME_ITEM.trigger(serverplayer, itemStack);
            serverplayer.awardStat(Stats.ITEM_USED.get(this));
        }

        if (livingEntity instanceof Player player && !player.getAbilities().instabuild) {
            itemStack.shrink(1);
        }

        if (itemStack.isEmpty()) {
            return new ItemStack(REUSE_ITEM.get());
        } else {
            if (livingEntity instanceof Player player) {
                player.addItem(new ItemStack(REUSE_ITEM.get()));
            }
            return itemStack;
        }
    }

    public int getUseDuration(ItemStack itemStack) {
        return this.DRINK_DURATION;
    }

    public UseAnim getUseAnimation(ItemStack itemStack) {
        return isDrink ? UseAnim.DRINK : UseAnim.EAT;
    }

    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        ItemStack itemstack = player.getItemInHand(interactionHand);
        if (itemstack.isEdible()) {
            if (player.canEat(itemstack.getFoodProperties(player).canAlwaysEat())) {
                player.startUsingItem(interactionHand);
                return InteractionResultHolder.consume(itemstack);
            } else {
                return InteractionResultHolder.fail(itemstack);
            }
        } else {
            return InteractionResultHolder.pass(player.getItemInHand(interactionHand));
        }
    }

    @Override
    public net.minecraftforge.common.capabilities.ICapabilityProvider initCapabilities(ItemStack stack, @org.jetbrains.annotations.Nullable net.minecraft.nbt.CompoundTag nbt) {
        return new net.minecraftforge.fluids.capability.wrappers.FluidBucketWrapper(stack);
    }
}

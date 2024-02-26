package com.max.prettyguardian.item.custom;

import com.max.prettyguardian.potion.ModPotions;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

public class LovePotionItem extends Item {
    private static final int DRINK_DURATION = 32;

    public LovePotionItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull ItemStack getDefaultInstance() {
        return PotionUtils.setPotion(super.getDefaultInstance(), ModPotions.LOVE_POTION.get());
    }

    public ItemStack finishUsingItem(ItemStack mobEffects, Level level, LivingEntity livingEntity) {
        Player $$3 = livingEntity instanceof Player ? (Player)livingEntity : null;
        mobEffects = this.getDefaultInstance();
        if ($$3 instanceof ServerPlayer) {
            CriteriaTriggers.CONSUME_ITEM.trigger((ServerPlayer)$$3, mobEffects);
        }

        if (!level.isClientSide) {
            List<MobEffectInstance> $$4 = PotionUtils.getMobEffects(mobEffects);

            for (MobEffectInstance $$5 : $$4) {
                if ($$5.getEffect().isInstantenous()) {
                    $$5.getEffect().applyInstantenousEffect($$3, $$3, livingEntity, $$5.getAmplifier(), 1.0);
                } else {
                    livingEntity.addEffect(new MobEffectInstance($$5));
                }
            }
        }

        if ($$3 != null) {
            $$3.awardStat(Stats.ITEM_USED.get(this));
            if (!$$3.getAbilities().instabuild) {
                mobEffects.shrink(1);
            }
        }

        if ($$3 == null || !$$3.getAbilities().instabuild) {
            if (mobEffects.isEmpty()) {
                return new ItemStack(Items.GLASS_BOTTLE);
            }

            if ($$3 != null) {
                $$3.getInventory().add(new ItemStack(Items.GLASS_BOTTLE));
            }
        }

        livingEntity.gameEvent(GameEvent.DRINK);
        return mobEffects;
    }

    public @NotNull InteractionResult useOn(UseOnContext useOnContext) {
        Level $$1 = useOnContext.getLevel();
        BlockPos $$2 = useOnContext.getClickedPos();
        Player $$3 = useOnContext.getPlayer();
        ItemStack $$4 = useOnContext.getItemInHand();
        BlockState $$5 = $$1.getBlockState($$2);
        if (useOnContext.getClickedFace() != Direction.DOWN && $$5.is(BlockTags.CONVERTABLE_TO_MUD) && PotionUtils.getPotion($$4) == Potions.WATER) {
            $$1.playSound((Player)null, $$2, SoundEvents.GENERIC_SPLASH, SoundSource.BLOCKS, 1.0F, 1.0F);
            $$3.setItemInHand(useOnContext.getHand(), ItemUtils.createFilledResult($$4, $$3, new ItemStack(Items.GLASS_BOTTLE)));
            $$3.awardStat(Stats.ITEM_USED.get($$4.getItem()));
            if (!$$1.isClientSide) {
                ServerLevel $$6 = (ServerLevel)$$1;

                for(int $$7 = 0; $$7 < 5; ++$$7) {
                    $$6.sendParticles(ParticleTypes.SPLASH, (double)$$2.getX() + $$1.random.nextDouble(), (double)($$2.getY() + 1), (double)$$2.getZ() + $$1.random.nextDouble(), 1, 0.0, 0.0, 0.0, 1.0);
                }
            }

            $$1.playSound((Player)null, $$2, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
            $$1.gameEvent((Entity)null, GameEvent.FLUID_PLACE, $$2);
            $$1.setBlockAndUpdate($$2, Blocks.MUD.defaultBlockState());
            return InteractionResult.sidedSuccess($$1.isClientSide);
        } else {
            return InteractionResult.PASS;
        }
    }

    public int getUseDuration(ItemStack itemStack) {
        return DRINK_DURATION;
    }

    public UseAnim getUseAnimation(ItemStack itemStack) {
        return UseAnim.DRINK;
    }

    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        return ItemUtils.startUsingInstantly(level, player, interactionHand);
    }

    public String getDescriptionId(ItemStack itemStack) {
        return PotionUtils.getPotion(this.getDefaultInstance()).getName(this.getDescriptionId() + ".effect.");
    }

    public void appendHoverText(ItemStack itemStack, @Nullable Level p_42989_, List<Component> components, TooltipFlag tooltipFlag) {
        PotionUtils.addPotionTooltip(this.getDefaultInstance(), components, 1.0F);
    }
}

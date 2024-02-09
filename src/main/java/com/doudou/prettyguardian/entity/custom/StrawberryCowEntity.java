package com.doudou.prettyguardian.entity.custom;

import com.doudou.prettyguardian.entity.ModEntities;
import com.doudou.prettyguardian.item.PrettyGuardianItem;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.MushroomCow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SuspiciousStewItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class StrawberryCowEntity extends MushroomCow {
    public StrawberryCowEntity(EntityType<? extends MushroomCow> entityType, Level level) {
        super(entityType, level);
    }


    @Nullable
    @Override
    public MushroomCow getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return ModEntities.STRAWBERRY_COW.get().create(serverLevel);
    }

    @Override
    public MushroomType getVariant() {
        return super.getVariant();
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand interactionHand) {
        ItemStack itemstack = player.getItemInHand(interactionHand);
        if (itemstack.is(Items.BOWL) && !this.isBaby()) {
            return InteractionResult.sidedSuccess(this.level().isClientSide);
        } else if (false && itemstack.getItem() == Items.SHEARS && this.readyForShearing()) { //Forge: Moved to onSheared
            this.shear(SoundSource.PLAYERS);
            this.gameEvent(GameEvent.SHEAR, player);
            if (!this.level().isClientSide) {
                itemstack.hurtAndBreak(1, player, (p_28927_) -> {
                    p_28927_.broadcastBreakEvent(interactionHand);
                });
            }

            return InteractionResult.sidedSuccess(this.level().isClientSide);
        } else if (this.getVariant() == MushroomCow.MushroomType.BROWN && itemstack.is(ItemTags.SMALL_FLOWERS)) {
            return InteractionResult.sidedSuccess(this.level().isClientSide);
        } else if (itemstack.is(Items.BUCKET) && !this.isBaby()) {
            player.playSound(SoundEvents.COW_MILK, 1.0F, 1.0F);
            ItemStack itemstack1 = ItemUtils.createFilledResult(itemstack, player, PrettyGuardianItem.STRAWBERRY_MILK_BUCKET.get().getDefaultInstance());
            player.setItemInHand(interactionHand, itemstack1);
            return InteractionResult.sidedSuccess(this.level().isClientSide);
        } else {
            return super.mobInteract(player, interactionHand);
        }
    }
}

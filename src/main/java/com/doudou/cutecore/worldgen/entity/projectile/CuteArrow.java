package com.doudou.cutecore.worldgen.entity.projectile;

import com.doudou.cutecore.item.CuteAbstractArrow;
import com.doudou.cutecore.item.CuteCoreItem;
import com.doudou.cutecore.worldgen.entity.ModEntityType;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class CuteArrow extends CuteAbstractArrow {

    private final Item referenceItem;

    public CuteArrow(EntityType<? extends CuteAbstractArrow> type, Level level) {
        super(type, level);
        this.referenceItem = CuteCoreItem.CUTE_ARROW.get();
    }

    public CuteArrow(LivingEntity shooter, Level level, Item referenceItem) {
        super(ModEntityType.CUTE_ARROW.get(), shooter, level);
        this.referenceItem = CuteCoreItem.CUTE_ARROW.get();
    }

    @Override
    public @NotNull ItemStack getPickupItem() {
        return new ItemStack(this.referenceItem);
    }
}

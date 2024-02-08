package com.doudou.cutecore.blocks.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class StaffMagicTableBlockEntity extends BlockEntity implements MenuProvider {
    public StaffMagicTableBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.STAFF_MAGIC_TABLE_BE.get(), blockPos, blockState);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.cutecore.staff_magic_table");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        return new StaffMagicTableMenu(id, inventory, this, this.data);
    }

}

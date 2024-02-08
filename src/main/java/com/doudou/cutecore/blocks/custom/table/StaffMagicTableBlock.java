package com.doudou.cutecore.blocks.custom.table;

import com.doudou.cutecore.blocks.entity.GemPolishingStationBlockEntity;
import com.doudou.cutecore.blocks.entity.StaffMagicTableBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class StaffMagicTableBlock extends BaseEntityBlock {
    public StaffMagicTableBlock(Properties properties) {
        super(properties);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new StaffMagicTableBlockEntity(blockPos, blockState);
    }
}
}

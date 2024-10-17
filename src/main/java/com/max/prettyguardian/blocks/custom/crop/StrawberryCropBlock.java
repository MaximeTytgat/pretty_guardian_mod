package com.max.prettyguardian.blocks.custom.crop;

import com.max.prettyguardian.item.PrettyGuardianItem;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class StrawberryCropBlock extends CropBlock {
    public static final int MAX_AGE = 4;
    public static final IntegerProperty AGE = BlockStateProperties.AGE_4;
    private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[] {
            Block.box(0, 0, 0, 16, 4, 16),
            Block.box(0, 0, 0, 16, 8, 16),
            Block.box(0, 0, 0, 16, 11, 16),
            Block.box(0, 0, 0, 16, 15, 16),
            Block.box(0, 0, 0, 16, 15, 16),
    };
    private static final Random random = new Random();

    public StrawberryCropBlock(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull VoxelShape getShape(
            @NotNull BlockState blockState,
            @NotNull BlockGetter blockGetter,
            @NotNull BlockPos blockPos,
            @NotNull CollisionContext collisionContext
    ) {
        return SHAPE_BY_AGE[this.getAge(blockState)];
    }

    @Override
    protected @NotNull ItemLike getBaseSeedId() {
        return PrettyGuardianItem.STRAWBERRY_SEEDS.get();
    }

    @Override
    public @NotNull IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    protected @NotNull InteractionResult useWithoutItem(
            BlockState blockState,
            @NotNull Level level,
            @NotNull BlockPos blockPos,
            @NotNull Player player,
            @NotNull BlockHitResult blockHitResult
    ) {
        if (blockState.getValue(AGE) == MAX_AGE) {
            int bonusSeed = random.nextInt(2) + 1;

            level.setBlockAndUpdate(blockPos, blockState.setValue(AGE, 0));
            level.addDestroyBlockEffect(blockPos, blockState);

            Block.popResource(level, blockPos, new ItemStack(PrettyGuardianItem.STRAWBERRY.get(), 2));
            if (bonusSeed == 1) {
                Block.popResource(level, blockPos, new ItemStack(PrettyGuardianItem.STRAWBERRY_SEEDS.get()));
            }

            return InteractionResult.SUCCESS;
        }
        return super.useWithoutItem(blockState, level, blockPos, player, blockHitResult);
    }
}

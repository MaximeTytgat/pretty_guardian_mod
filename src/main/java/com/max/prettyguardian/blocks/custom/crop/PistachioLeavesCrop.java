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
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Random;

public class PistachioLeavesCrop extends CropBlock {
    public static final int MAX_AGE = 4;
    public static final IntegerProperty AGE = BlockStateProperties.AGE_4;
    private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[] {
            Block.box(0, 0, 0, 16, 16, 16),
            Block.box(0, 0, 0, 16, 16, 16),
            Block.box(0, 0, 0, 16, 16, 16),
            Block.box(0, 0, 0, 16, 16, 16),
            Block.box(0, 0, 0, 16, 16, 16),
    };
    public PistachioLeavesCrop(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return SHAPE_BY_AGE[this.getAge(blockState)];
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return PrettyGuardianItem.STRAWBERRY_SEEDS.get();
    }

    @Override
    public IntegerProperty getAgeProperty() {
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
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        if (blockState.getValue(AGE) == MAX_AGE) {
            Random random = new Random();
            int bonus_seed = random.nextInt(5) + 1;

            level.setBlockAndUpdate(blockPos, blockState.setValue(AGE, 0));
            level.addDestroyBlockEffect(blockPos, blockState);

            Block.popResource(level, blockPos, new ItemStack(PrettyGuardianItem.STRAWBERRY.get(), 2));
            if (bonus_seed == 1) {
                Block.popResource(level, blockPos, new ItemStack(PrettyGuardianItem.STRAWBERRY_SEEDS.get()));
            }

            return InteractionResult.SUCCESS;
        }
        return super.use(blockState, level, blockPos, player, interactionHand, blockHitResult);
    }
}

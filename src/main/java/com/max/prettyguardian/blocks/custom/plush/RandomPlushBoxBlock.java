package com.max.prettyguardian.blocks.custom.plush;

import com.max.prettyguardian.blocks.PrettyGuardianBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.Containers;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class RandomPlushBoxBlock extends Block {

    public static final EnumProperty<Direction> FACING = DirectionalBlock.FACING;
    private static final VoxelShape SHAPE_NORTH = Shapes.or(
            Block.box(4, 4, 4, 12, 8, 12),
            Block.box(4.25, 0, 4.25, 11.75, 4, 11.75),
            Block.box(7, 3, 11.700000000000001, 9, 5.0009999999999994, 12.500000000000002)
    );
    private static final VoxelShape SHAPE_SOUTH = Shapes.or(
            Block.box(4, 4, 4, 12, 8, 12),
            Block.box(4.25, 0, 4.25, 11.75, 4, 11.75),
            Block.box(7, 3, 3.5, 9, 5.0009999999999994, 4.300000000000001)
    );
    private static final VoxelShape SHAPE_WEST = Shapes.or(
            Block.box(4, 4, 4, 12, 8, 12),
            Block.box(4.25, 0, 4.25, 11.75, 4, 11.75),
            Block.box(11.700000000000001, 3, 7, 12.500000000000002, 5.0009999999999994, 9)
    );
    private static final VoxelShape SHAPE_EAST = Shapes.or(
            Block.box(4, 4, 4, 12, 8, 12),
            Block.box(4.25, 0, 4.25, 11.75, 4, 11.75),
            Block.box(3.5, 3, 7, 4.300000000000001, 5.0009999999999994, 9)
    );
    public RandomPlushBoxBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void onRemove(BlockState blockState, Level level, BlockPos blockPos, BlockState blockState1, boolean p_60519_) {
        Block[] plushBlocks = {
                PrettyGuardianBlock.MYMELODY_PLUSH.get(),
                PrettyGuardianBlock.KUROMI_PLUSH.get(),
                PrettyGuardianBlock.CAVALIER_PLUSH.get(),
                PrettyGuardianBlock.TEDDYBEAR_PLUSH.get(),
                PrettyGuardianBlock.RABBIT_PLUSH.get(),
                PrettyGuardianBlock.COW_PLUSH.get(),
                PrettyGuardianBlock.CINNAMOROLL_PLUSH.get(),
                PrettyGuardianBlock.BABYYODA_PLUSH.get(),
                PrettyGuardianBlock.CAPPUCCINO_PLUSH.get(),
                PrettyGuardianBlock.CHIFFON_PLUSH.get(),
                PrettyGuardianBlock.EXPRESSO_PLUSH.get(),
                PrettyGuardianBlock.MOCHA_PLUSH.get(),
                PrettyGuardianBlock.PRINCESS_PORON_PLUSH.get(),
        };

        Block[] onePercentBlocks = {
                PrettyGuardianBlock.PLUSH_BEAR_HUGE.get(),
        };

        if (level.random.nextInt(100) == 0) {
            Random random = new Random();
            int randomIndex = random.nextInt(onePercentBlocks.length);
            Block randomPlushBlock = onePercentBlocks[randomIndex];
            SimpleContainer inventory = new SimpleContainer(1);
            inventory.setItem(0, new ItemStack(randomPlushBlock.asItem()));
            Containers.dropContents(level, blockPos, inventory);
            return;
        } else {
            Random random = new Random();
            int randomIndex = random.nextInt(plushBlocks.length);
            Block randomPlushBlock = plushBlocks[randomIndex];
            SimpleContainer inventory = new SimpleContainer(1);
            inventory.setItem(0, new ItemStack(randomPlushBlock.asItem()));
            Containers.dropContents(level, blockPos, inventory);
        }

        super.onRemove(blockState, level, blockPos, blockState1, p_60519_);
    }

    @Override
    public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        return switch (p_60555_.getValue(FACING)) {
            case NORTH -> SHAPE_SOUTH;
            case SOUTH -> SHAPE_NORTH;
            case WEST -> SHAPE_EAST;
            case EAST -> SHAPE_WEST;
            default -> SHAPE_NORTH;
        };
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
}

package com.doudou.cutecore.blocks.custom;

import com.doudou.cutecore.blocks.entity.ModBlockEntities;
import com.doudou.cutecore.blocks.entity.PicnicBasketBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Stream;

public class PicnicBasketBlock extends BaseEntityBlock {

    public static final BooleanProperty OPEN = BlockStateProperties.OPEN;

    private static final VoxelShape SHAPE = Stream.of(
            Block.box(4, 0, 3, 12, 1, 13),
            Block.box(3, 0, 2, 4, 6, 14),
            Block.box(12, 0, 2, 13, 6, 14),
            Block.box(4, 0, 13, 12, 6, 14),
            Block.box(4, 0, 2, 12, 6, 3),
            Block.box(3, 6, 1.5, 13, 7, 8),
            Block.box(3, 6, 8, 13, 7, 14.5),
            Block.box(12, 6, 6.9, 13.5, 7, 9.1),
            Block.box(2.5, 6, 6.9, 4, 7, 9.1),
            Block.box(2.9, 6, 7, 4, 10, 9),
            Block.box(12, 6, 7, 13.1, 10, 9),
            Block.box(4, 9, 7, 12, 10, 9)
    ).reduce(Shapes::or).get();

    public PicnicBasketBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(OPEN, false));
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return SHAPE;
    }

    @Override
    public RenderShape getRenderShape(BlockState blockState) {
        return RenderShape.MODEL;
    }

    @Override
    public void onRemove(BlockState blockState, Level level, BlockPos blockPos, BlockState blockState1, boolean b) {
        super.onRemove(blockState, level, blockPos, blockState1, b);
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {

        if (!level.isClientSide()) {
            BlockEntity blockEntity = level.getBlockEntity(blockPos);
            if (blockEntity instanceof PicnicBasketBlockEntity) {
                NetworkHooks.openScreen(((ServerPlayer) player), (PicnicBasketBlockEntity) blockEntity, blockPos);
            } else {
                throw new IllegalStateException("Our named container provider is missing!");
            }
        }


        return InteractionResult.sidedSuccess(level.isClientSide());
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new PicnicBasketBlockEntity(blockPos, blockState);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder builder) {
        builder.add(OPEN);
    }
}

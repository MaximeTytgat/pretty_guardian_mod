package com.max.prettyguardian.blocks.custom;

import com.max.prettyguardian.blocks.entity.ModBlockEntities;
import com.max.prettyguardian.blocks.entity.PicnicBasketBlockEntity;
import com.mojang.serialization.MapCodec;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.stream.Stream;

public class PicnicBasketBlock extends BaseEntityBlock {

    public static final MapCodec<PicnicBasketBlock> CODEC = simpleCodec(PicnicBasketBlock::new);
    public static final BooleanProperty OPEN = BlockStateProperties.OPEN;
    public static final EnumProperty<Direction> FACING = DirectionalBlock.FACING;
    public static final ResourceLocation CONTENTS = new ResourceLocation("contents");

    private static final VoxelShape SHAPE_NORTH = Stream.of(Block.box(2, 1, 3, 14, 7, 13), Block.box(3, 0, 4, 13, 1, 12)).reduce(Shapes::or).get();
    private static final VoxelShape SHAPE_EAST = Stream.of(Block.box(3, 1, 2, 13, 7, 14), Block.box(4, 0, 3, 12, 1, 13)).reduce(Shapes::or).get();


    public PicnicBasketBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(OPEN, false));
    }

    @Override
    protected @NotNull MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        Direction direction = blockState.getValue(FACING);
        return direction.getAxis() == Direction.Axis.X ? SHAPE_EAST : SHAPE_NORTH;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(OPEN, false);
    }

    @Override
    public RenderShape getRenderShape(BlockState blockState) {
        return RenderShape.MODEL;
    }

    @Override
    public void onRemove(BlockState blockState, Level level, BlockPos blockPos, BlockState blockState1, boolean b) {
        if (!blockState.is(blockState1.getBlock())) {
            BlockEntity blockentity = level.getBlockEntity(blockPos);
            if (blockentity instanceof PicnicBasketBlockEntity) {
                level.updateNeighbourForOutputSignal(blockPos, blockState.getBlock());
            }

            super.onRemove(blockState, level, blockPos, blockState1, b);
        }
    }

    public void setPlacedBy(Level level, BlockPos blockPos, BlockState blockState, LivingEntity livingEntity, ItemStack itemStack) {
        if (itemStack.hasCustomHoverName()) {
            BlockEntity blockentity = level.getBlockEntity(blockPos);
            if (blockentity instanceof PicnicBasketBlockEntity) {
                ((PicnicBasketBlockEntity) blockentity).setCustomName(itemStack.getHoverName());
            }
        }

    }

    public void appendHoverText(ItemStack p_56193_, @javax.annotation.Nullable BlockGetter p_56194_, List<Component> p_56195_, TooltipFlag p_56196_) {
        super.appendHoverText(p_56193_, p_56194_, p_56195_, p_56196_);
        CompoundTag compoundtag = BlockItem.getBlockEntityData(p_56193_);
        if (compoundtag != null) {
            if (compoundtag.contains("LootTable", 8)) {
                p_56195_.add(Component.literal("???????"));
            }

            if (compoundtag.contains("Items", 9)) {
                NonNullList<ItemStack> nonnulllist = NonNullList.withSize(27, ItemStack.EMPTY);
                ContainerHelper.loadAllItems(compoundtag, nonnulllist);
                int i = 0;
                int j = 0;

                for(ItemStack itemstack : nonnulllist) {
                    if (!itemstack.isEmpty()) {
                        ++j;
                        if (i <= 4) {
                            ++i;
                            MutableComponent mutablecomponent = itemstack.getHoverName().copy();
                            mutablecomponent.append(" x").append(String.valueOf(itemstack.getCount()));
                            p_56195_.add(mutablecomponent);
                        }
                    }
                }

                if (j - i > 0) {
                    p_56195_.add(Component.translatable("container.picnicBasket.more", j - i).withStyle(ChatFormatting.ITALIC));
                }
            }
        }
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        if (!level.isClientSide()) {
            BlockEntity blockEntity = level.getBlockEntity(blockPos);
            if (blockEntity instanceof PicnicBasketBlockEntity) {
                NetworkHooks.openScreen(((ServerPlayer) player), (PicnicBasketBlockEntity) blockEntity, blockPos);
            } else {
                throw new IllegalStateException("Our container provider is missing!");
            }
        }

//        blockState.setValue(OPEN, true);

        return InteractionResult.sidedSuccess(level.isClientSide());
    }

    public void playerWillDestroy(Level level, BlockPos blockPos, BlockState blockState, Player player) {
        BlockEntity blockentity = level.getBlockEntity(blockPos);
        if (blockentity instanceof PicnicBasketBlockEntity picnicBasketBlockEntity) {
            if (!level.isClientSide && player.isCreative() && !picnicBasketBlockEntity.isEmpty()) {
                ItemStack itemstack = new ItemStack(this);
                blockentity.saveToItem(itemstack);
                if (picnicBasketBlockEntity.hasCustomName()) {
                    itemstack.setHoverName(picnicBasketBlockEntity.getCustomName());
                }

                ItemEntity itementity = new ItemEntity(level, (double)blockPos.getX() + 0.5D, (double)blockPos.getY() + 0.5D, (double)blockPos.getZ() + 0.5D, itemstack);
                itementity.setDefaultPickUpDelay();
                level.addFreshEntity(itementity);
            } else {
                picnicBasketBlockEntity.unpackLootTable(player);
            }
        }

        super.playerWillDestroy(level, blockPos, blockState, player);
    }

    public List<ItemStack> getDrops(BlockState blockState, LootParams.Builder builder) {
        BlockEntity blockentity = builder.getOptionalParameter(LootContextParams.BLOCK_ENTITY);
        if (blockentity instanceof PicnicBasketBlockEntity picnicBasketBlockEntity) {
            builder = builder.withDynamicDrop(CONTENTS, (p_56219_) -> {
                for(int i = 0; i < picnicBasketBlockEntity.getContainerSize(); ++i) {
                    p_56219_.accept(picnicBasketBlockEntity.getItem(i));
                }

            });
        }

        return super.getDrops(blockState, builder);
    }

    public boolean hasAnalogOutputSignal(BlockState blockState) {
        return true;
    }

    public int getAnalogOutputSignal(BlockState blockState, Level level, BlockPos blockPos) {
        return AbstractContainerMenu.getRedstoneSignalFromContainer((Container) level.getBlockEntity(blockPos));
    }

    @Override
    public ItemStack getCloneItemStack(BlockGetter blockGetter, BlockPos blockPos, BlockState blockState) {
        ItemStack itemstack = super.getCloneItemStack(blockGetter, blockPos, blockState);
        blockGetter.getBlockEntity(blockPos, ModBlockEntities.PICNIC_BASKET_BE.get()).ifPresent((p_187446_) -> {
            p_187446_.saveToItem(itemstack);
        });
        return itemstack;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new PicnicBasketBlockEntity(blockPos, blockState);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder builder) {
        builder.add(OPEN, FACING);
    }
}

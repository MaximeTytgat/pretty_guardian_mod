package com.doudou.prettyguardian.blocks.entity;

import com.doudou.prettyguardian.blocks.custom.PicnicBasketBlock;
import com.doudou.prettyguardian.client.gui.sreens.inventory.PicnicBasketMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.stream.IntStream;

public class  PicnicBasketBlockEntity extends RandomizableContainerBlockEntity implements WorldlyContainer {

    public static final int COLUMNS = 2;
    public static final int ROWS = 2;
    public static final int CONTAINER_SIZE = 4;
    public static final int EVENT_SET_OPEN_COUNT = 1;
    public static final String ITEMS_TAG = "Items";
    private static final int[] SLOTS = IntStream.range(0, 4).toArray();
    private NonNullList<ItemStack> itemStacks = NonNullList.withSize(4, ItemStack.EMPTY);
    private int openCount;

    public PicnicBasketBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.PICNIC_BASKET_BE.get(), blockPos, blockState);
    }

    public void startOpen(Player p_59692_) {
        if (!this.remove && !p_59692_.isSpectator()) {
            if (this.openCount < 0) {
                this.openCount = 0;
            }

            ++this.openCount;
            this.level.blockEvent(this.worldPosition, this.getBlockState().getBlock(), 1, this.openCount);
            if (this.openCount == 1) {
                this.level.gameEvent(p_59692_, GameEvent.CONTAINER_OPEN, this.worldPosition);
                this.level.playSound((Player)null, this.worldPosition, SoundEvents.SHULKER_BOX_OPEN, SoundSource.BLOCKS, 0.5F, this.level.random.nextFloat() * 0.1F + 0.9F);
            }
        }

    }

    public void stopOpen(Player p_59688_) {
        if (!this.remove && !p_59688_.isSpectator()) {
            --this.openCount;
            this.level.blockEvent(this.worldPosition, this.getBlockState().getBlock(), 1, this.openCount);
            if (this.openCount <= 0) {
                this.level.gameEvent(p_59688_, GameEvent.CONTAINER_CLOSE, this.worldPosition);
                this.level.playSound((Player)null, this.worldPosition, SoundEvents.SHULKER_BOX_CLOSE, SoundSource.BLOCKS, 0.5F, this.level.random.nextFloat() * 0.1F + 0.9F);
            }
        }

    }

    @Override
    public int getContainerSize() {
        return this.itemStacks.size();
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.prettyguardian.picnic_basket");
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("block.prettyguardian.picnic_basket");
    }

    @Override
    public void load(CompoundTag compoundTag) {
        super.load(compoundTag);
        this.loadFromTag(compoundTag);
    }

    @Override
    protected void saveAdditional(CompoundTag compoundTag) {
        if (!this.trySaveLootTable(compoundTag)) {
            ContainerHelper.saveAllItems(compoundTag, this.itemStacks, false);
        }

        super.saveAdditional(compoundTag);
    }

    public void loadFromTag(CompoundTag p_59694_) {
        this.itemStacks = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        if (!this.tryLoadLootTable(p_59694_) && p_59694_.contains("Items", 9)) {
            ContainerHelper.loadAllItems(p_59694_, this.itemStacks);
        }
    }

    @Override
    protected @NotNull NonNullList<ItemStack> getItems() {
        return this.itemStacks;
    }

    @Override
    protected void setItems(@NotNull NonNullList<ItemStack> itemStacks) {
        this.itemStacks = itemStacks;
    }

    @Override
    public int @NotNull [] getSlotsForFace(@NotNull Direction direction) {
        return SLOTS;
    }
    @Override
    public boolean canPlaceItemThroughFace(int p_19235_, ItemStack itemStack, @Nullable Direction direction) {
        return !(Block.byItem(itemStack.getItem()) instanceof PicnicBasketBlock) && itemStack.getItem().canFitInsideContainerItems(); // FORGE: Make shulker boxes respect Item#canFitInsideContainerItems
    }

    public boolean canTakeItemThroughFace(int p_59682_, ItemStack p_59683_, Direction p_59684_) {
        return true;
    }

    @Override
    protected AbstractContainerMenu createMenu(int id, Inventory inventory) {
        return new PicnicBasketMenu(id, inventory, this);
    }

    @Override
    protected net.minecraftforge.items.IItemHandler createUnSidedHandler() {
        return new net.minecraftforge.items.wrapper.SidedInvWrapper(this, Direction.UP);
    }
}

package com.max.prettyguardian.blocks.entity;

import com.max.prettyguardian.client.gui.sreens.inventory.StaffMagicTableMenu;
import com.max.prettyguardian.item.PrettyGuardianItem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class StaffMagicTableBlockEntity extends BlockEntity implements MenuProvider {
    private final ItemStackHandler itemHandler = new ItemStackHandler(4);
    private static final int INPUT_SLOT_1 = 0;
    private static final int INPUT_SLOT_2 = 1;
    private static final int INPUT_SLOT_3 = 2;
    private static final int OUTPUT_SLOT = 3;
    private LazyOptional<ItemStackHandler> lazyItemHandler = LazyOptional.empty();

    public StaffMagicTableBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.STAFF_MAGIC_TABLE_BE.get(), blockPos, blockState);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyItemHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.prettyguardian.staff_magic_table");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        return new StaffMagicTableMenu(id, inventory, this);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory", itemHandler.serializeNBT());
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        itemHandler.deserializeNBT(tag.getCompound("inventory"));
    }



    public void tick(Level level, BlockPos pPos, BlockState pState) {
        if (hasRecipe()) {
            setChanged(level, pPos, pState);
            craftItem();
        }
    }

    private void craftItem() {
        ItemStack result = new ItemStack(PrettyGuardianItem.ETERNAL_SILVER_CISTAL_STAFF.get());
        this.itemHandler.extractItem(INPUT_SLOT_1, 1, false);
        this.itemHandler.extractItem(INPUT_SLOT_2, 1, false);
        this.itemHandler.extractItem(INPUT_SLOT_3, 1, false);

        this.itemHandler.setStackInSlot(OUTPUT_SLOT, new ItemStack(result.getItem(), this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + result.getCount()));
    }

    private boolean hasRecipe() {
        Item item1 = this.itemHandler.getStackInSlot(INPUT_SLOT_1).getItem();
        Item item2 = this.itemHandler.getStackInSlot(INPUT_SLOT_2).getItem();
        Item item3 = this.itemHandler.getStackInSlot(INPUT_SLOT_3).getItem();

        boolean slot1HasArtephacte = item1 == PrettyGuardianItem.PLUTONS_KEY.get() || item1 == PrettyGuardianItem.NEPTUNES_MIRROR.get() || item1 == PrettyGuardianItem.SPACE_SWORD.get();
        boolean slot2HasArtephacte = item2 == PrettyGuardianItem.PLUTONS_KEY.get() || item2 == PrettyGuardianItem.NEPTUNES_MIRROR.get() || item2 == PrettyGuardianItem.SPACE_SWORD.get();
        boolean slot3HasArtephacte = item3 == PrettyGuardianItem.PLUTONS_KEY.get() || item3 == PrettyGuardianItem.NEPTUNES_MIRROR.get() || item3 == PrettyGuardianItem.SPACE_SWORD.get();

        ItemStack result = new ItemStack(PrettyGuardianItem.ETERNAL_SILVER_CISTAL_STAFF.get());
        return (slot1HasArtephacte && slot2HasArtephacte && slot3HasArtephacte) && (item1 != item2 && item2 != item3 && item1 != item3) && canInsertAmountIntoOutputSlot(result.getCount()) && canInsertItemIntoOutputSlot(result.getItem());
    }

    private boolean canInsertItemIntoOutputSlot(Item item) {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() || this.itemHandler.getStackInSlot(OUTPUT_SLOT).is(item);
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + count <= this.itemHandler.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();
    }
}

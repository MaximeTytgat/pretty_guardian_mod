package com.max.prettyguardian.item.custom;

import com.max.prettyguardian.PrettyGuardian;
import com.max.prettyguardian.client.gui.sreens.inventory.GiftBoxMenu;
import net.minecraft.ChatFormatting;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.List;

public class GiftBoxItem extends Item implements Container {
    private final NonNullList<ItemStack> itemStacks = NonNullList.withSize(1, ItemStack.EMPTY);

    public GiftBoxItem(Properties properties) {
        super(properties);
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        if (interactionHand == InteractionHand.OFF_HAND) {
            return InteractionResultHolder.pass(player.getItemInHand(interactionHand));
        }

        CompoundTag tag = player.getItemInHand(interactionHand).hasTag() ? player.getItemInHand(interactionHand).getTag() : new CompoundTag();
        this.itemStacks.clear();
        ContainerHelper.loadAllItems(tag, this.itemStacks);

        ItemStack stack = player.getItemInHand(interactionHand);

        if (!level.isClientSide && player instanceof ServerPlayer serverPlayer) {
            SimpleMenuProvider simpleMenuProvider = new SimpleMenuProvider((id, inv, player1) -> new GiftBoxMenu(id, inv, this), stack.getHoverName());
            NetworkHooks.openScreen(serverPlayer, simpleMenuProvider);
        }


        return InteractionResultHolder.success(stack);
    }

    @Override
    public void stopOpen(Player player) {
        PrettyGuardian.LOGGER.info("StopOpen");
        ItemStack giftBox = player.getItemInHand(InteractionHand.MAIN_HAND);
        trytoSaveContent(giftBox);
    }

    private void trytoSaveContent(ItemStack giftBox) {
        CompoundTag tag = giftBox.hasTag() ? giftBox.getTag() : new CompoundTag();
        assert tag != null;
        giftBox.setTag(ContainerHelper.saveAllItems(tag, this.itemStacks, false));
    }

    @Override
    public int getContainerSize() {
        return this.itemStacks.size();
    }

    @Override
    public boolean isEmpty() {
        return this.itemStacks.isEmpty();
    }

    @Override
    public ItemStack getItem(int i) {
        return this.itemStacks.get(i);
    }

    @Override
    public ItemStack removeItem(int i, int i1) {
        return this.itemStacks.get(i).split(i1);
    }

    @Override
    public ItemStack removeItemNoUpdate(int i) {
        return this.itemStacks.get(i);
    }

    @Override
    public void setItem(int i, @NotNull ItemStack itemStack) {
        PrettyGuardian.LOGGER.info("SetItem");
        PrettyGuardian.LOGGER.info("i: " + i);
        PrettyGuardian.LOGGER.info("itemStack: " + itemStack);

        this.itemStacks.set(i, itemStack);
    }

    @Override
    public void setChanged() {

    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }


    @Override
    public void clearContent() {
        this.itemStacks.clear();
    }


    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> components, TooltipFlag tooltipFlag) {
        super.appendHoverText(itemStack, level, components, tooltipFlag);
        CompoundTag blockEntityData = BlockItem.getBlockEntityData(itemStack);
        if (blockEntityData != null) {
            if (blockEntityData.contains("Items", 9)) {
                NonNullList<ItemStack> itemStacks1 = NonNullList.withSize(27, ItemStack.EMPTY);
                ContainerHelper.loadAllItems(blockEntityData, itemStacks1);
                int i1 = 0;
                int i = 0;

                for (ItemStack stack : itemStacks1) {
                    if (!stack.isEmpty()) {
                        ++i;
                        if (i1 <= 4) {
                            ++i1;
                            MutableComponent $$9 = stack.getHoverName().copy();
                            $$9.append(" x").append(String.valueOf(stack.getCount()));
                            components.add($$9);
                        }
                    }
                }


                components.add(Component.translatable("container.shulkerBox.lala", new Object[]{i - i1}).withStyle(ChatFormatting.ITALIC));
            }
        }
    }
}

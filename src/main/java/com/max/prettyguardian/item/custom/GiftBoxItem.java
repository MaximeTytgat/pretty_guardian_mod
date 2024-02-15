package com.max.prettyguardian.item.custom;

import com.max.prettyguardian.client.gui.sreens.inventory.GiftBoxMenu;
import com.max.prettyguardian.client.gui.sreens.inventory.PicnicBasketMenu;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GiftBoxItem extends Item  {
    private NonNullList<ItemStack> itemStacks = NonNullList.withSize(1, ItemStack.EMPTY);

    public GiftBoxItem(Properties properties) {
        super(properties);
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        ItemStack stack = player.getItemInHand(interactionHand);

        if (!level.isClientSide && player instanceof ServerPlayer serverPlayer) {
            SimpleMenuProvider simpleMenuProvider = new SimpleMenuProvider((id, inv, player1) -> new GiftBoxMenu(id, inv, new SimpleContainer(1)), stack.getHoverName());
            NetworkHooks.openScreen(serverPlayer, simpleMenuProvider, (packetBuffer) -> {
                packetBuffer.writeItem(stack);
            });
        }


        return InteractionResultHolder.success(stack);
    }

    @Override
    public @Nullable ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        return new ICapabilityProvider() {
            private final LazyOptional<NonNullList<ItemStack>> itemStacks = LazyOptional.of(() -> GiftBoxItem.this.itemStacks);

            @Override
            public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
                if (cap == ForgeCapabilities.ITEM_HANDLER) {
                    return itemStacks.cast();
                }
                return LazyOptional.empty();
            }
        };
    }
}

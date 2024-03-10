package com.max.prettyguardian.item.custom;

import com.max.prettyguardian.client.gui.sreens.inventory.FakeLoveLetterMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;

public class LoveLetterItem extends Item {
    public LoveLetterItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        if (!level.isClientSide && player instanceof ServerPlayer serverPlayer) {
            SimpleMenuProvider simpleMenuProvider = new SimpleMenuProvider((id, inv, player1) -> new FakeLoveLetterMenu(id, inv, new SimpleContainer(1)), Component.empty());
            NetworkHooks.openScreen(serverPlayer, simpleMenuProvider);
        }

        return super.use(level, player, interactionHand);
    }

    @Override
    public boolean isFoil(ItemStack itemStack) {
        return itemStack.hasTag() && itemStack.getTag().contains("author");
    }
}
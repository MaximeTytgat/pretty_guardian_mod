package com.doudou.cutecore.item.custom;

import com.doudou.cutecore.client.gui.sreens.inventory.PicnicBasketMenu;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;

public class GiftBoxItem extends Item  {
    public GiftBoxItem(Properties properties) {
        super(properties);
    }


//    @Override
//    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
//        ItemStack stack = player.getItemInHand(hand);
//
//        if (!world.isClientSide && player instanceof ServerPlayer serverPlayer) {
//            String handlerName = hand == InteractionHand.MAIN_HAND ? PlayerInventoryProvider.MAIN_INVENTORY : PlayerInventoryProvider.OFFHAND_INVENTORY;
//            int slot = hand == InteractionHand.MAIN_HAND ? player.getInventory().selected : 0;
//            BackpackContext.Item context = new BackpackContext.Item(handlerName, slot);
//            NetworkHooks.openScreen(serverPlayer, new SimpleMenuProvider((w, p, pl) -> new PicnicBasketMenu(w, pl.getInventory(), context), stack.getHoverName()),
//                    context::toBuffer);
//        }
//        return InteractionResultHolder.success(stack);
//    }
}

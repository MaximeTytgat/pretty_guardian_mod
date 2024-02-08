package com.doudou.cutecore.item.custom;

import net.minecraft.world.item.Item;

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


//    @Override
//    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
//        if (!level.isClientSide()) {
//            BlockEntity blockEntity = level.getEntity(blockPos);
//            if (blockEntity instanceof GemPolishingStationBlockEntity) {
//                NetworkHooks.openScreen(((ServerPlayer) player), (GemPolishingStationBlockEntity) blockEntity, blockPos);
//            } else {
//                throw new IllegalStateException("Our container provider is missing!");
//            }
//        }
//
//        return InteractionResult.sidedSuccess(level.isClientSide());
//    }

}

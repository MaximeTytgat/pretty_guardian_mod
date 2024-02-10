package com.max.prettyguardian.item.custom.food;

import com.max.prettyguardian.PrettyGuardian;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ClassicDonut extends Item {
    public ClassicDonut(Properties properties) {
        super(properties);
    }


    @SubscribeEvent
    public void onPlayerInteract(PlayerInteractEvent.EntityInteractSpecific event) {
        Entity targetEntity = event.getTarget();
        Player player = event.getEntity();
        ItemStack itemstack = player.getItemInHand(InteractionHand.MAIN_HAND);

        if (itemstack.getItem() instanceof ClassicDonut && targetEntity instanceof LivingEntity) {
            if (targetEntity instanceof Player) {
                Player targetPlayer = (Player) targetEntity;
                // Your logic for interacting with another player here

                PrettyGuardian.LOGGER.info("Player " + player.getName().getString() + " interacted with player " + targetPlayer.getName().getString());
            }

            // Your additional logic for using the donut here

            // Make sure to decrease the item stack if it's not in creative mode
            if (!player.isCreative()) {
                itemstack.shrink(1);
            }

            event.setCancellationResult(InteractionResult.SUCCESS);
            event.setCanceled(true);
        }
    }
}

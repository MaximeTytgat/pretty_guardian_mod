package com.doudou.cutecore.event;

import com.doudou.cutecore.CuteCore;
import com.doudou.cutecore.item.CuteCoreItem;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

import java.util.Date;
import java.util.function.Predicate;

@Mod.EventBusSubscriber(modid = CuteCore.MOD_ID, value = Dist.CLIENT)
public class ModEvents {

    private static final long COOLDOWN_MILLIS_DOUBLE_JUMP = 1300;
    private static Date lastJumpTime = new Date();

    @SubscribeEvent
    public static void onKeyPress(InputEvent.Key event) {
        Player player = Minecraft.getInstance().player;

        if (player == null) {
            return;
        }

        // Vérifier le cooldown basé sur le temps
        Date currentTime = new Date();
        long timeSinceLastJump = currentTime.getTime() - lastJumpTime.getTime();

        if (event.getKey() == GLFW.GLFW_KEY_SPACE && event.getAction() == GLFW.GLFW_PRESS) {
            if (hasCuteWandInInventory(player) && !player.onGround()) {
                if (timeSinceLastJump >= COOLDOWN_MILLIS_DOUBLE_JUMP) {
                    player.jumpFromGround();
                    lastJumpTime = currentTime;
                }
            }
        }
    }

    // Méthode pour vérifier si le joueur a l'item CuteWandItem dans son inventaire
    private static boolean hasCuteWandInInventory(Player player) {
        // Code pour vérifier la présence de l'item dans l'inventaire
        // Retourne true si l'item est présent, sinon false
        return player.getInventory().hasAnyMatching(ETERNAL_SILVER_CISTAL_STAFF_ONLY);
    }

    public static final Predicate<ItemStack> ETERNAL_SILVER_CISTAL_STAFF_ONLY = (itemStack) -> {
        return itemStack.is(CuteCoreItem.ETERNAL_SILVER_CISTAL_STAFF.get());
    };
}

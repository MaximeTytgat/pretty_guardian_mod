package com.max.prettyguardian.event;

import com.max.prettyguardian.PrettyGuardian;
import com.max.prettyguardian.client.ClientPlayerEntityOnShoulderData;
import com.max.prettyguardian.item.PrettyGuardianItem;
import com.max.prettyguardian.item.custom.food.ClassicDonut;
import com.max.prettyguardian.particle.ModParticles;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

@Mod.EventBusSubscriber(modid = PrettyGuardian.MOD_ID, value = Dist.CLIENT)
public class ModEventClient {
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.ClientTickEvent event) {
        Minecraft MC = Minecraft.getInstance();
        Player player = MC.player;
        if(player != null && player.tickCount % 10 == 0 && player.level().isClientSide() && !MC.isPaused()) {
            boolean hasEntityOnShoulder = ClientPlayerEntityOnShoulderData.getHasEntityOnShoulder();
            if (hasEntityOnShoulder) {
                Random random = new Random();
                Vec3 look = player.getLookAngle();
                double angleRadians = Math.atan2(look.z, look.x);
                double angleDegrees = Math.toDegrees(angleRadians);
                angleDegrees += 270;
                float distance = 0.4F;
                double offsetX = Math.cos(Math.toRadians(angleDegrees)) * distance;
                double offsetZ = Math.sin(Math.toRadians(angleDegrees)) * distance;

                for(int i = 0; i < 3; ++i) {
                    player.level().addParticle(ModParticles.CELESTIAL_RABBIT_PARTICLES.get(), player.getX() + offsetX, player.getY() + 1.6f, player.getZ() + offsetZ, (random.nextDouble() - 0.5) * 2.0, -random.nextDouble(), (random.nextDouble() - 0.5) * 2.0);
                }

            }
        }
    }
}

package com.max.prettyguardian.networking;

import com.max.prettyguardian.PrettyGuardian;
import com.max.prettyguardian.networking.packet.PlayerEntityOnShoulderDataSCPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModMessages {
    private static SimpleChannel INSTANCE;

    private static int packetId = 0;
    private static int id() {
        return packetId++;
    }

    public static void register() {
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(PrettyGuardian.MOD_ID, "messages"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        INSTANCE = net;

        net.messageBuilder(PlayerEntityOnShoulderDataSCPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(PlayerEntityOnShoulderDataSCPacket::new)
                .encoder(PlayerEntityOnShoulderDataSCPacket::toBytes)
                .consumerMainThread(PlayerEntityOnShoulderDataSCPacket::handle)
                .add();
    }

    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }
    public static <MSG> void sendToAllPlayer(MSG message) {
        INSTANCE.send(PacketDistributor.ALL.noArg(), message);
    }
}
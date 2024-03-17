package com.max.prettyguardian.networking.packet;

import com.max.prettyguardian.client.ClientPlayerEntityOnShoulderData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PlayerEntityOnShoulderDataSCPacket {
    private final Boolean hasEntityOnShoulder;
    private final String playerId;

    public PlayerEntityOnShoulderDataSCPacket(boolean hasEntityOnShoulder, String playerId) {
        this.hasEntityOnShoulder = hasEntityOnShoulder;
        this.playerId = playerId;
    }

    public PlayerEntityOnShoulderDataSCPacket(FriendlyByteBuf buf) {
        this.hasEntityOnShoulder = buf.readBoolean();
        this.playerId = buf.readUtf(32767);
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeBoolean(this.hasEntityOnShoulder);
        buf.writeUtf(this.playerId);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // Here we are in the client
            ClientPlayerEntityOnShoulderData.setEntityOnShoulder(this.hasEntityOnShoulder, this.playerId);
        });
        return true;
    }
}

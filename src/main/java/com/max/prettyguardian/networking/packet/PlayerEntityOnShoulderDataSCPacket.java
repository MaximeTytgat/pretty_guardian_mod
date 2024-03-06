package com.max.prettyguardian.networking.packet;

import com.max.prettyguardian.PrettyGuardian;
import com.max.prettyguardian.client.ClientPlayerEntityOnShoulderData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class PlayerEntityOnShoulderDataSCPacket {
    private final Boolean hasEntityOnShoulder;
    private final String entityOnShoulderId;

    public PlayerEntityOnShoulderDataSCPacket(boolean hasEntityOnShoulder, String entityOnShoulderId) {
        this.hasEntityOnShoulder = hasEntityOnShoulder;
        this.entityOnShoulderId = entityOnShoulderId;
    }

    public PlayerEntityOnShoulderDataSCPacket(FriendlyByteBuf buf) {
        this.hasEntityOnShoulder = buf.readBoolean();
        this.entityOnShoulderId = buf.readUtf(32767);
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeBoolean(this.hasEntityOnShoulder);
        buf.writeUtf(this.entityOnShoulderId);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // Here we are in the client
            ClientPlayerEntityOnShoulderData.setEntityOnShoulder(this.hasEntityOnShoulder, this.entityOnShoulderId);
        });
        return true;
    }
}

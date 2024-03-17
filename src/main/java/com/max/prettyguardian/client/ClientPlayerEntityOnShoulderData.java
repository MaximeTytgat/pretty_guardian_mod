package com.max.prettyguardian.client;


public class ClientPlayerEntityOnShoulderData {
    private static Boolean hasEntityOnShoulder;
    private static String playerId;

    public static void setEntityOnShoulder(Boolean hasEntityOnShoulder, String playerId) {
        ClientPlayerEntityOnShoulderData.hasEntityOnShoulder = hasEntityOnShoulder;
        ClientPlayerEntityOnShoulderData.playerId = playerId;
    }

    public static Boolean getHasEntityOnShoulder() {
        return hasEntityOnShoulder;
    }

    public static String getPlayerId() {
        return playerId;
    }
}

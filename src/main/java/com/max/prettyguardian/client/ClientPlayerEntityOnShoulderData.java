package com.max.prettyguardian.client;


public class ClientPlayerEntityOnShoulderData {
    private static Boolean hasEntityOnShoulder;
    private static String entityOnShoulderId;

    public static void setEntityOnShoulder(Boolean hasEntityOnShoulder, String entityOnShoulderId) {
        ClientPlayerEntityOnShoulderData.hasEntityOnShoulder = hasEntityOnShoulder;
        ClientPlayerEntityOnShoulderData.entityOnShoulderId = entityOnShoulderId;
    }

    public static Boolean getHasEntityOnShoulder() {
        return hasEntityOnShoulder;
    }

    public static String getEntityOnShoulderId() {
        return entityOnShoulderId;
    }
}

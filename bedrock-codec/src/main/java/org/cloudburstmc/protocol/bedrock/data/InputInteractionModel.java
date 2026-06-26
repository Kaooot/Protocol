package org.cloudburstmc.protocol.bedrock.data;

public enum InputInteractionModel {

    TOUCH,
    CROSSHAIR,
    CLASSIC,
    COUNT;

    private static final InputInteractionModel[] VALUES = values();

    public static InputInteractionModel from(int ordinal) {
        if (ordinal >= 0 && ordinal < VALUES.length) {
            return VALUES[ordinal];
        }
        throw new UnsupportedOperationException("Detected unknown InputInteractionModel ID: " + ordinal);
    }
}
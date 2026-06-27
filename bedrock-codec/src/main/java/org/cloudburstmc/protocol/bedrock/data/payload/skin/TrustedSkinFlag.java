package org.cloudburstmc.protocol.bedrock.data.payload.skin;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Kaooot
 */
@Getter
@RequiredArgsConstructor
public enum TrustedSkinFlag {

    UNSET("unset"),
    FALSE("false"),
    TRUE("true");

    private final String id;

    private static final TrustedSkinFlag[] VALUES = values();

    public static TrustedSkinFlag from(int ordinal) {
        if (ordinal >= 0 && ordinal < VALUES.length) {
            return VALUES[ordinal];
        }
        throw new UnsupportedOperationException("Detected unknown TrustedSkinFlag ID: " + ordinal);
    }

    public static TrustedSkinFlag from(String value) {
        for (TrustedSkinFlag trustedSkinFlag : VALUES) {
            if (trustedSkinFlag.getId().equalsIgnoreCase(value)) {
                return trustedSkinFlag;
            }
        }
        throw new UnsupportedOperationException("Detected unknown TrustedSkinFlag ID: " + value);
    }
}
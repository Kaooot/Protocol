package org.cloudburstmc.protocol.bedrock.data.payload.party;

/**
 * @author Kaooot
 */
public enum PartyDestinationCookieIntent {

    NOTIFY,
    OPT_IN,
    OPT_OUT;

    private static final PartyDestinationCookieIntent[] VALUES = values();

    public static PartyDestinationCookieIntent from(int ordinal) {
        if (ordinal >= 0 && ordinal < VALUES.length) {
            return VALUES[ordinal];
        }
        throw new UnsupportedOperationException("Detected unknown PartyDestinationCookieIntent ID: " + ordinal);
    }
}
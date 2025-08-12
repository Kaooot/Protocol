package org.cloudburstmc.protocol.bedrock.data;

/**
 * @author Kaooot
 */
public enum TextPacketType {

    RAW,
    CHAT,
    TRANSLATE,
    POPUP,
    JUKEBOX_POPUP,
    TIP,
    SYSTEM_MESSAGE,
    WHISPER,
    ANNOUNCEMENT,
    TEXT_OBJECT_WHISPER,
    TEXT_OBJECT,
    /**
     * @since v553
     */
    TEXT_OBJECT_ANNOUNCEMENT;

    private static final TextPacketType[] VALUES = values();

    public static TextPacketType from(int ordinal) {
        if (ordinal >= 0 && ordinal < VALUES.length) {
            return VALUES[ordinal];
        }
        throw new UnsupportedOperationException("Detected unknown TextPacketType ID: " + ordinal);
    }
}
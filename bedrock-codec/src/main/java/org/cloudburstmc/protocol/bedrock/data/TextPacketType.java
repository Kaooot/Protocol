package org.cloudburstmc.protocol.bedrock.data;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Kaooot
 */
@Getter
@RequiredArgsConstructor
public enum TextPacketType {

    RAW("Raw"),
    CHAT("Chat"),
    TRANSLATE("Translate"),
    POPUP("Popup"),
    JUKEBOX_POPUP("JukeboxPopup"),
    TIP("Tip"),
    SYSTEM_MESSAGE("SystemMessage"),
    WHISPER("Whisper"),
    ANNOUNCEMENT("Announcement"),
    TEXT_OBJECT_WHISPER("TextObjectWhisper"),
    TEXT_OBJECT("TextObject"),
    /**
     * @since v553
     */
    TEXT_OBJECT_ANNOUNCEMENT("TextObjectAnnouncement");

    private static final TextPacketType[] VALUES = values();

    private final String id;

    public static TextPacketType from(int ordinal) {
        if (ordinal >= 0 && ordinal < VALUES.length) {
            return VALUES[ordinal];
        }
        throw new UnsupportedOperationException("Detected unknown TextPacketType ID: " + ordinal);
    }

    public static TextPacketType from(String id) {
        for (TextPacketType value : VALUES) {
            if (value.getId().equalsIgnoreCase(id)) {
                return value;
            }
        }
        throw new UnsupportedOperationException("Detected unknown TextPacketType ID: " + id);
    }
}
package org.cloudburstmc.protocol.bedrock.data.text;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TextPacketType {

    RAW("raw"),
    CHAT("chat"),
    TRANSLATE("translate"),
    POPUP("popup"),
    JUKEBOX_POPUP("jukeboxPopup"),
    TIP("tip"),
    SYSTEM_MESSAGE("systemMessage"),
    WHISPER("whisper"),
    ANNOUNCEMENT("announcement"),
    /**
     * @since v332
     */
    TEXT_OBJECT_WHISPER("textObjectWhisper"),
    /**
     * @since v332
     */
    TEXT_OBJECT("textObject"),
    /**
     * @since v554
     */
    TEXT_OBJECT_ANNOUNCEMENT("textObjectAnnouncement");

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
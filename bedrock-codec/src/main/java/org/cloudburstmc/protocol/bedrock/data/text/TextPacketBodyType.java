package org.cloudburstmc.protocol.bedrock.data.text;

import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import org.cloudburstmc.protocol.bedrock.packet.TextPacket;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public enum TextPacketBodyType {

    MESSAGE_ONLY,
    AUTHOR_AND_MESSAGE,
    MESSAGE_AND_PARAMS;

    private static final Map<TextPacketType, TextPacketBodyType> VALUES = new HashMap<>();
    private static final Map<TextPacketBodyType, Set<TextPacketType>> BODY_TYPE_TO_TYPE_MAP = new HashMap<>();
    private static final Map<Class<?>, TextPacketBodyType> BY_CLASS = new HashMap<>();
    private static final TextPacketBodyType[] TYPES = values();

    static {
        VALUES.put(TextPacketType.RAW, MESSAGE_ONLY);
        VALUES.put(TextPacketType.TIP, MESSAGE_ONLY);
        VALUES.put(TextPacketType.SYSTEM_MESSAGE, MESSAGE_ONLY);
        VALUES.put(TextPacketType.TEXT_OBJECT_WHISPER, MESSAGE_ONLY);
        VALUES.put(TextPacketType.TEXT_OBJECT_ANNOUNCEMENT, MESSAGE_ONLY);
        VALUES.put(TextPacketType.TEXT_OBJECT, MESSAGE_ONLY);

        VALUES.put(TextPacketType.CHAT, AUTHOR_AND_MESSAGE);
        VALUES.put(TextPacketType.WHISPER, AUTHOR_AND_MESSAGE);
        VALUES.put(TextPacketType.ANNOUNCEMENT, AUTHOR_AND_MESSAGE);

        VALUES.put(TextPacketType.TRANSLATE, MESSAGE_AND_PARAMS);
        VALUES.put(TextPacketType.POPUP, MESSAGE_AND_PARAMS);
        VALUES.put(TextPacketType.JUKEBOX_POPUP, MESSAGE_AND_PARAMS);

        for (final TextPacketType type : VALUES.keySet()) {
            final TextPacketBodyType bodyType = VALUES.get(type);
            BODY_TYPE_TO_TYPE_MAP.computeIfAbsent(bodyType, textPacketBodyType -> new ObjectOpenHashSet<>())
                    .add(type);
        }

        BY_CLASS.put(MessageOnly.class, MESSAGE_ONLY);
        BY_CLASS.put(AuthorAndMessage.class, AUTHOR_AND_MESSAGE);
        BY_CLASS.put(MessageAndParams.class, MESSAGE_AND_PARAMS);
    }

    public static TextPacketBodyType from(TextPacketType type) {
        return VALUES.get(type);
    }

    public static TextPacketBodyType from(int ordinal) {
        if (ordinal >= 0 && ordinal < TYPES.length) {
            return TYPES[ordinal];
        }
        throw new UnsupportedOperationException("Detected unknown TextPacketBodyType ID: " + ordinal);
    }

    public static TextPacketBodyType from(Class<?> type) {
        return BY_CLASS.get(type);
    }

    public Set<TextPacketType> getTypes() {
        return BODY_TYPE_TO_TYPE_MAP.get(this);
    }
}
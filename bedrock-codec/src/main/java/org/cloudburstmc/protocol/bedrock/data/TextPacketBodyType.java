package org.cloudburstmc.protocol.bedrock.data;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Getter
@RequiredArgsConstructor
public enum TextPacketBodyType {
    MESSAGE_ONLY(
            Arrays.asList(
                    TextPacketType.RAW,
                    TextPacketType.TIP,
                    TextPacketType.SYSTEM_MESSAGE,
                    TextPacketType.TEXT_OBJECT_WHISPER,
                    TextPacketType.TEXT_OBJECT_ANNOUNCEMENT,
                    TextPacketType.TEXT_OBJECT
            )
    ),
    AUTHOR_AND_MESSAGE(
            Arrays.asList(
                    TextPacketType.CHAT,
                    TextPacketType.WHISPER,
                    TextPacketType.ANNOUNCEMENT
            )
    ),
    MESSAGE_AND_PARAMS(
            Arrays.asList(
                    TextPacketType.TRANSLATE,
                    TextPacketType.POPUP,
                    TextPacketType.JUKEBOX_POPUP
            )
    );

    private final List<TextPacketType> types;
}
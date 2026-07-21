package org.cloudburstmc.protocol.bedrock.data;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum BuildPlatform {

    GOOGLE(1),
    IOS(2),
    OSX(3),
    AMAZON(4),
    WIN_32(8),
    DEDICATED(9),
    SONY(11),
    NINTENDO(12),
    XBOX(13),
    LINUX(15),
    UNKNOWN(-1);

    @Getter
    private final int id;

    private static final BuildPlatform[] VALUES = values();

    public static BuildPlatform from(int id) {
        for (BuildPlatform value : VALUES) {
            if (value.getId() == id) {
                return value;
            }
        }
        return UNKNOWN;
    }
}
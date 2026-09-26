package org.cloudburstmc.protocol.bedrock.data.connection;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BuildPlatform {

    GOOGLE(1),
    IOS(2),
    OSX(3),
    AMAZON(4),
    /**
     * @deprecated
     */
    GEAR_VR(5),
    /**
     * @deprecated
     */
    HOLOLENS(6),
    /**
     * @deprecated
     */
    UWP(7),
    WIN32(8),
    DEDICATED(9),
    /**
     * @deprecated
     */
    TV_OS(10),
    SONY(11),
    NINTENDO(12),
    XBOX(13),
    /**
     * @deprecated
     */
    WINDOWS_PHONE(14),
    LINUX(15),
    UNKNOWN(-1);

    private final int id;

    private static final BuildPlatform[] VALUES = values();

    public static BuildPlatform from(int id) {
        for (BuildPlatform platform : VALUES) {
            if (platform.id == id) {
                return platform;
            }
        }
        return UNKNOWN;
    }
}
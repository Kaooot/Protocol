package org.cloudburstmc.protocol.bedrock.data;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum BuildPlatform {

    UNKNOWN(-1),
    /**
     * Android
     */
    GOOGLE(1),
    IOS(2),
    /**
     * Mac OS
     */
    OSX(3),
    /**
     * Kindle, FireTV
     */
    AMAZON(4),
    /**
     * @deprecated since v748
     */
    GEAR_VR_DEPRECATED(5),
    HOLOLENS(6),
    /**
     * Windows Store version
     */
    UWP(7),
    /**
     * Educational edition
     */
    WIN_32(8),
    DEDICATED(9),
    /**
     * Apple TV
     */
    @Deprecated
    TV_OS_DEPRECATED(10),
    /**
     * Playstation
     */
    SONY(11),
    /**
     * Nintendo Switch
     */
    NX(12),
    XBOX(13),
    @Deprecated
    WINDOWS_PHONE_DEPRECATED(14),
    LINUX(15);

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
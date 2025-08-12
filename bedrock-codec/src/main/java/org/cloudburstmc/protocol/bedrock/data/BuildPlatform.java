package org.cloudburstmc.protocol.bedrock.data;

public enum BuildPlatform {

    UNDEFINED,
    /**
     * Android
     */
    GOOGLE,
    IOS,
    /**
     * Mac OS
     */
    OSX,
    /**
     * Kindle, FireTV
     */
    AMAZON,
    /**
     * @deprecated since v748
     */
    GEAR_VR_DEPRECATED,
    HOLOLENS,
    /**
     * Windows Store version
     */
    UWP,
    /**
     * Educational edition
     */
    WIN_32,
    DEDICATED,
    /**
     * Apple TV
     */
    @Deprecated
    TV_OS_DEPRECATED,
    /**
     * Playstation
     */
    SONY,
    /**
     * Nintendo Switch
     */
    NX,
    XBOX,
    @Deprecated
    WINDOWS_PHONE_DEPRECATED,
    LINUX;

    private static final BuildPlatform[] VALUES = values();

    public static BuildPlatform from(int ordinal) {
        if (ordinal >= 0 && ordinal < VALUES.length) {
            return VALUES[ordinal];
        }
        throw new UnsupportedOperationException("Detected unknown BuildPlatform ID: " + ordinal);
    }
}
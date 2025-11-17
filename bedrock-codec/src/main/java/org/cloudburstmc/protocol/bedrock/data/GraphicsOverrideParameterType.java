package org.cloudburstmc.protocol.bedrock.data;

/**
 * @author Kaooot
 */
public enum GraphicsOverrideParameterType {

    SKY_ZENITH_COLOR,
    SKY_HORIZON_COLOR,
    HORIZON_BLEND_MIN,
    HORIZON_BLEND_MAX,
    HORIZON_BLEND_START,
    HORIZON_BLEND_MIE_START,
    RAYLEIGH_STRENGTH,
    SUN_MIE_STRENGTH,
    MOON_MIE_STRENGTH,
    SUN_GLARE_SHAPE;

    private static final GraphicsOverrideParameterType[] VALUES = values();

    public static GraphicsOverrideParameterType from(int ordinal) {
        return VALUES[ordinal];
    }
}
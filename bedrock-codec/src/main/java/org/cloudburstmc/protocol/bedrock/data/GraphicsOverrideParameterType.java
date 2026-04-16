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
    SUN_GLARE_SHAPE,
    /**
     * @since v924
     */
    CHLOROPHYLL,
    /**
     * @since v924
     */
    CDOM,
    /**
     * @since v924
     */
    SUSPENDED_SEDIMENT,
    /**
     * @since v924
     */
    WAVES_DEPTH,
    /**
     * @since v924
     */
    WAVES_FREQUENCY,
    /**
     * @since v924
     */
    WAVES_FREQUENCY_SCALING,
    /**
     * @since v924
     */
    WAVES_SPEED,
    /**
     * @since v924
     */
    WAVES_SPEED_SCALING,
    /**
     * @since v924
     */
    WAVES_SHAPE,
    /**
     * @since v924
     */
    WAVES_OCTAVES,
    /**
     * @since v924
     */
    WAVES_MIX,
    /**
     * @since v924
     */
    WAVES_PULL,
    /**
     * @since v924
     */
    WAVES_DIRECTION_INCREMENT,
    /**
     * @since v924
     */
    MIDTONES_CONTRAST,
    /**
     * @since v924
     */
    HIGHLIGHTS_CONTRAST,
    /**
     * @since v924
     */
    SHADOWS_CONTRAST,
    /**
     * @since v944
     */
    HIGHLIGHTS_GAIN,
    /**
     * @since v944
     */
    HIGHLIGHTS_GAMMA,
    /**
     * @since v944
     */
    HIGHLIGHTS_OFFSET,
    /**
     * @since v944
     */
    HIGHLIGHTS_SATURATION,
    /**
     * @since v944
     */
    MIDTONES_GAIN,
    /**
     * @since v944
     */
    MIDTONES_GAMMA,
    /**
     * @since v944
     */
    MIDTONES_OFFSET,
    /**
     * @since v944
     */
    MIDTONES_SATURATION,
    /**
     * @since v944
     */
    SHADOWS_GAIN,
    /**
     * @since v944
     */
    SHADOWS_GAMMA,
    /**
     * @since v944
     */
    SHADOWS_OFFSET,
    /**
     * @since v944
     */
    SHADOWS_SATURATION,
    /**
     * @since v944
     */
    HIGHLIGHTS_MIN,
    /**
     * @since v944
     */
    SHADOWS_MAX,
    /**
     * @since v944
     */
    TEMPERATURE,
    /**
     * @since v944
     */
    SUN_COLOR,
    /**
     * @since v944
     */
    SUN_ILLUMINANCE,
    /**
     * @since v944
     */
    MOON_COLOR,
    /**
     * @since v944
     */
    MOON_ILLUMINANCE,
    /**
     * @since v944
     */
    FLASH_COLOR,
    /**
     * @since v944
     */
    FLASH_ILLUMINANCE,
    /**
     * @since v944
     */
    AMBIENT_COLOR,
    /**
     * @since v944
     */
    AMBIENT_ILLUMINANCE,
    /**
     * @since v975
     */
    EMISSIVE_DESATURATION,
    /**
     * @since v975
     */
    SKY_INTENSITY,
    /**
     * @since v975
     */
    ORBITAL_OFFSET_DEGREES;

    private static final GraphicsOverrideParameterType[] VALUES = values();

    public static GraphicsOverrideParameterType from(int ordinal) {
        return VALUES[ordinal];
    }
}
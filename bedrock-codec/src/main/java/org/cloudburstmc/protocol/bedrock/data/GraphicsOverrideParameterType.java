package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
public enum GraphicsOverrideParameterType {
  /**
   * SkyZenithColor
   */
  SKY_ZENITH_COLOR,

  /**
   * SkyHorizonColor
   */
  SKY_HORIZON_COLOR,

  /**
   * HorizonBlendMin
   */
  HORIZON_BLEND_MIN,

  /**
   * HorizonBlendMax
   */
  HORIZON_BLEND_MAX,

  /**
   * HorizonBlendStart
   */
  HORIZON_BLEND_START,

  /**
   * HorizonBlendMieStart
   */
  HORIZON_BLEND_MIE_START,

  /**
   * RayleighStrength
   */
  RAYLEIGH_STRENGTH,

  /**
   * SunMieStrength
   */
  SUN_MIE_STRENGTH,

  /**
   * MoonMieStrength
   */
  MOON_MIE_STRENGTH,

  /**
   * SunGlareShape
   */
  SUN_GLARE_SHAPE,

  /**
   * Chlorophyll
   */
  CHLOROPHYLL,

  CDOM,

  /**
   * SuspendedSediment
   */
  SUSPENDED_SEDIMENT,

  /**
   * WavesDepth
   */
  WAVES_DEPTH,

  /**
   * WavesFrequency
   */
  WAVES_FREQUENCY,

  /**
   * WavesFrequencyScaling
   */
  WAVES_FREQUENCY_SCALING,

  /**
   * WavesSpeed
   */
  WAVES_SPEED,

  /**
   * WavesSpeedScaling
   */
  WAVES_SPEED_SCALING,

  /**
   * WavesShape
   */
  WAVES_SHAPE,

  /**
   * WavesOctaves
   */
  WAVES_OCTAVES,

  /**
   * WavesMix
   */
  WAVES_MIX,

  /**
   * WavesPull
   */
  WAVES_PULL,

  /**
   * WavesDirectionIncrement
   */
  WAVES_DIRECTION_INCREMENT,

  /**
   * MidtonesContrast
   */
  MIDTONES_CONTRAST,

  /**
   * HighlightsContrast
   */
  HIGHLIGHTS_CONTRAST,

  /**
   * ShadowsContrast
   */
  SHADOWS_CONTRAST,

  /**
   * HighlightsGain
   */
  HIGHLIGHTS_GAIN,

  /**
   * HighlightsGamma
   */
  HIGHLIGHTS_GAMMA,

  /**
   * HighlightsOffset
   */
  HIGHLIGHTS_OFFSET,

  /**
   * HighlightsSaturation
   */
  HIGHLIGHTS_SATURATION,

  /**
   * MidtonesGain
   */
  MIDTONES_GAIN,

  /**
   * MidtonesGamma
   */
  MIDTONES_GAMMA,

  /**
   * MidtonesOffset
   */
  MIDTONES_OFFSET,

  /**
   * MidtonesSaturation
   */
  MIDTONES_SATURATION,

  /**
   * ShadowsGain
   */
  SHADOWS_GAIN,

  /**
   * ShadowsGamma
   */
  SHADOWS_GAMMA,

  /**
   * ShadowsOffset
   */
  SHADOWS_OFFSET,

  /**
   * ShadowsSaturation
   */
  SHADOWS_SATURATION,

  /**
   * HighlightsMin
   */
  HIGHLIGHTS_MIN,

  /**
   * ShadowsMax
   */
  SHADOWS_MAX,

  /**
   * Temperature
   */
  TEMPERATURE,

  /**
   * SunColor
   */
  SUN_COLOR,

  /**
   * SunIlluminance
   */
  SUN_ILLUMINANCE,

  /**
   * MoonColor
   */
  MOON_COLOR,

  /**
   * MoonIlluminance
   */
  MOON_ILLUMINANCE,

  /**
   * FlashColor
   */
  FLASH_COLOR,

  /**
   * FlashIlluminance
   */
  FLASH_ILLUMINANCE,

  /**
   * AmbientColor
   */
  AMBIENT_COLOR,

  /**
   * AmbientIlluminance
   */
  AMBIENT_ILLUMINANCE,

  /**
   * EmissiveDesaturation
   */
  EMISSIVE_DESATURATION,

  /**
   * SkyIntensity
   */
  SKY_INTENSITY,

  /**
   * OrbitalOffsetDegrees
   */
  ORBITAL_OFFSET_DEGREES;

  private static final GraphicsOverrideParameterType[] VALUES = values();

  public static GraphicsOverrideParameterType from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown GraphicsOverrideParameterType ID: " + ordinal);
  }
}

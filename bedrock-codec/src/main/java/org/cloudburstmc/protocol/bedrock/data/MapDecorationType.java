package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
public enum MapDecorationType {
  /**
   * MarkerWhite
   */
  MARKER_WHITE,

  /**
   * MarkerGreen
   */
  MARKER_GREEN,

  /**
   * MarkerRed
   */
  MARKER_RED,

  /**
   * MarkerBlue
   */
  MARKER_BLUE,

  /**
   * XWhite
   */
  XWHITE,

  /**
   * TriangleRed
   */
  TRIANGLE_RED,

  /**
   * SquareWhite
   */
  SQUARE_WHITE,

  /**
   * MarkerSign
   */
  MARKER_SIGN,

  /**
   * MarkerPink
   */
  MARKER_PINK,

  /**
   * MarkerOrange
   */
  MARKER_ORANGE,

  /**
   * MarkerYellow
   */
  MARKER_YELLOW,

  /**
   * MarkerTeal
   */
  MARKER_TEAL,

  /**
   * TriangleGreen
   */
  TRIANGLE_GREEN,

  /**
   * SmallSquareWhite
   */
  SMALL_SQUARE_WHITE,

  /**
   * Mansion
   */
  MANSION,

  /**
   * Monument
   */
  MONUMENT,

  /**
   * NoDraw
   */
  NO_DRAW,

  /**
   * VillageDesert
   */
  VILLAGE_DESERT,

  /**
   * VillagePlains
   */
  VILLAGE_PLAINS,

  /**
   * VillageSavanna
   */
  VILLAGE_SAVANNA,

  /**
   * VillageSnowy
   */
  VILLAGE_SNOWY,

  /**
   * VillageTaiga
   */
  VILLAGE_TAIGA,

  /**
   * JungleTemple
   */
  JUNGLE_TEMPLE,

  /**
   * WitchHut
   */
  WITCH_HUT,

  /**
   * TrialChambers
   */
  TRIAL_CHAMBERS,

  /**
   * Count
   */
  COUNT;

  private static final MapDecorationType[] VALUES = values();

  public static MapDecorationType from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown MapDecorationType ID: " + ordinal);
  }
}

package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

public enum HudElement {
  PAPER_DOLL,

  ARMOR,

  TOOL_TIPS,

  TOUCH_CONTROLS,

  CROSSHAIR,

  HOT_BAR,

  HEALTH,

  PROGRESS_BAR,

  HUNGER,

  AIR_BUBBLES,

  HORSE_HEALTH,

  STATUS_EFFECTS,

  ITEM_TEXT;

  private static final HudElement[] VALUES = values();

  public static HudElement from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown HudElement ID: " + ordinal);
  }
}

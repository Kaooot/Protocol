package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

public enum POIBlockInteractionType {
  NONE,

  EXTEND,

  CLONE,

  LOCK,

  CREATE,

  CREATE_LOCATOR,

  RENAME,

  ITEM_PLACED,

  ITEM_REMOVED,

  COOKING,

  DOUSING,

  LIGHTING,

  HAYSTACK,

  FILLED,

  EMPTIED,

  ADD_DYE,

  DYE_ITEM,

  CLEAR_ITEM,

  ENCHANT_ARROW,

  COMPOST_ITEM_PLACED,

  RECOVERED_BONEMEAL,

  BOOK_PLACED,

  BOOK_OPENED,

  DISENCHANT,

  REPAIR,

  DISENCHANT_AND_REPAIR;

  private static final POIBlockInteractionType[] VALUES = values();

  public static POIBlockInteractionType from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown POIBlockInteractionType ID: " + ordinal);
  }
}

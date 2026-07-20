package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
public enum ShowStoreOfferRedirectType {
  MARKETPLACE_OFFER,

  DRESSING_ROOM_OFFER,

  THIRD_PARTY_SERVER_PAGE;

  private static final ShowStoreOfferRedirectType[] VALUES = values();

  public static ShowStoreOfferRedirectType from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown ShowStoreOfferRedirectType ID: " + ordinal);
  }
}

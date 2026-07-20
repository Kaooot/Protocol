package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
public enum ServerWaypointGroupAction {
  NONE,

  ADD,

  REMOVE,

  UPDATE;

  private static final ServerWaypointGroupAction[] VALUES = values();

  public static ServerWaypointGroupAction from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown ServerWaypointGroupAction ID: " + ordinal);
  }
}

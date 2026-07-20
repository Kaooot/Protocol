package org.cloudburstmc.protocol.bedrock.data;

import java.awt.Color;
import java.lang.String;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerListAddEntry {
  private Action action;

  private UUID uuid;

  private long actorUniqueID;

  private String playerName;

  private String xblXuid;

  private String platformOnlineID;

  private BuildPlatform buildPlatform;

  private SerializedSkin serializedSkin;

  private boolean isTeacher;

  private boolean isHost;

  private boolean isSubclient;

  private Color playerColor;
}

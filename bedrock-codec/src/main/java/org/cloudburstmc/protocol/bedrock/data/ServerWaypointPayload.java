package org.cloudburstmc.protocol.bedrock.data;

import java.awt.Color;
import java.lang.Long;
import java.lang.String;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cloudburstmc.math.vector.Vector2f;
import org.cloudburstmc.protocol.common.util.OptionalBoolean;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServerWaypointPayload {
  private int updateFlag;

  private OptionalBoolean isVisible = OptionalBoolean.empty();

  private WorldPosition worldPosition;

  private String texturePath;

  private Vector2f iconSize;

  private Color color;

  private OptionalBoolean clientPositionAuthority = OptionalBoolean.empty();

  private Long actorUniqueID;
}

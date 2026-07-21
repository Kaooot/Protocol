package org.cloudburstmc.protocol.bedrock.data;

import java.awt.Color;
import java.lang.Long;
import java.lang.String;
import lombok.Data;
import org.cloudburstmc.math.vector.Vector2f;
import org.cloudburstmc.protocol.common.util.OptionalBoolean;

@Data
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

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
  private int UpdateFlag;

  private OptionalBoolean IsVisible = OptionalBoolean.empty();

  private WorldPosition WorldPosition;

  private String TexturePath;

  private Vector2f IconSize;

  private Color Color;

  private OptionalBoolean ClientPositionAuthority = OptionalBoolean.empty();

  private Long ActorUniqueID;
}

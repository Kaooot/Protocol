package org.cloudburstmc.protocol.bedrock.data;

import java.lang.Long;
import lombok.Data;
import org.cloudburstmc.math.vector.Vector3i;

@Data
public class MapItemTrackedActorUniqueId {
  private MapItemTrackedActorType type;

  private Long entityID;

  private Vector3i blockPosition;
}

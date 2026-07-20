package org.cloudburstmc.protocol.bedrock.data;

import java.lang.Long;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cloudburstmc.math.vector.Vector3i;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MapItemTrackedActorUniqueId {
  private MapItemTrackedActorType type;

  private Long entityID;

  private Vector3i blockPosition;
}

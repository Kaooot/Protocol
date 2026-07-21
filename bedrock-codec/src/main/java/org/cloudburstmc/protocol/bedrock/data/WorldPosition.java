package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;
import org.cloudburstmc.math.vector.Vector3f;

@Data
public class WorldPosition {
  private Vector3f position;

  private DimensionType dimensionType;
}

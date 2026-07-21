package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;
import org.cloudburstmc.math.vector.Vector3i;

@Data
public class BlockCommandData {
  private Vector3i blockPosition;

  private int commandBlockMode;

  private boolean redstoneMode;

  private boolean isConditional;
}

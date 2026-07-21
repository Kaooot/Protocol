package org.cloudburstmc.protocol.bedrock.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cloudburstmc.math.vector.Vector3i;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlockCommandData {
  private Vector3i blockPosition;

  private int commandBlockMode;

  private boolean redstoneMode;

  private boolean isConditional;
}

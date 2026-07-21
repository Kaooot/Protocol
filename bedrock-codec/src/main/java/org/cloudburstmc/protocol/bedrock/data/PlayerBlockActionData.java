package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;
import org.cloudburstmc.math.vector.Vector3i;

@Data
public class PlayerBlockActionData {
  private PlayerActionType playerActionType;

  private Vector3i position;

  private int facing;
}

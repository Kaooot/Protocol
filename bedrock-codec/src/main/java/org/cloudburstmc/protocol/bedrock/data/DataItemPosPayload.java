package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;
import org.cloudburstmc.math.vector.Vector3i;

@Data
public class DataItemPosPayload {
  private DataItemType type;

  private Vector3i value;
}

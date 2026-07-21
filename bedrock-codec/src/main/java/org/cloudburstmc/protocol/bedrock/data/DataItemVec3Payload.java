package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;
import org.cloudburstmc.math.vector.Vector3f;

@Data
public class DataItemVec3Payload {
  private DataItemType type;

  private Vector3f value;
}

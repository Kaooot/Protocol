package org.cloudburstmc.protocol.bedrock.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cloudburstmc.math.vector.Vector3i;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataItemPosPayload {
  private DataItemType type;

  private Vector3i value;
}

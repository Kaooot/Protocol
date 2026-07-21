package org.cloudburstmc.protocol.bedrock.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntityOffsetOption {
  private float entityOffsetX;

  private float entityOffsetY;

  private float entityOffsetZ;
}

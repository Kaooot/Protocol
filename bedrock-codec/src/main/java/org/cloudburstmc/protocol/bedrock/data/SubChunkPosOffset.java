package org.cloudburstmc.protocol.bedrock.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubChunkPosOffset {
  private int subchunkOffsetX;

  private int subchunkOffsetY;

  private int subchunkOffsetZ;
}

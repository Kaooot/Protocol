package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;

@Data
public class SubChunkPosOffset {
  private int subchunkOffsetX;

  private int subchunkOffsetY;

  private int subchunkOffsetZ;
}

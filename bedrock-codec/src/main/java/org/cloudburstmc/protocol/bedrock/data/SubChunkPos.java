package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;

@Data
public class SubChunkPos {
  private int subchunkPositionX;

  private int subchunkPositionY;

  private int subchunkPositionZ;
}

package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;
import org.cloudburstmc.math.vector.Vector3i;

@Data
public class UpdateSubChunkNetworkBlockInfo {
  private Vector3i pos;

  private int runtimeId;

  private int updateFlags;

  private long syncMessageEntityUniqueID;

  private int syncMessageMessage;
}

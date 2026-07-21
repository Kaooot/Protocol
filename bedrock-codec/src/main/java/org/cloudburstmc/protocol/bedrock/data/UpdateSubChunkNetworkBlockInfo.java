package org.cloudburstmc.protocol.bedrock.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cloudburstmc.math.vector.Vector3i;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateSubChunkNetworkBlockInfo {
  private Vector3i pos;

  private int runtimeId;

  private int updateFlags;

  private long syncMessageEntityUniqueID;

  private int syncMessageMessage;
}

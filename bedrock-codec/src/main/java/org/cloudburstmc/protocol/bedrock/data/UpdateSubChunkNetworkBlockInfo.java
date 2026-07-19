package org.cloudburstmc.protocol.bedrock.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cloudburstmc.math.vector.Vector3i;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateSubChunkNetworkBlockInfo {
  private Vector3i Pos;

  private int RuntimeId;

  private int UpdateFlags;

  private long SyncMessageEntityUniqueID;

  private int SyncMessageMessage;
}

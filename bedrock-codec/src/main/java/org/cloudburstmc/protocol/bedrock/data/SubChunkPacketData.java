package org.cloudburstmc.protocol.bedrock.data;

import java.lang.Long;
import java.lang.String;
import lombok.Data;

@Data
public class SubChunkPacketData {
  private SubChunkPosOffset subChunkPosOffset;

  private SubChunkRequestResult subChunkRequestResult;

  private String serializedSubChunk;

  private SubChunkHeightmapData heightMapData;

  private Long blobId;
}

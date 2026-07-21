package org.cloudburstmc.protocol.bedrock.data;

import java.lang.Long;
import java.lang.String;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubChunkPacketData {
  private SubChunkPosOffset subChunkPosOffset;

  private SubChunkRequestResult subChunkRequestResult;

  private String serializedSubChunk;

  private SubChunkHeightmapData heightMapData;

  private Long blobId;
}

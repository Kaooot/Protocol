package org.cloudburstmc.protocol.bedrock.data;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;
import lombok.Data;

@Data
public class UpdateSubChunkBlocksChangedInfo {
  private final List<UpdateSubChunkNetworkBlockInfo> blocksChangedStandards = new ObjectArrayList<>();

  private final List<UpdateSubChunkNetworkBlockInfo> blocksChangedExtras = new ObjectArrayList<>();
}

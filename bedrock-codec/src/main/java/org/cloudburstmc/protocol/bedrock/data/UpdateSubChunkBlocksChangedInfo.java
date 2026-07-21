package org.cloudburstmc.protocol.bedrock.data;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateSubChunkBlocksChangedInfo {
  private final List<UpdateSubChunkNetworkBlockInfo> blocksChangedStandards = new ObjectArrayList<>();

  private final List<UpdateSubChunkNetworkBlockInfo> blocksChangedExtras = new ObjectArrayList<>();
}

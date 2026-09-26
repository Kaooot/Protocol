package org.cloudburstmc.protocol.bedrock.data.chunk;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;

import java.util.List;

@Data
public class UpdateSubChunkBlocksChangedInfo {

    private final List<UpdateSubChunkNetworkBlockInfo> blocksChangedStandards = new ObjectArrayList<>();
    private final List<UpdateSubChunkNetworkBlockInfo> blocksChangedExtras = new ObjectArrayList<>();
}
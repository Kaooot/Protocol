package org.cloudburstmc.protocol.bedrock.data.chunk;

import lombok.Data;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.protocol.bedrock.data.definitions.BlockDefinition;

@Data
public class UpdateSubChunkNetworkBlockInfo {

    private Vector3i pos;
    private BlockDefinition definition;
    private int updateFlags;
    private long syncMessageEntityUniqueID;
    private int syncMessageMessage;
}
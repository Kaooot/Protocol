package org.cloudburstmc.protocol.bedrock.data.biome;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.data.definitions.BlockDefinition;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BiomeSurfaceMaterialData {

    private BlockDefinition topBlock;
    private BlockDefinition midBlock;
    private BlockDefinition seaFloorBlock;
    private BlockDefinition foundationBlock;
    private BlockDefinition seaBlock;
    private int seaFloorDepth;
}
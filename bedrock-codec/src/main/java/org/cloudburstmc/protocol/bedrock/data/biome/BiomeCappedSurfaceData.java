package org.cloudburstmc.protocol.bedrock.data.biome;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.data.definitions.BlockDefinition;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BiomeCappedSurfaceData {

    private final List<BlockDefinition> floorBlocks = new ObjectArrayList<>();
    private final List<BlockDefinition> ceilingBlocks = new ObjectArrayList<>();
    private BlockDefinition seaBlock;
    private BlockDefinition foundationBlock;
    private BlockDefinition beachBlock;
}
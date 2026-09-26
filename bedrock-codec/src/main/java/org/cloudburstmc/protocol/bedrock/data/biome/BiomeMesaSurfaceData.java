package org.cloudburstmc.protocol.bedrock.data.biome;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.data.definitions.BlockDefinition;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BiomeMesaSurfaceData {

    private BlockDefinition clayMaterial;
    private BlockDefinition hardClayMaterial;
    private boolean brycePillars;
    private boolean hasForest;
}
package org.cloudburstmc.protocol.bedrock.data.biome;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.data.definitions.BlockDefinition;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BiomeMountainParamsData {

    private BlockDefinition steepBlock;
    private boolean northSlopes;
    private boolean southSlopes;
    private boolean westSlopes;
    private boolean eastSlopes;
    private boolean topSlideEnabled;
}
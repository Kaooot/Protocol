package org.cloudburstmc.protocol.bedrock.data.structure;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.data.biome.FloatRange;
import org.cloudburstmc.protocol.bedrock.data.definitions.BlockDefinition;

@Data
public class SerializedNoiseBlockSpecifier {

    private String noise;
    private Float threshold;
    private FloatRange range;
    private BlockDefinition block;
}
package org.cloudburstmc.protocol.bedrock.data.biome;

import lombok.Value;
import org.cloudburstmc.protocol.bedrock.data.definitions.BlockDefinition;

/**
 * @author Kaooot
 */
@Value
public class SerializedNoiseBlockSpecifier {

    String noise;
    float threshold;
    FloatRange range;
    BlockDefinition block;
}
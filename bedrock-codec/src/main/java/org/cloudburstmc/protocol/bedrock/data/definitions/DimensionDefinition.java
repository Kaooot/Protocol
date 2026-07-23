package org.cloudburstmc.protocol.bedrock.data.definitions;

import lombok.Value;
import org.cloudburstmc.protocol.bedrock.data.GeneratorType;
import org.cloudburstmc.protocol.bedrock.data.payload.common.DimensionType;

import java.util.UUID;

@Value
public class DimensionDefinition {
    String name;
    int heightMaximum;
    int heightMinimum;
    GeneratorType generatorType;
    /**
     * @since v975
     */
    DimensionType dimensionType;
    /**
     * @since v2168
     */
    UUID packId;
}

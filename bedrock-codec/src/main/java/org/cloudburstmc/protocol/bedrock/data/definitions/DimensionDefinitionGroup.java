package org.cloudburstmc.protocol.bedrock.data.definitions;

import lombok.Value;
import org.cloudburstmc.protocol.bedrock.data.GeneratorType;
import org.cloudburstmc.protocol.bedrock.data.payload.common.DimensionType;

@Value
public class DimensionDefinitionGroup {
    String name;
    int heightMaximum;
    int heightMinimum;
    GeneratorType generatorType;
    DimensionType dimensionType;
}

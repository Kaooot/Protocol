package org.cloudburstmc.protocol.bedrock.data.definitions;

import lombok.Value;
import org.cloudburstmc.protocol.bedrock.data.GeneratorType;

@Value
public class DimensionDefinitionGroup {
    String name;
    int heightMaximum;
    int heightMinimum;
    GeneratorType generatorType;
}

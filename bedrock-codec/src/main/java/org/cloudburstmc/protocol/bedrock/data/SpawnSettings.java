package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.payload.common.DimensionType;

/**
 * @author Kaooot
 */
@Data
@ToString
public class SpawnSettings {

    private SpawnBiomeType type;
    private String userDefinedBiomeName;
    private DimensionType dimension;
}
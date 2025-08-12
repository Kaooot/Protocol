package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;
import lombok.ToString;

/**
 * @author Kaooot
 */
@Data
@ToString
public class SpawnSettings {

    private SpawnBiomeType type;
    private String userDefinedBiomeName;
    private Dimension dimension;
}
package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.docs.DataType;
import org.cloudburstmc.protocol.bedrock.docs.Docs;

/**
 * @author Kaooot
 */
@Docs
@Data
@ToString
public class SpawnSettings {

    @Docs(type = DataType.SHORT)
    private SpawnBiomeType type;
    private String userDefinedBiomeName;
    @Docs(type = DataType.VARINT)
    private Dimension dimension;
}
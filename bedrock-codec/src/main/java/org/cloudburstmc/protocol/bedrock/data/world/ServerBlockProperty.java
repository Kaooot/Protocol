package org.cloudburstmc.protocol.bedrock.data.world;

import lombok.Data;
import org.cloudburstmc.nbt.NbtMap;

@Data
public class ServerBlockProperty {

    private String blockName;
    private NbtMap blockDefinition;
}
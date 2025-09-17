package org.cloudburstmc.protocol.bedrock.data;

import lombok.Value;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.docs.Docs;

@Docs
@Value
public class BlockPropertyData {
    @Docs(name = "Block Name")
    private final String name;
    @Docs(name = "Block Definition")
    private final NbtMap properties;
}

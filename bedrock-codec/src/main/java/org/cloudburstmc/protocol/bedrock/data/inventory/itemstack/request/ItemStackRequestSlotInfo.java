package org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.annotation.Added;
import org.cloudburstmc.protocol.bedrock.annotation.Removed;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerEnumName;
import org.cloudburstmc.protocol.bedrock.data.inventory.FullContainerName;

@Data
public class ItemStackRequestSlotInfo {

    @Deprecated
    @Removed("1.21.20")
    private ContainerEnumName containerEnumName;
    @Added("1.21.20")
    private FullContainerName fullContainerName;
    private int slot;
    private int netIdVariant;
}
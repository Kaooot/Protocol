package org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerEnumName;
import org.cloudburstmc.protocol.bedrock.data.inventory.FullContainerName;

@Data
public class ItemStackRequestSlotInfo {

    /**
     * @deprecated since v712
     */
    private ContainerEnumName containerEnumName;
    /**
     * @since v712
     */
    private FullContainerName fullContainerName;
    private int slot;
    private int netIdVariant;
}
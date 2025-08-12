package org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response;

import lombok.Value;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerEnumName;
import org.cloudburstmc.protocol.bedrock.data.inventory.FullContainerName;

import java.util.List;

/**
 * ContainerEntry holds information on what slots in a container have what item stack in them.
 */
@Value
public class ItemStackResponseContainerInfo {
    /**
     * container that the slots that follow are in.
     *
     * @deprecated since v712
     */
    ContainerEnumName containerEnumName;

    /**
     * items holds information on what item stack should be present in specific slots in the container.
     */
    List<ItemStackResponseSlotInfo> slots;

    /**
     * @since v712
     */
    FullContainerName fullContainerName;
}

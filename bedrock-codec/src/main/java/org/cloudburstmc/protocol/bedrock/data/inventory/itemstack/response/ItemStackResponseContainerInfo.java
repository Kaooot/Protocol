package org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.data.inventory.FullContainerName;

import java.util.List;

@Data
@NoArgsConstructor
public class ItemStackResponseContainerInfo {

    private FullContainerName fullContainerName;
    private final List<ItemStackResponseSlotInfo> slots = new ObjectArrayList<>();

    public ItemStackResponseContainerInfo(FullContainerName fullContainerName, List<ItemStackResponseSlotInfo> slots) {
        this.fullContainerName = fullContainerName;
        this.slots.addAll(slots);
    }
}
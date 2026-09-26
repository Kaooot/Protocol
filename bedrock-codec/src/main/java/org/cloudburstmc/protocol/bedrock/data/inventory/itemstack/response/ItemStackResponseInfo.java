package org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.ItemStackRequestId;

import java.util.List;

@Data
@NoArgsConstructor
public class ItemStackResponseInfo {

    private ItemStackNetResult result;
    private ItemStackRequestId clientRequestId;
    private final List<ItemStackResponseContainerInfo> containers = new ObjectArrayList<>();

    public ItemStackResponseInfo(ItemStackNetResult result, ItemStackRequestId clientRequestId, List<ItemStackResponseContainerInfo> containers) {
        this.result = result;
        this.clientRequestId = clientRequestId;
        this.containers.addAll(containers);
    }
}
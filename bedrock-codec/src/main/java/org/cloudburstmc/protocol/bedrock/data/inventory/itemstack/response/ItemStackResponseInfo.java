package org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response;

import lombok.Value;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.ItemStackRequest;
import org.cloudburstmc.protocol.bedrock.data.payload.inventory.net.ItemStackRequestId;

import java.util.List;

/**
 * Represents an individual response to a {@link ItemStackRequest}
 * sent as part of {@link org.cloudburstmc.protocol.bedrock.packet.ItemStackResponsePacket}.
 */
@Value
public class ItemStackResponseInfo {

    /**
     * Replaces the success boolean as of v419
     */
    ItemStackNetResult result;

    /**
     * requestId is the unique ID of the request that this response is in reaction to. If rejected, the client
     * will undo the actions from the request with this ID.
     */
    ItemStackRequestId clientRequestId;

    /**
     * containers holds information on the containers that had their contents changed as a result of the
     * request.
     */
    List<ItemStackResponseContainerInfo> containers;

    public ItemStackResponseInfo(ItemStackNetResult result, ItemStackRequestId clientRequestId, List<ItemStackResponseContainerInfo> containers) {
        this.result = result;
        this.clientRequestId = clientRequestId;
        this.containers = containers;
    }
}
package org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response;

import lombok.ToString;
import lombok.Value;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.ItemStackRequest;

import java.util.List;

/**
 * Represents an individual response to a {@link ItemStackRequest}
 * sent as part of {@link org.cloudburstmc.protocol.bedrock.packet.ItemStackResponsePacket}.
 */
@Value
public class ItemStackResponseInfo {

    /**
     * success specifies if the request with the requestId below was successful. If this is the case, the
     * containers below will have information on what slots ended up changing. If not, the container info
     * will be empty.
     * @deprecated as of v419
     */
    @ToString.Exclude
    @Deprecated
    boolean success;

    /**
     * Replaces the success boolean as of v419
     */
    ItemStackNetResult result;

    /**
     * requestId is the unique ID of the request that this response is in reaction to. If rejected, the client
     * will undo the actions from the request with this ID.
     */
    int requestId;

    /**
     * containers holds information on the containers that had their contents changed as a result of the
     * request.
     */
    List<ItemStackResponseContainerInfo> containerInfo;

    @Deprecated
    public ItemStackResponseInfo(boolean success, int requestId, List<ItemStackResponseContainerInfo> containerInfo) {
        this.success = success;
        this.requestId = requestId;
        this.containerInfo = containerInfo;
        this.result = success ? ItemStackNetResult.SUCCESS : ItemStackNetResult.ERROR;
    }

    public ItemStackResponseInfo(ItemStackNetResult result, int requestId, List<ItemStackResponseContainerInfo> containerInfo) {
        this.result = result;
        this.requestId = requestId;
        this.containerInfo = containerInfo;
        this.success = false;
    }
}

package org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request;

import lombok.Data;

@Data
public class ItemStackRequestNetworkItemInstanceDescriptor {

    private Object itemDescriptor;
    private int stackSize;
    private int blockRuntimeId;
    private String userDataBuffer;
}
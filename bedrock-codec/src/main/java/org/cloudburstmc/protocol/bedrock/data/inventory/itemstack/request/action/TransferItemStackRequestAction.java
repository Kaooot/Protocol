package org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action;

import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.ItemStackRequestSlotInfo;

/**
 * TransferStackRequestActionData is the structure shared by StackRequestActions that transfer items from one
 * slot into another
 */
public interface TransferItemStackRequestAction extends ItemStackRequestAction {

    int getCount();

    ItemStackRequestSlotInfo getSource();

    ItemStackRequestSlotInfo getDestination();
}

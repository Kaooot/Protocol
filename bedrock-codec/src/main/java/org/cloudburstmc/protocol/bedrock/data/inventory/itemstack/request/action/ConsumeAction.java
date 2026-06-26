package org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action;

import lombok.Value;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.ItemStackRequestSlotInfo;

/**
 * ConsumeStackRequestAction is sent by the client when it uses an item to craft another item. The original
 * item is 'consumed'.
 */
@Value
public class ConsumeAction implements ItemStackRequestAction {
    int amount;
    ItemStackRequestSlotInfo source;

    @Override
    public ItemStackRequestActionType getType() {
        return ItemStackRequestActionType.CONSUME;
    }
}

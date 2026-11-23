package org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.ItemStackRequestActionType;

@Data
public class ItemStackRequestBeaconPaymentAction implements ItemStackRequestAction {

    @Override
    public ItemStackRequestActionType getType() {
        return ItemStackRequestActionType.SCREEN_BEACON_PAYMENT;
    }


    private ItemStackRequestActionType actionType;
    private int primaryEffectId;
    private int secondaryEffectId;
}
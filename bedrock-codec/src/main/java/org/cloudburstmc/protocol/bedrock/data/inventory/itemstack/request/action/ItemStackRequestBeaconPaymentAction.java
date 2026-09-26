package org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.ItemStackRequestActionType;

@Data
public class ItemStackRequestBeaconPaymentAction {

    private ItemStackRequestActionType actionType;
    private int primaryEffectId;
    private int secondaryEffectId;
}
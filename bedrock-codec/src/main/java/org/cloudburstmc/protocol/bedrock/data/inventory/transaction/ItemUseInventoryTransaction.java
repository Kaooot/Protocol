package org.cloudburstmc.protocol.bedrock.data.inventory.transaction;

import lombok.Data;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.protocol.bedrock.data.definitions.BlockDefinition;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import org.cloudburstmc.protocol.bedrock.data.player.HandSlot;

@Data
public class ItemUseInventoryTransaction {

    private InventoryTransaction actions;
    private ItemUseActionType actionType;
    /**
     * @since v712
     */
    private ItemUseTriggerType triggerType;
    private Vector3i position;
    private int face;
    private int slot;
    /**
     * @since v2193
     */
    private HandSlot hand;
    private ItemData item;
    private Vector3f fromPosition;
    private Vector3f clickPosition;
    /**
     * @since v340
     */
    private BlockDefinition targetBlockId;
    /**
     * @since v712
     */
    private ItemUsePredictedResult clientInteractPrediction;
    /**
     * @since v944
     */
    private ItemUseClientCooldownState clientCooldownState;
}
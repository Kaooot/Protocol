package org.cloudburstmc.protocol.bedrock.packet;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.inventory.FullContainerName;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import org.cloudburstmc.protocol.common.PacketSignal;

@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class InventorySlotPacket implements BedrockPacket {

    private int containerId;
    private int slot;
    /**
     * @since v729
     * @deprecated since v748. Use storageItem ItemData size instead.
     */
    private int dynamicContainerSize;
    private FullContainerName fullContainerName;
    private ItemData storageItem;
    private ItemData item;

    @Override
    public final PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.INVENTORY_SLOT;
    }

    @Override
    public InventorySlotPacket clone() {
        try {
            return (InventorySlotPacket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}
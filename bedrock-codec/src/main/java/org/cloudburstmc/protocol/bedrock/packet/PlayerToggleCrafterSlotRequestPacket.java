package org.cloudburstmc.protocol.bedrock.packet;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.protocol.common.PacketSignal;

@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class PlayerToggleCrafterSlotRequestPacket implements BedrockPacket {
    private Vector3i pos;
    private byte slotIndex;
    private boolean isDisabled;

    @Override
    public PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.PLAYER_TOGGLE_CRAFTER_SLOT_REQUEST;
    }

    @Override
    public PlayerToggleCrafterSlotRequestPacket clone() {
        try {
            return (PlayerToggleCrafterSlotRequestPacket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}


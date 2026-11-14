package org.cloudburstmc.protocol.bedrock.packet;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.inventory.VirtualContainerCloseCause;
import org.cloudburstmc.protocol.bedrock.data.inventory.VirtualContainerCloseKind;
import org.cloudburstmc.protocol.common.PacketSignal;

@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class VirtualContainerClosePacket implements BedrockPacket {

    private long sessionId;
    private VirtualContainerCloseKind kind;
    private VirtualContainerCloseCause cause;

    @Override
    public final PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.VIRTUAL_CONTAINER_CLOSE;
    }

    @Override
    public VirtualContainerClosePacket clone() {
        try {
            return (VirtualContainerClosePacket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}
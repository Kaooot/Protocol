package org.cloudburstmc.protocol.bedrock.packet;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.protocol.bedrock.data.DebugMarkerData;
import org.cloudburstmc.protocol.bedrock.data.PayloadType;
import org.cloudburstmc.protocol.common.PacketSignal;

@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class ClientboundDebugRendererPacket implements BedrockPacket {

    private PayloadType type;
    private DebugMarkerData debugMarkerData;

    @Override
    public PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.CLIENTBOUND_DEBUG_RENDERER;
    }

    @Override
    public ClientboundDebugRendererPacket clone() {
        try {
            return (ClientboundDebugRendererPacket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}


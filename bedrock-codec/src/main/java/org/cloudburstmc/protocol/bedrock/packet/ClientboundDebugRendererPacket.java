package org.cloudburstmc.protocol.bedrock.packet;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.protocol.bedrock.data.ClientboundDebugRendererType;
import org.cloudburstmc.protocol.common.PacketSignal;

@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class ClientboundDebugRendererPacket implements BedrockPacket {

    private ClientboundDebugRendererType debugMarkerType;

    /**
     * Only used if {@link #debugMarkerType} is set to {@link ClientboundDebugRendererType#ADD_DEBUG_MARKER_CUBE}.
     */
    private String debugMarkerText;
    /**
     * Only used if {@link #debugMarkerType} is set to {@link ClientboundDebugRendererType#ADD_DEBUG_MARKER_CUBE}.
     */
    private Vector3f debugMarkerPosition;
    /**
     * Only used if {@link #debugMarkerType} is set to {@link ClientboundDebugRendererType#ADD_DEBUG_MARKER_CUBE}.
     */
    private float debugMarkerColorRed;
    /**
     * Only used if {@link #debugMarkerType} is set to {@link ClientboundDebugRendererType#ADD_DEBUG_MARKER_CUBE}.
     */
    private float debugMarkerColorGreen;
    /**
     * Only used if {@link #debugMarkerType} is set to {@link ClientboundDebugRendererType#ADD_DEBUG_MARKER_CUBE}.
     */
    private float debugMarkerColorBlue;
    /**
     * Only used if {@link #debugMarkerType} is set to {@link ClientboundDebugRendererType#ADD_DEBUG_MARKER_CUBE}.
     */
    private float debugMarkerColorAlpha;
    /**
     * Only used if {@link #debugMarkerType} is set to {@link ClientboundDebugRendererType#ADD_DEBUG_MARKER_CUBE}.
     * In milliseconds.
     */
    private long debugMarkerDurationMS;

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


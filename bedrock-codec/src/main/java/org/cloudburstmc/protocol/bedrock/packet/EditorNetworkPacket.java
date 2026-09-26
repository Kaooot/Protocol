package org.cloudburstmc.protocol.bedrock.packet;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.common.PacketSignal;

@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class EditorNetworkPacket implements BedrockPacket {

    private Object binaryPayload; // NBT like
    private boolean routeToManager;
    /**
     * @since v944
     */
    private String rawVariantName;
    /**
     * @since v944
     */
    private String rawVariantData;

    @Override
    public final PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.EDITOR_NETWORK;
    }

    @Override
    public EditorNetworkPacket clone() {
        try {
            return (EditorNetworkPacket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}
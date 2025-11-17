package org.cloudburstmc.protocol.bedrock.packet;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.ddui.DataStoreUpdate;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * @author Kaooot
 */
@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class ServerboundDataStorePacket implements BedrockPacket {

    private DataStoreUpdate update;

    @Override
    public PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.SERVERBOUND_DATA_STORE;
    }

    @Override
    public BedrockPacket clone() {
        try {
            return (ServerboundDataStorePacket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}
package org.cloudburstmc.protocol.bedrock.packet;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.connection.DisconnectFailReason;
import org.cloudburstmc.protocol.bedrock.data.connection.DisconnectPacketMessages;
import org.cloudburstmc.protocol.common.PacketSignal;

@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class DisconnectPacket implements BedrockPacket {

    private DisconnectFailReason reason;
    private DisconnectPacketMessages messages;

    @Override
    public final PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.DISCONNECT;
    }

    @Override
    public DisconnectPacket clone() {
        try {
            return (DisconnectPacket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}
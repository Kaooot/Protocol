package org.cloudburstmc.protocol.bedrock.packet;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.ddui.DataDrivenScreenClosedReason;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * @author Kaooot
 */
@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class ServerboundDataDrivenScreenClosedPacket implements BedrockPacket {

    private int formId;
    private DataDrivenScreenClosedReason closeReason;

    @Override
    public PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.SERVERBOUND_DATA_DRIVEN_SCREEN_CLOSED;
    }

    @Override
    public ServerboundDataDrivenScreenClosedPacket clone() {
        try {
            return (ServerboundDataDrivenScreenClosedPacket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}
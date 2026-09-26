package org.cloudburstmc.protocol.bedrock.packet;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.connection.PacketViolationSeverity;
import org.cloudburstmc.protocol.bedrock.data.connection.PacketViolationType;
import org.cloudburstmc.protocol.common.PacketSignal;

@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class PacketViolationWarningPacket implements BedrockPacket {

    private PacketViolationType violationType;
    private PacketViolationSeverity violationSeverity;
    private int violationPacketid;
    private String violationContext;

    @Override
    public final PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.VIOLATION_WARNING;
    }

    @Override
    public PacketViolationWarningPacket clone() {
        try {
            return (PacketViolationWarningPacket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}
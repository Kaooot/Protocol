package org.cloudburstmc.protocol.bedrock.packet;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.DisconnectFailReason;
import org.cloudburstmc.protocol.bedrock.docs.DataType;
import org.cloudburstmc.protocol.bedrock.docs.Docs;
import org.cloudburstmc.protocol.bedrock.docs.condition.Condition;
import org.cloudburstmc.protocol.bedrock.docs.condition.Conditions;
import org.cloudburstmc.protocol.common.PacketSignal;

@Data
@Docs
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class DisconnectPacket implements BedrockPacket {
    @Docs(type = DataType.VARINT)
    private DisconnectFailReason reason = DisconnectFailReason.UNKNOWN;
    private boolean skipMessage;
    @Conditions(field = "skipMessage", values = {@Condition(true)})
    private String message;
    /**
     * @since v712
     */
    @Conditions(field = "skipMessage", values = {@Condition(true)})
    private String filteredMessage = "";

    @Override
    public final PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

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


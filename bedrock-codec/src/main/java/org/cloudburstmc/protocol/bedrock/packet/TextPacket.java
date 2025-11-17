package org.cloudburstmc.protocol.bedrock.packet;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.TextPacketType;
import org.cloudburstmc.protocol.bedrock.data.payload.text.TextPacketBody;
import org.cloudburstmc.protocol.common.PacketSignal;

@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class TextPacket implements BedrockPacket {
    private TextPacketType messageType;
    private boolean localize;
    private TextPacketBody body;
    private String sendersXUID;
    private String platformId = "";
    /**
     * @since v685
     */
    private String filteredMessage = "";

    @Override
    public final PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    public BedrockPacketType getPacketType() {
        return BedrockPacketType.TEXT;
    }

    @Override
    public TextPacket clone() {
        try {
            return (TextPacket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}


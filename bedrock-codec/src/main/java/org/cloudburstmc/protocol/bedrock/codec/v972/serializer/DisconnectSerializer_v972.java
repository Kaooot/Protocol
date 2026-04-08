package org.cloudburstmc.protocol.bedrock.codec.v972.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v712.serializer.DisconnectSerializer_v712;
import org.cloudburstmc.protocol.bedrock.data.DisconnectFailReason;
import org.cloudburstmc.protocol.bedrock.packet.DisconnectPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DisconnectSerializer_v972 extends DisconnectSerializer_v712 {
    public static final DisconnectSerializer_v972 INSTANCE = new DisconnectSerializer_v972();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, DisconnectPacket packet) {
        VarInts.writeUnsignedInt(buffer, packet.getReason().ordinal());
        final int type = packet.getMessages() != null ? 0 : 1;
        VarInts.writeUnsignedInt(buffer, type);
        if (type == 0) {
            this.writeMessages(buffer, helper, packet.getMessages());
        }
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, DisconnectPacket packet) {
        packet.setReason(DisconnectFailReason.values()[VarInts.readUnsignedInt(buffer)]);
        final int type = VarInts.readUnsignedInt(buffer);
        if (type == 0) {
            packet.setMessages(this.readMessages(buffer, helper));
        }
    }
}
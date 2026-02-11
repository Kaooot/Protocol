package org.cloudburstmc.protocol.bedrock.codec.v935.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.ddui.DataDrivenScreenClosedReason;
import org.cloudburstmc.protocol.bedrock.packet.ServerboundDataDrivenScreenClosedPacket;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ServerboundDataDrivenScreenClosedSerializer_v935 implements BedrockPacketSerializer<ServerboundDataDrivenScreenClosedPacket> {
    public static final ServerboundDataDrivenScreenClosedSerializer_v935 INSTANCE = new ServerboundDataDrivenScreenClosedSerializer_v935();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ServerboundDataDrivenScreenClosedPacket packet) {
        buffer.writeIntLE(packet.getFormId());
        helper.writeString(buffer, packet.getCloseReason().getId());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ServerboundDataDrivenScreenClosedPacket packet) {
        packet.setFormId(buffer.readIntLE());
        packet.setCloseReason(DataDrivenScreenClosedReason.from(helper.readString(buffer)));
    }
}
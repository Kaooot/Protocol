package org.cloudburstmc.protocol.bedrock.codec.v859.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.misc.GraphicsOverrideParameterType;
import org.cloudburstmc.protocol.bedrock.packet.GraphicsOverrideParameterPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GraphicsOverrideParameterSerializer_v859 implements BedrockPacketSerializer<GraphicsOverrideParameterPacket> {

    public static final GraphicsOverrideParameterSerializer_v859 INSTANCE = new GraphicsOverrideParameterSerializer_v859();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, GraphicsOverrideParameterPacket packet) {
        helper.writeArray(buffer, packet.getParameterKeyframeValues().entrySet(), (buf, aHelper, entry) -> {
            buf.writeFloatLE(entry.getKey());
            helper.writeVector3f(buf, entry.getValue());
        });
        helper.writeString(buffer, packet.getBiomeIdentifier());
        buffer.writeByte(packet.getIdentifierForParameter().ordinal());
        buffer.writeBoolean(packet.isResetParameter());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, GraphicsOverrideParameterPacket packet) {
        int length = VarInts.readUnsignedInt(buffer);
        for (int i = 0; i < length; i++) {
            float key = buffer.readFloatLE();
            Vector3f value = helper.readVector3f(buffer);
            packet.getParameterKeyframeValues().put(key, value);
        }
        packet.setBiomeIdentifier(helper.readString(buffer));
        packet.setIdentifierForParameter(GraphicsOverrideParameterType.values()[buffer.readUnsignedByte()]);
        packet.setResetParameter(buffer.readBoolean());
    }
}

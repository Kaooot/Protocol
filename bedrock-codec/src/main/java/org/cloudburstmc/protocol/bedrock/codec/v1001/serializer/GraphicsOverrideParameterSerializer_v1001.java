package org.cloudburstmc.protocol.bedrock.codec.v1001.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v924.serializer.GraphicsOverrideParameterSerializer_v924;
import org.cloudburstmc.protocol.bedrock.data.misc.GraphicsOverrideParameterType;
import org.cloudburstmc.protocol.bedrock.packet.GraphicsOverrideParameterPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

public class GraphicsOverrideParameterSerializer_v1001 extends GraphicsOverrideParameterSerializer_v924 {

    public static final GraphicsOverrideParameterSerializer_v1001 INSTANCE = new GraphicsOverrideParameterSerializer_v1001();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, GraphicsOverrideParameterPacket packet) {
        helper.writeArray(buffer, packet.getParameterKeyframeValues().entrySet(), (buf, aHelper, entry) -> {
            buf.writeFloatLE(entry.getKey());
            helper.writeVector3f(buf, entry.getValue());
        });
        helper.writeOptionalNull(buffer, packet.getFloatValue(), ByteBuf::writeFloatLE);
        helper.writeOptionalNull(buffer, packet.getVec3Value(), (buf, h, v) -> h.writeVector3f(buf, v));
        helper.writeString(buffer, packet.getBiomeIdentifier());
        helper.writeOptionalNull(buffer, packet.getPlayerIdentifier(), (buf, h, v) -> h.writeString(buf, v)); // new
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
        packet.setFloatValue(helper.readOptional(buffer, null, ByteBuf::readFloatLE));
        packet.setVec3Value(helper.readOptional(buffer, null, (buf, h) -> h.readVector3f(buf)));
        packet.setBiomeIdentifier(helper.readString(buffer));
        packet.setPlayerIdentifier(helper.readOptional(buffer, null, (buf, h) -> h.readString(buf))); // new
        packet.setIdentifierForParameter(GraphicsOverrideParameterType.values()[buffer.readUnsignedByte()]);
        packet.setResetParameter(buffer.readBoolean());
    }
}
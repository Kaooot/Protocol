package org.cloudburstmc.protocol.bedrock.codec.v859.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.GraphicsOverrideParameterType;
import org.cloudburstmc.protocol.bedrock.packet.GraphicsOverrideParameterPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GraphicsOverrideParameterSerializer_v859 implements BedrockPacketSerializer<GraphicsOverrideParameterPacket> {
    public static final GraphicsOverrideParameterSerializer_v859 INSTANCE = new GraphicsOverrideParameterSerializer_v859();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, GraphicsOverrideParameterPacket packet) {
        helper.writeArray(buffer, packet.getParameterKeyframeValues().entrySet(), (buf, codecHelper, entry) -> {
            buf.writeFloatLE(entry.getKey());
            codecHelper.writeVector3f(buf, entry.getValue());
        });
        helper.writeString(buffer, packet.getBiomeIdentifier());
        buffer.writeByte(packet.getIdentifierForParameter().ordinal());
        buffer.writeBoolean(packet.isResetParameter());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, GraphicsOverrideParameterPacket packet) {
        final Map<Float, Vector3f> parameterKeyframeValues = new HashMap<>();
        final int length = VarInts.readUnsignedInt(buffer);

        for (int i = 0; i < length; i++) {
            final float key = buffer.readFloatLE();
            final Vector3f value = helper.readVector3f(buffer);

            parameterKeyframeValues.put(key, value);
        }

        packet.getParameterKeyframeValues().putAll(parameterKeyframeValues);
        packet.setBiomeIdentifier(helper.readStringMaxLen(buffer, 255));
        packet.setIdentifierForParameter(GraphicsOverrideParameterType.from(buffer.readUnsignedByte()));
        packet.setResetParameter(buffer.readBoolean());
    }
}
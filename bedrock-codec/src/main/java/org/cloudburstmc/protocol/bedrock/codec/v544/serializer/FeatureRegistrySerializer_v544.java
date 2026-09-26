package org.cloudburstmc.protocol.bedrock.codec.v544.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.world.FeatureRegistryFeatureBinaryJsonFormat;
import org.cloudburstmc.protocol.bedrock.packet.FeatureRegistryPacket;

public class FeatureRegistrySerializer_v544 implements BedrockPacketSerializer<FeatureRegistryPacket> {

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, FeatureRegistryPacket packet) {
        helper.writeArray(buffer, packet.getFeaturesDataList(), (buf, codecHelper, data) -> {
            codecHelper.writeString(buf, data.getFeatureName());
            codecHelper.writeString(buf, data.getBinaryJsonOutput());
        });
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, FeatureRegistryPacket packet) {
        helper.readArray(buffer, packet.getFeaturesDataList(), (buf, codecHelper) -> {
            final FeatureRegistryFeatureBinaryJsonFormat format = new FeatureRegistryFeatureBinaryJsonFormat();
            format.setFeatureName(codecHelper.readString(buf));
            format.setBinaryJsonOutput(codecHelper.readString(buf));
            return format;
        });
    }
}
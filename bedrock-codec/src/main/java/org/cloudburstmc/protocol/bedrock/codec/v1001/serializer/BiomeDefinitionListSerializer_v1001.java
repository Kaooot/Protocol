package org.cloudburstmc.protocol.bedrock.codec.v1001.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v975.serializer.BiomeDefinitionListSerializer_v975;
import org.cloudburstmc.protocol.bedrock.data.biome.FloatRange;
import org.cloudburstmc.protocol.bedrock.data.structure.SerializedNoiseBlockSpecifier;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BiomeDefinitionListSerializer_v1001 extends BiomeDefinitionListSerializer_v975 {
    public static final BiomeDefinitionListSerializer_v1001 INSTANCE = new BiomeDefinitionListSerializer_v1001();

    @Override
    protected void writeSerializedNoiseBlockSpecifier(ByteBuf buffer, BedrockCodecHelper helper, SerializedNoiseBlockSpecifier specifier) {
        helper.writeString(buffer, specifier.getNoise());
        buffer.writeFloatLE(specifier.getThreshold());
        this.writeFloatRange(buffer, helper, specifier.getRange());
        this.writeBlock(buffer, helper, specifier.getBlock());
    }

    @Override
    protected SerializedNoiseBlockSpecifier readSerializedNoiseBlockSpecifier(ByteBuf buffer, BedrockCodecHelper helper) {
        final SerializedNoiseBlockSpecifier serializedNoiseBlockSpecifier = new SerializedNoiseBlockSpecifier();
        serializedNoiseBlockSpecifier.setNoise(helper.readString(buffer));
        serializedNoiseBlockSpecifier.setThreshold(buffer.readFloatLE());
        serializedNoiseBlockSpecifier.setRange(this.readFloatRange(buffer, helper));
        serializedNoiseBlockSpecifier.setBlock(this.readBlock(buffer, helper));
        return serializedNoiseBlockSpecifier;
    }

    protected void writeFloatRange(ByteBuf buffer, BedrockCodecHelper helper, FloatRange range) {
        buffer.writeFloatLE(range.getMin());
        buffer.writeFloatLE(range.getMax());
    }

    protected FloatRange readFloatRange(ByteBuf buffer, BedrockCodecHelper helper) {
        final FloatRange floatRange = new FloatRange();
        floatRange.setMin(buffer.readFloatLE());
        floatRange.setMax(buffer.readFloatLE());
        return floatRange;
    }
}
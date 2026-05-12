package org.cloudburstmc.protocol.bedrock.codec.v998.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v975.serializer.BiomeDefinitionListSerializer_v975;
import org.cloudburstmc.protocol.bedrock.data.biome.FloatRange;
import org.cloudburstmc.protocol.bedrock.data.biome.SerializedNoiseBlockSpecifier;
import org.cloudburstmc.protocol.bedrock.data.definitions.BlockDefinition;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BiomeDefinitionListSerializer_v998 extends BiomeDefinitionListSerializer_v975 {
    public static final BiomeDefinitionListSerializer_v998 INSTANCE = new BiomeDefinitionListSerializer_v998();

    protected void writeSerializedNoiseBlockSpecifier(ByteBuf buffer, BedrockCodecHelper helper, SerializedNoiseBlockSpecifier specifier) {
        helper.writeString(buffer, specifier.getNoise());
        buffer.writeFloatLE(specifier.getThreshold());
        this.writeFloatRange(buffer, helper, specifier.getRange());
        this.writeBlock(buffer, helper, specifier.getBlock());
    }

    protected SerializedNoiseBlockSpecifier readSerializedNoiseBlockSpecifier(ByteBuf buffer, BedrockCodecHelper helper) {
        final String noise = helper.readString(buffer);
        final float threshold = buffer.readFloatLE();
        final FloatRange range = this.readFloatRange(buffer, helper);
        final BlockDefinition block = this.readBlock(buffer, helper);
        return new SerializedNoiseBlockSpecifier(noise, threshold, range, block);
    }

    protected void writeFloatRange(ByteBuf buffer, BedrockCodecHelper helper, FloatRange range) {
        buffer.writeFloatLE(range.getMin());
        buffer.writeFloatLE(range.getMax());
    }

    protected FloatRange readFloatRange(ByteBuf buffer, BedrockCodecHelper helper) {
        final float min = buffer.readFloatLE();
        final float max = buffer.readFloatLE();
        return new FloatRange(min, max);
    }
}
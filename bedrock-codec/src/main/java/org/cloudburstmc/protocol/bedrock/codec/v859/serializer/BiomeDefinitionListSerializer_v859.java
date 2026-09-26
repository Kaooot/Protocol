package org.cloudburstmc.protocol.bedrock.codec.v859.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v844.serializer.BiomeDefinitionListSerializer_v844;
import org.cloudburstmc.protocol.bedrock.data.biome.BiomeDefinitionChunkGenData;
import org.cloudburstmc.protocol.bedrock.data.biome.BiomeReplacementData;
import org.cloudburstmc.protocol.bedrock.data.biome.BiomeReplacementsData;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BiomeDefinitionListSerializer_v859 extends BiomeDefinitionListSerializer_v844 {
    public static final BiomeDefinitionListSerializer_v859 INSTANCE = new BiomeDefinitionListSerializer_v859();

    @Override
    protected void writeBiomeDefinitionChunkGenData(ByteBuf buffer, BedrockCodecHelper helper, BiomeDefinitionChunkGenData definitionChunkGen) {
        super.writeBiomeDefinitionChunkGenData(buffer, helper, definitionChunkGen);
        helper.writeOptionalNull(buffer, definitionChunkGen.getReplacementBiomes(), this::writeBiomeReplacementsData);
    }

    @Override
    protected BiomeDefinitionChunkGenData readBiomeDefinitionChunkGenData(ByteBuf buffer, BedrockCodecHelper helper) {
        final BiomeDefinitionChunkGenData data = super.readBiomeDefinitionChunkGenData(buffer, helper);
        data.setReplacementBiomes(helper.readOptional(buffer, null, this::readBiomeReplacementsData));
        return data;
    }

    protected void writeBiomeReplacementsData(ByteBuf buffer, BedrockCodecHelper helper, BiomeReplacementsData data) {
        this.writeBiomeReplacementData(buffer, helper, data.getBiomeReplacements().get(0));
    }

    protected BiomeReplacementsData readBiomeReplacementsData(ByteBuf buffer, BedrockCodecHelper helper) {
        final BiomeReplacementsData data = new BiomeReplacementsData();
        data.getBiomeReplacements().add(this.readBiomeReplacementData(buffer, helper));
        return data;
    }

    protected void writeBiomeReplacementData(ByteBuf buffer, BedrockCodecHelper helper, BiomeReplacementData data) {
        buffer.writeShortLE(data.getReplacementBiome());
        buffer.writeShortLE(data.getDimension());
        helper.writeArray(buffer, data.getTargetBiomes(), (buf, codecHelper, targetBiome) -> buf.writeShortLE(targetBiome));
        buffer.writeFloatLE(data.getAmount());
        buffer.writeFloatLE(data.getNoiseFrequencyScale());
        buffer.writeIntLE(data.getReplacementIndex());
    }

    protected BiomeReplacementData readBiomeReplacementData(ByteBuf buffer, BedrockCodecHelper helper) {
        final BiomeReplacementData data = new BiomeReplacementData();
        data.setReplacementBiome(buffer.readShortLE());
        data.setDimension(buffer.readShortLE());
        helper.readArray(buffer, data.getTargetBiomes(), (buf, codecHelper) -> (int) buf.readShortLE());
        data.setAmount(buffer.readFloatLE());
        data.setNoiseFrequencyScale(buffer.readFloatLE());
        data.setReplacementIndex(buffer.readIntLE());
        return data;
    }
}
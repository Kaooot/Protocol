package org.cloudburstmc.protocol.bedrock.codec.v975.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v924.serializer.BiomeDefinitionListSerializer_v924;
import org.cloudburstmc.protocol.bedrock.data.biome.BiomeDefinitionChunkGenData;
import org.cloudburstmc.protocol.bedrock.data.biome.BiomeNoiseGradientSurfaceData;
import org.cloudburstmc.protocol.bedrock.data.biome.BiomeReplacementsData;
import org.cloudburstmc.protocol.bedrock.data.biome.BiomeSurfaceBuilderData;
import org.cloudburstmc.protocol.bedrock.data.structure.NoiseDescriptor;
import org.cloudburstmc.protocol.bedrock.data.structure.SerializedNoiseBlockSpecifier;
import org.cloudburstmc.protocol.bedrock.data.world.VillageType;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BiomeDefinitionListSerializer_v975 extends BiomeDefinitionListSerializer_v924 {
    public static final BiomeDefinitionListSerializer_v975 INSTANCE = new BiomeDefinitionListSerializer_v975();

    @Override
    protected void writeBiomeDefinitionChunkGenData(ByteBuf buffer, BedrockCodecHelper helper, BiomeDefinitionChunkGenData definitionChunkGen) {
        helper.writeOptionalNull(buffer, definitionChunkGen.getClimate(), this::writeClimate);
        helper.writeOptionalNull(buffer, definitionChunkGen.getConsolidatedFeatures(), this::writeBiomeConsolidatedFeaturesData);
        helper.writeOptionalNull(buffer, definitionChunkGen.getMountainParams(), this::writeBiomeMountainParamsData);
        helper.writeOptionalNull(buffer, definitionChunkGen.getSurfaceMaterialAdjustments(), this::writeBiomeSurfaceMaterialAdjustmentData);
        helper.writeOptionalNull(buffer, definitionChunkGen.getOverworldGenRules(), this::writeBiomeOverworldGenRulesData);
        helper.writeOptionalNull(buffer, definitionChunkGen.getMultinoiseGenRules(), this::writeBiomeMultinoiseGenRulesData);
        helper.writeOptionalNull(buffer, definitionChunkGen.getLegacyWorldGenRules(), this::writeBiomeLegacyWorldGenRulesData);
        helper.writeOptionalNull(buffer, definitionChunkGen.getReplacementBiomes(), this::writeBiomeReplacementsData);
        helper.writeOptionalNull(buffer, definitionChunkGen.getVillageType(), (byteBuf, villageType) -> byteBuf.writeByte(villageType.ordinal()));
        helper.writeOptionalNull(buffer, definitionChunkGen.getSurfaceBuilderData(), this::writeBiomeSurfaceBuilderData);
        helper.writeOptionalNull(buffer, definitionChunkGen.getSubsurfaceBuilderData(), this::writeBiomeSurfaceBuilderData);
    }

    @Override
    protected BiomeDefinitionChunkGenData readBiomeDefinitionChunkGenData(ByteBuf buffer, BedrockCodecHelper helper) {
        final BiomeDefinitionChunkGenData data = new BiomeDefinitionChunkGenData();
        data.setClimate(helper.readOptional(buffer, null, this::readClimate));
        data.setConsolidatedFeatures(helper.readOptional(buffer, null, this::readBiomeConsolidatedFeaturesData));
        data.setMountainParams(helper.readOptional(buffer, null, this::readBiomeMountainParamsData));
        data.setSurfaceMaterialAdjustments(helper.readOptional(buffer, null, this::readBiomeSurfaceMaterialAdjustmentData));
        data.setOverworldGenRules(helper.readOptional(buffer, null, this::readBiomeOverworldGenRulesData));
        data.setMultinoiseGenRules(helper.readOptional(buffer, null, this::readBiomeMultinoiseGenRulesData));
        data.setLegacyWorldGenRules(helper.readOptional(buffer, null, this::readBiomeLegacyWorldGenRulesData));
        data.setReplacementBiomes(helper.readOptional(buffer, null, this::readBiomeReplacementsData));
        data.setVillageType(helper.readOptional(buffer, null, (byteBuf, codecHelper) -> VillageType.from(byteBuf.readUnsignedByte())));
        data.setSurfaceBuilderData(helper.readOptional(buffer, null, this::readBiomeSurfaceBuilderData));
        data.setSubsurfaceBuilderData(helper.readOptional(buffer, null, this::readBiomeSurfaceBuilderData));
        return data;
    }

    @Override
    protected void writeBiomeReplacementsData(ByteBuf buffer, BedrockCodecHelper helper, BiomeReplacementsData data) {
        helper.writeArray(buffer, data.getBiomeReplacements(), this::writeBiomeReplacementData);
    }

    @Override
    protected BiomeReplacementsData readBiomeReplacementsData(ByteBuf buffer, BedrockCodecHelper helper) {
        final BiomeReplacementsData data = new BiomeReplacementsData();
        helper.readArray(buffer, data.getBiomeReplacements(), this::readBiomeReplacementData);
        return data;
    }

    @Override
    protected void writeBiomeSurfaceBuilderData(ByteBuf buffer, BedrockCodecHelper helper, BiomeSurfaceBuilderData data) {
        super.writeBiomeSurfaceBuilderData(buffer, helper, data);
        helper.writeOptionalNull(buffer, data.getNoiseGradientSurface(), this::writeBiomeNoiseGradientSurfaceData);
    }

    @Override
    protected BiomeSurfaceBuilderData readBiomeSurfaceBuilderData(ByteBuf buffer, BedrockCodecHelper helper) {
        final BiomeSurfaceBuilderData data = super.readBiomeSurfaceBuilderData(buffer, helper);
        data.setNoiseGradientSurface(helper.readOptional(buffer, null, this::readBiomeNoiseGradientSurfaceData));
        return data;
    }

    protected void writeBiomeNoiseGradientSurfaceData(ByteBuf buffer, BedrockCodecHelper helper, BiomeNoiseGradientSurfaceData data) {
        helper.writeArray(buffer, data.getNonreplaceableBlocks(), this::writeBlock);
        helper.writeArray(buffer, data.getGradientBlocks(), this::writeSerializedNoiseBlockSpecifier);
        this.writeNoiseDescriptor(buffer, helper, data.getNoise());
    }

    protected BiomeNoiseGradientSurfaceData readBiomeNoiseGradientSurfaceData(ByteBuf buffer, BedrockCodecHelper helper) {
        final BiomeNoiseGradientSurfaceData data = new BiomeNoiseGradientSurfaceData();
        helper.readArray(buffer, data.getNonreplaceableBlocks(), this::readBlock);
        helper.readArray(buffer, data.getGradientBlocks(), this::readSerializedNoiseBlockSpecifier);
        data.setNoise(this.readNoiseDescriptor(buffer, helper));
        return data;
    }

    protected void writeSerializedNoiseBlockSpecifier(ByteBuf buffer, BedrockCodecHelper helper, SerializedNoiseBlockSpecifier specifier) {
        this.writeBlock(buffer, helper, specifier.getBlock());
    }

    protected SerializedNoiseBlockSpecifier readSerializedNoiseBlockSpecifier(ByteBuf buffer, BedrockCodecHelper helper) {
        final SerializedNoiseBlockSpecifier serializedNoiseBlockSpecifier = new SerializedNoiseBlockSpecifier();
        serializedNoiseBlockSpecifier.setBlock(this.readBlock(buffer, helper));
        return serializedNoiseBlockSpecifier;
    }

    protected void writeNoiseDescriptor(ByteBuf buffer, BedrockCodecHelper helper, NoiseDescriptor descriptor) {
        helper.writeString(buffer, descriptor.getName());
        buffer.writeIntLE(descriptor.getFirstOctave());
        helper.writeArray(buffer, descriptor.getAmplitudes(), ByteBuf::writeFloatLE);
    }

    protected NoiseDescriptor readNoiseDescriptor(ByteBuf buffer, BedrockCodecHelper helper) {
        final NoiseDescriptor noiseDescriptor = new NoiseDescriptor();
        noiseDescriptor.setName(helper.readString(buffer));
        noiseDescriptor.setFirstOctave(buffer.readIntLE());
        helper.readArray(buffer, noiseDescriptor.getAmplitudes(), ByteBuf::readFloatLE, 100);
        return noiseDescriptor;
    }
}
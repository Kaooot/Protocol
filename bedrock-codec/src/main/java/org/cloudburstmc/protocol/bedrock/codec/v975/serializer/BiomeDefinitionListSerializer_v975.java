package org.cloudburstmc.protocol.bedrock.codec.v975.serializer;

import io.netty.buffer.ByteBuf;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v924.serializer.BiomeDefinitionListSerializer_v924;
import org.cloudburstmc.protocol.bedrock.data.biome.*;
import org.cloudburstmc.protocol.bedrock.data.definitions.BlockDefinition;

import java.util.List;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BiomeDefinitionListSerializer_v975 extends BiomeDefinitionListSerializer_v924 {
    public static final BiomeDefinitionListSerializer_v975 INSTANCE = new BiomeDefinitionListSerializer_v975();

    @Override
    protected void writeDefinitionChunkGen(ByteBuf buffer, BedrockCodecHelper helper, BiomeDefinitionChunkGenData definitionChunkGen) {
        helper.writeOptionalNull(buffer, definitionChunkGen.getClimate(), this::writeClimate);
        helper.writeOptionalNull(buffer, definitionChunkGen.getConsolidatedFeatures(), this::writeConsolidatedFeatures);
        helper.writeOptionalNull(buffer, definitionChunkGen.getMountainParams(), this::writeMountainParamsData);
        helper.writeOptionalNull(buffer, definitionChunkGen.getSurfaceMaterialAdjustment(), this::writeSurfaceMaterialAdjustment);
        helper.writeOptionalNull(buffer, definitionChunkGen.getOverworldGenRules(), this::writeOverworldGenRules);
        helper.writeOptionalNull(buffer, definitionChunkGen.getMultinoiseGenRules(), this::writeMultinoiseGenRules);
        helper.writeOptionalNull(buffer, definitionChunkGen.getLegacyWorldGenRules(), this::writeLegacyWorldGenRules);
        helper.writeOptionalNull(buffer, definitionChunkGen.getReplacementBiomes(), this::writeBiomeReplacementsData);
        helper.writeOptionalNull(buffer, definitionChunkGen.getVillageType(), (byteBuf, villageType) -> byteBuf.writeByte(villageType.ordinal()));
        helper.writeOptionalNull(buffer, definitionChunkGen.getSurfaceBuilderData(), this::writeBiomeSurfaceBuilderData);
        helper.writeOptionalNull(buffer, definitionChunkGen.getSubSurfaceBuilderData(), this::writeBiomeSurfaceBuilderData);
    }

    @Override
    protected BiomeDefinitionChunkGenData readDefinitionChunkGen(ByteBuf buffer, BedrockCodecHelper helper) {
        final BiomeClimateData climate = helper.readOptional(buffer, null, this::readClimate);
        final List<BiomeConsolidatedFeatureData> consolidatedFeatures = helper.readOptional(buffer, null, this::readConsolidatedFeatures);
        final BiomeMountainParamsData mountainParams = helper.readOptional(buffer, null, this::readMountainParamsData);
        final BiomeSurfaceMaterialAdjustmentData surfaceMaterialAdjustment = helper.readOptional(buffer, null, this::readSurfaceMaterialAdjustment);
        final BiomeOverworldGenRulesData overworldGenRules = helper.readOptional(buffer, null, this::readOverworldGenRules);
        final BiomeMultinoiseGenRulesData multinoiseGenRules = helper.readOptional(buffer, null, this::readMultinoiseGenRules);
        final BiomeLegacyWorldGenRulesData legacyWorldGenRules = helper.readOptional(buffer, null, this::readLegacyWorldGenRules);
        final List<BiomeReplacementData> replacementBiomes = helper.readOptional(buffer, null, this::readBiomeReplacementsData);
        final VillageType villageType = helper.readOptional(buffer, null, (byteBuf, codecHelper) -> VillageType.from(byteBuf.readUnsignedByte()));
        final BiomeSurfaceBuilderData surfaceBuilderData = helper.readOptional(buffer, null, this::readBiomeSurfaceBuilderData);
        final BiomeSurfaceBuilderData subSurfaceBuilderData = helper.readOptional(buffer, null, this::readBiomeSurfaceBuilderData);

        return new BiomeDefinitionChunkGenData(climate, consolidatedFeatures,
                mountainParams, surfaceMaterialAdjustment,
                surfaceBuilderData, overworldGenRules, multinoiseGenRules,
                legacyWorldGenRules, null, replacementBiomes, villageType, subSurfaceBuilderData);
    }

    protected void writeBiomeReplacementsData(ByteBuf buffer, BedrockCodecHelper helper, List<BiomeReplacementData> data) {
        helper.writeArray(buffer, data, this::writeBiomeReplacement);
    }

    protected List<BiomeReplacementData> readBiomeReplacementsData(ByteBuf buffer, BedrockCodecHelper helper) {
        final List<BiomeReplacementData> biomeReplacements = new ObjectArrayList<>();
        helper.readArray(buffer, biomeReplacements, this::readBiomeReplacement);
        return biomeReplacements;
    }

    @Override
    protected void writeBiomeSurfaceBuilderData(ByteBuf buffer, BedrockCodecHelper helper, BiomeSurfaceBuilderData data) {
        helper.writeOptionalNull(buffer, data.getSurfaceMaterial(), this::writeSurfaceMaterial);
        buffer.writeBoolean(data.isHasDefaultOverworldSurface());
        buffer.writeBoolean(data.isHasSwampSurface());
        buffer.writeBoolean(data.isHasFrozenOceanSurface());
        buffer.writeBoolean(data.isHasTheEndSurface());
        helper.writeOptionalNull(buffer, data.getMesaSurface(), this::writeMesaSurface);
        helper.writeOptionalNull(buffer, data.getCappedSurface(), this::writeCappedSurface);
        helper.writeOptionalNull(buffer, data.getNoiseGradientSurface(), this::writeBiomeNoiseGradientSurfaceData);
    }

    @Override
    protected BiomeSurfaceBuilderData readBiomeSurfaceBuilderData(ByteBuf buffer, BedrockCodecHelper helper) {
        BiomeSurfaceMaterialData surfaceMaterial = helper.readOptional(buffer, null, this::readSurfaceMaterial);
        boolean hasDefaultOverworldSurface = buffer.readBoolean();
        boolean hasSwampSurface = buffer.readBoolean();
        boolean hasFrozenOceanSurface = buffer.readBoolean();
        boolean hasTheEndSurface = buffer.readBoolean();
        BiomeMesaSurfaceData mesaSurface = helper.readOptional(buffer, null, this::readMesaSurface);
        BiomeCappedSurfaceData cappedSurface = helper.readOptional(buffer, null, this::readCappedSurface);
        BiomeNoiseGradientSurfaceData noiseGradientSurface = helper.readOptional(buffer, null, this::readBiomeNoiseGradientSurfaceData);
        return new BiomeSurfaceBuilderData(
                surfaceMaterial,
                hasDefaultOverworldSurface,
                hasSwampSurface,
                hasFrozenOceanSurface,
                hasTheEndSurface,
                mesaSurface,
                cappedSurface,
                noiseGradientSurface
        );
    }

    protected void writeBiomeNoiseGradientSurfaceData(ByteBuf buffer, BedrockCodecHelper helper, BiomeNoiseGradientSurfaceData data) {
        helper.writeArray(buffer, data.getNonReplaceableBlocks(), this::writeBlock);
        helper.writeArray(buffer, data.getGradientBlocks(), this::writeSerializedNoiseBlockSpecifier);
        this.writeNoiseDescriptor(buffer, helper, data.getNoise());
    }

    protected BiomeNoiseGradientSurfaceData readBiomeNoiseGradientSurfaceData(ByteBuf buffer, BedrockCodecHelper helper) {
        final List<BlockDefinition> nonReplaceableBlocks = new ObjectArrayList<>();
        final List<SerializedNoiseBlockSpecifier> gradientBlocks = new ObjectArrayList<>();
        helper.readArray(buffer, nonReplaceableBlocks, this::readBlock);
        helper.readArray(buffer, gradientBlocks, this::readSerializedNoiseBlockSpecifier);
        final NoiseDescriptor noise = this.readNoiseDescriptor(buffer, helper);
        return new BiomeNoiseGradientSurfaceData(
                nonReplaceableBlocks,
                gradientBlocks,
                noise
        );
    }

    protected void writeSerializedNoiseBlockSpecifier(ByteBuf buffer, BedrockCodecHelper helper, SerializedNoiseBlockSpecifier specifier) {
        this.writeBlock(buffer, helper, specifier.getBlock());
    }

    protected SerializedNoiseBlockSpecifier readSerializedNoiseBlockSpecifier(ByteBuf buffer, BedrockCodecHelper helper) {
        final BlockDefinition block = this.readBlock(buffer, helper);
        return new SerializedNoiseBlockSpecifier(null, 0f, null, block);
    }

    protected void writeNoiseDescriptor(ByteBuf buffer, BedrockCodecHelper helper, NoiseDescriptor descriptor) {
        helper.writeString(buffer, descriptor.getName());
        buffer.writeIntLE(descriptor.getFirstOctave());
        helper.writeArray(buffer, descriptor.getAmplitudes(), ByteBuf::writeFloatLE);
    }

    protected NoiseDescriptor readNoiseDescriptor(ByteBuf buffer, BedrockCodecHelper helper) {
        final String name = helper.readString(buffer);
        final int firstOctave = buffer.readIntLE();
        final List<Float> amplitudes = new ObjectArrayList<>();
        helper.readArray(buffer, amplitudes, ByteBuf::readFloatLE, 100);
        return new NoiseDescriptor(name, firstOctave, amplitudes);
    }
}
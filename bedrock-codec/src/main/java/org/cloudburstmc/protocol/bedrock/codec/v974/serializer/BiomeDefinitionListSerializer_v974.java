package org.cloudburstmc.protocol.bedrock.codec.v974.serializer;

import io.netty.buffer.ByteBuf;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v924.serializer.BiomeDefinitionListSerializer_v924;
import org.cloudburstmc.protocol.bedrock.data.biome.*;
import org.cloudburstmc.protocol.bedrock.data.definitions.BlockDefinition;
import org.cloudburstmc.protocol.common.util.SequencedHashSet;
import org.cloudburstmc.protocol.common.util.VarInts;

import java.util.List;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BiomeDefinitionListSerializer_v974 extends BiomeDefinitionListSerializer_v924 {
    public static final BiomeDefinitionListSerializer_v974 INSTANCE = new BiomeDefinitionListSerializer_v974();

    @Override
    protected void writeDefinitionChunkGen(ByteBuf buffer, BedrockCodecHelper helper, BiomeDefinitionChunkGenData definitionChunkGen, SequencedHashSet<String> strings) {
        helper.writeOptionalNull(buffer, definitionChunkGen.getClimate(), this::writeClimate);
        helper.writeOptionalNull(buffer, definitionChunkGen.getConsolidatedFeatures(),
                (buf, aHelper, consolidatedFeatures) -> this.writeConsolidatedFeatures(buf, aHelper, consolidatedFeatures, strings));
        helper.writeOptionalNull(buffer, definitionChunkGen.getMountainParams(), this::writeMountainParamsData);
        helper.writeOptionalNull(buffer, definitionChunkGen.getSurfaceMaterialAdjustment(),
                (buf, aHelper, surfaceMaterialAdjustment) -> this.writeSurfaceMaterialAdjustment(buf, aHelper, surfaceMaterialAdjustment, strings));
        helper.writeOptionalNull(buffer, definitionChunkGen.getOverworldGenRules(),
                (buf, aHelper, overworldGenRules) -> this.writeOverworldGenRules(buf, aHelper, overworldGenRules, strings));
        helper.writeOptionalNull(buffer, definitionChunkGen.getMultinoiseGenRules(), this::writeMultinoiseGenRules);
        helper.writeOptionalNull(buffer, definitionChunkGen.getLegacyWorldGenRules(),
                (buf, aHelper, legacyWorldGenRules) -> this.writeLegacyWorldGenRules(buf, aHelper, legacyWorldGenRules, strings));
        helper.writeOptionalNull(buffer, definitionChunkGen.getReplacementBiomes(), (buf, codecHelper, data) -> this.writeBiomeReplacementsData(buf, codecHelper, data, strings));
        helper.writeOptionalNull(buffer, definitionChunkGen.getVillageType(), (byteBuf, villageType) -> byteBuf.writeByte(villageType.ordinal()));
        helper.writeOptionalNull(buffer, definitionChunkGen.getSurfaceBuilderData(), this::writeBiomeSurfaceBuilderData);
        helper.writeOptionalNull(buffer, definitionChunkGen.getSubSurfaceBuilderData(), this::writeBiomeSurfaceBuilderData);
    }

    @Override
    protected BiomeDefinitionChunkGenData readDefinitionChunkGen(ByteBuf buffer, BedrockCodecHelper helper, List<String> strings) {
        BiomeClimateData climate = helper.readOptional(buffer, null, this::readClimate);
        List<BiomeConsolidatedFeatureData> consolidatedFeatures = helper.readOptional(buffer, null,
                (buf, aHelper) -> this.readConsolidatedFeatures(buf, aHelper, strings));
        BiomeMountainParamsData mountainParams = helper.readOptional(buffer, null, this::readMountainParamsData);
        BiomeSurfaceMaterialAdjustmentData surfaceMaterialAdjustment = helper.readOptional(buffer, null,
                (buf, aHelper) -> this.readSurfaceMaterialAdjustment(buf, aHelper, strings));
        BiomeOverworldGenRulesData overworldGenRules = helper.readOptional(buffer, null,
                (buf, aHelper) -> this.readOverworldGenRules(buf, aHelper, strings));
        BiomeMultinoiseGenRulesData multinoiseGenRules = helper.readOptional(buffer, null, this::readMultinoiseGenRules);
        BiomeLegacyWorldGenRulesData legacyWorldGenRules = helper.readOptional(buffer, null,
                (buf, aHelper) -> this.readLegacyWorldGenRules(buf, aHelper, strings));
        List<BiomeReplacementData> replacementBiomes = helper.readOptional(buffer, null, (buf, codecHelper) -> this.readBiomeReplacementsData(buf, codecHelper, strings));
        VillageType villageType = helper.readOptional(buffer, null, (byteBuf, codecHelper) -> VillageType.from(byteBuf.readUnsignedByte()));
        BiomeSurfaceBuilderData surfaceBuilderData = helper.readOptional(buffer, null, this::readBiomeSurfaceBuilderData);
        BiomeSurfaceBuilderData subSurfaceBuilderData = helper.readOptional(buffer, null, this::readBiomeSurfaceBuilderData);

        return new BiomeDefinitionChunkGenData(climate, consolidatedFeatures,
                mountainParams, surfaceMaterialAdjustment,
                surfaceBuilderData, overworldGenRules, multinoiseGenRules,
                legacyWorldGenRules, null, replacementBiomes, villageType, subSurfaceBuilderData);
    }

    protected void writeBiomeReplacementsData(ByteBuf buffer, BedrockCodecHelper helper, List<BiomeReplacementData> data, SequencedHashSet<String> strings) {
        helper.writeArray(buffer, data, (buf, codecHelper, value) -> this.writeBiomeReplacement(buf, codecHelper, value, strings));
    }

    protected List<BiomeReplacementData> readBiomeReplacementsData(ByteBuf buffer, BedrockCodecHelper helper, List<String> strings) {
        final List<BiomeReplacementData> biomeReplacements = new ObjectArrayList<>();
        helper.readArray(buffer, biomeReplacements, (buf, codecHelper) -> this.readBiomeReplacement(buf, codecHelper, strings));
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
        helper.writeArray(buffer, data.getGradientBlocks(), this::writeBlock);
        helper.writeString(buffer, data.getNoiseSeedString());
        VarInts.writeInt(buffer, data.getFirstOctave());
        helper.writeArray(buffer, data.getAmplitudes(), ByteBuf::writeFloatLE);
    }

    protected BiomeNoiseGradientSurfaceData readBiomeNoiseGradientSurfaceData(ByteBuf buffer, BedrockCodecHelper helper) {
        final List<BlockDefinition> nonReplaceableBlocks = new ObjectArrayList<>();
        final List<BlockDefinition> gradientBlocks = new ObjectArrayList<>();
        helper.readArray(buffer, nonReplaceableBlocks, this::readBlock);
        helper.readArray(buffer, gradientBlocks, this::readBlock);
        final String noiseSeedString = helper.readString(buffer);
        final int firstOctave = VarInts.readInt(buffer);
        final List<Float> amplitudes = new ObjectArrayList<>();
        helper.readArray(buffer, amplitudes, ByteBuf::readFloatLE);
        return new BiomeNoiseGradientSurfaceData(
                nonReplaceableBlocks,
                gradientBlocks,
                noiseSeedString,
                firstOctave,
                amplitudes
        );
    }
}
package org.cloudburstmc.protocol.bedrock.codec.v800.serializer;

import io.netty.buffer.ByteBuf;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.biome.ExpressionOp;
import org.cloudburstmc.protocol.bedrock.data.biome.*;
import org.cloudburstmc.protocol.bedrock.data.definitions.BlockDefinition;
import org.cloudburstmc.protocol.bedrock.data.structure.CoordinateEvaluationOrder;
import org.cloudburstmc.protocol.bedrock.data.structure.RandomDistributionType;
import org.cloudburstmc.protocol.bedrock.packet.BiomeDefinitionListPacket;
import org.cloudburstmc.protocol.common.util.DefinitionUtils;
import org.cloudburstmc.protocol.common.util.TriConsumer;
import org.cloudburstmc.protocol.common.util.VarInts;

import java.awt.*;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

@SuppressWarnings("deprecation")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BiomeDefinitionListSerializer_v800 implements BedrockPacketSerializer<BiomeDefinitionListPacket> {

    public static final BiomeDefinitionListSerializer_v800 INSTANCE = new BiomeDefinitionListSerializer_v800();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, BiomeDefinitionListPacket packet) {
        VarInts.writeUnsignedInt(buffer, packet.getMapOfBiomeNamesToData().size());
        for (Int2ObjectMap.Entry<BiomeDefinitionData> entry : packet.getMapOfBiomeNamesToData().int2ObjectEntrySet()) {
            buffer.writeShortLE(entry.getIntKey());
            this.writeBiomeDefinitionData(buffer, helper, entry.getValue());
        }

        helper.writeArray(buffer, packet.getStringList().getStrings(), helper::writeString);
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, BiomeDefinitionListPacket packet) {
        final int length = VarInts.readUnsignedInt(buffer);
        for (int i = 0; i < length; i++) {
            final int index = buffer.readUnsignedShortLE();
            final BiomeDefinitionData data = this.readBiomeDefinitionData(buffer, helper);
            packet.getMapOfBiomeNamesToData().put(index, data);
        }

        final int stringListLength = VarInts.readUnsignedInt(buffer);
        for (int i = 0; i < stringListLength; i++) {
            packet.getStringList().getStrings().add(helper.readString(buffer));
        }
    }

    protected void writeDefinitionId(ByteBuf buffer, BedrockCodecHelper helper, BiomeDefinitionData definition) {
        helper.writeOptional(buffer, Objects::nonNull, definition.getId(), ByteBuf::writeShortLE);
    }

    protected void writeBiomeDefinitionData(ByteBuf buffer, BedrockCodecHelper helper, BiomeDefinitionData definition) {
        this.writeDefinitionId(buffer, helper, definition);
        buffer.writeFloatLE(definition.getTemperature());
        buffer.writeFloatLE(definition.getDownfall());
        buffer.writeFloatLE(definition.getRedSporeDensity());
        buffer.writeFloatLE(definition.getBlueSporeDensity());
        buffer.writeFloatLE(definition.getAshDensity());
        buffer.writeFloatLE(definition.getWhiteAshDensity());
        buffer.writeFloatLE(definition.getDepth());
        buffer.writeFloatLE(definition.getScale());
        buffer.writeIntLE(definition.getMapWaterColorArgb().getRGB());
        buffer.writeBoolean(definition.isRain());
        helper.writeOptionalNull(buffer, definition.getTags(), this::writeBiomeTagsData);
        helper.writeOptionalNull(buffer, definition.getChunkGenData(), this::writeBiomeDefinitionChunkGenData);
    }

    protected void writeBiomeTagsData(ByteBuf buffer, BedrockCodecHelper helper, BiomeTagsData data) {
        VarInts.writeUnsignedInt(buffer, data.getTags().size());
        for (Integer tag : data.getTags()) {
            buffer.writeShortLE(tag);
        }
    }

    protected BiomeTagsData readBiomeTagsData(ByteBuf buffer, BedrockCodecHelper helper) {
        final BiomeTagsData data = new BiomeTagsData();
        final int length = VarInts.readUnsignedInt(buffer);
        for (int i = 0; i < length; i++) {
            data.getTags().add(buffer.readUnsignedShortLE());
        }
        return data;
    }

    protected Integer readDefinitionId(ByteBuf buffer, BedrockCodecHelper helper) {
        return helper.readOptional(buffer, null, (buf, codecHelper) -> buf.readUnsignedShortLE());
    }

    protected BiomeDefinitionData readBiomeDefinitionData(ByteBuf buffer, BedrockCodecHelper helper) {
        final BiomeDefinitionData data = new BiomeDefinitionData();
        data.setId(this.readDefinitionId(buffer, helper));
        data.setTemperature(buffer.readFloatLE());
        data.setDownfall(buffer.readFloatLE());
        data.setRedSporeDensity(buffer.readFloatLE());
        data.setBlueSporeDensity(buffer.readFloatLE());
        data.setAshDensity(buffer.readFloatLE());
        data.setWhiteAshDensity(buffer.readFloatLE());
        data.setDepth(buffer.readFloatLE());
        data.setScale(buffer.readFloatLE());
        data.setMapWaterColorArgb(new Color(buffer.readIntLE(), true));
        data.setRain(buffer.readBoolean());
        data.setTags(helper.readOptional(buffer, null, this::readBiomeTagsData));
        data.setChunkGenData(helper.readOptional(buffer, null, this::readBiomeDefinitionChunkGenData));
        return data;
    }

    protected void writeBiomeDefinitionChunkGenData(ByteBuf buffer, BedrockCodecHelper helper, BiomeDefinitionChunkGenData definitionChunkGen) {
        helper.writeOptionalNull(buffer, definitionChunkGen.getClimate(), this::writeClimate);
        helper.writeOptionalNull(buffer, definitionChunkGen.getConsolidatedFeatures(), this::writeBiomeConsolidatedFeaturesData);
        helper.writeOptionalNull(buffer, definitionChunkGen.getMountainParams(), this::writeBiomeMountainParamsData);
        helper.writeOptionalNull(buffer, definitionChunkGen.getSurfaceMaterialAdjustments(), this::writeBiomeSurfaceMaterialAdjustmentData);
        this.writeBiomeSurfaceBuilderData(buffer, helper, definitionChunkGen.getSurfaceBuilderData());
        helper.writeOptionalNull(buffer, definitionChunkGen.getOverworldGenRules(), this::writeBiomeOverworldGenRulesData);
        helper.writeOptionalNull(buffer, definitionChunkGen.getMultinoiseGenRules(), this::writeBiomeMultinoiseGenRulesData);
        helper.writeOptionalNull(buffer, definitionChunkGen.getLegacyWorldGenRules(), this::writeBiomeLegacyWorldGenRulesData);
    }

    protected BiomeDefinitionChunkGenData readBiomeDefinitionChunkGenData(ByteBuf buffer, BedrockCodecHelper helper) {
        final BiomeDefinitionChunkGenData data = new BiomeDefinitionChunkGenData();
        data.setClimate(helper.readOptional(buffer, null, this::readClimate));
        data.setConsolidatedFeatures(helper.readOptional(buffer, null, this::readBiomeConsolidatedFeaturesData));
        data.setMountainParams(helper.readOptional(buffer, null, this::readBiomeMountainParamsData));
        data.setSurfaceMaterialAdjustments(helper.readOptional(buffer, null, this::readBiomeSurfaceMaterialAdjustmentData));
        data.setSurfaceBuilderData(this.readBiomeSurfaceBuilderData(buffer, helper));
        data.setOverworldGenRules(helper.readOptional(buffer, null, this::readBiomeOverworldGenRulesData));
        data.setMultinoiseGenRules(helper.readOptional(buffer, null, this::readBiomeMultinoiseGenRulesData));
        data.setLegacyWorldGenRules(helper.readOptional(buffer, null, this::readBiomeLegacyWorldGenRulesData));
        return data;
    }

    protected void writeClimate(ByteBuf buffer, BedrockCodecHelper helper, BiomeClimateData climate) {
        buffer.writeFloatLE(climate.getTemperature());
        buffer.writeFloatLE(climate.getDownfall());
        buffer.writeFloatLE(climate.getRedSporeDensity());
        buffer.writeFloatLE(climate.getBlueSporeDensity());
        buffer.writeFloatLE(climate.getAshDensity());
        buffer.writeFloatLE(climate.getWhiteAshDensity());
        buffer.writeFloatLE(climate.getSnowAccumulationMin());
        buffer.writeFloatLE(climate.getSnowAccumulationMax());
    }

    protected BiomeClimateData readClimate(ByteBuf buffer, BedrockCodecHelper helper) {
        final BiomeClimateData data = new BiomeClimateData();
        data.setTemperature(buffer.readFloatLE());
        data.setDownfall(buffer.readFloatLE());
        data.setRedSporeDensity(buffer.readFloatLE());
        data.setBlueSporeDensity(buffer.readFloatLE());
        data.setAshDensity(buffer.readFloatLE());
        data.setWhiteAshDensity(buffer.readFloatLE());
        data.setSnowAccumulationMin(buffer.readFloatLE());
        data.setSnowAccumulationMax(buffer.readFloatLE());
        return data;
    }

    protected void writeBiomeConsolidatedFeaturesData(ByteBuf buffer, BedrockCodecHelper helper, BiomeConsolidatedFeaturesData data) {
        helper.writeArray(buffer, data.getFeatures(), this::writeBiomeConsolidatedFeatureData);
    }

    protected BiomeConsolidatedFeaturesData readBiomeConsolidatedFeaturesData(ByteBuf buffer, BedrockCodecHelper helper) {
        final BiomeConsolidatedFeaturesData data = new BiomeConsolidatedFeaturesData();
        helper.readArray(buffer, data.getFeatures(), this::readBiomeConsolidatedFeatureData);
        return data;
    }

    protected void writeBiomeConsolidatedFeatureData(ByteBuf buffer, BedrockCodecHelper helper, BiomeConsolidatedFeatureData data) {
        this.writeBiomeScatterParamData(buffer, helper, data.getScatter());
        buffer.writeShortLE(data.getFeature());
        buffer.writeShortLE(data.getIdentifier());
        buffer.writeShortLE(data.getPass());
        buffer.writeBoolean(data.isCanUseInternalFeature());
    }

    protected BiomeConsolidatedFeatureData readBiomeConsolidatedFeatureData(ByteBuf buffer, BedrockCodecHelper helper) {
        final BiomeScatterParamData scatter = this.readBiomeScatterParamData(buffer, helper);
        final short feature = buffer.readShortLE();
        final short identifier = buffer.readShortLE();
        final short pass = buffer.readShortLE();
        final boolean canUseInternalFeature = buffer.readBoolean();
        return new BiomeConsolidatedFeatureData(scatter, feature, identifier, pass, canUseInternalFeature);
    }

    protected void writeBiomeScatterParamData(ByteBuf buffer, BedrockCodecHelper helper, BiomeScatterParamData data) {
        helper.writeArray(buffer, data.getCoordinates(), this::writeBiomeCoordinateData);
        VarInts.writeInt(buffer, data.getEvalOrder().ordinal());
        this.writeExpressionOp(buffer, data.getChancePercentType());
        buffer.writeShortLE(data.getChancePercent());
        buffer.writeIntLE(data.getChanceNumerator());
        buffer.writeIntLE(data.getChanceDenominator());
        this.writeExpressionOp(buffer, data.getIterationsType());
        buffer.writeShortLE(data.getIterations());
    }

    protected BiomeScatterParamData readBiomeScatterParamData(ByteBuf buffer, BedrockCodecHelper helper) {
        final BiomeScatterParamData data = new BiomeScatterParamData();
        helper.readArray(buffer, data.getCoordinates(), this::readBiomeCoordinateData);
        data.setEvalOrder(CoordinateEvaluationOrder.from(VarInts.readInt(buffer)));
        data.setChancePercentType(this.readExpressionOp(buffer));
        data.setChancePercent(buffer.readShortLE());
        data.setChanceNumerator(buffer.readIntLE());
        data.setChanceDenominator(buffer.readIntLE());
        data.setIterationsType(this.readExpressionOp(buffer));
        data.setIterations(buffer.readShortLE());
        return data;
    }

    protected void writeBiomeCoordinateData(ByteBuf buffer, BedrockCodecHelper helper, BiomeCoordinateData data) {
        this.writeExpressionOp(buffer, data.getMinValueType());
        buffer.writeShortLE(data.getMinValue());
        this.writeExpressionOp(buffer, data.getMaxValueType());
        buffer.writeShortLE(data.getMaxValue());
        buffer.writeIntLE(data.getGridOffset());
        buffer.writeIntLE(data.getGridStepSize());
        VarInts.writeInt(buffer, data.getDistribution().ordinal());
    }

    protected BiomeCoordinateData readBiomeCoordinateData(ByteBuf buffer, BedrockCodecHelper helper) {
        final BiomeCoordinateData data = new BiomeCoordinateData();
        data.setMinValueType(this.readExpressionOp(buffer));
        data.setMinValue(buffer.readShortLE());
        data.setMaxValueType(this.readExpressionOp(buffer));
        data.setMaxValue(buffer.readShortLE());
        data.setGridOffset(buffer.readIntLE());
        data.setGridStepSize(buffer.readIntLE());
        data.setDistribution(RandomDistributionType.from(VarInts.readInt(buffer)));
        return data;
    }

    protected void writeBiomeMountainParamsData(ByteBuf buffer, BedrockCodecHelper helper, BiomeMountainParamsData mountainParams) {
        this.writeBlock(buffer, helper, mountainParams.getSteepBlock());
        buffer.writeBoolean(mountainParams.isNorthSlopes());
        buffer.writeBoolean(mountainParams.isSouthSlopes());
        buffer.writeBoolean(mountainParams.isWestSlopes());
        buffer.writeBoolean(mountainParams.isEastSlopes());
        buffer.writeBoolean(mountainParams.isTopSlideEnabled());
    }

    protected BiomeMountainParamsData readBiomeMountainParamsData(ByteBuf buffer, BedrockCodecHelper helper) {
        final BiomeMountainParamsData data = new BiomeMountainParamsData();
        data.setSteepBlock(this.readBlock(buffer, helper));
        data.setNorthSlopes(buffer.readBoolean());
        data.setSouthSlopes(buffer.readBoolean());
        data.setWestSlopes(buffer.readBoolean());
        data.setEastSlopes(buffer.readBoolean());
        data.setTopSlideEnabled(buffer.readBoolean());
        return data;
    }

    protected void writeBiomeSurfaceMaterialAdjustmentData(ByteBuf buffer, BedrockCodecHelper helper,
                                                           BiomeSurfaceMaterialAdjustmentData data) {
        helper.writeArray(buffer, data.getAdjustments(), this::writeBiomeElementData);
    }

    protected BiomeSurfaceMaterialAdjustmentData readBiomeSurfaceMaterialAdjustmentData(ByteBuf buffer, BedrockCodecHelper helper) {
        final BiomeSurfaceMaterialAdjustmentData data = new BiomeSurfaceMaterialAdjustmentData();
        helper.readArray(buffer, data.getAdjustments(), this::readBiomeElementData);
        return data;
    }

    protected void writeBiomeElementData(ByteBuf buffer, BedrockCodecHelper helper, BiomeElementData data) {
        buffer.writeFloatLE(data.getNoiseFreqScale());
        buffer.writeFloatLE(data.getNoiseLowerBound());
        buffer.writeFloatLE(data.getNoiseUpperBound());
        this.writeExpressionOp(buffer, data.getHeightMinType());
        buffer.writeShortLE(data.getHeightMin());
        this.writeExpressionOp(buffer, data.getHeightMaxType());
        buffer.writeShortLE(data.getHeightMax());
        this.writeBiomeSurfaceMaterialData(buffer, helper, data.getAdjustedMaterials());
    }

    protected BiomeElementData readBiomeElementData(ByteBuf buffer, BedrockCodecHelper helper) {
        final BiomeElementData data = new BiomeElementData();
        data.setNoiseFreqScale(buffer.readFloatLE());
        data.setNoiseLowerBound(buffer.readFloatLE());
        data.setNoiseUpperBound(buffer.readFloatLE());
        data.setHeightMinType(this.readExpressionOp(buffer));
        data.setHeightMin(buffer.readShortLE());
        data.setHeightMaxType(this.readExpressionOp(buffer));
        data.setHeightMax(buffer.readShortLE());
        data.setAdjustedMaterials(this.readBiomeSurfaceMaterialData(buffer, helper));
        return data;
    }

    protected void writeBiomeSurfaceMaterialData(ByteBuf buffer, BedrockCodecHelper helper, BiomeSurfaceMaterialData surfaceMaterial) {
        this.writeBlock(buffer, helper, surfaceMaterial.getTopBlock());
        this.writeBlock(buffer, helper, surfaceMaterial.getMidBlock());
        this.writeBlock(buffer, helper, surfaceMaterial.getSeaFloorBlock());
        this.writeBlock(buffer, helper, surfaceMaterial.getFoundationBlock());
        this.writeBlock(buffer, helper, surfaceMaterial.getSeaBlock());
        buffer.writeIntLE(surfaceMaterial.getSeaFloorDepth());
    }

    protected BiomeSurfaceMaterialData readBiomeSurfaceMaterialData(ByteBuf buffer, BedrockCodecHelper helper) {
        final BiomeSurfaceMaterialData data = new BiomeSurfaceMaterialData();
        data.setTopBlock(this.readBlock(buffer, helper));
        data.setMidBlock(this.readBlock(buffer, helper));
        data.setSeaFloorBlock(this.readBlock(buffer, helper));
        data.setFoundationBlock(this.readBlock(buffer, helper));
        data.setSeaBlock(this.readBlock(buffer, helper));
        data.setSeaFloorDepth(buffer.readIntLE());
        return data;
    }

    protected void writeBiomeMesaSurfaceData(ByteBuf buffer, BedrockCodecHelper helper, BiomeMesaSurfaceData mesaSurface) {
        this.writeBlock(buffer, helper, mesaSurface.getClayMaterial());
        this.writeBlock(buffer, helper, mesaSurface.getHardClayMaterial());
        buffer.writeBoolean(mesaSurface.isBrycePillars());
        buffer.writeBoolean(mesaSurface.isHasForest());
    }

    protected BiomeMesaSurfaceData readBiomeMesaSurfaceData(ByteBuf buffer, BedrockCodecHelper helper) {
        BlockDefinition clayMaterial = this.readBlock(buffer, helper);
        BlockDefinition hardClayMaterial = this.readBlock(buffer, helper);
        boolean brycePillars = buffer.readBoolean();
        boolean hasForest = buffer.readBoolean();

        return new BiomeMesaSurfaceData(clayMaterial, hardClayMaterial, brycePillars, hasForest);
    }

    protected void writeBiomeCappedSurfaceData(ByteBuf buffer, BedrockCodecHelper helper, BiomeCappedSurfaceData cappedSurface) {
        helper.writeArray(buffer, cappedSurface.getFloorBlocks(), this::writeBlock);
        helper.writeArray(buffer, cappedSurface.getCeilingBlocks(), this::writeBlock);
        helper.writeOptionalNull(buffer, cappedSurface.getSeaBlock(), this::writeBlock);
        helper.writeOptionalNull(buffer, cappedSurface.getFoundationBlock(), this::writeBlock);
        helper.writeOptionalNull(buffer, cappedSurface.getBeachBlock(), this::writeBlock);
    }

    protected BiomeCappedSurfaceData readBiomeCappedSurfaceData(ByteBuf buffer, BedrockCodecHelper helper) {
        final BiomeCappedSurfaceData data = new BiomeCappedSurfaceData();
        helper.readArray(buffer, data.getFloorBlocks(), this::readBlock);
        helper.readArray(buffer, data.getCeilingBlocks(), this::readBlock);
        data.setSeaBlock(helper.readOptional(buffer, null, this::readBlock));
        data.setFoundationBlock(helper.readOptional(buffer, null, this::readBlock));
        data.setBeachBlock(helper.readOptional(buffer, null, this::readBlock));
        return data;
    }

    protected void writeBiomeOverworldGenRulesData(ByteBuf buffer, BedrockCodecHelper helper, BiomeOverworldGenRulesData overworldGenRules) {
        final BiConsumer<ByteBuf, BiomeWeightedData> writeWeight = this::writeBiomeWeightedData;
        helper.writeArray(buffer, overworldGenRules.getHillsTransformations(), writeWeight);
        helper.writeArray(buffer, overworldGenRules.getMutateTransformations(), writeWeight);
        helper.writeArray(buffer, overworldGenRules.getRiverTransformations(), writeWeight);
        helper.writeArray(buffer, overworldGenRules.getShoreTransformations(), writeWeight);
        final TriConsumer<ByteBuf, BedrockCodecHelper, BiomeConditionalTransformationData> writeConditionalTransformation = this::writeBiomeConditionalTransformationData;
        helper.writeArray(buffer, overworldGenRules.getPreHillsEdge(), writeConditionalTransformation);
        helper.writeArray(buffer, overworldGenRules.getPostShoreEdge(), writeConditionalTransformation);
        helper.writeArray(buffer, overworldGenRules.getClimate(), this::writeBiomeWeightedTemperatureData);
    }

    protected BiomeOverworldGenRulesData readBiomeOverworldGenRulesData(ByteBuf buffer, BedrockCodecHelper helper) {
        final BiFunction<ByteBuf, BedrockCodecHelper, BiomeWeightedData> readWeight = this::readBiomeWeightedData;
        final BiFunction<ByteBuf, BedrockCodecHelper, BiomeConditionalTransformationData> readConditionalTransformation = this::readBiomeConditionalTransformationData;
        final BiomeOverworldGenRulesData data = new BiomeOverworldGenRulesData();
        helper.readArray(buffer, data.getHillsTransformations(), readWeight);
        helper.readArray(buffer, data.getMutateTransformations(), readWeight);
        helper.readArray(buffer, data.getRiverTransformations(), readWeight);
        helper.readArray(buffer, data.getShoreTransformations(), readWeight);
        helper.readArray(buffer, data.getPreHillsEdge(), readConditionalTransformation);
        helper.readArray(buffer, data.getPostShoreEdge(), readConditionalTransformation);
        helper.readArray(buffer, data.getClimate(), this::readBiomeWeightedTemperatureData);
        return data;
    }

    protected void writeBiomeWeightedData(ByteBuf buffer, BiomeWeightedData weightedData) {
        buffer.writeShortLE(weightedData.getBiomeIdentifier());
        buffer.writeIntLE(weightedData.getWeight());
    }

    protected BiomeWeightedData readBiomeWeightedData(ByteBuf buffer, BedrockCodecHelper helper) {
        final short biomeIdentifier = buffer.readShortLE();
        final int weight = buffer.readIntLE();
        return new BiomeWeightedData(biomeIdentifier, weight);
    }

    protected void writeBiomeConditionalTransformationData(ByteBuf buffer, BedrockCodecHelper helper, BiomeConditionalTransformationData conditionalTransformation) {
        helper.writeArray(buffer, conditionalTransformation.getTransformsInto(), this::writeBiomeWeightedData);
        buffer.writeShortLE(conditionalTransformation.getConditionJson());
        buffer.writeIntLE(conditionalTransformation.getMinPassingNeighbors());
    }

    protected BiomeConditionalTransformationData readBiomeConditionalTransformationData(ByteBuf buffer, BedrockCodecHelper helper) {
        final BiomeConditionalTransformationData data = new BiomeConditionalTransformationData();
        helper.readArray(buffer, data.getTransformsInto(), this::readBiomeWeightedData);
        data.setConditionJson(buffer.readShortLE());
        data.setMinPassingNeighbors((int) buffer.readUnsignedIntLE());
        return data;
    }

    protected void writeBiomeWeightedTemperatureData(ByteBuf buffer, BedrockCodecHelper helper, BiomeWeightedTemperatureData weightedTemperature) {
        VarInts.writeInt(buffer, weightedTemperature.getTemperature().ordinal());
        buffer.writeIntLE(weightedTemperature.getWeight());
    }

    protected BiomeWeightedTemperatureData readBiomeWeightedTemperatureData(ByteBuf buffer, BedrockCodecHelper helper) {
        final BiomeWeightedTemperatureData data = new BiomeWeightedTemperatureData();
        data.setTemperature(BiomeTemperatureCategory.from(VarInts.readInt(buffer)));
        data.setWeight(buffer.readIntLE());
        return data;
    }

    protected void writeBiomeMultinoiseGenRulesData(ByteBuf buffer, BedrockCodecHelper helper, BiomeMultinoiseGenRulesData data) {
        buffer.writeFloatLE(data.getTemperature());
        buffer.writeFloatLE(data.getHumidity());
        buffer.writeFloatLE(data.getAltitude());
        buffer.writeFloatLE(data.getWeirdness());
        buffer.writeFloatLE(data.getWeight());
    }

    protected BiomeMultinoiseGenRulesData readBiomeMultinoiseGenRulesData(ByteBuf buffer, BedrockCodecHelper helper) {
        float temperature = buffer.readFloatLE();
        float humidity = buffer.readFloatLE();
        float altitude = buffer.readFloatLE();
        float weirdness = buffer.readFloatLE();
        float weight = buffer.readFloatLE();

        return new BiomeMultinoiseGenRulesData(temperature, humidity, altitude, weirdness, weight);
    }

    protected void writeBiomeLegacyWorldGenRulesData(ByteBuf buffer, BedrockCodecHelper helper, BiomeLegacyWorldGenRulesData data) {
        helper.writeArray(buffer, data.getLegacyPreHillsEdge(), this::writeBiomeConditionalTransformationData);
    }

    protected BiomeLegacyWorldGenRulesData readBiomeLegacyWorldGenRulesData(ByteBuf buffer, BedrockCodecHelper helper) {
        final BiomeLegacyWorldGenRulesData data = new BiomeLegacyWorldGenRulesData();
        helper.readArray(buffer, data.getLegacyPreHillsEdge(), this::readBiomeConditionalTransformationData);
        return data;
    }

    protected void writeBlock(ByteBuf buffer, BedrockCodecHelper helper, BlockDefinition blockDefinition) {
        if (blockDefinition == null) {
            buffer.writeIntLE(-1);
            return;
        }
        DefinitionUtils.checkDefinition(helper.getBlockDefinitions(), blockDefinition);
        buffer.writeIntLE(blockDefinition.getRuntimeId());
    }

    protected BlockDefinition readBlock(ByteBuf buffer, BedrockCodecHelper helper) {
        int runtimeId = buffer.readIntLE();
        if (runtimeId == -1) {
            return null;
        }
        return helper.getBlockDefinitions().getDefinition(runtimeId);
    }

    protected ExpressionOp readExpressionOp(ByteBuf buffer) {
        int index = VarInts.readInt(buffer);
        if (index == -1) {
            return null;
        }
        return ExpressionOp.from(index);
    }

    protected void writeExpressionOp(ByteBuf buffer, ExpressionOp expressionOp) {
        if (expressionOp == null) {
            VarInts.writeInt(buffer, -1);
            return;
        }
        VarInts.writeInt(buffer, expressionOp.ordinal());
    }

    protected void writeBiomeSurfaceBuilderData(ByteBuf buffer, BedrockCodecHelper helper, BiomeSurfaceBuilderData data) {
        helper.writeOptionalNull(buffer, data.getSurfaceMaterials(), this::writeBiomeSurfaceMaterialData);
        buffer.writeBoolean(data.isHasSwampSurface());
        buffer.writeBoolean(data.isHasFrozenOceanSurface());
        buffer.writeBoolean(data.isHasTheEndSurface());
        helper.writeOptionalNull(buffer, data.getMesaSurface(), this::writeBiomeMesaSurfaceData);
        helper.writeOptionalNull(buffer, data.getCappedSurface(), this::writeBiomeCappedSurfaceData);
    }

    protected BiomeSurfaceBuilderData readBiomeSurfaceBuilderData(ByteBuf buffer, BedrockCodecHelper helper) {
        final BiomeSurfaceBuilderData data = new BiomeSurfaceBuilderData();
        data.setSurfaceMaterials(helper.readOptional(buffer, null, this::readBiomeSurfaceMaterialData));
        data.setHasSwampSurface(buffer.readBoolean());
        data.setHasFrozenOceanSurface(buffer.readBoolean());
        data.setHasTheEndSurface(buffer.readBoolean());
        data.setMesaSurface(helper.readOptional(buffer, null, this::readBiomeMesaSurfaceData));
        data.setCappedSurface(helper.readOptional(buffer, null, this::readBiomeCappedSurfaceData));
        return data;
    }
}
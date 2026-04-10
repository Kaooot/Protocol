package org.cloudburstmc.protocol.bedrock.codec.v800.serializer;

import io.netty.buffer.ByteBuf;
import it.unimi.dsi.fastutil.Pair;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.CoordinateEvaluationOrder;
import org.cloudburstmc.protocol.bedrock.data.ExpressionOp;
import org.cloudburstmc.protocol.bedrock.data.RandomDistributionType;
import org.cloudburstmc.protocol.bedrock.data.biome.*;
import org.cloudburstmc.protocol.bedrock.data.definitions.BlockDefinition;
import org.cloudburstmc.protocol.bedrock.packet.BiomeDefinitionListPacket;
import org.cloudburstmc.protocol.common.util.DefinitionUtils;
import org.cloudburstmc.protocol.common.util.TriConsumer;
import org.cloudburstmc.protocol.common.util.VarInts;

import java.awt.*;
import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

@SuppressWarnings("deprecation")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BiomeDefinitionListSerializer_v800 implements BedrockPacketSerializer<BiomeDefinitionListPacket> {

    public static final BiomeDefinitionListSerializer_v800 INSTANCE = new BiomeDefinitionListSerializer_v800();

    private static final BiomeTemperatureCategory[] TEMPERATURE_CATEGORIES = BiomeTemperatureCategory.values();
    private static final ExpressionOp[] EXPRESSION_OPS = ExpressionOp.values();
    private static final CoordinateEvaluationOrder[] EVALUATION_ORDERS = CoordinateEvaluationOrder.values();
    private static final RandomDistributionType[] RANDOM_DISTRIBUTION_TYPES = RandomDistributionType.values();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, BiomeDefinitionListPacket packet) {
        helper.writeArray(buffer, packet.getBiomes(), (byteBuf, aHelper, entry) -> {
            byteBuf.writeShortLE(entry.key());
            writeDefinition(byteBuf, aHelper, entry.value());
        });
        helper.writeArray(buffer, packet.getBiomeStringList(), helper::writeString);
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, BiomeDefinitionListPacket packet) {
        final List<Pair<Short, BiomeDefinitionData>> biomes = new ObjectArrayList<>();
        helper.readArray(buffer, biomes, (byteBuf, bedrockCodecHelper) -> {
            final short index = (short) byteBuf.readUnsignedShortLE();
            return Pair.of(index, this.readDefinition(byteBuf, bedrockCodecHelper));
        });
        helper.readArray(buffer, packet.getBiomeStringList(),
                (byteBuf, bedrockCodecHelper) -> bedrockCodecHelper.readString(byteBuf));
        packet.getBiomes().addAll(biomes);
    }

    protected void writeDefinitionId(ByteBuf buffer, BedrockCodecHelper helper, BiomeDefinitionData definition) {
        helper.writeOptional(buffer, Objects::nonNull, definition.getId(), (buf, id) -> buf.writeShortLE(id));
    }

    protected void writeDefinition(ByteBuf buffer, BedrockCodecHelper helper, BiomeDefinitionData definition) {
        this.writeDefinitionId(buffer, helper, definition);
        buffer.writeFloatLE(definition.getTemperature());
        buffer.writeFloatLE(definition.getDownfall());
        buffer.writeFloatLE(definition.getRedSporeDensity());
        buffer.writeFloatLE(definition.getBlueSporeDensity());
        buffer.writeFloatLE(definition.getAshDensity());
        buffer.writeFloatLE(definition.getWhiteAshDensity());
        buffer.writeFloatLE(definition.getDepth());
        buffer.writeFloatLE(definition.getScale());
        buffer.writeIntLE(definition.getMapWaterColor().getRGB());
        buffer.writeBoolean(definition.isRain());
        helper.writeOptionalNull(buffer, definition.getTags(), (byteBuf, aHelper, tags) -> {
            VarInts.writeUnsignedInt(byteBuf, tags.size());
            for (Short tag : tags) {
                byteBuf.writeShortLE(tag);
            }
        });
        helper.writeOptionalNull(buffer, definition.getChunkGenData(), this::writeDefinitionChunkGen);
    }

    protected short readDefinitionId(ByteBuf buffer, BedrockCodecHelper helper) {
        return helper.readOptional(buffer, null, (buf, codecHelper) -> (short) buf.readUnsignedShortLE());
    }

    protected BiomeDefinitionData readDefinition(ByteBuf buffer, BedrockCodecHelper helper) {
        final short id = this.readDefinitionId(buffer, helper);
        final float temperature = buffer.readFloatLE();
        final float downfall = buffer.readFloatLE();
        final float redSporeDensity = buffer.readFloatLE();
        final float blueSporeDensity = buffer.readFloatLE();
        final float ashDensity = buffer.readFloatLE();
        final float whiteAshDensity = buffer.readFloatLE();
        final float depth = buffer.readFloatLE();
        final float scale = buffer.readFloatLE();
        final Color mapWaterColor = new Color(buffer.readIntLE(), true);
        final boolean rain = buffer.readBoolean();
        final List<Short> tags = helper.readOptional(buffer, null, (buf, codecHelper) -> {
            final List<Short> list = new ObjectArrayList<>();
            codecHelper.readArray(buf, list, (buf1, codecHelper1) -> (short) buf1.readUnsignedShortLE());
            return list;
        });
        final BiomeDefinitionChunkGenData chunkGenData = helper.readOptional(buffer, null, this::readDefinitionChunkGen);
        return new BiomeDefinitionData(id, temperature, downfall, redSporeDensity, blueSporeDensity,
                ashDensity, whiteAshDensity, depth, scale, mapWaterColor,
                rain, tags, chunkGenData, 0f
        );
    }

    protected void writeDefinitionChunkGen(ByteBuf buffer, BedrockCodecHelper helper, BiomeDefinitionChunkGenData definitionChunkGen) {
        helper.writeOptionalNull(buffer, definitionChunkGen.getClimate(), this::writeClimate);
        helper.writeOptionalNull(buffer, definitionChunkGen.getConsolidatedFeatures(), this::writeConsolidatedFeatures);
        helper.writeOptionalNull(buffer, definitionChunkGen.getMountainParams(), this::writeMountainParamsData);
        helper.writeOptionalNull(buffer, definitionChunkGen.getSurfaceMaterialAdjustment(), this::writeSurfaceMaterialAdjustment);
        this.writeBiomeSurfaceBuilderData(buffer, helper, definitionChunkGen.getSurfaceBuilderData());
        helper.writeOptionalNull(buffer, definitionChunkGen.getOverworldGenRules(), this::writeOverworldGenRules);
        helper.writeOptionalNull(buffer, definitionChunkGen.getMultinoiseGenRules(), this::writeMultinoiseGenRules);
        helper.writeOptionalNull(buffer, definitionChunkGen.getLegacyWorldGenRules(), this::writeLegacyWorldGenRules);
    }

    protected BiomeDefinitionChunkGenData readDefinitionChunkGen(ByteBuf buffer, BedrockCodecHelper helper) {
        BiomeClimateData climate = helper.readOptional(buffer, null, this::readClimate);
        List<BiomeConsolidatedFeatureData> consolidatedFeatures = helper.readOptional(buffer, null, this::readConsolidatedFeatures);
        BiomeMountainParamsData mountainParams = helper.readOptional(buffer, null, this::readMountainParamsData);
        BiomeSurfaceMaterialAdjustmentData surfaceMaterialAdjustment = helper.readOptional(buffer, null, this::readSurfaceMaterialAdjustment);
        BiomeSurfaceBuilderData surfaceBuilderData = this.readBiomeSurfaceBuilderData(buffer, helper);
        BiomeOverworldGenRulesData overworldGenRules = helper.readOptional(buffer, null, this::readOverworldGenRules);
        BiomeMultinoiseGenRulesData multinoiseGenRules = helper.readOptional(buffer, null, this::readMultinoiseGenRules);
        BiomeLegacyWorldGenRulesData legacyWorldGenRules = helper.readOptional(buffer, null, this::readLegacyWorldGenRules);

        return new BiomeDefinitionChunkGenData(climate, consolidatedFeatures,
                mountainParams, surfaceMaterialAdjustment,
                surfaceBuilderData,
                overworldGenRules, multinoiseGenRules,
                legacyWorldGenRules, null, null, null, null);
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
        float temperature = buffer.readFloatLE();
        float downfall = buffer.readFloatLE();
        float redSporeDensity = buffer.readFloatLE();
        float blueSporeDensity = buffer.readFloatLE();
        float ashDensity = buffer.readFloatLE();
        float whiteAshDensity = buffer.readFloatLE();
        float snowAccumulationMin = buffer.readFloatLE();
        float snowAccumulationMax = buffer.readFloatLE();

        return new BiomeClimateData(temperature, downfall, redSporeDensity, blueSporeDensity,
                ashDensity, whiteAshDensity, snowAccumulationMin, snowAccumulationMax);
    }

    protected void writeConsolidatedFeatures(ByteBuf buffer, BedrockCodecHelper helper, List<BiomeConsolidatedFeatureData> consolidatedFeatures) {
        helper.writeArray(buffer, consolidatedFeatures, this::writeConsolidatedFeature);
    }

    protected List<BiomeConsolidatedFeatureData> readConsolidatedFeatures(ByteBuf buffer, BedrockCodecHelper helper) {
        List<BiomeConsolidatedFeatureData> consolidatedFeatures = new ObjectArrayList<>();
        helper.readArray(buffer, consolidatedFeatures, this::readConsolidatedFeature);
        return consolidatedFeatures;
    }

    protected void writeConsolidatedFeature(ByteBuf buffer, BedrockCodecHelper helper, BiomeConsolidatedFeatureData consolidatedFeature) {
        this.writeScatterParam(buffer, helper, consolidatedFeature.getScatter());
        buffer.writeShortLE(consolidatedFeature.getFeature());
        buffer.writeShortLE(consolidatedFeature.getIdentifier());
        buffer.writeShortLE(consolidatedFeature.getPass());
        buffer.writeBoolean(consolidatedFeature.isCanUseInternalFeature());
    }

    protected BiomeConsolidatedFeatureData readConsolidatedFeature(ByteBuf buffer, BedrockCodecHelper helper) {
        final BiomeScatterParamData scatter = this.readScatterParam(buffer, helper);
        final short feature = buffer.readShortLE();
        final short identifier = buffer.readShortLE();
        final short pass = buffer.readShortLE();
        final boolean canUseInternalFeature = buffer.readBoolean();
        return new BiomeConsolidatedFeatureData(scatter, feature, identifier, pass, canUseInternalFeature);
    }

    protected void writeScatterParam(ByteBuf buffer, BedrockCodecHelper helper, BiomeScatterParamData scatterParam) {
        helper.writeArray(buffer, scatterParam.getCoordinates(), this::writeCoordinate);
        VarInts.writeInt(buffer, scatterParam.getEvalOrder().ordinal());
        VarInts.writeInt(buffer, scatterParam.getChancePercentType() == null ? -1 : scatterParam.getChancePercentType().ordinal());
        buffer.writeShortLE(scatterParam.getChancePercent());
        buffer.writeIntLE(scatterParam.getChanceNumerator());
        buffer.writeIntLE(scatterParam.getChanceDenominator());
        VarInts.writeInt(buffer, scatterParam.getIterationsType() == null ? -1 : scatterParam.getIterationsType().ordinal());
        buffer.writeShortLE(scatterParam.getIterations());
    }

    protected BiomeScatterParamData readScatterParam(ByteBuf buffer, BedrockCodecHelper helper) {
        final List<BiomeCoordinateData> coordinates = new ObjectArrayList<>();
        helper.readArray(buffer, coordinates, this::readCoordinate);
        final CoordinateEvaluationOrder evalOrder = EVALUATION_ORDERS[VarInts.readInt(buffer)];
        final int chancePercentTypeInt = VarInts.readInt(buffer);
        final ExpressionOp chancePercentType = chancePercentTypeInt == -1 ? null : EXPRESSION_OPS[chancePercentTypeInt];
        final short chancePercent = buffer.readShortLE();
        final int chanceNumerator = buffer.readIntLE();
        final int chanceDenominator = buffer.readIntLE();
        final int iterationTypeInt = VarInts.readInt(buffer);
        final ExpressionOp iterationsType = iterationTypeInt == -1 ? null : EXPRESSION_OPS[iterationTypeInt];
        final short iterations = buffer.readShortLE();
        return new BiomeScatterParamData(coordinates, evalOrder, chancePercentType,
                chancePercent, chanceNumerator, chanceDenominator,
                iterationsType, iterations);
    }

    protected void writeCoordinate(ByteBuf buffer, BedrockCodecHelper helper, BiomeCoordinateData coordinate) {
        this.writeExpressionOp(buffer, coordinate.getMinValueType());
        buffer.writeShortLE(coordinate.getMinValue());
        this.writeExpressionOp(buffer, coordinate.getMaxValueType());
        buffer.writeShortLE(coordinate.getMaxValue());
        buffer.writeIntLE((int) coordinate.getGridOffset());
        buffer.writeIntLE((int) coordinate.getGridStepSize());
        VarInts.writeInt(buffer, coordinate.getDistribution().ordinal());
    }

    protected BiomeCoordinateData readCoordinate(ByteBuf buffer, BedrockCodecHelper helper) {
        final ExpressionOp minValueType = this.readExpressionOp(buffer);
        final short minValue = buffer.readShortLE();
        final ExpressionOp maxValueType = this.readExpressionOp(buffer);
        final short maxValue = buffer.readShortLE();
        final long gridOffset = buffer.readUnsignedIntLE();
        final long gridStepSize = buffer.readUnsignedIntLE();
        final RandomDistributionType distribution = RANDOM_DISTRIBUTION_TYPES[VarInts.readInt(buffer)];
        return new BiomeCoordinateData(minValueType, minValue, maxValueType,
                maxValue, gridOffset, gridStepSize, distribution);
    }

    protected void writeMountainParamsData(ByteBuf buffer, BedrockCodecHelper helper, BiomeMountainParamsData mountainParams) {
        this.writeBlock(buffer, helper, mountainParams.getSteepBlock());
        buffer.writeBoolean(mountainParams.isNorthSlopes());
        buffer.writeBoolean(mountainParams.isSouthSlopes());
        buffer.writeBoolean(mountainParams.isWestSlopes());
        buffer.writeBoolean(mountainParams.isEastSlopes());
        buffer.writeBoolean(mountainParams.isTopSlideEnabled());
    }

    protected BiomeMountainParamsData readMountainParamsData(ByteBuf buffer, BedrockCodecHelper helper) {
        BlockDefinition steepBlock = this.readBlock(buffer, helper);
        boolean northSlopes = buffer.readBoolean();
        boolean southSlopes = buffer.readBoolean();
        boolean westSlopes = buffer.readBoolean();
        boolean eastSlopes = buffer.readBoolean();
        boolean topSlideEnabled = buffer.readBoolean();

        return new BiomeMountainParamsData(steepBlock, northSlopes, southSlopes,
                westSlopes, eastSlopes, topSlideEnabled);
    }

    protected void writeSurfaceMaterialAdjustment(ByteBuf buffer, BedrockCodecHelper helper,
                                                  BiomeSurfaceMaterialAdjustmentData surfaceMaterialAdjustment) {
        helper.writeArray(buffer, surfaceMaterialAdjustment.getBiomeElements(), this::writeBiomeElement);
    }

    protected BiomeSurfaceMaterialAdjustmentData readSurfaceMaterialAdjustment(ByteBuf buffer, BedrockCodecHelper helper) {
        List<BiomeElementData> biomeElements = new ObjectArrayList<>();
        helper.readArray(buffer, biomeElements, this::readBiomeElement);
        return new BiomeSurfaceMaterialAdjustmentData(biomeElements);
    }

    protected void writeBiomeElement(ByteBuf buffer, BedrockCodecHelper helper, BiomeElementData biomeElement) {
        buffer.writeFloatLE(biomeElement.getNoiseFrequencyScale());
        buffer.writeFloatLE(biomeElement.getNoiseLowerBound());
        buffer.writeFloatLE(biomeElement.getNoiseUpperBound());
        this.writeExpressionOp(buffer, biomeElement.getHeightMinType());
        buffer.writeShortLE(biomeElement.getHeightMin());
        this.writeExpressionOp(buffer, biomeElement.getHeightMaxType());
        buffer.writeShortLE(biomeElement.getHeightMax());
        this.writeSurfaceMaterial(buffer, helper, biomeElement.getAdjustedMaterials());
    }

    protected BiomeElementData readBiomeElement(ByteBuf buffer, BedrockCodecHelper helper) {
        final float noiseFrequencyScale = buffer.readFloatLE();
        final float noiseLowerBound = buffer.readFloatLE();
        final float noiseUpperBound = buffer.readFloatLE();
        final ExpressionOp heightMinType = this.readExpressionOp(buffer);
        final short heightMin = buffer.readShortLE();
        final ExpressionOp heightMaxType = this.readExpressionOp(buffer);
        final short heightMax = buffer.readShortLE();
        final BiomeSurfaceMaterialData adjustedMaterials = readSurfaceMaterial(buffer, helper);
        return new BiomeElementData(noiseFrequencyScale, noiseLowerBound, noiseUpperBound,
                heightMinType, heightMin, heightMaxType, heightMax, adjustedMaterials);
    }

    protected void writeSurfaceMaterial(ByteBuf buffer, BedrockCodecHelper helper, BiomeSurfaceMaterialData surfaceMaterial) {
        this.writeBlock(buffer, helper, surfaceMaterial.getTopBlock());
        this.writeBlock(buffer, helper, surfaceMaterial.getMidBlock());
        this.writeBlock(buffer, helper, surfaceMaterial.getSeaFloorBlock());
        this.writeBlock(buffer, helper, surfaceMaterial.getFoundationBlock());
        this.writeBlock(buffer, helper, surfaceMaterial.getSeaBlock());
        buffer.writeIntLE(surfaceMaterial.getSeaFloorDepth());
    }

    protected BiomeSurfaceMaterialData readSurfaceMaterial(ByteBuf buffer, BedrockCodecHelper helper) {
        BlockDefinition topBlock = this.readBlock(buffer, helper);
        BlockDefinition midBlock = this.readBlock(buffer, helper);
        BlockDefinition seaFloorBlock = this.readBlock(buffer, helper);
        BlockDefinition foundationBlock = this.readBlock(buffer, helper);
        BlockDefinition seaBlock = this.readBlock(buffer, helper);
        int seaFloorDepth = buffer.readIntLE();

        return new BiomeSurfaceMaterialData(topBlock, midBlock, seaFloorBlock, foundationBlock, seaBlock, seaFloorDepth);
    }


    protected void writeMesaSurface(ByteBuf buffer, BedrockCodecHelper helper, BiomeMesaSurfaceData mesaSurface) {
        this.writeBlock(buffer, helper, mesaSurface.getClayMaterial());
        this.writeBlock(buffer, helper, mesaSurface.getHardClayMaterial());
        buffer.writeBoolean(mesaSurface.isBrycePillars());
        buffer.writeBoolean(mesaSurface.isHasForest());
    }

    protected BiomeMesaSurfaceData readMesaSurface(ByteBuf buffer, BedrockCodecHelper helper) {
        BlockDefinition clayMaterial = this.readBlock(buffer, helper);
        BlockDefinition hardClayMaterial = this.readBlock(buffer, helper);
        boolean brycePillars = buffer.readBoolean();
        boolean hasForest = buffer.readBoolean();

        return new BiomeMesaSurfaceData(clayMaterial, hardClayMaterial, brycePillars, hasForest);
    }


    protected void writeCappedSurface(ByteBuf buffer, BedrockCodecHelper helper, BiomeCappedSurfaceData cappedSurface) {
        helper.writeArray(buffer, cappedSurface.getFloorBlocks(), this::writeBlock);
        helper.writeArray(buffer, cappedSurface.getCeilingBlocks(), this::writeBlock);
        helper.writeOptionalNull(buffer, cappedSurface.getSeaBlock(), this::writeBlock);
        helper.writeOptionalNull(buffer, cappedSurface.getFoundationBlock(), this::writeBlock);
        helper.writeOptionalNull(buffer, cappedSurface.getBeachBlock(), this::writeBlock);
    }

    protected BiomeCappedSurfaceData readCappedSurface(ByteBuf buffer, BedrockCodecHelper helper) {
        List<BlockDefinition> floorBlocks = new ObjectArrayList<>();
        helper.readArray(buffer, floorBlocks, this::readBlock);
        List<BlockDefinition> ceilingBlocks = new ObjectArrayList<>();
        helper.readArray(buffer, ceilingBlocks, this::readBlock);
        BlockDefinition seaBlock = helper.readOptional(buffer, null, this::readBlock);
        BlockDefinition foundationBlock = helper.readOptional(buffer, null, this::readBlock);
        BlockDefinition beachBlock = helper.readOptional(buffer, null, this::readBlock);

        return new BiomeCappedSurfaceData(floorBlocks, ceilingBlocks, seaBlock, foundationBlock, beachBlock);
    }

    protected void writeOverworldGenRules(ByteBuf buffer, BedrockCodecHelper helper, BiomeOverworldGenRulesData overworldGenRules) {
        final BiConsumer<ByteBuf, BiomeWeightedData> writeWeight = this::writeWeight;
        helper.writeArray(buffer, overworldGenRules.getHillsTransformations(), writeWeight);
        helper.writeArray(buffer, overworldGenRules.getMutateTransformations(), writeWeight);
        helper.writeArray(buffer, overworldGenRules.getRiverTransformations(), writeWeight);
        helper.writeArray(buffer, overworldGenRules.getShoreTransformations(), writeWeight);
        final TriConsumer<ByteBuf, BedrockCodecHelper, BiomeConditionalTransformationData> writeConditionalTransformation = this::writeConditionalTransformation;
        helper.writeArray(buffer, overworldGenRules.getPreHillsEdge(), writeConditionalTransformation);
        helper.writeArray(buffer, overworldGenRules.getPostShoreEdge(), writeConditionalTransformation);
        helper.writeArray(buffer, overworldGenRules.getClimate(), this::writeWeightedTemperature);
    }

    protected BiomeOverworldGenRulesData readOverworldGenRules(ByteBuf buffer, BedrockCodecHelper helper) {
        final BiFunction<ByteBuf, BedrockCodecHelper, BiomeWeightedData> readWeight = this::readWeight;
        final List<BiomeWeightedData> hillsTransformations = new ObjectArrayList<>();
        helper.readArray(buffer, hillsTransformations, readWeight);

        final List<BiomeWeightedData> mutateTransformations = new ObjectArrayList<>();
        helper.readArray(buffer, mutateTransformations, readWeight);

        final List<BiomeWeightedData> riverTransformations = new ObjectArrayList<>();
        helper.readArray(buffer, riverTransformations, readWeight);

        final List<BiomeWeightedData> shoreTransformations = new ObjectArrayList<>();
        helper.readArray(buffer, shoreTransformations, readWeight);

        final BiFunction<ByteBuf, BedrockCodecHelper, BiomeConditionalTransformationData> readConditionalTransformation = this::readConditionalTransformation;
        final List<BiomeConditionalTransformationData> preHillsEdgeTransformations = new ObjectArrayList<>();
        helper.readArray(buffer, preHillsEdgeTransformations, readConditionalTransformation);

        final List<BiomeConditionalTransformationData> postShoreTransformations = new ObjectArrayList<>();
        helper.readArray(buffer, postShoreTransformations, readConditionalTransformation);

        final List<BiomeWeightedTemperatureData> climateTransformations = new ObjectArrayList<>();
        helper.readArray(buffer, climateTransformations, this::readWeightedTemperature);
        return new BiomeOverworldGenRulesData(hillsTransformations,
                mutateTransformations,
                riverTransformations,
                shoreTransformations,
                preHillsEdgeTransformations,
                postShoreTransformations,
                climateTransformations);
    }

    protected void writeWeight(ByteBuf buffer, BiomeWeightedData weightedData) {
        buffer.writeShortLE(weightedData.getBiomeIdentifier());
        buffer.writeIntLE(weightedData.getWeight());
    }

    protected BiomeWeightedData readWeight(ByteBuf buffer, BedrockCodecHelper helper) {
        final short biomeIdentifier = buffer.readShortLE();
        final int weight = buffer.readIntLE();
        return new BiomeWeightedData(biomeIdentifier, weight);
    }

    protected void writeConditionalTransformation(ByteBuf buffer, BedrockCodecHelper helper, BiomeConditionalTransformationData conditionalTransformation) {
        helper.writeArray(buffer, conditionalTransformation.getTransformsInto(), this::writeWeight);
        buffer.writeShortLE(conditionalTransformation.getConditionJson());
        buffer.writeIntLE(conditionalTransformation.getMinPassingNeighbors());
    }

    protected BiomeConditionalTransformationData readConditionalTransformation(ByteBuf buffer, BedrockCodecHelper helper) {
        final List<BiomeWeightedData> weightedBiomes = new ObjectArrayList<>();
        helper.readArray(buffer, weightedBiomes, this::readWeight);
        final short conditionJson = buffer.readShortLE();
        final int minPassingNeighbors = (int) buffer.readUnsignedIntLE();
        return new BiomeConditionalTransformationData(weightedBiomes, conditionJson, minPassingNeighbors);
    }

    protected void writeWeightedTemperature(ByteBuf buffer, BedrockCodecHelper helper, BiomeWeightedTemperatureData weightedTemperature) {
        VarInts.writeInt(buffer, weightedTemperature.getTemperature().ordinal());
        buffer.writeIntLE((int) weightedTemperature.getWeight());
    }

    protected BiomeWeightedTemperatureData readWeightedTemperature(ByteBuf buffer, BedrockCodecHelper helper) {
        BiomeTemperatureCategory temperature = TEMPERATURE_CATEGORIES[VarInts.readInt(buffer)];
        int weight = buffer.readIntLE();
        return new BiomeWeightedTemperatureData(temperature, weight);
    }

    protected void writeMultinoiseGenRules(ByteBuf buffer, BedrockCodecHelper helper, BiomeMultinoiseGenRulesData multinoiseGenRules) {
        buffer.writeFloatLE(multinoiseGenRules.getTemperature());
        buffer.writeFloatLE(multinoiseGenRules.getHumidity());
        buffer.writeFloatLE(multinoiseGenRules.getAltitude());
        buffer.writeFloatLE(multinoiseGenRules.getWeirdness());
        buffer.writeFloatLE(multinoiseGenRules.getWeight());
    }

    protected BiomeMultinoiseGenRulesData readMultinoiseGenRules(ByteBuf buffer, BedrockCodecHelper helper) {
        float temperature = buffer.readFloatLE();
        float humidity = buffer.readFloatLE();
        float altitude = buffer.readFloatLE();
        float weirdness = buffer.readFloatLE();
        float weight = buffer.readFloatLE();

        return new BiomeMultinoiseGenRulesData(temperature, humidity, altitude, weirdness, weight);
    }

    protected void writeLegacyWorldGenRules(ByteBuf buffer, BedrockCodecHelper helper, BiomeLegacyWorldGenRulesData legacyWorldGenRules) {
        helper.writeArray(buffer, legacyWorldGenRules.getLegacyPreHills(), this::writeConditionalTransformation);
    }

    protected BiomeLegacyWorldGenRulesData readLegacyWorldGenRules(ByteBuf buffer, BedrockCodecHelper helper) {
        final List<BiomeConditionalTransformationData> legacyPreHills = new ObjectArrayList<>();
        helper.readArray(buffer, legacyPreHills, this::readConditionalTransformation);
        return new BiomeLegacyWorldGenRulesData(legacyPreHills);
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
        return EXPRESSION_OPS[index];
    }

    protected void writeExpressionOp(ByteBuf buffer, ExpressionOp expressionOp) {
        if (expressionOp == null) {
            VarInts.writeInt(buffer, -1);
            return;
        }
        VarInts.writeInt(buffer, expressionOp.ordinal());
    }

    protected void writeBiomeSurfaceBuilderData(ByteBuf buffer, BedrockCodecHelper helper, BiomeSurfaceBuilderData data) {
        helper.writeOptionalNull(buffer, data.getSurfaceMaterial(), this::writeSurfaceMaterial);
        buffer.writeBoolean(data.isHasSwampSurface());
        buffer.writeBoolean(data.isHasFrozenOceanSurface());
        buffer.writeBoolean(data.isHasTheEndSurface());
        helper.writeOptionalNull(buffer, data.getMesaSurface(), this::writeMesaSurface);
        helper.writeOptionalNull(buffer, data.getCappedSurface(), this::writeCappedSurface);
    }

    protected BiomeSurfaceBuilderData readBiomeSurfaceBuilderData(ByteBuf buffer, BedrockCodecHelper helper) {
        final BiomeSurfaceMaterialData surfaceMaterial = helper.readOptional(buffer, null, this::readSurfaceMaterial);
        final boolean hasSwampSurface = buffer.readBoolean();
        final boolean hasFrozenOceanSurface = buffer.readBoolean();
        final boolean hasTheEndSurface = buffer.readBoolean();
        final BiomeMesaSurfaceData mesaSurface = helper.readOptional(buffer, null, this::readMesaSurface);
        final BiomeCappedSurfaceData cappedSurface = helper.readOptional(buffer, null, this::readCappedSurface);
        return new BiomeSurfaceBuilderData(
                surfaceMaterial,
                false,
                hasSwampSurface,
                hasFrozenOceanSurface,
                hasTheEndSurface,
                mesaSurface,
                cappedSurface,
                null
        );
    }
}
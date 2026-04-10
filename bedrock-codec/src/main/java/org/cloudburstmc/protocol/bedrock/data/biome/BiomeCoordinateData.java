package org.cloudburstmc.protocol.bedrock.data.biome;

import lombok.Value;
import org.cloudburstmc.protocol.bedrock.data.ExpressionOp;
import org.cloudburstmc.protocol.bedrock.data.RandomDistributionType;

@Value
public class BiomeCoordinateData {
    ExpressionOp minValueType;
    short minValue;
    ExpressionOp maxValueType;
    short maxValue;
    long gridOffset;
    long gridStepSize;
    RandomDistributionType distribution;
}
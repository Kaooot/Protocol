package org.cloudburstmc.protocol.bedrock.data.biome;

import lombok.Value;
import org.cloudburstmc.protocol.bedrock.data.CoordinateEvaluationOrder;
import org.cloudburstmc.protocol.bedrock.data.ExpressionOp;

import java.util.List;

@Value
public class BiomeScatterParamData {

    List<BiomeCoordinateData> coordinates;
    CoordinateEvaluationOrder evalOrder;
    ExpressionOp chancePercentType;
    short chancePercent;
    int chanceNumerator;
    int chanceDenominator;
    ExpressionOp iterationsType;
    short iterations;
}
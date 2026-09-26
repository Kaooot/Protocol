package org.cloudburstmc.protocol.bedrock.data.biome;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.data.structure.CoordinateEvaluationOrder;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BiomeScatterParamData {

    private final List<BiomeCoordinateData> coordinates = new ObjectArrayList<>();
    private CoordinateEvaluationOrder evalOrder;
    private ExpressionOp chancePercentType;
    private int chancePercent;
    private int chanceNumerator;
    private int chanceDenominator;
    private ExpressionOp iterationsType;
    private int iterations;
}
package org.cloudburstmc.protocol.bedrock.data.biome;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.data.structure.RandomDistributionType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BiomeCoordinateData {

    private ExpressionOp minValueType;
    private int minValue;
    private ExpressionOp maxValueType;
    private int maxValue;
    private int gridOffset;
    private int gridStepSize;
    private RandomDistributionType distribution;
}
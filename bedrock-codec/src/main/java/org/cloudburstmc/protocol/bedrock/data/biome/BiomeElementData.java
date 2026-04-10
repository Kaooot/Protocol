package org.cloudburstmc.protocol.bedrock.data.biome;

import lombok.Value;
import org.cloudburstmc.protocol.bedrock.data.ExpressionOp;

@Value
public class BiomeElementData {
    float noiseFrequencyScale;
    float noiseLowerBound;
    float noiseUpperBound;
    ExpressionOp heightMinType;
    short heightMin;
    ExpressionOp heightMaxType;
    short heightMax;
    BiomeSurfaceMaterialData adjustedMaterials;
}

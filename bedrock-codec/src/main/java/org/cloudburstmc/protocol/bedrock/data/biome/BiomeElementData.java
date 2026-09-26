package org.cloudburstmc.protocol.bedrock.data.biome;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BiomeElementData {

    private float noiseFreqScale;
    private float noiseLowerBound;
    private float noiseUpperBound;
    private ExpressionOp heightMinType;
    private int heightMin;
    private ExpressionOp heightMaxType;
    private int heightMax;
    private BiomeSurfaceMaterialData adjustedMaterials;
}
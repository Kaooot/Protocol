package org.cloudburstmc.protocol.bedrock.data.biome;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BiomeClimateData {

    private float temperature;
    private float downfall;
    /**
     * @deprecated since v844
     */
    float redSporeDensity;
    /**
     * @deprecated since v844
     */
    float blueSporeDensity;
    /**
     * @deprecated since v844
     */
    float ashDensity;
    /**
     * @deprecated since v844
     */
    float whiteAshDensity;
    private float snowAccumulationMin;
    private float snowAccumulationMax;
}
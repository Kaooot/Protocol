package org.cloudburstmc.protocol.bedrock.data.biome;

import lombok.Value;

@Value
public class BiomeClimateData {
    float temperature;
    float downfall;
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
    float snowAccumulationMin;
    float snowAccumulationMax;
}

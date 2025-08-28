package org.cloudburstmc.protocol.bedrock.data.biome;

import lombok.Value;

@Value
public class BiomeClimateData {
    float temperature;
    float downfall;
    /**
     * @deprecated since v843
     */
    float redSporeDensity;
    /**
     * @deprecated since v843
     */
    float blueSporeDensity;
    /**
     * @deprecated since v843
     */
    float ashDensity;
    /**
     * @deprecated since v843
     */
    float whiteAshDensity;
    float snowAccumulationMin;
    float snowAccumulationMax;
}

package org.cloudburstmc.protocol.bedrock.data.biome;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.annotation.Removed;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BiomeClimateData {

    private float temperature;
    private float downfall;
    @Deprecated
    @Removed("1.21.111")
    float redSporeDensity;
    @Deprecated
    @Removed("1.21.111")
    float blueSporeDensity;
    @Deprecated
    @Removed("1.21.111")
    float ashDensity;
    @Deprecated
    @Removed("1.21.111")
    float whiteAshDensity;
    private float snowAccumulationMin;
    private float snowAccumulationMax;
}
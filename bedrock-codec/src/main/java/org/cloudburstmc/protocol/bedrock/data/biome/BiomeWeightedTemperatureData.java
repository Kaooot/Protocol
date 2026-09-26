package org.cloudburstmc.protocol.bedrock.data.biome;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BiomeWeightedTemperatureData {

    private BiomeTemperatureCategory temperature;
    private int weight;
}
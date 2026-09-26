package org.cloudburstmc.protocol.bedrock.data.biome;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoiseBlockSpecifier {

    private String noise;

    private float threshold;

    private float rangeMin;

    private float rangeMax;

    private int block;
}

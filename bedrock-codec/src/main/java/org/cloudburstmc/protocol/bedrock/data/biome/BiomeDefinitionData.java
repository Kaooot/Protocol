package org.cloudburstmc.protocol.bedrock.data.biome;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BiomeDefinitionData {

    private Integer id;
    private float temperature;
    private float downfall;
    /**
     * @deprecated since v844
     */
    private float redSporeDensity;
    /**
     * @deprecated since v844
     */
    private float blueSporeDensity;
    /**
     * @deprecated since v844
     */
    private float ashDensity;
    /**
     * @deprecated since v844
     */
    private float whiteAshDensity;
    /**
     * @since v844
     */
    private float foliageSnow;
    private float depth;
    private float scale;
    private Color mapWaterColorArgb;
    private boolean rain;
    private BiomeTagsData tags;
    private BiomeDefinitionChunkGenData chunkGenData;
}
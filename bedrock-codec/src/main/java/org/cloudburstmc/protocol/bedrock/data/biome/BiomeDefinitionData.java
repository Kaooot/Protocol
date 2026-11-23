package org.cloudburstmc.protocol.bedrock.data.biome;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.annotation.Added;
import org.cloudburstmc.protocol.bedrock.annotation.Removed;

import java.awt.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BiomeDefinitionData {

    private Integer id;
    private float temperature;
    private float downfall;
    @Deprecated
    @Removed("1.21.111")
    private float redSporeDensity;
    @Deprecated
    @Removed("1.21.111")
    private float blueSporeDensity;
    @Deprecated
    @Removed("1.21.111")
    private float ashDensity;
    @Deprecated
    @Removed("1.21.111")
    private float whiteAshDensity;
    @Added("1.21.111")
    private float foliageSnow;
    private float depth;
    private float scale;
    private Color mapWaterColorArgb;
    private boolean rain;
    private BiomeTagsData tags;
    private BiomeDefinitionChunkGenData chunkGenData;
}
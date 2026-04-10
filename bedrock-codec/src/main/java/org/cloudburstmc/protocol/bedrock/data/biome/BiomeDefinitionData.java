package org.cloudburstmc.protocol.bedrock.data.biome;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.awt.*;
import java.util.List;

@Value
@RequiredArgsConstructor(onConstructor_ = {@Deprecated})
public class BiomeDefinitionData {

    @Nullable
    Short id;
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
    float depth;
    float scale;
    Color mapWaterColor;
    boolean rain;
    @Nullable
    List<Short> tags;
    @Nullable
    BiomeDefinitionChunkGenData chunkGenData;
    /**
     * @since v844
     */
    float foliageSnow;
}
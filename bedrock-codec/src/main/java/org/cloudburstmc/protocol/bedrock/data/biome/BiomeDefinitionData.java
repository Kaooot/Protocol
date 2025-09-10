package org.cloudburstmc.protocol.bedrock.data.biome;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.cloudburstmc.protocol.common.util.index.Indexable;
import org.cloudburstmc.protocol.common.util.index.IndexedList;
import org.cloudburstmc.protocol.common.util.index.Unindexed;

import java.awt.*;
import java.util.List;

@Value
@RequiredArgsConstructor(onConstructor_ = {@Deprecated})
public class BiomeDefinitionData {

    @Nullable
    @Getter(AccessLevel.NONE)
    transient Indexable<String> id;
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
    float depth;
    float scale;
    Color mapWaterColor;
    boolean rain;
    @Nullable
    @Getter(AccessLevel.NONE)
    transient Indexable<List<String>> tags;
    @Nullable
    BiomeDefinitionChunkGenData chunkGenData;
    /**
     * @since v843
     */
    float foliageSnow;

    @JsonCreator
    public BiomeDefinitionData(@Nullable String id, float temperature, float downfall, float redSporeDensity,
                               float blueSporeDensity, float ashDensity, float whiteAshDensity, float depth,
                               float scale, Color mapWaterColor, boolean rain, @Nullable List<String> tags,
                               @Nullable BiomeDefinitionChunkGenData chunkGenData, float foliageSnow) {
        this.id = id == null ? null : new Unindexed<>(id);
        this.temperature = temperature;
        this.downfall = downfall;
        this.redSporeDensity = redSporeDensity;
        this.blueSporeDensity = blueSporeDensity;
        this.ashDensity = ashDensity;
        this.whiteAshDensity = whiteAshDensity;
        this.depth = depth;
        this.scale = scale;
        this.mapWaterColor = mapWaterColor;
        this.rain = rain;
        this.tags = tags == null ? null : new Unindexed<>(tags);
        this.chunkGenData = chunkGenData;
        this.foliageSnow = foliageSnow;
    }

    @JsonCreator
    public BiomeDefinitionData(@Nullable Indexable<String> id, float temperature, float downfall, float redSporeDensity,
                               float blueSporeDensity, float ashDensity, float whiteAshDensity, float depth,
                               float scale, Color mapWaterColor, boolean rain, @Nullable IndexedList<String> tags,
                               @Nullable BiomeDefinitionChunkGenData chunkGenData) {
        this(id, temperature, downfall, redSporeDensity, blueSporeDensity, ashDensity, whiteAshDensity, depth, scale, mapWaterColor, rain, tags, chunkGenData, 0f);
    }

    public @Nullable String getId() {
        if (id == null) {
            return null;
        }
        return id.get();
    }

    public @Nullable List<String> getTags() {
        if (tags == null) {
            return null;
        }
        return tags.get();
    }
}

package org.cloudburstmc.protocol.bedrock.data.biome;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * @author Kaooot
 */
@Value
@RequiredArgsConstructor(onConstructor_ = {@Deprecated})
public class BiomeSurfaceBuilderData {

    @Nullable
    BiomeSurfaceMaterialData surfaceMaterial;
    /**
     * @since v844
     */
    boolean hasDefaultOverworldSurface;
    boolean hasSwampSurface;
    boolean hasFrozenOceanSurface;
    boolean hasTheEndSurface;
    @Nullable
    BiomeMesaSurfaceData mesaSurface;
    @Nullable
    BiomeCappedSurfaceData cappedSurface;
    /**
     * @since v975
     */
    @Nullable
    BiomeNoiseGradientSurfaceData noiseGradientSurface;
}
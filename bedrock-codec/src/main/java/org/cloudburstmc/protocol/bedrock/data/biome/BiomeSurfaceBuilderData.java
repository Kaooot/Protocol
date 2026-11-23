package org.cloudburstmc.protocol.bedrock.data.biome;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.annotation.Added;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BiomeSurfaceBuilderData {

    private BiomeSurfaceMaterialData surfaceMaterials;
    @Added("1.21.111")
    private boolean hasDefaultOverworldSurface;
    private boolean hasSwampSurface;
    private boolean hasFrozenOceanSurface;
    private boolean hasTheEndSurface;
    private BiomeMesaSurfaceData mesaSurface;
    private BiomeCappedSurfaceData cappedSurface;
    private BiomeNoiseGradientSurfaceData noiseGradientSurface;
}
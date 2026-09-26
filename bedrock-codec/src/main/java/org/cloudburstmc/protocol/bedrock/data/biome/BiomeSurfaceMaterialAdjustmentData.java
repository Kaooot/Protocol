package org.cloudburstmc.protocol.bedrock.data.biome;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class BiomeSurfaceMaterialAdjustmentData {

    private final List<BiomeElementData> adjustments = new ObjectArrayList<>();
}
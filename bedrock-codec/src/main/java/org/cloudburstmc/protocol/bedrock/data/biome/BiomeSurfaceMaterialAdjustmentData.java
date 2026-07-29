package org.cloudburstmc.protocol.bedrock.data.biome;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;
import lombok.Data;

@Data
public class BiomeSurfaceMaterialAdjustmentData {
  private final List<BiomeElementData> adjustments = new ObjectArrayList<>();
}

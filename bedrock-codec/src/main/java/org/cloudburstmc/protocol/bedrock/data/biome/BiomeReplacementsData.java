package org.cloudburstmc.protocol.bedrock.data.biome;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;
import lombok.Data;

@Data
public class BiomeReplacementsData {
  private final List<BiomeReplacementData> biomeReplacements = new ObjectArrayList<>();
}

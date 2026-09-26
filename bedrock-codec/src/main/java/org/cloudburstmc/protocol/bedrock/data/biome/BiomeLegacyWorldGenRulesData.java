package org.cloudburstmc.protocol.bedrock.data.biome;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class BiomeLegacyWorldGenRulesData {

    private final List<BiomeConditionalTransformationData> legacyPreHillsEdge = new ObjectArrayList<>();
}
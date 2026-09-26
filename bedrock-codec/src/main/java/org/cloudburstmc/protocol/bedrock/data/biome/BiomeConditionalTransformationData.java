package org.cloudburstmc.protocol.bedrock.data.biome;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BiomeConditionalTransformationData {

    private final List<BiomeWeightedData> transformsInto = new ObjectArrayList<>();
    private int conditionJson;
    private int minPassingNeighbors;
}
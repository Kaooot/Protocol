package org.cloudburstmc.protocol.bedrock.data.biome;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;

import java.util.List;

@Data
public class BiomeConsolidatedFeaturesData {

    private final List<BiomeConsolidatedFeatureData> features = new ObjectArrayList<>();
}
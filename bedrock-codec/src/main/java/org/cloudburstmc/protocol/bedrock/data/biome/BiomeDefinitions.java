package org.cloudburstmc.protocol.bedrock.data.biome;

import java.util.LinkedHashMap;
import java.util.Map;

public class BiomeDefinitions {

    private final Map<String, BiomeDefinitionData> definitions;

    public BiomeDefinitions() {
        this.definitions = new LinkedHashMap<>();
    }

    public BiomeDefinitions(Map<String, BiomeDefinitionData> definitions) {
        this.definitions = definitions;
    }

    public BiomeDefinitions(IndexedBiomes indexedBiomes) {
        this.definitions = indexedBiomes.get();
    }

    public Map<String, BiomeDefinitionData> getDefinitions() {
        return this.definitions;
    }
}

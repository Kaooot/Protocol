package org.cloudburstmc.protocol.bedrock.data.biome;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;

import java.util.List;

@Data
public class BiomeStringList {

    private final List<String> strings = new ObjectArrayList<>();
}
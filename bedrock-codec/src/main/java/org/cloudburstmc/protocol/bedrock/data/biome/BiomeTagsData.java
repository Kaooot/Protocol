package org.cloudburstmc.protocol.bedrock.data.biome;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;

import java.util.List;

@Data
public class BiomeTagsData {

    private final List<Integer> tags = new ObjectArrayList<>();
}
package org.cloudburstmc.protocol.bedrock.data.biome;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;

import java.util.List;

@Data
public class BiomeReplacementsData {

    private final List<BiomeReplacementData> biomeReplacements = new ObjectArrayList<>();
}
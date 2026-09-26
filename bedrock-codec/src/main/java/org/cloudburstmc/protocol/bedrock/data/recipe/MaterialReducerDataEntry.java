package org.cloudburstmc.protocol.bedrock.data.recipe;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;

import java.util.List;

@Data
public class MaterialReducerDataEntry {

    private int fromItemKey;
    private final List<MaterialReducerEntryOutput> itemIdsAndCounts = new ObjectArrayList<>();
}
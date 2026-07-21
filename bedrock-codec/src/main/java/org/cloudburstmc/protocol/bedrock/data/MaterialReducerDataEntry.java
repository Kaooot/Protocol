package org.cloudburstmc.protocol.bedrock.data;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;
import lombok.Data;

@Data
public class MaterialReducerDataEntry {
  private int fromItemKey;

  private final List<MaterialReducerEntryOutput> itemIdsAndCounts = new ObjectArrayList<>();
}

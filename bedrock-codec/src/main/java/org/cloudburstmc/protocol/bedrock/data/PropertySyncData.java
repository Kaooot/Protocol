package org.cloudburstmc.protocol.bedrock.data;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;
import lombok.Data;

@Data
public class PropertySyncData {
  private final List<PropertySyncIntEntry> intEntriesList = new ObjectArrayList<>();

  private final List<PropertySyncFloatEntry> floatEntriesList = new ObjectArrayList<>();
}

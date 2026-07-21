package org.cloudburstmc.protocol.bedrock.data;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PropertySyncData {
  private final List<PropertySyncIntEntry> intEntriesList = new ObjectArrayList<>();

  private final List<PropertySyncFloatEntry> floatEntriesList = new ObjectArrayList<>();
}

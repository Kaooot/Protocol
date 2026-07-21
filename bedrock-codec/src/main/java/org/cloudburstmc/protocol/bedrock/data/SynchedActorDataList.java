package org.cloudburstmc.protocol.bedrock.data;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;
import lombok.Data;

@Data
public class SynchedActorDataList {
  private final List<DataItemEntry> data = new ObjectArrayList<>();
}

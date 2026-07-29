package org.cloudburstmc.protocol.bedrock.data.clock;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;
import lombok.Data;
import org.cloudburstmc.protocol.bedrock.data.SyncWorldClockStateData;

@Data
public class SyncStateData {
  private final List<SyncWorldClockStateData> clockData = new ObjectArrayList<>();
}

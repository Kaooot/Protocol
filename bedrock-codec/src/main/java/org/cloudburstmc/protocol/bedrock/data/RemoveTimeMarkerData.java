package org.cloudburstmc.protocol.bedrock.data;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.lang.Long;
import java.util.List;
import lombok.Data;

@Data
public class RemoveTimeMarkerData {
  private long clockId;

  private final List<Long> timeMarkerIds = new ObjectArrayList<>();
}

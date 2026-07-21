package org.cloudburstmc.protocol.bedrock.data;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;
import lombok.Data;

@Data
public class AddTimeMarkerData {
  private long clockId;

  private final List<TimeMarkerData> timeMarkers = new ObjectArrayList<>();
}

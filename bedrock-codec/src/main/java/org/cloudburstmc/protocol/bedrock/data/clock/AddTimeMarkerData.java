package org.cloudburstmc.protocol.bedrock.data.clock;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;
import lombok.Data;
import org.cloudburstmc.protocol.bedrock.data.TimeMarkerData;

@Data
public class AddTimeMarkerData {
  private long clockId;

  private final List<TimeMarkerData> timeMarkers = new ObjectArrayList<>();
}

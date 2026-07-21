package org.cloudburstmc.protocol.bedrock.data;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.lang.String;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorldClockData {
  private long id;

  private String name;

  private int time;

  private boolean isPaused;

  private final List<TimeMarkerData> timeMarkers = new ObjectArrayList<>();
}

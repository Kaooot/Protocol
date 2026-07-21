package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import lombok.Data;

@Data
public class TimeMarkerData {
  private long id;

  private String name;

  private int time;

  private int period;
}

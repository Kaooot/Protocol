package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import lombok.Data;

@Data
public class SystemCategory {
  private String categoryName;

  private long systemIndex;
}

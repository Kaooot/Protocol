package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import lombok.Data;

@Data
public class TrimMaterial {
  private String materialId;

  private String color;

  private String itemName;
}

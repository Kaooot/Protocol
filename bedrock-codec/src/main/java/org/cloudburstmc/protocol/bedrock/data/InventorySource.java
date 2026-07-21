package org.cloudburstmc.protocol.bedrock.data;

import java.lang.Integer;
import lombok.Data;

@Data
public class InventorySource {
  private InventorySourceType sourceType;

  private Integer containerID;

  private InventorySourceFlags bitFlags;
}

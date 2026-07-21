package org.cloudburstmc.protocol.bedrock.data;

import java.lang.Integer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventorySource {
  private InventorySourceType sourceType;

  private Integer containerID;

  private InventorySourceFlags bitFlags;
}

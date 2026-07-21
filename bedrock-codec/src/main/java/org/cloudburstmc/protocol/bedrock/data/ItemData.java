package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cloudburstmc.nbt.NbtMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemData {
  private String itemName;

  private int itemId;

  private boolean isComponentBased;

  private ItemVersion itemVersion;

  private NbtMap itemComponentData;
}

package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import lombok.Data;
import org.cloudburstmc.nbt.NbtMap;

@Data
public class ItemData {
  private String itemName;

  private int itemId;

  private boolean isComponentBased;

  private ItemVersion itemVersion;

  private NbtMap itemComponentData;
}

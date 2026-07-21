package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;

@Data
public class ItemUsed {
  private int itemId;

  private int itemAux;

  private int useMethod;

  private int count;
}

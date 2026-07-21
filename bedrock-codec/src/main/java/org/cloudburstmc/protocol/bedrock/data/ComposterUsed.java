package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;

@Data
public class ComposterUsed {
  private POIBlockInteractionType blockInteractionType;

  private int itemId;
}

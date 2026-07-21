package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;

@Data
public class CauldronUsed {
  private int contentsColor;

  private int contentsType;

  private int fillLevel;
}

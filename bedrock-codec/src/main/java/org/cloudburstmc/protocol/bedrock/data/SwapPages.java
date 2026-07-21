package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;

@Data
public class SwapPages {
  private int pageIndex;

  private int swapWithIndex;
}

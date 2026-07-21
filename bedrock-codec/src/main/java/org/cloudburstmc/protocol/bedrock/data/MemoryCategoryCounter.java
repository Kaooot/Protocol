package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;

@Data
public class MemoryCategoryCounter {
  private MemoryCategory category;

  private long currentBytes;
}

package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import lombok.Data;
import org.cloudburstmc.nbt.NbtMap;

@Data
public class DataStoreChange {
  private String dataStoreName;

  private String property;

  private int updateCount;

  private NbtMap theNewPropertyValue;
}

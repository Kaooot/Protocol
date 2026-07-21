package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cloudburstmc.nbt.NbtMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataStoreChange {
  private String dataStoreName;

  private String property;

  private int updateCount;

  private NbtMap theNewPropertyValue;
}

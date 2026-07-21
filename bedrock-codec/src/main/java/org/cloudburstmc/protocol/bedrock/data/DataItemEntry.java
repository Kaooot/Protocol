package org.cloudburstmc.protocol.bedrock.data;

import java.lang.Object;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataItemEntry {
  private int ID;

  private Object payload;
}

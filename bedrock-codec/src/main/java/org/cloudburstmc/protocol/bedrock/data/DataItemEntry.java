package org.cloudburstmc.protocol.bedrock.data;

import java.lang.Object;
import lombok.Data;

@Data
public class DataItemEntry {
  private int ID;

  private Object payload;
}

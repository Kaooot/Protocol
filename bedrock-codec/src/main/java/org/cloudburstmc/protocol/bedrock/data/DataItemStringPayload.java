package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import lombok.Data;

@Data
public class DataItemStringPayload {
  private DataItemType type;

  private String value;
}

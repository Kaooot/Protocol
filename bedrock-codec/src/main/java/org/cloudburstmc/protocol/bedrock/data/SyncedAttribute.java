package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import lombok.Data;

@Data
public class SyncedAttribute {
  private String attributeName;

  private float minValue;

  private float currentValue;

  private float maxValue;
}

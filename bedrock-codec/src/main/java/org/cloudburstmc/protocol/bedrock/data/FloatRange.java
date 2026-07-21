package org.cloudburstmc.protocol.bedrock.data;

import java.lang.Float;
import lombok.Data;

@Data
public class FloatRange {
  private Float min;

  private Float max;
}

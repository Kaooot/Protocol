package org.cloudburstmc.protocol.bedrock.data;

import java.lang.Float;
import lombok.Data;

@Data
public class FloatAttributeData {
  private float value;

  private FloatAttributeOperation operation;

  private Float constraintMin;

  private Float constraintMax;
}

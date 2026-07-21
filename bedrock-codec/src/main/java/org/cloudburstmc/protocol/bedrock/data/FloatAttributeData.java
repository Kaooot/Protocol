package org.cloudburstmc.protocol.bedrock.data;

import java.lang.Float;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FloatAttributeData {
  private float value;

  private FloatAttributeOperation operation;

  private Float constraintMin;

  private Float constraintMax;
}

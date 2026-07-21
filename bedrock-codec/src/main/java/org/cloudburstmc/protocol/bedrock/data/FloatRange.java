package org.cloudburstmc.protocol.bedrock.data;

import java.lang.Float;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FloatRange {
  private Float min;

  private Float max;
}

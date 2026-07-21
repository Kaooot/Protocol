package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttributeModifier {
  private String id;

  private String name;

  private float amount;

  private int operation;

  private int operand;

  private boolean isSerializable;
}

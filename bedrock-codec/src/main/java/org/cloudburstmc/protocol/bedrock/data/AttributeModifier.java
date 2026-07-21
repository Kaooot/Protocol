package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import lombok.Data;

@Data
public class AttributeModifier {
  private String id;

  private String name;

  private float amount;

  private int operation;

  private int operand;

  private boolean isSerializable;
}

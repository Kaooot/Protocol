package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;

@Data
public class ColorAttributeData {
  private Color255RGBA value;

  private ColorAttributeOperation operation;
}

package org.cloudburstmc.protocol.bedrock.data;

import java.awt.Color;
import java.lang.Float;
import java.lang.String;
import lombok.Data;
import org.cloudburstmc.protocol.common.util.OptionalBoolean;

@Data
public class TextDataPayload {
  private String text;

  private OptionalBoolean useRotation = OptionalBoolean.empty();

  private Color backgroundColor;

  private Float lineGapHeight;

  private OptionalBoolean depthTest = OptionalBoolean.empty();

  private OptionalBoolean showBackface = OptionalBoolean.empty();

  private OptionalBoolean showTextBackface = OptionalBoolean.empty();
}

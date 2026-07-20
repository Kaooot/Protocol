package org.cloudburstmc.protocol.bedrock.data;

import java.awt.Color;
import java.lang.String;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.common.util.OptionalBoolean;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TextDataPayload {
  private String text;

  private OptionalBoolean useRotation = OptionalBoolean.empty();

  private Color backgroundColor;

  private OptionalBoolean depthTest = OptionalBoolean.empty();

  private OptionalBoolean showBackface = OptionalBoolean.empty();

  private OptionalBoolean showTextBackface = OptionalBoolean.empty();
}

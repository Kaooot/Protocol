package org.cloudburstmc.protocol.bedrock.data;

import java.lang.Object;
import java.lang.String;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnvironmentAttributeData {
  private String attributeName;

  private Object fromAttribute;

  private Object attribute;

  private Object toAttribute;

  private int currentTransitionTicks;

  private int totalTransitionTicks;

  private EasingFunction easing;

  private int localTransitionTicks;

  private boolean noiseTransition;
}

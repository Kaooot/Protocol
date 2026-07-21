package org.cloudburstmc.protocol.bedrock.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttributeLayerSettings {
  private int priority;

  private float weight;

  private boolean enabled;

  private boolean transitionsPaused;
}

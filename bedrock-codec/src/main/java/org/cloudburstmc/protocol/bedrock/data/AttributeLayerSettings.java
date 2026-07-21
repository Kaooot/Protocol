package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;

@Data
public class AttributeLayerSettings {
  private int priority;

  private float weight;

  private boolean enabled;

  private boolean transitionsPaused;
}

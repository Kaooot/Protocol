package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;

@Data
public class Fade {
  private float duration;

  private float targetVolume;
}

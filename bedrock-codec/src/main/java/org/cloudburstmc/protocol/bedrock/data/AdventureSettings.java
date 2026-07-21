package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;

@Data
public class AdventureSettings {
  private boolean noPvm;

  private boolean noMvp;

  private boolean immutableWorld;

  private boolean showNameTags;

  private boolean autoJump;
}

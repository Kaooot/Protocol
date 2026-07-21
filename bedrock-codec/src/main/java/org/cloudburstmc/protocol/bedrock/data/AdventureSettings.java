package org.cloudburstmc.protocol.bedrock.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdventureSettings {
  private boolean noPvm;

  private boolean noMvp;

  private boolean immutableWorld;

  private boolean showNameTags;

  private boolean autoJump;
}

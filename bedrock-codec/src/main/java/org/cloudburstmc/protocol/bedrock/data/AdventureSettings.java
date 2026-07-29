package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;

/**
 * AdventureSettings for UpdateAdventureSettingsPacket
 *
 * @since v534
 */
@Data
public class AdventureSettings {
  /**
   * Whether player vs mob is enabled
   */
  private boolean noPvm;

  /**
   * Whether mob vs player is enabled
   */
  private boolean noMvp;

  /**
   * True when the world is immutable, false otherwise
   */
  private boolean immutableWorld;

  /**
   * If name tags should be shown
   */
  private boolean showNameTags;

  /**
   * Whether auto jumping should be allowed
   */
  private boolean autoJump;
}

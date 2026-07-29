package org.cloudburstmc.protocol.bedrock.data.education;

import lombok.Data;

/**
 * Edu only, see EducationLevelSettings
 *
 * @since v465
 */
@Data
public class AgentCapabilities {
  private boolean canModifyBlocks;
}

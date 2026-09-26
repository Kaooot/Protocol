package org.cloudburstmc.protocol.bedrock.data.education;

import lombok.Data;
import org.cloudburstmc.protocol.common.util.OptionalBoolean;

/**
 * Edu only, see EducationLevelSettings
 *
 * @since v465
 */
@Data
public class AgentCapabilities {

    private OptionalBoolean canModifyBlocks = OptionalBoolean.empty();
}
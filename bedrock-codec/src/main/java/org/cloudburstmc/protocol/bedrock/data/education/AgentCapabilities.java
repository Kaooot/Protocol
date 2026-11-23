package org.cloudburstmc.protocol.bedrock.data.education;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.annotation.Added;
import org.cloudburstmc.protocol.common.util.OptionalBoolean;

/**
 * Edu only, see EducationLevelSettings
 */
@Data
@Added("1.17.30")
public class AgentCapabilities {

    private OptionalBoolean canModifyBlocks = OptionalBoolean.empty();
}
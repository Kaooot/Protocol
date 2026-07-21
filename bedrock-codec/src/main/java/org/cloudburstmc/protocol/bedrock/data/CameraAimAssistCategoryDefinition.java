package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import lombok.Data;

@Data
public class CameraAimAssistCategoryDefinition {
  private String name;

  private CameraAimAssistCategoryPriorities priorities;
}

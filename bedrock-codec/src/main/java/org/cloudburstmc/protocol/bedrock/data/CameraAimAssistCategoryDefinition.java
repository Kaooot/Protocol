package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CameraAimAssistCategoryDefinition {
  private String cameraAimAssistCategoryIdentifier;

  private CameraAimAssistCategoryPriorities priorities;
}

package org.cloudburstmc.protocol.bedrock.data.camera.aimassist;

import lombok.Data;

@Data
public class CameraAimAssistCategoryDefinition {

    private String name;
    private CameraAimAssistCategoryPriorities priorities = new CameraAimAssistCategoryPriorities();
}
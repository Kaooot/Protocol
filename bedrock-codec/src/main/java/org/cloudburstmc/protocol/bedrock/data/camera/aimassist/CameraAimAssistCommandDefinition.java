package org.cloudburstmc.protocol.bedrock.data.camera.aimassist;

import lombok.Data;
import org.cloudburstmc.math.vector.Vector2f;

@Data
public class CameraAimAssistCommandDefinition {

    private String presetId;
    private AimAssistTargetMode targetMode;
    private Vector2f viewAngle;
    private Float distance;
}
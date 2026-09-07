package org.cloudburstmc.protocol.bedrock.data.camera.instruction;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.data.camera.EasingFunction;

@Data
public class CameraFovInstruction {

    private float fieldOfView;
    private float fovEaseTime;
    private EasingFunction fovEaseType;
    private boolean fieldOfViewClear;
}
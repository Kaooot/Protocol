package org.cloudburstmc.protocol.bedrock.data.camera;

import lombok.Data;

@Data
public class CameraFovInstruction {

    private float fieldOfView;
    private float fovEaseTime;
    private EasingFunction fovEaseType;
    private boolean fieldOfViewClear;
}
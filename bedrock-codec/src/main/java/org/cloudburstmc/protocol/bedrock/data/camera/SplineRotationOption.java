package org.cloudburstmc.protocol.bedrock.data.camera;

import lombok.Data;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.protocol.bedrock.annotation.Added;

@Data
public class SplineRotationOption {

    private Vector3f keyFrameValue;
    private float keyFrameTime;
    @Added("26.0")
    private EasingFunction keyFrameEasingFunc;
}
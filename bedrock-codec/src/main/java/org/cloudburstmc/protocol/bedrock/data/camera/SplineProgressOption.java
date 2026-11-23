package org.cloudburstmc.protocol.bedrock.data.camera;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.annotation.Added;

@Data
public class SplineProgressOption {

    private float keyFrameValue;
    private float keyFrameTime;
    @Added("26.0")
    private EasingFunction keyFrameEasingFunc;
}
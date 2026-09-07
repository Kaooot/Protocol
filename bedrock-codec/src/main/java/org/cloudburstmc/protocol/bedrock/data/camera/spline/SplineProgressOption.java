package org.cloudburstmc.protocol.bedrock.data.camera.spline;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.data.camera.EasingFunction;

@Data
public class SplineProgressOption {

    private float keyFrameValue;
    private float keyFrameTime;
    /**
     * @since v924
     */
    private EasingFunction keyFrameEasingFunc;
}
package org.cloudburstmc.protocol.bedrock.data.camera.spline;

import lombok.Data;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.protocol.bedrock.data.camera.EasingFunction;

@Data
public class SplineRotationOption {

    private Vector3f keyFrameValue;
    private float keyFrameTime;
    /**
     * @since v924
     */
    private EasingFunction keyFrameEasingFunc;
}
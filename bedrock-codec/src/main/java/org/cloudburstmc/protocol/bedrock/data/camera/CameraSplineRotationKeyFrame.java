package org.cloudburstmc.protocol.bedrock.data.camera;

import lombok.Value;
import org.cloudburstmc.math.vector.Vector3f;

/**
 * @author Kaooot
 */
@Value
public class CameraSplineRotationKeyFrame {

    Vector3f rotation;
    float time;
    EasingType easing;
}
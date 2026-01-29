package org.cloudburstmc.protocol.bedrock.data.camera;

import lombok.Value;

/**
 * @author Kaooot
 */
@Value
public class CameraSplineProgressKeyFrame {

    float progress;
    float time;
    EasingType easing;
}
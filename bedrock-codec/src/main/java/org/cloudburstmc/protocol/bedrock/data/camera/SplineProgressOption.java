package org.cloudburstmc.protocol.bedrock.data.camera;

import lombok.Data;

@Data
public class SplineProgressOption {

    private float keyFrameValue;
    private float keyFrameTime;
    /**
     * @since v924
     */
    private EasingFunction keyFrameEasingFunc;
}
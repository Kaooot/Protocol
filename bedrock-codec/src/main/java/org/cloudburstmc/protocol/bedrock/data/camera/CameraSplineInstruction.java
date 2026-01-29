package org.cloudburstmc.protocol.bedrock.data.camera;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.cloudburstmc.math.vector.Vector2f;
import org.cloudburstmc.math.vector.Vector3f;

import java.util.List;

/**
 * @author Kaooot
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CameraSplineInstruction {
    private float totalTime;
    private CameraSplineType type;
    private List<Vector3f> curve = new ObjectArrayList<>();
    private List<SplineProgressOption> progressKeyFrames = new ObjectArrayList<>();
    private List<SplineRotationOption> rotationOption = new ObjectArrayList<>();
    /**
     * @since v924
     */
    private String splineIdentifier;
    /**
     * @since v924
     */
    private boolean loadFromJSON;

    @Value
    public static class SplineRotationOption {
        /**
         * @deprecated since v924
         */
        Vector3f keyFrameValues;
        /**
         * @deprecated since v924
         */
        float keyFrameTimes;
        /**
         * @since v924
         */
        Vector3f keyFrameValue;
        /**
         * @since v924
         */
        float keyFrameTime;
        /**
         * @since v924
         */
        EasingType keyFrameEasingFunc;
    }

    @Value
    public static class SplineProgressOption {
        float keyFrameValue;
        float keyFrameTime;
        EasingType keyFrameEasingFunc;
    }
}
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
    private List<Vector2f> progressKeyFrames = new ObjectArrayList<>();
    private List<SplineRotationOption> rotationOption = new ObjectArrayList<>();

    @Value
    public static class SplineRotationOption {
        Vector3f keyFrameValues;
        float keyFrameTimes;
    }
}
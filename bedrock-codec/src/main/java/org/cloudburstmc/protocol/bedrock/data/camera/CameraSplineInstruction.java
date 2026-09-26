package org.cloudburstmc.protocol.bedrock.data.camera;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;
import org.cloudburstmc.math.vector.Vector3f;

import java.util.List;

@Data
public class CameraSplineInstruction {

    private float totalTime;
    private CameraSplineType type;
    private final List<Vector3f> curve = new ObjectArrayList<>();
    private final List<SplineProgressOption> progressKeyFrames = new ObjectArrayList<>();
    private final List<SplineRotationOption> rotationOption = new ObjectArrayList<>();
    /**
     * @since v924
     */
    private String splineIdentifier;
    /**
     * @since v924
     */
    private boolean loadFromJson;
}
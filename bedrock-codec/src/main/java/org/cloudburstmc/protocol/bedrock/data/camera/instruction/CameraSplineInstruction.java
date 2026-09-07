package org.cloudburstmc.protocol.bedrock.data.camera.instruction;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.protocol.bedrock.data.camera.CameraSplineType;
import org.cloudburstmc.protocol.bedrock.data.camera.spline.SplineProgressOption;
import org.cloudburstmc.protocol.bedrock.data.camera.spline.SplineRotationOption;

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
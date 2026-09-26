package org.cloudburstmc.protocol.bedrock.data.camera;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;

import java.util.List;

@Data
public class CameraSplineDefinition {

    private String name;
    private float totalTime;
    private CameraSplineType splineType;
    private final List<CameraSplineControlPoint> controlPoints = new ObjectArrayList<>();
    private final List<CameraSplineProgressKeyFrame> progressKeyFrames = new ObjectArrayList<>();
    private final List<CameraSplineRotationKeyFrame> rotationKeyFrames = new ObjectArrayList<>();
    /**
     * @since v944
     */
    private String splineIdentifier;
    /**
     * @since v944
     */
    private boolean loadFromJson;
}
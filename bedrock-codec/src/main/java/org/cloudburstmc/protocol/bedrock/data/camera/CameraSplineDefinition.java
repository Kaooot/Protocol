package org.cloudburstmc.protocol.bedrock.data.camera;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;
import org.cloudburstmc.protocol.bedrock.annotation.Added;

import java.util.List;

@Data
public class CameraSplineDefinition {

    private String name;
    private float totalTime;
    private CameraSplineType splineType;
    private final List<CameraSplineControlPoint> controlPoints = new ObjectArrayList<>();
    private final List<CameraSplineProgressKeyFrame> progressKeyFrames = new ObjectArrayList<>();
    private final List<CameraSplineRotationKeyFrame> rotationKeyFrames = new ObjectArrayList<>();
    @Added("26.10")
    private String splineIdentifier;
    @Added("26.10")
    private boolean loadFromJson;
}
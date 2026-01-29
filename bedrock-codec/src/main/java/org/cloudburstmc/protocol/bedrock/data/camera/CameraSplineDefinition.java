package org.cloudburstmc.protocol.bedrock.data.camera;

import lombok.Value;

import java.util.List;

/**
 * @author Kaooot
 */
@Value
public class CameraSplineDefinition {

    String name;
    float totalTime;
    CameraSplineType splineType;
    List<CameraSplineControlPoint> controlPoints;
    List<CameraSplineProgressKeyFrame> progressKeyFrames;
    List<CameraSplineRotationKeyFrame> rotationKeyFrames;
}
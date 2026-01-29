package org.cloudburstmc.protocol.bedrock.data.camera;

import lombok.Value;
import org.cloudburstmc.math.vector.Vector3f;

/**
 * @author Kaooot
 */
@Value
public class CameraSplineControlPoint {

    Vector3f position;
}
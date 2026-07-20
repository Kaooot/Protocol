package org.cloudburstmc.protocol.bedrock.data;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.lang.String;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CameraSplineDefinition {
  private String name;

  private float totalTime;

  private String splineType;

  private final List<CameraSplineControlPoint> controlPoints = new ObjectArrayList<>();

  private final List<CameraSplineProgressKeyFrame> progressKeyFrames = new ObjectArrayList<>();

  private final List<CameraSplineRotationKeyFrame> rotationKeyFrames = new ObjectArrayList<>();
}

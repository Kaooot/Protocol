package org.cloudburstmc.protocol.bedrock.data;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.lang.String;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.protocol.common.util.OptionalBoolean;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CameraSplineInstruction {
  private float totalTime;

  private int type;

  private final List<Vector3f> curve = new ObjectArrayList<>();

  private final List<SplineProgressOption> progressKeyFrames = new ObjectArrayList<>();

  private final List<SplineRotationOption> rotationOption = new ObjectArrayList<>();

  private String splineIdentifier;

  private OptionalBoolean loadFromJson = OptionalBoolean.empty();
}

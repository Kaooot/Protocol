package org.cloudburstmc.protocol.bedrock.data;

import java.awt.Color;
import java.lang.Float;
import java.lang.Long;
import java.lang.Object;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cloudburstmc.math.vector.Vector3f;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrimitiveShapeDataPayload {
  private long networkId;

  private ScriptPrimitiveShapeType shapeType;

  private Vector3f location;

  private Float scale;

  private Vector3f rotation;

  private Float totalTimeLeft;

  private Float maximumRenderDistance;

  private Color color;

  private DimensionType dimensionID;

  private Long attachedToEntityID;

  private Object extraShapeData;
}

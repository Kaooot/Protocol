package org.cloudburstmc.protocol.bedrock.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cloudburstmc.math.vector.Vector2f;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConeDataPayload {
  private Vector2f radii;

  private float height;

  private int numSegments;
}

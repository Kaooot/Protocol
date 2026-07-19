package org.cloudburstmc.protocol.bedrock.data;

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
public class MoveActorAbsoluteData {
  private long ActorRuntimeID;

  private int Header;

  private Vector3f Position;

  private int RotationX;

  private int RotationY;

  private int RotationYHead;
}

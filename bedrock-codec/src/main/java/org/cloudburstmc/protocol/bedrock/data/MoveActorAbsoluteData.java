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
  private long actorRuntimeID;

  private int header;

  private Vector3f position;

  private int rotationX;

  private int rotationY;

  private int rotationYHead;
}

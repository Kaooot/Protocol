package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;
import org.cloudburstmc.math.vector.Vector3f;

@Data
public class MoveActorAbsoluteData {
  private long actorRuntimeID;

  private int header;

  private Vector3f position;

  private int rotationX;

  private int rotationY;

  private int rotationYHead;
}

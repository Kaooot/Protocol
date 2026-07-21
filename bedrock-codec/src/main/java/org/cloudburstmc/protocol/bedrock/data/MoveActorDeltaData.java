package org.cloudburstmc.protocol.bedrock.data;

import java.lang.Float;
import java.lang.Integer;
import lombok.Data;

@Data
public class MoveActorDeltaData {
  private long actorRuntimeID;

  private Float newPositionX;

  private Float newPositionY;

  private Float newPositionZ;

  private Integer rotationX;

  private Integer rotationY;

  private Integer rotationYHead;

  private boolean isOnGround;

  private boolean forceMove;

  private boolean forceMoveLocalEntity;

  private boolean forceCompletion;
}

package org.cloudburstmc.protocol.bedrock.data;

import java.lang.Float;
import java.lang.Integer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MoveActorDeltaData {
  private long ActorRuntimeID;

  private Float NewPositionX;

  private Float NewPositionY;

  private Float NewPositionZ;

  private Integer RotationX;

  private Integer RotationY;

  private Integer RotationYHead;

  private boolean IsOnGround;

  private boolean ForceMove;

  private boolean ForceMoveLocalEntity;

  private boolean ForceCompletion;
}

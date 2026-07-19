package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.math.vector.Vector3i;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StructureSettings {
  private String StructurePaletteName;

  private boolean Shouldignoreentities;

  private boolean Shouldignoreblocks;

  private boolean ShouldAllowNonTickingPlayerandTickingAreaChunks;

  private Vector3i StructureSize;

  private Vector3i StructureOffset;

  private long LastEditPlayer;

  private Rotation Rotation;

  private Mirror Mirror;

  private AnimationMode AnimationMode;

  private float AnimationSeconds;

  private float IntegrityValue;

  private int IntegritySeed;

  private Vector3f RotationPivot;
}

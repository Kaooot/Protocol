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
  private String structurePaletteName;

  private boolean shouldIgnoreEntities;

  private boolean shouldIgnoreBlocks;

  private boolean shouldAllowNonTickingPlayerAndTickingAreaChunks;

  private Vector3i structureSize;

  private Vector3i structureOffset;

  private long lastEditPlayer;

  private Rotation rotation;

  private Mirror mirror;

  private AnimationMode animationMode;

  private float animationSeconds;

  private float integrityValue;

  private int integritySeed;

  private Vector3f rotationPivot;
}

package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import lombok.Data;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.protocol.bedrock.data.structure.AnimationMode;

@Data
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

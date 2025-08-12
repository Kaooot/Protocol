package org.cloudburstmc.protocol.bedrock.data.structure;

import lombok.Value;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.math.vector.Vector3i;

@Value
public class StructureSettings {
    private final String structurePaletteName;
    private final boolean shouldIgnoreEntities;
    private final boolean shouldIgnoreBlocks;
    private final boolean shouldAllowNonTickingPlayerAndTickingAreaChunks;
    private final Vector3i structureSize;
    private final Vector3i structureOffset;
    private final long lastEditPlayer;
    private final Rotation rotation;
    private final Mirror mirror;
    private final AnimationMode animationMode;
    private final float animationSeconds;
    private final float integrityValue;
    private final int integritySeed;
    private final Vector3f rotationPivot;
}

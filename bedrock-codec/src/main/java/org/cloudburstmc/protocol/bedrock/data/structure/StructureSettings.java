package org.cloudburstmc.protocol.bedrock.data.structure;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.protocol.bedrock.annotation.Added;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StructureSettings {

    private String structurePaletteName;
    private boolean shouldIgnoreEntities;
    private boolean shouldIgnoreBlocks;
    @Added("1.18.30")
    private boolean shouldAllowNonTickingPlayerAndTickingAreaChunks;
    private Vector3i structureSize;
    private Vector3i structureOffset;
    private long lastEditPlayer;
    private Rotation rotation;
    private Mirror mirror;
    @Added("1.17.0")
    private AnimationMode animationMode;
    @Added("1.17.0")
    private float animationSeconds;
    private float integrityValue;
    private int integritySeed;
    @Added("1.13.0")
    private Vector3f rotationPivot;
}
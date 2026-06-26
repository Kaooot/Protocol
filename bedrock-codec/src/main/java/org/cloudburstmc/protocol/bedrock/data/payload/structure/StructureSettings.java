package org.cloudburstmc.protocol.bedrock.data.payload.structure;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.math.vector.Vector3i;

/**
 * @author Kaooot
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StructureSettings {

    private String structurePaletteName;
    private boolean shouldIgnoreEntities;
    private boolean shouldIgnoreBlocks;
    /**
     * @since v503
     */
    private boolean shouldAllowNonTickingPlayerAndTickingAreaChunks;
    private Vector3i structureSize;
    private Vector3i structureOffset;
    private long lastEditPlayer;
    private Rotation rotation;
    private Mirror mirror;
    /**
     * @since v440
     */
    private AnimationMode animationMode;
    /**
     * @since v440
     */
    private float animationSeconds;
    private float integrityValue;
    private int integritySeed;
    /**
     * @since v388
     */
    private Vector3f rotationPivot;
}
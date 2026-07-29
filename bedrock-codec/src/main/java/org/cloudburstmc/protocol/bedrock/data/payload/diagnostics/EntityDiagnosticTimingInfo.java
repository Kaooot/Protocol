package org.cloudburstmc.protocol.bedrock.data.payload.diagnostics;

import lombok.Data;
import org.cloudburstmc.math.vector.Vector3f;

/**
 * @author Kaooot
 */
@Data
public class EntityDiagnosticTimingInfo {

    private String displayName;
    private String entity;
    private long timeInNS;
    private int percentOfTotal;
    /**
     * @since v2177
     */
    private Vector3f position;
    /**
     * @since v2177
     */
    private String dimension;
}
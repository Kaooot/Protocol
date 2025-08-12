package org.cloudburstmc.protocol.bedrock.data.actor;

import lombok.Data;
import lombok.ToString;
import org.cloudburstmc.math.vector.Vector3f;

/**
 * @author Kaooot
 */
@Data
@ToString
public class MoveActorAbsoluteData {

    private long actorRuntimeID;
    private boolean onGround;
    private boolean teleported;
    private boolean forceMove;
    private Vector3f pos;
    private Vector3f rotation;
}
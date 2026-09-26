package org.cloudburstmc.protocol.bedrock.data.actor;

import lombok.Data;
import org.cloudburstmc.math.vector.Vector3f;

@Data
public class MoveActorAbsoluteData {

    private long actorRuntimeID;
    private boolean onGround;
    private boolean teleported;
    private boolean forceMove;
    private boolean forceCompletion;
    private Vector3f pos;
    private Vector3f rotation;
}
package org.cloudburstmc.protocol.bedrock.data.camera.instruction;

import lombok.Data;
import org.cloudburstmc.math.vector.Vector3f;

@Data
public class CameraTargetInstruction {

    private Vector3f targetCenterOffset;
    private long targetActorID;
}
package org.cloudburstmc.protocol.bedrock.data.camera;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.protocol.bedrock.docs.Docs;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Docs(name = "CameraInstruction::TargetInstruction")
public class CameraTargetInstruction {
    private Vector3f targetCenterOffset;
    private long targetActorID;
}
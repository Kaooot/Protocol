package org.cloudburstmc.protocol.bedrock.data.camera;

import lombok.Data;

@Data
public class CameraAttachToEntityInstruction {

    private long entityActorID;
}
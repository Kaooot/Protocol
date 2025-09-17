package org.cloudburstmc.protocol.bedrock.data.camera;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.docs.DataType;
import org.cloudburstmc.protocol.bedrock.docs.Docs;
import org.cloudburstmc.protocol.bedrock.docs.Version;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Docs(name = "CameraInstruction::FovInstruction", since = Version.V827)
public class CameraFovInstruction {
    @Docs(name = "Field of View")
    private float fov;
    @Docs(name = "FOV Ease Time")
    private float easeTime;
    @Docs(name = "FOV Ease Type", type = DataType.BYTE)
    private EasingType easeType;
    @Docs(name = "Field of View Clear")
    private boolean clear;
}

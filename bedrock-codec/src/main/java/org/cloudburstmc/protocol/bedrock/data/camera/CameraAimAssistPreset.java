package org.cloudburstmc.protocol.bedrock.data.camera;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.cloudburstmc.math.vector.Vector2f;
import org.cloudburstmc.protocol.bedrock.packet.CameraAimAssistPacket;

@Data
@Builder
@AllArgsConstructor
public class CameraAimAssistPreset {
    private String presetId;
    private Integer targetMode;
    private Vector2f viewAngle;
    private Float distance;
}

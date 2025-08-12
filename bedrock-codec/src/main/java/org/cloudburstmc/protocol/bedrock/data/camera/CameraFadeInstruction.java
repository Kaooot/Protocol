package org.cloudburstmc.protocol.bedrock.data.camera;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CameraFadeInstruction {
    private TimeOption time;
    private Color color;

    @Data
    public static class TimeOption {
        private final float fadeInTime;
        private final float holdTime;
        private final float fadeOutTime;
    }
}

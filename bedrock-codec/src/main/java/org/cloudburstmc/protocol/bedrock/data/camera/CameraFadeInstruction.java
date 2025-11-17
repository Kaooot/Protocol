package org.cloudburstmc.protocol.bedrock.data.camera;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CameraFadeInstruction {
    private TimeOption time;
    private ColorOption color;

    @Data
    public static class TimeOption {
        private final float fadeInTime;
        private final float holdTime;
        private final float fadeOutTime;
    }

    @Data
    public static class ColorOption {
        private final float red;
        private final float green;
        private final float blue;
    }
}

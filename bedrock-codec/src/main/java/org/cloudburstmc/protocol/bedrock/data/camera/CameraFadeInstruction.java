package org.cloudburstmc.protocol.bedrock.data.camera;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.docs.Docs;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Docs(name = "CameraInstruction::FadeInstruction")
public class CameraFadeInstruction {
    private TimeOption time;
    private ColorOption color;

    @Data
    @Docs(name = "CameraInstruction::FadeInstruction::TimeOption")
    public static class TimeOption {
        private final float fadeInTime;
        private final float holdTime;
        private final float fadeOutTime;
    }

    @Data
    @Docs(name = "CameraInstruction::FadeInstruction::ColorOption")
    public static class ColorOption {
        private final float red;
        private final float green;
        private final float blue;
    }
}

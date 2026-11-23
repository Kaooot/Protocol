package org.cloudburstmc.protocol.bedrock.data.attribute;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.annotation.Added;
import org.cloudburstmc.protocol.bedrock.data.camera.EasingFunction;
import org.cloudburstmc.protocol.bedrock.data.structure.NoiseAlignment;

@Data
public class NoiseTransitionSettingsData {

    private int totalTransitionTicks;
    private int currentTransitionTicks;
    private EasingFunction easing;
    @Added("26.60")
    private String clockName;
    @Added("26.30")
    private int localTransitionTicks;
    @Added("26.60")
    private String noiseName;
    private NoiseAlignment noiseAlignment;
}
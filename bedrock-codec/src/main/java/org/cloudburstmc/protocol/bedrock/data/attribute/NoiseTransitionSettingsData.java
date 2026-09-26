package org.cloudburstmc.protocol.bedrock.data.attribute;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.data.camera.EasingFunction;
import org.cloudburstmc.protocol.bedrock.data.structure.NoiseAlignment;

@Data
public class NoiseTransitionSettingsData {

    private int totalTransitionTicks;
    private int currentTransitionTicks;
    private EasingFunction easing;
    private String clockName;
    private int localTransitionTicks;
    private String noiseName;
    private NoiseAlignment noiseAlignment;
}
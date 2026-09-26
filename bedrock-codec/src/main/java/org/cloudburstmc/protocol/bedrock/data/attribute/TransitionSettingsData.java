package org.cloudburstmc.protocol.bedrock.data.attribute;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.data.camera.EasingFunction;

@Data
public class TransitionSettingsData {

    private int totalTransitionTicks;
    private int currentTransitionTicks;
    private EasingFunction easing;
    private String clockName;
}
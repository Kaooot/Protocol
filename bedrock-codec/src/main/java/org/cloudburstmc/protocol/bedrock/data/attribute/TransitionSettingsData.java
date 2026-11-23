package org.cloudburstmc.protocol.bedrock.data.attribute;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.annotation.Added;
import org.cloudburstmc.protocol.bedrock.data.camera.EasingFunction;

@Data
public class TransitionSettingsData {

    private int totalTransitionTicks;
    private int currentTransitionTicks;
    private EasingFunction easing;
    @Added("26.60")
    private String clockName;
}
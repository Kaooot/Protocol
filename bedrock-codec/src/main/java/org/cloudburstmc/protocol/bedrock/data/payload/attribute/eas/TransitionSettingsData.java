package org.cloudburstmc.protocol.bedrock.data.payload.attribute.eas;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.data.camera.EasingFunction;

/**
 * @author Kaooot
 */
@Data
public class TransitionSettingsData {

    private int totalTransitionTicks;
    private int currentTransitionTicks;
    private EasingFunction easing;
    private String clockName;
}

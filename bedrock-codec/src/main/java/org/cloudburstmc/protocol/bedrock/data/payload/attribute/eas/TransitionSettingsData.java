package org.cloudburstmc.protocol.bedrock.data.payload.attribute.eas;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.data.camera.EasingType;

/**
 * @author Kaooot
 */
@Data
public class TransitionSettingsData {

    private int totalTransitionTicks;
    private int currentTransitionTicks;
    private EasingType easing;
    private String clockName;
}

package org.cloudburstmc.protocol.bedrock.data.payload.attribute.eas;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author Kaooot
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class NoiseTransitionSettingsData extends TransitionSettingsData {

    private int localTransitionTicks;
    private String noiseName;
    private NoiseAlignment noiseAlignment;
}

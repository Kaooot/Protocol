package org.cloudburstmc.protocol.bedrock.data.payload.attribute.eas;

import lombok.Data;

/**
 * @author Kaooot
 */
@Data
public class NoiseTransitionAttributeData implements EnvironmentAttributePayload {

    private EASAttributeData fromAttribute;
    private EASAttributeData toAttribute;
    private NoiseTransitionSettingsData settings;

    @Override
    public EnvironmentAttributePayloadType getType() {
        return EnvironmentAttributePayloadType.NOISE_TRANSITION;
    }
}

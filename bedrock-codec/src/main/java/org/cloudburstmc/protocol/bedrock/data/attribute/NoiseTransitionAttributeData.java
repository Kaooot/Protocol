package org.cloudburstmc.protocol.bedrock.data.attribute;

import lombok.Data;

@Data
public class NoiseTransitionAttributeData implements EnvironmentAttributePayload {

    @Override
    public EnvironmentAttributePayloadType getType() {
        return EnvironmentAttributePayloadType.NOISE_TRANSITION;
    }


    private Object fromAttribute;
    private Object toAttribute;
    private NoiseTransitionSettingsData settings;
}
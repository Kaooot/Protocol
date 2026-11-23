package org.cloudburstmc.protocol.bedrock.data.attribute;

import lombok.Data;

@Data
public class TransitionAttributeData implements EnvironmentAttributePayload {

    @Override
    public EnvironmentAttributePayloadType getType() {
        return EnvironmentAttributePayloadType.TRANSITION;
    }


    private Object fromAttribute;
    private Object toAttribute;
    private TransitionSettingsData settings;
}
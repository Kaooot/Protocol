package org.cloudburstmc.protocol.bedrock.data.payload.attribute.eas;

import lombok.Data;

/**
 * @author Kaooot
 */
@Data
public class TransitionAttributeData implements EnvironmentAttributePayload {

    private EASAttributeData fromAttribute;
    private EASAttributeData toAttribute;
    private TransitionSettingsData settings;

    @Override
    public EnvironmentAttributePayloadType getType() {
        return EnvironmentAttributePayloadType.TRANSITION;
    }
}

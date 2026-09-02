package org.cloudburstmc.protocol.bedrock.data.payload.attribute.eas;

import lombok.Data;

/**
 * @author Kaooot
 */
@Data
public class ConstantAttributeData implements EnvironmentAttributePayload {

    private EASAttributeData attribute;

    @Override
    public EnvironmentAttributePayloadType getType() {
        return EnvironmentAttributePayloadType.CONSTANT;
    }
}

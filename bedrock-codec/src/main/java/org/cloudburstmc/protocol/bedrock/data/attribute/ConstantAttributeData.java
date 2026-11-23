package org.cloudburstmc.protocol.bedrock.data.attribute;

import lombok.Data;

@Data
public class ConstantAttributeData implements EnvironmentAttributePayload {

    @Override
    public EnvironmentAttributePayloadType getType() {
        return EnvironmentAttributePayloadType.CONSTANT;
    }


    private Object attribute;
}
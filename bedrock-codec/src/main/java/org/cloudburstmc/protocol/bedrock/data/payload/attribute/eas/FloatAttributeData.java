package org.cloudburstmc.protocol.bedrock.data.payload.attribute.eas;

import lombok.Data;

/**
 * @author Kaooot
 */
@Data
public class FloatAttributeData implements AttributeData {

    private float value;
    private FloatAttributeOperation operation;
    private Float constraintMin;
    private Float constraintMax;

    @Override
    public AttributeDataType getType() {
        return AttributeDataType.FLOAT;
    }
}
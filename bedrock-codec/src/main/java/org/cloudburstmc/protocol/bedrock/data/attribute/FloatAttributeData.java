package org.cloudburstmc.protocol.bedrock.data.attribute;

import lombok.Data;

@Data
public class FloatAttributeData implements AttributeData {

    @Override
    public AttributeDataType getType() {
        return AttributeDataType.FLOAT;
    }


    private float value;
    private FloatAttributeOperation operation;
    private Float constraintMin;
    private Float constraintMax;
}

package org.cloudburstmc.protocol.bedrock.data.attribute;

import lombok.Data;

@Data
public class BoolAttributeData implements AttributeData {

    @Override
    public AttributeDataType getType() {
        return AttributeDataType.BOOL;
    }


    private boolean value;
    private BoolAttributeOperation operation;
}
package org.cloudburstmc.protocol.bedrock.data.attribute;

import lombok.Data;

@Data
public class StringAttributeData implements AttributeData {

    @Override
    public AttributeDataType getType() {
        return AttributeDataType.STRING;
    }


    private String value;
    private StringAttributeOperation operation;
}
package org.cloudburstmc.protocol.bedrock.data.attribute;

import lombok.Data;

@Data
public class ColorAttributeData implements AttributeData {

    @Override
    public AttributeDataType getType() {
        return AttributeDataType.COLOR;
    }


    private Color255RGBA color;
    private ColorAttributeOperation operation;
}

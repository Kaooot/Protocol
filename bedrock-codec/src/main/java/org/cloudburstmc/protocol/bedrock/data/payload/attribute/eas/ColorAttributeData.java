package org.cloudburstmc.protocol.bedrock.data.payload.attribute.eas;

import lombok.Data;

/**
 * @author Kaooot
 */
@Data
public class ColorAttributeData implements AttributeData {

    private Integer color;
    private ColorAttributeOperation operation;

    @Override
    public AttributeDataType getType() {
        return AttributeDataType.COLOR;
    }
}
package org.cloudburstmc.protocol.bedrock.data.payload.attribute.eas;

import lombok.Data;

/**
 * @author Kaooot
 */
@Data
public class BoolAttributeData implements EASAttributeData {

    private boolean value;
    private BoolAttributeOperation operation;

    @Override
    public AttributeDataType getType() {
        return AttributeDataType.BOOL;
    }
}
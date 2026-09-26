package org.cloudburstmc.protocol.bedrock.data.attribute;

import lombok.Data;

@Data
public class BoolAttributeData {

    private boolean value;
    private BoolAttributeOperation operation;
}
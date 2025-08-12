package org.cloudburstmc.protocol.bedrock.data.attribute;

import lombok.Value;

@Value
public class AttributeModifierData {
    String id;
    String name;
    float amount;
    AttributeModifierOperation operation;
    AttributeOperands operand;
    boolean isSerializable;
}
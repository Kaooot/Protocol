package org.cloudburstmc.protocol.bedrock.data.payload.attribute;

import lombok.Value;

@Value
public class AttributeModifier {
    String id;
    String name;
    float amount;
    AttributeModifierOperation operation;
    AttributeOperands operand;
    boolean isSerializable;
}
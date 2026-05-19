package org.cloudburstmc.protocol.bedrock.data.payload.attribute;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.Collections;
import java.util.List;

@Value
@AllArgsConstructor
public class AttributeData {
    String attributeName;
    float minValue;
    float maxValue;
    float currentValue;
    float defaultMinValue;
    float defaultMaxValue;
    float defaultValue;
    List<AttributeModifier> modifiers;

    public AttributeData(String attributeName, float minValue, float maxValue, float currentValue) {
        this(attributeName, minValue, maxValue, currentValue, maxValue, Collections.emptyList());
    }

    public AttributeData(String attributeName, float minValue, float maxValue, float currentValue, float defaultValue) {
        this(attributeName, minValue, maxValue, currentValue, defaultValue, Collections.emptyList());
    }

    public AttributeData(String attributeName, float minValue, float maxValue, float currentValue, float defaultValue, List<AttributeModifier> modifiers) {
        this(attributeName, minValue, maxValue, currentValue, minValue, maxValue, defaultValue, modifiers);
    }
}

package org.cloudburstmc.protocol.bedrock.data.attribute;

import lombok.Data;

@Data
public class NoiseTransitionAttributeData {

    private Object fromAttribute;
    private Object toAttribute;
    private NoiseTransitionSettingsData settings;
}
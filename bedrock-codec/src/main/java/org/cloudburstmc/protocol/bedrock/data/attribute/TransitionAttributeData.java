package org.cloudburstmc.protocol.bedrock.data.attribute;

import lombok.Data;

@Data
public class TransitionAttributeData {

    private Object fromAttribute;
    private Object toAttribute;
    private TransitionSettingsData settings;
}
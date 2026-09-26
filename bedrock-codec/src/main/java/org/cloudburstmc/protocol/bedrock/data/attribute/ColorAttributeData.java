package org.cloudburstmc.protocol.bedrock.data.attribute;

import lombok.Data;

@Data
public class ColorAttributeData {

    private Color255RGBA color;
    private ColorAttributeOperation operation;
}

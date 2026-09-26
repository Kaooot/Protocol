package org.cloudburstmc.protocol.bedrock.data.attribute;

import lombok.Data;

@Data
public class FloatOverride {

    private UpdateType type;
    private float value;
}
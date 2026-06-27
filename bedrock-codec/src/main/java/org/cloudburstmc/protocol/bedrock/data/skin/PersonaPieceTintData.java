package org.cloudburstmc.protocol.bedrock.data.skin;

import lombok.Value;

import java.util.List;

@Deprecated
@Value
public class PersonaPieceTintData {
    private final String type;
    private final List<String> colors;
}

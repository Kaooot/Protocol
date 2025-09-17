package org.cloudburstmc.protocol.bedrock.data;

import lombok.Value;
import org.cloudburstmc.protocol.bedrock.docs.Docs;

@Docs
@Value
public class Experiment {
    private final String name;
    private final boolean enabled; // ??? Always set to true
}

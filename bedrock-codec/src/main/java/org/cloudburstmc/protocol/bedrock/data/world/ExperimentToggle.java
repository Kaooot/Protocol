package org.cloudburstmc.protocol.bedrock.data.world;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExperimentToggle {

    private String name;
    private boolean enabled;
}